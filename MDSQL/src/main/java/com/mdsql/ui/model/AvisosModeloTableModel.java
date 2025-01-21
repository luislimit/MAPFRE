package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Aviso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

public class AvisosModeloTableModel extends DefaultTableModel<Aviso> {

    /**
     *
     */
    private static final long serialVersionUID = -8306918464859990294L;

    /**
     * @param cabecera
     */
    public AvisosModeloTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public AvisosModeloTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public AvisosModeloTableModel(List<Aviso> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aviso row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNivelImportancia().getDescripcionNivelAviso();
            case 1:
                return row.getNombreObjeto();
            case 2:
                return row.getTitulo();
            case 3:
                return row.getMcaHabilitado();
            case 4:
                return row.getCodigoPeticion();
            case 5:
                return row.getDescripcion();
            default:
                break;
        }
        return null;
    }
}
