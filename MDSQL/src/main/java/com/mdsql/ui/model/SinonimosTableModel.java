package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Sinonimo;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.List;

/**
 * @author federico
 *
 */
public class SinonimosTableModel extends DefaultTableModel<Sinonimo> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public SinonimosTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public SinonimosTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public SinonimosTableModel(List<Sinonimo> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sinonimo row = data.get(rowIndex);
        DateFormatter dateInformeFormatter;

        switch (columnIndex) {
            case 0:
                return row.getCodUsrGrant();
            case 1:
                return row.getTipObjeto();
            case 2:
                return row.getDesEntorno();
            case 3:
                return row.getCodOwnerSyn();
            case 4:
                return row.getMcaPdc();
            case 5:
                return row.getMcaHabilitado();
            case 6:
                return row.getCodPeticion();
            case 7:
                return row.getValReglaSyn();
            case 8:
                return row.getCodUsrAlta();
            case 9:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFecAlta());
            case 10:
                return row.getCodUsr();
            case 11:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFecActu());
            default:
                break;
        }

        return null;
    }
}
