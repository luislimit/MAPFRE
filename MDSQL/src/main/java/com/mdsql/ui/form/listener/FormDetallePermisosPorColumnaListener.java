/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaPermisosColumna;
import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.PermisosColumnaService;
import com.mdsql.ui.form.FormDetallePermisosPorColumna;
import com.mdsql.ui.form.FormMantenimientoPermisosPorColumna;
import com.mdsql.ui.model.PermisosColumnaTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormDetallePermisosPorColumnaListener extends ListenerSupportModeloPermiso implements ActionListener, ListSelectionListener {

    protected FormDetallePermisosPorColumna pantalla;

    public FormDetallePermisosPorColumnaListener(FormDetallePermisosPorColumna pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getBtnLimpiar())) {
            evtBtnLimpiar();
        } else if (obj.equals(pantalla.getBtnInforme())) {
            evtBtnInforme();
        } else if (obj.equals(pantalla.getBtnModificacion())) {
            evtBtnModificacion();
        } else if (obj.equals(pantalla.getBtnAlta())) {
            evtBtnAlta();
        }
    }

    private void evtBtnBuscar() {
        fillTblPermisos();
    }

    private void evtBtnLimpiar() {
        clearForm();
    }

    @Override
    public void clearForm() {
        super.clearForm();
        pantalla.getTxtColumna().setText("");
        pantalla.getTxtTabla().setText("");
        pantalla.getBtnInforme().setEnabled(false);
        pantalla.getBtnModificacion().setEnabled(false);
        ((PermisosColumnaTableModel) pantalla.getTblPermisos().getModel()).clearData();
    }

    private void evtBtnInforme() {
        try {
            String proyecto = pantalla.getTxtModeloProyecto().getText();
            ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();

            String sufijoPermisos = configuration.getConfig("SufijoExcelPermisosColumnas");
            excelGeneratorService.generarExcelPermisos(pantalla.getTblPermisos(), sufijoPermisos, proyecto);

        } catch (IOException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    private void evtBtnModificacion() {
        int idx = pantalla.getTblPermisos().getSelectedRow();
        if (idx >= 0) {
            PermisosColumnaTableModel tableModel = (PermisosColumnaTableModel) pantalla.getTblPermisos().getModel();
            PermisoColumna permisoColumna = tableModel.getSelectedRow(idx);
            showFormMantenimiento(permisoColumna);
        }
    }

    private void evtBtnAlta() {
        showFormMantenimiento(null);
    }

    private void showFormMantenimiento(PermisoColumna permisoColumna) {
        Modelo modelo = pantalla.getModelo();
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_MODELO, modelo);
        if (permisoColumna != null) {
            params.put(MDSQLConstants.P_IN_OBJETO, permisoColumna);
        }
        FormMantenimientoPermisosPorColumna dialog = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormMantenimientoPermisosPorColumna.class, params);
        Boolean dataChanged = (Boolean) dialog.getReturnParams().get(MDSQLConstants.P_OUT_DATA_CHANGED);
        if (dataChanged != null && dataChanged) {
            clearForm();
            //Se han guardado cambios se debe reconsultar la tabla
            fillTblPermisos();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        pantalla.getBtnInforme().setEnabled(false);
        pantalla.getBtnModificacion().setEnabled(false);
        super.fillCmbPermiso("COLUMNA"); // 
        fillTblPermisos();
    }

    public void fillTblPermisos() {
        try {
            PermisosColumnaService permisosColumnaService = (PermisosColumnaService) getService(MDSQLConstants.PERMISOS_COLUMNA_SERVICE);
            String p_cod_proyecto = pantalla.getTxtModeloProyecto().getText();
            String p_nom_objeto = pantalla.getTxtTabla().getText();
            String p_des_entorno = (String) pantalla.getCmbEntorno().getSelectedItem();

            String p_nom_columna = pantalla.getTxtColumna().getText(); // 
            System.out.println("(String) pantalla.getCmbPermiso().getSelectedItem()=" + (String) pantalla.getCmbPermiso().getSelectedItem());
            String p_val_grant = (String) pantalla.getCmbPermiso().getSelectedItem(); // Permiso

            // Receptor permisos 
            Grant grant = ((Grant) pantalla.getCmbReceptorPermisos().getSelectedItem());
            String p_cod_usr_grant = null;
            if (grant != null) {
                p_cod_usr_grant = grant.getCodGrant();
            }

            String p_mca_pdc = (String) pantalla.getCmbIncluirPDC().getSelectedItem(); // Incluir en PDC
            String p_mca_grant_option = (String) pantalla.getCmbWithGrantOption().getSelectedItem();
            String p_mca_habilitado = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());

            OutputConsultaPermisosColumna output
                    = permisosColumnaService.consultaPermisos(p_cod_proyecto,
                            p_nom_objeto,
                            p_nom_columna,
                            p_des_entorno,
                            p_val_grant,
                            p_cod_usr_grant,
                            p_mca_grant_option,
                            p_mca_pdc,
                            p_mca_habilitado);

            PermisosColumnaTableModel permisosTableModel = (PermisosColumnaTableModel) pantalla.getTblPermisos().getModel();
            permisosTableModel.clearData();
            permisosTableModel.setData(output.getPermisosColumna());

            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());

            // Si hay datos en una de las dos tablas, habilitamos el botón de informe
            boolean hayDatosParaInforme = (output.getPermisosColumna() != null && !output.getPermisosColumna().isEmpty());
            pantalla.getBtnInforme().setEnabled(hayDatosParaInforme);
            //No hay elemento seleccionado, no se puede modificar
            pantalla.getBtnModificacion().setEnabled(false);
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        pantalla.getBtnModificacion().setEnabled(true);
    }

}
