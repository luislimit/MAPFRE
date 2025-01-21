package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Variable;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

public class VariableTableModel extends DefaultTableModel<Variable> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public VariableTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public VariableTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public VariableTableModel(List<Variable> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Variable row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getCodigoVariable();
            case 1:
                return row.getValor();
            case 2:
                return row.getTipo();
            case 3:
                return row.getEntorno();
            case 4:
                return row.getValorSustituir();
            case 5:
                return row.getBbdd();
            case 6:
                return row.getHabilitada();
            case 7:
                return row.getUsoInterno();
            case 8:
                return row.getMcaExcepcion();
            case 9:
                return row.getPeticion();
            default:
                break;
        }

        return null;
    }
}
