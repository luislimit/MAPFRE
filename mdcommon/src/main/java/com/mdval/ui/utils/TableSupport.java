package com.mdval.ui.utils;

import java.util.List;
import java.util.Objects;

import javax.swing.JTable;

import org.apache.commons.collections.CollectionUtils;

import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TableSupport extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2528825112240587759L;
	
	/**
	 * 
	 */
	public TableSupport() {
		super();
	}

	/**
	 * @param isAutoresized
	 */
	public TableSupport(Boolean isAutoresized) {
		super();
		
		if (!isAutoresized) {
			setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
	}
	
	/**
	 * @param tableModel
	 */
	public void initModel(DefaultTableModel<?> tableModel) {
		this.setModel(tableModel);
		
		setColumnWidths(tableModel.getCabecera());
	}
	
	/**
	 * @param cabecera
	 */
	public void setColumnWidths(Cabecera cabecera) {
		List<Integer> widths = cabecera.getColumnSizes();
		
		if (CollectionUtils.isNotEmpty(widths)) {
	        for (int i = 0; i < widths.size(); i++) {
	            if (i < columnModel.getColumnCount()) {
	            	Integer width = widths.get(i);
	            	if (!Objects.isNull(width)) {
	            		columnModel.getColumn(i).setPreferredWidth(width);
	            	}
	            }
	            else break;
	        }
		}
    }

	/**
	 * 
	 */
	public void forceRepaintColumn(Integer column) {
		// Corregir bug de no mostrado de resultados redimensionando la pantalla en 1
		// punto
		Integer width = columnModel.getColumn(column).getWidth();

		// sumamos 1 punto por dimension y redimensionamos
		Integer newWidth = width + 1;
		columnModel.getColumn(column).setPreferredWidth(newWidth);
		columnModel.getColumn(column).setPreferredWidth(width);
	}
}
