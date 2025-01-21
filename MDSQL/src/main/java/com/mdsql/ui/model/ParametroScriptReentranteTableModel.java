package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ReentranteParametro;
import java.util.List;
import com.mdsql.ui.model.cabeceras.TablaParametroReentranteCabecera;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class ParametroScriptReentranteTableModel extends DefaultTableModel<ReentranteParametro> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean isEditableValue = true;

    /**
     * @param cabecera
     * @param isEditableValue
     */
    public ParametroScriptReentranteTableModel(Cabecera cabecera, boolean isEditableValue) {
        super(cabecera);
        this.isEditableValue = isEditableValue;
    }
    
    
    /**
     * @param columnNames
     * @param columnClasses
     */
    public ParametroScriptReentranteTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ParametroScriptReentranteTableModel(List<ReentranteParametro> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReentranteParametro row = data.get(rowIndex);

        if (columnIndex == 0) {
            return row.getNombreParam();
        } else if (columnIndex == 1) {
            return row.getValorParam();
        }

        return null;
    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {
        ReentranteParametro row = data.get(rowIndex);
        if (columnIndex == 0) {
            row.setNombreParam((String) valor);
        } else if (columnIndex == 1) {
            row.setValorParam((String) valor);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Sólo se puede editar la celda del valor si no existe un procesado
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return isEditableValue && col == 1;
    }

}
