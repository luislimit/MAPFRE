package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.model.DetalleScriptTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaDetalleScriptListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaDetalleScript pantalla;

    public PantallaDetalleScriptListener(PantallaDetalleScript pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    @Override
    public void onLoad() {
        try {
            ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);

            BigDecimal idProceso = (BigDecimal) pantalla.getParams().get(MDSQLConstants.P_IN_PROCESO);
            BigDecimal numeroOrden = (BigDecimal) pantalla.getParams().get(MDSQLConstants.P_IN_ORDEN);

            OutputConsulta<DetObjeto> output = scriptService.detalleObjetosScripts(idProceso, numeroOrden);

            populateModel(output.getLista());
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param modelos
     */
    private void populateModel(List<DetObjeto> objetos) {
        // Obtiene el modelo y lo actualiza
        DetalleScriptTableModel tableModel = (DetalleScriptTableModel) pantalla
                .getTblDetalle().getModel();
        tableModel.setData(objetos);

        pantalla.getTblDetalle().repaint();
    }
}
