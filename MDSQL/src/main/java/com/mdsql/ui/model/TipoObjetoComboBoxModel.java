package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author federico
 *
 */
public class TipoObjetoComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3700195174395455435L;

	private List<String> tipos;

	private String selection = null;
	
	public TipoObjetoComboBoxModel() {
		super();
		tipos = new ArrayList<>();
	}

	public TipoObjetoComboBoxModel(List<String> tipos) {
		super();
		this.tipos = new ArrayList<>();
		this.tipos.add(null);
		this.tipos.addAll(tipos);
	}

	@Override
	public int getSize() {
		return tipos.size();
	}

	@Override
	public String getElementAt(int index) {
		return tipos.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (String) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
