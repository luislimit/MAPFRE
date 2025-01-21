package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.HistoricoProc;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.ui.PantallaConsultaMovimientosProcesado;
import com.mdsql.ui.model.MovimientosProcesadoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaConsultaMovimientosProcesadoListener extends ListenerSupport implements ActionListener, ListSelectionListener, OnLoadListener {

    private final PantallaConsultaMovimientosProcesado pantalla;

    public PantallaConsultaMovimientosProcesadoListener(PantallaConsultaMovimientosProcesado pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnCerrar())) {
            pantalla.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        int numRow = pantalla.getTblMovimiento().getSelectedRow();
        if (numRow < 0) {
            return;
        }
        //Obtener el registro seleccionado
        MovimientosProcesadoTableModel model = (MovimientosProcesadoTableModel) pantalla.getTblMovimiento().getModel();
        HistoricoProc historicoProc = model.getSelectedRow(numRow);

        //TODO: Pendiente de que se devuelva este dato por el servicio
        pantalla.getTxtDescripcion().setText(historicoProc.getDescripcion());
    }

    @Override
    public void onLoad() {
        try {
            Proceso proceso = (Proceso) pantalla.getParams().get(MDSQLConstants.P_IN_PROCESO);
            if (proceso == null) {
                throw new ServiceException("Falta indicar el parámetro con el PROCESO");
            }
            HistoricoService service = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
            OutputConsulta<HistoricoProc> output = service.consultarHistoricoProcesado(proceso.getIdProceso());

            MovimientosProcesadoTableModel model = (MovimientosProcesadoTableModel) pantalla.getTblMovimiento().getModel();
            model.clearData();
            model.setData(output.getLista());

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
            pantalla.setErrorOnload(Boolean.TRUE);
        }
    }
}
