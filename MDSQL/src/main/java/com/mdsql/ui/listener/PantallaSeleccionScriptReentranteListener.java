/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ReentranteInfo;
import com.mdsql.bussiness.entities.ReentranteParametro;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.ReentranteService;
import com.mdsql.ui.PantallaSeleccionScriptReentrante;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.ParametroScriptReentranteTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;
import java.util.Objects;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.lang3.StringUtils;

public class PantallaSeleccionScriptReentranteListener extends ListenerSupport implements ActionListener, OnLoadListener, ItemListener, ListSelectionListener {

    private final PantallaSeleccionScriptReentrante pantalla;
    private ReentranteService reentranteService;
    private Session session;

    public PantallaSeleccionScriptReentranteListener(PantallaSeleccionScriptReentrante pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        if (pantalla.getBtnCancelar().equals(object)) {
            evtBtnCancelar();
        } else if (pantalla.getBtnAceptar().equals(object)) {
            evtBtnAceptar();
        } else if (pantalla.getBtnLimpiar().equals(object)) {
            evtBtnLimpiar();
        } else if (pantalla.getBtnScriptCambio().equals(object)) {
            evtSeleccionaFichero(pantalla.getTxtScriptCambio());
        } else if (pantalla.getBtnScriptComentarios().equals(object)) {
            evtSeleccionaFichero(pantalla.getTxtScriptComentarios());
        } else if (pantalla.getBtnScriptCreacion().equals(object)) {
            evtSeleccionaFichero(pantalla.getTxtScriptCreacion());
        } else if (pantalla.getBtnRutaSalida().equals(object)) {
            MDSQLUIHelper.seleccionarRuta(pantalla.getTxtRutaSalida());
        }
    }

