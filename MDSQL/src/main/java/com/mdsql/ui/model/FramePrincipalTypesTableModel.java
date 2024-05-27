package com.mdsql.ui.model;

import java.util.List;
import java.util.Objects;

import com.mdsql.bussiness.entities.Type;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class FramePrincipalTypesTableModel extends DefaultTableModel<Type> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public FramePrincipalTypesTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public FramePrincipalTypesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public FramePrincipalTypesTableModel(List<Type> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Type row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return (!Objects.isNull(row.getNumeroOrdenType())) ? row.getNumeroOrdenType().intValue() : "";
		} else if (1 == columnIndex) {
			return row.getNombreObjeto();
		} 
 
		return null;
	}
}
