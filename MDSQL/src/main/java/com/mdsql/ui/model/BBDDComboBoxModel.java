package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.BBDD;

/**
 * @author federico
 *
 */
public class BBDDComboBoxModel extends AbstractListModel<BBDD> implements ComboBoxModel<BBDD> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083638254718894808L;
	
	private List<BBDD> bbdds;
	
	private BBDD selection = null;
	
	/**
	 * 
	 */
	public BBDDComboBoxModel() {
		super();
		bbdds= new ArrayList<>();
	}
	
	/**
	 * 
	 */
	public BBDDComboBoxModel(List<BBDD> bbdds) {
		super();
		this.bbdds = new ArrayList<>();
		
		for (BBDD bbdd : bbdds) {
			this.bbdds.add(bbdd);
		}
	}
	
	/**
	 * 
	 */
	public void clear() {
		this.bbdds.clear();
	}

	@Override
	public int getSize() {
		return bbdds.size();
	}

	@Override
	public BBDD getElementAt(int index) {
		return bbdds.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (BBDD) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
