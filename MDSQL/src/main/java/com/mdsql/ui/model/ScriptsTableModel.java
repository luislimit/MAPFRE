package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class ScriptsTableModel extends DefaultTableModel<Script> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;
    private boolean seleccionBloqueada = false;

    /**
     * @param cabecera
     */
    public ScriptsTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public ScriptsTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ScriptsTableModel(List<Script> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    public void setSeleccionBloqueada(boolean seleccionBloqueada) {
        this.seleccionBloqueada = seleccionBloqueada;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Script row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getSelected();
            case 1:
                return row.getNumeroOrden().intValue();
            case 2:
                return row.getDescripcionEstadoScript();
            case 3:
                return row.getFecha();
            case 4:
                return row.getOperaciones();
            case 5:
                return row.getObjetos();
            case 6:
                return row.getNombreScript();
            default:
                break;
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Script row = data.get(rowIndex);
        if (0 == columnIndex) {
            if ("Ejecutado".equals(row.getDescripcionEstadoScript())) {
                row.setSelected(Boolean.FALSE);
                fireTableCellUpdated(rowIndex, columnIndex);
                return;
            }
            row.setSelected(seleccionBloqueada || (Boolean) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Script row = data.get(rowIndex);

        if (0 == columnIndex) {
            return !("Ejecutado".equals(row.getDescripcionEstadoScript())
                    || "Error".equals(row.getDescripcionEstadoScript())
                    || "Descuadrado".equals(row.getDescripcionEstadoScript())
                    || "Excepción".equals(row.getDescripcionEstadoScript())
                    || seleccionBloqueada);
        }
        return false;
    }
}
