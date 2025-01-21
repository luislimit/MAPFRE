package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdval.utils.DateFormatter;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class DiagramasTableModel extends DefaultTableModel<TablasDiagrama> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public DiagramasTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public DiagramasTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public DiagramasTableModel(List<TablasDiagrama> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TablasDiagrama row = data.get(rowIndex);
        DateFormatter dateInformeFormatter;

        switch (columnIndex) {
            case 0:
                return row.getNomTabla();
            case 1:
                return row.getComentario();
            case 2:
                return row.getCodPeticion();
            case 3:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFecActu());
            case 4:
                return row.getCodUsr();
            default:
                break;
        }

        return null;
    }
}
