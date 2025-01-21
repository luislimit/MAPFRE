package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mdsql.ui.DlgSolicitaTexto;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;

public class DlgSolicitaTextoListener extends ListenerSupport implements ActionListener {

    private final DlgSolicitaTexto pantalla;

    public DlgSolicitaTextoListener(DlgSolicitaTexto pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnAceptar())) {
            evtBtnAceptar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void evtBtnAceptar() {
        String texto = pantalla.getTxtTexto().getText();
        if (texto == null || texto.isEmpty()) {
            MDSQLUIHelper.showMessage(pantalla, "error.debe_indicar_texto");
            return;
        }
        // Verificamos si hay clave de confirmación y en ese caso la mostramos
        String claveConfirmacion = (String) pantalla.getParams().get(MDSQLConstants.P_IN_CLAVE_CONFIRMACION);
        if (claveConfirmacion != null && !claveConfirmacion.isEmpty() && !MDSQLUIHelper.confirmAction(pantalla, claveConfirmacion)){
            return;
        }
        pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_ACEPTAR);
        pantalla.getReturnParams().put(MDSQLConstants.P_OUT_TEXTO, texto);
        pantalla.dispose();
    }
}
