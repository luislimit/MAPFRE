/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.OutputConsultaPermisosColumna;
import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.PermisosColumnaService;
import com.mdsql.ui.form.FormMantenimientoPermisosPorColumna;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormMantenimientoPermisosPorColumnaListener extends ListenerSupportModeloPermiso implements ActionListener {

    protected FormMantenimientoPermisosPorColumna pantalla;
    private String mcaAlta = MDSQLConstants.S;
    private static final String TIPO_OBJETO = "COLUMNA";

    public FormMantenimientoPermisosPorColumnaListener(FormMantenimientoPermisosPorColumna pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnGuardar())) {
            evtBtnGuardar();
        }
    }

    private void evtBtnGuardar() {
        try {
            if (!MDSQLUIHelper.confirmAction(pantalla.getFrameParent(), "confirmacion.guardar")) {
                return;
            }            
            PermisosColumnaService permisosColumnaService = (PermisosColumnaService) getService(MDSQLConstants.PERMISOS_COLUMNA_SERVICE);
            PermisoColumna permisoColumna = formToPermisoColumna();
            OutputConsultaPermisosColumna output = permisosColumnaService.guardarPermiso(permisoColumna, mcaAlta);
            
            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());

            // Indicamos que han ocurrido cambios en la pantalla
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_DATA_CHANGED, Boolean.TRUE);
            if (mcaAlta.equals(MDSQLConstants.N)) {
                pantalla.dispose();
            }
            
        } catch (IOException | ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
    
    
    @Override
    public void onLoad() {
        super.onLoad();
        super.fillCmbPermiso(TIPO_OBJETO); // Pendiente de definir si ponemos una constante

        //Tratamiento del resto de parámetros de entrada
        PermisoColumna permisoColumna = (PermisoColumna) pantalla.getParams().get(MDSQLConstants.P_IN_OBJETO);

        //Comprobamos si se trata de una modificacion 
        if (permisoColumna != null) {
            mcaAlta = MDSQLConstants.N;
            permisoColumnaToForm(permisoColumna);
            MDSQLUIHelper.setReadOnlyText(pantalla.getTxtTabla());
            MDSQLUIHelper.setReadOnlyText(pantalla.getTxtColumna());
        } else {
            pantalla.getChkHabilitada().setSelected(true);
        }
    }

    /**
     * A partir de los datos de pantalla rellena un objeto de tipo
     * PermisoColumna
     *
     * @return PermisoColumna
     */
    public PermisoColumna formToPermisoColumna() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        Grant receptorPermisos = (Grant) pantalla.getCmbReceptorPermisos().getSelectedItem();
        String codUsrGrant = (receptorPermisos != null) ? receptorPermisos.getCodGrant() : null;
        String desEntorno = (String) pantalla.getCmbEntorno().getSelectedItem();
        String tipoObjeto = TIPO_OBJETO;
        String mcaHabilitado = getValueChkHabilitada();
        String codPeticion = pantalla.getTxtPeticion().getText();
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String codUsr = session.getCodUsr();
        String valGrant = (String) pantalla.getCmbPermiso().getSelectedItem();
        String mcaGrantOption = (String) pantalla.getCmbWithGrantOption().getSelectedItem();
        String mcaPdc = (String) pantalla.getCmbIncluirPDC().getSelectedItem();
        String nombreObjeto = pantalla.getTxtTabla().getText();
        String nombreColumna = pantalla.getTxtColumna().getText();
        return PermisoColumna.builder()
                .codigoProyecto(codProyecto)
                .codUsrGrant(codUsrGrant)
                .nomColumna(nombreColumna)
                .nomObjeto(nombreObjeto)
                .valGrant(valGrant)
                .desEntorno(desEntorno)
                .codPeticion(codPeticion)
                .tipObjeto(tipoObjeto)
                .mcaGrantOption(mcaGrantOption)
                .mcaPdc(mcaPdc)
                .mcaHabilitado(mcaHabilitado)
                .codPeticion(codPeticion)
                .codUsr(codUsr)
                .build();
    }

    /**
     * Dado un PermisoColumna carga los datos en los campos de la pantalla
     *
     * @param permiso
     */
    public void permisoColumnaToForm(PermisoColumna permiso) {
        if (permiso == null) {
            return;
        }
        pantalla.getCmbEntorno().setSelectedItem(permiso.getDesEntorno());
        pantalla.getTxtTabla().setText(permiso.getNomObjeto());
        pantalla.getTxtColumna().setText(permiso.getNomColumna());
        pantalla.getTxtPeticion().setText(permiso.getCodPeticion());
        pantalla.getCmbPermiso().setSelectedItem(permiso.getValGrant());
        setValueSiNoComboBox(pantalla.getCmbWithGrantOption(),permiso.getMcaGrantOption());
        setValueSiNoComboBox(pantalla.getCmbIncluirPDC(),permiso.getMcaPdc());
        
        pantalla.getTxtUsuario().setText(permiso.getCodUsrAlta());
        dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
        
        String fecAlta = dateInformeFormatter.dateToString(permiso.getFecAlta());
        pantalla.getTxtFecha().setText(fecAlta);
        
        setValueChkHabilitada(permiso.getMcaHabilitado());
        setValueCmbReceptorPermisos(permiso.getCodUsrGrant());
    }
}
