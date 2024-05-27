package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.bussiness.service.PropietarioService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.PantallaDetallePermisosPorObjeto;
import com.mdsql.ui.PantallaMantenimientoPermisosPorObjeto;
import com.mdsql.ui.model.*;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import org.apache.commons.collections.CollectionUtils;

public class PantallaDetallePermisosPorObjetoListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto;
	
	public PantallaDetallePermisosPorObjetoListener(PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto) {
		super();
		this.pantallaDetallePermisosPorObjeto = pantallaDetallePermisosPorObjeto;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_OBJETO_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_OBJETO_ALTA.equals(jButton.getActionCommand())) {
			eventBtnAlta();
		}
		
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_OBJETO_MODIFICACION.equals(jButton.getActionCommand())) {
			eventBtnModificacion();
		}

		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_OBJETO_INFORME.equals(jButton.getActionCommand())) {
			eventBtnInforme();
		}

		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_OBJETO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaDetallePermisosPorObjeto.dispose();
		}
	}
	
	private void eventBtnBuscar() {
		
	}
	
	private void eventBtnAlta() {
		Modelo seleccionado = pantallaDetallePermisosPorObjeto.getModelo();
		Map<String, Object> params = new HashMap<>();

		params.put("modelo", seleccionado);

		PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto = (PantallaMantenimientoPermisosPorObjeto) MDSQLUIHelper.createDialog(pantallaDetallePermisosPorObjeto.getFrameParent(),
				MDSQLConstants.CMD_MNTO_PERMISOS_OBJETO, params);
		MDSQLUIHelper.show(pantallaMantenimientoPermisosPorObjeto);
	}
	
	private void eventBtnModificacion() {
		Modelo seleccionado = pantallaDetallePermisosPorObjeto.getModelo();
		String valuePS = (String) pantallaDetallePermisosPorObjeto.getCmbPermisoSinonimo().getSelectedItem();
		Map<String, Object> params = new HashMap<>();

		params.put("modelo", seleccionado);

		if ("Permiso".equals(valuePS)) {
			params.put("permiso", pantallaDetallePermisosPorObjeto.getPermisoSeleccionado());
		}

		if (("Sinónimo").equals(valuePS)) {
			params.put("sinonimo", pantallaDetallePermisosPorObjeto.getSinonimoSeleccionado());
		}

		PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto = (PantallaMantenimientoPermisosPorObjeto) MDSQLUIHelper.createDialog(pantallaDetallePermisosPorObjeto.getFrameParent(),
				MDSQLConstants.CMD_MNTO_PERMISOS_OBJETO, params);
		MDSQLUIHelper.show(pantallaMantenimientoPermisosPorObjeto);
	}
	
	private void eventBtnInforme() {
			
	}

	@Override
	public void onLoad() {
		try {
			TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);
			PropietarioService propietarioService = (PropietarioService) getService(MDSQLConstants.PROPIETARIO_SERVICE);

			Modelo modelo = (Modelo) pantallaDetallePermisosPorObjeto.getParams().get("modelo");
			pantallaDetallePermisosPorObjeto.setModelo(modelo);

			actualizarPermisos(modelo);
			actualizarSinonimos(modelo);

			// Rellenar combos
			OutputConsultaTiposObjeto outputConsultaTiposObjeto = tipoObjetoService.consultarTiposObjeto();
						
			// Hay avisos
			if (outputConsultaTiposObjeto.getResult() == 2) {
				ServiceException serviceException = outputConsultaTiposObjeto.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaDetallePermisosPorObjeto.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}

			List<Propietario> propietarios = propietarioService.consultarPropietariosSinonimo(modelo);
			List<Grant> receptores = propietarioService.consultarReceptoresModelo(modelo);

			if (CollectionUtils.isNotEmpty(outputConsultaTiposObjeto.getTiposObjeto())) {
				TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(outputConsultaTiposObjeto.getTiposObjeto());
				pantallaDetallePermisosPorObjeto.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
			}

			if (CollectionUtils.isNotEmpty(propietarios)) {
				PropietarioComboBoxModel propietarioComboBoxModel = new PropietarioComboBoxModel(propietarios);
				pantallaDetallePermisosPorObjeto.getCmbPropietarioSinonimo().setModel(propietarioComboBoxModel);
			}

			if (CollectionUtils.isNotEmpty(receptores)) {
				GrantComboBoxModel grantComboBoxModel = new GrantComboBoxModel(receptores);
				pantallaDetallePermisosPorObjeto.getCmbReceptorPermisos().setModel(grantComboBoxModel);
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaDetallePermisosPorObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
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
		PermisosTableModel tableModel = (PermisosTableModel) pantallaDetallePermisosPorObjeto
				.getTblPermisos().getModel();
		tableModel.clearData();

		tableModel.setData(permisosGenerales);
	}

	private void fillSinonimos(List<Sinonimo> sinonimosGenerales) throws ServiceException {
		// Obtiene el modelo y lo actualiza
		SinonimosTableModel tableModel = (SinonimosTableModel) pantallaDetallePermisosPorObjeto
				.getTblSinonimos().getModel();
		tableModel.clearData();

		tableModel.setData(sinonimosGenerales);
	}
}
