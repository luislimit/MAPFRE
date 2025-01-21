package com.mdsql.ui.listener.tables;

import com.mdsql.bussiness.entities.Variable;
import com.mdsql.ui.PantallaMantenimientoVariables;
import com.mdsql.ui.model.VariableTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.utils.AppHelper;
import com.mdval.utils.DateFormatter;
import com.mdval.utils.LogWrapper;
import java.util.Objects;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariablesTableListener extends ListenerSupport implements ListSelectionListener {

    private final PantallaMantenimientoVariables pantallaMantenimientoVariables;

    private final DateFormatter dateFormatter;

    public VariablesTableListener(PantallaMantenimientoVariables pantallaMantenimientoVariables) {
        super();
        this.pantallaMantenimientoVariables = pantallaMantenimientoVariables;

        dateFormatter = new DateFormatter();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        Integer index = lsm.getMinSelectionIndex();

        VariableTableModel tableModel = (VariableTableModel) pantallaMantenimientoVariables.getTblVariables().getModel();

        Variable seleccionada = tableModel.getSelectedRow(index);
        if (!Objects.isNull(seleccionada)) {
            LogWrapper.debug(log, "Selected: %s", seleccionada.toString());

            pantallaMantenimientoVariables.getTxtCodigoVariable().setText(seleccionada.getCodigoVariable());
            pantallaMantenimientoVariables.getTxtValorVariable().setText(seleccionada.getValor());
            MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbUsoInterno(), seleccionada.getUsoInterno());
            pantallaMantenimientoVariables.getTxtPeticion().setText(seleccionada.getPeticion());
            pantallaMantenimientoVariables.getTxtValorSustituir().setText(seleccionada.getValorSustituir());
            MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbTipoVariable(), seleccionada.getTipo());
            pantallaMantenimientoVariables.getTxtBBDD().setText(seleccionada.getBbdd());
            MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbEntorno(), seleccionada.getEntorno());
            pantallaMantenimientoVariables.getChkHabilitada().setSelected(AppHelper.normalizeCheckValue(seleccionada.getHabilitada()));

            pantallaMantenimientoVariables.getChkExcepcion().setSelected(AppHelper.normalizeCheckValue(seleccionada.getMcaExcepcion()));

            pantallaMantenimientoVariables.getTxtUsuarioAlta().setText(seleccionada.getUsrAlta());

            String sFecha = dateFormatter.dateToString(seleccionada.getFechaAlta());
            pantallaMantenimientoVariables.getTxtFechaAlta().setText(sFecha);

            pantallaMantenimientoVariables.getTxtUsuarioModificacion().setText(seleccionada.getUsrModificacion());

            sFecha = dateFormatter.dateToString(seleccionada.getFechaModificacion());
            pantallaMantenimientoVariables.getTxtFechaModificacion().setText(sFecha);
            pantallaMantenimientoVariables.getTxtComentario().setText(seleccionada.getComentario());

            pantallaMantenimientoVariables.getBtnGuardar().setEnabled(Boolean.TRUE);
        }
    }

}
