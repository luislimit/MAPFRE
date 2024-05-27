package com.mdsql.ui.listener.tables;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.PantallaEjecutarScripts;
import com.mdsql.ui.model.ScriptsTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;

public class VigenteScriptsTableModelListener implements TableModelListener {

	private PantallaEjecutarScripts pantallaEjecutarScripts;

	public VigenteScriptsTableModelListener(PantallaEjecutarScripts pantallaEjecutarScript) {
		this.pantallaEjecutarScripts = pantallaEjecutarScript;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		ScriptsTableModel tableModel = (ScriptsTableModel) e.getSource();
		ScriptsTableModel historicoTableModel = (ScriptsTableModel) pantallaEjecutarScripts.getTblHistorico()
				.getModel();

		Script script = tableModel.getSelectedRow(e.getFirstRow());
		Boolean selected = script.getSelected();

		// Están en orden en la tabla
		if (Boolean.FALSE.equals(selected)) {
			for (int i = e.getFirstRow(); i < 2; i++) {
				Script scr = tableModel.getSelectedRow(i);
	
				if (!"Ejecutado".equals(scr.getDescripcionEstadoScript())
						|| !"Error".equals(scr.getDescripcionEstadoScript())
						|| !"Descuadrado".equals(scr.getDescripcionEstadoScript())
						|| !"Excepción".equals(scr.getDescripcionEstadoScript())) {
					scr.setSelected(selected);
				}
			}
	
			
			for (int i = 0; i < historicoTableModel.getRowCount(); i++) {
				Script scr = historicoTableModel.getSelectedRow(i);
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
		}
		
		if (MDSQLUIHelper.isAnySelected(tableModel) || MDSQLUIHelper.isAnySelected(historicoTableModel)) {
			pantallaEjecutarScripts.getBtnAceptar().setEnabled(Boolean.TRUE);
		}
		else {
			pantallaEjecutarScripts.getBtnAceptar().setEnabled(Boolean.FALSE);
		}
			
		// Forzamos el repintado de la tabla para actualizar los cambios
		pantallaEjecutarScripts.getTblVigente().repaint();
		pantallaEjecutarScripts.getTblHistorico().repaint();

		pantallaEjecutarScripts.getTblVigente().clearSelection();
		pantallaEjecutarScripts.getTblHistorico().clearSelection();
		pantallaEjecutarScripts.setSeleccionado(null);
		pantallaEjecutarScripts.enableButtons(Boolean.FALSE);
	}
}
