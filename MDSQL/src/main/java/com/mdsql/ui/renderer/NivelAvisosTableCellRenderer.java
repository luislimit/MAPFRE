package com.mdsql.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Objects;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.ColorCeldaNota;

public class NivelAvisosTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		Aviso aviso = ((ProcesarScriptNotaTableModel) table.getModel()).getSelectedRow(row);
		
		if (!Objects.isNull(aviso)) {
			Integer orden = aviso.getNivelImportancia().getCodigoNivelAviso().intValue();
			setColorCelda(c, orden, col, isSelected);
		}

		return c;
	}

	/**
	 * @param c
	 * @param s
	 */
	private void setColorCelda(Component c, Integer o, int col, boolean isSelected) {
		ColorCeldaNota colorCelda = ColorCeldaNota.getByOrden(o);
		Color bgColor = Objects.isNull(colorCelda) ? Color.WHITE : colorCelda.getValue();

		c.setForeground(Color.black);

		Color color = (col == 0) ? bgColor
				: (!isSelected) ? Color.WHITE : MDSQLConstants.CELL_SELECTED_BGCOLOR;
		c.setBackground(color);
	}
}