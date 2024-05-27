package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.Grant;

/**
 * @author federico
 *
 */
public class GrantComboBoxModel extends AbstractListModel<Grant> implements ComboBoxModel<Grant> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<Grant> list;

	private Grant selection = null;

	/**
	 *
	 */
	public GrantComboBoxModel() {
		super();
		list = new ArrayList<>();
	}

	/**
	 *
	 */
	public GrantComboBoxModel(List<Grant> list) {
		super();
		this.list = new ArrayList<>();
		this.list.add(null);
		this.list.addAll(list);
	}
	
	/**
	 * 
	 */
	public void clear() {
		this.list.clear();
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public Grant getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (Grant) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

	public Grant getByCode(String code) {
		for (Grant grant : list) {
			if (!Objects.isNull(grant) && grant.getCodGrant().equals(code)) {
				return grant;
			}
		}

		return null;
	}
}
