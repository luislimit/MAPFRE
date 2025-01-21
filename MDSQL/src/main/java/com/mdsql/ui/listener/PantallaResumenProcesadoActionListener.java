package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputConsultaProcesado;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.EntregaService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.PantallaConsultaMovimientosProcesado;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaVerErroresScript;
import com.mdsql.ui.model.ResumenProcesadoScriptsTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.EstadosProcesado;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.UIHelper;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public class PantallaResumenProcesadoActionListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaResumenProcesado pantalla;

    /**
     * @param pantalla
     */
    public PantallaResumenProcesadoActionListener(PantallaResumenProcesado pantalla) {
        this.pantalla = pantalla;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnEntregar())) {
            evtEntregar();
        } else if (obj.equals(pantalla.getBtnMovimientos())) {
            evtMovimientos();
        } else if (obj.equals(pantalla.getBtnVerErrores())) {
            evtVerErrores();
        } else if (obj.equals(pantalla.getBtnDetalleScript())) {
            evtDetalleScript();
        } else if (obj.equals(pantalla.getBtnVerLog())) {
            evtVerLog();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void evtVerLog() {
        Map<String, Object> params = new HashMap<>();

        ScriptEjecutado seleccionado = pantalla.getSeleccionado();

        // Consultamos el proceso seleccionado por si fuera una consulta
        Proceso proceso = pantalla.getProcesoSeleccionado();

        params.put("script", seleccionado);
        params.put("proceso", proceso);
        params.put("consulta", Boolean.TRUE);

        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaAjustarLogEjecucion.class, params);
    }

    private void evtDetalleScript() {
        //Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Map<String, Object> params = new HashMap<>();

        ScriptEjecutado seleccionado = pantalla.getSeleccionado();

        // Consultamos el proceso seleccionado por si fuera una consulta
        Proceso proceso = pantalla.getProcesoSeleccionado();

        params.put("script", seleccionado.getNombreScript());
        params.put("proceso", proceso.getIdProceso());
        params.put("numeroOrden", seleccionado.getNumeroOrden());

        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaDetalleScript.class, params);
    }

    private void evtVerErrores() {
        Map<String, Object> params = new HashMap<>();

        ScriptEjecutado seleccionado = pantalla.getSeleccionado();

        // Consultamos el proceso seleccionado por si fuera una consulta
        Proceso proceso = pantalla.getProcesoSeleccionado();

        params.put("script", seleccionado);
        params.put("proceso", proceso);
        params.put("tipo", "scripts");

        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaVerErroresScript.class, params);
    }

    /**
     *
     */
    private void evtEntregar() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            Proceso proceso = pantalla.getProcesoSeleccionado();
            BigDecimal idProceso = proceso.getIdProceso();
            //String codigoProyecto = proceso.getModelo().getCodigoProyecto();

            Integer response = UIHelper.showConfirm("¿Desea entregar el procesado?", "Entregar");

            if (response == JOptionPane.YES_OPTION) {

                ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
                // Recuperar los archivos del proceso
                OutputFicherosPeticion outputFicheros = procesoService.consultaFicherosAccion(EstadosProcesado.ENTREGADO.getIndex(), idProceso);
                MDSQLUIHelper.showWarnings(pantalla, outputFicheros.getWarnings());

                // Ejecutamos las acciones de los ficheros dispararando excepción si no se puede realizar la acción
                procesoService.ejecutarFicherosAccion(outputFicheros, true);

                // Realizar la entrega
                String txtComentario = pantalla.getTxtComentarios().getText();
                String codUsr = session.getCodUsr();
                String versionErwin = pantalla.getTxtVersionErwin().getText();
                // Confirmar la entrega del proceso
                EntregaService entregaService = (EntregaService) getService(MDSQLConstants.ENTREGA_SERVICE);
                OutputValor<String> outputEstado = entregaService.entregarPeticion(idProceso, codUsr, txtComentario, versionErwin);
                MDSQLUIHelper.showWarnings(pantalla, outputEstado.getWarnings());
                String estado = outputEstado.getValor();

                // Crear los ficheros de entrega
                proceso.setDescripcionEstadoProceso(estado);
                pantalla.getReturnParams().put("cmd", MDSQLConstants.CMD_ENTREGAR_SCRIPT);
                pantalla.getReturnParams().put("estado", estado);

                if ("Entregado".equals(proceso.getDescripcionEstadoProceso())) {
                    session.setProceso(null);
                }

                pantalla.dispose();
            }
        } catch (ServiceException /*| IOException*/ e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        try {
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

            Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
            if (Objects.isNull(proceso)) {
                Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
                proceso = session.getProceso();
            }
            pantalla.setProcesoSeleccionado(proceso);
            OutputConsultaProcesado output = procesoService.consultaProcesado(proceso.getIdProceso());

            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());

            if (!Objects.isNull(output)) {
                populateProceso(output);
                populateScripts(output.getListaScriptsEjecutados());
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param output
     */
    private void populateProceso(OutputConsultaProcesado output) {
        MDSQLUIHelper.resetText(pantalla.getTxtModelo(),
                output.getNombreModelo(), 20);

        MDSQLUIHelper.resetText(pantalla.getTxtSubmodelo(),
                output.getDescripcionSubProyecto(), 20);

        pantalla.getTxtBBDD().setText(output.getNombreBBDD());
        pantalla.getTxtEsquema().setText(output.getNombreEsquema());
        pantalla.getTxtBBDDHistorico().setText(output.getNombreBBDDHistorico());
        pantalla.getTxtEsquemaHistorico().setText(output.getNombreesquemaHistorico());
        pantalla.getTxtPeticion().setText(output.getCodigoPeticion());
        pantalla.getTxtUsuario().setText(output.getCodigoUsuario());
        pantalla.getTxtSolicitadaPor().setText(output.getCodigoUsrPeticion());
        pantalla.getTxtFecha().setText(output.getFechaProceso().toString());

        pantalla.getTxtEstado().setText(output.getDescripcionEstadoProceso());
        EstadosProcesado estado = EstadosProcesado.getByCode(output.getCodigoEstadoProceso());
        Color backgroundColor = estado.getColor();
        if (backgroundColor != null) {
            pantalla.getTxtEstado().setBackground(backgroundColor);
            pantalla.getTxtEstado().setForeground(MDSQLUIHelper.getColorContraste(backgroundColor));
        }

        if (pantalla.getProcesoSeleccionado() != null
                && pantalla.getProcesoSeleccionado().getIdProceso() != null) {
            pantalla.getTxtIdProcesado().setText(pantalla.getProcesoSeleccionado().getIdProceso().toString());
        }

        MDSQLUIHelper.resetText(pantalla.getTxtRuta(),
                output.getTxtRutaEntrada(), null);

        pantalla.getTxtComentarios().setText(output.getTxtComentario());

        pantalla.getTxtVersionErwin().setText(output.getVersionErwin());
        pantalla.getTxtVersionado().setText(output.getVersionado());
        pantalla.getTxtDescripcion().setText(output.getDescripcion());
    }

    /**
     * @param listaScriptsEjecutados
     */
    private void populateScripts(List<ScriptEjecutado> listaScriptsEjecutados) {
        // Obtiene el modelo y lo actualiza
        ResumenProcesadoScriptsTableModel tableModel = (ResumenProcesadoScriptsTableModel) pantalla
                .getTblScripts().getModel();
        tableModel.setData(listaScriptsEjecutados);
    }

    private void evtMovimientos() {
        Proceso proceso = pantalla.getProcesoSeleccionado();

        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_PROCESO, proceso);

        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaConsultaMovimientosProcesado.class, params);
    }

}
