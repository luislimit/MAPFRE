package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class CuadresObjetosTableModel extends DefaultTableModel<CuadreObjeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public CuadresObjetosTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public CuadresObjetosTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public CuadresObjetosTableModel(List<CuadreObjeto> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		CuadreObjeto row = data.get(rowIndex);
		
		if (columnIndex == 0) {
			return row.getNombreObjeto();
		} else if (1 == columnIndex) {
			return row.getTipoObjeto();
		} else if (2 == columnIndex) {
			return row.getTipoAccion();
		} else if (3 == columnIndex) {
			return row.getNumeroOperacionBBDD();
		} else if (4 == columnIndex) {
			return row.getNumeroOperacionScript();
		} 
		
		return null;
	}
}
