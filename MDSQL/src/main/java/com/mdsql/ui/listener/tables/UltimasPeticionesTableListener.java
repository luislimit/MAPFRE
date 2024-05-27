package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.ui.PantallaProcesar;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UltimasPeticionesTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaProcesar pantallaProcesar;

	public UltimasPeticionesTableListener(PantallaProcesar pantallaProcesar) {
		super();
		this.pantallaProcesar = pantallaProcesar;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();

		ProcesarScriptUltimasPeticionesTableModel tableModel = (ProcesarScriptUltimasPeticionesTableModel) pantallaProcesar
				.getTblUltimasPeticiones().getModel();

		Proceso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaProcesar.setProcesoSeleccionado(seleccionado);
			pantallaProcesar.getBtnVerProcesado().setEnabled(Boolean.TRUE);
		}
	}

}