    @Override
    public void onLoad() {
        try {
            reentranteService = (ReentranteService) getService(MDSQLConstants.REENTRANTE_SERVICE);

            /*BigDecimal idProceso = (BigDecimal) pantalla.getParams().get("proceso");
            BigDecimal numeroOrden = (BigDecimal) pantalla.getParams().get("numeroOrden");*/
            OutputConsulta<CodigoDescripcion> output;
            output = reentranteService.consultaTipoReentrante();
            CodigoDescripcionComboBoxModel modelTipoOperacion = new CodigoDescripcionComboBoxModel(output.getLista());
            pantalla.getCmbTipoOperacion().setModel(modelTipoOperacion);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            //Inicializar pantalla con los datos de la sesión
            iniciaPantalla();

        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    private void evtBtnCancelar() {
        pantalla.dispose();
    }

    private void evtBtnAceptar() {
        try {
            String scriptCambio = pantalla.getTxtScriptCambio().getText();
            String scriptCreacion = pantalla.getTxtScriptCreacion().getText();
            String scriptComentarios = pantalla.getTxtScriptComentarios().getText();
            String rutaSalida = pantalla.getTxtRutaSalida().getText();

            if (StringUtils.isEmpty(scriptCambio)
                    && StringUtils.isEmpty(scriptCreacion)
                    && StringUtils.isEmpty(scriptComentarios)
                    && StringUtils.isEmpty(rutaSalida)) {
                MDSQLUIHelper.showMessage(pantalla, "error.debe_seleccionar_script_ruta");
                return;
            }

            CodigoDescripcion tipoOperacion = (CodigoDescripcion) pantalla.getCmbTipoOperacion().getSelectedItem();
            if (tipoOperacion == null || tipoOperacion.getCodigo().isEmpty()) {
                MDSQLUIHelper.showMessage(pantalla, "error.debe_seleccionar_tipo_operacion");
                return;
            }

            String nombreTabla = pantalla.getTxtNombreTabla().getText();

            ParametroScriptReentranteTableModel tableModel = (ParametroScriptReentranteTableModel) pantalla.getTblMantenimientoParametro().getModel();
            List<ReentranteParametro> parametros = tableModel.getData();

            ReentranteInfo reentranteInfo = ReentranteInfo.builder().
                    scriptCambio(scriptCambio).
                    scriptComentarios(scriptComentarios).
                    scriptCreacion(scriptCreacion).
                    tipoOperacion(tipoOperacion.getCodigo()).
                    nombreTabla(nombreTabla).
                    parametros(parametros).
                    ruta(rutaSalida).
                    build();

            OutputWarning output = reentranteService.validaParamOperacion(reentranteInfo);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            session.setReentranteInfo(reentranteInfo);
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_ACEPTAR);
            pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtSeleccionaFichero(JTextField txtFilename) {
        MDSQLUIHelper.abrirScript(pantalla, txtFilename);
        // Establecer la ruta por defecto
        String scriptCambio = pantalla.getTxtScriptCambio().getText();
        String scriptCreacion = pantalla.getTxtScriptCreacion().getText();
        String scriptComentarios = pantalla.getTxtScriptComentarios().getText();

        String[] fileNames = {scriptCambio, scriptCreacion, scriptComentarios};
        for (String fileName : fileNames) {
            if (fileName != null && !fileName.isEmpty()) {
                File file = new File(fileName);
                pantalla.getTxtRutaSalida().setText(file.getParent());
                return;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Al seleccionar un elemento del comboBox de Tipos de Operación
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }
        CodigoDescripcion selected = (CodigoDescripcion) e.getItem();

        if (!Objects.isNull(selected) && !selected.getCodigo().isEmpty()) {
            try {
                pantalla.getTxtDescripcion().setText(selected.getDescripcion());
                //Colocar el cursos al inicio del texto para que no aparezca el texto desplazado
                pantalla.getTxtDescripcion().setCaretPosition(0);

                //Buscamos los parámetros asociados al tipo de Operacion
                OutputConsulta<ReentranteParametro> output = reentranteService.consultaParametroReentrante(selected.getCodigo());
                //Cargamos los parámetros en la tabla
                fillTabla(output.getLista());
                //Mostrar avisos
                MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            } catch (ServiceException ex) {
                MDSQLUIHelper.showErrors(pantalla, ex);
            }
        } else {
            fillTabla(null);
        }
    }

    private void fillTabla(List<ReentranteParametro> parametros) {
        pantalla.getTxtAyuda().setText("");
        //Cargamos los datos en la tabla
        ParametroScriptReentranteTableModel tableModel = (ParametroScriptReentranteTableModel) pantalla
                .getTblMantenimientoParametro().getModel();
        tableModel.setData(parametros);
        tableModel.fireTableDataChanged();
    }

    /**
     * Al seleccionar un elemento de la tabla de parametros
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        ReentranteParametro parametro = (ReentranteParametro) MDSQLUIHelper.getSelectedTableObject(pantalla.getTblMantenimientoParametro());
        if (!Objects.isNull(parametro)) {
            pantalla.getTxtAyuda().setText(parametro.getTextoParam());
            //Colocar el cursos al inicio del texto para que no aparezca el texto desplazado
            pantalla.getTxtAyuda().setCaretPosition(0);
        }
    }

    private void iniciaPantalla() {
        //Mostrar información de reentrante si estaba definida previamente
        ReentranteInfo reentranteInfo = session.getReentranteInfo();
        if (reentranteInfo != null) {
            pantalla.getTxtScriptCambio().setText(reentranteInfo.getScriptCambio());
            pantalla.getTxtScriptCreacion().setText(reentranteInfo.getScriptCreacion());
            pantalla.getTxtScriptComentarios().setText(reentranteInfo.getScriptComentarios());
            pantalla.getTxtRutaSalida().setText(reentranteInfo.getRuta());

            CodigoDescripcion tipoOperacion = CodigoDescripcion.builder().codigo(reentranteInfo.getTipoOperacion()).build();
            pantalla.getCmbTipoOperacion().setSelectedItem(tipoOperacion);

            pantalla.getTxtNombreTabla().setText(reentranteInfo.getNombreTabla());
            fillTabla(reentranteInfo.getParametros());
        }
        // si ya se ha procesado, se abre en sólo lectura
        if (session.getProceso() != null) {
            pantalla.getCmbTipoOperacion().setEnabled(false);
            MDSQLUIHelper.setReadOnlyText(pantalla.getTxtNombreTabla());
            pantalla.getBtnAceptar().setEnabled(false);
            pantalla.getBtnCancelar().requestFocus();
            pantalla.getBtnScriptComentarios().setEnabled(false);
            pantalla.getBtnScriptCreacion().setEnabled(false);
            pantalla.getBtnScriptCambio().setEnabled(false);
        }
    }

    private void evtBtnLimpiar() {
        //Limpiar toda la pantalla
        pantalla.getTxtScriptCambio().setText("");
        pantalla.getTxtScriptCreacion().setText("");
        pantalla.getTxtScriptComentarios().setText("");
        pantalla.getTxtRutaSalida().setText("");
        pantalla.getCmbTipoOperacion().setSelectedIndex(-1);
        pantalla.getTxtDescripcion().setText("");
        pantalla.getTxtNombreTabla().setText("");
        fillTabla(null);
        pantalla.repaint();
    }

}
