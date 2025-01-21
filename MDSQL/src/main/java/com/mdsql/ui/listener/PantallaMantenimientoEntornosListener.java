package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Entorno;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.EntornoService;
import com.mdsql.ui.PantallaMantenimientoEntornos;
import com.mdsql.ui.model.EntornoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;

public class PantallaMantenimientoEntornosListener extends ListenerSupport implements ActionListener {

    private final PantallaMantenimientoEntornos pantalla;

    public PantallaMantenimientoEntornosListener(PantallaMantenimientoEntornos pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR.equals(jButton.getActionCommand())) {
            eventBtnBuscar();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR.equals(jButton.getActionCommand())) {
            eventBtnGrabar();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.dispose();
        }
    }

    private void eventBtnBuscar() {
        try {
            actualizarEntornos();
        } catch (ServiceException | IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void eventBtnGrabar() {
        try {
            EntornoService entornoService = (EntornoService) getService(MDSQLConstants.ENTORNO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codUsr = session.getCodUsr();

            String claveEncriptacion = ConfigurationSingleton.getInstance().getConfig("TOKEN");

            String nomBBDD = pantalla.getTxtBBDD().getText();
            String nomEsquema = pantalla.getTxtEsquema().getText();
            String password = pantalla.getTxtPassword().getText();
            String mcaHabilitado = AppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
            String comentario = pantalla.getTxtComentario().getText();

            OutputWarning output = entornoService.guardarEntorno(nomBBDD, nomEsquema, claveEncriptacion, password, mcaHabilitado, comentario, codUsr);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            
            clearForm();
            actualizarEntornos();
        } catch (ServiceException | IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void clearForm() {
        pantalla.getTxtBBDD().setText(StringUtils.EMPTY);
        pantalla.getTxtEsquema().setText(StringUtils.EMPTY);
        pantalla.getTxtPassword().setText(StringUtils.EMPTY);
        pantalla.getChkHabilitada().setSelected(Boolean.FALSE);
        pantalla.getTxtComentario().setText(StringUtils.EMPTY);

        pantalla.getBtnGrabar().setEnabled(Boolean.TRUE);
    }

    private void actualizarEntornos() throws ServiceException, IOException {
        EntornoService entornoService = (EntornoService) getService(MDSQLConstants.ENTORNO_SERVICE);
        String claveEncriptacion = ConfigurationSingleton.getInstance().getConfig("TOKEN");

        String nomBBDD = pantalla.getTxtBBDD().getText();
        String nomEsquema = pantalla.getTxtEsquema().getText();
        String mcaHabilitado = AppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());

        OutputConsulta<Entorno> output = entornoService.consultarEntornos(nomBBDD, nomEsquema, claveEncriptacion, mcaHabilitado);

        // Hay avisos
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        fillEntornos(output.getLista());
    }

    private void fillEntornos(List<Entorno> list) throws ServiceException {
        // Obtiene el modelo y lo actualiza
        EntornoTableModel tableModel = (EntornoTableModel) pantalla
                .getTblMantenimientoEntornos().getModel();
        tableModel.clearData();

        tableModel.setData(list);
    }
}
