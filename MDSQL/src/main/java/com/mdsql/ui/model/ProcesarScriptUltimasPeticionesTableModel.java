package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Proceso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class ProcesarScriptUltimasPeticionesTableModel extends DefaultTableModel<Proceso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public ProcesarScriptUltimasPeticionesTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ProcesarScriptUltimasPeticionesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ProcesarScriptUltimasPeticionesTableModel(List<Proceso> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Proceso row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getCodigoPeticion();
		} else if (1 == columnIndex) {
			return row.getCodigoUsrPeticion();
		} else if (2 == columnIndex) {
			return row.getDescripcionEstadoProceso();
		} else if (3 == columnIndex) {
			return row.getFechaInicio();
		} else if (4 == columnIndex) {
			return row.getCodigoUsr();
		} else if (5 == columnIndex) {
			return row.getTxtDescripcion();
		} else if (6 == columnIndex) {
			return row.getTxtObservacionEntrega();	
		} 
 
		return null;
	}
}
