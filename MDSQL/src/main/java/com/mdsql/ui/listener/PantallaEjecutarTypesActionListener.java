package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.PantallaEjecutarTypes;
import com.mdsql.ui.PantallaVerCuadresScript;
import com.mdsql.ui.PantallaVerErroresScript;
import com.mdsql.ui.model.TypesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.ui.utils.collections.CreateTypeScriptsClosure;
import com.mdsql.ui.utils.collections.UpdateTypesScriptsClosure;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaEjecutarTypesActionListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private PantallaEjecutarTypes pantalla;

    public PantallaEjecutarTypesActionListener(PantallaEjecutarTypes pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR.equals(jButton.getActionCommand())) {
            eventBtnRechazar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES.equals(jButton.getActionCommand())) {
            eventBtnVerCuadres();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES.equals(jButton.getActionCommand())) {
            eventBtnVerErrores();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
            eventBtnAceptar();
        }

        if (MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.dispose();
        }

    }

    public void eventBtnRechazar() {
        /*Map<String, Object> params = new HashMap<>();

		Proceso proceso = pantalla.getProceso();

		params.put("proceso", proceso);

		DlgRechazar dlgRechazar = (DlgRechazar) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
				MDSQLConstants.CMD_RECHAZAR_PROCESADO, params);
		MDSQLUIHelper.show(dlgRechazar);

		proceso = (Proceso) dlgRechazar.getReturnParams().get("proceso");
		
		if ("Rechazado".equals(proceso.getDescripcionEstadoProceso())) {
			pantalla.getReturnParams().put("proceso", proceso);
			pantalla.getReturnParams().put("estado", "RECHAZADO");
			pantalla.dispose();
		}*/
        Proceso proceso = pantalla.getProceso();
        try {
            MDSQLUIHelper.cambioEstadoProcesado(pantalla, proceso.getIdProceso(), MDSQLConstants.EstadosProcesado.RECHAZADO);
            pantalla.getReturnParams().put("proceso", proceso);
            pantalla.getReturnParams().put("estado", "RECHAZADO");
            pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }            
    }

    public void eventBtnVerCuadres() {
        Map<String, Object> params = new HashMap<>();

        Proceso proceso = pantalla.getProceso();
        Type seleccionado = pantalla.getSeleccionado();
        BigDecimal numeroOrden = seleccionado.getNumeroOrdenType();

        params.put("orden", numeroOrden);
        params.put("proceso", proceso);

        /*PantallaVerCuadresScript pantallaVerCuadresScript = (PantallaVerCuadresScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_VER_CUADRES_SCRIPT, params);
        MDSQLUIHelper.show(pantallaVerCuadresScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaVerCuadresScript.class, params);
    }

    public void eventBtnVerErrores() {
        Map<String, Object> params = new HashMap<>();

        Proceso proceso = pantalla.getProceso();
        Type seleccionado = pantalla.getSeleccionado();
        BigDecimal numeroOrden = seleccionado.getNumeroOrdenType();

        params.put("numeroOrden", numeroOrden);
        params.put("proceso", proceso);
        params.put("tipo", "type");

        /*PantallaVerErroresScript pantallaVerErroresScript = (PantallaVerErroresScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_VER_ERRORES_SCRIPT, params);
        MDSQLUIHelper.show(pantallaVerErroresScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(),PantallaVerErroresScript.class, params);
    }

    public void eventBtnAceptar() {
        try {
            ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
            Proceso proceso = pantalla.getProceso();
            BBDD bbdd = proceso.getBbdd();

            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String selectedRoute = session.getSelectedRoute();

            TypesTableModel tableModelTypes = (TypesTableModel) pantalla.getTblTypes()
                    .getModel();
            List<Type> types = tableModelTypes.getData();

            CollectionUtils.forAllDo(types, new CreateTypeScriptsClosure(selectedRoute));

            // En este caso sólo se ejecuta el script lanza
            Script scriptLanza = proceso.getScriptLanza();

            OutputRegistraEjecucionType ejecucion = scriptService.executeScript(bbdd, scriptLanza.getNombreScript(), scriptLanza.getLineasScript(), proceso.getFicheroLog());

            MDSQLUIHelper.showWarnings(pantalla, ejecucion.getWarnings());

            // Actualizar los types de la tabla y la repinta
            CollectionUtils.forAllDo(types, new UpdateTypesScriptsClosure(ejecucion));
            pantalla.getTblTypes().repaint();

            updateCurrentProcess(proceso, ejecucion);
            if ("Error".equals(ejecucion.getDescripcionEstadoProceso())) {
                proceso.setDescripcionEstadoProceso(ejecucion.getDescripcionEstadoProceso());
                pantalla.getTxtEstadoEjecucion().setText(ejecucion.getDescripcionEstadoProceso());

                // Disable Aceptar button
                pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
            } else {
                proceso.setDescripcionEstadoProceso("Ejecutado");
                session.setProceso(proceso);

                pantalla.getReturnParams().put("proceso", proceso);
                pantalla.getReturnParams().put("entregar", Boolean.TRUE);
                pantalla.getReturnParams().put("cmd", MDSQLConstants.PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR);

                pantalla.dispose();
            }

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
        pantalla.setProceso(proceso);
        pantalla.getTxtIdProcesado().setText(proceso.getIdProceso().toString());

        // Obtiene los types
        List<Type> types = proceso.getTypes();
        TypesTableModel tableModelTypes = (TypesTableModel) pantalla.getTblTypes()
                .getModel();
        tableModelTypes.setData(types);

        if (isAllExecuted(types)) {
            // Disable Aceptar button
            pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
        }

        // También se deshabilita si el procesado está Rechazado, Error, Entregado
        if ("Rechazado".equals(proceso.getDescripcionEstadoProceso())
                || "Error".equals(proceso.getDescripcionEstadoProceso())
                || "Entregado".equals(proceso.getDescripcionEstadoProceso())) {
            // Disable Aceptar button
            pantalla.getBtnAceptar().setEnabled(Boolean.FALSE);
        }
    }

    private Boolean isAllExecuted(List<Type> types) {
        for (Type type : types) {
            if (!"Ejecutado".equals(type.getDescripcionEstadoScript())) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private void updateCurrentProcess(Proceso proceso, OutputRegistraEjecucionType ejecucion) {
        proceso.setCodigoEstadoProceso(ejecucion.getCodigoEstadoProceso());
        proceso.setDescripcionEstadoProceso(ejecucion.getDescripcionEstadoProceso());

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        session.setProceso(proceso);
    }
}
