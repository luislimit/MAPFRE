package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Informe;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

public class HistoricoCambiosModeloTableModel extends DefaultTableModel<Informe> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public HistoricoCambiosModeloTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public HistoricoCambiosModeloTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public HistoricoCambiosModeloTableModel(List<Informe> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Informe row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getFechaCambio();
            case 1:
                return row.getNombreObjeto();
            case 2:
                return row.getTipoObjeto();
            case 3:
                return row.getNombreElemento();
            case 4:
                return row.getTipoElemento();
            case 5:
                return row.getTipoCambio();
            case 6:
                return row.getDetalle();
            case 7:
                return row.getCodPeticion();
            case 8:
                return row.getCodUsr();
            case 9:
                return row.getNombreScript();
            default:
                break;
        }
        return null;
    }
}
