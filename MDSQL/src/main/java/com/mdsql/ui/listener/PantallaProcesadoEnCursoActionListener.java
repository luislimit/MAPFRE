package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaProcesadoEnCurso;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observer;

/**
 * @author federico
 *
 */
public class PantallaProcesadoEnCursoActionListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaProcesadoEnCurso pantallaProcesadoEnCurso;

	/**
	 * @param framePrincipal
	 */
	public PantallaProcesadoEnCursoActionListener(PantallaProcesadoEnCurso pantallaProcesadoEnCurso) {
		this.pantallaProcesadoEnCurso = pantallaProcesadoEnCurso;
	}

	public void addObservador(Observer o) {
		this.addObserver(o);
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaProcesadoEnCurso.dispose();
		}

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO.equals(jButton.getActionCommand())) {
			eventBtnVerProcesado();
		}
	}

	private void eventBtnVerProcesado() {
		Map<String, Object> params = new HashMap<>();

		Proceso seleccionado = pantallaProcesadoEnCurso.getProcesoSeleccionado();

		params.put("idProceso", seleccionado.getIdProceso());
		params.put("entregar", Boolean.FALSE);

		PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
				.createDialog(pantallaProcesadoEnCurso.getFrameParent(), MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
		MDSQLUIHelper.show(pantallaResumenProcesado);
	}

	@Override
	public void onLoad() {
		try {
			Proceso proceso = (Proceso) pantallaProcesadoEnCurso.getParams().get("proceso");

			fillUltimasPeticiones(proceso.getModelo());

			fillAvisos(proceso.getModelo());
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaProcesadoEnCurso.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillUltimasPeticiones(Modelo seleccionado) throws ServiceException {
		// Limpiar la tabla de peticiones
		((ProcesarScriptUltimasPeticionesTableModel) pantallaProcesadoEnCurso.getTblUltimasPeticiones().getModel())
				.clearData();

		// Hacer la consulta
		InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();

		inputSeleccionarProcesados.setPCodigoproyecto(seleccionado.getCodigoProyecto());
		inputSeleccionarProcesados.setPUltimas(new BigDecimal(1));

		ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
		List<Proceso> peticiones = procesoService.seleccionarProcesados(inputSeleccionarProcesados);

		if (CollectionUtils.isNotEmpty(peticiones)) {
			populateModelUltimasPeticiones(peticiones);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillAvisos(Modelo seleccionado) throws ServiceException {
		// Limpiar la tabla de avisos
		((ProcesarScriptNotaTableModel) pantallaProcesadoEnCurso.getTblNotas().getModel()).clearData();

		// Hacer la consulta
		AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
		List<Aviso> avisos = avisoService.consultaAvisosModelo(seleccionado.getCodigoProyecto());

		if (CollectionUtils.isNotEmpty(avisos)) {
			populateModelAvisos(avisos);
		}
	}

	/**
	 * @param peticiones
	 */
	private void populateModelUltimasPeticiones(List<Proceso> peticiones) {
		// Obtiene el modelo y lo actualiza
		ProcesarScriptUltimasPeticionesTableModel tableModel = (ProcesarScriptUltimasPeticionesTableModel) pantallaProcesadoEnCurso
				.getTblUltimasPeticiones().getModel();
		tableModel.setData(peticiones);
	}

	/**
	 * @param avisos
	 */
	private void populateModelAvisos(List<Aviso> avisos) {
		// Obtiene el modelo y lo actualiza
		ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantallaProcesadoEnCurso.getTblNotas()
				.getModel();
		tableModel.setData(avisos);
	}
}
