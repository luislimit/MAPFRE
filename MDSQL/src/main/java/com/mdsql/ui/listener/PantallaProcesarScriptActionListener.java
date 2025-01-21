package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.ConsultaBD;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputProcesaType;
import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputDatosReentrante;
import com.mdsql.bussiness.entities.OutputProcesa;
import com.mdsql.bussiness.entities.OutputProcesaReentrante;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputProcesaType;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ReentranteInfo;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.bussiness.service.ReentranteService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.bussiness.service.TypeService;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.PantallaProcesarScript;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaSeleccionHistorico;
import com.mdsql.ui.PantallaSeleccionScriptReentrante;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.EstadosProcesado;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author federico
 *
 */
public class PantallaProcesarScriptActionListener extends ListenerSupportModeloPermiso implements ListSelectionListener {

    private final PantallaProcesarScript pantalla;

    /**
     * @param pantalla
     */
    public PantallaProcesarScriptActionListener(PantallaProcesarScript pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        if (object.equals(pantalla.getBtnLimpiar())) {
            eventBtnLimpiar();
        } else if (object.equals(pantalla.getBtnProcesar())) {
            eventBtnProcesar();
        } else if (object.equals(pantalla.getBtnVerProcesado())) {
            eventBtnVerProcesado();
        } else if (object.equals(pantalla.getBtnRecuperarUltimoProcesado())) {
            eventBtnRecuperarUltimoProcesado();
        } else if (object.equals(pantalla.getBtnParametros())) {
            eventBtnParametros();
        } else {
            super.actionPerformed(e);
        }
    }

