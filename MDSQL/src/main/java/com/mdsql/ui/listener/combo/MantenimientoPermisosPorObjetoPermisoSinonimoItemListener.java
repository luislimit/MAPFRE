package com.mdsql.ui.listener.combo;

import com.mdsql.ui.PantallaMantenimientoPermisosPorObjeto;
import org.apache.commons.lang3.StringUtils;

public class MantenimientoPermisosPorObjetoPermisoSinonimoItemListener extends PermisoSinonimoItemListener {

	private PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto;

	public MantenimientoPermisosPorObjetoPermisoSinonimoItemListener(PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto) {
		super();
		this.pantallaMantenimientoPermisosPorObjeto = pantallaMantenimientoPermisosPorObjeto;
	}

	@Override
	public void processItem(String item) {
		if ("Permiso".equals(item)) {
			pantallaMantenimientoPermisosPorObjeto.getCmbPropietarioSinonimo().setSelectedItem(null);
			pantallaMantenimientoPermisosPorObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.FALSE);
			pantallaMantenimientoPermisosPorObjeto.getTxtFuncionNombre().setText(StringUtils.EMPTY);
			pantallaMantenimientoPermisosPorObjeto.getTxtFuncionNombre().setEnabled(Boolean.FALSE);

			pantallaMantenimientoPermisosPorObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.TRUE);
			pantallaMantenimientoPermisosPorObjeto.getCmbPermiso().setEnabled(Boolean.TRUE);
		}

		if (("Sinónimo").equals(item)) {
			pantallaMantenimientoPermisosPorObjeto.getCmbWithGrantOpcion().setSelectedItem(null);
			pantallaMantenimientoPermisosPorObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.FALSE);
			pantallaMantenimientoPermisosPorObjeto.getCmbPermiso().setSelectedItem(null);
			pantallaMantenimientoPermisosPorObjeto.getCmbPermiso().setEnabled(Boolean.FALSE);

			pantallaMantenimientoPermisosPorObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.TRUE);
			pantallaMantenimientoPermisosPorObjeto.getTxtFuncionNombre().setEnabled(Boolean.TRUE);
		}
	}

}
