/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Permiso;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.Sinonimo;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.ui.form.FormPermisosGeneralesPorModeloPorTipoObjeto;
import com.mdsql.ui.model.PermisosTableModel;
import com.mdsql.ui.model.SinonimosTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormPermisosGeneralesPorModeloPorTipoObjetoListener extends ListenerSupportModeloPermiso implements ActionListener, ListSelectionListener {

    protected FormPermisosGeneralesPorModeloPorTipoObjeto pantalla;

    public FormPermisosGeneralesPorModeloPorTipoObjetoListener(FormPermisosGeneralesPorModeloPorTipoObjeto pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnGuardar())) {
            evtBtnGuardar();
        } else if (obj.equals(pantalla.getBtnInforme())) {
            evtBtnInforme();
        } else if (obj.equals(pantalla.getBtnLimpiar())) {
            evtBtnLimpiar();            
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtBtnGuardar() {
        int opcion = pantalla.getCmbPermisoSinonimo().getSelectedIndex();
        try {
            if (opcion < 1 || !MDSQLUIHelper.confirmAction(pantalla.getFrameParent(), "confirmacion.guardar")) { // opcion: 1-Permiso ; 2-Sinonimo
                return;
            }
            PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);

            if (opcion == COMBOBOX_PERMISOSINONIMO_PERMISO) { // Permiso
                Permiso permiso = FormToPermiso();
                String codProyecto = permiso.getCodigoProyecto();
                String codUsrGrant = permiso.getCodUsrGrant();
                String valGrant = permiso.getValGrant();
                String desEntorno = permiso.getDesEntorno();
                String tipoObjeto = permiso.getTipObjeto();
                String mcaGrantOption = permiso.getMcaGrantOption();
                String mcaIncluirPDC = permiso.getMcaPdc();
                String mcaHabilitado = permiso.getMcaHabilitado();
                String codPeticion = permiso.getCodPeticion();
                String codUsr = permiso.getCodUsr();
                //
                permisosService.guardarPermiso(codProyecto, codUsrGrant, valGrant, desEntorno, tipoObjeto, mcaGrantOption, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);
                fillTblPermisos();
            } else if (opcion == COMBOBOX_PERMISOSINONIMO_SINONIMO) { //Sinonimo
                Sinonimo sinonimo = formToSinonimo();
                String codProyecto = sinonimo.getCodigoProyecto();
                String codUsrGrant = sinonimo.getCodUsrGrant();
                String codOwnerSyn = sinonimo.getCodOwnerSyn();
                String desEntorno = sinonimo.getDesEntorno();
                String tipoObjeto = sinonimo.getTipObjeto();
                String funcionNombre = sinonimo.getValReglaSyn();
                String mcaIncluirPDC = sinonimo.getMcaPdc();
                String mcaHabilitado = sinonimo.getMcaHabilitado();
                String codPeticion = sinonimo.getCodPeticion();
                String codUsr = sinonimo.getCodUsr();
                //
                permisosService.guardarSinonimo(codProyecto, codUsrGrant, codOwnerSyn, desEntorno, tipoObjeto, funcionNombre, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);
                fillTblSinonimos();
            }
            clearForm();
        } catch (IOException | ServiceException | ParseException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    private void evtBtnInforme() {
        try {
            String proyecto = pantalla.getTxtModeloProyecto().getText();
            ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();

            String sufijoPermisos = configuration.getConfig("SufijoExcelPermisosGenerales");
            excelGeneratorService.generarExcelPermisos(pantalla.getTblPermisos(), sufijoPermisos, proyecto);

            String sufijoSinonimos = configuration.getConfig("SufijoExcelSinonimosGenerales");
            excelGeneratorService.generarExcelSinonimos(pantalla.getTblSinonimos(), sufijoSinonimos, proyecto);
        } catch (IOException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    public void fillTblSinonimos() {
        try {
            Modelo modelo = pantalla.getModelo();
            PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
            List<Sinonimo> sinonimos = permisosService.consultaSinonimosGenerales(modelo);
            if (CollectionUtils.isNotEmpty(sinonimos)) {
                SinonimosTableModel tableModel = (SinonimosTableModel) pantalla.getTblSinonimos().getModel();
                tableModel.clearData();
                tableModel.setData(sinonimos);
            }
        } catch (ServiceException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    public void fillTblPermisos() {
        try {
            Modelo modelo = pantalla.getModelo();
            PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
            List<Permiso> permisos = permisosService.consultaPermisosGenerales(modelo);
            if (CollectionUtils.isNotEmpty(permisos)) {
                // Obtiene el modelo y lo actualiza
                PermisosTableModel tableModel = (PermisosTableModel) pantalla.getTblPermisos().getModel();
                tableModel.clearData();
                tableModel.setData(permisos);
            }
        } catch (ServiceException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        fillTblPermisos();
        fillTblSinonimos();
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
            Permiso permiso = (Permiso) evtSeleccionTabla(tblPermisos, tblSinonimos);
            PermisoToForm(permiso);
            //pantalla.getCmbPermisoSinonimo().setEnabled(false);
        } else if (lsm.equals(tblSinonimos.getSelectionModel())) {
            Sinonimo sinonimo = (Sinonimo) evtSeleccionTabla(tblSinonimos, tblPermisos);
            SinonimoToForm(sinonimo);
            //pantalla.getCmbPermisoSinonimo().setEnabled(false);
        }
    }

    private Object evtSeleccionTabla(JTable selected, JTable unSelected) {
        Object seleccionado = null;
        int row = selected.getSelectedRow();
        if (row >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) selected.getModel();
            seleccionado = tableModel.getData().get(row);
            // Desmarcamos la tabla
            unSelected.clearSelection();
        }
        return seleccionado;
    }

    /**
     * A partir de los datos de pantalla rellena un objeto de tipo permiso
     *
     * @return Permiso
     */
    private Permiso FormToPermiso() throws ParseException {
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

        String codUsrAlta = pantalla.getTxtUsuarioAlta().getText();

        dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
        Date fecActu = dateInformeFormatter.stringToDate(pantalla.getTxtFechaModificacion().getText());
        Date fecAlta = dateInformeFormatter.stringToDate(pantalla.getTxtFechaAlta().getText());

        return Permiso.builder()
                .codigoProyecto(codProyecto)
                .codUsrGrant(codUsrGrant)
                .valGrant(valGrant)
                .desEntorno(desEntorno)
                .codPeticion(codPeticion)
                .tipObjeto(tipoObjeto)
                .mcaGrantOption(mcaGrantOption)
                .mcaPdc(mcaPdc)
                .mcaHabilitado(mcaHabilitado)
                .codPeticion(codPeticion)
                .codUsr(codUsr)
                .fecAlta(fecAlta)
                .codUsrAlta(codUsrAlta)
                .fecActu(fecActu)
                .build();
    }

    /**
     * A partir de los datos de pantalla devuelve un objeto de tipo sinonimo
     *
     * @return Sinonimo
     */
    private Sinonimo formToSinonimo() throws ParseException {
        //Tomamos los datos comunes de Permiso y Sinonimo
        Permiso permiso = FormToPermiso();
        String codigoProyecto = permiso.getCodigoProyecto();
        String codUsrGrant = permiso.getCodUsrGrant();
        String desEntorno = permiso.getDesEntorno();
        String tipObjeto = permiso.getTipObjeto();
        String mcaPdc = permiso.getMcaPdc();
        String mcaHabilitado = permiso.getMcaHabilitado();
        String codPeticion = permiso.getCodPeticion();
        String codUsr = permiso.getCodUsr();
        String codUsrAlta = permiso.getCodUsrAlta();
        Date fecActu = permiso.getFecActu();
        Date fecAlta = permiso.getFecAlta();

        //Tomamos el resto de datos exclusivos para los sinonimos
        Propietario propietarioSinonimo = (Propietario) pantalla.getCmbPropietarioSinonimo().getSelectedItem();
        String codOwnerSyn = (propietarioSinonimo != null) ? propietarioSinonimo.getCodPropietario() : null;

        String valReglaSyn = pantalla.getTxtFuncionNombre().getText();

        return Sinonimo.builder()
                .codigoProyecto(codigoProyecto)
                .codUsrGrant(codUsrGrant)
                .codOwnerSyn(codOwnerSyn)
                .desEntorno(desEntorno)
                .tipObjeto(tipObjeto)
                .valReglaSyn(valReglaSyn)
                .mcaPdc(mcaPdc)
                .mcaHabilitado(mcaHabilitado)
                .codPeticion(codPeticion)
                .codUsr(codUsr)
                .fecAlta(fecAlta)
                .codUsrAlta(codUsrAlta)
                .fecActu(fecActu)
                .build();
    }

    /**
     * Dado un permiso carga los datos en los campos de la pantalla
     *
     * @param permiso
     */
    private void PermisoToForm(Permiso permiso) {
        if (permiso == null) {
            return;
        }
        clearForm();
        pantalla.getCmbPermisoSinonimo().setSelectedIndex(1);
        pantalla.getCmbEntorno().setSelectedItem(permiso.getDesEntorno());
        pantalla.getCmbTipoObjeto().setSelectedItem(permiso.getTipObjeto());
        //Forzar a que se rellene el combo de Permisos
        pantalla.getCmbTipoObjeto().dispatchEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        pantalla.getTxtPeticion().setText(permiso.getCodPeticion());
        pantalla.getCmbPermiso().setSelectedItem(permiso.getValGrant());
        pantalla.getCmbWithGrantOption().setSelectedItem(permiso.getMcaGrantOption());

        setValueChkHabilitada(permiso.getMcaHabilitado());
        setValueCmbReceptorPermisos(permiso.getCodUsrGrant());

        pantalla.getTxtUsuarioModificacion().setText(permiso.getCodUsr());
        pantalla.getTxtUsuarioAlta().setText(permiso.getCodUsrAlta());

        dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);

        String fecActu = dateInformeFormatter.dateToString(permiso.getFecActu());
        pantalla.getTxtFechaModificacion().setText(fecActu);

        String fecAlta = dateInformeFormatter.dateToString(permiso.getFecAlta());
        pantalla.getTxtFechaAlta().setText(fecAlta);
    }

    /**
     * Dado un permiso carga los datos en los campos de la pantalla
     *
     * @param sinonimo
     */
    private void SinonimoToForm(Sinonimo sinonimo) {
        if (sinonimo == null) {
            return;
        }
        clearForm();
        pantalla.getCmbPermisoSinonimo().setSelectedIndex(2);
        pantalla.getCmbEntorno().setSelectedItem(sinonimo.getDesEntorno());
        pantalla.getCmbTipoObjeto().setSelectedItem(sinonimo.getTipObjeto());
        pantalla.getTxtPeticion().setText(sinonimo.getCodPeticion());
        pantalla.getCmbIncluirPDC().setSelectedItem(sinonimo.getMcaPdc());
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
    public void clearForm() {
        super.clearForm();
        pantalla.getTxtFechaModificacion().setText("");
        pantalla.getTxtUsuarioModificacion().setText("");
        pantalla.getTxtFechaAlta().setText("");
        pantalla.getTxtUsuarioAlta().setText("");
    }

    private void evtBtnLimpiar() {
        System.out.println("Evento Limpiar");
        pantalla.getTblPermisos().clearSelection();
        pantalla.getTblSinonimos().clearSelection();   
        clearForm();
    }
}
