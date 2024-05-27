package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.InformeCambios;
import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Operacion;
import com.mdsql.bussiness.entities.OutputConsultaHistoricoProceso;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.OutputInformeCambios;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.ConsultaService;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.bussiness.service.InformeService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.PantallaHistoricoCambios;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.EstadoComboBoxModel;
import com.mdsql.ui.model.HistoricoObjetoTableModel;
import com.mdsql.ui.model.OperacionComboBoxModel;
import com.mdsql.ui.model.TipoObjetoComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaHistoricoCambiosListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaHistoricoCambios pantallaHistoricoCambios;

	public PantallaHistoricoCambiosListener(PantallaHistoricoCambios pantallaHistoricoCambios) {
		super();
		this.pantallaHistoricoCambios = pantallaHistoricoCambios;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
			buscarModelo();
		}
		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR.equals(jButton.getActionCommand())) {
			buscar();
		}
		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS.equals(jButton.getActionCommand())) {
			informeCambios();
		}
		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT.equals(jButton.getActionCommand())) {
			verDetalleScript();
		}
		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO.equals(jButton.getActionCommand())) {
			resumenProcesado();
		}
		if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_CANCELAR.equals(jButton.getActionCommand())) {
			cancelar();
		}
	}

	private void cancelar() {
		pantallaHistoricoCambios.dispose();

	}

	private void resumenProcesado() {
		Map<String, Object> params = new HashMap<>();
		
		HistoricoProceso seleccionado = pantallaHistoricoCambios.getSeleccionado();
		
		Proceso proceso = MDSQLAppHelper.buildProceso(seleccionado.getIdProceso());
			
		params.put("proceso", proceso);
		params.put("entregar", Boolean.FALSE);

		PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
				.createDialog(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
		MDSQLUIHelper.show(pantallaResumenProcesado);
	}

	private void verDetalleScript() {
		Map<String, Object> params = new HashMap<>();

		HistoricoProceso seleccionado = pantallaHistoricoCambios.getSeleccionado();

		params.put("script", seleccionado.getNombreScript());
		params.put("proceso", seleccionado.getIdProceso());
		params.put("numeroOrden", seleccionado.getNumeroOrden());

		PantallaDetalleScript pantallaDetalleScript = (PantallaDetalleScript) MDSQLUIHelper
				.createDialog(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_DETALLE_SCRIPT, params);
		MDSQLUIHelper.show(pantallaDetalleScript);
	}

	private void informeCambios() {
		try {
			InformeService informeService = (InformeService) getService(MDSQLConstants.INFORME_SERVICE);
			ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);
			
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String path = configuration.getConfig("RutaInformes");
			
			String codigoProyecto = pantallaHistoricoCambios.getTxtModelo().getText().toUpperCase();
			String desde = pantallaHistoricoCambios.getTxtDesde().getText();
			String hasta = pantallaHistoricoCambios.getTxtHasta().getText();
			Date fechaDesde = (StringUtils.isNotBlank(desde)) ? dateInputFormatter.stringToDate(desde) : null;
			Date fechaHasta = (StringUtils.isNotBlank(hasta)) ? dateInputFormatter.stringToDate(hasta) : null;
			
			OutputInformeCambios outputInformeCambios = informeService.informeCambios(codigoProyecto, fechaDesde, fechaHasta);
			
			// Hay avisos
			if (outputInformeCambios.getResult() == 2) {
				ServiceException serviceException = outputInformeCambios.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}
			
			String sDesde = dateInformeFormatter.dateToString(fechaDesde);
			String sHasta = dateInformeFormatter.dateToString(fechaHasta);
			
			List<InformeCambios> listaCambios = outputInformeCambios.getListaCambios();
			
			if(listaCambios.isEmpty()) {
				JOptionPane.showMessageDialog(pantallaHistoricoCambios.getFrameParent(), "No hay datos para generar informe");
			} else {
				excelGeneratorService.generarExcelHistoricoCambios(listaCambios, path, codigoProyecto, sDesde, sHasta);
			}
		} catch (ServiceException | ParseException | IOException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	private void buscar() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
			
			InputConsutaHistoricoProceso inputConsutaHistoricoProceso = new InputConsutaHistoricoProceso();
			
			String codigoProyecto = pantallaHistoricoCambios.getTxtModelo().getText();
			inputConsutaHistoricoProceso.setCodigoProyecto(codigoProyecto);
			
			String nombreObjetoPadre = pantallaHistoricoCambios.getTxtObjetoPadre().getText();
			inputConsutaHistoricoProceso.setNombreObjetoPadre(nombreObjetoPadre);
			
			String tipoObjetoPadre = (String) pantallaHistoricoCambios.getCmbTipoObjetoPadre().getSelectedItem();
			inputConsutaHistoricoProceso.setTipoObjetoPadre(tipoObjetoPadre);
			
			Operacion operacionPadre = (Operacion) pantallaHistoricoCambios.getCmbOperacionPadre().getSelectedItem();
			String tipoAccionPadre = (!Objects.isNull(operacionPadre)) ? operacionPadre.getTipoAccion() : null; 
			inputConsutaHistoricoProceso.setTipoAccionPadre(tipoAccionPadre);
			
			String nombreObjeto = pantallaHistoricoCambios.getTxtObjeto().getText();
			inputConsutaHistoricoProceso.setNombreObjeto(nombreObjeto);
			
			String tipoObjeto = (String) pantallaHistoricoCambios.getCmbTipoObjeto().getSelectedItem();
			inputConsutaHistoricoProceso.setTipoObjeto(tipoObjeto);
			
			Operacion operacion = (Operacion) pantallaHistoricoCambios.getCmbOperacion().getSelectedItem();
			String tipoAccion = (!Objects.isNull(operacion)) ? operacion.getTipoAccion() : null; 
			inputConsutaHistoricoProceso.setTipoAccion(tipoAccion);
			
			String desde = pantallaHistoricoCambios.getTxtDesde().getText();
			String hasta = pantallaHistoricoCambios.getTxtHasta().getText();
			Date fechaDesde = (StringUtils.isNotBlank(desde)) ? dateInputFormatter.stringToDate(desde) : null;
			Date fechaHasta = (StringUtils.isNotBlank(hasta)) ? dateInputFormatter.stringToDate(hasta) : null;
			inputConsutaHistoricoProceso.setFechaDesde(fechaDesde);
			inputConsutaHistoricoProceso.setFechaHasta(fechaHasta);
			
			Estado estadoScript = (Estado) pantallaHistoricoCambios.getCmbEstadoScript().getSelectedItem();
			BigDecimal codigoEstadoScript = (!Objects.isNull(estadoScript)) ? estadoScript.getCodigoEstado() : null;
			inputConsutaHistoricoProceso.setCodigoEstadoScript(codigoEstadoScript);
			
			Estado estadoProcesado = (Estado) pantallaHistoricoCambios.getCmbEstadoProcesado().getSelectedItem();
			BigDecimal codigoEstadoProcesado = (!Objects.isNull(estadoProcesado)) ? estadoProcesado.getCodigoEstado() : null;
			inputConsutaHistoricoProceso.setCodigoEstadoProceso(codigoEstadoProcesado);
			
			
			OutputConsultaHistoricoProceso outputConsultaHistoricoProceso = historicoService.consultarHistoricoProceso(inputConsutaHistoricoProceso);
			
			// Hay avisos
			if (outputConsultaHistoricoProceso.getResult() == 2) {
				ServiceException serviceException = outputConsultaHistoricoProceso.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}
			
			populateModel(outputConsultaHistoricoProceso.getHistoricoProcesos());
			
			pantallaHistoricoCambios.getBtnResumen().setEnabled(Boolean.FALSE);
			pantallaHistoricoCambios.getBtnVerDetalle().setEnabled(Boolean.FALSE);
			
			
		} catch (ServiceException | ParseException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}

	}

	private void buscarModelo() {
		try {
			Map<String, Object> params = new HashMap<>();
			Modelo seleccionado = null;
			
			String codigoProyecto = pantallaHistoricoCambios.getTxtModelo().getText();
			List<Modelo> modelos = recuperarModelos(codigoProyecto, null, null);
			if (modelos.size() == 1) {
				seleccionado = modelos.get(0);
			}
			else {
				if (StringUtils.isNotBlank(codigoProyecto)) {
					params.put("codigoProyecto", codigoProyecto);
				}
	
				PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper
						.createDialog(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_SEARCH_MODEL, params);
				MDSQLUIHelper.show(pantallaSeleccionModelos);
				seleccionado = pantallaSeleccionModelos.getSeleccionado();
			}
			
			if (!Objects.isNull(seleccionado)) {
				pantallaHistoricoCambios.getTxtModelo().setText(seleccionado.getCodigoProyecto());
			}
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	/**
	 * @param modelos
	 */
	private void populateModel(List<HistoricoProceso> historicos) {
		// Obtiene el modelo y lo actualiza
		HistoricoObjetoTableModel tableModel = (HistoricoObjetoTableModel) pantallaHistoricoCambios
				.getTblHistoricoObjetos().getModel();
		tableModel.setData(historicos);
		
		pantallaHistoricoCambios.forceRepaint();
	}
	
	/**
	 * @param codModelo
	 * @param nombreModelo
	 * @param codSubmodelo
	 * @return
	 * @throws ServiceException 
	 */
	private List<Modelo> recuperarModelos(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
		ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
		
		OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);
		
		// Hay avisos
		if (outputConsultaModelos.getResult() == 2) {
			ServiceException serviceException = outputConsultaModelos.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		return outputConsultaModelos.getModelos(); 
	}

	@Override
	public void onLoad() {
		try {
			ConsultaService consultaService = (ConsultaService) getService(MDSQLConstants.CONSULTA_SERVICE);
			
			List<String> tiposObjeto = consultaService.consultaTiposObjeto();
			pantallaHistoricoCambios.getCmbTipoObjeto().setModel(new TipoObjetoComboBoxModel(tiposObjeto));
			pantallaHistoricoCambios.getCmbTipoObjetoPadre().setModel(new TipoObjetoComboBoxModel(tiposObjeto));
			
			List<Operacion> operaciones = consultaService.consultaOperaciones();
			pantallaHistoricoCambios.getCmbOperacion().setModel(new OperacionComboBoxModel(operaciones));
			pantallaHistoricoCambios.getCmbOperacionPadre().setModel(new OperacionComboBoxModel(operaciones));
			
			List<Estado> estadosScript = consultaService.consultaEstadosScript();
			pantallaHistoricoCambios.getCmbEstadoScript().setModel(new EstadoComboBoxModel(estadosScript));
			
			List<Estado> estadosProcesado = consultaService.consultaEstadosProcesado();
			pantallaHistoricoCambios.getCmbEstadoProcesado().setModel(new EstadoComboBoxModel(estadosProcesado));
			
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoCambios.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

}
