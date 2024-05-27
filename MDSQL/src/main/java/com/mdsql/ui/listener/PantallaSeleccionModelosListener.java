package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.SeleccionModelosTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

/**
 * @author federico
 *
 */
public class PantallaSeleccionModelosListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaSeleccionModelos pantallaSeleccionModelos;

	public PantallaSeleccionModelosListener(PantallaSeleccionModelos pantallaSeleccionModelos) {
		super();
		this.pantallaSeleccionModelos = pantallaSeleccionModelos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		
		if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR.equals(jButton.getActionCommand())) {
			evntBtnSeleccionar();
		}

		if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_NOTAS.equals(jButton.getActionCommand())) {
			pantallaSeleccionModelos.dispose();
		}

		if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_VARIABLES.equals(jButton.getActionCommand())) {
			pantallaSeleccionModelos.dispose();
		}

		if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES.equals(jButton.getActionCommand())) {
			pantallaSeleccionModelos.dispose();
		}
	}

	/*
	 * 
	 */
	private void eventBtnBuscar() {
		try {
			String codModelo = pantallaSeleccionModelos.getTxtCodModelo().getText();
			String nombreModelo = pantallaSeleccionModelos.getTxtNombreModelo().getText();
			String codSubmodelo = pantallaSeleccionModelos.getTxtCodSubmodelo().getText();

			List<Modelo> modelos = buscar(codModelo, nombreModelo, codSubmodelo);
			populateModel(modelos);
			
			pantallaSeleccionModelos.forceRepaint();
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaSeleccionModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
	
	/**
	 * 
	 */
	private void evntBtnSeleccionar() {
		pantallaSeleccionModelos.dispose();
	}

	/**
	 * @param codModelo
	 * @param nombreModelo
	 * @param codSubmodelo
	 * @return
	 * @throws ServiceException 
	 */
	private List<Modelo> buscar(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
		ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
		
		OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);
		
		// Hay avisos
		if (outputConsultaModelos.getResult() == 2) {
			ServiceException serviceException = outputConsultaModelos.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaSeleccionModelos.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		return outputConsultaModelos.getModelos(); 
	}

	/**
	 * @param modelos
	 */
	private void populateModel(List<Modelo> modelos) {
		// Obtiene el modelo y lo actualiza
		SeleccionModelosTableModel tableModel = (SeleccionModelosTableModel) pantallaSeleccionModelos
				.getTblModelos().getModel();
		tableModel.setData(modelos);
		
		pantallaSeleccionModelos.getTblModelos().repaint();
	}

	@Override
	public void onLoad() {
		try {
			String codModelo = (String) pantallaSeleccionModelos.getParams().get("codigoProyecto");
			if (StringUtils.isNotBlank(codModelo)) {
				String nombreModelo = pantallaSeleccionModelos.getTxtNombreModelo().getText();
				String codSubmodelo = pantallaSeleccionModelos.getTxtCodSubmodelo().getText();
				
				List<Modelo> modelos = buscar(codModelo, nombreModelo, codSubmodelo);
				populateModel(modelos);
			}
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaSeleccionModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
}