package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ReentranteInfo;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.*;
import com.mdsql.ui.model.FicherosReentranteTableModel;
import com.mdsql.ui.model.FramePrincipalTypesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.UIHelper;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.LogWrapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author federico
 *
 */
@Slf4j
public class FramePrincipalActionListener extends ListenerSupport implements ActionListener, OnLoadListener, FocusListener {

    private final FramePrincipal framePrincipal;

    private DialogSupport pantallaEjecutar;
    private JTextArea currentTxtSQLArea;

    private static final int PANEL_SCRIPT = 0;
    private static final int PANEL_REENTRANTE = 3;

    private static final int SQL_SCRIPT = 1;
    private static final int SQL_CAMBIO = 2;
    private static final int SQL_CREACION = 3;
    private static final int SQL_COMENTARIO = 4;

    private static final int TAB_VIGENTE = 0;
    private static final int TAB_HISTORICO = 1;
    private static final int TAB_TYPES = 2;
    private static final int TAB_REENTRANTE = 3;
    private static final int TAB_MAXIMO = 4;

    /**
     * @param framePrincipal
     */
    public FramePrincipalActionListener(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JTextArea) {
            currentTxtSQLArea = (JTextArea) e.getSource();
            return;
        }

        JButton jButton = (JButton) e.getSource();

        if (null != jButton.getActionCommand()) {
            switch (jButton.getActionCommand()) {
                case MDSQLConstants.FRAME_PRINCIPAL_LOAD_SCRIPT:
                    evtLoadScript();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_LOAD_REENTRANTE:
                    evtLoadReentrante();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_COMENTARIO_REENTRANTE:
                    evtBtnComentariosReentrante();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_CARGAR_SCRIPT_OBJETOS:
                    evtLoadScriptObjects();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_PROCESAR_SCRIPT:
                    evtProcesarScript();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_SAVE:
                    evtGuardarScript();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_LIMPIAR_SCRIPT:
                    evtLimpiarScript();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_LIMPIAR_SESION:
                    evtLimpiarSesion();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_EXECUTE:
                    evtEjecutar();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_PROCESADO_CURSO:
                    evtProcesadoEnCurso();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_REFRESCAR_FICHERO:
                    evtRefrescarFichero();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_INFORMACION_MODELO:
                    evtInformacionModelo();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_ENTREGAR_PROCESADO:
                    evtEntregarScript();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_UNDO:
                    evtUndo();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_REDO:
                    evtRedo();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_CUT:
                    currentTxtSQLArea.cut();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_COPY:
                    currentTxtSQLArea.copy();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_PASTE:
                    currentTxtSQLArea.paste();
                    break;
                case MDSQLConstants.FRAME_PRINCIPAL_BTN_CERRAR:
                    evtBtnCerrarPeticion();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *
     */
    private void evtLimpiarSesion() {
        resetFramePrincipal();
        updateProcesadoEnCurso(MDSQLConstants.CMD_LIMPIAR_SESION);
    }

    /**
     *
     */
    private void evtInformacionModelo() {
        Map<String, Object> params = new HashMap<>();

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso)) {
            params.put("codigoProyecto", proceso.getModelo().getCodigoProyecto());

            /*DialogSupport informacionModelo = MDSQLUIHelper.createDialog(framePrincipal,
                    MDSQLConstants.CMD_INFORMACION_MODELO, params);
            MDSQLUIHelper.show(informacionModelo);*/
            MDSQLUIHelper.showForm(framePrincipal, PantallaInformacionModelo.class, params);
        } else {
            // Aviso de que no hay procesado en curso
            JOptionPane.showMessageDialog(framePrincipal, "No hay procesado en curso");
        }
    }

    /**
     *
     */
    private void evtRefrescarFichero() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();

