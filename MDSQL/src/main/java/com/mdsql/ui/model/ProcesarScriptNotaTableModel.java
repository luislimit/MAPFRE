package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Aviso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.List;

/**
 * @author federico
 *
 */
public class ProcesarScriptNotaTableModel extends DefaultTableModel<Aviso> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public ProcesarScriptNotaTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public ProcesarScriptNotaTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public ProcesarScriptNotaTableModel(List<Aviso> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aviso row = data.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return row.getNivelImportancia().getDescripcionNivelAviso();
            case 1:
                return row.getTitulo();
            case 2:
                return row.getCodigoPeticion();
            case 3:
                return row.getMcaHabilitado();
            case 4:
                return row.getCodigoUsuario();
            case 5:
                return row.getFechaActualizacion();
            default:
                break;
        }

        return null;
    }
}
