package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.DlgExcepcion;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgExcepcionListener extends ListenerSupport implements ActionListener {

    private final DlgExcepcion pantalla;

    public DlgExcepcionListener(DlgExcepcion pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnAceptar())) {
            excepcion();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void excepcion() {
        try {
            ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
            Script script = (Script) pantalla.getParams().get("script");
            String txtMotivoExcepcion = pantalla.getTxtMotivoExcepcion().getText();
            OutputExcepcionScript outputExcepcionScript = scriptService.excepcionScript(proceso, script, txtMotivoExcepcion, session.getCodUsr());

            pantalla.getReturnParams().put("estadoScript", outputExcepcionScript.getDescripcionEstadoScript());

            pantalla.dispose();

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }
}
