/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.utils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class DialogSupportModelo extends DialogSupport {

    @Getter
    @Setter
    protected Modelo modelo;

    @Getter
    @Setter
    protected SubProyecto subModelo;

    protected ListenerSupportModelo formListener;

    /**
     * @param parent
     * @param modal
     */
    public DialogSupportModelo(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }

    /**
     * Creates new form
     *
     * @param parent
     * @param modal
     * @param params
     */
    public DialogSupportModelo(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

    public JTextField getTxtModeloProyecto() {
        return null;
    }

    public JTextField getTxtModeloProyectoDescrip() {
        return null;
    }

    public JButton getBtnModeloProyecto() {
        return null;
    }

    public JButton getBtnCancelar() {
        return null;
    }

    public JComboBox<SubProyecto> getCmbSubModelo() {
        return null;
    }
    
    public JButton getBtnLimpiar() {
        return null;
    }    
}
