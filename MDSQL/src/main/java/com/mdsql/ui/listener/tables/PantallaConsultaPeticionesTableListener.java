package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.ui.PantallaConsultaPeticiones;
import com.mdsql.ui.model.ConsultaPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaConsultaPeticionesTableListener extends ListenerSupport implements ListSelectionListener {

    private final PantallaConsultaPeticiones pantalla;

    public PantallaConsultaPeticionesTableListener(PantallaConsultaPeticiones pantalla) {
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

        ConsultaPeticionesTableModel tableModel = (ConsultaPeticionesTableModel) pantalla
                .getTblPeticiones().getModel();

        Proceso seleccionado = tableModel.getSelectedRow(index);
        if (!Objects.isNull(seleccionado)) {
            LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
            pantalla.setSeleccionado(seleccionado);
            pantalla.getBtnCargarProcesado().setEnabled(Boolean.TRUE);
            
            // La lógica de habilitación de estos botones depende del registro
            pantalla.getBtnRechazar().setEnabled(seleccionado.getMcaRechazar().equals("S"));
            pantalla.getBtnIncidencia().setEnabled(seleccionado.getMcaIncidencia().equals("S"));
            pantalla.getBtnEntregar().setEnabled(seleccionado.getMcaEntregar().equals("S"));
            pantalla.getBtnExcluir().setEnabled(seleccionado.getMcaExcluir().equals("S"));
        }
    }
}
