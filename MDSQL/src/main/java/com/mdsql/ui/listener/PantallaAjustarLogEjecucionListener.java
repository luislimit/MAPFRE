package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.InputEliminaLog;
import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Scriptable;
import com.mdsql.bussiness.service.LogService;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.model.AjustarLogEjecucionTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaAjustarLogEjecucionListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaAjustarLogEjecucion pantallaAjustarLogEjecucion;

	public PantallaAjustarLogEjecucionListener(PantallaAjustarLogEjecucion pantallaAjustarLogEjecucion) {
		super();
		this.pantallaAjustarLogEjecucion = pantallaAjustarLogEjecucion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR.equals(jButton.getActionCommand())) {
			eliminarEvt();
		}
		if (MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaAjustarLogEjecucion.dispose();
		}

	}

	private void eliminarEvt() {
		try {
			eliminarRegistro();
			
			loadLogEjecucion();
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaAjustarLogEjecucion.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	@Override
	public void onLoad() {
		try {
			loadLogEjecucion();
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaAjustarLogEjecucion.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
	
	/**
	 * 
	 */
	private void eliminarRegistro() throws ServiceException {
		LogService logService = (LogService) getService(MDSQLConstants.LOG_SERVICE);

		LogEjecucion seleccionado = pantallaAjustarLogEjecucion.getSeleccionado();
		
		InputEliminaLog inputEliminaLog = new InputEliminaLog();
		inputEliminaLog.setIdProceso(seleccionado.getIdProceso());
		inputEliminaLog.setNumeroOrden(seleccionado.getNumeroOrden());
		inputEliminaLog.setNumeroIteracion(seleccionado.getNumeroIteracion());
		inputEliminaLog.setNumeroEjecucion(seleccionado.getNumeroEjecucion());
		inputEliminaLog.setNumeroParche(seleccionado.getNumeroParche());
		inputEliminaLog.setNumeroSentencia(seleccionado.getNumeroSentencia());
		inputEliminaLog.setMcaEliminada(seleccionado.getMcaEliminada());
		String comentario = pantallaAjustarLogEjecucion.getTxtComentario().getText();
		inputEliminaLog.setTxtComentario(comentario);
		
		ServiceException serviceException = logService.eliminaLog(inputEliminaLog);
		
		if (!Objects.isNull(serviceException)) {
			if (serviceException.getType().equals(2)) {
				Map<String, Object> params = MDSQLUIHelper.buildError(serviceException);
				MDSQLUIHelper.showPopup(pantallaAjustarLogEjecucion.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}
			else {
				throw serviceException;
			}
		}
	}

	/**
	 * 
	 */
	private void loadLogEjecucion() throws ServiceException {
		LogService logService = (LogService) getService(MDSQLConstants.LOG_SERVICE);

		Scriptable script = (Scriptable) pantallaAjustarLogEjecucion.getParams().get("script");
		Proceso proceso = (Proceso) pantallaAjustarLogEjecucion.getParams().get("proceso");

		BigDecimal idProceso = proceso.getIdProceso();
		BigDecimal numeroOrden = script.getNumeroOrden();

		List<LogEjecucion> logEjecucion = logService.logEjecucion(idProceso, numeroOrden);
		
		AjustarLogEjecucionTableModel tableModel = (AjustarLogEjecucionTableModel) pantallaAjustarLogEjecucion.getTblAjustarLog().getModel();
		tableModel.setData(logEjecucion);
		
		pantallaAjustarLogEjecucion.forceRepaint();
	}

}
