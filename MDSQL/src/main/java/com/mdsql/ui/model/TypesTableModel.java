package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Type;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class TypesTableModel extends DefaultTableModel<Type> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public TypesTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public TypesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public TypesTableModel(List<Type> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Type row = data.get(rowIndex);

		if (0 == columnIndex) {
			return row.getNumeroOrdenType().intValue();
		} else if (1 == columnIndex) {
			return row.getDescripcionEstadoScript();
		} else if (2 == columnIndex) {
			return row.getFechaCambio();
		} else if (3 == columnIndex) {
			return row.getDROP();
		} else if (4 == columnIndex) {
			return row.getTYS();
		} else if (5 == columnIndex) {
			return row.getTYB();
		} else if (6 == columnIndex) {
			return row.getPDC();
		} else if (7 == columnIndex) {
			return row.getNombreObjeto();
		}

		return null;
	}
}
