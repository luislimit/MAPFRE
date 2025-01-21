package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ProgramacionModelo;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

public class ValidacionesProgramadasTableModel extends DefaultTableModel<ProgramacionModelo> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public ValidacionesProgramadasTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public ValidacionesProgramadasTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ValidacionesProgramadasTableModel(List<ProgramacionModelo> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProgramacionModelo row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getCodigoProyecto();
            case 1:
                return row.getNombreModelo();
            case 2:
                return row.getTipAccion();
            case 3:
                return row.getMcaHabilitado();
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
