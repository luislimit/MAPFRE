package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaProcesadoEnCurso;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.observer.Observer;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author federico
 *
 */
public class PantallaProcesadoEnCursoActionListener extends ListenerSupportModelo {

    private final PantallaProcesadoEnCurso pantalla;

    /**
     * @param pantalla
     *
     */
    public PantallaProcesadoEnCursoActionListener(PantallaProcesadoEnCurso pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    public void addObservador(Observer o) {
        this.addObserver(o);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (pantalla.getBtnVerProcesado().equals(obj)) {
            eventBtnVerProcesado();
        } else {
            super.actionPerformed(e);
        }

    }

    private void eventBtnVerProcesado() {
        Map<String, Object> params = new HashMap<>();

        Proceso seleccionado = pantalla.getProcesoSeleccionado();

        params.put("idProceso", seleccionado.getIdProceso());
        params.put("entregar", Boolean.FALSE);

        /*PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
        MDSQLUIHelper.show(pantallaResumenProcesado);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaResumenProcesado.class, params);
    }

    @Override
    public void onLoad() {
        try {
            Proceso proceso = (Proceso) pantalla.getParams().get("proceso");

            fillUltimasPeticiones(proceso.getModelo());

            fillAvisos(proceso.getModelo());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    /**
     * @param seleccionado
     */
    private void fillUltimasPeticiones(Modelo seleccionado) throws ServiceException {
        // Limpiar la tabla de peticiones
        ((ProcesarScriptUltimasPeticionesTableModel) pantalla.getTblUltimasPeticiones().getModel())
                .clearData();

        // Hacer la consulta
        InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();

        inputSeleccionarProcesados.setPCodigoproyecto(seleccionado.getCodigoProyecto());
        inputSeleccionarProcesados.setPUltimas(new BigDecimal(1));

        ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

        OutputConsulta<Proceso> output = procesoService.seleccionarProcesados(inputSeleccionarProcesados);
        List<Proceso> peticiones = output.getLista();
        if (CollectionUtils.isNotEmpty(peticiones)) {
            populateModelUltimasPeticiones(peticiones);
        }
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     * @param seleccionado
     */
    private void fillAvisos(Modelo seleccionado) throws ServiceException {
        // Limpiar la tabla de avisos
        ((ProcesarScriptNotaTableModel) pantalla.getTblNotas().getModel()).clearData();

        // Hacer la consulta
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
        OutputConsulta<Aviso> output = avisoService.consultaAvisosModelo(seleccionado.getCodigoProyecto());

        populateModelAvisos(output.getLista());
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     * @param peticiones
     */
    private void populateModelUltimasPeticiones(List<Proceso> peticiones) {
        // Obtiene el modelo y lo actualiza
        ProcesarScriptUltimasPeticionesTableModel tableModel = (ProcesarScriptUltimasPeticionesTableModel) pantalla
                .getTblUltimasPeticiones().getModel();
        tableModel.setData(peticiones);
    }

    /**
     * @param avisos
     */
    private void populateModelAvisos(List<Aviso> avisos) {
        // Obtiene el modelo y lo actualiza
        ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantalla.getTblNotas()
                .getModel();
        tableModel.setData(avisos);
    }
}
