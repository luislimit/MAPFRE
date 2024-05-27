package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.Proceso;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

public class ConsultaPeticionesTableModel extends DefaultTableModel<Proceso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306918464859990294L;
	
	/**
	 * @param cabecera
	 */
	public ConsultaPeticionesTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ConsultaPeticionesTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ConsultaPeticionesTableModel(List<Proceso> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Proceso row = data.get(rowIndex);

		if (columnIndex == 0) {
			return row.getCodigoPeticion();
		} else if (1 == columnIndex) {
			return row.getDescripcionEstadoProceso();
		} else if (2 == columnIndex) {
			return row.getFechaInicio();
		} else if (3 == columnIndex) {
			return row.getCodProyecto();
		} else if (4 == columnIndex) {
			return row.getCodSubproyecto();
		} else if (5 == columnIndex) {
			return row.getMcaErrores();
		} else if (6 == columnIndex) {
			return row.getCodigoUsrPeticion();
		} else if (7 == columnIndex) {
			return row.getTxtDescripcion();
		} else if (8 == columnIndex) {
			return row.getCodigoUsr();
		} 

		return null;
	}
}
