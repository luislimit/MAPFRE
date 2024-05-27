package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaMantenimientoNotasModelos;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.NivelesImportanciaComboBoxModel;
import com.mdsql.ui.model.NotasModeloTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;

public class PantallaMantenimientoNotasModelosListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoNotasModelos pantallaMantenimientoNotasModelos;
	
	public PantallaMantenimientoNotasModelosListener(PantallaMantenimientoNotasModelos pantallaMantenimientoNotasModelos) {
		super();
		this.pantallaMantenimientoNotasModelos = pantallaMantenimientoNotasModelos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
			eventBtnBuscarModelo();
			cargarModelo(pantallaMantenimientoNotasModelos.getModeloSeleccionado());
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoNotasModelos.dispose();
		}
	}

	private void eventBtnBuscarModelo() {
		Modelo seleccionado = null;
		Map<String, Object> params = new HashMap<>();
		params.put("opcion", "mntoNotasModelos");

		String codigoProyecto = pantallaMantenimientoNotasModelos.getTxtCodigoProyecto().getText();

		if (StringUtils.isNotBlank(codigoProyecto)) {
			params.put("codigoProyecto", codigoProyecto);
		}

		PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantallaMantenimientoNotasModelos.getFrameParent(),
				MDSQLConstants.CMD_SEARCH_MODEL, params);
		MDSQLUIHelper.show(pantallaSeleccionModelos);
		seleccionado = pantallaSeleccionModelos.getSeleccionado();
		pantallaMantenimientoNotasModelos.setModeloSeleccionado(seleccionado);
	}
	
	private void eventBtnGuardar() {
		try {
			Aviso avisoSeleccionado = pantallaMantenimientoNotasModelos.getAvisoSeleccionado();

			if (!Objects.isNull(avisoSeleccionado)) {
				modificacion(avisoSeleccionado);
			}
			else {
				alta();
			}

			clearForm();
			clearList();
			cargarAvisosModelo(pantallaMantenimientoNotasModelos.getModeloSeleccionado());
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoNotasModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void alta() throws ServiceException {
		AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
		Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
		String codUsr = session.getCodUsr();

		Modelo modelo = pantallaMantenimientoNotasModelos.getModeloSeleccionado();
		
		if (!Objects.isNull(modelo)) {
			String codigoProyecto = modelo.getCodigoProyecto();
			String descripcionAviso = pantallaMantenimientoNotasModelos.getTxtDescripcion().getText();
			String txtAviso = pantallaMantenimientoNotasModelos.getTxtTitulo().getText();

			String codNivelAviso = StringUtils.EMPTY;
			NivelImportancia nivelAviso = (NivelImportancia) pantallaMantenimientoNotasModelos.getCmbImportancia().getSelectedItem();
			if (!Objects.isNull(nivelAviso)) {
				codNivelAviso = nivelAviso.getCodigoNivelAviso().toString();
			}

			String codPeticion = pantallaMantenimientoNotasModelos.getTxtPeticion().getText();

			avisoService.altaAviso(codigoProyecto, descripcionAviso, txtAviso, codNivelAviso, codPeticion, codUsr);
		}
	}

	private void modificacion(Aviso avisoSeleccionado) throws ServiceException {
		AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
		Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
		String codUsr = session.getCodUsr();

		Modelo modelo = pantallaMantenimientoNotasModelos.getModeloSeleccionado();
		String codigoProyecto = modelo.getCodigoProyecto();
		BigDecimal codigoAviso = avisoSeleccionado.getCodigoAviso();
		String descripcionAviso = pantallaMantenimientoNotasModelos.getTxtDescripcion().getText();
		String txtAviso = pantallaMantenimientoNotasModelos.getTxtTitulo().getText();

		String codNivelAviso = StringUtils.EMPTY;
		NivelImportancia nivelAviso = (NivelImportancia) pantallaMantenimientoNotasModelos.getCmbImportancia().getSelectedItem();
		if (!Objects.isNull(nivelAviso)) {
			codNivelAviso = nivelAviso.getCodigoNivelAviso().toString();
		}

		String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaMantenimientoNotasModelos.getChkHabilitada().isSelected());
		String codPeticion = pantallaMantenimientoNotasModelos.getTxtPeticion().getText();

		avisoService.modificarAviso(codigoProyecto, codigoAviso, descripcionAviso, txtAviso, codNivelAviso, mcaHabilitado, codPeticion, codUsr);
	}

	private void cargarModelo(Modelo modeloSeleccionado) {
		try {
			clearForm();
			clearList();

			if (!Objects.isNull(modeloSeleccionado)) {
				pantallaMantenimientoNotasModelos.getTxtCodigoProyecto().setText(modeloSeleccionado.getCodigoProyecto());
				pantallaMantenimientoNotasModelos.getTxtModeloProyecto().setText(modeloSeleccionado.getNombreModelo());

				cargarAvisosModelo(modeloSeleccionado);
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoNotasModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void cargarAvisosModelo(Modelo modeloSeleccionado) throws ServiceException {
		if (!Objects.isNull(modeloSeleccionado)) {
			// Limpiar la tabla de avisos
			NotasModeloTableModel tableModel = (NotasModeloTableModel) pantallaMantenimientoNotasModelos.getTblNotasModelos().getModel();
			tableModel.clearData();
	
			// Hacer la consulta
			AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
			List<Aviso> avisos = avisoService.consultaAvisosModelo(modeloSeleccionado.getCodigoProyecto());
	
			if (CollectionUtils.isNotEmpty(avisos)) {
				tableModel.setData(avisos);
			}
		}
	}

	private void cargarNivelesImportancia() throws ServiceException {
		AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);

		List<NivelImportancia> niveles = avisoService.consultaNivelesImportancia();
		if (CollectionUtils.isNotEmpty(niveles)) {
			NivelesImportanciaComboBoxModel nivelesImportanciaComboBoxModel = new NivelesImportanciaComboBoxModel(niveles);
			pantallaMantenimientoNotasModelos.getCmbImportancia().setModel(nivelesImportanciaComboBoxModel);
		}
	}

	private void clearForm() {
		pantallaMantenimientoNotasModelos.getTxtPeticion().setText(StringUtils.EMPTY);
		MDSQLUIHelper.setSelectedItem(pantallaMantenimientoNotasModelos.getCmbImportancia(), null);
		pantallaMantenimientoNotasModelos.getChkHabilitada().setSelected(Boolean.FALSE);
		pantallaMantenimientoNotasModelos.getTxtTitulo().setText(StringUtils.EMPTY);
		pantallaMantenimientoNotasModelos.getTxtDescripcion().setText(StringUtils.EMPTY);
		pantallaMantenimientoNotasModelos.getTxtUsuarioAlta().setText(StringUtils.EMPTY);
		pantallaMantenimientoNotasModelos.getTxtFechaAlta().setText(StringUtils.EMPTY);
		pantallaMantenimientoNotasModelos.getTxtUsuarioModificacion().setText(StringUtils.EMPTY);
		pantallaMantenimientoNotasModelos.getTxtFechaModificacion().setText(StringUtils.EMPTY);

		pantallaMantenimientoNotasModelos.getBtnGuardar().setEnabled(Boolean.TRUE);
	}

	private void clearList() {
		NotasModeloTableModel tableModel = (NotasModeloTableModel) pantallaMantenimientoNotasModelos
				.getTblNotasModelos().getModel();
		tableModel.clearData();
	}

	@Override
	public void onLoad() {
		try {
			cargarNivelesImportancia();
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoNotasModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
}