    private void eventBtnVerProcesado() {
        Map<String, Object> params = new HashMap<>();

        Proceso seleccionado = pantalla.getProcesoSeleccionado();

        params.put(MDSQLConstants.P_IN_PROCESO, seleccionado);
        params.put(MDSQLConstants.P_IN_ENTREGAR, Boolean.FALSE);

        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaResumenProcesado.class, params);
    }

    /**
     *
     */
    private void eventBtnLimpiar() {
        pantalla.getTxtModeloProyecto().setText(StringUtils.EMPTY);
        pantalla.getTxtEsquema().setText(StringUtils.EMPTY);
        pantalla.getChkGenerarHistorico().setSelected(Boolean.FALSE);
        pantalla.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
        pantalla.getTxtEsquema().setText(StringUtils.EMPTY);
        pantalla.getTxtBBDDHistorico().setText(StringUtils.EMPTY);
        pantalla.getTxtEsquemaHistorico().setText(StringUtils.EMPTY);
        pantalla.getTxtDescripcion().setText(StringUtils.EMPTY);
        pantalla.getTxtSolicitadaPor().setText(StringUtils.EMPTY);
        pantalla.getTxtPeticion().setText(StringUtils.EMPTY);
        pantalla.getTxtDemanda().setText(StringUtils.EMPTY);

        ((ProcesarScriptNotaTableModel) pantalla.getTblNotas().getModel()).clearData();
        ((ProcesarScriptUltimasPeticionesTableModel) pantalla.getTblUltimasPeticiones().getModel())
                .clearData();

        // Clear comboboxes
        DefaultComboBoxModel<BBDD> bbddModel = new DefaultComboBoxModel<>();
        pantalla.getCmbBBDD().setModel(bbddModel);

        DefaultComboBoxModel<SubProyecto> subproyectoModel = new DefaultComboBoxModel<>();
        pantalla.getCmbSubmodelo().setModel(subproyectoModel);

        pantalla.getBtnLimpiar().setEnabled(Boolean.FALSE);
        pantalla.getBtnVerProcesado().setEnabled(Boolean.FALSE);

        pantalla.setModelo(null);
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    private void eventBtnProcesar() {
        Modelo seleccionado = pantalla.getModelo();
        Procesado procesado = pantalla.getProcesado();

        if (Objects.isNull(seleccionado) || procesado == null) {
            return;
        }
        switch (procesado) {
            case TYPE:
                procesarType();
                break;
            case REENTRANTE:
                procesarReentrante();
                break;
            case SCRIPT:
                // El modelo tiene histórico
                if (MDSQLConstants.S.equals(seleccionado.getMcaHis())) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("codigoProyecto", seleccionado.getCodigoProyecto());
                    params.put("script", pantalla.getParams().get("script"));
                    params.put("codigoPeticion", pantalla.getTxtPeticion().getText());

                    /*PantallaSeleccionHistorico pantallaSeleccionHistorico
                            = (PantallaSeleccionHistorico) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
                                    MDSQLConstants.CMD_SELECCION_HISTORICO, params);*/
                    PantallaSeleccionHistorico pantallaSeleccionHistorico = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionHistorico.class, params);

                    // Si al cargar hay error, la pantalla no se mostrará
                    if (!pantallaSeleccionHistorico.getErrorOnload()) {
                        MDSQLUIHelper.show(pantallaSeleccionHistorico);

                        Boolean continuarProcesado = (Boolean) pantallaSeleccionHistorico.getReturnParams().get("procesado");
                        if (!Objects.isNull(continuarProcesado) && continuarProcesado) {
                            List<SeleccionHistorico> objetosHistorico = (List<SeleccionHistorico>) pantallaSeleccionHistorico.getReturnParams().get("objetosHistorico");
                            procesarScript(objetosHistorico);
                        } else {
                            JOptionPane.showMessageDialog(pantalla.getFrameParent(), "Operación cancelada");
                        }
                    }
                } else {
                    procesarScript(null);
                }
        }
    }

    /**
     * @throws com.mdval.exceptions.ServiceException
     */
    @Override
    public void procesarModelo() throws ServiceException {
        Modelo modelo = pantalla.getModelo();
        pantalla.getTxtModeloProyecto().setText(modelo.getCodigoProyecto());
        pantalla.getTxtEsquema().setText(modelo.getNombreEsquema());

        fillCmbSubModelos(modelo);

        fillUltimasPeticiones(modelo);

        fillAvisos(modelo);

        SubProyecto subproyecto = pantalla.getSubModelo();
        if (!Objects.isNull(subproyecto)) {
            fillBBDD(modelo, subproyecto);
        }

        fillChkHistorico(modelo);

        pantalla.getBtnLimpiar().setEnabled(Boolean.TRUE);
    }

    /**
     * @param seleccionado
     */
    private void fillUltimasPeticiones(Modelo seleccionado) throws ServiceException {
        // Limpiar la tabla de peticiones
        ((ProcesarScriptUltimasPeticionesTableModel) pantalla.getTblUltimasPeticiones().getModel())
                .clearData();

        // Hacer la consulta
        InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();

        inputSeleccionarProcesados.setPCodigoproyecto(seleccionado.getCodigoProyecto());
        inputSeleccionarProcesados.setPUltimas(new BigDecimal(1));

        ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
        OutputConsulta<Proceso> output = procesoService.seleccionarProcesados(inputSeleccionarProcesados);
        List<Proceso> peticiones = output.getLista();

        if (CollectionUtils.isNotEmpty(peticiones)) {
            populateModelUltimasPeticiones(peticiones);
        }
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     * @param seleccionado
     */
    private void fillAvisos(Modelo seleccionado) throws ServiceException {
        // Limpiar la tabla de avisos
        ((ProcesarScriptNotaTableModel) pantalla.getTblNotas().getModel()).clearData();

        // Hacer la consulta
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
        OutputConsulta<Aviso> output = avisoService.consultaAvisosModelo(seleccionado.getCodigoProyecto());

        populateModelAvisos(output.getLista());
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     * @param seleccionado
     */
    private void fillBBDD(Modelo modelo, SubProyecto subproyecto) throws ServiceException {
        BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);

        String codigoProyecto = modelo.getCodigoProyecto();
        String codigoSubproyecto = !Objects.isNull(subproyecto)
                ? subproyecto.getCodigoSubProyecto()
                : null;

        OutputConsulta<BBDD> output = bbddService.consultaBBDDModelo(codigoProyecto, codigoSubproyecto);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        pantalla.setBbdds(output.getLista());
        if (CollectionUtils.isNotEmpty(output.getLista())) {
            BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(output.getLista());
            pantalla.getCmbBBDD().setModel(modelBBDD);
        }

        // Pone la base de datos por defecto
        String baseDatos = modelo.getNombreBbdd();
        if (StringUtils.isNotBlank(baseDatos)) {
            BBDDComboBoxModel modelBBDD = (BBDDComboBoxModel) pantalla.getCmbBBDD().getModel();
            for (int i = 0; i < modelBBDD.getSize(); i++) {
                BBDD bbdd = modelBBDD.getElementAt(i);
                if (bbdd.getNombreBBDD().equals(baseDatos)) {
                    pantalla.getCmbBBDD().setSelectedItem(bbdd);
                }
            }
        }
    }

    /**
     * @param seleccionado
     */
    private void fillChkHistorico(Modelo seleccionado) {
        // En caso de no tener histórico el check de Generar histórico estará desmarcado
        // y deshabilitado.
        String tieneHistorico = seleccionado.getMcaHis();
        if (MDSQLConstants.N.equals(tieneHistorico)) {
            pantalla.getChkGenerarHistorico().setSelected(Boolean.FALSE);
            pantalla.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
        } else {
            pantalla.getChkGenerarHistorico().setSelected(Boolean.TRUE);
            pantalla.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
        }
    }

    /**
     * @param avisos
     */
    private void populateModelAvisos(List<Aviso> avisos) {
        // Obtiene el modelo y lo actualiza
        ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantalla.getTblNotas()
                .getModel();
        tableModel.setData(avisos);
    }

    /**
     * @param peticiones
     */
    private void populateModelUltimasPeticiones(List<Proceso> peticiones) {
        // Obtiene el modelo y lo actualiza
        ProcesarScriptUltimasPeticionesTableModel tableModel = (ProcesarScriptUltimasPeticionesTableModel) pantalla
                .getTblUltimasPeticiones().getModel();
        tableModel.setData(peticiones);
    }

    /**
     * @param objetosHistorico
     */
    private void procesarScript(List<SeleccionHistorico> objetosHistorico) {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String usuario = session.getCodUsr();

            ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);

            Modelo seleccionado = pantalla.getModelo();
            if (!Objects.isNull(seleccionado)) {
                SubProyecto subProyecto = pantalla.getSubModelo();

                InputProcesaScript inputProcesaScript = new InputProcesaScript();
                List<TextoLinea> lineasScript = pantalla.getScript();
                inputProcesaScript.setLineasScript(lineasScript);
                inputProcesaScript.setPCodigoProyecto(seleccionado.getCodigoProyecto());

                if (!Objects.isNull(subProyecto)) {
                    inputProcesaScript.setPCodigoSubProyecto(subProyecto.getCodigoSubProyecto());
                }

                inputProcesaScript.setPCodigoPeticion(pantalla.getTxtPeticion().getText());
                inputProcesaScript.setPCodigoDemanda(pantalla.getTxtDemanda().getText());
                inputProcesaScript.setPMcaReprocesa(MDSQLConstants.N);
                inputProcesaScript.setPCodigoUsr(usuario);
                inputProcesaScript.setPCodigoUsrPeticion(pantalla.getTxtSolicitadaPor().getText());
                inputProcesaScript.setPMcaHIS(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkGenerarHistorico().isSelected()));

                BBDD selectedBBDD = (BBDD) pantalla.getCmbBBDD().getSelectedItem();
                if (!Objects.isNull(selectedBBDD)) {
                    inputProcesaScript.setPNombreBBDD(selectedBBDD.getNombreBBDD());
                    inputProcesaScript.setPNombreEsquema(selectedBBDD.getNombreEsquema());
                    inputProcesaScript.setPNombreBBDDHIS(selectedBBDD.getNombreBBDDHis());
                    inputProcesaScript.setPNombreEsquemaHis(selectedBBDD.getNombreEsquemaHis());
                }

                inputProcesaScript.setPTxtDescripcion(pantalla.getTxtDescripcion().getText());

                // Si la lista de objetos del histórico está vacía, no informa la lista a procesar
                if (CollectionUtils.isNotEmpty(objetosHistorico)) {
                    inputProcesaScript.setListaObjetoHis(objetosHistorico);
                }

                File file = (File) pantalla.getParams().get("file");
                inputProcesaScript.setPNombreFichaEntrada(file.getName());

                String parentPath = file.getAbsoluteFile().getParent();
                inputProcesaScript.setPTxtRutaEntrada(parentPath);

                OutputProcesaScript outputProcesaScript = scriptService.procesarScript(inputProcesaScript);

                MDSQLUIHelper.showWarnings(pantalla, outputProcesaScript.getWarnings());

                //Ejecutar consultas para borrado de indices, constraints y FK si fuera necesario
                executeConsultaDB(usuario, parentPath, outputProcesaScript);

                Proceso proceso = generateProceso(usuario, outputProcesaScript);
                proceso.setModelo(seleccionado);
                proceso.setSubproyecto(subProyecto);
                proceso.setBbdd(selectedBBDD);
                proceso.setBbdds(pantalla.getBbdds());

                pantalla.getReturnParams().put("proceso", proceso);

                List<Script> listaScripts = outputProcesaScript.getListaScripts();
                proceso.setScripts(listaScripts);

                if (CollectionUtils.isNotEmpty(listaScripts)) {
                    pantalla.getReturnParams().put("scripts", listaScripts);
                }

                finProcesadoOk(session, proceso);
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * Ejecutar consultas para borrado de indices, constraints y FK devuelto al
     * procesar scripts y reentrantes
     */
    private void executeConsultaDB(String usuario, String ruta, OutputProcesaScript output) throws ServiceException {
        //Ejecutar consultas para borrado de indices, constraints y FK si fuera necesario
        if (output.getListaConsultaBD().isEmpty()) {
            return;
        }

        ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);

        for (ConsultaBD consultaBD : output.getListaConsultaBD()) {
            try {
                String nombreScript = consultaBD.getNombreScript();
                if (nombreScript == null || nombreScript.isEmpty()) {
                    throw new ServiceException("executeConsultaDB:: No se ha indicado el nombre del fichero");
                }
                String nombreLog = consultaBD.getNombreScriptLog();
                if (nombreLog == null || nombreLog.isEmpty()) {
                    throw new ServiceException("executeConsultaDB:: No se ha indicado el nombre del fichero de LOG");
                }
                if (ruta == null || ruta.isEmpty()) {
                    throw new ServiceException("executeConsultaDB:: No se ha indicado la ruta");
                }
                if (!ruta.endsWith(File.separator)) {
                    ruta = ruta.concat(File.separator);
                }
                String mensaje = MDSQLUIHelper.getKeyTextValue("mensaje.ejecutando.consultas.bbdd");
                //Concatenamos las rutas
                nombreScript = ruta.concat(nombreScript);
                nombreLog = ruta.concat(nombreLog);
                // Sólo hay que crear el script lanza si existe el nombre
                MDSQLAppHelper.dumpLinesToFile(consultaBD.getScript(), nombreScript);
                // Ejecución del script
                scriptService.executeLanzaFile(consultaBD.getNombreEsquema(), consultaBD.getNombreBBDD(), consultaBD.getPassword(), nombreScript, mensaje);
                // Obtiene el log
                List<TextoLinea> lineasLog = MDSQLAppHelper.writeFileToLines(new File(nombreLog));
                OutputWarning outputWarning = scriptService.consultaBBDDModelo(output.getIdProceso(), lineasLog, usuario);
                MDSQLUIHelper.showWarnings(pantalla, outputWarning.getWarnings());
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
    }

    /**
     *
     */
    private void procesarType() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String usuario = session.getCodUsr();

            TypeService typeService = (TypeService) getService(MDSQLConstants.TYPE_SERVICE);

            Modelo seleccionado = pantalla.getModelo();
            SubProyecto subProyecto = pantalla.getSubModelo();

            InputProcesaType inputProcesaType = new InputProcesaType();
            List<TextoLinea> lineasScript = pantalla.getScript();
            inputProcesaType.setLineasScript(lineasScript);
            inputProcesaType.setPCodigoProyecto(seleccionado.getCodigoProyecto());
            inputProcesaType.setPCodigoSubProyecto(subProyecto.getCodigoSubProyecto());
            inputProcesaType.setPCodigoPeticion(pantalla.getTxtPeticion().getText());
            inputProcesaType.setPCodigoDemanda(pantalla.getTxtDemanda().getText());
            inputProcesaType.setPCodigoUsr(usuario);
            inputProcesaType.setPCodigoUsrPeticion(pantalla.getTxtSolicitadaPor().getText());

            BBDD selectedBBDD = (BBDD) pantalla.getCmbBBDD().getSelectedItem();
            if (!Objects.isNull(selectedBBDD)) {
                inputProcesaType.setPNombreBBDD(selectedBBDD.getNombreBBDD());
                inputProcesaType.setPNombreEsquema(selectedBBDD.getNombreEsquema());
                inputProcesaType.setPNombreBBDDHIS(selectedBBDD.getNombreBBDDHis());
                inputProcesaType.setPNombreEsquemaHis(selectedBBDD.getNombreEsquemaHis());
            }

            inputProcesaType.setPTxtDescripcion(pantalla.getTxtDescripcion().getText());

            File file = (File) pantalla.getParams().get("file");
            inputProcesaType.setPNombreFichaEntrada(file.getName());

            String parentPath = file.getAbsoluteFile().getParent();
            inputProcesaType.setPTxtRutaEntrada(parentPath);

            OutputProcesaType outputProcesaType = typeService.procesarType(inputProcesaType);

            MDSQLUIHelper.showWarnings(pantalla, outputProcesaType.getWarnings());

            Proceso proceso = generateProceso(usuario, outputProcesaType);
            proceso.setModelo(seleccionado);
            proceso.setSubproyecto(subProyecto);
            proceso.setBbdd(selectedBBDD);
            proceso.setBbdds(pantalla.getBbdds());

            List<Type> listaTypes = outputProcesaType.getListaType();
            proceso.setTypes(listaTypes);

            String nombreScriptLanza = outputProcesaType.getPNombreScriptLanza();
            List<TextoLinea> scriptLanza = outputProcesaType.getTxtScriptLanza();

            String nombreFicheroLog = outputProcesaType.getPNombreScriptLog();
            proceso.setFicheroLog(nombreFicheroLog);

            if (CollectionUtils.isNotEmpty(listaTypes)) {
                pantalla.getReturnParams().put("types", listaTypes);
                pantalla.getReturnParams().put("nombreScriptLanza", nombreScriptLanza);
                pantalla.getReturnParams().put("scriptLanza", scriptLanza);
            }

            finProcesadoOk(session, proceso);

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param usuario
     * @param outputProcesa
     * @return
     */
    private Proceso generateProceso(String usuario, OutputProcesa outputProcesa) {
        Proceso proceso = new Proceso();

        proceso.setModelo(pantalla.getModelo());
        proceso.setSubproyecto(pantalla.getSubModelo());
        BBDD selectedBBDD = (BBDD) pantalla.getCmbBBDD().getSelectedItem();
        proceso.setBbdd(selectedBBDD);
        proceso.setBbdds(pantalla.getBbdds());

        proceso.setIdProceso(outputProcesa.getIdProceso());
        proceso.setCodigoEstadoProceso(outputProcesa.getCodigoEstadoProceso());
        proceso.setDescripcionEstadoProceso(outputProcesa.getDescripcionEstadoProceso());

        proceso.setCodigoPeticion(pantalla.getTxtPeticion().getText());
        proceso.setCodigoDemanda(pantalla.getTxtDemanda().getText());
        proceso.setCodigoUsr(usuario);
        proceso.setCodigoUsrPeticion(pantalla.getTxtSolicitadaPor().getText());
        proceso.setTxtDescripcion(pantalla.getTxtDescripcion().getText());
        return proceso;
    }

    private void cargaProcesado(Proceso proceso, boolean editable) {
        if (proceso == null) {
            return;
        }
        try {
            pantalla.setModelo(proceso.getModelo());
            procesarModelo();

            pantalla.getTxtModeloProyecto().setEditable(editable);
            pantalla.getCmbSubModelo().setEnabled(editable);
            pantalla.getTxtPeticion().setEditable(editable);
            pantalla.getTxtSolicitadaPor().setEditable(editable);
            pantalla.getCmbBBDD().setEnabled(editable);

            //pantalla.getTxtDemanda().setEditable(editable);
            pantalla.getTxtDemanda().setEditable(false);

            pantalla.getTxtDescripcion().setEditable(editable);

            //pantalla.getTxtModeloProyecto().setText(proceso.getModelo().getCodigoProyecto());
            pantalla.getCmbSubModelo().setSelectedItem(proceso.getSubproyecto());
            pantalla.getTxtPeticion().setText(proceso.getCodigoPeticion());
            pantalla.getTxtSolicitadaPor().setText(proceso.getCodigoUsrPeticion());
            pantalla.getCmbBBDD().setSelectedItem(proceso.getBbdd());
            pantalla.getTxtEsquema().setText(proceso.getBbdd().getNombreEsquema());
            pantalla.getTxtBBDDHistorico().setText(proceso.getBbdd().getNombreBBDDHis());
            pantalla.getTxtEsquemaHistorico().setText(proceso.getBbdd().getNombreEsquemaHis());
            pantalla.getTxtDemanda().setText(proceso.getCodigoDemanda());
            pantalla.getTxtDescripcion().setText(proceso.getTxtDescripcion());

            pantalla.getBtnModeloProyecto().setEnabled(editable);
            pantalla.getBtnProcesar().setEnabled(editable);
        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    @Override
    public void onLoad() {
        pantalla.getBtnLimpiar().setEnabled(Boolean.FALSE);
        pantalla.getBtnVerProcesado().setEnabled(Boolean.FALSE);
        pantalla.getBtnParametros().setEnabled(Boolean.FALSE);
        try {
            Map<String, Object> params = pantalla.getParams();
            Proceso proceso = (Proceso) params.get("proceso");
            if (Objects.isNull(proceso)) {
                Procesado procesado = (Procesado) params.get("procesado");
                if (procesado != null) {
                    pantalla.setProcesado(procesado);
                    if (procesado.equals(Procesado.REENTRANTE)) {
                        cargaReentrante(proceso);
                    } else {
                        List<TextoLinea> script = (List<TextoLinea>) params.get("script");
                        pantalla.setScript(script);
                    }
                }
            } else {
                cargaProcesado(proceso, false);
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void eventBtnRecuperarUltimoProcesado() {
        try {
            Proceso proceso = pantalla.getProcesoSeleccionado();
            if (proceso != null) {
                BigDecimal estado = proceso.getCodigoEstadoProceso();
                BigDecimal GENERADO = BigDecimal.valueOf(EstadosProcesado.GENERADO.getIndex());
                if (!estado.equals(GENERADO)) {
                    MDSQLUIHelper.showMessage(pantalla, "aviso.ultimo_procesado_en_curso");
                }
            } else {
                Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
                proceso = session.getUltimoProceso();
                if (proceso != null && pantalla.getProcesado().equals(Procesado.REENTRANTE)) {
                    cargaReentrante(proceso);
                } else {
                    cargaProcesado(proceso, true);
                }
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        ListSelectionModel lsm = ((ListSelectionModel) e.getSource());

        // Identificar la tabla que ha disparado el evento
        if (lsm.equals(pantalla.getTblNotas().getSelectionModel())) {
            evtSeleccionTblNotas();
        } else {
            // Si hubiera que tratar la tabla de Ultimas Peticiones
        }
    }

    private void evtSeleccionTblNotas() {
        JTable tblNotas = pantalla.getTblNotas();
        int row = tblNotas.getSelectedRow();
        if (row >= 0) {
            ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) tblNotas.getModel();
            Aviso aviso = tableModel.getData().get(row);
            pantalla.getTxtDescripcionAviso().setText(aviso.getDescripcion());
            // Colocar el cursor al inicio del texto para mostrar desde el principio
            pantalla.getTxtDescripcionAviso().setCaretPosition(0);
        } else {
            pantalla.getTxtDescripcionAviso().setText("");
        }
    }

    private Modelo buscarModelo(String codProyecto, String codSubProyecto) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
        OutputConsulta<Modelo> outputModelo = modeloService.consultaModelos(codProyecto, null, codSubProyecto);
        for (Modelo m : outputModelo.getLista()) {
            if (m.getCodigoProyecto().equals(codProyecto)) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     */
    private void eventBtnParametros() {
        PantallaSeleccionScriptReentrante pantallaSeleccionScriptReentrante;
        pantallaSeleccionScriptReentrante
                = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionScriptReentrante.class,
                        null);
        String exitButton = (String) pantallaSeleccionScriptReentrante.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (exitButton == null || !MDSQLConstants.BTN_ACEPTAR.equals(exitButton)) {
            //No ha presionado Aceptar en la pantalla de seleccion de reentrantes
            return;
        }
        // Cargar el nombre de la tabla y el tipo de operación desde la sesión
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        pantalla.getTxtNombreTabla().setText(session.getReentranteInfo().getNombreTabla());
        pantalla.getTxtTipoOperacion().setText(session.getReentranteInfo().getTipoOperacion());

        FramePrincipal framePrincipal = (FramePrincipal) pantalla.getFrameParent();
        framePrincipal.getActionListener().muestraReentrantes();
    }

    /**
     *
     * @param proceso
     * @throws ServiceException
     */
    private void cargaReentrante(Proceso proceso) throws ServiceException {
        if (proceso == null) {
            // Rellenar Modelo, subModelo y BBDD
            ReentranteService reentranteService = (ReentranteService) getService(MDSQLConstants.REENTRANTE_SERVICE);
            OutputDatosReentrante output = reentranteService.datosReentrante();
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            // Rellenamos el Código del Modelo
            pantalla.getTxtModeloProyecto().setText(output.getCodProyecto());
            //Buscamos el modelo
            Modelo modelo = buscarModelo(output.getCodProyecto(), output.getCodSubProyecto());
            pantalla.setModelo(modelo);
            // Cargamos los datos vinculados al modelo
            procesarModelo();

            // Seleccionamos el subproyecto
            SubProyecto subModelo = SubProyecto.builder().codigoSubProyecto(output.getCodSubProyecto()).build();
            pantalla.getCmbSubModelo().setSelectedItem(subModelo);

            // Seleccionamos la BBDD
            BBDD bbdd = BBDD.builder().nombreBBDD(output.getNombreBBDD()).build();
            pantalla.getCmbBBDD().setSelectedItem(bbdd);

        } else {
            // Rellenamos los datos a partir del proceso
            cargaProcesado(proceso, true);
        }
        // Cargar el nombre de la tabla y el tipo de operación desde la sesión
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        pantalla.getTxtNombreTabla().setText(session.getReentranteInfo().getNombreTabla());
        pantalla.getTxtTipoOperacion().setText(session.getReentranteInfo().getTipoOperacion());
        // desactivar la lupa, submodelo y BBDD
        pantalla.getBtnModeloProyecto().setEnabled(false);
        pantalla.getTxtModeloProyecto().setEditable(false);
        pantalla.getCmbSubModelo().setEnabled(false);
        pantalla.getCmbBBDD().setEnabled(false);
        // se habilita el botón Parámetros
        pantalla.getBtnParametros().setEnabled(true);
    }

    /**
     *
     */
    private void procesarReentrante() {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            ReentranteService service = (ReentranteService) getService(MDSQLConstants.REENTRANTE_SERVICE);

            String codProyecto = pantalla.getTxtModeloProyecto().getText();
            String codSubProyecto = ((SubProyecto) pantalla.getCmbSubModelo().getSelectedItem()).getCodigoSubProyecto();
            String codPeticion = pantalla.getTxtPeticion().getText();
            String codDemanda = pantalla.getTxtDemanda().getText();
            String codUsr = MDSQLAppHelper.getUsuario();
            String codUsrPeticion = pantalla.getTxtSolicitadaPor().getText();
            String nomBBDD = ((BBDD) pantalla.getCmbBBDD().getSelectedItem()).getNombreBBDD();
            String nomEsquema = pantalla.getTxtEsquema().getText();
            ReentranteInfo reentranteInfo = session.getReentranteInfo();
            String descripcion = pantalla.getTxtDescripcion().getText();

            OutputProcesaReentrante output = service.procesaScriptReentrante(
                    codProyecto,
                    codSubProyecto,
                    codPeticion,
                    codDemanda,
                    codUsr,
                    codUsrPeticion,
                    nomBBDD,
                    nomEsquema,
                    reentranteInfo,
                    descripcion);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            //Ejecutar consultas para borrado de indices, constraints y FK si fuera necesario
            executeConsultaDB(codUsr, reentranteInfo.getRuta(), output);

            Proceso proceso = generateProceso(session.getCodUsr(), output);
            List<Script> listaScripts = output.getListaScripts();
            proceso.setScripts(listaScripts);
            proceso.setLanzas(output.getListaLanza());

            finProcesadoOk(session, proceso);

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * Realizar las acciones al finalizar procesado de script
     *
     * @param session
     * @param proceso
     */
    private void finProcesadoOk(Session session, Proceso proceso) {
        session.setProceso(proceso);
        // Guardamos el último proceso por si se desea recuperar con [Recuperar ultimo procesado]
        session.setUltimoProceso(proceso);
        pantalla.getReturnParams().put("proceso", proceso);
        pantalla.dispose();
    }
}
