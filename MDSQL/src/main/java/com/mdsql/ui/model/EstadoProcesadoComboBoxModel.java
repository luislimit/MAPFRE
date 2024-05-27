package com.mdsql.ui.model;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.utils.MDSQLConstants.EstadosProcesado;

/**
 * @author federico
 *
 */
public class EstadoProcesadoComboBoxModel extends AbstractListModel<EstadosProcesado> implements ComboBoxModel<EstadosProcesado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083638254718894808L;

	private EstadosProcesado[] estados = EstadosProcesado.values();

	private EstadosProcesado selection = null;

	@Override
	public int getSize() {
		return estados.length;
	}

	@Override
	public EstadosProcesado getElementAt(int index) {
		return estados[index];
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (EstadosProcesado) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
