package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class CuadresObjetosTableModel extends DefaultTableModel<CuadreObjeto> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public CuadresObjetosTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public CuadresObjetosTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public CuadresObjetosTableModel(List<CuadreObjeto> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CuadreObjeto row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNombreObjeto();
            case 1:
                return row.getTipoObjeto();
            case 2:
                return row.getTipoAccion();
            case 3:
                return row.getNumeroOperacionBBDD();
            case 4:
                return row.getNumeroOperacionScript();
            default:
                break;
        }

        return null;
    }
}
