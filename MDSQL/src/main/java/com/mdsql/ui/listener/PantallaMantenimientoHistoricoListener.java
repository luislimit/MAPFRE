package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.*;
import com.mdsql.ui.*;
import com.mdsql.ui.model.HistoricoTableModel;
import com.mdsql.ui.model.TipoObjetoComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaMantenimientoHistoricoListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoHistorico pantallaMantenimientoHistorico;
	
	public PantallaMantenimientoHistoricoListener(PantallaMantenimientoHistorico pantallaMantenimientoHistorico) {
		super();
		this.pantallaMantenimientoHistorico = pantallaMantenimientoHistorico;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
			eventBtnBuscarModelo();
		}
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_ALTA.equals(jButton.getActionCommand())) {
			eventBtnAlta();
		}
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BAJA.equals(jButton.getActionCommand())) {
			eventBtnBaja();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_INFORME.equals(jButton.getActionCommand())) {
			eventBtnInforme();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoHistorico.dispose();
		}
	}

	private void eventBtnBuscarModelo() {
		try {
			Modelo seleccionado = null;
			Map<String, Object> params = new HashMap<>();
	
			String codigoProyecto = pantallaMantenimientoHistorico.getTxtModelo().getText();
			
			List<Modelo> modelos = buscarModelos(codigoProyecto, null, null);
			if (modelos.size() == 1) {
				seleccionado = modelos.get(0);
				pantallaMantenimientoHistorico.setModeloSeleccionado(seleccionado);
				pantallaMantenimientoHistorico.getTxtModelo().setText(seleccionado.getCodigoProyecto());
			}
			else {
				if (StringUtils.isNotBlank(codigoProyecto)) {
					params.put("codigoProyecto", codigoProyecto);
				}
				
				PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantallaMantenimientoHistorico.getFrameParent(),
						MDSQLConstants.CMD_SEARCH_MODEL, params);
				MDSQLUIHelper.show(pantallaSeleccionModelos);
				seleccionado = pantallaSeleccionModelos.getSeleccionado();
				pantallaMantenimientoHistorico.setModeloSeleccionado(seleccionado);
				pantallaMantenimientoHistorico.getTxtModelo().setText(seleccionado.getCodigoProyecto());
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
	
	private void eventBtnBuscar() {
		buscar();
	}

	private void eventBtnAlta() {
		Map<String, Object> params = new HashMap<>();

		PantallaHistoricoAlta pantallaHistoricoAlta = (PantallaHistoricoAlta) MDSQLUIHelper.createDialog(pantallaMantenimientoHistorico.getFrameParent(),
				MDSQLConstants.CMD_HISTORICO_ALTA, params);
		MDSQLUIHelper.show(pantallaHistoricoAlta);

		String response = (String) pantallaHistoricoAlta.getReturnParams().get("response");
		if (!Objects.isNull(response) && "OK".equals(response)) {
			buscar();
		}
	}
	
	private void eventBtnBaja() {
		Map<String, Object> params = new HashMap<>();

		params.put("historico", pantallaMantenimientoHistorico.getSeleccionado());
		params.put("modelo", pantallaMantenimientoHistorico.getModeloSeleccionado());

		PantallaHistoricoBaja pantallaHistoricoBaja = (PantallaHistoricoBaja) MDSQLUIHelper.createDialog(pantallaMantenimientoHistorico.getFrameParent(),
				MDSQLConstants.CMD_HISTORICO_BAJA, params);
		MDSQLUIHelper.show(pantallaHistoricoBaja);

		String response = (String) pantallaHistoricoBaja.getReturnParams().get("response");
		if (!Objects.isNull(response) && "OK".equals(response)) {
			buscar();
		}
	}
	
	private void eventBtnInforme() {
		informe();
	}

	@Override
	public void onLoad() {
		try {
			TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);

			// Rellenar combos
			OutputConsultaTiposObjeto outputConsultaTiposObjeto = tipoObjetoService.consultarTiposObjeto();
			
			// Hay avisos
			if (outputConsultaTiposObjeto.getResult() == 2) {
				ServiceException serviceException = outputConsultaTiposObjeto.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			if (CollectionUtils.isNotEmpty(outputConsultaTiposObjeto.getTiposObjeto())) {
				TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(outputConsultaTiposObjeto.getTiposObjeto());
				pantallaMantenimientoHistorico.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void buscar() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);

			String codigoProyecto = pantallaMantenimientoHistorico.getTxtModelo().getText();
			String tipoObjeto = (String) pantallaMantenimientoHistorico.getCmbTipoObjeto().getSelectedItem();

			OutputConsultaHistorico outputConsultaHistorico = historicoService.consultarHistorico(codigoProyecto, tipoObjeto);
			
			// Hay avisos
			if (outputConsultaHistorico.getResult() == 2) {
				ServiceException serviceException = outputConsultaHistorico.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			fillHistorico(outputConsultaHistorico.getHistorico());
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
	
	/**
	 * @param codModelo
	 * @param nombreModelo
	 * @param codSubmodelo
	 * @return
	 * @throws ServiceException 
	 */
	private List<Modelo> buscarModelos(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
		ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
		
		OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);
		
		// Hay avisos
		if (outputConsultaModelos.getResult() == 2) {
			ServiceException serviceException = outputConsultaModelos.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		return outputConsultaModelos.getModelos(); 
	}

	private void informe() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);

			String codigoProyecto = pantallaMantenimientoHistorico.getTxtModelo().getText();
			String tipoObjeto = (String) pantallaMantenimientoHistorico.getCmbTipoObjeto().getSelectedItem();

			OutputConsultaHistorico outputConsultaHistorico = historicoService.consultarHistorico(codigoProyecto, tipoObjeto);
			
			// Hay avisos
			if (outputConsultaHistorico.getResult() == 2) {
				ServiceException serviceException = outputConsultaHistorico.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			fillInforme(codigoProyecto, outputConsultaHistorico.getHistorico());
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void fillInforme(String codigoProyecto, List<Historico> lista) {
		try {
			ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String path = configuration.getConfig("RutaInformes");
			String sufijo = configuration.getConfig("SufijoExcelObjHistorico");

			if(lista.isEmpty()) {
				JOptionPane.showMessageDialog(pantallaMantenimientoHistorico.getFrameParent(), "No hay datos para generar informe");
			} else {
				excelGeneratorService.generarExcelHistorico(lista, path, sufijo, codigoProyecto, new Date());
			}
		} catch (IOException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	private void fillHistorico(List<Historico> list) throws ServiceException {
		// Obtiene el modelo y lo actualiza
		HistoricoTableModel tableModel = (HistoricoTableModel) pantallaMantenimientoHistorico
				.getTblMantenimientoHistorico().getModel();
		tableModel.clearData();

		tableModel.setData(list);
		pantallaMantenimientoHistorico.getBtnBaja().setEnabled(Boolean.FALSE);
	}
}
