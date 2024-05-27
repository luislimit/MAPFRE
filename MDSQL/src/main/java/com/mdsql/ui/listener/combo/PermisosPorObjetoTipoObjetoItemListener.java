package com.mdsql.ui.listener.combo;

import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.ui.PantallaDetallePermisosPorObjeto;
import com.mdsql.ui.model.PermisoComboBoxModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public class PermisosPorObjetoTipoObjetoItemListener extends TipoObjetoItemListener {

	private PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto;

	public PermisosPorObjetoTipoObjetoItemListener(PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto) {
		super();
		this.pantallaDetallePermisosPorObjeto = pantallaDetallePermisosPorObjeto;
	}

	@Override
	public void processItem(String item) {
		try {
			PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);

			List<String> permisos = permisosService.consultarPermisosPorTipoObjeto(item);

			PermisoComboBoxModel permisoComboBoxModel = new PermisoComboBoxModel(permisos);
			pantallaDetallePermisosPorObjeto.getCmbPermiso().setModel(permisoComboBoxModel);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaDetallePermisosPorObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	@Override
	public void processDeselected() {
		PermisoComboBoxModel permisoComboBoxModel = new PermisoComboBoxModel();
		pantallaDetallePermisosPorObjeto.getCmbPermiso().setModel(permisoComboBoxModel);
	}

}
