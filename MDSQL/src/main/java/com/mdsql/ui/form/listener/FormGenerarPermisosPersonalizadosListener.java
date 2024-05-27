/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaPermisosPersonalizados;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.PermisosPersonalizadosService;
import com.mdsql.ui.form.FormConfirmacionGeneracionPermisos;
import com.mdsql.ui.form.FormGenerarPermisosPersonalizados;
import com.mdsql.ui.model.PermisosColumnaTableModel;
import com.mdsql.ui.model.SinonimosObjetoTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormGenerarPermisosPersonalizadosListener extends ListenerSupportModeloPermiso implements ActionListener {

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
        } else if (obj.equals(pantalla.getBtnLimpiar())) {
            clearForm();
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
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormConfirmacionGeneracionPermisos.class, params);
    }
    
    
    @Override
    public void clearForm() {
        super.clearForm();
        pantalla.getTxtNombreObjeto().setText("");
        pantalla.getBtnGenerar().setEnabled(false);
        ((PermisosColumnaTableModel) pantalla.getTblPermisos().getModel()).clearData();
        ((SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel()).clearData();
    }    
}
