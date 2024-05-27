package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Modelo;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class SeleccionModelosTableModel extends DefaultTableModel<Modelo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public SeleccionModelosTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public SeleccionModelosTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public SeleccionModelosTableModel(List<Modelo> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Modelo row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getCodigoProyecto();
		} else if (1 == columnIndex) {
			return row.getNombreModelo();
		} else if (2 == columnIndex) {
			return row.getNombreEsquema();
		} else if (3 == columnIndex) {
			return row.getNombreBbdd();
		} else if (4 == columnIndex) {
			return row.getNombreCarpetaAdj();
		} else if (5 == columnIndex) {
			return row.getCodigoCapaUsrown();
		} else if (6 == columnIndex) {
			return row.getMcaVariables();	
		} else if (7 == columnIndex) {
			return row.getMcaGrantAll();
		} else if (8 == columnIndex) {
			return row.getMcaGrantPublic();
		} else if (9 == columnIndex) {
			return row.getMcaInh();
		} else if (10 == columnIndex) {
			return row.getObservaciones();
		} else if (11 == columnIndex) {
			return row.getEntregaPDC();
		} 
 
		return null;
	}
}
