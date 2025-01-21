package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class EntornosPruebaTableModel extends DefaultTableModel<EntornoPrueba> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8306918464859990294L;

	/**
	 * @param cabecera
	 */
	public EntornosPruebaTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public EntornosPruebaTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public EntornosPruebaTableModel(List<EntornoPrueba> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		EntornoPrueba row = data.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return row.getNombreEntorno();
                case 1:
                    return row.getBbdd();
                case 2:
                    return row.getEsquema();
                case 3:
                    return row.getTablespace();
                case 4:
                    return row.getGradoParal();
                case 5:
                    return row.getDescripcion();
                case 6:
                    return row.getMcaHabilitado();                    
                default:
                    break;
            }

		return null;
	}
}
