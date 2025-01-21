package com.mdsql.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.mdsql.bussiness.entities.CodigoDescripcion;

/**
 *
 * @author LVARONA
 */
public class CodigoDescripcionComboBoxModel extends AbstractListModel<CodigoDescripcion> implements ComboBoxModel<CodigoDescripcion> {

    /**
     *
     */
    private static final long serialVersionUID = -8083638254718894808L;

    private final List<CodigoDescripcion> lista;

    private CodigoDescripcion selection = null;

    public CodigoDescripcionComboBoxModel() {
        super();
        lista = new ArrayList<>();
    }

    public CodigoDescripcionComboBoxModel(List<CodigoDescripcion> lista, boolean opcional) {
        super();
        this.lista = new ArrayList<>();
        if (opcional) {
            this.lista.add(new CodigoDescripcion());
        }
        this.lista.addAll(lista);
    }

    public CodigoDescripcionComboBoxModel(List<CodigoDescripcion> lista) {
        this(lista, true);
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public CodigoDescripcion getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (CodigoDescripcion) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

}
