package com.mdval.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.collections.CollectionUtils;

import com.mdval.ui.model.cabeceras.Cabecera;

import lombok.Getter;

/**
 * @author federico
 *
 */
public abstract class DefaultTableModel<T> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8351154302842174012L;

	@Getter
	protected List<T> data;
	
	@Getter
	protected Cabecera cabecera;
	
	private final List<String> columnNames;
	private final List<Class<?>> columnClasses ;
	
	
	/**
	 * @param cabecera
	 */
	public DefaultTableModel(Cabecera cabecera) {
		this.data = new ArrayList<>();
		this.cabecera = cabecera;
		this.columnNames = cabecera.getColumnIdentifiers();
		this.columnClasses = cabecera.getColumnClasses();
	}
	
	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public DefaultTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		this.data = new ArrayList<>();
		this.columnNames = columnNames;
		this.columnClasses = columnClasses;
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public DefaultTableModel(List<T> data, List<String> columnNames, List<Class<?>> columnClasses) {
		this.data = data;
		this.columnNames = columnNames;
		this.columnClasses = columnClasses;
	}
	
	/**
	 * @param newData
	 */
	public void setData(List<T> newData) {
		if (!CollectionUtils.isEmpty(this.data)) {
			this.data.clear();
		}
		
		if (!CollectionUtils.isEmpty(newData)) {
			this.data.addAll(newData);
		}
	}
	
	/**
	 * @param newData
	 */
	public void addData(T newData) {
		this.data.add(newData);
		this.fireTableDataChanged();
	}
	
	/**
	 * @param object
	 */
	public void removeData(T object) {
		this.data.remove(object);
		this.fireTableDataChanged();
	}
	
	/**
	 * 
	 */
	public void clearData() {
		this.data.clear();
		this.fireTableDataChanged();
	}

	/**
	 *
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	/**
	 *
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses.get(columnIndex);
	}

	/**
	 *
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/**
	 *
	 */
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/**
	 *
	 */
	public abstract Object getValueAt(int rowIndex, int columnIndex);
	
	/**
	 * @param rowIndex
	 * @return
	 */
	public T getSelectedRow(int rowIndex) {
		return (rowIndex != -1) ? data.get(rowIndex) : null;
	}
}
