package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.ui.PantallaProcesarScript;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AvisosTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaProcesarScript pantallaProcesarScript;

	public AvisosTableListener(PantallaProcesarScript pantallaProcesarScript) {
		super();
		this.pantallaProcesarScript = pantallaProcesarScript;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantallaProcesarScript.getTblNotas().getModel();
		
		Aviso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaProcesarScript.setAvisoSeleccionado(seleccionado);
		}
	}

	
}
