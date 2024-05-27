package com.mdsql.ui.listener.combo;

import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.ui.PantallaMantenimientoPermisosPorObjeto;
import com.mdsql.ui.model.PermisoComboBoxModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public class MantenimientoPermisosPorObjetoTipoObjetoItemListener extends TipoObjetoItemListener {

	private PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto;

	public MantenimientoPermisosPorObjetoTipoObjetoItemListener(PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto) {
		super();
		this.pantallaMantenimientoPermisosPorObjeto = pantallaMantenimientoPermisosPorObjeto;
	}

	@Override
	public void processItem(String item) {
		try {
			PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);

			List<String> permisos = permisosService.consultarPermisosPorTipoObjeto(item);

			PermisoComboBoxModel permisoComboBoxModel = new PermisoComboBoxModel(permisos);
			pantallaMantenimientoPermisosPorObjeto.getCmbPermiso().setModel(permisoComboBoxModel);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoPermisosPorObjeto.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	@Override
	public void processDeselected() {
		PermisoComboBoxModel permisoComboBoxModel = new PermisoComboBoxModel();
		pantallaMantenimientoPermisosPorObjeto.getCmbPermiso().setModel(permisoComboBoxModel);
	}

}
