package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.Estado;

/**
 * @author federico
 *
 */
public class EstadoComboBoxModel extends AbstractListModel<Estado> implements ComboBoxModel<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<Estado> estados;

	private Estado selection = null;
	
	public EstadoComboBoxModel() {
		super();
		estados = new ArrayList<>();
	}

	public EstadoComboBoxModel(List<Estado> estados) {
		super();
		this.estados = estados;
	}

	@Override
	public int getSize() {
		return estados.size();
	}

	@Override
	public Estado getElementAt(int index) {
		return estados.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (Estado) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
