package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.NivelImportancia;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author federico
 *
 */
public class NivelesImportanciaComboBoxModel extends AbstractListModel<NivelImportancia> implements ComboBoxModel<NivelImportancia> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3700195174395455435L;

	private List<NivelImportancia> lista;

	private NivelImportancia selection = null;

	public NivelesImportanciaComboBoxModel() {
		super();
		lista = new ArrayList<>();
	}

	public NivelesImportanciaComboBoxModel(List<NivelImportancia> lista) {
		super();
		this.lista = new ArrayList<>();
		this.lista.add(null);
		this.lista.addAll(lista);
	}

	@Override
	public int getSize() {
		return lista.size();
	}

	@Override
	public NivelImportancia getElementAt(int index) {
		return lista.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (NivelImportancia) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
