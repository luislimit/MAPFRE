package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.EntornosPruebaService;
import com.mdsql.ui.PantallaMantenimientoEntornosPrueba;
import com.mdsql.ui.model.EntornosPruebaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import org.apache.commons.collections.CollectionUtils;

public class PantallaMantenimientoEntornosPruebaListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaMantenimientoEntornosPrueba pantalla;

    public PantallaMantenimientoEntornosPruebaListener(PantallaMantenimientoEntornosPrueba pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnGuardar())) {
            eventBtnGuardar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void eventBtnGuardar() {
        try {
            EntornosPruebaService entornosPruebaService = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codUsr = session.getCodUsr();

            EntornoPrueba entornoPrueba = new EntornoPrueba();

            String nombreEntorno = pantalla.getTxtNombreEntorno().getText();
            String bbdd = pantalla.getTxtBBDD().getText();
            String esquema = pantalla.getTxtEsquema().getText();
            String descripcion = pantalla.getTxtDescripcion().getText();
            String tablespace = pantalla.getTxtTablespace().getText();
            String gradoParal = pantalla.getTxtGradoparal().getText();
            String mcaHabilitado = AppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());

            entornoPrueba.setNombreEntorno(nombreEntorno);
            entornoPrueba.setBbdd(bbdd);
            entornoPrueba.setEsquema(esquema);
            entornoPrueba.setDescripcion(descripcion);
            entornoPrueba.setTablespace(tablespace);
            entornoPrueba.setGradoParal(new BigDecimal(gradoParal));
            entornoPrueba.setMcaHabilitado(mcaHabilitado);

            OutputWarning output = entornosPruebaService.guardarEntorno(entornoPrueba, codUsr);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            populateEntornosPrueba();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    @Override
    public void onLoad() {
        populateEntornosPrueba();
    }

    private void populateEntornosPrueba() {
        clearForm();
        try {
            EntornosPruebaService entornosPruebaService = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);

            OutputConsulta<EntornoPrueba> consultarEntornosPrueba = entornosPruebaService.consultarEntornos();

            if (CollectionUtils.isNotEmpty(consultarEntornosPrueba.getLista())) {
                // Obtiene el modelo y lo actualiza
                EntornosPruebaTableModel tableModel = (EntornosPruebaTableModel) pantalla.getTblEntornos().getModel();
                tableModel.clearData();
                tableModel.setData(consultarEntornosPrueba.getLista());
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void clearForm() {
        pantalla.getTxtNombreEntorno().setText("");
        pantalla.getTxtBBDD().setText("");
        pantalla.getTxtEsquema().setText("");
        pantalla.getTxtDescripcion().setText("");
        pantalla.getTxtTablespace().setText("");
        pantalla.getTxtGradoparal().setText("");
        pantalla.getChkHabilitada().setSelected(false);
    }
}
