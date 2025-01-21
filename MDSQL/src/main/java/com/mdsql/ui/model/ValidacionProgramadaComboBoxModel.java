package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ValidacionProgramada;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author federico
 *
 */
public class ValidacionProgramadaComboBoxModel extends AbstractListModel<ValidacionProgramada>
        implements ComboBoxModel<ValidacionProgramada> {

    /**
     *
     */
    private static final long serialVersionUID = -8083638254718894808L;

    private final List<ValidacionProgramada> lista;

    private ValidacionProgramada selection = null;

    public ValidacionProgramadaComboBoxModel() {
        super();
        lista = new ArrayList<>();
    }

    public ValidacionProgramadaComboBoxModel(List<ValidacionProgramada> lista, boolean opcional) {
        super();
        this.lista = new ArrayList<>();
        if (opcional) {
            this.lista.add(new ValidacionProgramada());
        }
        this.lista.addAll(lista);
    }

    public ValidacionProgramadaComboBoxModel(List<ValidacionProgramada> lista) {
        this(lista, true);
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public ValidacionProgramada getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (ValidacionProgramada) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

}
