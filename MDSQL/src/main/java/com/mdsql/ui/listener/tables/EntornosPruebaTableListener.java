package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.ui.PantallaMantenimientoEntornosPrueba;
import com.mdsql.ui.model.EntornosPruebaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntornosPruebaTableListener extends ListenerSupport implements ListSelectionListener {

    private final PantallaMantenimientoEntornosPrueba pantalla;

    public EntornosPruebaTableListener(PantallaMantenimientoEntornosPrueba pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        Integer index = lsm.getMinSelectionIndex();

        EntornosPruebaTableModel tableModel = (EntornosPruebaTableModel) pantalla.getTblEntornos().getModel();

        EntornoPrueba seleccionado = tableModel.getSelectedRow(index);
        if (!Objects.isNull(seleccionado)) {
            LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
            pantalla.getTxtNombreEntorno().setText(seleccionado.getNombreEntorno());
            pantalla.getTxtBBDD().setText(seleccionado.getBbdd());
            pantalla.getTxtEsquema().setText(seleccionado.getEsquema());
            pantalla.getTxtTablespace().setText(seleccionado.getTablespace());
            pantalla.getTxtGradoparal().setText(seleccionado.getGradoParal().toString());
            pantalla.getTxtDescripcion().setText(seleccionado.getDescripcion());

            Boolean habilitada = AppHelper.normalizeCheckValue(seleccionado.getMcaHabilitado());
            pantalla.getChkHabilitada().setSelected(habilitada);

            pantalla.getBtnGuardar().setEnabled(Boolean.TRUE);
        }
    }
}
