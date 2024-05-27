package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class ResumenProcesadoScriptsTableModel extends DefaultTableModel<ScriptEjecutado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public ResumenProcesadoScriptsTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ResumenProcesadoScriptsTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ResumenProcesadoScriptsTableModel(List<ScriptEjecutado> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		ScriptEjecutado row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getNumeroOrden().intValue();
		} else if (1 == columnIndex) {
			return row.getDescripcionEstadoScript();
		} else if (2 == columnIndex) {
			return row.getFechaEjecucion();
		} else if (3 == columnIndex) {
			return row.getTxtCuadreOperacion();
		} else if (4 == columnIndex) {
			return row.getTxtCueadreObj();
		} else if (5 == columnIndex) {
			return row.getMcaErrores();
		} else if (6 == columnIndex) {
			return row.getNombreScript();
		}
		
		return null;
	}
}
