package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.Historico;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.List;

public class HistoricoTableModel extends DefaultTableModel<Historico> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8306918464859990294L;

	/**
	 * @param cabecera
	 */
	public HistoricoTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public HistoricoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public HistoricoTableModel(List<Historico> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Historico row = data.get(rowIndex);

		if (columnIndex == 0) {
			return row.getNombreObjeto();
		} else if (1 == columnIndex) {
			return row.getHistorificado();
		} else if (2 == columnIndex) {
			return row.getTipoObjeto();
		} else if (3 == columnIndex) {
			return row.getPeticion();
		} else if (4 == columnIndex) {
			return row.getFechaActualizacion();
		} else if (5 == columnIndex) {
			return row.getCodigoUsuario();
		}

		return null;
	}
}
