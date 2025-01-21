package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaDetalleAvisosPorModelo;
import com.mdsql.ui.PantallaMantenimientoAvisos;
import com.mdsql.ui.adapter.DoubleClickable;
import com.mdsql.ui.model.AvisosModeloTableModel;
import com.mdsql.ui.model.NivelesImportanciaComboBoxModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaDetalleAvisosPorModeloListener extends ListenerSupportModeloPermiso implements ListSelectionListener, DoubleClickable {

    protected PantallaDetalleAvisosPorModelo pantalla;

    public PantallaDetalleAvisosPorModeloListener(PantallaDetalleAvisosPorModelo pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getBtnModificacion())) {
            evtBtnModificacion();
        } else if (obj.equals(pantalla.getBtnAlta())) {
            evtBtnAlta();
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtBtnBuscar() {
        refrescaTabla();
    }

    public void evtBtnModificacion() {
        int idx = pantalla.getTblAvisos().getSelectedRow();
        if (idx >= 0) {
            DefaultTableModel tableModel = (DefaultTableModel) pantalla.getTblAvisos().getModel();
            Object seleccionado = tableModel.getSelectedRow(idx);
            showFormMantenimiento(seleccionado);
        }
    }

    private void evtBtnAlta() {
        showFormMantenimiento(null);
    }

    private void showFormMantenimiento(Object objeto) {
        Map<String, Object> params = new HashMap<>();

        params.put(MDSQLConstants.P_IN_MODELO, pantalla.getModelo());

        if (objeto != null) {
            params.put(MDSQLConstants.P_IN_OBJETO, objeto);
        }

        PantallaMantenimientoAvisos dialog
                = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaMantenimientoAvisos.class, params);

        Boolean dataChanged = (Boolean) dialog.getReturnParams().get(MDSQLConstants.P_OUT_DATA_CHANGED);
        // Si viene de un alta siempre se reconsultan las tablas, si es modificacion solo si ha guardado
        if (dataChanged != null && dataChanged) {
            //clearForm();
            refrescaTabla();
        }
    }

    /**
     * Se ejecutar al seleccionar un elemento de la lista
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        pantalla.getBtnModificacion().setEnabled(true);
    }

    /**
     * Se ejecuta al presionar dobleclick sobre la tabla
     */
    @Override
    public void evtOnDoubleClick() {
        evtBtnModificacion();
    }

    private void fillTabla(List lista) {
        // Obtener el modelo de la tabla
        AvisosModeloTableModel tableModel = (AvisosModeloTableModel) pantalla.getTblAvisos().getModel();
        //Limpiamos la tabla
        tableModel.clearData();
        //Establecemos los datos
        if (lista != null) {
            tableModel.setData(lista);
        }
        tableModel.fireTableDataChanged();
    }

    private void refrescaTabla() {
        try {
            // Datos de parámetros
            String codigoProyecto = pantalla.getTxtModeloProyecto().getText();
            String codPeticion = pantalla.getTxtPeticion().getText();
            NivelImportancia nivelImportancia = (NivelImportancia) pantalla.getCmbImportancia().getSelectedItem();
            String codNivelAviso = nivelImportancia == null ? "" : nivelImportancia.getCodigoNivelAviso().toString();
            String nomObjeto = pantalla.getTxtNombreObjeto().getText();
            String mcaHabilitado = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
            // Invocar a p_con_avisos_objeto
            AvisoService service = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
            OutputConsulta<Aviso> output = service.consultaAvisosObjeto(codigoProyecto, codPeticion, codNivelAviso, nomObjeto, mcaHabilitado);
            // Cargar la lista en la tabla
            fillTabla(output.getLista());
            //Se activará al seleccionar un elemento de la lista
            pantalla.getBtnModificacion().setEnabled(false);
            // Mostrar avisos después de actualizar la tabla
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        try {
            super.onLoad();
            setDefHabilitada(true); // Se establece para cuando se haga un clearForm
            clearForm();
            cargarNivelesImportancia();
            pantalla.getBtnAlta().setEnabled(false);
        } catch (ServiceException e) {
            pantalla.setErrorOnload(Boolean.TRUE);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void cargarNivelesImportancia() throws ServiceException {
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);

        OutputConsulta<NivelImportancia> output = avisoService.consultaNivelesImportancia();
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        NivelesImportanciaComboBoxModel nivelesImportanciaComboBoxModel = new NivelesImportanciaComboBoxModel(output.getLista());
        pantalla.getCmbImportancia().setModel(nivelesImportanciaComboBoxModel);
    }

    /**
     * Se ejecuta al seleccionar un modelo
     *
     * @throws ServiceException
     */
    @Override
    public void procesarModelo() throws ServiceException {
        super.procesarModelo();
        //Habilitar el botón de Alta si hay modelo seleccionado
        pantalla.getBtnAlta().setEnabled(true);
    }

    /**
     * Se ejecuta antes de cambiar el modelo, limpia las dependencias de este
     */
    @Override
    public void clearDepsModelo() {
        super.clearDepsModelo();
        pantalla.getBtnAlta().setEnabled(false);
        pantalla.getBtnModificacion().setEnabled(false);
    }

    /**
     * Se ejecuta antes de cambiar el modelo, limpia los valores de pantalla y
     * al presionar el botón Limpiar
     */
    @Override
    public void clearForm() {
        super.clearForm();
        pantalla.getCmbImportancia().setSelectedIndex(-1);
        pantalla.getTxtNombreObjeto().setText("");
        pantalla.getTxtPeticion().setText("");
        pantalla.getBtnModificacion().setEnabled(false);
        fillTabla(null);
    }

}
