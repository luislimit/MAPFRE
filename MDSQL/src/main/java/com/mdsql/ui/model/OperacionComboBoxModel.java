package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.Operacion;

/**
 * @author federico
 *
 */
public class OperacionComboBoxModel extends AbstractListModel<Operacion> implements ComboBoxModel<Operacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<Operacion> operaciones;

	private Operacion selection = null;
	
	

	public OperacionComboBoxModel() {
		super();
		operaciones = new ArrayList<>();
	}

	public OperacionComboBoxModel(List<Operacion> operaciones) {
		super();
		this.operaciones = operaciones;
	}

	@Override
	public int getSize() {
		return operaciones.size();
	}

	@Override
	public Operacion getElementAt(int index) {
		return operaciones.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (Operacion) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
