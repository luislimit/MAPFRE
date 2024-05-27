package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.ErrorScript;
import com.mdsql.bussiness.entities.OutputErroresScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptParche;
import com.mdsql.bussiness.entities.Scriptable;
import com.mdsql.bussiness.service.ErroresService;
import com.mdsql.ui.PantallaVerErroresScript;
import com.mdsql.ui.model.VerErroresScriptTableModel;
import com.mdsql.ui.model.VerParchesScriptTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observer;

public class PantallaVerErroresScriptListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaVerErroresScript pantallaVerErroresScript;

	public PantallaVerErroresScriptListener(PantallaVerErroresScript pantallaVerErroresScript) {
		super();
		this.pantallaVerErroresScript = pantallaVerErroresScript;
	}

	public void addObservador(Observer o) {
		this.addObserver(o);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaVerErroresScript.dispose();
		}
	}

	@Override
	public void onLoad() {
		try {
			ErroresService erroresService = (ErroresService) getService(MDSQLConstants.ERRORES_SERVICE);

			Proceso proceso = (Proceso) pantallaVerErroresScript.getParams().get("proceso");
			String tipo = (String) pantallaVerErroresScript.getParams().get("tipo");
			
			BigDecimal idProceso = proceso.getIdProceso();
			BigDecimal numeroOrden = null;
			if ("type".equals(tipo)) {
				numeroOrden = (BigDecimal) pantallaVerErroresScript.getParams().get("numeroOrden");
			}
			else {
				Scriptable script = (Scriptable) pantallaVerErroresScript.getParams().get("script");
				numeroOrden = script.getNumeroOrden();
			} 

			/**
			 * Se llamará al método específico según se estén ejecutando scripts o scripts
			 * tipo
			 */
			List<ErrorScript> errores = null;
			List<ScriptParche> parches = null;
			
			if ("type".equals(tipo)) {
				errores = erroresService.consultaErroresType(idProceso, numeroOrden);
			} else {
				OutputErroresScript outputErroresScript = erroresService.consultaErroresScript(idProceso, numeroOrden);
				errores = outputErroresScript.getListaErroresScript();
				parches = outputErroresScript.getListaScriptParche();
			}

			populateErrores(errores);
			populateParches(parches);

		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaVerErroresScript.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	/**
	 * @param errores
	 */
	private void populateErrores(List<ErrorScript> errores) {
		// Obtiene el modelo y lo actualiza
		VerErroresScriptTableModel tableModel = (VerErroresScriptTableModel) pantallaVerErroresScript
				.getTblErroresScript().getModel();
		tableModel.setData(errores);
	}

	/**
	 * @param parches
	 */
	private void populateParches(List<ScriptParche> parches) {
		// Obtiene el modelo y lo actualiza
		VerParchesScriptTableModel tableModel = (VerParchesScriptTableModel) pantallaVerErroresScript
				.getTblParches().getModel();
		tableModel.setData(parches);
	}
}
