package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ProcesadoPeticion;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

/**
 *
 * @author LVARONA
 */
public class CerrarPeticionTableModel extends DefaultTableModel<ProcesadoPeticion> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public CerrarPeticionTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public CerrarPeticionTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public CerrarPeticionTableModel(List<ProcesadoPeticion> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProcesadoPeticion row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNomTabla();
            case 1:
                return row.getDescripcion();
            case 2:
                return row.getDescripcionEstadoProceso();
            case 3:
                return row.getErwin();
            case 4:
                return row.getFecha();
            case 5:
                return row.getCodUsr();
            default:
                break;
        }

        return null;
    }
}
