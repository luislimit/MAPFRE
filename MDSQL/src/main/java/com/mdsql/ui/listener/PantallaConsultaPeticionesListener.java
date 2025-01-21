package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Estado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaConsultaPeticiones;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ConsultaPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.EstadosProcesado;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class PantallaConsultaPeticionesListener extends ListenerSupportModelo implements ActionListener {

    private final PantallaConsultaPeticiones pantalla;

    public PantallaConsultaPeticionesListener(PantallaConsultaPeticiones pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            buscar();
        } else if (obj.equals(pantalla.getBtnCargarProcesado())) {
            cargarProcesado();
        } else if (obj.equals(pantalla.getBtnEntregar())) {
            evtBtnEntregar();
        } else if (obj.equals(pantalla.getBtnExcluir())) {
            evtBtnExcluir();
        } else if (obj.equals(pantalla.getBtnIncidencia())) {
            evtBtnIncidencia();
        } else if (obj.equals(pantalla.getBtnRechazar())) {
            evtBtnRechazar();
        } else {
            super.actionPerformed(e);
        }
    }

    private void cargarProcesado() {
        Proceso proceso = pantalla.getSeleccionado();
        showPantallaResumenProcesado(proceso, Boolean.FALSE);
    }

    private void buscar() {
        try {
            deshabilitaBotones();

            InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();
            inputSeleccionarProcesados.setPCodigoproyecto(pantalla.getTxtModeloProyecto().getText());

            SubProyecto subProyecto = (SubProyecto) pantalla.getCmbSubModelo().getSelectedItem();
            String codigoSubproyecto = subProyecto == null ? null : subProyecto.getCodigoSubProyecto();
            inputSeleccionarProcesados.setPCodigoSubProyecto(codigoSubproyecto);

            inputSeleccionarProcesados.setPCodigoPeticion(pantalla.getTxtPeticion().getText());

            String idProcesoStr = pantalla.getTxtProcesado().getText();
            BigDecimal idProceso = idProcesoStr.isEmpty() ? null : new BigDecimal(idProcesoStr);
            inputSeleccionarProcesados.setIdProceso(idProceso);

            inputSeleccionarProcesados.setPCodigoUsuarioPeticion(pantalla.getTxtSolicitadaPor().getText());
            inputSeleccionarProcesados.setMcaCerrado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkCerrado().isSelected()));
            inputSeleccionarProcesados.setMcaEjecutado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkEjecutado().isSelected()));
            inputSeleccionarProcesados.setMcaEnEjecucion(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkEnEjecucion().isSelected()));
            inputSeleccionarProcesados.setMcaEntregado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkEntregado().isSelected()));
            inputSeleccionarProcesados.setMcaError(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkError().isSelected()));
            inputSeleccionarProcesados.setMcaExcluido(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkExcluido().isSelected()));
            inputSeleccionarProcesados.setMcaGenerado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkGenerado().isSelected()));
            inputSeleccionarProcesados.setMcaIncidencia(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkIncidencia().isSelected()));
            inputSeleccionarProcesados.setMcaRechazado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkRechazado().isSelected()));
            //
            String mcaConErrores = null; // Todos
            switch (pantalla.getCmbConErrores().getSelectedIndex()) {
                case 0:
                    break;
                case 1:
                    mcaConErrores = "S";
                    break;
                case 2:
                    mcaConErrores = "N";
            }
            inputSeleccionarProcesados.setMcaConErrores(mcaConErrores);
            inputSeleccionarProcesados.setPCodigoUsuario(pantalla.getTxtUsuario().getText());
            inputSeleccionarProcesados.setPFechaInicio(pantalla.getTxtDesde().getText());
            inputSeleccionarProcesados.setPFechaFin(pantalla.getTxtHasta().getText());

            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
            OutputConsulta<Proceso> output = procesoService.seleccionarProcesados(inputSeleccionarProcesados);
            populateModel(output.getLista());

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param modelos
     */
    private void populateModel(List<Proceso> procesos) {
        // Obtiene el modelo y lo actualiza
        ConsultaPeticionesTableModel tableModel = (ConsultaPeticionesTableModel) pantalla
                .getTblPeticiones().getModel();
        tableModel.setData(procesos);
        tableModel.fireTableDataChanged();
        pantalla.getBtnBuscar().requestFocus();
        pantalla.repaint();
    }

    /**
     *
     */
    private void evtBtnExcluir() {
        /*Proceso proceso = pantalla.getSeleccionado();
        if (proceso == null) {
            return;
        }
        String texto = MDSQLUIHelper.solicitaTexto(pantalla.getFrameParent(),"titulo.motivo.exclusion", "confirmacion.mensaje");
        if (texto == null) {
            return;
        }
        try {
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            OutputWarning output = procesoService.excluirProcesado(proceso.getIdProceso(), texto, session.getCodUsr());
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            buscar();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }*/
        evtBtnCambioEstado(MDSQLConstants.EstadosProcesado.EXCLUIDO);
    }

    /**
     *
     */
    private void evtBtnIncidencia() {
        /*Proceso proceso = pantalla.getSeleccionado();
        if (proceso == null) {
            return;
        }
        String texto = MDSQLUIHelper.solicitaTexto(pantalla.getFrameParent(),"titulo.motivo.incidencia", "confirmacion.mensaje");
        if (texto == null) {
            return;
        }
        try {
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

            OutputWarning output = procesoService.incidenciaProcesado(proceso.getIdProceso(), texto, session.getCodUsr());
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            buscar();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }*/
        evtBtnCambioEstado(MDSQLConstants.EstadosProcesado.INCIDENCIA);
    }

    /**
     *
     */
    private void evtBtnRechazar() {
        /*Proceso proceso = pantalla.getSeleccionado();
        if (proceso == null) {
            return;
        }
        Map<String, Object> params = new HashMap();
        params.put(MDSQLConstants.P_IN_PROCESO, proceso);

        DlgRechazar dialog = MDSQLUIHelper.showForm(pantalla.getFrameParent(), DlgRechazar.class, params);
        //Comprobamos si ha realizado el rechazo
        if (dialog.getReturnParams().get(MDSQLConstants.P_OUT_PROCESO) != null) {
            buscar();
        }*/
        evtBtnCambioEstado(MDSQLConstants.EstadosProcesado.RECHAZADO);
    }

    private void evtBtnCambioEstado(MDSQLConstants.EstadosProcesado estado) {
        Proceso proceso = pantalla.getSeleccionado();
        try {
            MDSQLUIHelper.cambioEstadoProcesado(pantalla, proceso.getIdProceso(), estado);
            buscar();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     *
     */
    private void evtBtnEntregar() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        Proceso proceso = session.getProceso();
        if (!Objects.isNull(proceso)) {
            // Aviso de que hay procesado en curso
            MDSQLUIHelper.showMessage(pantalla, "info.debe_finalizar_rechazar_procesado");
            return;
        }
        proceso = pantalla.getSeleccionado();
        if (!Objects.isNull(proceso) && showPantallaResumenProcesado(proceso, Boolean.TRUE)) {
            buscar();
        }
    }

    /**
     *
     * @param proceso
     * @param entregar
     * @return
     */
    private boolean showPantallaResumenProcesado(Proceso proceso, Boolean entregar) {
        Map<String, Object> params = new HashMap<>();
        params.put("proceso", proceso);
        params.put("entregar", entregar);
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaResumenProcesado.class, params);
        //Verificar si la pantalla ha finalizado correctamente => se limpia el proceso al Entregar
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        return session.getProceso() == null;
    }

    /**
     *
     */
    private void deshabilitaBotones() {
        pantalla.getBtnRechazar().setEnabled(false);
        pantalla.getBtnIncidencia().setEnabled(false);
        pantalla.getBtnEntregar().setEnabled(false);
        pantalla.getBtnExcluir().setEnabled(false);
        pantalla.getBtnCargarProcesado().setEnabled(false);
    }

    @Override
    public void clearForm() {
        pantalla.getChkCerrado().setSelected(false);
        pantalla.getChkEjecutado().setSelected(false);
        pantalla.getChkEnEjecucion().setSelected(false);
        pantalla.getChkEntregado().setSelected(false);
        pantalla.getChkError().setSelected(false);
        pantalla.getChkExcluido().setSelected(false);
        pantalla.getChkGenerado().setSelected(false);
        pantalla.getChkIncidencia().setSelected(false);
        pantalla.getChkRechazado().setSelected(false);
        pantalla.getCmbConErrores().setSelectedIndex(-1);
        pantalla.getCmbSubModelo().setSelectedIndex(-1);
        pantalla.getTblPeticiones();
        pantalla.getTxtDesde().setText("");
        pantalla.getTxtHasta().setText("");
        //pantalla.getTxtModeloProyecto().setText("");
        pantalla.getTxtPeticion().setText("");
        pantalla.getTxtProcesado().setText("");
        pantalla.getTxtSolicitadaPor().setText("");
        pantalla.getTxtUsuario().setText("");
        pantalla.repaint();
    }

    @Override
    public void onLoad() {
        super.onLoad();

        //Inicializar el estado de los botones
        deshabilitaBotones();

        //Verificar si hay parámetros iniciales para consultar
        Map<String, Object> params = pantalla.getParams();
        if (params == null || params.isEmpty()) {
            return;
        }
        String usuario = (String) params.get(MDSQLConstants.P_IN_USUARIO);
        pantalla.getTxtUsuario().setText(usuario);
        // Rellenamos los estados si se indican
        List<Estado> listaEstados = (ArrayList) params.get(MDSQLConstants.P_IN_LISTA);
        if (listaEstados != null && !listaEstados.isEmpty()) {
            for (Estado estado : listaEstados) {
                EstadosProcesado estadoProc = EstadosProcesado.getByCode(estado.getCodigoEstado());
                switch (estadoProc) {
                    case EJECUTADO:
                        pantalla.getChkEjecutado().setSelected(true);
                        break;
                    case ENTREGADO:
                        pantalla.getChkEntregado().setSelected(true);
                        break;
                    case EN_EJECUCION:
                        pantalla.getChkEnEjecucion().setSelected(true);
                        break;
                    case ERROR:
                        pantalla.getChkError().setSelected(true);
                        break;
                    case GENERADO:
                        pantalla.getChkGenerado().setSelected(true);
                        break;
                    case RECHAZADO:
                        pantalla.getChkRechazado().setSelected(true);
                        break;
                    case CERRADO:
                        pantalla.getChkCerrado().setSelected(true);
                        break;
                    case INCIDENCIA:
                        pantalla.getChkIncidencia().setSelected(true);
                        break;
                    case EXCLUIDO:
                        pantalla.getChkExcluido().setSelected(true);
                        break;
                    default:
                        break;
                }
            }
            //Una vez se han rellenado los estados, consultamos los datos
            buscar();
        }

    }

}
