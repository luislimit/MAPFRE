package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.adapter.DoubleClickable;
import com.mdsql.ui.model.SeleccionModelosTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.io.IOException;

/**
 * @author federico
 *
 */
public class PantallaSeleccionModelosListener extends ListenerSupport implements ActionListener, OnLoadListener, DoubleClickable {

    private final PantallaSeleccionModelos pantallaSeleccionModelos;

    public PantallaSeleccionModelosListener(PantallaSeleccionModelos pantallaSeleccionModelos) {
        super();
        this.pantallaSeleccionModelos = pantallaSeleccionModelos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String actionCommand = jButton.getActionCommand();

        if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_BUSCAR.equals(actionCommand)) {
            eventBtnBuscar();
        }

        if (MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR.equals(actionCommand)) {
            evntBtnSeleccionar();
        }

        if (actionCommand.equals(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_NOTAS)
                || actionCommand.equals(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES)
                || actionCommand.equals(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_COLUMNA)
                || actionCommand.equals(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_OBJETO)
                || actionCommand.equals(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_VARIABLES)) {
            evtCerrar(actionCommand);
        }
    }

    private void evtCerrar(String actionCommand) {
        //Indicamos la opción por la que se ha cerrado la pantalla
        pantallaSeleccionModelos.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, actionCommand);
        pantallaSeleccionModelos.dispose();
    }


    /*
	 * 
     */
    private void eventBtnBuscar() {
        try {
            String codModelo = pantallaSeleccionModelos.getTxtCodModelo().getText();
            String nombreModelo = pantallaSeleccionModelos.getTxtNombreModelo().getText();
            String codSubmodelo = pantallaSeleccionModelos.getTxtCodSubmodelo().getText();

            List<Modelo> modelos = buscar(codModelo, nombreModelo, codSubmodelo);
            populateModel(modelos);

            pantallaSeleccionModelos.forceRepaint();
        } catch (ServiceException e) {
            Map<String, Object> params = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(pantallaSeleccionModelos.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
        }
    }

    /**
     *
     */
    private void evntBtnSeleccionar() {
        pantallaSeleccionModelos.dispose();
    }

    /**
     * @param codModelo
     * @param nombreModelo
     * @param codSubmodelo
     * @return
     * @throws ServiceException
     */
    private List<Modelo> buscar(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

        OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);

        // Hay avisos
        if (outputConsultaModelos.getResult() == 2) {
            /*ServiceException serviceException = outputConsultaModelos.getServiceException();
            Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
            MDSQLUIHelper.showPopup(pantallaSeleccionModelos.getFrameParent(), MDSQLConstants.CMD_WARN, params);*/
            MDSQLUIHelper.showWarnings(pantallaSeleccionModelos, outputConsultaModelos.getServiceException());
        }

        return outputConsultaModelos.getModelos();
    }

    /**
     * @param modelos
     */
    private void populateModel(List<Modelo> modelos) {
        // Obtiene el modelo y lo actualiza
        SeleccionModelosTableModel tableModel = (SeleccionModelosTableModel) pantallaSeleccionModelos
                .getTblModelos().getModel();
        tableModel.setData(modelos);

        pantallaSeleccionModelos.getTblModelos().repaint();
    }

    @Override
    public void onLoad() {
        try {
            String codModelo = (String) pantallaSeleccionModelos.getParams().get("codigoProyecto");
            if (StringUtils.isBlank(codModelo)) {
                // En caso de que sea mi usuario, ponemos un modelo para agilizar las pruebas, se puede comentar
                Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

                ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
                //Leemos la variable de configuracion que tiene formato USUARIO.MODELO
                String usuarioDefaultModelo = configuration.getConfig("usuarioDefaultModelo");
                if (usuarioDefaultModelo != null) {
                    String[] arr = usuarioDefaultModelo.split("\\.");
                    if (arr.length == 2) {
                        String defaultModelo = arr[1];
                        String defaultUsuario = arr[0];
                        if (defaultUsuario != null && session.getCodUsr().equals(defaultUsuario) && defaultModelo != null && !defaultModelo.equals("*")) {
                            codModelo = defaultModelo;
                        }
                    }
                }
            }

            if (StringUtils.isNotBlank(codModelo)) {
                pantallaSeleccionModelos.getTxtCodModelo().setText(codModelo);
                String nombreModelo = ""; //pantallaSeleccionModelos.getTxtNombreModelo().getText();
                String codSubmodelo = ""; //pantallaSeleccionModelos.getTxtCodSubmodelo().getText();

                List<Modelo> modelos = buscar(codModelo, nombreModelo, codSubmodelo);
                populateModel(modelos);
            }
        } catch (IOException | ServiceException e) {
            pantallaSeleccionModelos.setErrorOnload(Boolean.TRUE);
            MDSQLUIHelper.showErrors(pantallaSeleccionModelos, e);
        }
    }

    @Override
    public void evtOnDoubleClick() {
        String opcion = (String) pantallaSeleccionModelos.getParams().get(MDSQLConstants.P_IN_OPCION_MENU);
        if (opcion != null && opcion.equals(MDSQLConstants.MNU_MANTENIMIENTO_PERMISOS)) {
            return;
        }
        String actionCommand;

        if (opcion == null) {
            actionCommand = MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR;
        } else {
            switch (opcion) {
                case MDSQLConstants.MNU_NOTAS_MODELOS:
                    actionCommand = MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_NOTAS;
                    break;
                case MDSQLConstants.MNU_PERMISOS_GENERALES:
                    actionCommand = MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES;
                    break;
                case MDSQLConstants.MNU_VARIABLES:
                    actionCommand = MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_VARIABLES;
                    break;
                default:
                    actionCommand = MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR;
            }
        }
        evtCerrar(actionCommand);
    }
}
