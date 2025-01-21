package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.InputReparaScript;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputReparaScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.ScriptOld;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.EjecucionService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.PantallaRepararScript;
import com.mdsql.ui.PantallaSeleccionHistorico;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.UIHelper;
import com.mdval.utils.AppHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

public class PantallaRepararScriptListener extends ListenerSupport implements ActionListener {

    private final PantallaRepararScript pantalla;

    private File archivoReprocesado;

    private File archivoReparacion;

    public PantallaRepararScriptListener(PantallaRepararScript pantallaRepararScript) {
        super();
        this.pantalla = pantallaRepararScript;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton jButton = (AbstractButton) e.getSource();

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
            aceptar();
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.dispose();
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_REPROCESAR_SCRIPT.equals(jButton.getActionCommand())) {
            enablePreprocesarScript(Boolean.TRUE);
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_NO_REPROCESAR_SCRIPT.equals(jButton.getActionCommand())) {
            enablePreprocesarScript(Boolean.FALSE);
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_PROCESADO.equals(jButton.getActionCommand())) {
            enableScriptReparacion(Boolean.FALSE);
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_REPARACION.equals(jButton.getActionCommand())) {
            enableScriptReparacion(Boolean.TRUE);
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO.equals(jButton.getActionCommand())) {
            abrirScript();
        }

