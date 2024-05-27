package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.PantallaMantenimientoVariables;
import com.mdsql.ui.model.*;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.LiteralesSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaMantenimientoVariablesListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoVariables pantallaMantenimientoVariables;

	public PantallaMantenimientoVariablesListener(PantallaMantenimientoVariables pantallaMantenimientoVariables) {
		super();
		this.pantallaMantenimientoVariables = pantallaMantenimientoVariables;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoVariables.dispose();
		}
	}

	private void eventBtnGuardar() {
		try {
			ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();
			Modelo modelo = pantallaMantenimientoVariables.getModelo();

			String codigoProyecto = modelo.getCodigoProyecto();
			String codigoVariable = pantallaMantenimientoVariables.getTxtCodigoVariable().getText();
			String entorno = (String) pantallaMantenimientoVariables.getCmbEntorno().getSelectedItem();
			String bbdd = pantallaMantenimientoVariables.getTxtBBDD().getText();
			String tipoVariable = (String) pantallaMantenimientoVariables.getCmbTipoVariable().getSelectedItem();
			String valorVariable = pantallaMantenimientoVariables.getTxtValorVariable().getText();
			String valorSustituir = pantallaMantenimientoVariables.getTxtValorVariableSustituir().getText();
			String codigoPeticion = pantallaMantenimientoVariables.getTxtPeticion().getText();
			String mcaInterno = (String) pantallaMantenimientoVariables.getCmbUsoInterno().getSelectedItem();
			String mcaHabilitado = AppHelper
					.normalizeValueToCheck(pantallaMantenimientoVariables.getChkHabilitada().isSelected());
			String comentario = pantallaMantenimientoVariables.getTxtComentario().getText();

			OutputActualizarVariablesModelo outputActualizarVariablesModelo = modeloService.actualizarVariableModelo(codigoProyecto, codigoVariable, entorno, bbdd, tipoVariable,
					valorVariable, valorSustituir, codigoPeticion, mcaInterno, mcaHabilitado, comentario, codUsr);
			
			// Hay avisos
			if (outputActualizarVariablesModelo.getResult() == 2) {
				ServiceException serviceException = outputActualizarVariablesModelo.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaMantenimientoVariables.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			clearForm();
			actualizarVariables(modelo);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoVariables.getFrameParent(), MDSQLConstants.CMD_ERROR,
					errParams);
		}
	}

	private void clearForm() {
		try {
			LiteralesSingleton literales = LiteralesSingleton.getInstance();

			pantallaMantenimientoVariables.getTxtCodigoVariable().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getTxtValorVariable().setText(StringUtils.EMPTY);
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbUsoInterno(),
					literales.getLiteral("no"));
			pantallaMantenimientoVariables.getTxtPeticion().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getTxtValorVariableSustituir().setText(StringUtils.EMPTY);
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbTipoVariable(), null);
			pantallaMantenimientoVariables.getTxtBBDD().setText(StringUtils.EMPTY);
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbEntorno(), null);
			pantallaMantenimientoVariables.getTxtComentario().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getChkHabilitada().setSelected(Boolean.FALSE);
			pantallaMantenimientoVariables.getChkUsoPermisos().setSelected(Boolean.FALSE);

			pantallaMantenimientoVariables.getTxtUsuarioAlta().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getTxtFechaAlta().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getTxtUsuarioModificacion().setText(StringUtils.EMPTY);
			pantallaMantenimientoVariables.getTxtFechaModificacion().setText(StringUtils.EMPTY);
		} catch (IOException e) {
		}
	}

	@Override
	public void onLoad() {
		try {
			TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);

			Modelo modelo = (Modelo) pantallaMantenimientoVariables.getParams().get("modelo");
			pantallaMantenimientoVariables.setModelo(modelo);

			actualizarVariables(modelo);

			// Rellenar combos
			List<String> tipos = tipoObjetoService.consultarTiposVariable();

			if (CollectionUtils.isNotEmpty(tipos)) {
				TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(tipos);
				pantallaMantenimientoVariables.getCmbTipoVariable().setModel(tipoObjetoComboBoxModel);
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoVariables.getFrameParent(), MDSQLConstants.CMD_ERROR,
					errParams);
		}
	}

	private void actualizarVariables(Modelo modelo) throws ServiceException {
		ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

		OutputVariablesModelo outputVariablesModelo = modeloService.consultaVariables(modelo);
		
		// Hay avisos
		if (outputVariablesModelo.getResult() == 2) {
			ServiceException serviceException = outputVariablesModelo.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaMantenimientoVariables.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		if (CollectionUtils.isNotEmpty(outputVariablesModelo.getVariables())) {
			fillVariables(outputVariablesModelo.getVariables());
		}
	}

	private void fillVariables(List<Variable> variables) {
		// Obtiene el modelo y lo actualiza
		VariableTableModel tableModel = (VariableTableModel) pantallaMantenimientoVariables.getTblVariables()
				.getModel();
		tableModel.clearData();

		tableModel.setData(variables);
	}
}
