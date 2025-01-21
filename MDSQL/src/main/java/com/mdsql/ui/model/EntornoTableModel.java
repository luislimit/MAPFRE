package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Entorno;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class EntornoTableModel extends DefaultTableModel<Entorno> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8306918464859990294L;

	/**
	 * @param cabecera
	 */
	public EntornoTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public EntornoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public EntornoTableModel(List<Entorno> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Entorno row = data.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return row.getBbdd();
                case 1:
                    return row.getEsquema();
                case 2:
                    return row.getPassword();
                case 3:
                    return row.getComentario();
                case 4:
                    return row.getHabilitado();
                default:
                    break;
            }

		return null;
	}
}
