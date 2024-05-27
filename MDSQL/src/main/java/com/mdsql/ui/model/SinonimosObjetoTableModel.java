package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.SinonimoObjeto;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.List;

/**
 * @author federico
 *
 */
public class SinonimosObjetoTableModel extends DefaultTableModel<SinonimoObjeto> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public SinonimosObjetoTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public SinonimosObjetoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public SinonimosObjetoTableModel(List<SinonimoObjeto> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SinonimoObjeto row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getCodUsrGrant();
            case 1:
                return row.getTipObjeto();
            case 2:
                return row.getNomObjeto();                
            case 3:
                return row.getDesEntorno();
            case 4:
                return row.getCodOwnerSyn();
            case 5:
                return row.getMcaPdc();
            case 6:
                return row.getMcaHabilitado();
            case 7:
                return row.getCodPeticion();
            case 8:
                return row.getValReglaSyn();
            default:
                break;
        }

        return null;
    }
}
