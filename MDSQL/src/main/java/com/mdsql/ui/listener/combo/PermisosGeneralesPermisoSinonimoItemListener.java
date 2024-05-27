package com.mdsql.ui.listener.combo;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.ui.PantallaPermisosGeneralesporModeloporTipoObjeto;
import com.mdsql.ui.utils.MDSQLUIHelper;

public class PermisosGeneralesPermisoSinonimoItemListener extends PermisoSinonimoItemListener {

	private PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto;

	public PermisosGeneralesPermisoSinonimoItemListener(PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto) {
		super();
		this.pantallaPermisosGeneralesporModeloporTipoObjeto = pantallaPermisosGeneralesporModeloporTipoObjeto;
	}

	@Override
	public void processItem(String item) {
		if ("Permiso".equals(item)) {
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().setSelectedItem(null);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.FALSE);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().setText(StringUtils.EMPTY);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().setEnabled(Boolean.FALSE);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.TRUE);
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion(), literales.getLiteral("no"));
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso().setEnabled(Boolean.TRUE);
		}

		if (("Sinónimo").equals(item)) {
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion().setSelectedItem(null);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion().setEnabled(Boolean.FALSE);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso().setSelectedItem(null);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso().setEnabled(Boolean.FALSE);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPropietarioSinonimo().setEnabled(Boolean.TRUE);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFuncionNombre().setEnabled(Boolean.TRUE);
		}
	}

}
