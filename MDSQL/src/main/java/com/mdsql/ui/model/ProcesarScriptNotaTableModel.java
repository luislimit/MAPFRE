package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Aviso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class ProcesarScriptNotaTableModel extends DefaultTableModel<Aviso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public ProcesarScriptNotaTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ProcesarScriptNotaTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ProcesarScriptNotaTableModel(List<Aviso> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aviso row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getNivelImportancia().getDescripcionNivelAviso();
		} else if (1 == columnIndex) {
			return row.getDescripcionAviso();
		} else if (2 == columnIndex) {
			return row.getTxtAviso();
		} else if (3 == columnIndex) {
			return row.getCodigoPeticion();
		} else if (4 == columnIndex) {
			return row.getMcaHabilitado();
		} else if (5 == columnIndex) {
			return row.getCodigoUsuario();
		} else if (6 == columnIndex) {
			return row.getFechaActualizacion();	
		} 
 
		return null;
	}
}
