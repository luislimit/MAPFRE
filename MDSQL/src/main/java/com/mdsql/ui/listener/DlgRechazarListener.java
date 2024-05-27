package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.DlgRechazar;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.UIHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DlgRechazarListener extends ListenerSupport implements ActionListener {

	private DlgRechazar dlgRechazar;

	public DlgRechazarListener(DlgRechazar dlgRechazar) {
		super();
		this.dlgRechazar = dlgRechazar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.DLG_RECHAZAR_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			Integer response = UIHelper.showConfirm("Al rechazar el procesado en curso,\n"
					+ "se desecharán los scripts actuales, incluidos aquellos \n que se han ejecutado sin errores, "
					+ "¿desea continuar?", "Rechazar");

			if (response == JOptionPane.YES_OPTION) {
				rechazarProceso();
			}
		}

		if (MDSQLConstants.DLG_RECHAZAR_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			dlgRechazar.getReturnParams().put("comando", "CANCELAR");
			dlgRechazar.dispose();
		}
	}

	private void rechazarProceso() {
		try {
			ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

			Proceso proceso = (Proceso) dlgRechazar.getParams().get("proceso");
			String txtMotivoRechazo = dlgRechazar.getTxtMotivoRechazo().getText();
			procesoService.rechazarProcesado(proceso.getIdProceso(), txtMotivoRechazo, session.getCodUsr());

			proceso.setDescripcionEstadoProceso("Rechazado");

			// Borrar los scripts lanza y renombrar los logs
			borrarScriptsLanza(proceso);
			rechazarScripts(proceso);

			dlgRechazar.getReturnParams().put("proceso", proceso);
			dlgRechazar.dispose();

		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(dlgRechazar.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	/**
	 * Borra los scripts lanza si se han generado
	 * 
	 * @param proceso
	 */
	private void borrarScriptsLanza(Proceso proceso) {

		List<Script> scripts = proceso.getScripts();
		String ruta = proceso.getRutaTrabajo();

		borrarScriptsFromList(scripts, ruta);
		borrarScriptsFromTypes(proceso, ruta);
	}

	private void borrarScriptsFromTypes(Proceso proceso, String ruta) {
		String lanzaFile = StringUtils.EMPTY;
		String logFile = StringUtils.EMPTY;

		if (StringUtils.isNotBlank(ruta) && CollectionUtils.isNotEmpty(proceso.getTypes())) {
			try {
				lanzaFile = ruta.concat(proceso.getScriptLanza().getNombreScript());
				logFile = MDSQLAppHelper.getLogFor(proceso.getScriptLanza().getNombreScript());
				Files.delete(Paths.get(lanzaFile));
				Files.delete(Paths.get(ruta.concat(logFile)));
			} catch (IOException e) {
				LogWrapper.warn(log, "No existe el fichero %s", lanzaFile);
			}
		}
	}

	private void borrarScriptsFromList(List<Script> scripts, String ruta) {
		if (StringUtils.isNotBlank(ruta) && CollectionUtils.isNotEmpty(scripts)) {
			for (Script script : scripts) {
				String lanzaFile = StringUtils.EMPTY;

				try {
					lanzaFile = ruta.concat(script.getNombreScriptLanza());
					Files.delete(Paths.get(lanzaFile));
				} catch (IOException e) {
					LogWrapper.warn(log, "No existe el fichero %s", lanzaFile);
				}
			}
		}
	}

	/**
	 * @param proceso
	 */
	private void rechazarScripts(Proceso proceso) {
		try {
			String sufijoRechazo = ConfigurationSingleton.getInstance().getConfig("SufijoRechazoProcesado");
			List<Script> scripts = proceso.getScripts();
			List<Type> types = proceso.getTypes();
			String ruta = proceso.getRutaTrabajo();

			rechazarScriptsFromList(sufijoRechazo, scripts, ruta);
			rechazarScriptsFromTypes(sufijoRechazo, types, ruta);

		} catch (IOException e) {
			LogWrapper.warn(log, "¡¡¡SufijoRechazoProcesado!!!");
		}
	}

	private void rechazarScriptsFromTypes(String sufijoRechazo, List<Type> types, String ruta) {
		if (StringUtils.isNotBlank(ruta) && CollectionUtils.isNotEmpty(types)) {
			for (Type type : types) {
				String nombreFile = type.getNombreObjeto();
				if (CollectionUtils.isNotEmpty(type.getScriptType()) && type.getNumeroOrdenType().equals(BigDecimal.ZERO)) {
					for (ScriptType scriptType : type.getScriptType()) {
						nombreFile = scriptType.getNombreScript();
						renombrarArchivo(sufijoRechazo, ruta, nombreFile);
					}
				}
				else {
					renombrarArchivo(sufijoRechazo, ruta, nombreFile);
				}
			}
		}
	}

	private void renombrarArchivo(String sufijoRechazo, String ruta, String nombreFile) {
		File f = new File(ruta.concat(nombreFile));
		if (f.exists()) {
			String rechazado = nombreFile.concat("_" + sufijoRechazo);
			MDSQLAppHelper.renombrarArchivo(f, ruta.concat(rechazado));
		}
	}

	

	private void rechazarScriptsFromList(String sufijoRechazo, List<Script> scripts, String ruta) {
		if (StringUtils.isNotBlank(ruta) && CollectionUtils.isNotEmpty(scripts)) {
			for (Script script : scripts) {
				String nombreFile = script.getNombreScript();
				File f = new File(ruta.concat(nombreFile));
				if (f.exists()) {
					String name = nombreFile.substring(0, nombreFile.lastIndexOf('.'));
					String extension = getExtensionByStringHandling(nombreFile).get();
					String rechazado = name.concat("_" + sufijoRechazo);
					String fileNameRechazado = rechazado + "." + extension;
					MDSQLAppHelper.renombrarArchivo(f, fileNameRechazado);
				}
				
				nombreFile = script.getNombreScriptLog();
				f = new File(ruta.concat(nombreFile));
				if (f.exists()) {
					String name = nombreFile.substring(0, nombreFile.lastIndexOf('.'));
					String extension = getExtensionByStringHandling(nombreFile).get();
					String rechazado = name.concat("_" + sufijoRechazo);
					String fileNameRechazado = rechazado + "." + extension;
					MDSQLAppHelper.renombrarArchivo(f, fileNameRechazado);
				}
			}
		}
	}

	/**
	 * @param filename
	 * @return
	 */
	private Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
