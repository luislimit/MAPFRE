/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputProcesaPermisoPersonalizado;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.PermisosPersonalizadosService;
import com.mdsql.ui.form.FormConfirmacionGeneracionPermisos;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormConfirmacionGeneracionPermisosListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final FormConfirmacionGeneracionPermisos pantalla;
    private Modelo modelo;
    private SubProyecto subModelo;
    private String nomObjeto;
    private String tipObjeto;
    private String codPeticion;
    private final Session session;

    public FormConfirmacionGeneracionPermisosListener(FormConfirmacionGeneracionPermisos pantalla) {
        super();
        this.pantalla = pantalla;
        this.session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
    }

    @Override
    public void onLoad() {
        if (pantalla.getParams() != null) {
            modelo = (Modelo) pantalla.getParams().get(MDSQLConstants.P_IN_MODELO);
            subModelo = (SubProyecto) pantalla.getParams().get(MDSQLConstants.P_IN_SUB_MODELO);
            nomObjeto = (String) pantalla.getParams().get(MDSQLConstants.P_IN_NOM_OBJETO);
            tipObjeto = (String) pantalla.getParams().get(MDSQLConstants.P_IN_TIP_OBJETO);
            codPeticion = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_PETICION);
            if (codPeticion != null && !codPeticion.isEmpty()) {
                pantalla.getTxtPeticion().setText(codPeticion);
                pantalla.getTxtPeticion().setEditable(false);
            }
            try {
                if (modelo != null) {
                    fillCmbBBDD();
                }
            } catch (ServiceException e) {
                MDSQLUIHelper.showErrors(pantalla, e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if (btn.equals(pantalla.getBtnCancelar())) {
            evtBtnCancelar();
        } else if (btn.equals(pantalla.getBtnAceptar())) {
            evtBtnAceptar();
        } else if (btn.equals(pantalla.getBtnRuta())) {
            evtBtnRuta();
        }
    }

    private void evtBtnCancelar() {
        pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_CANCELAR);
        pantalla.dispose();
    }

    private void evtBtnAceptar() {
        try {
            if (!MDSQLUIHelper.confirmAction(pantalla.getFrameParent(), "confirmacion.mensaje")) {
                return;
            }
            PermisosPersonalizadosService permisosPersonalizadosService;
            permisosPersonalizadosService = (PermisosPersonalizadosService) getService(MDSQLConstants.PERMISOS_PERSONALIZADOS_SERVICE);
            OutputProcesaPermisoPersonalizado output;
            String p_cod_proyecto = modelo.getCodigoProyecto();
            String p_cod_sub_proy = subModelo.getCodigoSubProyecto();
            String p_tip_objeto = tipObjeto; // Viene de la pantalla anterior
            String p_nom_objeto = nomObjeto; // Viene de la pantalla anterior
            String p_cod_peticion_ant = codPeticion; // Viene de la pantalla anterior
            String p_cod_peticion = pantalla.getTxtPeticion().getText();
            String p_cod_demanda = pantalla.getTxtDemanda().getText();

            BBDD bbdd = (BBDD) pantalla.getCmbBBDD().getSelectedItem();

            String p_nom_BBDD = (bbdd != null) ? bbdd.getNombreBBDD() : null;
            String p_cod_usr_peticion = pantalla.getTxtSolictadaPor().getText();
            String p_txt_comentario = pantalla.getTxtComentario().getText();
            String p_mca_generales = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkGenerarPermisosGenerales().isSelected());
            String p_mca_resto_per = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkGenerarRestoPermisosPersonalizados().isSelected());
            String p_ruta_salida = pantalla.getTxtRuta().getText();

            String p_cod_usr = session.getCodUsr();

            output = permisosPersonalizadosService.procesa(
                    p_cod_proyecto,
                    p_cod_sub_proy,
                    p_tip_objeto, // Viene de la pantalla anterior
                    p_nom_objeto, // Viene de la pantalla anterior
                    p_cod_peticion_ant, // Viene de la pantalla anterior
                    p_cod_peticion,
                    p_cod_demanda,
                    p_nom_BBDD,
                    p_cod_usr_peticion,
                    p_txt_comentario,
                    p_mca_generales,
                    p_mca_resto_per,
                    p_ruta_salida,
                    p_cod_usr
            );
            List<Script> scripts = output.getListaScripts();
            //Creamos y cargamos el proceso en la sesion
            Proceso proceso = Proceso.builder().
                    idProceso(output.getIdProceso()).
                    bbdd(bbdd).
                    codigoPeticion(p_cod_peticion).
                    codigoUsr(p_cod_usr).
                    modelo(modelo).
                    subproyecto(subModelo).
                    rutaScript(p_ruta_salida).
                    fechaInicio(output.getFechaProceso()).
                    codigoEstadoProceso(output.getCodigoEstadoProceso()).
                    descripcionEstadoProceso(output.getDescripcionEstadoProceso()).
                    codigoDemanda(p_cod_demanda).
                    scripts(scripts).build();
            session.setProceso(proceso);
            // Procesamos los scripts vinculados al nuevo proceso, si hay error, renombramos los scripts generados
            List<OutputRegistraEjecucion> ejecuciones = null;
            try {
                ejecuciones = permisosPersonalizadosService.executeScripts(bbdd, scripts, p_ruta_salida);
            } catch (ServiceException e) {
                renameGeneratedScripts(p_ruta_salida, scripts);
                throw e;
            }
            // Informamos resultado de la ejecucion, si Hay avisos
            for (OutputRegistraEjecucion ejecucion : ejecuciones) {
                // Por cada script muestra un aviso
                if (ejecucion.getResult() == 2) {
                    MDSQLUIHelper.showWarnings(pantalla, ejecucion.getServiceException());
                }
            }
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_ACEPTAR);
            pantalla.dispose();
        } catch (IOException | ServiceException e) {
            session.setProceso(null);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtBtnRuta() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        String ruta = pantalla.getTxtRuta().getText();
        // Si hay una ruta seleccionada abrimos en ella
        if (ruta == null || ruta.isEmpty()) {
            //Si no hay ruta, tomamos la de la sesión
            ruta = session.getSelectedRoute();
            // Si no hay ruta en la sesión, tomamos RutaDefectoScripts
            try {
                if (ruta == null || ruta.isEmpty()) {
                    ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
                    ruta = configuration.getConfig("RutaDefectoScripts");
                }
            } catch (IOException e) {
            }
        }
        if (ruta != null && !ruta.isEmpty()) {
            fc.setCurrentDirectory(new File(ruta));
        }
        int state = fc.showOpenDialog(fc);
        if (state == JFileChooser.APPROVE_OPTION) {
            ruta = fc.getSelectedFile().getAbsolutePath();
            pantalla.getTxtRuta().setText(ruta);
        }
    }

    private void fillCmbBBDD() throws ServiceException {
        BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);

        List<BBDD> bbdds = bbddService.consultaBBDDModelo(modelo.getCodigoProyecto(), subModelo.getCodigoSubProyecto());
        BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(bbdds);
        pantalla.getCmbBBDD().setModel(modelBBDD);

        // Pone la base de datos por defecto
        String baseDatos = modelo.getNombreBbdd();
        if (StringUtils.isNotBlank(baseDatos)) {
            modelBBDD = (BBDDComboBoxModel) pantalla.getCmbBBDD().getModel();
            for (int i = 0; i < modelBBDD.getSize(); i++) {
                BBDD bbdd = modelBBDD.getElementAt(i);
                if (bbdd.getNombreBBDD().equals(baseDatos)) {
                    pantalla.getCmbBBDD().setSelectedItem(bbdd);
                }
            }
        }
    }

    private void renameGeneratedScripts(String ruta, List<Script> scripts) throws ServiceException {
        if (scripts == null || scripts.isEmpty()) {
            return;
        }
        String separator = File.separator;
        String rutaScripts = ruta;
        if (!rutaScripts.endsWith(separator)) {
            rutaScripts = rutaScripts.concat(separator);
        }
        try {
            String sufijoRechazo = ConfigurationSingleton.getInstance().getConfig("SufijoRechazoProcesado");
            // Renombramos los ficheros ya procesados
            for (Script script : scripts) {
                renameFile(rutaScripts, sufijoRechazo, script.getNombreScript());
                renameFile(rutaScripts, sufijoRechazo, script.getNombreScriptLanza());
                renameFile(rutaScripts, sufijoRechazo, script.getNombreScriptLog());
            }
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    private void renameFile(String ruta, String sufijoRechazo, String oldName) throws ServiceException {
        File oldFile = Paths.get(ruta.concat(oldName)).toFile();
        if (oldFile.exists()) {
            String newName = oldName + "_" + sufijoRechazo ;
            File newFile = Paths.get(ruta.concat(newName)).toFile();
            if (!oldFile.renameTo(newFile)) {
                throw new ServiceException(String.format("Error al renombrar el fichero \"%s\" como \"%s\"", oldName, newName));
            }
        }
    }
}
