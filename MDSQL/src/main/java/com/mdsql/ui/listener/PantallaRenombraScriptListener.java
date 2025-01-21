package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ScriptPeticion;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.PantallaRenombraScript;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 *
 * @author LVARONA
 */
public class PantallaRenombraScriptListener extends ListenerSupport implements ActionListener {

    private final PantallaRenombraScript pantalla;
    private final String ruta;
    private final ScriptPeticion script;

    public PantallaRenombraScriptListener(PantallaRenombraScript pantalla) {
        this.pantalla = pantalla;
        ruta = (String) pantalla.getParams().get(MDSQLConstants.P_IN_RUTA);
        script = (ScriptPeticion) pantalla.getParams().get(MDSQLConstants.P_IN_SCRIPT);
        //pantalla.getTxtNombreNuevo().setText(ruta + File.separator+ script.getNombreScript());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnNombreNuevo())) {
            evtBtnNombreNuevo();
        } else if (obj.equals(pantalla.getBtnAceptar())) {
            evtBtnAceptar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void evtBtnNombreNuevo() {
        MDSQLUIHelper.abrirScript(pantalla, pantalla.getTxtNombreNuevo(), ruta);
    }

    private void evtBtnAceptar() {
        String nombreNuevo = pantalla.getTxtNombreNuevo().getText();
        String comentario = pantalla.getTxtComentario().getText();
        if (nombreNuevo == null || nombreNuevo.isEmpty()) {
            MDSQLUIHelper.showMessage(pantalla, "info.nombre.fichero.vacio");
            return;
        }
        try {
            File fileNuevo = new File(nombreNuevo);
            ScriptService service = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
            OutputWarning output = service.cambiaNombreScript(script.getIdProceso(), script.getNumeroOrden(), script.getNombreScript(), fileNuevo.getName(), comentario);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        
        pantalla.getReturnParams().put(MDSQLConstants.P_OUT_DATA_CHANGED, Boolean.TRUE);
        pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
}
