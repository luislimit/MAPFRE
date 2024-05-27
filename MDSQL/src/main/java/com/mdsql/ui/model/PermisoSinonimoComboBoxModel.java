package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author federico
 *
 */
public class PermisoSinonimoComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<String> responses;

	private String selection = null;

	/**
	 *
	 */
	public PermisoSinonimoComboBoxModel() {
		super();
		
		responses = new ArrayList<>();
		responses.add(null);
		responses.add("Permiso");
		responses.add("Sinónimo");
	}

	@Override
	public int getSize() {
		return responses.size();
	}

	@Override
	public String getElementAt(int index) {
		return responses.get(index);
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
