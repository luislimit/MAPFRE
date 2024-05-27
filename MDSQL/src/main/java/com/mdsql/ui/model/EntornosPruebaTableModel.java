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

		if (columnIndex == 0) {
			return row.getNombreEntorno();
		} else if (1 == columnIndex) {
			return row.getBbdd();
		} else if (2 == columnIndex) {
			return row.getEsquema();
		} else if (3 == columnIndex) {
			return row.getTablespace();
		} else if (4 == columnIndex) {
			return row.getGradoParal();
		} else if (5 == columnIndex) {
			return row.getDescripcion();
		}

		return null;
	}
}
