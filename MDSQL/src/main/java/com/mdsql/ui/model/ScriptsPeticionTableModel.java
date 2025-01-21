package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ScriptPeticion;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

/**
 *
 * @author LVARONA
 */
public class ScriptsPeticionTableModel extends DefaultTableModel<ScriptPeticion> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public ScriptsPeticionTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public ScriptsPeticionTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ScriptsPeticionTableModel(List<ScriptPeticion> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ScriptPeticion row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNombreTabla();
            case 1:
                return row.getNombreScript();
            case 2:
                return row.getDescripcionEstadoScript();
            case 3:
                return row.getFecha();
            case 4:
                return row.getCodUsr();
        }

        return null;
    }
}
