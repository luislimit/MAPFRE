package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.SeleccionModelosTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaSeleccionModelosTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaSeleccionModelos pantallaSeleccionModelos;

	public PantallaSeleccionModelosTableListener(PantallaSeleccionModelos pantallaSeleccionModelos) {
		super();
		this.pantallaSeleccionModelos = pantallaSeleccionModelos;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		SeleccionModelosTableModel tableModel = (SeleccionModelosTableModel) pantallaSeleccionModelos.getTblModelos().getModel();
		
		Modelo seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaSeleccionModelos.setSeleccionado(seleccionado);

			String opcion = (String) pantallaSeleccionModelos.getParams().get("opcion");

			if ("mntoNotasModelos".equals(opcion)) {
				pantallaSeleccionModelos.getBtnNotas().setEnabled(Boolean.TRUE);
			}
			else if ("mntoVariables".equals(opcion)) {
				pantallaSeleccionModelos.getBtnVariables().setEnabled(Boolean.TRUE);
			}
			else if ("mntoPermisosGenerales".equals(opcion)) {
				pantallaSeleccionModelos.getBtnPermisosGenerales().setEnabled(Boolean.TRUE);
			}
			else {
				pantallaSeleccionModelos.getBtnSeleccionar().setEnabled(Boolean.TRUE);
			}
		}
	}

	
}
