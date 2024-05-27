package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputAltaHistorico;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.OutputConsultaTiposObjeto;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.PantallaHistoricoAlta;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.TipoObjetoComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PantallaHistoricoAltaListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaHistoricoAlta pantallaHistoricoAlta;

	public PantallaHistoricoAltaListener(PantallaHistoricoAlta pantallaHistoricoAlta) {
		super();
		this.pantallaHistoricoAlta = pantallaHistoricoAlta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
			eventBtnBuscarModelo();
		}

		if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			alta();
		}

		if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaHistoricoAlta.getReturnParams().put("response", "KO");
			pantallaHistoricoAlta.dispose();
		}
	}

	private void eventBtnBuscarModelo() {
		try {
			Modelo seleccionado = null;
			Map<String, Object> params = new HashMap<>();
	
			String codigoProyecto = pantallaHistoricoAlta.getTxtModelo().getText();
			
			List<Modelo> modelos = buscarModelos(codigoProyecto, null, null);
			if (modelos.size() == 1) {
				seleccionado = modelos.get(0);
				pantallaHistoricoAlta.setModeloSeleccionado(seleccionado);
				pantallaHistoricoAlta.getTxtModelo().setText(seleccionado.getCodigoProyecto());
			}
			else {
				if (StringUtils.isNotBlank(codigoProyecto)) {
					params.put("codigoProyecto", codigoProyecto);
				}
				
				PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantallaHistoricoAlta.getFrameParent(),
						MDSQLConstants.CMD_SEARCH_MODEL, params);
				MDSQLUIHelper.show(pantallaSeleccionModelos);
				seleccionado = pantallaSeleccionModelos.getSeleccionado();
				pantallaHistoricoAlta.setModeloSeleccionado(seleccionado);
				pantallaHistoricoAlta.getTxtModelo().setText(seleccionado.getCodigoProyecto());
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
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
				MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			if (CollectionUtils.isNotEmpty(outputConsultaTiposObjeto.getTiposObjeto())) {
				TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(outputConsultaTiposObjeto.getTiposObjeto());
				pantallaHistoricoAlta.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
			}
		} catch (ServiceException e) {
			pantallaHistoricoAlta.getReturnParams().put("response", "KO");
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void alta() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();

			Modelo modeloSeleccionado = pantallaHistoricoAlta.getModeloSeleccionado();

			if (!Objects.isNull(modeloSeleccionado)) {
				String codigoProyecto = modeloSeleccionado.getCodigoProyecto();
				String tipoObjeto = (String) pantallaHistoricoAlta.getCmbTipoObjeto().getSelectedItem();
				String nombreObjeto = pantallaHistoricoAlta.getTxtNombreObjeto().getText();
				String peticion = pantallaHistoricoAlta.getTxtPeticion().getText();
				String historificada = AppHelper.normalizeValueToCheck(pantallaHistoricoAlta.getChkHistorificada().isSelected());

				OutputAltaHistorico outputAltaHistorico = historicoService.altaHistorico(codigoProyecto, nombreObjeto, tipoObjeto, historificada, peticion, codUsr);
				
				// Hay avisos
				if (outputAltaHistorico.getResult() == 2) {
					ServiceException serviceException = outputAltaHistorico.getServiceException();
					Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
					MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_WARN, params);
				}
				
				pantallaHistoricoAlta.getReturnParams().put("response", "OK");
			}
			else {
				pantallaHistoricoAlta.getReturnParams().put("response", "KO");
			}
			
			pantallaHistoricoAlta.dispose();
		} catch (ServiceException e) {
			pantallaHistoricoAlta.getReturnParams().put("response", "KO");
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
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
			MDSQLUIHelper.showPopup(pantallaHistoricoAlta.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		return outputConsultaModelos.getModelos(); 
	}
}
