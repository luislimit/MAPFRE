package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.*;
import com.mdsql.ui.*;
import com.mdsql.ui.model.HistoricoTableModel;
import com.mdsql.ui.model.StringComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.ConfigurationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaMantenimientoHistoricoListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaMantenimientoHistorico pantalla;

    public PantallaMantenimientoHistoricoListener(PantallaMantenimientoHistorico pantallaMantenimientoHistorico) {
        super();
        this.pantalla = pantallaMantenimientoHistorico;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
            eventBtnBuscarModelo();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR.equals(jButton.getActionCommand())) {
            eventBtnBuscar();
        }
        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_ALTA.equals(jButton.getActionCommand())) {
            eventBtnAlta();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BAJA.equals(jButton.getActionCommand())) {
            eventBtnBaja();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_INFORME.equals(jButton.getActionCommand())) {
            eventBtnInforme();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR.equals(jButton.getActionCommand())) {
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
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void eventBtnBuscar() {
        buscar();
    }

    private void eventBtnAlta() {
        Map<String, Object> params = new HashMap<>();

        /*PantallaHistoricoAlta pantallaHistoricoAlta = (PantallaHistoricoAlta) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
				MDSQLConstants.CMD_HISTORICO_ALTA, params);
		MDSQLUIHelper.show(pantallaHistoricoAlta);*/
        PantallaHistoricoAlta pantallaHistoricoAlta = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaHistoricoAlta.class, params);

        String response = (String) pantallaHistoricoAlta.getReturnParams().get("response");
        if (!Objects.isNull(response) && "OK".equals(response)) {
            buscar();
        }
    }

    private void eventBtnBaja() {
        Map<String, Object> params = new HashMap<>();

        params.put("historico", pantalla.getSeleccionado());
        params.put("modelo", pantalla.getModeloSeleccionado());

        /*PantallaHistoricoBaja pantallaHistoricoBaja = (PantallaHistoricoBaja) MDSQLUIHelper.createDialog(pantalla.getFrameParent(),
				MDSQLConstants.CMD_HISTORICO_BAJA, params);
		MDSQLUIHelper.show(pantallaHistoricoBaja);*/
        PantallaHistoricoBaja pantallaHistoricoBaja = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaHistoricoBaja.class, params);

        String response = (String) pantallaHistoricoBaja.getReturnParams().get("response");
        if (!Objects.isNull(response) && "OK".equals(response)) {
            buscar();
        }
    }

    private void eventBtnInforme() {
        informe();
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
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void buscar() {
        try {
            HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);

            String codigoProyecto = pantalla.getTxtModelo().getText();
            String tipoObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();

            OutputConsulta<Historico> output = historicoService.consultarHistorico(codigoProyecto, tipoObjeto);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            fillHistorico(output.getLista());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
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

    private void informe() {
        try {
            HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);

            String codigoProyecto = pantalla.getTxtModelo().getText();
            String tipoObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();

            OutputConsulta<Historico> output = historicoService.consultarHistorico(codigoProyecto, tipoObjeto);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            fillInforme(codigoProyecto, output.getLista());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void fillInforme(String codigoProyecto, List<Historico> lista) {
        try {
            ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            String path = configuration.getConfig("RutaInformes");
            String sufijo = configuration.getConfig("SufijoExcelObjHistorico");

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(pantalla.getFrameParent(), "No hay datos para generar informe");
            } else {
                excelGeneratorService.generarExcelHistorico(lista, path, sufijo, codigoProyecto, new Date());
            }
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void fillHistorico(List<Historico> list) throws ServiceException {
        // Obtiene el modelo y lo actualiza
        HistoricoTableModel tableModel = (HistoricoTableModel) pantalla
                .getTblMantenimientoHistorico().getModel();
        tableModel.clearData();

        tableModel.setData(list);
        pantalla.getBtnBaja().setEnabled(Boolean.FALSE);
    }
}
