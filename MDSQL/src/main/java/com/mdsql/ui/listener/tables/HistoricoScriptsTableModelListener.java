package com.mdsql.ui.listener.tables;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.PantallaEjecutarScripts;
import com.mdsql.ui.model.ScriptsTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;

public class HistoricoScriptsTableModelListener implements TableModelListener {

	private PantallaEjecutarScripts pantallaEjecutarScripts;

	public HistoricoScriptsTableModelListener(PantallaEjecutarScripts pantallaEjecutarScript) {
		this.pantallaEjecutarScripts = pantallaEjecutarScript;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		ScriptsTableModel tableModel = (ScriptsTableModel) e.getSource();
		ScriptsTableModel vigenteTableModel = (ScriptsTableModel) pantallaEjecutarScripts.getTblVigente()
				.getModel();

		Script script = tableModel.getSelectedRow(e.getFirstRow());
		Boolean selected = script.getSelected();
		if (Boolean.FALSE.equals(selected)) {
			for (int i = e.getFirstRow(); i < 2; i++) {
				Script scr = tableModel.getSelectedRow(i);
	
				if ("Pendiente".equals(scr.getDescripcionEstadoScript())) {
					scr.setSelected(selected);
				}
			}
		}
		else {
			for (int i = e.getFirstRow(); i >= 0; i--) {
				Script scr = tableModel.getSelectedRow(i);
	
				if ("Pendiente".equals(scr.getDescripcionEstadoScript())) {
					scr.setSelected(selected);
				}
			}
	
			for (int i = 0; i < vigenteTableModel.getRowCount(); i++) {
				Script scr = vigenteTableModel.getSelectedRow(i);
				if ("Pendiente".equals(scr.getDescripcionEstadoScript())) {
					scr.setSelected(selected);
				}
			}
		}
		
		if (MDSQLUIHelper.isAnySelected(tableModel) || MDSQLUIHelper.isAnySelected(vigenteTableModel)) {
			pantallaEjecutarScripts.getBtnAceptar().setEnabled(Boolean.TRUE);
		}
		else {
			pantallaEjecutarScripts.getBtnAceptar().setEnabled(Boolean.FALSE);
		}

		// Forzamos el repintado de las tablas para actualizar los cambios
		pantallaEjecutarScripts.getTblVigente().repaint();
		pantallaEjecutarScripts.getTblHistorico().repaint();
		
		pantallaEjecutarScripts.getTblHistorico().clearSelection();
		pantallaEjecutarScripts.setSeleccionado(null);
		pantallaEjecutarScripts.enableButtons(Boolean.FALSE);
	}
}
