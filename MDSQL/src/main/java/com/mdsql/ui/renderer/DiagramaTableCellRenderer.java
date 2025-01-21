package com.mdsql.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Objects;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdsql.ui.model.DiagramasConsultaTableModel;
import com.mdsql.utils.MDSQLConstants;

public class DiagramaTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int col) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        TablasDiagrama registro = ((DiagramasConsultaTableModel) table.getModel()).getSelectedRow(row);

        if (!Objects.isNull(registro)) {
            String eliminado = registro.getMcaInh();
            setColorCelda(c, eliminado, isSelected);
        }
        return c;
    }


    private void setColorCelda(Component c, String eliminado, boolean isSelected) {
        Color color = eliminado.equals("S") ? Color.RED
                : (!isSelected) ? Color.WHITE : MDSQLConstants.CELL_SELECTED_BGCOLOR;
        c.setBackground(color);
        c.setForeground(eliminado.equals("S") ? Color.WHITE: Color.black);
    }
}
