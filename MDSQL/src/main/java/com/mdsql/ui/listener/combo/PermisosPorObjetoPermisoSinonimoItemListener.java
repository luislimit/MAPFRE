package com.mdsql.ui.listener.combo;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.ui.PantallaDetallePermisosPorObjeto;

public class PermisosPorObjetoPermisoSinonimoItemListener extends PermisoSinonimoItemListener {

	private PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto;

	public PermisosPorObjetoPermisoSinonimoItemListener(PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto) {
		super();
		this.pantallaDetallePermisosPorObjeto = pantallaDetallePermisosPorObjeto;
	}

	@Override
	public void processItem(String item) {
		if ("Permiso".equals(item)) {
			pantallaDetallePermisosPorObjeto.getCmbPropietarioSinonimo().setSelectedItem(null);
			pantallaDetallePermisosPorObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.FALSE);
			pantallaDetallePermisosPorObjeto.getTxtFuncionNombre().setText(StringUtils.EMPTY);
			pantallaDetallePermisosPorObjeto.getTxtFuncionNombre().setEnabled(Boolean.FALSE);

			pantallaDetallePermisosPorObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.TRUE);
			pantallaDetallePermisosPorObjeto.getCmbPermiso().setEnabled(Boolean.TRUE);
		}

		if (("Sinónimo").equals(item)) {
			pantallaDetallePermisosPorObjeto.getCmbWithGrantOpcion().setSelectedItem(null);
			pantallaDetallePermisosPorObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.FALSE);
			pantallaDetallePermisosPorObjeto.getCmbPermiso().setSelectedItem(null);
			pantallaDetallePermisosPorObjeto.getCmbPermiso().setEnabled(Boolean.FALSE);

			pantallaDetallePermisosPorObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.TRUE);
			pantallaDetallePermisosPorObjeto.getTxtFuncionNombre().setEnabled(Boolean.TRUE);
		}
	}

}
