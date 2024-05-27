package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Permiso;
import com.mdsql.ui.PantallaPermisosGeneralesporModeloporTipoObjeto;
import com.mdsql.ui.model.GrantComboBoxModel;
import com.mdsql.ui.model.PermisosTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermisosTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto;

	public PermisosTableListener(PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto) {
		super();
		this.pantallaPermisosGeneralesporModeloporTipoObjeto = pantallaPermisosGeneralesporModeloporTipoObjeto;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		PermisosTableModel tableModel = (PermisosTableModel) pantallaPermisosGeneralesporModeloporTipoObjeto.getTblPermisos().getModel();
		
		Permiso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaPermisosGeneralesporModeloporTipoObjeto.setPermisoSeleccionado(seleccionado);
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermisoSinonimo(), "Permiso");
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbEntorno(), seleccionado.getDesEntorno());
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbTipoObjeto(), seleccionado.getTipObjeto());
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbPermiso(), seleccionado.getValGrant());
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbWithGrantOpcion(), seleccionado.getMcaGrantOption());

			String codReceptor = seleccionado.getCodUsrGrant();
			GrantComboBoxModel model = (GrantComboBoxModel) pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso().getModel();
			Grant grant = model.getByCode(codReceptor);
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbReceptorPermiso(), grant);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtPeticion().setText(seleccionado.getCodPeticion());
			MDSQLUIHelper.setSelectedItem(pantallaPermisosGeneralesporModeloporTipoObjeto.getCmbIncluirPDC(), seleccionado.getMcaPdc());
			Boolean habilitada = AppHelper.normalizeCheckValue(seleccionado.getMcaHabilitado());
			pantallaPermisosGeneralesporModeloporTipoObjeto.getChkHabilitado().setSelected(habilitada);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtUsuarioAlta().setText(seleccionado.getCodUsrAlta());
			String sFecha = dateFormatter.dateToString(seleccionado.getFecAlta());
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFechaAlta().setText(sFecha);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtUsuarioModificacion().setText(seleccionado.getCodUsr());

			sFecha = dateFormatter.dateToString(seleccionado.getFecActu());
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTxtFechaModificacion().setText(sFecha);

			pantallaPermisosGeneralesporModeloporTipoObjeto.getBtnGuardar().setEnabled(Boolean.TRUE);
			pantallaPermisosGeneralesporModeloporTipoObjeto.getTblSinonimos().clearSelection();
		}
	}

	
}
