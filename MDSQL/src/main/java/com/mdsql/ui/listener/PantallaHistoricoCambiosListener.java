package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.InformeCambios;
import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.service.ConsultaService;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.bussiness.service.InformeService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.PantallaHistoricoCambios;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.EstadoComboBoxModel;
import com.mdsql.ui.model.HistoricoObjetoTableModel;
import com.mdsql.ui.model.StringComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.ConfigurationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

public class PantallaHistoricoCambiosListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaHistoricoCambios pantalla;

    public PantallaHistoricoCambiosListener(PantallaHistoricoCambios pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
            buscarModelo();
        }
        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR.equals(jButton.getActionCommand())) {
            buscar();
        }
        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS.equals(jButton.getActionCommand())) {
            informeCambios();
        }
        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT.equals(jButton.getActionCommand())) {
            verDetalleScript();
        }
        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO.equals(jButton.getActionCommand())) {
            resumenProcesado();
        }
        if (MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_CANCELAR.equals(jButton.getActionCommand())) {
            cancelar();
        }
    }

    private void cancelar() {
        pantalla.dispose();

    }

    private void resumenProcesado() {
        Map<String, Object> params = new HashMap<>();

        HistoricoProceso seleccionado = pantalla.getSeleccionado();

        Proceso proceso = MDSQLAppHelper.buildProceso(seleccionado.getIdProceso());

        params.put("proceso", proceso);
        params.put("entregar", Boolean.FALSE);

        /*PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
        MDSQLUIHelper.show(pantallaResumenProcesado);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaResumenProcesado.class, params);
    }

    private void verDetalleScript() {
        Map<String, Object> params = new HashMap<>();

        HistoricoProceso seleccionado = pantalla.getSeleccionado();

        params.put("script", seleccionado.getNombreScript());
        params.put("proceso", seleccionado.getIdProceso());
        params.put("numeroOrden", seleccionado.getNumeroOrden());

        /*PantallaDetalleScript pantallaDetalleScript = (PantallaDetalleScript) MDSQLUIHelper
                .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_DETALLE_SCRIPT, params);
        MDSQLUIHelper.show(pantallaDetalleScript);*/
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaDetalleScript.class, params);
    }

    private void informeCambios() {
        try {
            InformeService informeService = (InformeService) getService(MDSQLConstants.INFORME_SERVICE);
            ExcelGeneratorService excelGeneratorService = (ExcelGeneratorService) getService(MDSQLConstants.EXCEL_GENERATOR_SERVICE);

            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            String path = configuration.getConfig("RutaInformes");

            String codigoProyecto = pantalla.getTxtModelo().getText().toUpperCase();
            String fechaDesde = pantalla.getTxtDesde().getText();
            String fechaHasta = pantalla.getTxtHasta().getText();

            OutputConsulta<InformeCambios> output = informeService.informeCambios(codigoProyecto, fechaDesde, fechaHasta);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            List<InformeCambios> listaCambios = output.getLista();

            if (listaCambios.isEmpty()) {
                JOptionPane.showMessageDialog(pantalla.getFrameParent(), "No hay datos para generar informe");
            } else {
                excelGeneratorService.generarExcelHistoricoCambios(listaCambios, path, codigoProyecto, fechaDesde, fechaHasta);
            }
        } catch (ServiceException | IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void buscar() {
        try {
            HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);

            InputConsutaHistoricoProceso inputConsutaHistoricoProceso = new InputConsutaHistoricoProceso();

            String codigoProyecto = pantalla.getTxtModelo().getText();
            inputConsutaHistoricoProceso.setCodigoProyecto(codigoProyecto);

            String nombreObjetoPadre = pantalla.getTxtObjetoPadre().getText();
            inputConsutaHistoricoProceso.setNombreObjetoPadre(nombreObjetoPadre);

            String tipoObjetoPadre = (String) pantalla.getCmbTipoObjetoPadre().getSelectedItem();
            inputConsutaHistoricoProceso.setTipoObjetoPadre(tipoObjetoPadre);

            CodigoDescripcion operacionPadre = (CodigoDescripcion) pantalla.getCmbOperacionPadre().getSelectedItem();
            String tipoAccionPadre = (!Objects.isNull(operacionPadre)) ? operacionPadre.getCodigo() : null;
            inputConsutaHistoricoProceso.setTipoAccionPadre(tipoAccionPadre);

            String nombreObjeto = pantalla.getTxtObjeto().getText();
            inputConsutaHistoricoProceso.setNombreObjeto(nombreObjeto);

            String tipoObjeto = (String) pantalla.getCmbTipoObjeto().getSelectedItem();
            inputConsutaHistoricoProceso.setTipoObjeto(tipoObjeto);

            CodigoDescripcion operacion = (CodigoDescripcion) pantalla.getCmbOperacion().getSelectedItem();
            String tipoAccion = (!Objects.isNull(operacion)) ? operacion.getCodigo() : null;
            inputConsutaHistoricoProceso.setTipoAccion(tipoAccion);

            inputConsutaHistoricoProceso.setFechaDesde(pantalla.getTxtDesde().getText());
            inputConsutaHistoricoProceso.setFechaHasta(pantalla.getTxtHasta().getText());

            Estado estadoScript = (Estado) pantalla.getCmbEstadoScript().getSelectedItem();
            BigDecimal codigoEstadoScript = (!Objects.isNull(estadoScript)) ? estadoScript.getCodigoEstado() : null;
            inputConsutaHistoricoProceso.setCodigoEstadoScript(codigoEstadoScript);

            Estado estadoProcesado = (Estado) pantalla.getCmbEstadoProcesado().getSelectedItem();
            BigDecimal codigoEstadoProcesado = (!Objects.isNull(estadoProcesado)) ? estadoProcesado.getCodigoEstado() : null;
            inputConsutaHistoricoProceso.setCodigoEstadoProceso(codigoEstadoProcesado);

            OutputConsulta<HistoricoProceso> output = historicoService.consultarHistoricoObjeto(inputConsutaHistoricoProceso);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            populateModel(output.getLista());

            pantalla.getBtnResumen().setEnabled(Boolean.FALSE);
            pantalla.getBtnVerDetalle().setEnabled(Boolean.FALSE);

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);

        }

    }

    private void buscarModelo() {
        try {
            Map<String, Object> params = new HashMap<>();
            Modelo seleccionado = null;

            String codigoProyecto = pantalla.getTxtModelo().getText();
            List<Modelo> modelos = recuperarModelos(codigoProyecto, null, null);
            if (modelos.size() == 1) {
                seleccionado = modelos.get(0);
            } else {
                if (StringUtils.isNotBlank(codigoProyecto)) {
                    params.put("codigoProyecto", codigoProyecto);
                }

                /*PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper
                        .createDialog(pantalla.getFrameParent(), MDSQLConstants.CMD_SEARCH_MODEL, params);
                MDSQLUIHelper.show(pantallaSeleccionModelos);*/
                PantallaSeleccionModelos pantallaSeleccionModelos = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionModelos.class, params);
                seleccionado = pantallaSeleccionModelos.getSeleccionado();
            }

            if (!Objects.isNull(seleccionado)) {
                pantalla.getTxtModelo().setText(seleccionado.getCodigoProyecto());
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * @param modelos
     */
    private void populateModel(List<HistoricoProceso> historicos) {
        // Obtiene el modelo y lo actualiza
        HistoricoObjetoTableModel tableModel = (HistoricoObjetoTableModel) pantalla
                .getTblHistoricoObjetos().getModel();
        tableModel.setData(historicos);

        pantalla.forceRepaint();
    }

    /**
     * @param codModelo
     * @param nombreModelo
     * @param codSubmodelo
     * @return
     * @throws ServiceException
     */
    private List<Modelo> recuperarModelos(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

        OutputConsulta<Modelo> output = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);

        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        return output.getLista();
    }

    @Override
    public void onLoad() {
        try {
            ConsultaService consultaService = (ConsultaService) getService(MDSQLConstants.CONSULTA_SERVICE);

            List<String> tiposObjeto = consultaService.consultaTiposObjeto();
            pantalla.getCmbTipoObjeto().setModel(new StringComboBoxModel(tiposObjeto));
            pantalla.getCmbTipoObjetoPadre().setModel(new StringComboBoxModel(tiposObjeto));

            OutputConsulta<CodigoDescripcion> operaciones = consultaService.consultaOperaciones();
            MDSQLUIHelper.showWarnings(pantalla, operaciones.getWarnings());

            pantalla.getCmbOperacion().setModel(new CodigoDescripcionComboBoxModel(operaciones.getLista()));
            pantalla.getCmbOperacionPadre().setModel(new CodigoDescripcionComboBoxModel(operaciones.getLista()));

            List<Estado> estadosScript = consultaService.consultaEstadosScript();
            pantalla.getCmbEstadoScript().setModel(new EstadoComboBoxModel(estadosScript));

            OutputConsulta<Estado> outputEstados = consultaService.consultaEstadosProcesado();
            pantalla.getCmbEstadoProcesado().setModel(new EstadoComboBoxModel(outputEstados.getLista()));
            MDSQLUIHelper.showWarnings(pantalla, outputEstados.getWarnings());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
}
