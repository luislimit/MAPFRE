package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.ScriptParche;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class VerParchesScriptTableModel extends DefaultTableModel<ScriptParche> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5715520235047183111L;
	
	/**
	 * @param cabecera
	 */
	public VerParchesScriptTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public VerParchesScriptTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public VerParchesScriptTableModel(List<ScriptParche> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		ScriptParche row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getNumeroOrden();
		} else if (1 == columnIndex) {
			return row.getDescripcionEstadoEjecucion();
		} else if (2 == columnIndex) {
			return row.getFechaScript();
		} else if (3 == columnIndex) {
			return row.getIdProceso();
		} else if (4 == columnIndex) {
			return row.getNumeroEjecucion();
		} else if (5 == columnIndex) {
			return row.getNumeroIteracion();
		} else if (6 == columnIndex) {
			return row.getNombreScript();
		} else if (7 == columnIndex) {
			return row.getTxtComentario();
		}
		
		return null;
	}
}
