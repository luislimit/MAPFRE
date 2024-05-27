package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaConsultaPeticiones;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ConsultaPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

public class PantallaConsultaPeticionesListener extends ListenerSupport implements ActionListener {

	private PantallaConsultaPeticiones pantallaConsultaPeticiones;

	public PantallaConsultaPeticionesListener(PantallaConsultaPeticiones pantallaConsultaPeticiones) {
		super();
		this.pantallaConsultaPeticiones = pantallaConsultaPeticiones;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_CONSULTA_PETICIONES_BUSCAR.equals(jButton.getActionCommand())) {
			buscar();
		}
		if (MDSQLConstants.PANTALLA_CONSULTA_PETICIONES_CARGAR_PROCESADO.equals(jButton.getActionCommand())) {
			cargarProcesado();
		}
		if (MDSQLConstants.PANTALLA_CONSULTA_PETICIONES_CANCELAR.equals(jButton.getActionCommand())) {
			cancelar();
		}
	}

	private void cargarProcesado() {

		Proceso proceso = pantallaConsultaPeticiones.getSeleccionado();
		Map<String, Object> params = new HashMap<>();

		params.put("proceso", proceso);
		params.put("entregar", Boolean.FALSE);

		PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper.createDialog(
				pantallaConsultaPeticiones.getFrameParent(), MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
		MDSQLUIHelper.show(pantallaResumenProcesado);
	}

	private void buscar() {
		try {
			ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
			InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();

			String codigoProyecto = pantallaConsultaPeticiones.getTxtModelo().getText();
			inputSeleccionarProcesados.setPCodigoproyecto(codigoProyecto);

			String codigoSubproyecto = pantallaConsultaPeticiones.getTxtSubmodelo().getText();
			inputSeleccionarProcesados.setPCodigoSubProyecto(codigoSubproyecto);

			String codigoPeticion = pantallaConsultaPeticiones.getTxtPeticion().getText();
			inputSeleccionarProcesados.setPCodigoPeticion(codigoPeticion);

			String codUsrPeticion = pantallaConsultaPeticiones.getTxtSolicitada().getText();
			inputSeleccionarProcesados.setPCodigoUsuarioPeticion(codUsrPeticion);

			String estado = pantallaConsultaPeticiones.getTxtEstado().getText();
			inputSeleccionarProcesados.setPDescripcionEstadoProceso(estado);

			String usuario = pantallaConsultaPeticiones.getTxtUsuario().getText();
			inputSeleccionarProcesados.setPCodigoUsuario(usuario);

			String desde = pantallaConsultaPeticiones.getTxtDesde().getText();
			String hasta = pantallaConsultaPeticiones.getTxtHasta().getText();
			Date fechaDesde = (StringUtils.isNotBlank(desde)) ? dateBuscarFormatter.stringToDate(desde) : null;
			Date fechaHasta = (StringUtils.isNotBlank(hasta)) ? dateBuscarFormatter.stringToDate(hasta) : null;
			inputSeleccionarProcesados.setPFechaInicio((java.sql.Date) fechaDesde);
			inputSeleccionarProcesados.setPFechaFin((java.sql.Date) fechaHasta);

			List<Proceso> procesos = procesoService.seleccionarProcesados(inputSeleccionarProcesados);
			populateModel(procesos);
			
			
		} catch (ServiceException | ParseException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaConsultaPeticiones.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	private void cancelar() {
		pantallaConsultaPeticiones.dispose();
	}

	/**
	 * @param modelos
	 */
	private void populateModel(List<Proceso> procesos) {
		// Obtiene el modelo y lo actualiza
		ConsultaPeticionesTableModel tableModel = (ConsultaPeticionesTableModel) pantallaConsultaPeticiones
				.getTblPeticiones().getModel();
		tableModel.setData(procesos);

		pantallaConsultaPeticiones.forceRepaint();
	}
}
