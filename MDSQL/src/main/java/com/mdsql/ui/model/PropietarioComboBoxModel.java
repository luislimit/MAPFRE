package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.Propietario;

/**
 * @author federico
 *
 */
public class PropietarioComboBoxModel extends AbstractListModel<Propietario> implements ComboBoxModel<Propietario> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<Propietario> list;

	private Propietario selection = null;

	/**
	 *
	 */
	public PropietarioComboBoxModel() {
		super();
		list = new ArrayList<>();
	}

	/**
	 *
	 */
	public PropietarioComboBoxModel(List<Propietario> list) {
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
	public Propietario getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (Propietario) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

	public Propietario getByCode(String code) {
		for (Propietario propietario : list) {
			if (!Objects.isNull(propietario) && propietario.getCodPropietario().equals(code)) {
				return propietario;
			}
		}

		return null;
	}

}
