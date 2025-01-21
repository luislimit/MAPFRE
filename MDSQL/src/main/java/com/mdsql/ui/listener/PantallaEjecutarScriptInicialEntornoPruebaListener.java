package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputProcesa;
import com.mdsql.bussiness.entities.OutputProcesaScriptInicial;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.EntornosPruebaService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.FramePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mdsql.ui.PantallaEjecutarScriptInicialEntornoPrueba;
import com.mdsql.ui.model.EntornoPruebaComboBoxModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaEjecutarScriptInicialEntornoPruebaListener extends ListenerSupportModelo implements ActionListener, OnLoadListener {

    protected final PantallaEjecutarScriptInicialEntornoPrueba pantalla;

    public PantallaEjecutarScriptInicialEntornoPruebaListener(PantallaEjecutarScriptInicialEntornoPrueba pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == pantalla.getBtnScript()) {
            eventBtnScript();
        } else if (obj == pantalla.getBtnProcesar()) {
            eventBtnProcesar();
        } else if (obj == pantalla.getBtnEjecutar()) {
            eventBtnEjecutar();
        } else if (obj == pantalla.getBtnCancelar()) {
            eventBtnCancelar();
        } else {
            super.actionPerformed(e);
        }
    }

    private void eventBtnScript() {
        MDSQLUIHelper.abrirScript(pantalla, pantalla.getTxtScript());
        habilitaBtnProcesar();
    }

    private void eventBtnProcesar() {
        try {
            rechazarProcesado();
            //
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            //
            String proyecto = pantalla.getTxtModeloProyecto().getText();
            SubProyecto subProyecto;
            if (pantalla.getCmbSubModelo().getSelectedItem() != null) {
                subProyecto = ((SubProyecto) pantalla.getCmbSubModelo().getSelectedItem());
            } else {
                subProyecto = new SubProyecto();
            }
            String peticion = pantalla.getTxtPeticion().getText();
            String demanda = pantalla.getTxtDemanda().getText();
            String codUsr = session.getCodUsr();
            String codUsrPeticion = pantalla.getTxtSolicitada().getText();
            // Información del fichero
            String fullScriptName = pantalla.getTxtScript().getText();
            File file = new File(fullScriptName);
            String ruta = file.getParent();
            String scriptName = file.getName();
            String rutaConSeparator = ruta.concat(File.separator);
            session.setSelectedRoute(rutaConSeparator);

            EntornosPruebaService service = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);
            List<TextoLinea> script = MDSQLAppHelper.writeFileToLines(file);
            //
            String nombreEntorno = null;
            if (pantalla.getCmbEntornoPrueba().getSelectedIndex() != -1) {
                nombreEntorno = ((EntornoPrueba) pantalla.getCmbEntornoPrueba().getSelectedItem()).getNombreEntorno();
            }
            String mcaDrop = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkGenerarDROP().isSelected());

            OutputProcesaScriptInicial output = service.procesaScriptInicial(script,
                    proyecto,
                    subProyecto.getCodigoSubProyecto(),
                    peticion,
                    demanda,
                    codUsr,
                    codUsrPeticion,
                    scriptName,
                    ruta,
                    nombreEntorno,
                    mcaDrop);

            Script lanza = Script.builder().
                    nombreScript(output.getNombreScriptLanza()).
                    lineasScript(output.getScriptLanza()).
                    build();

            Proceso proceso = Proceso.builder().
                    idProceso(output.getIdProceso()).
                    modelo(pantalla.getModelo()).
                    codProyecto(proyecto).
                    codSubproyecto(subProyecto.getCodigoSubProyecto()).
                    subproyecto(subProyecto).
                    rutaTrabajo(rutaConSeparator).
                    rutaScript(rutaConSeparator).
                    codigoPeticion(peticion).
                    codigoDemanda(demanda).
                    codigoUsr(codUsr).
                    codigoUsrPeticion(codUsrPeticion).
                    scripts(output.getListaScripts()).
                    scriptLanza(lanza).
                    ficheroLog(output.getNombreScriptLog()).
                    codigoEstadoProceso(output.getCodigoEstadoProceso()).
                    descripcionEstadoProceso(output.getDescripcionEstadoProceso()).
                    fechaInicio(output.getFechaProceso()).build();

            session.setProceso(proceso);
            //Indicamos que el script es de tipo SCRIPT por si cancela la entrega lo pueda rechazar
            FramePrincipal framePrincipal = ((FramePrincipal) pantalla.getFrameParent());
            framePrincipal.setProcesado(MDSQLConstants.Procesado.SCRIPT);

            pantalla.getBtnEjecutar().setEnabled(true);
            //Creamos los scripts en la ruta por defecto
            for (Script s : output.getListaScripts()) {
                MDSQLAppHelper.dumpLinesToFile(s.getLineasScript(), rutaConSeparator.concat(s.getNombreScript()));
            }
            //Creamos el fichero Lanza
            MDSQLAppHelper.dumpLinesToFile(output.getScriptLanza(), rutaConSeparator.concat(output.getNombreScriptLanza()));
            //
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        } catch (IOException | ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void eventBtnEjecutar() {
        if (pantalla.getCmbEntornoPrueba().getSelectedIndex() == -1
                || (!MDSQLUIHelper.confirmAction(pantalla.getFrameParent(), "confirmacion.mensaje"))) {
            return;
        }
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();

            ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
            BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);
            EntornosPruebaService entPruService = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);

            //bbdd.setPassword(password);
            String ruta = proceso.getRutaScript();
            String lanzaFile = ruta.concat(proceso.getScriptLanza().getNombreScript());
            String nombreLog = ruta.concat(proceso.getFicheroLog());
            BigDecimal idProceso = proceso.getIdProceso();
            String codUsr = proceso.getCodigoUsr();

            EntornoPrueba entornoPrueba = (EntornoPrueba) pantalla.getCmbEntornoPrueba().getSelectedItem();
            String nombreBBDD = entornoPrueba.getBbdd();
            String nombreEsquema = entornoPrueba.getEsquema();
            // Esto es para cada una de las BBDD
            String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema);
            // Ejecución del script
            scriptService.executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

            // Obtiene el fichero log (se indica en el Spool del lanza)
            List<TextoLinea> logLines = MDSQLAppHelper.writeFileToLines(new File(nombreLog));

            // Enviar el fichero de Log para obtener el estado del procesado
            OutputProcesa output = entPruService.registraScriptInicial(idProceso, codUsr, logLines);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            // Verificar si el estado es rechazado
            if (MDSQLConstants.EstadosProcesado.RECHAZADO.getName().equals(output.getDescripcionEstadoProceso())) {
                // Limpiar la sesion y renombrar/eliminar ficheros
                session.setProceso(null);
                rechazarScripts(proceso);
                pantalla.getTxtScript().setText("");
                pantalla.getBtnEjecutar().setEnabled(false);
                return;
            }
            // Verificar el estado de la ejecución, sólo para pruebas
            if (!MDSQLConstants.EstadosProcesado.EJECUTADO.getName().equals(output.getDescripcionEstadoProceso())) {
                System.out.println("PantallaEjecutarScriptInicialEntornoPruebaListener.eventBtnEjecutar => "
                        + nombreBBDD + "." + nombreEsquema + " " + lanzaFile + " Estado=" + output.getDescripcionEstadoProceso());
            }
        } catch (IOException | ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
            return;
        }
        pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_EJECUTAR);
        pantalla.dispose();
    }

    public void eventBtnCancelar() {
        try {
            //Si ya existía un procesado se rechaza
            rechazarProcesado();
            pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        try {
            clearForm();
            super.onLoad();
            fillCmbEntornoPrueba();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
            pantalla.setErrorOnload(Boolean.TRUE);
        }
    }

    private void fillCmbEntornoPrueba() throws ServiceException {
        EntornosPruebaService service = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);
        OutputConsulta<EntornoPrueba> output = service.consultarEntornos();
        // Si hay avisos se muestran
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        pantalla.getCmbEntornoPrueba().setModel(new EntornoPruebaComboBoxModel(output.getLista()));
    }

    private void habilitaBtnProcesar() {
        boolean habilitar
                = pantalla.getTxtModeloProyecto().getText() != null
                && !pantalla.getTxtModeloProyecto().getText().isEmpty()
                && pantalla.getTxtScript().getText() != null
                && !pantalla.getTxtScript().getText().isEmpty();
        pantalla.getBtnProcesar().setEnabled(habilitar);
    }

    @Override
    public void clearForm() {
        pantalla.getChkGenerarDROP().setSelected(false);
        pantalla.getBtnProcesar().setEnabled(false);
        pantalla.getBtnEjecutar().setEnabled(false);
    }

    @Override
    public void procesarModelo() throws ServiceException {
        super.procesarModelo();
        habilitaBtnProcesar();
    }

    /**
     * Si hay un procesado NO RECHAZADO previamente, lo rechaza y renombra los
     * archivos
     *
     * @throws com.mdval.exceptions.ServiceException
     */
    protected void rechazarProcesado() throws ServiceException {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();
        if (proceso != null) {
            try {
                if (!Objects.equals(proceso.getDescripcionEstadoProceso(), MDSQLConstants.EstadosProcesado.RECHAZADO.getName())) {
                    ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
                    ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
                    String txtMotivoRechazo = configuration.getConfig("literalRechazoScriptInicial");
                    OutputWarning output = procesoService.rechazarProcesado(proceso.getIdProceso(), txtMotivoRechazo, session.getCodUsr());
                    MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());                    
                }
                rechazarScripts(proceso);
                session.setProceso(null);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
    }

    private void rechazarScripts(Proceso proceso) {
        try {
            String sufijoRechazo = ConfigurationSingleton.getInstance().getConfig("SufijoRechazoProcesado");
            List<Script> scripts = proceso.getScripts();
            String ruta = proceso.getRutaScript();
            // Renombramos los scripts
            if (StringUtils.isNotBlank(ruta) && CollectionUtils.isNotEmpty(scripts)) {
                for (Script script : scripts) {
                    renombrarArchivo(script.getNombreScript(), ruta, sufijoRechazo);
                }
            }
            // Renombramos fichero de Log
            renombrarArchivo(proceso.getFicheroLog(), ruta, sufijoRechazo);
            // Borrar fichero lanza
            if (proceso.getScriptLanza() != null && proceso.getScriptLanza().getNombreScript() != null) {
                File fileLanza = new File(ruta.concat(proceso.getScriptLanza().getNombreScript()));
                if (fileLanza.exists()){
                    fileLanza.delete();
                } 
            }
        } catch (IOException ex) {
            Logger.getLogger(PantallaEjecutarScriptInicialEntornoPruebaListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void renombrarArchivo(String nombreFile, String ruta, String sufijoRechazo) {
        if (nombreFile == null || ruta == null) {
            return;
        }
        File f = new File(ruta.concat(nombreFile));
        if (f.exists()) {
            String fullName = f.getAbsolutePath();
            String[] nameAndExtension = MDSQLAppHelper.separateFileNameAndExtension(fullName);
            String nombre = nameAndExtension[0];
            String extension = nameAndExtension[1];
            String rechazado = nombre.concat("_" + sufijoRechazo);
            String fileNameRechazado = rechazado + (extension.isEmpty() ? "" : "." + extension);
            //System.out.println("[Log] => Se renombra " + nombre + " a " + fileNameRechazado);
            MDSQLAppHelper.renombrarArchivo(f, fileNameRechazado);
        }
    }
}
