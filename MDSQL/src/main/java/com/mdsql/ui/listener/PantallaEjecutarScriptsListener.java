package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.DlgExcepcion;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.PantallaDescartarScript;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.PantallaEjecutarScripts;
import com.mdsql.ui.PantallaRepararScript;
import com.mdsql.ui.PantallaVerCuadresScript;
import com.mdsql.ui.PantallaVerErroresScript;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.model.ScriptsTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.ui.utils.collections.ScriptSelectedClosure;
import com.mdsql.ui.utils.collections.ScriptSelectedPredicate;
import com.mdsql.ui.utils.collections.UpdateScriptsClosure;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdsql.utils.collections.ScriptPredicate;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.Cursor;

public class PantallaEjecutarScriptsListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaEjecutarScripts pantalla;
    private boolean isReentrante;

    public PantallaEjecutarScriptsListener(PantallaEjecutarScripts pantallaEjecutarScripts) {
        super();
        this.pantalla = pantallaEjecutarScripts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_RECHAZAR.equals(jButton.getActionCommand())) {
            eventBtnRechazar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_LOG.equals(jButton.getActionCommand())) {
            eventBtnVerLog();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_DETALLE_SCRIPT.equals(jButton.getActionCommand())) {
            eventBtnDetalleScript();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_DESCARTAR.equals(jButton.getActionCommand())) {
            eventBtnDescartar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_REPARAR.equals(jButton.getActionCommand())) {
            eventBtnReparar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_CUADRES.equals(jButton.getActionCommand())) {
            eventBtnVerCuadres();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_ERRORES.equals(jButton.getActionCommand())) {
            eventBtnVerErrores();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_EXCEPCION.equals(jButton.getActionCommand())) {
            eventBtnExcepcion();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
            eventBtnAceptar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.getReturnParams().put("estado", "EN_CURSO");
            pantalla.dispose();
        }
    }

    private void eventBtnRechazar() {
       /* Map<String, Object> params = new HashMap<>();

        Proceso proceso = pantalla.getProceso();

        params.put("proceso", proceso);

        DlgRechazar dlgRechazar = (DlgRechazar) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
                MDSQLConstants.CMD_RECHAZAR_PROCESADO, params);
        MDSQLUIHelper.show(dlgRechazar);

        proceso = (Proceso) dlgRechazar.getReturnParams().get("proceso");

        if ("Rechazado".equals(proceso.getDescripcionEstadoProceso())) {
            pantalla.getReturnParams().put("proceso", proceso);
            pantalla.getReturnParams().put("estado", "RECHAZADO");
            pantalla.dispose();
        }
       */
        Proceso proceso = pantalla.getProceso();
        try {
            MDSQLUIHelper.cambioEstadoProcesado(pantalla, proceso.getIdProceso(), MDSQLConstants.EstadosProcesado.RECHAZADO);
            proceso.setDescripcionEstadoProceso("Rechazado");
            pantalla.getReturnParams().put("proceso", proceso);
            pantalla.getReturnParams().put("estado", "RECHAZADO");
            pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }       
    }

    private void eventBtnVerLog() {
        Map<String, Object> params = new HashMap<>();

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put("script", seleccionado);
        params.put("proceso", proceso);

        if ("Ejecutado".equals(seleccionado.getDescripcionEstadoScript())) {
            params.put("consulta", Boolean.TRUE);
        } else {
            params.put("consulta", Boolean.FALSE);
        }

        /*PantallaAjustarLogEjecucion pantallaAjustarLogEjecucion = (PantallaAjustarLogEjecucion) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_AJUSTAR_LOG_EJECUCION,
                        params);
        MDSQLUIHelper.show(pantallaAjustarLogEjecucion);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaAjustarLogEjecucion.class, params);
    }

    private void eventBtnDetalleScript() {
        Map<String, Object> params = new HashMap<>();

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put(MDSQLConstants.P_IN_SCRIPT, seleccionado.getNombreScript());
        params.put(MDSQLConstants.P_IN_PROCESO, proceso.getIdProceso());
        params.put(MDSQLConstants.P_IN_ORDEN, seleccionado.getNumeroOrden());

        /*PantallaDetalleScript pantallaDetalleScript = (PantallaDetalleScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_DETALLE_SCRIPT, params);
        MDSQLUIHelper.show(pantallaDetalleScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaDetalleScript.class, params);
    }

    private void eventBtnDescartar() {
        Map<String, Object> params = new HashMap<>();

        Proceso proceso = pantalla.getProceso();

        params.put("proceso", proceso);
        params.put("script", pantalla.getSeleccionado());

        /*PantallaDescartarScript pantallaDescartarScript = (PantallaDescartarScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_DESCARTAR_SCRIPT, params);
        MDSQLUIHelper.show(pantallaDescartarScript);*/
        PantallaDescartarScript pantallaDescartarScript = MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaDescartarScript.class, params);

        String estado = (String) pantallaDescartarScript.getReturnParams().get("estado");
        if (StringUtils.isNotBlank(estado)) {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            proceso.setDescripcionEstadoProceso("Ejecutado");
            session.setProceso(proceso);

            cerraryEntregar(proceso);
        }
    }

    private void eventBtnReparar() {
        Map<String, Object> params = new HashMap<>();

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put("script", seleccionado);
        params.put("proceso", proceso);

        /*PantallaRepararScript pantallaRepararScript = (PantallaRepararScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_REPARAR_SCRIPT, params);
        MDSQLUIHelper.show(pantallaRepararScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaRepararScript.class, params);
    }

    private void eventBtnVerCuadres() {
        Map<String, Object> params = new HashMap<>();

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put("script", seleccionado);
        params.put("proceso", proceso);

        /*PantallaVerCuadresScript pantallaVerCuadresScript = (PantallaVerCuadresScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_VER_CUADRES_SCRIPT, params);
        MDSQLUIHelper.show(pantallaVerCuadresScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaVerCuadresScript.class, params);
    }

    private void eventBtnVerErrores() {
        Map<String, Object> params = new HashMap<>();

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put("script", seleccionado);
        params.put("proceso", proceso);
        params.put("tipo", "scripts");

        /*PantallaVerErroresScript pantallaVerErroresScript = (PantallaVerErroresScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_VER_ERRORES_SCRIPT, params);
        MDSQLUIHelper.show(pantallaVerErroresScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaVerErroresScript.class, params);
    }

    private void eventBtnExcepcion() {
        Map<String, Object> params = new HashMap<>();
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

        Script seleccionado = pantalla.getSeleccionado();
        Proceso proceso = pantalla.getProceso();

        params.put("script", seleccionado);
        params.put("proceso", proceso);

        /*DlgExcepcion dlgExcepcion = (DlgExcepcion) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
                MDSQLConstants.CMD_EXCEPCION_SCRIPT, params);
        MDSQLUIHelper.show(dlgExcepcion);*/
        DlgExcepcion dlgExcepcion = MDSQLUIHelper.showForm(pantalla.getFrameParent(),DlgExcepcion.class, params);

        Map<String, Object> returnParams = dlgExcepcion.getReturnParams();
        if (!Objects.isNull(returnParams.get("estadoScript"))) {
            String estado = (String) returnParams.get("estadoScript");
            List<Script> vigentes = ((ScriptsTableModel) pantalla.getTblVigente().getModel()).getData();
            CollectionUtils.forAllDo(vigentes, new UpdateScriptsClosure(seleccionado.getNumeroOrden(), estado));

            // Repinta la tabla vigente
            pantalla.getTblVigente().repaint();

            // Desactivar los botones Reparar y Excepcion
            pantalla.getBtnReparar().setEnabled(Boolean.FALSE);
            pantalla.getBtnExcepcion().setEnabled(Boolean.FALSE);

            // Actualiza el script en el proceso
            CollectionUtils.forAllDo(proceso.getScripts(), new UpdateScriptsClosure(seleccionado.getNumeroOrden(), estado));

            /**
             * Ver si todos los scripts están ejecutados y el estado del proceso
             * es Ejecutado para mostrar la pantalla de resumen del procesado
             */
            if (isAllExecuted(proceso.getScripts())) {
                proceso.setDescripcionEstadoProceso("Ejecutado");
                session.setProceso(proceso);

                cerraryEntregar(proceso);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void eventBtnAceptar() {
        Cursor cursor = pantalla.getCursor();
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            Proceso proceso = pantalla.getProceso();

            // Comprobar que estén todos ejecutados, si lo están cerrar esta ventana
            // inmediatamente
            if (isAllExecuted(proceso.getScripts())) {
                cerraryEntregar(proceso);
            } else {
                BBDD bbdd = proceso.getBbdd();

                List<Script> vigente = ((ScriptsTableModel) pantalla.getTblVigente().getModel())
                        .getData();
                List<Script> historico = ((ScriptsTableModel) pantalla.getTblHistorico().getModel())
                        .getData();

                // Filtrar los scripts seleccionados
                vigente = new ArrayList<>(CollectionUtils.select(vigente, new ScriptSelectedPredicate()));
                historico = new ArrayList<>(CollectionUtils.select(historico, new ScriptSelectedPredicate()));

                // Une los scripts y los ordena
                List<Script> scripts = new ArrayList<>(CollectionUtils.union(vigente, historico));
                Collections.sort(scripts, (left, right) -> left.getNumeroOrden().compareTo(right.getNumeroOrden()));

                ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
                List<OutputRegistraEjecucion> ejecuciones;
                // Cambiamos el cursor

                // Ejecuta los scripts
                pantalla.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if (isReentrante) {
                    String ruta = session.getReentranteInfo().getRuta();
                    ejecuciones = scriptService.executeScriptsReentrante(scripts, proceso.getLanzas(), ruta);
                } else {
                    ejecuciones = scriptService.executeScripts(bbdd, scripts, null);
                }
                pantalla.setCursor(cursor);

                // Hay avisos
                for (OutputRegistraEjecucion ejecucion : ejecuciones) {
                    // Por cada script muestra los avisos
                    MDSQLUIHelper.showWarnings(pantalla, ejecucion.getWarnings());
                }

                // Actualizar los scripts de las tablas y las repinta
                vigente = ((ScriptsTableModel) pantalla.getTblVigente().getModel()).getData();
                CollectionUtils.forAllDo(vigente, new UpdateScriptsClosure(ejecuciones));

                historico = ((ScriptsTableModel) pantalla.getTblHistorico().getModel()).getData();
                CollectionUtils.forAllDo(historico, new UpdateScriptsClosure(ejecuciones));

                /**
                 * Si hay scripts en estado Descuadrado o Error, hay que
                 * desmarcar los siguientes y deshabilitar el botón Aceptar
                 */
                scripts = new ArrayList<>(CollectionUtils.union(vigente, historico));
                Integer numeroOrden = hayErrores(scripts);
                if (numeroOrden > 0) {
                    desmarcar(vigente, numeroOrden);
                    desmarcar(historico, numeroOrden);
                    pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
                }

                // Actualizar los scripts en el proceso en sesión
                updateCurrentProcess(proceso, ejecuciones);

                pantalla.getTxtEstadoEjecucion().setText(proceso.getDescripcionEstadoProceso());

                // Actualizar la información de las tablas en pantalla
                refreshScriptTables();

                /**
                 * Ver si todos los scripts están ejecutados y el estado del
                 * proceso es Ejecutado para mostrar la pantalla de resumen del
                 * procesado
                 */
                if (isAllExecuted(proceso.getScripts())) {
                    proceso.setDescripcionEstadoProceso("Ejecutado");
                    session.setProceso(proceso);

                    cerraryEntregar(proceso);
                }
            }
        } catch (ServiceException e) {
            pantalla.setCursor(cursor);
            refreshScriptTables(); 
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void cerraryEntregar(Proceso proceso) {
        pantalla.getReturnParams().put("proceso", proceso);
        pantalla.getReturnParams().put("entregar", Boolean.TRUE);
        pantalla.getReturnParams().put("cmd", MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR);

        pantalla.dispose();
    }

// Actualizar la información de las tablas en pantalla    
    private void refreshScriptTables() {
        ((ScriptsTableModel) pantalla.getTblVigente().getModel()).fireTableDataChanged();
        ((ScriptsTableModel) pantalla.getTblHistorico().getModel()).fireTableDataChanged();
    }

    @Override
    public void onLoad() {

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        isReentrante = proceso.getTipo().equals(Procesado.REENTRANTE);

        if (isReentrante) {
            pantalla.getBtnDescartar().setEnabled(false);
            pantalla.getBtnReparar().setEnabled(false);
            pantalla.getBtnExcepcion().setEnabled(false);
        }

        pantalla.setProceso(proceso);
        pantalla.getTxtIdProcesado().setText(proceso.getIdProceso().toString());

        List<BBDD> bbdds = proceso.getBbdds();
        if (CollectionUtils.isNotEmpty(bbdds)) {
            BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(bbdds);
            pantalla.getCmbBBDD().setModel(modelBBDD);
        }

        // Obtiene los scripts
        List<Script> scripts = proceso.getScripts();
        if (hayErrores(scripts) > 0) {
            pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
        }

        // Actualiza las tablas, muestra todos los scripts que NO son historicos
        List<Script> vigentes = filterListScriptsFrom(scripts, MDSQLConstants.TIPOS_SCRIPT_HISTORICO, true);

        ScriptsTableModel tableModelVigente = (ScriptsTableModel) pantalla.getTblVigente().getModel();
        tableModelVigente.setData(vigentes);

        // Muestra sólo los scripts Historicos
        List<Script> historicos = filterListScriptsFrom(scripts, MDSQLConstants.TIPOS_SCRIPT_HISTORICO, false);

        ScriptsTableModel tableModelHistorico = (ScriptsTableModel) pantalla.getTblHistorico()
                .getModel();
        tableModelHistorico.setData(historicos);

        if (isAllExecuted(proceso.getScripts())) {
            // Disable Aceptar button
            pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
        }

        // También se deshabilita si el procesado está Rechazado, Error, Entregado
        if ("Rechazado".equals(proceso.getDescripcionEstadoProceso())
                || "Error".equals(proceso.getDescripcionEstadoProceso())
                || "Entregado".equals(proceso.getDescripcionEstadoProceso())) {
            // Disable Aceptar button
            pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
        }

        if (session.getProcesado().equals(Procesado.REENTRANTE)) {
            pantalla.getBtnDescartar().setEnabled(false);
            pantalla.getBtnReparar().setEnabled(false);
            pantalla.getBtnExcepcion().setEnabled(false);
            pantalla.getCmbBBDD().setEnabled(false);
            //pantalla.getTblVigente() -- No se puede cambiar el valor de la casilla
        }
    }

    /**
     * @param scripts
     * @param filtro
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Script> filterListScriptsFrom(List<Script> scripts, String[] filtro, boolean exclude) {
        List<Script> filteredList = new ArrayList<>(CollectionUtils.select(scripts, new ScriptPredicate(filtro, exclude)));
        // En principio estarán todos seleccionados
        CollectionUtils.forAllDo(filteredList, new ScriptSelectedClosure());

        return filteredList;
    }

    /**
     * @param ejecuciones
     */
    private void updateCurrentProcess(Proceso proceso, List<OutputRegistraEjecucion> ejecuciones) {
        List<Script> scripts = proceso.getScripts();
        CollectionUtils.forAllDo(scripts, new UpdateScriptsClosure(ejecuciones));

        if (CollectionUtils.isNotEmpty(ejecuciones)) {
            OutputRegistraEjecucion ejecucion = ejecuciones.get(0);
            proceso.setCodigoEstadoProceso(ejecucion.getCodigoEstadoProceso());
            proceso.setDescripcionEstadoProceso(ejecucion.getDescripcionEstadoProceso());
        }

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        session.setProceso(proceso);
    }

    /**
     * @param scripts
     * @return
     */
    private Boolean isAllExecuted(List<Script> scripts) {
        for (Script script : scripts) {
            if (!"Ejecutado".equals(script.getDescripcionEstadoScript())
                    && !"Excepción".equals(script.getDescripcionEstadoScript())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * @param scripts
     * @return
     */
    private Integer hayErrores(List<Script> scripts) {
        for (Script script : scripts) {
            if ("Error".equals(script.getDescripcionEstadoScript())
                    || "Descuadrado".equals(script.getDescripcionEstadoScript())) {
                return script.getNumeroOrden().intValue();
            }
        }
        return 0;
    }

    /**
     * @param scripts
     * @param numeroOrden
     */
    private void desmarcar(List<Script> scripts, Integer numeroOrden) {
        for (Script script : scripts) {
            Integer orden = script.getNumeroOrden().intValue();

            if (orden > numeroOrden) {
                script.setSelected(Boolean.FALSE);
            }
        }

    }
    
}
