package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.DetObjeto;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class DetalleScriptTableModel extends DefaultTableModel<DetObjeto> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public DetalleScriptTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public DetalleScriptTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public DetalleScriptTableModel(List<DetObjeto> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetObjeto row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNumeroSentencia();
            case 1:
                return row.getNombreObjetoPadre();
            case 2:
                return row.getTipoObjetoPadre();
            case 3:
                return row.getTipoAccionPadre();
            case 4:
                return row.getNombreObjeto();
            case 5:
                return row.getDetalle();
            case 6:
                return row.getTipoObjeto();
            case 7:
                return row.getTipoAccion();
            case 8:
                return row.getTipoDato();
            case 9:
                return row.getNumeroLongitud();
            case 10:
                return row.getNumeroDecimal();
        }
        return null;
    }
}
