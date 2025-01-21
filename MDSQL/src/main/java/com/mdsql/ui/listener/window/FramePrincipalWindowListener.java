package com.mdsql.ui.listener.window;

import com.mdsql.bussiness.entities.OutputValidaUsuario;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.UtilsService;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.PantallaConsultaPeticiones;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.LogWrapper;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FramePrincipalWindowListener extends ListenerSupport implements WindowListener {

    private final FramePrincipal framePrincipal;

    /**
     * @param framePrincipal
     */
    public FramePrincipalWindowListener(FramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    /**
     * Se hace en este evento para que aparezca el framePrincipal en el fondo Si
     * se hace en el onLoad de framePrincipalActionListener, se mostraría antes
     * de abrirse la aplicacion
     *
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) {
        try {
            String consultaPeticionesInicio = (String) ConfigurationSingleton.getInstance().getConfig("consultaPeticionesInicio");

            //Invocar a p_valida_usuario para obtener la lista de estados
            UtilsService service = (UtilsService) getService(MDSQLConstants.UTILS_SERVICE);

            String usuario = MDSQLAppHelper.getUsuario();

            OutputValidaUsuario output = service.validaUsuario(usuario, MDSQLConstants.VERSION);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            session.setDelayMensaje(output.getDelayMensaje());

            if (consultaPeticionesInicio.equals("N")) {
                return;
            }

            MDSQLUIHelper.showWarnings(framePrincipal, output.getWarnings());

            if ("S".equals(output.getTieneEnCurso())) {
                Map<String, Object> params = new HashMap();
                params.put(MDSQLConstants.P_IN_LISTA, output.getEstados());
                params.put(MDSQLConstants.P_IN_USUARIO, usuario);
                MDSQLUIHelper.showForm(framePrincipal, PantallaConsultaPeticiones.class, params);
            }

        } catch (IOException | ServiceException ex) {
            MDSQLUIHelper.showErrors(framePrincipal, ex);
            framePrincipal.dispose();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        if (!Objects.isNull(session)) {
            Proceso proceso = session.getProceso();

            if (Objects.isNull(proceso)) {
                try {
                    Path fileToDeletePath = Paths.get(MDSQLConstants.SESSION + ".ser");
                    Files.delete(fileToDeletePath);
                } catch (IOException e1) {
                    LogWrapper.warn(log, "El archivo de sesión no existe");
                }
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
