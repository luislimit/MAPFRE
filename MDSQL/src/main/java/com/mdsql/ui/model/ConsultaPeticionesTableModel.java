package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Proceso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class ConsultaPeticionesTableModel extends DefaultTableModel<Proceso> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public ConsultaPeticionesTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public ConsultaPeticionesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ConsultaPeticionesTableModel(List<Proceso> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getCodigoPeticion();
            case 1:
                return row.getDescripcionEstadoProceso();
            case 2:
                return row.getFechaInicio();
            case 3:
                return row.getCodProyecto();
            case 4:
                return row.getCodSubproyecto();
            case 5:
                return row.getMcaErrores();
            case 6:
                return row.getCodigoUsrPeticion();
            case 7:
                return row.getTxtDescripcion();
            case 8:
                return row.getIdProceso();
            case 9:
                return row.getCodigoUsr();
            default:
                break;
        }

        return null;
    }
}
