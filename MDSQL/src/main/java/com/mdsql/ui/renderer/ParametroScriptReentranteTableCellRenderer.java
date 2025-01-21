package com.mdsql.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.mdsql.utils.MDSQLConstants;

public class ParametroScriptReentranteTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
        private final boolean isEditableValue;
        

    public ParametroScriptReentranteTableCellRenderer(boolean isEditableValue) {
        this.isEditableValue = isEditableValue;
    }

        
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                setColorCelda(c, col, isSelected);

		return c;
	}

	/**
	 * @param c
	 * @param s
	 */
	private void setColorCelda(Component c, int col, boolean isSelected) {
		c.setForeground(Color.black);

		Color color = (col == 0 || !isEditableValue) ? MDSQLConstants.TEXT_DISABLED_BGCOLOR
				: (!isSelected) ? Color.WHITE : MDSQLConstants.CELL_SELECTED_BGCOLOR;
		c.setBackground(color);
	}
}