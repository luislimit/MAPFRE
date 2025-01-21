package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.ErrorScript;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class VerErroresScriptTableModel extends DefaultTableModel<ErrorScript> {

    /**
     *
     */
    private static final long serialVersionUID = -5715520235047183111L;

    /**
     * @param cabecera
     */
    public VerErroresScriptTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public VerErroresScriptTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public VerErroresScriptTableModel(List<ErrorScript> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ErrorScript row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNumeroOrden();
            case 1:
                return row.getFechaError();
            case 2:
                return row.getIdProceso();
            case 3:
                return row.getNumeroEjecucion();
            case 4:
                return row.getNumeroIteracion();
            case 5:
                return row.getNombreScript();
            default:
                break;
        }
        return null;
    }
}
