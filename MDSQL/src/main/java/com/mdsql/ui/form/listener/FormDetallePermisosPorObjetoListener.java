/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaPermisosSinonimos;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.PermisosObjetoService;
import com.mdsql.ui.adapter.DoubleClickable;
import com.mdsql.ui.form.FormDetallePermisosPorObjeto;
import com.mdsql.ui.form.FormMantenimientoPermisosPorObjeto;
import com.mdsql.ui.model.PermisosObjetoTableModel;
import com.mdsql.ui.model.SinonimosObjetoTableModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormDetallePermisosPorObjetoListener extends ListenerSupportModeloPermiso implements ActionListener, ListSelectionListener, DoubleClickable {

    protected FormDetallePermisosPorObjeto pantalla;
    private Object seleccionado;

    public FormDetallePermisosPorObjetoListener(FormDetallePermisosPorObjeto pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getBtnInforme())) {
            evtBtnInforme();
        } else if (obj.equals(pantalla.getBtnModificacion())) {
            evtBtnModificacion();
        } else if (obj.equals(pantalla.getBtnAlta())) {
            evtBtnAlta();
        }
    }

    private void evtBtnBuscar() {
        fillTablas();
        seleccionado = null;
    }

    private void evtBtnInforme() {
        try {
            String proyecto = pantalla.getTxtModeloProyecto().getText();
            ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            String sufijoPermisos = configuration.getConfig("SufijoExcelPermisosObjetos");
            excelGeneratorService.generarExcelPermisos(pantalla.getTblPermisos(), sufijoPermisos, proyecto);

            String sufijoSinonimos = configuration.getConfig("SufijoExcelSinonimosObjetos");
            excelGeneratorService.generarExcelSinonimos(pantalla.getTblSinonimos(), sufijoSinonimos, proyecto);

        } catch (IOException e) {
            Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }

    }

    public void evtBtnModificacion() {
        if (seleccionado != null) { //Ha seleccionado un elemento de tabla
            showFormMantenimiento(seleccionado);
        }
    }

    private void evtBtnAlta() {
        showFormMantenimiento(null);
    }

    private void showFormMantenimiento(Object objeto) {
        Map<String, Object> params = new HashMap<>();

        Modelo modelo = pantalla.getModelo();
        if (!Objects.isNull(modelo)) {
            params.put(MDSQLConstants.P_IN_MODELO, modelo);
        }
        if (objeto != null) {
            params.put(MDSQLConstants.P_IN_OBJETO, objeto);
        }
        FormMantenimientoPermisosPorObjeto dialog = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormMantenimientoPermisosPorObjeto.class, params);
        Boolean dataChanged = (Boolean) dialog.getReturnParams().get(MDSQLConstants.P_OUT_DATA_CHANGED);
        // Si viene de un alta siempre se reconsultan las tablas, si es modificacion solo si ha guardado
        if (dataChanged != null && dataChanged) {
            clearForm();
            fillTablas();
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

    public void evtSeleccionTabla(JTable selected, JTable unSelected) {
        int row = selected.getSelectedRow();
        if (row >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) selected.getModel();
            seleccionado = tableModel.getData().get(row);
            // Desmarcamos la tabla
            unSelected.clearSelection();
            //Habilitar botones
            pantalla.getBtnModificacion().setEnabled(true);
        }
    }

    public void fillTablas() {
        try {
            PermisosObjetoService permisosObjetoService = (PermisosObjetoService) getService(MDSQLConstants.PERMISOS_OBJETO_SERVICE);
            String p_cod_proyecto = pantalla.getTxtModeloProyecto().getText();
            String p_nom_objeto = pantalla.getTxtNombreObjeto().getText();
            String p_tip_objeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
            String p_txt_per_syn = (String) pantalla.getCmbPermisoSinonimo().getSelectedItem();
            String p_des_entorno = (String) pantalla.getCmbEntorno().getSelectedItem();

            // Propietario sinonimo
            Propietario propietario = ((Propietario) pantalla.getCmbPropietarioSinonimo().getSelectedItem());
            String p_cod_owner_syn = null;
            if (propietario != null) {
                p_cod_owner_syn = propietario.getCodPropietario();
            }

            System.out.println("pantalla.getTxtFuncionNombre().getText()=" + pantalla.getTxtFuncionNombre().getText());
            String p_val_regla_syn = pantalla.getTxtFuncionNombre().getText(); // Funcion nombre
            System.out.println("(String) pantalla.getCmbPermiso().getSelectedItem()=" + (String) pantalla.getCmbPermiso().getSelectedItem());
            String p_val_grant = (String) pantalla.getCmbPermiso().getSelectedItem(); // Permiso

            // Receptor permisos 
            Grant grant = ((Grant) pantalla.getCmbReceptorPermisos().getSelectedItem());
            String p_cod_usr_grant = null;
            if (grant != null) {
                p_cod_usr_grant = grant.getCodGrant();
            }

            String p_mca_PDC = (String) pantalla.getCmbIncluirPDC().getSelectedItem(); // Incluir en PDC
            String p_mca_grant_option = (String) pantalla.getCmbWithGrantOption().getSelectedItem();
            String p_mca_habilitado = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());

            OutputConsultaPermisosSinonimos output
                    = permisosObjetoService.consultaPermisosSinonimos(p_cod_proyecto,
                            p_nom_objeto,
                            p_tip_objeto,
                            p_txt_per_syn,
                            p_des_entorno,
                            p_cod_owner_syn,
                            p_val_regla_syn,
                            p_val_grant,
                            p_cod_usr_grant,
                            p_mca_PDC,
                            p_mca_grant_option,
                            p_mca_habilitado);

            PermisosObjetoTableModel permisosTableModel = (PermisosObjetoTableModel) pantalla.getTblPermisos().getModel();
            permisosTableModel.clearData();
            permisosTableModel.setData(output.getPermisosObjeto());

            SinonimosObjetoTableModel sinonimosTableModel = (SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel();
            sinonimosTableModel.clearData();
            sinonimosTableModel.setData(output.getSinonimosObjeto());

            // Si hay datos en una de las dos tablas, habilitamos el botón de informe
            boolean hayDatosParaInforme = ((output.getPermisosObjeto() != null && !output.getPermisosObjeto().isEmpty())
                    || (output.getSinonimosObjeto() != null && !output.getSinonimosObjeto().isEmpty())) ;
            pantalla.getBtnInforme().setEnabled(hayDatosParaInforme);
            //No hay elemento seleccionado, no se puede modificar
            pantalla.getBtnModificacion().setEnabled(false);
            
            MDSQLUIHelper.showWarnings(pantalla, output.getServiceException());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void clearForm() {
        super.clearForm();
        seleccionado = null;
        pantalla.getTxtNombreObjeto().setText("");
        ((PermisosObjetoTableModel) pantalla.getTblPermisos().getModel()).clearData();
        ((SinonimosObjetoTableModel) pantalla.getTblSinonimos().getModel()).clearData();
        pantalla.getBtnInforme().setEnabled(false);
        pantalla.getBtnModificacion().setEnabled(false);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        setDefWithGrantOption(COMBOBOX_SINVALOR);
        setDefIncluirPDC(COMBOBOX_SINVALOR);
        setDefHabilitada(false);
        clearForm();
    }

    @Override
    public void evtOnDoubleClick() {
        evtBtnModificacion();
    }

}
