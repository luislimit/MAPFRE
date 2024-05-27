package com.mdsql.ui.listener.tables;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.PantallaEjecutarScripts;
import com.mdsql.ui.model.ScriptsTableModel;

public class VigenteScriptsTableListener extends ScriptsTableListener {

	public VigenteScriptsTableListener(PantallaEjecutarScripts pantallaEjecutarScript) {
		super(pantallaEjecutarScript);
	}

	@Override
	protected Script getScriptSeleccionado(Integer index) {
		ScriptsTableModel tableModel = (ScriptsTableModel) pantallaEjecutarScripts
				.getTblVigente().getModel();
		return tableModel.getSelectedRow(index);
	}
}
