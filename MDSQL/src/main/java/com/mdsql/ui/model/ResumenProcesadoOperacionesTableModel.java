package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class ResumenProcesadoOperacionesTableModel extends DefaultTableModel<CuadreOperacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public ResumenProcesadoOperacionesTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ResumenProcesadoOperacionesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ResumenProcesadoOperacionesTableModel(List<CuadreOperacion> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		CuadreOperacion row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getTipoObjeto();
		} else if (1 == columnIndex) {
			return row.getTipoAccion();
		} else if (2 == columnIndex) {
			return row.getNumeroOperacionBBDD();
		} else if (3 == columnIndex) {
			return row.getNumeroOperacionScript();
		}
 
		return null;
	}
}
