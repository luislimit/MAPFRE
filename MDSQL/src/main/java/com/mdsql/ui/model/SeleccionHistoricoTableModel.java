package com.mdsql.ui.model;

import java.util.List;

import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class SeleccionHistoricoTableModel extends DefaultTableModel<SeleccionHistorico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191724356955356391L;
	
	/**
	 * @param cabecera
	 */
	public SeleccionHistoricoTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public SeleccionHistoricoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}
	
	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public SeleccionHistoricoTableModel(List<SeleccionHistorico> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		SeleccionHistorico row = data.get(rowIndex);
		
		if (0 == columnIndex) {
			return row.getConfigurado();
		} else if (1 == columnIndex) {
			return row.getObjeto();
		} else if (2 == columnIndex) {
			return row.getTipo();
		} else if (3 == columnIndex) {
			return row.getHistorico();
		} else if (4 == columnIndex) {
			return row.getVigente();
		}  
		
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		SeleccionHistorico row = data.get(rowIndex);
		if (0 == columnIndex) {
			row.setConfigurado((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		if (3 == columnIndex) {
			row.setHistorico((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		if (4 == columnIndex) {
			row.setVigente((Boolean) aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		SeleccionHistorico row = data.get(rowIndex);
		if (0 == columnIndex) {
			return (row.getConfigurado() && !row.getEditable()) ? false : true;
		}
		
		if (3 == columnIndex) {
			//return (row.getHistorico() && !row.getEditable()) ? false : true;
			return true;
		}
		
		if (4 == columnIndex) {
			return true;
		}

		return false;
	}
	
	/**
	 * @param model
	 * @return
	 */
	public boolean checkAllConfigured() {
		for (SeleccionHistorico sel : data) {
			if (!sel.getConfigurado()) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * @param model
	 * @return
	 */
	public boolean checkAllVigente() {
		for (SeleccionHistorico sel : data) {
			if (!sel.getVigente()) {
				return false;
			}
		}

		return true;
	}
}
