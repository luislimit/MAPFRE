package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.SeleccionModelosTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaSeleccionModelosTableListener extends ListenerSupport implements ListSelectionListener {

    private PantallaSeleccionModelos pantallaSeleccionModelos;

    public PantallaSeleccionModelosTableListener(PantallaSeleccionModelos pantallaSeleccionModelos) {
        super();
        this.pantallaSeleccionModelos = pantallaSeleccionModelos;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        Integer index = lsm.getMinSelectionIndex();

        SeleccionModelosTableModel tableModel = (SeleccionModelosTableModel) pantallaSeleccionModelos.getTblModelos().getModel();

        Modelo seleccionado = tableModel.getSelectedRow(index);
        if (!Objects.isNull(seleccionado)) {
            pantallaSeleccionModelos.setSeleccionado(seleccionado);

            String opcion = (String) pantallaSeleccionModelos.getParams().get(MDSQLConstants.P_IN_OPCION_MENU);
            if (opcion == null) {
                pantallaSeleccionModelos.getBtnSeleccionar().setEnabled(Boolean.TRUE);
            } else {
                switch (opcion) {
                    case MDSQLConstants.MNU_NOTAS_MODELOS:
                        pantallaSeleccionModelos.getBtnNotas().setEnabled(Boolean.TRUE);
                        break;
                    case MDSQLConstants.MNU_VARIABLES:
                        pantallaSeleccionModelos.getBtnVariables().setEnabled(Boolean.TRUE);
                        break;
                    case MDSQLConstants.MNU_PERMISOS_GENERALES:
                        pantallaSeleccionModelos.getBtnPermisosGenerales().setEnabled(Boolean.TRUE);
                        break;
                    case MDSQLConstants.MNU_MANTENIMIENTO_PERMISOS:
                        pantallaSeleccionModelos.getBtnPermisosPorColumna().setEnabled(true);
                        pantallaSeleccionModelos.getBtnPermisosPorObjeto().setEnabled(true);
                        break;
                    default:
                        pantallaSeleccionModelos.getBtnSeleccionar().setEnabled(Boolean.TRUE);
                        break;
                }
            }
        }
    }

}
