package com.mdsql.ui.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 *
 * @author LVARONA
 */
public class TableSelectionRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Color para la celda seleccionada
        Color defaultSelectedColor = UIManager.getColor("Table.selectionBackground");
        Color defaultBackgroundColor = UIManager.getColor("Table.background"); // Color.WHITE; //Color si no está seleccionada
        Color selectedCellColor = defaultSelectedColor;//Color.YELLOW;         // Color para la fila seleccionada
        Color selectedRowColor = defaultSelectedColor; //Color.LIGHT_GRAY;     // Verificar si la celda está seleccionada
        if (isSelected) {
            cellComponent.setBackground(selectedCellColor);
        } else {
            // Verificar si la fila está seleccionada
            if (table.getSelectionModel().isSelectedIndex(row)) {
                cellComponent.setBackground(selectedRowColor);
            } else {
            // Color de fondo por defecto                
                cellComponent.setBackground(defaultBackgroundColor);
            }
        }
        return cellComponent;
    }
}
