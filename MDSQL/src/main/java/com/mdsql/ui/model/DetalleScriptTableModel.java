package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.DetObjeto;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class DetalleScriptTableModel extends DefaultTableModel<DetObjeto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public DetalleScriptTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public DetalleScriptTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public DetalleScriptTableModel(List<DetObjeto> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DetObjeto row = data.get(rowIndex);

		if (columnIndex == 0) {
			return row.getNumeroSentencia();
		} else if (1 == columnIndex) {
			return row.getNombreObjetoPadre();
		} else if (2 == columnIndex) {
			return row.getTipoObjetoPadre();
		} else if (3 == columnIndex) {
			return row.getTipoAccionPadre();
		} else if (4 == columnIndex) {
			return row.getNombreObjeto();
		} else if (5 == columnIndex) {
			return row.getTipoObjeto();
		} else if (6 == columnIndex) {
			return row.getTipoAccion();
		} else if (7 == columnIndex) {
			return row.getTipoDato();
		} else if (8 == columnIndex) {
			return row.getNumeroLongitud();
		} else if (9 == columnIndex) {
			return row.getNumeroDecimal();
		}

		return null;
	}
}
