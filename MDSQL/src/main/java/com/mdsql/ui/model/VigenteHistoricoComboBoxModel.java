package com.mdsql.ui.model;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author federico
 *
 */
@Slf4j
public class VigenteHistoricoComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private List<String> responses;

	private String selection = null;

	/**
	 *
	 */
	public VigenteHistoricoComboBoxModel() {
		super();
		
		responses = new ArrayList<>();
		responses.add(null);
		responses.add("Vigente");
		responses.add("Histórico");
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
