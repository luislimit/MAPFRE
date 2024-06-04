/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.OutputConsultaPermisosPersonalizados;
import com.mdsql.bussiness.service.PermisosPersonalizadosService;
import com.mdsql.ui.form.FormConsultaPermisosPersonalizados;
import com.mdsql.ui.model.PermisosColumnaTableModel;
import com.mdsql.ui.model.SinonimosObjetoTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormConsultaPermisosPersonalizadosListener extends ListenerSupportModeloPermiso implements ActionListener, ListSelectionListener {

    protected FormConsultaPermisosPersonalizados pantalla;

    public FormConsultaPermisosPersonalizadosListener(FormConsultaPermisosPersonalizados pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtBtnLimpiar() {
        clearForm();
    }

    @Override
    public void clearForm(){
        super.clearForm();
        pantalla.getTxtColumna().setText("");
        pantalla.getTxtNombreObjeto().setText("");
        pantalla.getTxtFechaDesde().setText("");
        pantalla.getTxtFechaHasta().setText("");
        ((PermisosColumnaTableModel) pantalla.getTblPermisos().getModel()).clearData();
        ((SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel()).clearData();        
    }
    
    private void evtBtnBuscar() {
        try {
            PermisosPersonalizadosService permisosPersonalizadosService = (PermisosPersonalizadosService) getService(MDSQLConstants.PERMISOS_PERSONALIZADOS_SERVICE);
            String p_cod_proyecto = pantalla.getTxtModeloProyecto().getText();
            String p_nom_objeto = pantalla.getTxtNombreObjeto().getText();
            String p_nom_columna = pantalla.getTxtColumna().getText();
            String p_txt_per_syn = (String) pantalla.getCmbPermisoSinonimo().getSelectedItem(); // Permiso/Sinonimo
            String p_des_entorno = (String) pantalla.getCmbEntorno().getSelectedItem();
            String p_cod_owner_syn = getValueCmbPropietarioSinonimo(); // Propietario sinonimo
            String p_val_regla_syn = pantalla.getTxtFuncionNombre().getText(); // Funcion nombre
            String p_val_grant = (String) pantalla.getCmbPermiso().getSelectedItem(); // Permiso
            String p_tip_objeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
            String p_cod_usr_grant = getValueCmbReceptorPermisos(); // Receptor permisos
            String p_cod_peticion = pantalla.getTxtPeticion().getText();
            String p_mca_grant_option = (String) pantalla.getCmbWithGrantOption().getSelectedItem();
            String p_mca_pdc = (String) pantalla.getCmbIncluirPDC().getSelectedItem();
            String p_mca_habilitado = getValueChkHabilitada();
            String p_cod_usr = pantalla.getTxtUsuarioModificacion().getText(); // Usuario de modificación
            String p_fec_desde = pantalla.getTxtFechaDesde().getText();
            String p_fec_hasta = pantalla.getTxtFechaHasta().getText();

            OutputConsultaPermisosPersonalizados output = permisosPersonalizadosService.consulta(
                    p_cod_proyecto,
                    p_nom_objeto,
                    p_nom_columna,
                    p_txt_per_syn, // Permiso/Sinonimo
                    p_des_entorno,
                    p_cod_owner_syn, // Propietario sinonimo
                    p_val_regla_syn, // Funcion nombre
                    p_val_grant, // Permiso
                    p_tip_objeto,
                    p_cod_usr_grant, // Receptor permisos
                    p_cod_peticion,
                    p_mca_grant_option,
                    p_mca_pdc,
                    p_mca_habilitado,
                    p_cod_usr, // Usuario de modificación
                    p_fec_desde,
                    p_fec_hasta);
            
            PermisosColumnaTableModel permisosTableModel = (PermisosColumnaTableModel) pantalla.getTblPermisos().getModel();
            permisosTableModel.clearData();
            permisosTableModel.setData(output.getPermisosColumna());    
            
            SinonimosObjetoTableModel sinonimosTableModel = (SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel();
            sinonimosTableModel.clearData();
            sinonimosTableModel.setData(output.getSinonimosObjeto());    
            
            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());

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

        JTable tblSinonimos = pantalla.getTblSinonimos();
        JTable tblPermisos = pantalla.getTblPermisos();
        //
        if (lsm.equals(tblPermisos.getSelectionModel())) {
            evtSeleccionTabla(tblPermisos, tblSinonimos);
        } else if (lsm.equals(tblSinonimos.getSelectionModel())) {
            evtSeleccionTabla(tblSinonimos, tblPermisos);
        }
    }

    private void evtSeleccionTabla(JTable selected, JTable unSelected) {
        int row = selected.getSelectedRow();
        if (row >= 0) {
            // Desmarcamos la tabla
            unSelected.clearSelection();
        }
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        setDefWithGrantOption(COMBOBOX_SINVALOR);
        setDefIncluirPDC(COMBOBOX_SINVALOR);
        setDefHabilitada(true);
        clearForm();
    }    
}
