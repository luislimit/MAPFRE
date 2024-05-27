package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.*;
import com.mdsql.ui.PantallaPermisosGeneralesporModeloporTipoObjeto;
import com.mdsql.ui.model.*;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaPermisosGeneralesporModeloporTipoObjetoListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto;

	public PantallaPermisosGeneralesporModeloporTipoObjetoListener(PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto) {
		super();
		this.pantallaPermisosGeneralesporModeloporTipoObjeto = pantallaPermisosGeneralesporModeloporTipoObjeto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}

		if (MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_INFORME.equals(jButton.getActionCommand())) {
			eventBtnInforme();
		}

		if (MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaPermisosGeneralesporModeloporTipoObjeto.dispose();
		}
	}
	
	private void eventBtnGuardar() {
		String valuePS = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermisoSinonimo().getSelectedItem();

		if ("Permiso".equals(valuePS)) {
			guardarPermiso(pantallaPermisosGeneralesporModeloporTipoObjeto);
		}

		if (("Sinónimo").equals(valuePS)) {
			guardarSinonimo(pantallaPermisosGeneralesporModeloporTipoObjeto);
		}
	}

	private void guardarSinonimo(PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto) {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
			Modelo modelo = pantallaPermisosGeneralesporModeloporTipoObjeto.getModelo();

			// Guardar
			String codProyecto = pantallaPermisosGeneralesporModeloporTipoObjeto.getModelo().getCodigoProyecto();
			Grant grant = (Grant) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso().getSelectedItem();
			Propietario propietario = (Propietario) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().getSelectedItem();

			String codUsrGrant = grant.getCodGrant();
			String codOwnerSyn = propietario.getCodPropietario();

			String desEntorno = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbEntorno().getSelectedItem();
			String tipoObjeto = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbTipoObjeto().getSelectedItem();
			String funcionNombre = pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().getText();
			String mcaIncluirPDC = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbIncluirPDC().getSelectedItem();
			String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaPermisosGeneralesporModeloporTipoObjeto.getChkHabilitado().isSelected());
			String codPeticion = pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtPeticion().getText();
			String codUsr = session.getCodUsr();

			permisosService.guardarSinonimo(codProyecto, codUsrGrant, codOwnerSyn, desEntorno, tipoObjeto, funcionNombre, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);

			// Limpiar formulario
			clearForm();

			// Actualizar
			actualizarSinonimos(modelo);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void guardarPermiso(PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto) {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
			Modelo modelo = pantallaPermisosGeneralesporModeloporTipoObjeto.getModelo();

			// Guardar
			String codProyecto = pantallaPermisosGeneralesporModeloporTipoObjeto.getModelo().getCodigoProyecto();
			Grant grant = (Grant) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso().getSelectedItem();

			String codUsrGrant = grant.getCodGrant();
			String valGrant = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso().getSelectedItem();

			String desEntorno = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbEntorno().getSelectedItem();
			String tipoObjeto = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbTipoObjeto().getSelectedItem();
			String mcaGrantOption = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion().getSelectedItem();
			String mcaIncluirPDC = (String) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbIncluirPDC().getSelectedItem();
			String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaPermisosGeneralesporModeloporTipoObjeto.getChkHabilitado().isSelected());
			String codPeticion = pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtPeticion().getText();
			String codUsr = session.getCodUsr();

			permisosService.guardarPermiso(codProyecto, codUsrGrant, valGrant, desEntorno, tipoObjeto, mcaGrantOption, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);

			// Limpiar formulario
			clearForm();

			// Actualizar
			actualizarPermisos(modelo);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void clearForm() {
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermisoSinonimo(), null);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion(), literales.getLiteral("no"));
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbIncluirPDC(), literales.getLiteral("si"));

		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtPeticion().setText(StringUtils.EMPTY);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbTipoObjeto(), null);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso(), null);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbEntorno(), null);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo(), null);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().setText(StringUtils.EMPTY);
		MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso(), null);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getChkHabilitado().setSelected(Boolean.FALSE);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtUsuarioAlta().setText(StringUtils.EMPTY);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFechaAlta().setText(StringUtils.EMPTY);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtUsuarioModificacion().setText(StringUtils.EMPTY);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFechaModificacion().setText(StringUtils.EMPTY);

		pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso().setEnabled(Boolean.TRUE);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.TRUE);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().setEnabled(Boolean.TRUE);
		pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.TRUE);
	}

	private void eventBtnInforme() {
		try {
			PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
			Modelo modelo = pantallaPermisosGeneralesporModeloporTipoObjeto.getModelo();

			List<Permiso> permisosGenerales = permisosService.consultaPermisosGenerales(modelo);
			List<Sinonimo> sinonimosGenerales = permisosService.consultaSinonimosGenerales(modelo);

			if (CollectionUtils.isNotEmpty(permisosGenerales)) {
				fillInformePermisos(modelo, permisosGenerales);
			}
			else {
				JOptionPane.showMessageDialog(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), "No hay permisos para generar informe");
			}

			if (CollectionUtils.isNotEmpty(sinonimosGenerales)) {
				fillInformeSinonimos(modelo, sinonimosGenerales);
			}
			else {
				JOptionPane.showMessageDialog(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), "No hay sinónimos para generar informe");
			}
		} catch (ServiceException | IOException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	@Override
	public void onLoad() {
		try {
			TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);
			PropietarioService propietarioService = (PropietarioService) getService(MDSQLConstants.PROPIETARIO_SERVICE);

			Modelo modelo = (Modelo) pantallaPermisosGeneralesporModeloporTipoObjeto.getParams().get("modelo");
			pantallaPermisosGeneralesporModeloporTipoObjeto.setModelo(modelo);

			actualizarPermisos(modelo);
			actualizarSinonimos(modelo);

			// Rellenar combos
			OutputConsultaTiposObjeto outputConsultaTiposObjeto = tipoObjetoService.consultarTiposObjeto();
			
			// Hay avisos
			if (outputConsultaTiposObjeto.getResult() == 2) {
				ServiceException serviceException = outputConsultaTiposObjeto.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}
			
			List<Propietario> propietarios = propietarioService.consultarPropietariosSinonimo(modelo);
			List<Grant> receptores = propietarioService.consultarReceptoresModelo(modelo);

			if (CollectionUtils.isNotEmpty(outputConsultaTiposObjeto.getTiposObjeto())) {
				TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(outputConsultaTiposObjeto.getTiposObjeto());
				pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
			}

			if (CollectionUtils.isNotEmpty(propietarios)) {
				PropietarioComboBoxModel propietarioComboBoxModel = new PropietarioComboBoxModel(propietarios);
				pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().setModel(propietarioComboBoxModel);
			}

			if (CollectionUtils.isNotEmpty(receptores)) {
				GrantComboBoxModel grantComboBoxModel = new GrantComboBoxModel(receptores);
				pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso().setModel(grantComboBoxModel);
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaPermisosGeneralesporModeloporTipoObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void actualizarSinonimos(Modelo modelo) throws ServiceException {
		PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);

		List<Sinonimo> sinonimosGenerales = permisosService.consultaSinonimosGenerales(modelo);
		if (CollectionUtils.isNotEmpty(sinonimosGenerales)) {
			fillSinonimos(sinonimosGenerales);
		}
	}

	private void actualizarPermisos(Modelo modelo) throws ServiceException {
		PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);

		List<Permiso> permisosGenerales = permisosService.consultaPermisosGenerales(modelo);
		if (CollectionUtils.isNotEmpty(permisosGenerales)) {
			fillPermisos(permisosGenerales);
		}
	}

	private void fillPermisos(List<Permiso> permisosGenerales) throws ServiceException {
		// Obtiene el modelo y lo actualiza
		PermisosTableModel tableModel = (PermisosTableModel) pantallaPermisosGeneralesporModeloporTipoObjeto
				.getTblPermisos().getModel();
		tableModel.clearData();

		tableModel.setData(permisosGenerales);
	}

	private void fillSinonimos(List<Sinonimo> sinonimosGenerales) throws ServiceException {
		// Obtiene el modelo y lo actualiza
		SinonimosTableModel tableModel = (SinonimosTableModel) pantallaPermisosGeneralesporModeloporTipoObjeto
				.getTblSinonimos().getModel();
		tableModel.clearData();

		tableModel.setData(sinonimosGenerales);
	}

	private void fillInformeSinonimos(Modelo modelo, List<Sinonimo> sinonimosGenerales) throws IOException {
		ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

		ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
		String path = configuration.getConfig("RutaInformes");
		String sufijo = configuration.getConfig("SufijoExcelSinonimosGenerales");

		excelGeneratorService.generarExcelSinonimos(sinonimosGenerales, path, sufijo, modelo.getCodigoProyecto(), new Date());
	}

	private void fillInformePermisos(Modelo modelo, List<Permiso> permisosGenerales) throws IOException {
		ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

		ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
		String path = configuration.getConfig("RutaInformes");
		String sufijo = configuration.getConfig("SufijoExcelPermisosGenerales");

		excelGeneratorService.generarExcelPermisos(permisosGenerales, path, sufijo, modelo.getCodigoProyecto(), new Date());
	}
}
