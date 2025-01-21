package com.mdsql.ui.listener.tables;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdsql.bussiness.service.CuadreService;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ResumenProcesadoObjetosTableModel;
import com.mdsql.ui.model.ResumenProcesadoOperacionesTableModel;
import com.mdsql.ui.model.ResumenProcesadoScriptsTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ResumenProcesadoScriptsTableListener extends ListenerSupport implements ListSelectionListener {

    private final PantallaResumenProcesado pantalla;

    public ResumenProcesadoScriptsTableListener(PantallaResumenProcesado pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            if (e.getValueIsAdjusting()) {
                return;
            }

            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            Integer index = lsm.getMinSelectionIndex();

            ResumenProcesadoScriptsTableModel tableModel = (ResumenProcesadoScriptsTableModel) pantalla.getTblScripts().getModel();

            ScriptEjecutado seleccionado = tableModel.getSelectedRow(index);

            if (!Objects.isNull(seleccionado)) {

                CuadreService cuadreService = (CuadreService) getService(MDSQLConstants.CUADRE_SERVICE);
                Proceso procesoSeleccionado = pantalla.getProcesoSeleccionado();
                BigDecimal idProceso = procesoSeleccionado.getIdProceso();

                OutputConsulta<CuadreOperacion> outputCuadreOperaciones = cuadreService.consultaCuadreOperacionesScript(idProceso,
                        seleccionado.getNumeroOrden());
                MDSQLUIHelper.showWarnings(pantalla, outputCuadreOperaciones.getWarnings());

                OutputConsulta<CuadreObjeto> outputCuadreObjetos = cuadreService.consultaCuadreOperacionesObjetoScript(idProceso,
                        seleccionado.getNumeroOrden());
                MDSQLUIHelper.showWarnings(pantalla, outputCuadreObjetos.getWarnings());

                populateTablaOperaciones(outputCuadreOperaciones.getLista());
                populateTablaObjetos(outputCuadreObjetos.getLista());

                pantalla.setSeleccionado(seleccionado);

                pantalla.getBtnVerErrores().setEnabled(Boolean.TRUE);
                pantalla.getBtnDetalleScript().setEnabled(Boolean.TRUE);
                pantalla.getBtnVerLog().setEnabled(Boolean.TRUE);
            }
        } catch (ServiceException se) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), se);
        }
    }

    /**
     * @param peticiones
     */
    private void populateTablaOperaciones(List<CuadreOperacion> cuadreOperaciones) {
        // Obtiene el modelo y lo actualiza
        ResumenProcesadoOperacionesTableModel tableModel = (ResumenProcesadoOperacionesTableModel) pantalla
                .getTblOperaciones().getModel();
        tableModel.setData(cuadreOperaciones);

        ((DefaultTableModel) pantalla.getTblOperaciones().getModel()).fireTableDataChanged();
    }

    /**
     * @param peticiones
     */
    private void populateTablaObjetos(List<CuadreObjeto> cuadreObjetos) {
        // Obtiene el modelo y lo actualiza
        ResumenProcesadoObjetosTableModel tableModel = (ResumenProcesadoObjetosTableModel) pantalla
                .getTblObjetos().getModel();
        tableModel.setData(cuadreObjetos);

        ((DefaultTableModel) pantalla.getTblObjetos().getModel()).fireTableDataChanged();
    }

}
