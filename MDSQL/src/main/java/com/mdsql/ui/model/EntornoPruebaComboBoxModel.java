package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.EntornoPrueba;

/**
 * @author federico
 *
 */
public class EntornoPruebaComboBoxModel extends AbstractListModel<EntornoPrueba> implements ComboBoxModel<EntornoPrueba> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private final List<EntornoPrueba> list;

	private EntornoPrueba selection = null;

	/**
	 *
	 */
	public EntornoPruebaComboBoxModel() {
		super();
		list = new ArrayList<>();
	}

	/**
	 *
     * @param list */
	public EntornoPruebaComboBoxModel(List<EntornoPrueba> list) {
		super();
		this.list = new ArrayList<>();
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
	public EntornoPrueba getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (EntornoPrueba) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

	public EntornoPrueba getByName(String code) {
		for (EntornoPrueba entornoPrueba : list) {
			if (!Objects.isNull(entornoPrueba) && entornoPrueba.getNombreEntorno().equals(code)) {
				return entornoPrueba;
			}
		}
		return null;
	}
}


