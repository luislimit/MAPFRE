package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaSeleccionHistorico;
import com.mdsql.ui.model.SeleccionHistoricoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.ui.utils.collections.SeleccionHistoricoPredicate;
import com.mdsql.ui.utils.collections.SeleccionHistoricoUpdateClosure;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observable;
import com.mdval.ui.utils.observer.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.commons.collections.CollectionUtils;

public class PantallaSeleccionHistoricoListener extends ListenerSupport
        implements ActionListener, OnLoadListener, Observer {

    private final PantallaSeleccionHistorico pantalla;

    public PantallaSeleccionHistoricoListener(PantallaSeleccionHistorico pantalla) {
        super();
        this.pantalla = pantalla;
    }

    public void addObservador(Observer o) {
        this.addObserver(o);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_ADD.equals(jButton.getActionCommand())) {
            addToHistorico();
        }

        if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_GENERAR.equals(jButton.getActionCommand())) {
            generarHistorico();
        }

        if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
            cancelar();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onLoad() {
        try {
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

            String codigoProyecto = (String) pantalla.getParams().get("codigoProyecto");
            List<TextoLinea> lineas = (List<TextoLinea>) pantalla.getParams().get("script");

            OutputConsulta<SeleccionHistorico> output = procesoService.seleccionarHistorico(codigoProyecto, lineas);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            populateModelSeleccion(output.getLista());

            SeleccionHistoricoTableModel model = (SeleccionHistoricoTableModel) pantalla
                    .getTblHistorico().getModel();

            if (model.checkAllConfigured()) {
                pantalla.getBtnAddHistorico().setEnabled(Boolean.FALSE);
            }
        } catch (ServiceException e) {
            pantalla.setErrorOnload(Boolean.TRUE);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    private void addToHistorico() {
        try {
            ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codigoUsuario = session.getCodUsr();
            String codigoProyecto = (String) pantalla.getParams().get("codigoProyecto");
            String codigoPeticion = (String) pantalla.getParams().get("codigoPeticion");

            List<SeleccionHistorico> listaObjetos = ((SeleccionHistoricoTableModel) pantalla
                    .getTblHistorico().getModel()).getData();

            // Filtramos sólo los que tienen marcado el check de histórico
            List<SeleccionHistorico> listaSeleccionados = (List<SeleccionHistorico>) CollectionUtils
                    .select(listaObjetos, new SeleccionHistoricoPredicate());

            OutputWarning output = procesoService.altaHistorico(listaSeleccionados, codigoProyecto, codigoPeticion, codigoUsuario);

            CollectionUtils.forAllDo(listaObjetos, new SeleccionHistoricoUpdateClosure(listaSeleccionados));
            pantalla.getTblHistorico().repaint();

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void generarHistorico() {
        int dialogResult = MDSQLUIHelper.showConfirm("¿Desea continuar con el procesado?", "Atención");

        Boolean result = (dialogResult == JOptionPane.YES_OPTION) ? Boolean.TRUE : Boolean.FALSE;
        pantalla.getReturnParams().put("procesado", result);

        List<SeleccionHistorico> listaObjetos = ((SeleccionHistoricoTableModel) pantalla.getTblHistorico()
                .getModel()).getData();

        pantalla.getReturnParams().put("objetosHistorico", listaObjetos);

        updateObservers(MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_GENERAR);
        pantalla.dispose();
    }

    private void cancelar() {
        pantalla.getReturnParams().put("procesado", Boolean.FALSE);
        pantalla.dispose();
    }

    /**
     * @param avisos
     */
    private void populateModelSeleccion(List<SeleccionHistorico> seleccion) {
        // Obtiene el modelo y lo actualiza
        SeleccionHistoricoTableModel tableModel = (SeleccionHistoricoTableModel) pantalla
                .getTblHistorico().getModel();
        tableModel.setData(seleccion);
    }

    @Override
    public void update(Observable o, Object cmd) {
    }
}