        if (MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO_REPARACION.equals(jButton.getActionCommand())) {
            abrirScriptReparacion();
        }
    }

    /**
     *
     */
    private void abrirScript() {
        archivoReprocesado = MDSQLUIHelper.abrirScript(pantalla.getFrameParent(), pantalla.getTxtScript());
    }

    /**
     *
     */
    private void abrirScriptReparacion() {
        archivoReparacion = MDSQLUIHelper.abrirScript(pantalla.getFrameParent(), pantalla.getTxtScriptReparacion());
    }

    /**
     * @param value
     */
    private void enableScriptReparacion(Boolean value) {
        pantalla.getBtnAbrirFicheroReparacion().setEnabled(value);
        pantalla.getTxtScriptReparacion().setEnabled(value);
    }

    /**
     * @param value
     */
    private void enablePreprocesarScript(Boolean value) {
        pantalla.getBtnAbrirFichero().setEnabled(value);
        pantalla.getTxtScript().setEnabled(value);
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    private void aceptar() {
        try {
            Integer response = UIHelper.showConfirm(literales.getLiteral("confirmacion.mensaje"),
                    literales.getLiteral("confirmacion.titulo"));

            if (response == JOptionPane.YES_OPTION) {

                Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
                Modelo modelo = proceso.getModelo();

                Script script = (Script) pantalla.getParams().get("script");

                // El modelo tiene histórico
                String tieneHistorico = modelo.getMcaHis();
                if (MDSQLConstants.S.equals(tieneHistorico)) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("codigoProyecto", modelo.getCodigoProyecto());

                    // Obtiene las líneas del script y se las pasa al selector de histórico en un parámetro
                    params.put("script", script.getLineasScript());

                    /*PantallaSeleccionHistorico pantallaSeleccionHistorico = (PantallaSeleccionHistorico) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
                            MDSQLConstants.CMD_SELECCION_HISTORICO, params);
                    MDSQLUIHelper.show(pantallaSeleccionHistorico);*/
                    PantallaSeleccionHistorico pantallaSeleccionHistorico = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionHistorico.class, params);

                    Boolean continuarProcesado = (Boolean) pantallaSeleccionHistorico.getReturnParams().get("procesado");
                    if (continuarProcesado) {
                        List<SeleccionHistorico> objetosHistorico = (List<SeleccionHistorico>) pantallaSeleccionHistorico.getReturnParams().get("objetosHistorico");
                        repararScript(proceso, script, objetosHistorico);
                    } else {
                        MDSQLUIHelper.showMessage(pantalla, "info.operacion.cancelada");
                    }
                } else {
                    repararScript(proceso, script, null);
                }
            }

        } catch (ServiceException | IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param proceso
     * @param script
     * @throws ServiceException
     */
    private void repararScript(Proceso proceso, Script script, List<SeleccionHistorico> objetosHistorico) throws ServiceException, IOException {
        ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
        BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);
        EjecucionService ejecucionService = (EjecucionService) getService(MDSQLConstants.EJECUCION_SERVICE);

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String selectedRoute = session.getSelectedRoute();
        String ruta = selectedRoute.concat(File.separator);
        String codigoUsuario = session.getCodUsr();

        InputReparaScript inputReparaScript = new InputReparaScript();
        inputReparaScript.setNumeroOrden(script.getNumeroOrden());
        inputReparaScript.setIdProceso(proceso.getIdProceso());
        inputReparaScript.setCodigoUsuario(codigoUsuario);

        String nombreBBDD = proceso.getModelo().getNombreBbdd();
        inputReparaScript.setNombreBBDD(nombreBBDD);
        String nombreEsquema = proceso.getModelo().getNombreEsquema();
        inputReparaScript.setNombreEsquema(nombreEsquema);
        String mcaHis = proceso.getModelo().getMcaHis();
        inputReparaScript.setPMcaHis(mcaHis);
        String nombreBBDDHis = proceso.getBbdd().getNombreBBDDHis();
        inputReparaScript.setNombreBBDDHis(nombreBBDDHis);
        String nombreEsquemaHis = proceso.getBbdd().getNombreEsquemaHis();
        inputReparaScript.setNombreEsquemaHis(nombreEsquemaHis);

        Boolean reprocesa = pantalla.getRbtnReprocesar().isSelected();
        Boolean mismoScript = pantalla.getRbtnEjecutarScriptProcesado().isSelected();

        String mcaMismoScript = AppHelper.normalizeValueToCheck(mismoScript);
        inputReparaScript.setMcaMismoScript(mcaMismoScript);

        String mcaReprocesa = AppHelper.normalizeValueToCheck(reprocesa);
        inputReparaScript.setMcaReprocesa(mcaReprocesa);

        if (reprocesa) {
            // Leer el script y pasarlo a líneas
            List<TextoLinea> lineasScriptReprocesar = MDSQLUIHelper.toTextoLineas(archivoReprocesado, MDSQLConstants.DEFAULT_CHARSET);
            inputReparaScript.setNombreScriptNew(archivoReprocesado.getName());
            inputReparaScript.setTxtRutaNew(session.getRutaScript());
            inputReparaScript.setScriptNew(lineasScriptReprocesar);

            if (!mismoScript) {
                List<TextoLinea> lineasScriptParche = MDSQLUIHelper.toTextoLineas(archivoReparacion, MDSQLConstants.DEFAULT_CHARSET);
                inputReparaScript.setNombreScriptParche(archivoReparacion.getName());
                inputReparaScript.setTxtRutaParche(session.getRutaScript());
                inputReparaScript.setScriptParche(lineasScriptParche);
            }
        }

        inputReparaScript.setListaObjetoHis(objetosHistorico);

        String txtComentario = pantalla.getTxtComentario().getText();
        inputReparaScript.setTxtComentario(txtComentario);

        OutputReparaScript repararScript = scriptService.repararScript(inputReparaScript);

        if (reprocesa) {
            renombrarFicheros(proceso, repararScript);
            cargarScripts(repararScript);

            if (!mismoScript) {
                // Mostrar pantalla Ajustar log ejecución
                eventBtnVerLog(proceso, script);

                // Se ejecuta el script de reparación
                scriptService.ejecutarRepararScript(script, reprocesa, mismoScript, repararScript);
            } else {
                // Generar el lanza y lo ejecuta
                if (repararScript.getScriptLanza() != null && !repararScript.getScriptLanza().isEmpty()) {
                    String lanzaFile = ruta.concat(repararScript.getNombreScriptLanza());
                    MDSQLAppHelper.dumpLinesToFile(script.getLineasScriptLanza(), lanzaFile);

                    String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema);

                    // Ejecución del script
                    scriptService.executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

                    // Obtener el log asociado al lanza
                    String logFile = ruta.concat(script.getNombreScriptLog());
                    List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

                    // Registra la ejecución
                    OutputRegistraEjecucion outputRegistraEjecucion = ejecucionService.registraEjecucion(
                            proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList);

                    MDSQLUIHelper.showWarnings(pantalla, outputRegistraEjecucion.getWarnings());

                } else { // O generar los ficheros
                    for (Script scr : repararScript.getListaScript()) {
                        MDSQLAppHelper.dumpLinesToFile(scr.getLineasScript(), ruta.concat(scr.getNombreScript()));
                    }
                }

            }

            List<Script> scripts = repararScript.getListaScript();
            pantalla.getReturnParams().put("scripts", scripts);
            pantalla.dispose();
        } else {
            if (!mismoScript) {
                // Mostrar pantalla Ajustar log ejecución
                eventBtnVerLog(proceso, script);

                // Se ejecuta el script de reparación
                scriptService.ejecutarRepararScript(script, reprocesa, mismoScript, repararScript);
            } else {
                scriptService.executeScripts(proceso.getBbdd(), repararScript.getListaScript(), null);
            }
        }
    }

    private void eventBtnVerLog(Proceso proceso, Script script) {
        Map<String, Object> params = new HashMap<>();

        params.put("script", script);
        params.put("proceso", proceso);

        if ("Ejecutado".equals(script.getDescripcionEstadoScript())) {
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

    /**
     * @param repararScript
     */
    private void renombrarFicheros(Proceso proceso, OutputReparaScript repararScript) {
        String ruta = proceso.getRutaTrabajo();

        for (ScriptOld scriptOld : repararScript.getListaScriptOld()) {
            File f = new File(ruta.concat(scriptOld.getNombreScriptOld()));
            MDSQLAppHelper.renombrarArchivo(f, ruta.concat(scriptOld.getNombreScriptNew()));
        }
    }

    private void cargarScripts(OutputReparaScript repararScript) {
        FramePrincipal framePrincipal = (FramePrincipal) pantalla.getFrameParent();
        MDSQLUIHelper.putScriptsOn(framePrincipal, repararScript.getListaScript());
    }
}
