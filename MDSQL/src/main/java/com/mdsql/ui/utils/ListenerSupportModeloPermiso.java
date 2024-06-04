/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.utils;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaTiposObjeto;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.bussiness.service.PropietarioService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.model.GrantComboBoxModel;
import com.mdsql.ui.model.PermisoComboBoxModel;
import com.mdsql.ui.model.PermisoSinonimoComboBoxModel;
import com.mdsql.ui.model.PropietarioComboBoxModel;
import com.mdsql.ui.model.SiNoOpcionalComboBoxModel;
import com.mdsql.ui.model.TipoObjetoComboBoxModel;
import com.mdsql.ui.model.VigenteHistoricoComboBoxModel;
import com.mdsql.ui.renderer.CmbStringRenderer;
import com.mdsql.ui.renderer.GrantRenderer;
import com.mdsql.ui.renderer.PropietarioRenderer;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ListenerSupportModeloPermiso extends ListenerSupportModelo implements ActionListener {

    private final DialogSupportModeloPermiso dialogSupportModeloPermiso;
    public static final int COMBOBOX_SINVALOR = 0;
    public static final int COMBOBOX_SINO_SI = 1;
    public static final int COMBOBOX_SINO_NO = 2;
    public static final int COMBOBOX_PERMISOSINONIMO_PERMISO = 1;
    public static final int COMBOBOX_PERMISOSINONIMO_SINONIMO = 2;
    // Valores por defecto correspondientes a los campos, se usan al iniciar y limpiar pantallas
    private int defWithGrantOption;
    private int defIncluirPDC;
    private boolean defHabilitada;

    public ListenerSupportModeloPermiso(DialogSupportModeloPermiso dialogSupportModeloPermiso) {
        super(dialogSupportModeloPermiso);
        this.dialogSupportModeloPermiso = dialogSupportModeloPermiso;
        this.defWithGrantOption = COMBOBOX_SINO_NO;
        this.defIncluirPDC = COMBOBOX_SINO_SI;
        this.defHabilitada = false;
    }

    public void setDefWithGrantOption(int defWithGrantOption) {
        this.defWithGrantOption = defWithGrantOption;
    }

    public void setDefIncluirPDC(int defIncluirPDC) {
        this.defIncluirPDC = defIncluirPDC;
    }

    public void setDefHabilitada(boolean defHabilitada) {
        this.defHabilitada = defHabilitada;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object obj = e.getSource();

        if (obj.equals(dialogSupportModeloPermiso.getCmbTipoObjeto())) {
            evtCmbTipoObjeto();
        } else if (obj.equals(dialogSupportModeloPermiso.getCmbPermisoSinonimo())) {
            evtCmbPermisoSinonimo();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        //Inicializar los modelos de los combos y tablas
        initModels();
        //Asignar listeners a los combos
        if (dialogSupportModeloPermiso.getCmbPermisoSinonimo() != null) {
            dialogSupportModeloPermiso.getCmbPermisoSinonimo().addActionListener(this);
        }
        if (dialogSupportModeloPermiso.getCmbTipoObjeto() != null) {
            try {
                dialogSupportModeloPermiso.getCmbTipoObjeto().addActionListener(this);
                fillCmbTipoObjeto();
            } catch (ServiceException ex) {
                dialogSupportModeloPermiso.setErrorOnload(Boolean.TRUE);
                MDSQLUIHelper.showErrors(dialogSupportModeloPermiso, ex);
            }
        }
    }

    private void evtCmbTipoObjeto() {
        String tipoObjeto = (String) dialogSupportModeloPermiso.getCmbTipoObjeto().getSelectedItem();
        fillCmbPermiso(tipoObjeto);
    }

    public void fillCmbPermiso(String tipoObjeto) {
        JComboBox cmbPermiso = dialogSupportModeloPermiso.getCmbPermiso();
        if (cmbPermiso == null) {
            return;
        }
        if (tipoObjeto != null) {
            try {
                PermisosService permisosService = (PermisosService) getService(MDSQLConstants.PERMISOS_SERVICE);
                List<String> permisos = permisosService.consultarPermisosPorTipoObjeto(tipoObjeto);
                if (CollectionUtils.isNotEmpty(permisos)) {
                    PermisoComboBoxModel permisoComboBoxModel = new PermisoComboBoxModel(permisos);
                    cmbPermiso.setModel(permisoComboBoxModel);
                    if (permisos.size() == 1) {
                        cmbPermiso.setSelectedItem(permisos.get(0));
                    }
                }
            } catch (ServiceException e) {
                MDSQLUIHelper.showErrors(dialogSupportModeloPermiso, e);
            }
        }
    }

    private void evtCmbPermisoSinonimo() {
        //Si se selecciona Permiso, se limpiarán y deshabilitarán los campos de Propietario Sinónimo y Función Nombre
        //Se habilitarán los combos de WITH GRANT OPTION y Permiso.
        int opcion = dialogSupportModeloPermiso.getCmbPermisoSinonimo().getSelectedIndex(); //1-Permiso; 2-Sinónimo

        JComboBox cmbPropietarioSinonimo = dialogSupportModeloPermiso.getCmbPropietarioSinonimo();
        JTextField funcionNombre = dialogSupportModeloPermiso.getTxtFuncionNombre();
        JComboBox CmbWithGrantOption = dialogSupportModeloPermiso.getCmbWithGrantOption();
        JComboBox CmbPermiso = dialogSupportModeloPermiso.getCmbPermiso();
        switch (opcion) {
            case COMBOBOX_PERMISOSINONIMO_PERMISO: //Permiso
                if (cmbPropietarioSinonimo != null) {
                    cmbPropietarioSinonimo.setEnabled(false);
                    cmbPropietarioSinonimo.setSelectedIndex(-1);
                }
                if (funcionNombre != null) {
                    funcionNombre.setEnabled(false);
                    funcionNombre.setText("");
                }
                if (CmbWithGrantOption != null) {
                    CmbWithGrantOption.setEnabled(true);
                }
                if (CmbPermiso != null) {
                    CmbPermiso.setEnabled(true);
                }
                break;
            case COMBOBOX_PERMISOSINONIMO_SINONIMO: //Sinónimo
                if (cmbPropietarioSinonimo != null) {
                    cmbPropietarioSinonimo.setEnabled(true);
                }
                if (funcionNombre != null) {
                    funcionNombre.setEnabled(true);
                }
                if (CmbWithGrantOption != null) {
                    CmbWithGrantOption.setEnabled(false);
                    CmbWithGrantOption.setSelectedIndex(-1);
                }
                if (CmbPermiso != null) {
                    CmbPermiso.setEnabled(false);
                    CmbPermiso.setSelectedIndex(-1);
                }
                break;
            default:
                if (cmbPropietarioSinonimo != null) {
                    cmbPropietarioSinonimo.setEnabled(true);
                }
                if (funcionNombre != null) {
                    funcionNombre.setEnabled(true);
                }
                if (CmbWithGrantOption != null) {
                    CmbWithGrantOption.setEnabled(true);
                }
                if (CmbPermiso != null) {
                    CmbPermiso.setEnabled(true);
                }
        }
    }

    @Override
    public void procesarModelo() throws ServiceException {
        super.procesarModelo();
        if (dialogSupportModeloPermiso.getCmbPropietarioSinonimo() != null) {
            fillCmbPropietarioSinonimo();
        }
        if (dialogSupportModeloPermiso.getCmbReceptorPermisos() != null) {
            fillCmbReceptorPermisos();
        }
    }

    private void initModels() {

        JComboBox cmbReceptorPermisos = dialogSupportModeloPermiso.getCmbReceptorPermisos();
        if (cmbReceptorPermisos != null) {
            cmbReceptorPermisos.setRenderer(new GrantRenderer());
        }

        JComboBox cmbPropietarioSinonimo = dialogSupportModeloPermiso.getCmbPropietarioSinonimo();
        if (cmbPropietarioSinonimo != null) {
            cmbPropietarioSinonimo.setRenderer(new PropietarioRenderer());
        }

        JComboBox cmbPermisoSinonimo = dialogSupportModeloPermiso.getCmbPermisoSinonimo();
        if (cmbPermisoSinonimo != null) {
            cmbPermisoSinonimo.setModel(new PermisoSinonimoComboBoxModel());
            cmbPermisoSinonimo.setRenderer(new CmbStringRenderer());
        }

        JComboBox cmbWithGrantOption = dialogSupportModeloPermiso.getCmbWithGrantOption();
        if (cmbWithGrantOption != null) {
            cmbWithGrantOption.setModel(new SiNoOpcionalComboBoxModel());
            cmbWithGrantOption.setRenderer(new CmbStringRenderer());
            cmbWithGrantOption.setSelectedIndex(defWithGrantOption); // No por defecto
        }

        JComboBox cmbIncluirPDC = dialogSupportModeloPermiso.getCmbIncluirPDC();
        if (cmbIncluirPDC != null) {
            cmbIncluirPDC.setModel(new SiNoOpcionalComboBoxModel());
            cmbIncluirPDC.setRenderer(new CmbStringRenderer());
            cmbIncluirPDC.setSelectedIndex(defIncluirPDC); // Sí por defecto
        }

        JComboBox cmbEntorno = dialogSupportModeloPermiso.getCmbEntorno();
        if (cmbEntorno != null) {
            cmbEntorno.setModel(new VigenteHistoricoComboBoxModel());
            cmbEntorno.setRenderer(new CmbStringRenderer());
        }

    }

    private void fillCmbPropietarioSinonimo() throws ServiceException {
        Modelo modelo = dialogSupportModeloPermiso.getModelo();
        if (modelo != null) {
            PropietarioService propietarioService = (PropietarioService) getService(MDSQLConstants.PROPIETARIO_SERVICE);
            List<Propietario> propietarios = propietarioService.consultarPropietariosSinonimo(modelo);
            PropietarioComboBoxModel propietarioComboBoxModel = new PropietarioComboBoxModel(propietarios);
            dialogSupportModeloPermiso.getCmbPropietarioSinonimo().setModel(propietarioComboBoxModel);
        }
    }

    private void fillCmbReceptorPermisos() throws ServiceException {
        Modelo modelo = dialogSupportModeloPermiso.getModelo();
        if (modelo != null) {
            PropietarioService propietarioService = (PropietarioService) getService(MDSQLConstants.PROPIETARIO_SERVICE);
            List<Grant> permisos = propietarioService.consultarReceptoresModelo(modelo);
            GrantComboBoxModel grantComboBoxModel = new GrantComboBoxModel(permisos);
            dialogSupportModeloPermiso.getCmbReceptorPermisos().setModel(grantComboBoxModel);
        }
    }

    private void fillCmbTipoObjeto() throws ServiceException {
        TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);
        OutputConsultaTiposObjeto outputConsultaTiposObjeto = tipoObjetoService.consultarTiposObjeto();

        // Si hay avisos se muestran
        MDSQLUIHelper.showWarnings(dialogSupportModeloPermiso, outputConsultaTiposObjeto.getServiceException());

        if (CollectionUtils.isNotEmpty(outputConsultaTiposObjeto.getTiposObjeto())) {
            TipoObjetoComboBoxModel tipoObjetoComboBoxModel = new TipoObjetoComboBoxModel(outputConsultaTiposObjeto.getTiposObjeto());
            dialogSupportModeloPermiso.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
        }
    }

    @Override
    public void clearForm() {
        JComboBox cmbReceptorPermisos = dialogSupportModeloPermiso.getCmbReceptorPermisos();
        if (cmbReceptorPermisos != null) {
            cmbReceptorPermisos.setSelectedIndex(-1);
        }

        JComboBox cmbPropietarioSinonimo = dialogSupportModeloPermiso.getCmbPropietarioSinonimo();
        if (cmbPropietarioSinonimo != null) {
            cmbPropietarioSinonimo.setSelectedIndex(-1);
            cmbPropietarioSinonimo.setEnabled(true);
        }

        JComboBox cmbPermisoSinonimo = dialogSupportModeloPermiso.getCmbPermisoSinonimo();
        if (cmbPermisoSinonimo != null) {
            cmbPermisoSinonimo.setSelectedIndex(-1);
        }

        JComboBox cmbWithGrantOption = dialogSupportModeloPermiso.getCmbWithGrantOption();
        if (cmbWithGrantOption != null) {
            cmbWithGrantOption.setSelectedIndex(defWithGrantOption); // No por defecto
            cmbWithGrantOption.setEnabled(true);
        }

        JComboBox cmbIncluirPDC = dialogSupportModeloPermiso.getCmbIncluirPDC();
        if (cmbIncluirPDC != null) {
            cmbIncluirPDC.setSelectedIndex(defIncluirPDC); // Sí por defecto
            cmbIncluirPDC.setEnabled(true);
        }

        JComboBox cmbEntorno = dialogSupportModeloPermiso.getCmbEntorno();
        if (cmbEntorno != null) {
            cmbEntorno.setSelectedIndex(-1);
        }

        JComboBox cmbTipoObjeto = dialogSupportModeloPermiso.getCmbTipoObjeto();
        if (cmbTipoObjeto != null) {
            cmbTipoObjeto.setSelectedIndex(-1);
        }

        JComboBox cmbPermiso = dialogSupportModeloPermiso.getCmbPermiso();
        if (cmbPermiso != null) {
            cmbPermiso.setSelectedIndex(-1);
            cmbPermiso.setEnabled(true);
        }

        JTextField txtFuncionNombre = dialogSupportModeloPermiso.getTxtFuncionNombre();
        if (txtFuncionNombre != null) {
            txtFuncionNombre.setText("");
            txtFuncionNombre.setEnabled(true);
        }

        JTextField txtPeticion = dialogSupportModeloPermiso.getTxtPeticion();
        if (txtPeticion != null) {
            txtPeticion.setText("");
            txtPeticion.setEnabled(true);
        }

        JCheckBox chkHabilitada = dialogSupportModeloPermiso.getChkHabilitada();
        if (chkHabilitada != null) {
            chkHabilitada.setSelected(defHabilitada);
        }
        super.clearForm();
    }

    /**
     * Seleccionar el texto en el Combo CmbPropietarioSinonimo
     *
     * @param codigo
     */
    public void setValueCmbReceptorPermisos(String codigo) {
        JComboBox comboBox = dialogSupportModeloPermiso.getCmbReceptorPermisos();
        int idx = -1;
        for (int i = 1; i < comboBox.getModel().getSize(); i++) {
            Grant grant = (Grant) comboBox.getItemAt(i);
            if (grant != null) {
                if (grant.getCodGrant().equals(codigo)) {
                    idx = i;
                    break;
                }
            }
        }
        comboBox.setSelectedIndex(idx);
    }

    /**
     * Seleccionar el texto en el Combo CmbReceptorPermisos
     *
     * @param codigo
     */
    public void setValueCmbPropietarioSinonimo(String codigo) {
        JComboBox comboBox = dialogSupportModeloPermiso.getCmbPropietarioSinonimo();
        int idx = -1;
        for (int i = 1; i < comboBox.getModel().getSize(); i++) {
            Propietario propietario = (Propietario) comboBox.getItemAt(i);
            if (propietario != null) {
                if (propietario.getCodPropietario().equals(codigo)) {
                    idx = i;
                    break;
                }
            }
        }
        comboBox.setSelectedIndex(idx);
    }

    public void setValueChkHabilitada(String value) {
        Boolean mcaHabilitado = MDSQLAppHelper.normalizeCheckValue(value);
        dialogSupportModeloPermiso.getChkHabilitada().setSelected(mcaHabilitado);
    }

    public String getValueCmbPropietarioSinonimo() {
        Propietario propietarioSinonimo = (Propietario) dialogSupportModeloPermiso.getCmbPropietarioSinonimo().getSelectedItem();
        return (propietarioSinonimo != null) ? propietarioSinonimo.getCodPropietario() : null;
    }

    public String getValueCmbReceptorPermisos() {
        Grant grant = (Grant) dialogSupportModeloPermiso.getCmbReceptorPermisos().getSelectedItem();
        return (grant != null) ? grant.getCodGrant() : null;
    }

    public String getValueChkHabilitada() {
        return MDSQLAppHelper.normalizeValueToCheck(dialogSupportModeloPermiso.getChkHabilitada().isSelected());
    }

    public void setValueSiNoComboBox(JComboBox comboBox, String value) {
        switch (value) {
            case "N":
                comboBox.setSelectedIndex(COMBOBOX_SINO_NO);
                break;
            case "S":
                comboBox.setSelectedIndex(COMBOBOX_SINO_SI);
                break;
            default:
                comboBox.setSelectedIndex(COMBOBOX_SINVALOR);
                break;
        }
    }
}
