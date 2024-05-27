package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.*;
import com.mdsql.ui.*;
import com.mdsql.ui.form.FormConsultaPermisosPersonalizados;
import com.mdsql.ui.form.FormDetallePermisosPorColumna;
import com.mdsql.ui.form.FormDetallePermisosPorObjeto;
import com.mdsql.ui.form.FormGenerarPermisosPersonalizados;
import com.mdsql.ui.form.FormPermisosGeneralesPorModeloPorTipoObjeto;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author federico
 *
 */
@Slf4j
public class MenuMantenimientoActionListener extends ListenerSupport implements ActionListener {

    private final FrameSupport framePrincipal;

    /*private PantallaProcesarScript pantallaProcesarScript;

    private DialogSupport pantallaEjecutar;*/
    /**
     * @param framePrincipal
     */
    public MenuMantenimientoActionListener(FrameSupport framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        String actionCommand = item.getActionCommand();
        if (actionCommand == null) {
            return;
        }

        System.out.println("Seleccionado = " + actionCommand);

        switch (actionCommand) {
            case MDSQLConstants.MNU_PERMISOS_GENERALES:
                selModeloAndShowForm(actionCommand);
                break;
            case MDSQLConstants.MNU_CONSULTA_PERMISOS:
                showForm(FormConsultaPermisosPersonalizados.class);
                break;
            case MDSQLConstants.MNU_MANTENIMIENTO_PERMISOS:
                selModeloAndShowForm(actionCommand);
                break;
            case MDSQLConstants.MNU_GENERAR_PERMISOS:
                evtGenerarPermisos();
                break;
            case MDSQLConstants.MNU_MANTENIMIENTO_HISTORICO:
                showForm(PantallaMantenimientoHistorico.class);
                break;
            case MDSQLConstants.MNU_NOTAS_MODELOS:
                showForm(PantallaMantenimientoNotasModelos.class);
                break;
            case MDSQLConstants.MNU_ENTORNOS:
                showForm(PantallaMantenimientoEntornos.class);
                break;
            case MDSQLConstants.MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS:
                showForm(PantallaMantenimientoEntornosPrueba.class);
                break;
            case MDSQLConstants.MNU_EJECUCION_SCRIPT_INICIAL:
                showForm(PantallaEjecutarScriptInicialEntornoPrueba.class);
                break;
            case MDSQLConstants.MNU_VARIABLES:
                selModeloAndShowForm(actionCommand);
                break;
            default:
                break;
        }
    }

    private void evtGenerarPermisos() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();
        if (proceso == null || "Generado".equals(proceso.getDescripcionEstadoProceso())) {
            DialogSupport dialog = showForm(FormGenerarPermisosPersonalizados.class);
            //Si el proceso ha terminado correctamente, mostramos la pantalla de resumen
            if (dialog.getReturnParams() != null && !dialog.getReturnParams().isEmpty()) {
                String exitBtn = (String) dialog.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
                if (exitBtn.equals(MDSQLConstants.BTN_ACEPTAR)) {
                    showForm(PantallaResumenProcesado.class);
                }
            }
        } else {
            // Aviso de que no hay procesado en curso
            JOptionPane.showMessageDialog(framePrincipal, "Hay procesado en curso, debe finalizarlo o rechazarlo desde la opción ejecutar scripts");
        }
    }

    private DialogSupport showForm(Class<?> dlgClass) {
        Map<String, Object> params = new HashMap<>();
        return (DialogSupport) MDSQLUIHelper.showForm(framePrincipal, dlgClass, params);
    }

    private void selModeloAndShowForm(String actionCommand) {
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_OPCION_MENU, actionCommand);
        PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(framePrincipal,
                MDSQLConstants.CMD_SEARCH_MODEL, params);
        MDSQLUIHelper.show(pantallaSeleccionModelos);

        String btnSeleccionModelo = (String) pantallaSeleccionModelos.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (btnSeleccionModelo != null) {
            Modelo modelo = pantallaSeleccionModelos.getSeleccionado();
            if (!Objects.isNull(modelo)) {
                params = new HashMap<>();
                params.put(MDSQLConstants.P_IN_MODELO, modelo);
                switch (btnSeleccionModelo) {
                    case MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_NOTAS:
                        MDSQLUIHelper.showForm(framePrincipal, PantallaMantenimientoNotasModelos.class, params);
                        break;
                    case MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES:
                        MDSQLUIHelper.showForm(framePrincipal, FormPermisosGeneralesPorModeloPorTipoObjeto.class, params);
                        break;
                    case MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_COLUMNA:
                        MDSQLUIHelper.showForm(framePrincipal, FormDetallePermisosPorColumna.class, params);
                        break;
                    case MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_OBJETO:
                        MDSQLUIHelper.showForm(framePrincipal, FormDetallePermisosPorObjeto.class, params);
                        break;
                    case MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_VARIABLES:
                        MDSQLUIHelper.showForm(framePrincipal, PantallaMantenimientoVariables.class, params);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}