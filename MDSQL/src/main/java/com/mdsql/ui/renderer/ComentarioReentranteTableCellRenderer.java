package com.mdsql.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Objects;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.mdsql.bussiness.entities.ReentranteComentarioColumna;
import com.mdsql.ui.model.ComentarioReentranteTableModel;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.EstadosRDO;

/**
 *
 * @author LVARONA
 */
public class ComentarioReentranteTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int col) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        ReentranteComentarioColumna registro = ((ComentarioReentranteTableModel) table.getModel()).getSelectedRow(row);

        if (!Objects.isNull(registro)) {
            setColorCelda(c, registro.getRdo(), col, isSelected);
        }
        return c;
    }

    private void setColorCelda(Component c, String s, int col, boolean isSelected) {
        Color backColor = Color.WHITE;
        Color textColor = Color.BLACK;

        if (s.equals(EstadosRDO.NOK.toString())){
            backColor = Color.RED;
            textColor = Color.WHITE;
        } else if (isSelected){
            backColor = MDSQLConstants.CELL_SELECTED_BGCOLOR;
        }
        c.setBackground(backColor);
        c.setForeground(textColor);
    }
}
