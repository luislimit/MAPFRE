package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.InputEliminaLog;
import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Scriptable;
import com.mdsql.bussiness.service.LogService;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.model.AjustarLogEjecucionTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;

public class PantallaAjustarLogEjecucionListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaAjustarLogEjecucion pantalla;

    public PantallaAjustarLogEjecucionListener(PantallaAjustarLogEjecucion pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR.equals(jButton.getActionCommand())) {
            eliminarEvt();
        }
        if (MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.dispose();
        }

    }

    private void eliminarEvt() {
        try {
            eliminarRegistro();

            loadLogEjecucion();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    @Override
    public void onLoad() {
        try {
            loadLogEjecucion();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    /**
     *
     */
    private void eliminarRegistro() throws ServiceException {
        LogService logService = (LogService) getService(MDSQLConstants.LOG_SERVICE);

        LogEjecucion seleccionado = pantalla.getSeleccionado();

        InputEliminaLog inputEliminaLog = new InputEliminaLog();
        inputEliminaLog.setIdProceso(seleccionado.getIdProceso());
        inputEliminaLog.setNumeroOrden(seleccionado.getNumeroOrden());
        inputEliminaLog.setNumeroIteracion(seleccionado.getNumeroIteracion());
        inputEliminaLog.setNumeroEjecucion(seleccionado.getNumeroEjecucion());
        inputEliminaLog.setNumeroParche(seleccionado.getNumeroParche());
        inputEliminaLog.setNumeroSentencia(seleccionado.getNumeroSentencia());
        inputEliminaLog.setMcaEliminada(seleccionado.getMcaEliminada());
        String comentario = pantalla.getTxtComentario().getText();
        inputEliminaLog.setTxtComentario(comentario);

        ServiceException serviceException = logService.eliminaLog(inputEliminaLog);

        if (!Objects.isNull(serviceException)) {
            if (serviceException.getType().equals(2)) {
                MDSQLUIHelper.showWarnings(pantalla.getFrameParent(), serviceException);
            } else {
                throw serviceException;
            }
        }
    }

    /**
     *
     */
    private void loadLogEjecucion() throws ServiceException {
        LogService logService = (LogService) getService(MDSQLConstants.LOG_SERVICE);

        Scriptable script = (Scriptable) pantalla.getParams().get("script");
        Proceso proceso = (Proceso) pantalla.getParams().get("proceso");

        BigDecimal idProceso = proceso.getIdProceso();
        BigDecimal numeroOrden = script.getNumeroOrden();

        List<LogEjecucion> logEjecucion = logService.logEjecucion(idProceso, numeroOrden);

        AjustarLogEjecucionTableModel tableModel = (AjustarLogEjecucionTableModel) pantalla.getTblAjustarLog().getModel();
        tableModel.setData(logEjecucion);

        pantalla.forceRepaint();
    }

}
