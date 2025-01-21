package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaInformacionModelo;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PantallaInformacionModeloListener extends ListenerSupport implements OnLoadListener, ListSelectionListener {

    private final PantallaInformacionModelo pantalla;

    public PantallaInformacionModeloListener(PantallaInformacionModelo pantallaInformacionModelo) {
        super();
        this.pantalla = pantallaInformacionModelo;
    }

    @Override
    public void onLoad() {
        try {
            AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);

            String codigoProyecto = (String) pantalla.getParams().get("codigoProyecto");

            pantalla.getTxtModeloProyecto().setText(codigoProyecto);

            OutputConsulta<Aviso> output = avisoService.consultaAvisosModelo(codigoProyecto);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            populateModelAvisos(output.getLista());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param avisos
     */
    private void populateModelAvisos(List<Aviso> avisos) {
        // Obtiene el modelo y lo actualiza
        ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantalla.getTblInformacion()
                .getModel();
        tableModel.setData(avisos);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        JTable tblInformacion = pantalla.getTblInformacion();
        int row = tblInformacion.getSelectedRow();

        if (row >= 0) {
            ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) tblInformacion.getModel();
            Aviso aviso = tableModel.getData().get(row);
            pantalla.getTxtDescripcionAviso().setText(aviso.getDescripcion());
        }
    }
}
