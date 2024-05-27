package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.ui.PantallaMantenimientoNotasModelos;
import com.mdsql.ui.model.NotasModeloTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.DateFormatter;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotasModeloTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaMantenimientoNotasModelos pantallaMantenimientoNotasModelos;

	private DateFormatter dateFormatter;

	public NotasModeloTableListener(PantallaMantenimientoNotasModelos pantallaMantenimientoNotasModelos) {
		super();
		this.pantallaMantenimientoNotasModelos = pantallaMantenimientoNotasModelos;

		dateFormatter = new DateFormatter();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();

		NotasModeloTableModel tableModel = (NotasModeloTableModel) pantallaMantenimientoNotasModelos.getTblNotasModelos().getModel();
		
		Aviso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaMantenimientoNotasModelos.setAvisoSeleccionado(seleccionado);

			pantallaMantenimientoNotasModelos.getTxtPeticion().setText(seleccionado.getCodigoPeticion());
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoNotasModelos.getCmbImportancia(), seleccionado.getNivelImportancia());
			pantallaMantenimientoNotasModelos.getTxtTitulo().setText(seleccionado.getDescripcionAviso());
			pantallaMantenimientoNotasModelos.getTxtDescripcion().setText(seleccionado.getTxtAviso());
			pantallaMantenimientoNotasModelos.getTxtUsuarioAlta().setText(seleccionado.getCodigoUsrAlta());

			String sFecha = dateFormatter.dateToString(seleccionado.getFechaAlta());
			pantallaMantenimientoNotasModelos.getTxtFechaAlta().setText(sFecha);

			pantallaMantenimientoNotasModelos.getTxtUsuarioModificacion().setText(seleccionado.getCodigoUsuario());
			sFecha = dateFormatter.dateToString(seleccionado.getFechaActualizacion());
			pantallaMantenimientoNotasModelos.getTxtFechaModificacion().setText(sFecha);

			Boolean habilitado = AppHelper.normalizeCheckValue(seleccionado.getMcaHabilitado());
			pantallaMantenimientoNotasModelos.getChkHabilitada().setSelected(habilitado);

			pantallaMantenimientoNotasModelos.getBtnGuardar().setEnabled(Boolean.TRUE);
		}
	}

	
}
