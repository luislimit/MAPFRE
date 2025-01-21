package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Informe;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.service.InformeService;
import com.mdsql.ui.PantallaConsultaHistoricoCambiosModelo;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.HistoricoCambiosModeloTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 *
 * @author LVARONA
 */
public class PantallaConsultaHistoricoCambiosModeloListener extends ListenerSupportModelo {

    protected PantallaConsultaHistoricoCambiosModelo pantalla;

    public PantallaConsultaHistoricoCambiosModeloListener(PantallaConsultaHistoricoCambiosModelo pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void onLoad() {
        try {
            cargarTiposInforme();
            clearForm();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getBtnInforme())) {
            evtBtnInforme();
        } else {
            super.actionPerformed(e);
        }
    }

    /**
     * Se invoca cuando se selecciona el modelo
     */
    @Override
    public void clearForm() {
        super.clearForm();
        pantalla.getTxtDesde().setText("");
        pantalla.getTxtHasta().setText("");
        pantalla.getTxtElemento().setText("");
        pantalla.getCmbInforme().setSelectedIndex(-1);
        pantalla.getChkIncluirPermisosSinonimos().setSelected(false);
        pantalla.getBtnInforme().setEnabled(false);
    }

    /**
     *
     */
    private void fillTabla() {
        try {
            //Recuperar los datos de pantalla
            Modelo modelo = pantalla.getModelo();
            String codProyecto = modelo == null? null: modelo.getCodigoProyecto();
            
            String nombreObjeto = pantalla.getTxtElemento().getText();
            CodigoDescripcion tipoInforme = (CodigoDescripcion)pantalla.getCmbInforme().getSelectedItem();
            
            String tipo = (tipoInforme == null)? null: tipoInforme.getCodigo();
            
            String fechaDesde = pantalla.getTxtDesde().getText();
            String fechaHasta = pantalla.getTxtHasta().getText();
            String mcaPermisos = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkIncluirPermisosSinonimos().isSelected());
            
            InformeService service =(InformeService)MDSQLAppHelper.getBean(MDSQLConstants.INFORME_SERVICE);
            OutputConsulta<Informe> output = service.consultaInforme(codProyecto, tipo, nombreObjeto, fechaDesde, fechaHasta, mcaPermisos);
            
            HistoricoCambiosModeloTableModel model = (HistoricoCambiosModeloTableModel) pantalla.getTblHistoricoCambiosModelo().getModel();
            model.setData(output.getLista());
            model.fireTableDataChanged();

            // si se han recuperado datos activamos el botón de informes
            pantalla.getBtnInforme().setEnabled(true);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            
        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    /**
     *
     */
    private void cargarTiposInforme() throws ServiceException {
       InformeService service =(InformeService)MDSQLAppHelper.getBean(MDSQLConstants.INFORME_SERVICE);
       OutputConsulta<CodigoDescripcion> output = service.consultaTipoInforme();
       MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
       CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista());
       pantalla.getCmbInforme().setModel(model);
    }

    private void evtBtnBuscar() {
        fillTabla();
    }

    /**
     *
     */
    private void evtBtnInforme() {
        try {
            MDSQLUIHelper.exportTableToExcel(pantalla, pantalla.getTblHistoricoCambiosModelo());
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

}
