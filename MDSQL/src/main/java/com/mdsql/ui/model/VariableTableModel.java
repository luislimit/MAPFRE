package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Variable;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

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

		if (columnIndex == 0) {
			return row.getCodigoVariable();
		} else if (1 == columnIndex) {
			return row.getValor();
		} else if (2 == columnIndex) {
			return row.getTipo();
		} else if (3 == columnIndex) {
			return row.getEntorno();
		} else if (4 == columnIndex) {
			return row.getValorSustituir();
		} else if (5 == columnIndex) {
			return row.getBbdd();
		} else if (6 == columnIndex) {
			return row.getHabilitada();
		} else if (7 == columnIndex) {
			return row.getUsoInterno();
		} else if (8 == columnIndex) {
			return row.getPeticion();
		}

		return null;
	}
}
