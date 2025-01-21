package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.service.CuadreService;
import com.mdsql.ui.PantallaVerCuadresScript;
import com.mdsql.ui.model.CuadresObjetosTableModel;
import com.mdsql.ui.model.CuadresOperacionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observer;

public class PantallaVerCuadresScriptListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaVerCuadresScript pantalla;

    public PantallaVerCuadresScriptListener(PantallaVerCuadresScript pantalla) {
        super();
        this.pantalla = pantalla;
    }

    public void addObservador(Observer o) {
        this.addObserver(o);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnCancelar())) {
            cancelar();
        }
    }

    @Override
    public void onLoad() {
        try {
            CuadreService cuadreService = (CuadreService) getService(MDSQLConstants.CUADRE_SERVICE);

            Script script = (Script) pantalla.getParams().get("script");
            Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
            BigDecimal orden = (BigDecimal) pantalla.getParams().get("orden");

            BigDecimal idProceso = proceso.getIdProceso();
            BigDecimal numeroOrden = (!Objects.isNull(script)) ? script.getNumeroOrden() : orden;

            OutputConsulta<CuadreOperacion> outputCuadreOperaciones = cuadreService.consultaCuadreOperacionesScript(idProceso,
                    numeroOrden);
            MDSQLUIHelper.showWarnings(pantalla, outputCuadreOperaciones.getWarnings());

            OutputConsulta<CuadreObjeto> outputCuadreObjetos = cuadreService.consultaCuadreOperacionesObjetoScript(idProceso,
                    numeroOrden);
            MDSQLUIHelper.showWarnings(pantalla, outputCuadreObjetos.getWarnings());

            populateModels(outputCuadreOperaciones.getLista(), outputCuadreObjetos.getLista());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void cancelar() {
        pantalla.dispose();
    }

    /**
     * @param cuadreOperaciones
     * @param cuadreObjetos
     */
    private void populateModels(List<CuadreOperacion> cuadreOperaciones, List<CuadreObjeto> cuadreObjetos) {
        // Obtiene el modelo y lo actualiza
        CuadresOperacionesTableModel tableModelOperaciones = (CuadresOperacionesTableModel) pantalla
                .getTblOperaciones().getModel();
        tableModelOperaciones.setData(cuadreOperaciones);

        // Obtiene el modelo y lo actualiza
        CuadresObjetosTableModel tableModelObjetos = (CuadresObjetosTableModel) pantalla
                .getTblObjetos().getModel();
        tableModelObjetos.setData(cuadreObjetos);
    }
}
