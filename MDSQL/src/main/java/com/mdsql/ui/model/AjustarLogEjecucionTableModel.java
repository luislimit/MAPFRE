package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class AjustarLogEjecucionTableModel extends DefaultTableModel<LogEjecucion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public AjustarLogEjecucionTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public AjustarLogEjecucionTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public AjustarLogEjecucionTableModel(List<LogEjecucion> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LogEjecucion row = data.get(rowIndex);

		if (columnIndex == 0) {
			return row.getNombreScript();
		} else if (1 == columnIndex) {
			return row.getIdProceso();
		} else if (2 == columnIndex) {
			return row.getNumeroIteracion();
		} else if (3 == columnIndex) {
			return row.getNumeroEjecucion();
		} else if (4 == columnIndex) {
			return row.getNumeroSentencia();
		} else if (5 == columnIndex) {
			return row.getTipoObjeto();
		} else if (6 == columnIndex) {
			return row.getTipoAccion();
		} else if (7 == columnIndex) {
			return row.getNombreObjeto();
		} else if (8 == columnIndex) {
			return row.getDescripcionEstadoEjecucion();
		} else if (9 == columnIndex) {
			return row.getMcaEliminada();
		} else if (10 == columnIndex) {
			return row.getTxtObsElimina();
		}

		return null;
	}
}
