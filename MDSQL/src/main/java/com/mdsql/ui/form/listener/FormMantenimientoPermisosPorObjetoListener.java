/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.PermisoObjeto;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SinonimoObjeto;
import com.mdsql.bussiness.service.PermisosObjetoService;
import com.mdsql.ui.form.FormMantenimientoPermisosPorObjeto;
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
public class FormMantenimientoPermisosPorObjetoListener extends ListenerSupportModeloPermiso implements ActionListener {

    protected FormMantenimientoPermisosPorObjeto pantalla;
    private String mcaAlta = MDSQLConstants.S;

    public FormMantenimientoPermisosPorObjetoListener(FormMantenimientoPermisosPorObjeto pantalla) {
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
            PermisosObjetoService permisosObjetoService = (PermisosObjetoService) getService(MDSQLConstants.PERMISOS_OBJETO_SERVICE);
            ServiceException warnings = null;
            if (pantalla.getCmbPermisoSinonimo().getSelectedIndex() == COMBOBOX_PERMISOSINONIMO_PERMISO) { // Permiso
                PermisoObjeto permiso = formToPermisoObjeto();
                warnings = permisosObjetoService.guardarPermisoObjeto(permiso, mcaAlta);

            } else if (pantalla.getCmbPermisoSinonimo().getSelectedIndex() == COMBOBOX_PERMISOSINONIMO_SINONIMO) {  //Sinonimo
                SinonimoObjeto sinonimo = formToSinonimoObjeto();
                warnings = permisosObjetoService.guardarSinonimoObjeto(sinonimo, mcaAlta);
            }

            if (warnings != null) {
                MDSQLUIHelper.showWarnings(pantalla, warnings);
            }
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_DATA_CHANGED, Boolean.TRUE);
            // Si estamos modificando, cerramos la pantalla, indicando que ha salido presionando [Guardar]
            if (mcaAlta.equals(MDSQLConstants.N)) {
                pantalla.dispose();
            }
        } catch (IOException | ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * A partir de los datos de pantalla rellena un objeto de tipo PermisoObjeto
     *
     * @return PermisoObjeto
     */
    public PermisoObjeto formToPermisoObjeto() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        Grant receptorPermisos = (Grant) pantalla.getCmbReceptorPermisos().getSelectedItem();
        String codUsrGrant = (receptorPermisos != null) ? receptorPermisos.getCodGrant() : null;
        String desEntorno = (String) pantalla.getCmbEntorno().getSelectedItem();
        String tipoObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
        String mcaHabilitado = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
        String codPeticion = pantalla.getTxtPeticion().getText();
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String codUsr = session.getCodUsr();
        String valGrant = (String) pantalla.getCmbPermiso().getSelectedItem();
        String mcaGrantOption = (String) pantalla.getCmbWithGrantOption().getSelectedItem();
        String mcaPdc = (String) pantalla.getCmbIncluirPDC().getSelectedItem();
        String nombreObjeto = pantalla.getTxtNombreObjeto().getText();
        return PermisoObjeto.builder()
                .codigoProyecto(codProyecto)
                .codUsrGrant(codUsrGrant)
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
     * A partir de los datos de pantalla devuelve un objeto de tipo
     * SinonimoObjeto
     *
     * @return Sinonimo
     */
    public SinonimoObjeto formToSinonimoObjeto() {
        PermisoObjeto permiso = formToPermisoObjeto();
        String codigoProyecto = permiso.getCodigoProyecto();
        String codUsrGrant = permiso.getCodUsrGrant();
        String nombreObjeto = permiso.getNomObjeto();
        String codOwnerSyn = getValueCmbPropietarioSinonimo();
        String desEntorno = permiso.getDesEntorno();
        String tipObjeto = permiso.getTipObjeto();
        String valReglaSyn = pantalla.getTxtFuncionNombre().getText();
        String mcaPdc = (String) pantalla.getCmbIncluirPDC().getSelectedItem();
        String mcaHabilitado = getValueChkHabilitada();
        String codPeticion = permiso.getCodPeticion();
        String codUsr = permiso.getCodUsr();

        return SinonimoObjeto.builder()
                .codigoProyecto(codigoProyecto)
                .codUsrGrant(codUsrGrant)
                .nomObjeto(nombreObjeto)
                .codOwnerSyn(codOwnerSyn)
                .desEntorno(desEntorno)
                .tipObjeto(tipObjeto)
                .valReglaSyn(valReglaSyn)
                .mcaPdc(mcaPdc)
                .mcaHabilitado(mcaHabilitado)
                .codPeticion(codPeticion)
                .codUsr(codUsr)
                .build();
    }

    /**
     * Dado un PermisoObjeto carga los datos en los campos de la pantalla
     *
     * @param permiso
     */
    public void permisoObjetoToForm(PermisoObjeto permiso) {
        if (permiso == null) {
            return;
        }
        clearForm();
        pantalla.getCmbPermisoSinonimo().setSelectedIndex(1);
        pantalla.getCmbEntorno().setSelectedItem(permiso.getDesEntorno());
        pantalla.getTxtNombreObjeto().setText(permiso.getNomObjeto());

        pantalla.getCmbTipoObjeto().setSelectedItem(permiso.getTipObjeto());
        //Forzar a que se rellene el combo de Permisos
        pantalla.getCmbTipoObjeto().dispatchEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        pantalla.getTxtPeticion().setText(permiso.getCodPeticion());
        pantalla.getCmbPermiso().setSelectedItem(permiso.getValGrant());

        setValueSiNoComboBox(pantalla.getCmbWithGrantOption(),permiso.getMcaGrantOption());
        setValueSiNoComboBox(pantalla.getCmbIncluirPDC(),permiso.getMcaPdc());

        pantalla.getTxtUsuarioModificacion().setText(permiso.getCodUsr());
        pantalla.getTxtUsuarioAlta().setText(permiso.getCodUsrAlta());

        dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);

        String fecActu = dateInformeFormatter.dateToString(permiso.getFecActu());
        pantalla.getTxtFechaModificacion().setText(fecActu);

        String fecAlta = dateInformeFormatter.dateToString(permiso.getFecAlta());
        pantalla.getTxtFechaAlta().setText(fecAlta);

        setValueChkHabilitada(permiso.getMcaHabilitado());
        setValueCmbReceptorPermisos(permiso.getCodUsrGrant());
    }

    /**
     * Dado un permiso carga los datos en los campos de la pantalla
     *
     * @param sinonimo
     */
    public void sinonimoObjetoToForm(SinonimoObjeto sinonimo) {
        if (sinonimo == null) {
            return;
        }
        clearForm();
        pantalla.getCmbPermisoSinonimo().setSelectedIndex(2);

        pantalla.getCmbEntorno().setSelectedItem(sinonimo.getDesEntorno());
        pantalla.getCmbTipoObjeto().setSelectedItem(sinonimo.getTipObjeto());
        pantalla.getTxtNombreObjeto().setText(sinonimo.getNomObjeto());
        pantalla.getTxtPeticion().setText(sinonimo.getCodPeticion());
        
        setValueSiNoComboBox(pantalla.getCmbIncluirPDC(),sinonimo.getMcaPdc());
        
        pantalla.getTxtFuncionNombre().setText(sinonimo.getValReglaSyn());

        setValueChkHabilitada(sinonimo.getMcaHabilitado());
        setValueCmbPropietarioSinonimo(sinonimo.getCodOwnerSyn());
        setValueCmbReceptorPermisos(sinonimo.getCodUsrGrant());

        dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);

        String fecActu = dateInformeFormatter.dateToString(sinonimo.getFecActu());
        pantalla.getTxtFechaModificacion().setText(fecActu);

        String fecAlta = dateInformeFormatter.dateToString(sinonimo.getFecActu());
        pantalla.getTxtFechaAlta().setText(fecAlta);

        pantalla.getTxtUsuarioModificacion().setText(sinonimo.getCodUsr());
        pantalla.getTxtUsuarioAlta().setText(sinonimo.getCodUsrAlta());
    }

    @Override
    public void onLoad() {
        //tratamiento del parámetro del modelo
        super.onLoad();
        //Tratamiento del resto de parámetros de entrada
        Object object = pantalla.getParams().get(MDSQLConstants.P_IN_OBJETO);
        //Comprobamos si se trata de una modificacion 
        if (object != null) {
            mcaAlta = MDSQLConstants.N;
            // Verificar si se intenta modificar un permiso 
            if (object instanceof PermisoObjeto) {
                permisoObjetoToForm((PermisoObjeto) object);
            } else {
                sinonimoObjetoToForm((SinonimoObjeto) object);
            }
            pantalla.getCmbPermisoSinonimo().setEnabled(false);
            MDSQLUIHelper.setReadOnlyText(pantalla.getTxtNombreObjeto());
        } else {
            pantalla.getChkHabilitada().setSelected(true);
        }
    }
}
