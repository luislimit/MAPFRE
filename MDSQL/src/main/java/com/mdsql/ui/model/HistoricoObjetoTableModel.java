package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class HistoricoObjetoTableModel extends DefaultTableModel<HistoricoProceso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public HistoricoObjetoTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public HistoricoObjetoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public HistoricoObjetoTableModel(List<HistoricoProceso> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HistoricoProceso row = data.get(rowIndex);

		if (columnIndex == 0) {
			return row.getCodigoPeticion();
		} else if (1 == columnIndex) {
			return row.getDescripcionEstadoProceso();
		} else if (2 == columnIndex) {
			return row.getFechaProceso();
		} else if (3 == columnIndex) {
			return row.getCodigoSubProyecto();
		} else if (4 == columnIndex) {
			return row.getCodigoUsuarioPeticion();
		} else if (5 == columnIndex) {
			return row.getCodigoUsuario();
		} else if (6 == columnIndex) {
			return row.getTipoAccion();
		} else if (7 == columnIndex) {
			return row.getTipoAccionPadre();
		} else if (8 == columnIndex) {
			return row.getNombreScript();
		} else if (9 == columnIndex) {
			return row.getDescripcionEstadoScript();
		}

		return null;
	}
}
