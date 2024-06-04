/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaPermisosPersonalizados;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.PermisosPersonalizadosService;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.form.FormConfirmacionGeneracionPermisos;
import com.mdsql.ui.form.FormGenerarPermisosPersonalizados;
import com.mdsql.ui.model.PermisosColumnaTableModel;
import com.mdsql.ui.model.SinonimosObjetoTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormGenerarPermisosPersonalizadosListener extends ListenerSupportModeloPermiso implements ActionListener, ListSelectionListener{

    private final FormGenerarPermisosPersonalizados pantalla;

    public FormGenerarPermisosPersonalizadosListener(FormGenerarPermisosPersonalizados pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getBtnGenerar())) {
            evtBtnGenerar();
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtBtnBuscar() {
        try {
            PermisosPersonalizadosService permisosPersonalizadosService = (PermisosPersonalizadosService) getService(MDSQLConstants.PERMISOS_PERSONALIZADOS_SERVICE);
            String p_cod_proyecto = pantalla.getTxtModeloProyecto().getText();
            String p_cod_sub_proy = getValueCmbSubModelo(); 
            String p_nom_objeto = pantalla.getTxtNombreObjeto().getText();
            String p_tip_objeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
            String p_cod_peticion = pantalla.getTxtPeticion().getText();

            OutputConsultaPermisosPersonalizados output; 
            output = permisosPersonalizadosService.consultaPermisoSinonimo(p_cod_proyecto, p_cod_sub_proy, p_nom_objeto, p_tip_objeto, p_cod_peticion);
            
            PermisosColumnaTableModel permisosTableModel = (PermisosColumnaTableModel) pantalla.getTblPermisos().getModel();
            permisosTableModel.clearData();
            permisosTableModel.setData(output.getPermisosColumna());    
            
            SinonimosObjetoTableModel sinonimosTableModel = (SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel();
            sinonimosTableModel.clearData();
            sinonimosTableModel.setData(output.getSinonimosObjeto()); 
            
            boolean tablesWithData = !(output.getPermisosColumna().isEmpty() && output.getSinonimosObjeto().isEmpty());
            pantalla.getBtnGenerar().setEnabled(tablesWithData);
            
            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }        
    }

    private void evtBtnGenerar() {
        Modelo modelo = pantalla.getModelo();
        SubProyecto subModelo = (SubProyecto) dialogSupportModelo.getCmbSubModelo().getSelectedItem();
        String nomObjeto = pantalla.getTxtNombreObjeto().getText();
        String tipObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
        String codPeticion = pantalla.getTxtPeticion().getText();
        Map<String, Object> params = new HashMap<>();

        params.put(MDSQLConstants.P_IN_MODELO, modelo);
        params.put(MDSQLConstants.P_IN_NOM_OBJETO, nomObjeto);
        params.put(MDSQLConstants.P_IN_SUB_MODELO, subModelo);
        params.put(MDSQLConstants.P_IN_TIP_OBJETO, tipObjeto);
        params.put(MDSQLConstants.P_IN_COD_PETICION, codPeticion);

        FormConfirmacionGeneracionPermisos formConfirmacionGeneracionPermisos;
        formConfirmacionGeneracionPermisos= MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormConfirmacionGeneracionPermisos.class, params);

        String result = (String) formConfirmacionGeneracionPermisos.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (result != null && result.equals(MDSQLConstants.BTN_ACEPTAR)){
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
	    Proceso proceso = session.getProceso();
            params.put("proceso", proceso);
	    params.put("entregar", Boolean.FALSE);
            MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaResumenProcesado.class, new HashMap<>());
        }
    }
    
    
    @Override
    public void clearForm() {
        super.clearForm();
        pantalla.getTxtNombreObjeto().setText("");
        pantalla.getBtnGenerar().setEnabled(false);
        ((PermisosColumnaTableModel) pantalla.getTblPermisos().getModel()).clearData();
        ((SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel()).clearData();
        pantalla.getBtnGenerar().setEnabled(false);
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
            //Habilitar botones
            pantalla.getBtnGenerar().setEnabled(true);
        }
    }    
}
