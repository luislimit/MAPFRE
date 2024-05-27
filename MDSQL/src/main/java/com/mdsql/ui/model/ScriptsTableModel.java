package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Script;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class ScriptsTableModel extends DefaultTableModel<Script> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;

	/**
	 * @param cabecera
	 */
	public ScriptsTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ScriptsTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ScriptsTableModel(List<Script> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Script row = data.get(rowIndex);

		if (0 == columnIndex) {
			return row.getSelected();
		} else if (1 == columnIndex) {
			return row.getNumeroOrden().intValue();
		} else if (2 == columnIndex) {
			return row.getDescripcionEstadoScript();
		} else if (3 == columnIndex) {
			return row.getFecha();
		} else if (4 == columnIndex) {
			return row.getOperaciones();
		} else if (5 == columnIndex) {
			return row.getObjetos();
		} else if (6 == columnIndex) {
			return row.getNombreScript();
		}

		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Script row = data.get(rowIndex);
		if (0 == columnIndex) {
			if ("Ejecutado".equals(row.getDescripcionEstadoScript())) {
				row.setSelected(Boolean.FALSE);
				fireTableCellUpdated(rowIndex, columnIndex);
				return;
			}

			row.setSelected((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		Script row = data.get(rowIndex);

		if (0 == columnIndex) {
			if ("Ejecutado".equals(row.getDescripcionEstadoScript()) || "Error".equals(row.getDescripcionEstadoScript())
					|| "Descuadrado".equals(row.getDescripcionEstadoScript())
					|| "Excepción".equals(row.getDescripcionEstadoScript())) {
				return false;
			}

			return true;
		}

		return false;
	}
}
