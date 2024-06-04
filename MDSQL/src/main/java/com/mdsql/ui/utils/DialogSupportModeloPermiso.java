/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.utils;

import com.mdval.ui.utils.FrameSupport;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class DialogSupportModeloPermiso extends DialogSupportModelo {


    /**
     * @param parent
     * @param modal
     */
    public DialogSupportModeloPermiso(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }

    /**
     * Creates new form
     *
     * @param parent
     * @param modal
     * @param params
     */
    public DialogSupportModeloPermiso(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

    public JComboBox getCmbTipoObjeto() {
        return null;
    }
  
    public JComboBox getCmbPropietarioSinonimo() {
        return null;
    }

    public JComboBox getCmbReceptorPermisos() {
        return null;
    }

    public JComboBox getCmbPermisoSinonimo() {
        return null;
    }
    public JComboBox getCmbWithGrantOption() {
        return null;
    }

    public JComboBox getCmbIncluirPDC() {
        return null;
    }

    public JComboBox getCmbEntorno() {
        return null;
    }

    public JComboBox getCmbPermiso() {
        return null;
    }

    public JTextField getTxtFuncionNombre() {
        return null;
    }

    public JCheckBox getChkHabilitada() {
        return null;
    }

    public JTextField getTxtPeticion() {
        return null;
    }
}
