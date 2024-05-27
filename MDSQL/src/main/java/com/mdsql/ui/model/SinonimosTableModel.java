package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Sinonimo;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.List;

/**
 * @author federico
 *
 */
public class SinonimosTableModel extends DefaultTableModel<Sinonimo> {

	/**
	 *
	 */
	private static final long serialVersionUID = -4191724356955356391L;

	/**
	 * @param cabecera
	 */
	public SinonimosTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public SinonimosTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public SinonimosTableModel(List<Sinonimo> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Sinonimo row = data.get(rowIndex);

		if (0 == columnIndex) {
			return row.getCodUsrGrant();
		} else if (1 == columnIndex) {
			return row.getTipObjeto();
		} else if (2 == columnIndex) {
			return row.getDesEntorno();
		} else if (3 == columnIndex) {
			return row.getCodOwnerSyn();
		} else if (4 == columnIndex) {
			return row.getMcaPdc();
		} else if (5 == columnIndex) {
			return row.getMcaHabilitado();
		} else if (6 == columnIndex) {
			return row.getCodPeticion();
		} else if (7 == columnIndex) {
			return row.getValReglaSyn();
		}

		return null;
	}
}
