package com.mdsql.ui.listener.text;

import com.mdsql.ui.utils.DialogSupportModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import javax.swing.JTextField;

/**
 *
 * @author LVARONA
 */
public class TxtModeloProyectoListener implements FocusListener, ActionListener {

    private final DialogSupportModelo pantalla;
    private String modeloProyectoAnt;

    public TxtModeloProyectoListener(DialogSupportModelo pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void focusGained(FocusEvent e) {
        modeloProyectoAnt = pantalla.getTxtModeloProyecto().getText();
    }

    @Override
    public void focusLost(FocusEvent e) {
        String modeloProyecto = ((JTextField) e.getSource()).getText();
        if (!Objects.equals(modeloProyecto, modeloProyectoAnt)) {
            // Ha cambiado el valor del ModeloProyecto
            pantalla.getFormListener().clearDepsModelo();
            modeloProyectoAnt = modeloProyecto;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String modeloProyecto = ((JTextField) e.getSource()).getText();
        // Ha cambiado el valor del ModeloProyecto, si presiona ENTER mostramos la opcion de busqueda
        pantalla.getFormListener().clearDepsModelo();
        modeloProyectoAnt = modeloProyecto;
        pantalla.getFormListener().evtBtnSearchModel();
        modeloProyecto = ((JTextField) e.getSource()).getText();
        modeloProyectoAnt = modeloProyecto;
        //Si ha seleccionado un modelo, navegamos al siguiente elemento
        if (pantalla.getBtnModeloProyecto() != null && modeloProyecto != null) {
            pantalla.getBtnModeloProyecto().requestFocus();
        }
    }

}
