package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.model.FicherosReentranteTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FramePrincipalFicherosReentranteTableListener extends ListenerSupport implements ListSelectionListener {

    private final FramePrincipal pantalla;

    public FramePrincipalFicherosReentranteTableListener(FramePrincipal pantalla) {
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

        FicherosReentranteTableModel tableModel = (FicherosReentranteTableModel) pantalla.getTblFicherosReentrante().getModel();

        if (tableModel != null && index >= 0) {
            Script seleccionado = tableModel.getSelectedRow(index);
            if (!Objects.isNull(seleccionado)) {
                LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
                pantalla.getIfrmReentranteFichero().setTitle(seleccionado.getNombreScript());
                MDSQLAppHelper.dumpContentToText(seleccionado.getLineasScript(), pantalla.getTxtReentranteFichero());

                pantalla.getIfrmReentranteLanza().setTitle(seleccionado.getNombreScriptLanza());
                MDSQLAppHelper.dumpContentToText(seleccionado.getLineasScriptLanza(), pantalla.getTxtReentranteLanza());
            }
        }
    }
}
