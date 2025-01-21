package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import com.mdsql.bussiness.entities.ErrorScript;
import com.mdsql.bussiness.entities.OutputErroresScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptParche;
import com.mdsql.bussiness.entities.Scriptable;
import com.mdsql.bussiness.service.ErroresService;
import com.mdsql.ui.PantallaVerErroresScript;
import com.mdsql.ui.model.VerErroresScriptTableModel;
import com.mdsql.ui.model.VerParchesScriptTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
//import com.mdval.ui.utils.observer.Observer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PantallaVerErroresScriptListener extends ListenerSupport implements ActionListener, OnLoadListener, ListSelectionListener {

    private final PantallaVerErroresScript pantalla;

    public PantallaVerErroresScriptListener(PantallaVerErroresScript pantallaVerErroresScript) {
        super();
        this.pantalla = pantallaVerErroresScript;
    }
/*
    public void addObservador(Observer o) {
        this.addObserver(o);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    @Override
    public void onLoad() {
        try {
            ErroresService erroresService = (ErroresService) getService(MDSQLConstants.ERRORES_SERVICE);

            Proceso proceso = (Proceso) pantalla.getParams().get("proceso");
            String tipo = (String) pantalla.getParams().get("tipo");

            BigDecimal idProceso = proceso.getIdProceso();
            BigDecimal numeroOrden;
            if ("type".equals(tipo)) {
                numeroOrden = (BigDecimal) pantalla.getParams().get("numeroOrden");
            } else {
                Scriptable script = (Scriptable) pantalla.getParams().get("script");
                numeroOrden = script.getNumeroOrden();
            }

            /**
             * Se llamará al método específico según se estén ejecutando scripts
             * o scripts tipo
             */
            List<ErrorScript> errores;
            List<ScriptParche> parches = null;

            if ("type".equals(tipo)) {
                errores = erroresService.consultaErroresType(idProceso, numeroOrden);
            } else {
                OutputErroresScript outputErroresScript = erroresService.consultaErroresScript(idProceso, numeroOrden);
                errores = outputErroresScript.getListaErroresScript();
                parches = outputErroresScript.getListaScriptParche();
            }

            populateErrores(errores);
            populateParches(parches);

        } catch (ServiceException e) {
            pantalla.setErrorOnload(Boolean.TRUE);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param errores
     */
    private void populateErrores(List<ErrorScript> errores) {
        // Obtiene el modelo y lo actualiza
        VerErroresScriptTableModel tableModel = (VerErroresScriptTableModel) pantalla
                .getTblErroresScript().getModel();
        tableModel.setData(errores);
    }

    /**
     * @param parches
     */
    private void populateParches(List<ScriptParche> parches) {
        // Obtiene el modelo y lo actualiza
        VerParchesScriptTableModel tableModel = (VerParchesScriptTableModel) pantalla
                .getTblParches().getModel();
        tableModel.setData(parches);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = pantalla.getTblErroresScript().getSelectedRow();

        VerErroresScriptTableModel tableModel = (VerErroresScriptTableModel) pantalla.getTblErroresScript().getModel();
        ErrorScript seleccionado = tableModel.getData().get(row);
        // Desmarcamos la tabla
        pantalla.getTxtError().setText(seleccionado.getTxtError());
    }
}
