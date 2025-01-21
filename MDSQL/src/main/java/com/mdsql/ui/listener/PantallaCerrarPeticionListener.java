package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputConfirmaCierre;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.ProcesadoPeticion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.EntregaService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaCerrarPeticion;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaVerScriptsPeticion;
import com.mdsql.ui.model.CerrarPeticionTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaCerrarPeticionListener extends ListenerSupport implements ActionListener, ListSelectionListener {

    protected PantallaCerrarPeticion pantalla;
    private ProcesadoPeticion seleccionado;
    private Proceso proceso;

    public PantallaCerrarPeticionListener(PantallaCerrarPeticion pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBuscar();
        } else if (obj.equals(pantalla.getBtnRechazar())) {
            //evtBtnRechazar();
            evtBtnCambioEstado(MDSQLConstants.EstadosProcesado.RECHAZADO);
        } else if (obj.equals(pantalla.getBtnVerProcesado())) {
            evtBtnVerProcesado();
        } else if (obj.equals(pantalla.getBtnExcluir())) {
            evtBtnCambioEstado(MDSQLConstants.EstadosProcesado.EXCLUIDO);
        } else if (obj.equals(pantalla.getBtnVerScripts())) {
            evtBtnVerScripts();
        } else if (obj.equals(pantalla.getBtnCerrarPeticion())) {
            evtBtnCerrarPeticion();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            evtSalir();
        }
    }

    /**
     * Se dispara al seleccionar un elemento de la lista
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        seleccionado = (ProcesadoPeticion) MDSQLUIHelper.getSelectedTableObject(pantalla.getTblProcesado());
        if (seleccionado == null) {
            proceso = null;
            return;
        }
        proceso = new Proceso();
        proceso.setIdProceso(seleccionado.getIdProceso());
        //
        enableButtons();
        pantalla.getBtnVerProcesado().setEnabled(true);
    }

    /**
     * Habilita los botones dependiendo de la lógica de negocio
     *
     */
    private void enableButtons() {
        pantalla.getBtnRechazar().setEnabled("S".equals(seleccionado.getMcaRechazar()));
        pantalla.getBtnExcluir().setEnabled("S".equals(seleccionado.getMcaExcluir()));
    }

    /**
     *
     */
    private void evtSalir() {
        pantalla.dispose();
    }

    /**
     *
     */
    private void evtBuscar() {
        fillTabla();
    }

    private void fillTabla() {
        // Desactivamos los botones
        pantalla.getBtnRechazar().setEnabled(false);
        pantalla.getBtnVerProcesado().setEnabled(false);
        pantalla.getBtnExcluir().setEnabled(false);
        pantalla.getBtnVerScripts().setEnabled(false);
        pantalla.getBtnCerrarPeticion().setEnabled(false);
        //Limpiamos la pantalla antes de rellenar la tabla
        CerrarPeticionTableModel model = (CerrarPeticionTableModel) pantalla.getTblProcesado().getModel();
        model.clearData();

        String codPeticion = pantalla.getTxtPeticion().getText();
        try {
            EntregaService service = (EntregaService) getService(MDSQLConstants.ENTREGA_SERVICE);
            OutputConsulta<ProcesadoPeticion> output = service.consultaProcesadosPeticion(codPeticion);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            //Actualizamos los datos de la tabla con el resultado
            model.setData(output.getLista());
            model.fireTableDataChanged();
            pantalla.getBtnCerrarPeticion().setEnabled(true);
            pantalla.getBtnVerScripts().setEnabled(true);
        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    /**
     *
     */
    private void evtBtnCambioEstado(MDSQLConstants.EstadosProcesado estado) {
        try {
            MDSQLUIHelper.cambioEstadoProcesado(pantalla, proceso.getIdProceso(), estado);
            fillTabla();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     *
     */
    private void evtBtnVerProcesado() {
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_PROCESO, proceso);
        params.put(MDSQLConstants.P_IN_ENTREGAR, Boolean.FALSE);
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaResumenProcesado.class, params);
    }

    /**
     *
     */
    private void evtBtnVerScripts() {
        String codPeticion = pantalla.getTxtPeticion().getText();
        BigDecimal codEstado = new BigDecimal(MDSQLConstants.EstadosProcesado.ENTREGADO.getIndex());
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_COD_PETICION, codPeticion);
        params.put(MDSQLConstants.P_IN_COD_ESTADO, codEstado);
        params.put(MDSQLConstants.P_IN_PROCESO, null); // Se muestran los scripts a nivel de peticion no de procesado
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaVerScriptsPeticion.class, params);
    }

    /**
     *
     */
    private void evtBtnCerrarPeticion() {
        try {
            String codPeticion = pantalla.getTxtPeticion().getText();

            //Recuperar los archivos del cierre
            EntregaService entregaService = (EntregaService) getService(MDSQLConstants.ENTREGA_SERVICE);
            OutputFicherosPeticion output = entregaService.prepararCierre(codPeticion);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            // Ejecutamos las acciones de los ficheros dispararando excepción si no se puede realizar la acción
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
            procesoService.ejecutarFicherosAccion(output, true);

            // Confirmar el cierre
            String descripcion = pantalla.getTxtDescripcion().getText();
            String versionado = pantalla.getTxtVersionado().getText();
            String Erwin = pantalla.getTxtErwin().getText();
            OutputConfirmaCierre outputConfirma = entregaService.confirmaCierre(codPeticion, descripcion, versionado, Erwin);
            MDSQLUIHelper.showWarnings(pantalla, outputConfirma.getWarnings());

            // Reconsultar la tabla
            fillTabla();

            //Verificar si se debe generar el informe
            if ("S".equals(outputConfirma.getMcaInformeTRN())) {
                MDSQLUIHelper.generaInformeTRN(pantalla);
            }

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
}
