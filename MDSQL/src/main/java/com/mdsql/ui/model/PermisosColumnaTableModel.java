package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.List;

/**
 * @author LVARONA
 *
 */
public class PermisosColumnaTableModel extends DefaultTableModel<PermisoColumna> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public PermisosColumnaTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public PermisosColumnaTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public PermisosColumnaTableModel(List<PermisoColumna> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PermisoColumna row = data.get(rowIndex);
        DateFormatter dateInformeFormatter;

        switch (columnIndex) {
            case 0:
                return row.getCodUsrGrant();
            case 1:
                return row.getNomObjeto();
            case 2:
                return row.getNomColumna();
            case 3:
                return row.getValGrant();
            case 4:
                return row.getDesEntorno();
            case 5:
                return row.getMcaGrantOption();
            case 6:
                return row.getMcaPdc();
            case 7:
                return row.getMcaHabilitado();
            case 8:
                return row.getCodPeticion();
            case 9:
                return row.getCodUsrAlta();
            case 10:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFecAlta());
            case 11:
                return row.getCodUsr();
            case 12:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFecActu());
            default:
                break;
        }
        return null;
    }
}