            if (Objects.isNull(proceso)) {
                reloadFiles();
            } else {
                if ("Generado".equals(proceso.getDescripcionEstadoProceso())) {
                    Integer response = UIHelper.showConfirm(
                            "Tras aceptar, se borran de la pantalla los scripts recién procesados.", "Rechazar");

                    if (response == JOptionPane.YES_OPTION) {

                        rechazarProceso(proceso, "literalRechazoRefresco");
                        session.setProceso(null);
                        //Guardamos los ficheros cargados
                        ArrayList<File> fileList = framePrincipal.getCurrentFiles();
                        Procesado procesado = framePrincipal.getProcesado();
                        resetFramePrincipal();
                        framePrincipal.setCurrentFiles(fileList);
                        framePrincipal.setProcesado(procesado);
                        reloadFiles();
                    }
                }
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    private void evtEntregarScript() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso) && "Ejecutado".equals(proceso.getDescripcionEstadoProceso())) {
            Map<String, Object> params = new HashMap<>();

            params.put("idProceso", proceso.getIdProceso());
            params.put("entregar", Boolean.TRUE);

            /*PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
                    .createDialog(framePrincipal, MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
            MDSQLUIHelper.show(pantallaResumenProcesado);*/
            PantallaResumenProcesado pantallaResumenProcesado = MDSQLUIHelper.showForm(framePrincipal, PantallaResumenProcesado.class, params);

            String estado = (String) pantallaResumenProcesado.getReturnParams().get("estado");
            if ("Entregado".equals(estado)) {
                resetFramePrincipal();
            }

            updateProcesadoEnCurso(MDSQLConstants.CMD_ENTREGAR_SCRIPT);
        }
    }

    /**
     *
     */
    private void evtProcesadoEnCurso() {

        Map<String, Object> params = new HashMap<>();

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso)) {
            params.put("proceso", proceso);

            /*DialogSupport procesadoEnCurso = MDSQLUIHelper.createDialog(framePrincipal,
                    MDSQLConstants.CMD_PROCESADO_EN_CURSO, params);
            MDSQLUIHelper.show(procesadoEnCurso);*/
            MDSQLUIHelper.showForm(framePrincipal, PantallaProcesadoEnCurso.class, params);
        } else {
            // Aviso de que no hay procesado en curso
            JOptionPane.showMessageDialog(framePrincipal, "No hay procesado en curso");
        }

    }

    /**
     * Opción seleccionada: "Deshacer".
     *
     * Deshace el último cambio realizado en el documento actual.
     */
    private void evtUndo() {
        try {
            // deshace el último cambio realizado sobre el documento en el área de edición
            framePrincipal.getUndoManager().undo();
        } catch (CannotUndoException ex) { // en caso de que ocurra una excepción
            MDSQLUIHelper.showErrors(framePrincipal, ex);
        }

        // actualiza el estado de las opciones "Deshacer" y "Rehacer"
        framePrincipal.updateEditionControls();
    }

    /**
     * Opción seleccionada: "Rehacer".
     *
     * Rehace el último cambio realizado en el documento actual.
     */
    private void evtRedo() {
        try {
            // rehace el último cambio realizado sobre el documento en el área de edición
            framePrincipal.getUndoManager().redo();
        } catch (CannotRedoException e) { // en caso de que ocurra una excepción
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }

        // actualiza el estado de las opciones "Deshacer" y "Rehacer"
        framePrincipal.updateEditionControls();
    }

    /**
     *
     */
    private void evtGuardarScript() {
        if (!Objects.isNull(framePrincipal.getCurrentFile())) {
            if (confirmSave()) {
                actionSave();
            } else {
                // Aviso de que no se ha modificado
                JOptionPane.showMessageDialog(framePrincipal, "El script no se ha modificado");
            }
        }
    }

    /**
     *
     */
    private void evtLimpiarScript() {
        resetFramePrincipal();
        updateProcesadoEnCurso(MDSQLConstants.CMD_LIMPIAR_SCRIPT);
    }

    private void muestraPanelScript(int panel) {
        boolean estado = (panel == 0);

        //Desactivar pestañas, dejando activa la indicada
        for (int tabPage = 0; tabPage < TAB_MAXIMO; tabPage++) {
            framePrincipal.getTabPanel().setEnabledAt(tabPage, (tabPage == panel));
        }
        // Mostrar los paneles a la derecha
        framePrincipal.getPanelScript().setVisible(estado);
        framePrincipal.getPanelScriptReentrante().setVisible(!estado);
        framePrincipal.getTabPanel().setSelectedIndex(panel);
    }

    /**
     *
     */
    private void evtLoadScript() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();

            if (!Objects.isNull(proceso)) {
                // Aviso de que hay procesado en curso
                MDSQLUIHelper.showMessage(framePrincipal, "info.debe_finalizar_rechazar_procesado");
            } else {
                muestraPanelScript(PANEL_SCRIPT);

                if (confirmSave()) {
                    actionSave();
                }
                resetFramePrincipal();

                File file = loadScript();
                if (!Objects.isNull(file)) {
                    LogWrapper.debug(log, "Ruta del script: %s", file.getParent());
                    session.setRutaScript(file.getParent());
                    framePrincipal.setProcesado(Procesado.SCRIPT);
                    loadFileInFramePrincipal(SQL_SCRIPT, file);
                }
            }
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    private void evtLoadReentrante() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();

            if (!Objects.isNull(proceso)) {
                BigDecimal estado = proceso.getCodigoEstadoProceso();
                BigDecimal GENERADO = BigDecimal.valueOf(MDSQLConstants.EstadosProcesado.GENERADO.getIndex());

                if (!estado.equals(GENERADO)) {
                    MDSQLUIHelper.showMessage(framePrincipal, "info.debe_finalizar_rechazar_procesado");
                    return;
                }

                if (MDSQLUIHelper.confirmAction(framePrincipal, "Ya hay un procesado en estado Generado. ¿Desea rechazarlo?")) {
                    rechazarProceso(proceso, "literalRechazoCargaScript");
                } else {
                    return; // No desea rechazar el script
                }
            }

            if (confirmSave()) {
                actionSave();
            }

            String ruta = selectRutaInicial();
            if (ruta == null || ruta.isEmpty()) {
                return;
            }

            //Limpiamos los datos para inicializar la pantalla
            session.setReentranteInfo(null);
            //
            resetFramePrincipal();
            muestraPanelScript(PANEL_REENTRANTE);

            PantallaSeleccionScriptReentrante pantallaSeleccionScriptReentrante;
            pantallaSeleccionScriptReentrante
                    = MDSQLUIHelper.showForm(framePrincipal, PantallaSeleccionScriptReentrante.class,
                            null);
            String exitButton = (String) pantallaSeleccionScriptReentrante.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);

            if (exitButton == null || !MDSQLConstants.BTN_ACEPTAR.equals(exitButton)) {
                //No ha presionado Aceptar en la pantalla de seleccion de reentrantes, restablecemos los paneles
                muestraPanelScript(PANEL_SCRIPT);
                return;
            }
            // Ha presionado ACEPTAR
            framePrincipal.setProcesado(Procesado.REENTRANTE);

            muestraReentrantes();

        } catch (ServiceException e) {
            muestraPanelScript(PANEL_SCRIPT);
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    public void muestraReentrantes() {
        if (!framePrincipal.getCurrentFiles().isEmpty()) {
            framePrincipal.setCurrentFiles(new ArrayList());
        }
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        ReentranteInfo reentranteInfo = session.getReentranteInfo();

        String scriptCreacion = reentranteInfo.getScriptCreacion();
        String scriptComentarios = reentranteInfo.getScriptComentarios();
        String scriptCambio = reentranteInfo.getScriptCambio();

        loadReentranteInFramePrincipal(SQL_CAMBIO, scriptCambio); //Index 0
        loadReentranteInFramePrincipal(SQL_CREACION, scriptCreacion); //Index 1
        loadReentranteInFramePrincipal(SQL_COMENTARIO, scriptComentarios); //Index 2
    }

    /**
     *
     */
    private void evtLoadScriptObjects() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();

            if (!Objects.isNull(proceso)) {
                // Aviso de que no hay procesado en curso
                MDSQLUIHelper.showMessage(framePrincipal, "info.debe_finalizar_rechazar_procesado");
            } else {
                muestraPanelScript(PANEL_SCRIPT);

                if (confirmSave()) {
                    actionSave();
                }

                resetFramePrincipal();

                File file = loadScript();
                if (!Objects.isNull(file)) {
                    LogWrapper.debug(log, "Ruta del script: %s", file.getParent());
                    session.setRutaScript(file.getParent());
                    framePrincipal.setProcesado(Procesado.TYPE);
                    loadFileInFramePrincipal(SQL_SCRIPT, file);
                }
            }
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    private void loadReentranteInFramePrincipal(int tipoSQL, String fileName) {
        try {
            if (fileName.isEmpty()) {
                framePrincipal.getCurrentFiles().add(null);
            } else {
                File file = new File(fileName);
                framePrincipal.getCurrentFiles().add(file);
                setContent(tipoSQL);
            }
        } catch (IOException e) {
            log.error("ERROR: ", e);
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    private void loadFileInFramePrincipal(int tipoSQL, File file) {
        try {
            if (!Objects.isNull(file)) {
                framePrincipal.getCurrentFiles().add(file);
                setContent(tipoSQL);
            }
        } catch (IOException e) {
            log.error("ERROR: ", e);
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    private void updateProcesado(Procesado procesado) {
        // Marcamos inicialmente todas como inactivas
        JTabbedPane tabPanel = framePrincipal.getTabPanel();
        for (int page = 0; page < TAB_MAXIMO; page++) {
            tabPanel.setEnabledAt(page, Boolean.FALSE);
        }
        // Activamos en dependencia del procesado
        switch (procesado) {
            case SCRIPT:
                framePrincipal.getTabPanel().setEnabledAt(TAB_VIGENTE, Boolean.TRUE);
                framePrincipal.getTabPanel().setEnabledAt(TAB_HISTORICO, Boolean.TRUE);
                framePrincipal.getTabPanel().setSelectedIndex(TAB_VIGENTE);
                break;
            case TYPE:
                framePrincipal.getTabPanel().setEnabledAt(TAB_TYPES, Boolean.TRUE);
                framePrincipal.getTabPanel().setSelectedIndex(TAB_TYPES);
                break;
            case REENTRANTE:
                framePrincipal.getTabPanel().setEnabledAt(TAB_REENTRANTE, Boolean.TRUE);
                framePrincipal.getTabPanel().setSelectedIndex(TAB_REENTRANTE);
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    private void evtProcesarScript() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso)) {
            // Aviso de que hay procesado en curso
            MDSQLUIHelper.showMessage(framePrincipal, "info.debe_finalizar_rechazar_procesado");
        } else if (framePrincipal.getProcesado() == null) {
            MDSQLUIHelper.showMessage(framePrincipal, "info.debe_seleccionar_script");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("procesado", framePrincipal.getProcesado());
            // Update session
            session.setProcesado(framePrincipal.getProcesado());

            // Las líneas del script vienen directamente del text area
            params.put("script", MDSQLUIHelper.toTextoLineas(framePrincipal.getTxtSQLCode()));
            params.put("file", framePrincipal.getCurrentFile());

            /*pantallaProcesarScript = (PantallaProcesarScript) MDSQLUIHelper.createDialog(framePrincipal,
                    MDSQLConstants.CMD_PROCESAR_SCRIPT, params);
            MDSQLUIHelper.show(pantallaProcesarScript);*/
            PantallaProcesarScript pantallaProcesarScript = MDSQLUIHelper.showForm(framePrincipal, PantallaProcesarScript.class, params);

            proceso = (Proceso) pantallaProcesarScript.getReturnParams().get("proceso");

            if (!Objects.isNull(proceso)) {
                proceso.setRutaScript(session.getRutaScript());
                proceso.setTipo(framePrincipal.getProcesado());

                //session.setProceso(proceso); Ya se hace al salir de la pantalla
                switch (framePrincipal.getProcesado()) {
                    case SCRIPT:
                        List<Script> scripts = (List<Script>) pantallaProcesarScript.getReturnParams().get("scripts");
                        fillProcesadoScript(scripts);
                        break;
                    case TYPE:
                        List<Type> types = (List<Type>) pantallaProcesarScript.getReturnParams().get("types");
                        String nombreScriptLanza = (String) pantallaProcesarScript.getReturnParams()
                                .get("nombreScriptLanza");
                        List<TextoLinea> scriptLanza = (List<TextoLinea>) pantallaProcesarScript.getReturnParams()
                                .get("scriptLanza");
                        fillProcesadoType(proceso, types, nombreScriptLanza, scriptLanza);
                        break;
                    case REENTRANTE:
                        fillProcesadoReentrante(proceso);
                        break;
                    default:
                        break;
                }

                framePrincipal.getTxtSQLCode().setEditable(Boolean.FALSE);
                framePrincipal.getTxtSQLCode().setEnabled(Boolean.FALSE);

                updateProcesadoEnCurso(StringUtils.EMPTY);
                updateProcesado(framePrincipal.getProcesado());
            }
        }
    }

    /**
     *
     */
    private void evtEjecutar() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso) && framePrincipal.getProcesado() != null) {

            Map<String, Object> params = new HashMap<>();
            params.put("proceso", proceso);

            String item = StringUtils.EMPTY;
            switch (framePrincipal.getProcesado()) {
                case REENTRANTE:
                case SCRIPT:
                    item = MDSQLConstants.CMD_EJECUTAR_SCRIPT;
                    /*pantallaEjecutar = (PantallaEjecutarScripts) MDSQLUIHelper.createDialog(framePrincipal, item, params);
                    MDSQLUIHelper.show(pantallaEjecutar);*/
                    pantallaEjecutar = MDSQLUIHelper.showForm(framePrincipal, PantallaEjecutarScripts.class, params);
                    break;
                case TYPE:
                    item = MDSQLConstants.CMD_EJECUTAR_TYPE;
                    /*pantallaEjecutar = (PantallaEjecutarTypes) MDSQLUIHelper.createDialog(framePrincipal, item, params);
                    MDSQLUIHelper.show(pantallaEjecutar);*/
                    pantallaEjecutar = MDSQLUIHelper.showForm(framePrincipal, PantallaEjecutarTypes.class, params);
                    break;
                /*case REENTRANTE:
                    item = MDSQLConstants.CMD_EJECUTAR_SCRIPT; // Reentrante
                    pantallaEjecutar = (PantallaEjecutarScripts) MDSQLUIHelper.createDialog(framePrincipal, item, params);
                    MDSQLUIHelper.show(pantallaEjecutar);
                    break;*/
                default:
                    break;
            }

            String estado = (String) pantallaEjecutar.getReturnParams().get("estado");
            if ("RECHAZADO".equals(estado)) {
                resetFramePrincipal();
            } else {
                String cmd = (String) pantallaEjecutar.getReturnParams().get("cmd");
                if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR.equals(cmd)
                        || MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR.equals(cmd)) {
                    params = new HashMap<>();

                    params.put("idProceso", pantallaEjecutar.getReturnParams().get("idProceso"));
                    params.put("entregar", pantallaEjecutar.getReturnParams().get("entregar"));

                    item = MDSQLConstants.CMD_ENTREGAR_SCRIPT;
                    /*PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
                            .createDialog(framePrincipal, MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
                    MDSQLUIHelper.show(pantallaResumenProcesado);*/
                    PantallaResumenProcesado pantallaResumenProcesado = MDSQLUIHelper.showForm(framePrincipal, PantallaResumenProcesado.class, params);

                    estado = (String) pantallaResumenProcesado.getReturnParams().get("estado");
                    if ("Entregado".equals(estado)) {
                        resetFramePrincipal();
                    }
                }
            }
            updateProcesadoEnCurso(item);
        } else {
            // Aviso de que no hay procesado en curso
            JOptionPane.showMessageDialog(framePrincipal, "Es necesario procesar un script");
        }
    }

    /**
     * @param proceso
     */
    private void rechazarProceso(Proceso proceso, String literalRechazo) throws ServiceException {
        try {
            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            String txtRechazo = configuration.getConfig(literalRechazo);

            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            OutputWarning output = procesoService.rechazarProcesado(proceso.getIdProceso(), txtRechazo, session.getCodUsr());
            MDSQLUIHelper.showWarnings(framePrincipal, output.getWarnings());

            session.setProceso(null);
            //Guardamos los ficheros cargados
            ArrayList<File> fileList = framePrincipal.getCurrentFiles();
            Procesado procesado = framePrincipal.getProcesado();
            resetFramePrincipal();
            framePrincipal.setCurrentFiles(fileList);
            framePrincipal.setProcesado(procesado);
            reloadFiles();

        } catch (ServiceException | IOException e) {
            throw new ServiceException(e);
        }
    }

    private String selectRutaInicial() {
        /*DialogSupport dialog = MDSQLUIHelper.createDialog(framePrincipal, MDSQLConstants.CMD_LOAD_SCRIPT);
        MDSQLUIHelper.show(dialog);*/
        PantallaBuscadorFicheros dialog = MDSQLUIHelper.showForm(framePrincipal, PantallaBuscadorFicheros.class, null);
        String rutaInicial = (String) dialog.getReturnParams().get("RutaInicial");
        if (rutaInicial != null) {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            session.setSelectedRoute(rutaInicial);
        }
        return rutaInicial;
    }

    /**
     * @return
     */
    private File loadScript() throws IOException {
        File file = null;
        String rutaInicial = selectRutaInicial();

        if (StringUtils.isNotBlank(rutaInicial)) {
            file = selectFile(rutaInicial);
        }

        return file;
    }

    private void setContent(int tipoSQL) throws IOException {
        JInternalFrame frmScript;
        JTextArea txtScript;
        int indexFile;

        switch (tipoSQL) {
            case SQL_CAMBIO:
                frmScript = framePrincipal.getFrmSQLCambio();
                txtScript = framePrincipal.getTxtSQLCambio();
                indexFile = 0;
                break;
            case SQL_CREACION:
                frmScript = framePrincipal.getFrmSQLCreacion();
                txtScript = framePrincipal.getTxtSQLCreacion();
                indexFile = 1;
                break;
            case SQL_COMENTARIO:
                frmScript = framePrincipal.getFrmSQLComentarios();
                txtScript = framePrincipal.getTxtSQLComentarios();
                indexFile = 2;
                break;
            default:
                frmScript = framePrincipal.getFrmSQLScript();
                txtScript = framePrincipal.getTxtSQLCode();
                indexFile = 0;
        }
        if (framePrincipal.getCurrentFiles() != null
                && framePrincipal.getCurrentFiles().size() - 1 >= indexFile
                && framePrincipal.getCurrentFiles().get(indexFile) != null) {

            File file = framePrincipal.getCurrentFiles().get(indexFile);
            txtScript.setText("");
            MDSQLAppHelper.dumpContentToText(file, txtScript);

            // txtScript.getDocument().addUndoableEditListener(framePrincipal.getEditorEventHandler());
            framePrincipal.getUndoManager().die(); // se limpia el buffer del administrador de edición
            framePrincipal.updateEditionControls(); // se actualiza el estado de las
            // opciones "Deshacer" y "Rehacer"

            frmScript.setTitle(file.getName());
            framePrincipal.setHasChanged(Boolean.FALSE);

            txtScript.setEditable(Boolean.TRUE);
            txtScript.setEnabled(Boolean.TRUE);
        }
    }

    private void reloadFiles() {
        try {
            if (Procesado.REENTRANTE.equals(framePrincipal.getProcesado())) {
                setContent(SQL_CAMBIO);
                setContent(SQL_CREACION);
                setContent(SQL_COMENTARIO);
            } else {
                setContent(SQL_SCRIPT);
            }
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(framePrincipal, e);
        }
    }

    /**
     * @param rutaInicial
     * @return
     */
    private File selectFile(String rutaInicial) throws IOException {
        File file = null;

        JFileChooser chooser = MDSQLUIHelper.getJFileChooser(rutaInicial);
        if (chooser.showOpenDialog(framePrincipal) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            LogWrapper.debug(log, "Archivo seleccionado: %s", file.getAbsolutePath());
            String ruta = file.getParent();

            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            session.setSelectedRoute(ruta);
            LogWrapper.debug(log, "Ruta global: %s", session.getSelectedRoute());
        }

        return file;
    }

    /**
     * @return
     */
    private Boolean confirmSave() {
        Boolean hasChanged = framePrincipal.getHasChanged();
        if (!Objects.isNull(hasChanged) && Boolean.TRUE.equals(hasChanged)) { // si el documento esta marcado como
            // modificado
            // le ofrece al usuario guardar los cambios
            int option = JOptionPane.showConfirmDialog(framePrincipal, "¿Desea guardar los cambios?");

            switch (option) {
                case JOptionPane.YES_OPTION: // si elige que si
                    return Boolean.TRUE; // guarda el archivo
                case JOptionPane.CANCEL_OPTION: // si elige cancelar
                    return Boolean.FALSE; // cancela esta operación
                // en otro caso se continúa con la operación y no se guarda el documento actual
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Opción seleccionada: "Guardar".
     *
     * Guarda el documento actual en el archivo asociado actualmente.
     */
    private void actionSave() {
        if (Objects.isNull(framePrincipal.getCurrentFile())) { // si no hay un archivo asociado al documento actual
            actionSaveAs(); // invoca el método actionSaveAs()
        } else if (framePrincipal.getHasChanged()) { // si el documento esta marcado como modificado
            try {
                if (Procesado.SCRIPT.equals(framePrincipal.getProcesado())
                        || Procesado.TYPE.equals(framePrincipal.getProcesado())) {

                    /* MDSQLAppHelper.dumpTextToFile(framePrincipal.getTxtSQLCode(), framePrincipal.getCurrentFile());
                    // Le pone el nombre del archivo al título del editor
                    framePrincipal.getFrmSQLScript().setTitle(framePrincipal.getCurrentFile().getName());*/
                    saveFile(SQL_SCRIPT);
                } else { // Reentrante
                    saveFile(SQL_CAMBIO);
                    saveFile(SQL_CREACION);
                    saveFile(SQL_COMENTARIO);
                }
                // marca el estado del documento como no modificado
                framePrincipal.setHasChanged(Boolean.FALSE);

                framePrincipal.getUndoManager().die(); // se limpia el buffer del administrador de edición
                framePrincipal.updateEditionControls(); // se actualiza el estado de las
            } catch (IOException e) { // en caso de que ocurra una excepción
                MDSQLUIHelper.showErrors(framePrincipal, e);
            }
        }
    }

    private void saveFile(int tipoSQL) throws IOException {
        JInternalFrame frmScript;
        JTextArea txtScript;
        int indexFile;

        switch (tipoSQL) {
            case SQL_CAMBIO:
                frmScript = framePrincipal.getFrmSQLCambio();
                txtScript = framePrincipal.getTxtSQLCambio();
                indexFile = 0;
                break;
            case SQL_CREACION:
                frmScript = framePrincipal.getFrmSQLCreacion();
                txtScript = framePrincipal.getTxtSQLCreacion();
                indexFile = 1;
                break;
            case SQL_COMENTARIO:
                frmScript = framePrincipal.getFrmSQLComentarios();
                txtScript = framePrincipal.getTxtSQLComentarios();
                indexFile = 2;
                break;
            default:
                frmScript = framePrincipal.getFrmSQLScript();
                txtScript = framePrincipal.getTxtSQLCode();
                indexFile = 0;
        }
        if (framePrincipal.getCurrentFiles() != null
                && framePrincipal.getCurrentFiles().size() - 1 >= indexFile) {
            MDSQLAppHelper.dumpTextToFile(txtScript, framePrincipal.getCurrentFiles().get(indexFile));
            // Le pone el nombre del archivo al título del editor
            frmScript.setTitle(framePrincipal.getCurrentFiles().get(indexFile).getName());
        }
    }

    /**
     * Opción seleccionada: "Guardar como".
     *
     * Le permite al usuario elegir la ubicación donde se guardará el documento
     * actual.
     */
    private void actionSaveAs() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String rutaInicial = session.getSelectedRoute();

            JFileChooser fc = MDSQLUIHelper.getJFileChooser(rutaInicial); // obtiene un JFileChooser

            // presenta un dialogo modal para que el usuario seleccione un archivo
            int state = fc.showSaveDialog(framePrincipal);
            if (state == JFileChooser.APPROVE_OPTION) { // si elige guardar en el archivo
                File file = fc.getSelectedFile(); // obtiene el archivo seleccionado

                MDSQLAppHelper.dumpTextToFile(framePrincipal.getTxtSQLCode(), file);

                // nuevo título de la ventana con el nombre del archivo guardado
                framePrincipal.getFrmSQLScript().setTitle(file.getName());

                // establece el archivo guardado como el archivo actual
                framePrincipal.setCurrentFile(file);
                // marca el estado del documento como no modificado
                framePrincipal.setHasChanged(Boolean.FALSE);
            }
        } catch (IOException e) { // en caso de que ocurra una excepción
            MDSQLUIHelper.showErrors(framePrincipal, e);

        }
    }

    /**
     *
     */
    private void resetFramePrincipal() {
        framePrincipal.setCurrentFiles(new ArrayList());
        framePrincipal.setProcesado(null);
        /// limpia el contenido del area de edición
        framePrincipal.getTxtSQLCode().setText(StringUtils.EMPTY);
        framePrincipal.getFrmSQLScript().setTitle(StringUtils.EMPTY);
        framePrincipal.getTxtSQLCreacion().setText(StringUtils.EMPTY);
        framePrincipal.getFrmSQLCreacion().setTitle(StringUtils.EMPTY);
        framePrincipal.getTxtSQLCambio().setText(StringUtils.EMPTY);
        framePrincipal.getFrmSQLCambio().setTitle(StringUtils.EMPTY);
        framePrincipal.getTxtSQLComentarios().setText(StringUtils.EMPTY);
        framePrincipal.getFrmSQLComentarios().setTitle(StringUtils.EMPTY);

        framePrincipal.getUndoManager().die(); // limpia el buffer del administrador de edición
        framePrincipal.updateEditionControls(); // actualiza el estado de las opciones "Deshacer" y "Rehacer"

        // marca el estado del documento como no modificado
        framePrincipal.setHasChanged(Boolean.FALSE);

        framePrincipal.disableEditionButtons();
        framePrincipal.disableTabs();
        framePrincipal.resetFrames();

        // Volver a la pestaña Vigente
        framePrincipal.getTabPanel().setSelectedIndex(0);
    }

    /**
     *
     */
    private void updateProcesadoEnCurso(String cmd) {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (MDSQLConstants.CMD_EJECUTAR_SCRIPT.equals(cmd) || MDSQLConstants.CMD_EJECUTAR_TYPE.equals(cmd)) {
            Proceso p = (Proceso) pantallaEjecutar.getReturnParams().get("proceso");

            if ((!Objects.isNull(p))) {
                if ("Rechazado".equals(p.getDescripcionEstadoProceso())) {
                    proceso = null;
                } else {
                    proceso = p;
                }
            }
        }

        if (proceso != null && MDSQLConstants.CMD_ENTREGAR_SCRIPT.equals(cmd)
                && "Entregado".equals(proceso.getDescripcionEstadoProceso())) {
            proceso = null;
        }

        if (MDSQLConstants.CMD_LIMPIAR_SESION.equals(cmd)) {
            proceso = null;
        }

        updateButtons(proceso);

        session.setProceso(proceso);
        if (proceso == null) {
            session.setReentranteInfo(null);
        }

        // Save session to disk
//		MDSQLAppHelper.serializeToDisk(session, MDSQLConstants.SESSION);
    }

    private void updateButtons(Proceso proceso) {

        if (!Objects.isNull(proceso)) {
            if (null != proceso.getDescripcionEstadoProceso()) {
                switch (proceso.getDescripcionEstadoProceso()) {
                    case "Generado":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnLimpiarScripts().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.TRUE);
                        break;
                    case "En Ejecucion":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.FALSE);
                        break;
                    case "Error":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.FALSE);
                        break;
                    case "Rechazado":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.TRUE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.TRUE);
                        break;
                    case "Ejecutado":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.TRUE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.TRUE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.FALSE);
                        break;
                    case "Entregado":
                        framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.TRUE);
                        framePrincipal.getBtnLimpiarScripts().setEnabled(Boolean.TRUE);
                        framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.FALSE);
                        framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.TRUE);
                        break;
                    default:
                        break;
                }
            }
        } else {
            framePrincipal.getBtnLimpiarSesion().setEnabled(Boolean.TRUE);
            framePrincipal.getBtnLimpiarScripts().setEnabled(Boolean.TRUE);
            framePrincipal.getBtnEntregarProcesado().setEnabled(Boolean.TRUE);
            framePrincipal.getBtnRefrescarFichero().setEnabled(Boolean.TRUE);
        }
    }

    private void fillProcesadoReentrante(Proceso proceso) {
        // Obtiene el modelo y lo actualiza
        FicherosReentranteTableModel tableModel = (FicherosReentranteTableModel) framePrincipal.getTblFicherosReentrante().getModel();
        tableModel.setData(proceso.getScripts());
        tableModel.fireTableDataChanged();

        /*framePrincipal.getTabPanel().setEnabledAt(TAB_VIGENTE, Boolean.FALSE);
        framePrincipal.getTabPanel().setEnabledAt(TAB_HISTORICO, Boolean.FALSE);
        framePrincipal.getTabPanel().setEnabledAt(TAB_TYPES, Boolean.TRUE);
         */
        // Seleccionar el primero
        framePrincipal.getTblFicherosReentrante().setRowSelectionInterval(0, 0);
    }

    /**
     * @param types
     */
    private void fillProcesadoType(Proceso proceso, List<Type> types, String nombreScriptLanza,
            List<TextoLinea> scriptLanza) {
        // Obtiene el modelo y lo actualiza
        FramePrincipalTypesTableModel tableModel = (FramePrincipalTypesTableModel) framePrincipal.getTblListaObjetos()
                .getModel();
        tableModel.setData(types);
        tableModel.fireTableDataChanged();

        // Mostrar el script lanza
        framePrincipal.getIfrmLanzador().setTitle(nombreScriptLanza);
        MDSQLAppHelper.dumpContentToText(scriptLanza, framePrincipal.getTxtScriptLanza());

        Script script = MDSQLAppHelper.createScript(nombreScriptLanza, scriptLanza);
        proceso.setScriptLanza(script);

        framePrincipal.getTabPanel().setEnabledAt(TAB_VIGENTE, Boolean.FALSE);
        framePrincipal.getTabPanel().setEnabledAt(TAB_HISTORICO, Boolean.FALSE);
        framePrincipal.getTabPanel().setEnabledAt(TAB_TYPES, Boolean.TRUE);

        //framePrincipal.getTblListaObjetos().forceRepaintColumn(0);
        //framePrincipal.getTblListaObjetos().repaint();
        // Seleccionar el primero
        framePrincipal.getTblListaObjetos().setRowSelectionInterval(0, 0);
    }

    /**
     * @param scripts
     */
    private void fillProcesadoScript(List<Script> scripts) {
        if (CollectionUtils.isNotEmpty(scripts)) {
            MDSQLUIHelper.putScriptsOn(framePrincipal, scripts);
            framePrincipal.getTabPanel().setEnabledAt(TAB_VIGENTE, Boolean.TRUE);
            framePrincipal.getTabPanel().setEnabledAt(TAB_HISTORICO, Boolean.TRUE);
            framePrincipal.getTabPanel().setEnabledAt(TAB_TYPES, Boolean.FALSE);
        }
    }

    @Override
    public void onLoad() {
        framePrincipal.disableTabs();
        muestraBtnEdicion();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextArea) {
            currentTxtSQLArea = (JTextArea) e.getSource();
            // Comprobamos si es uno de los editores de script
            if (currentTxtSQLArea != framePrincipal.getTxtSQLCambio()
                    && currentTxtSQLArea != framePrincipal.getTxtSQLCreacion()
                    && currentTxtSQLArea != framePrincipal.getTxtSQLComentarios()
                    && currentTxtSQLArea != framePrincipal.getTxtSQLCode()) {
                currentTxtSQLArea = null;
            }
            muestraBtnEdicion();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextArea) {
            currentTxtSQLArea = null;
            muestraBtnEdicion();
        }
    }

    private void muestraBtnEdicion() {
        Boolean visible = (currentTxtSQLArea != null);
        framePrincipal.getBtnUndo().setVisible(visible);
        framePrincipal.getBtnRedo().setVisible(visible);
        framePrincipal.getBtnCut().setVisible(visible);
        framePrincipal.getBtnCopy().setVisible(visible);
        framePrincipal.getBtnPaste().setVisible(visible);
    }

    private void evtBtnCerrarPeticion() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();

        if (!Objects.isNull(proceso)) {
            // Aviso de que hay procesado en curso
            MDSQLUIHelper.showMessage(framePrincipal, "info.debe_finalizar_rechazar_procesado");
            return;
        }
        MDSQLUIHelper.showForm(framePrincipal, PantallaCerrarPeticion.class, null);
    }

    private void evtBtnComentariosReentrante() {
        MDSQLUIHelper.showForm(framePrincipal, PantallaScriptComentarios.class, null);
    }
}
