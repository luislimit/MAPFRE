package com.mdsql.ui.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author federico
 *
 */
public class PermisoComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3700195174395455435L;

	private List<String> list;

	private String selection = null;

	public PermisoComboBoxModel() {
		super();
		list = new ArrayList<>();
	}

	public PermisoComboBoxModel(List<String> list) {
		super();
		this.list = new ArrayList<>();
		this.list.add(null);
		this.list.addAll(list);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public String getElementAt(int index) {
		return list.get(index);
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
