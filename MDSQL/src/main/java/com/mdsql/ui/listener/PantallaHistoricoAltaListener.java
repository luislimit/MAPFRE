package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.PantallaHistoricoAlta;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.StringComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaHistoricoAltaListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaHistoricoAlta pantalla;

    public PantallaHistoricoAltaListener(PantallaHistoricoAlta pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
            eventBtnBuscarModelo();
        }

        if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
            alta();
        }

        if (MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.getReturnParams().put("response", "KO");
            pantalla.dispose();
        }
    }

    private void eventBtnBuscarModelo() {
        try {
            Modelo seleccionado;
            Map<String, Object> params = new HashMap<>();

            String codigoProyecto = pantalla.getTxtModelo().getText();

            List<Modelo> modelos = buscarModelos(codigoProyecto, null, null);
            if (modelos.size() == 1) {
                seleccionado = modelos.get(0);
                pantalla.setModeloSeleccionado(seleccionado);
                pantalla.getTxtModelo().setText(seleccionado.getCodigoProyecto());
            } else {
                if (StringUtils.isNotBlank(codigoProyecto)) {
                    params.put("codigoProyecto", codigoProyecto);
                }
                /*
				PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
						MDSQLConstants.CMD_SEARCH_MODEL, params);
				MDSQLUIHelper.show(pantallaSeleccionModelos);*/
                PantallaSeleccionModelos pantallaSeleccionModelos = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionModelos.class, params);
                seleccionado = pantallaSeleccionModelos.getSeleccionado();
                pantalla.setModeloSeleccionado(seleccionado);
                pantalla.getTxtModelo().setText(seleccionado.getCodigoProyecto());
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        try {
            TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);

            // Rellenar combos
            OutputConsulta<String> output = tipoObjetoService.consultarTiposObjeto();

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            if (CollectionUtils.isNotEmpty(output.getLista())) {
                StringComboBoxModel tipoObjetoComboBoxModel = new StringComboBoxModel(output.getLista());
                pantalla.getCmbTipoObjeto().setModel(tipoObjetoComboBoxModel);
            }
        } catch (ServiceException e) {
            pantalla.getReturnParams().put("response", "KO");
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void alta() {
        try {
            HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codUsr = session.getCodUsr();

            Modelo modeloSeleccionado = pantalla.getModeloSeleccionado();

            if (!Objects.isNull(modeloSeleccionado)) {
                String codigoProyecto = modeloSeleccionado.getCodigoProyecto();
                String tipoObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
                String nombreObjeto = pantalla.getTxtNombreObjeto().getText();
                String peticion = pantalla.getTxtPeticion().getText();
                String historificada = AppHelper.normalizeValueToCheck(pantalla.getChkHistorificada().isSelected());

                OutputWarning output = historicoService.altaHistorico(codigoProyecto, nombreObjeto, tipoObjeto, historificada, peticion, codUsr);

                MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

                pantalla.getReturnParams().put("response", "OK");
            } else {
                pantalla.getReturnParams().put("response", "KO");
            }

            pantalla.dispose();
        } catch (ServiceException e) {
            pantalla.getReturnParams().put("response", "KO");
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }

    }

    /**
     * @param codModelo
     * @param nombreModelo
     * @param codSubmodelo
     * @return
     * @throws ServiceException
     */
    private List<Modelo> buscarModelos(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

        OutputConsulta<Modelo> output = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);

        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        return output.getLista();
    }
}
