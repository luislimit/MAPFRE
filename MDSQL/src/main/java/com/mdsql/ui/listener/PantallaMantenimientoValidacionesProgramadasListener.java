package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ProgramacionModelo;
import com.mdsql.bussiness.entities.ValidacionProgramada;
import com.mdsql.bussiness.service.ValidacionService;
import com.mdsql.ui.PantallaMantenimientoValidacionesProgramadas;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.ValidacionProgramadaComboBoxModel;
import com.mdsql.ui.model.ValidacionesProgramadasTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaMantenimientoValidacionesProgramadasListener extends ListenerSupportModelo implements ListSelectionListener, ItemListener {

    protected PantallaMantenimientoValidacionesProgramadas pantalla;
    private List<ProgramacionModelo> lista = null;

    public PantallaMantenimientoValidacionesProgramadasListener(PantallaMantenimientoValidacionesProgramadas pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void onLoad() {
        try {
            limpiaModelo();
            cargarValidacionesProgramadas();
            cargarAccion();
        } catch (ServiceException e) {
            pantalla.setErrorOnload(true);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnIncluirModificar())) {
            evtBtnIncluirModificar();
        } else if (obj.equals(pantalla.getBtnEliminar())) {
            evtBtnEliminar();
        } else if (obj.equals(pantalla.getBtnGuardar())) {
            evtBtnGuardar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.cerrarVentana();
        } else {
            super.actionPerformed(e);
        }
    }

    /**
     * Se ejecuta al seleccionar un elemento de la lista
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        int numRow = pantalla.getTblValidacion().getSelectedRow();
        if (numRow < 0) {
            return;
        }
        //Obtener el registro seleccionado
        ValidacionesProgramadasTableModel model = (ValidacionesProgramadasTableModel) pantalla.getTblValidacion().getModel();
        ProgramacionModelo selected = model.getSelectedRow(numRow);
        //Mostrar datos en pantalla
        pantalla.getTxtModeloProyecto().setText(selected.getCodigoProyecto());
        pantalla.getTxtModeloProyectoDescrip().setText(selected.getNombreModelo());
        pantalla.getChkHabilitada().setSelected(MDSQLAppHelper.normalizeCheckValue(selected.getMcaHabilitado()));

        CodigoDescripcion tipAccion = CodigoDescripcion.builder().codigo(selected.getTipAccion()).build();
        pantalla.getCmbAccion().setSelectedItem(tipAccion);
        pantalla.getCmbAccion().repaint();

        pantalla.getBtnIncluirModificar().setEnabled(true);
        pantalla.getBtnEliminar().setEnabled(true);
    }

    /**
     * Se ejecuta al cambiar la selección del comboBox cmbProcedimiento
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        //Verificar si hay cambios pendientes antes de cambiar de validación
        if (pantalla.isCambiosPendientes()
                && !MDSQLUIHelper.confirmAction(pantalla, "confirmacion.cambios.validaciones.programadas")) {
            return;
        }
        ValidacionProgramada procedimiento = (ValidacionProgramada) pantalla.getCmbProcedimiento().getSelectedItem();
        pantalla.getTxtDescripcion().setText(procedimiento.getDescripcion());
        //Rellenar la tabla con las programaciones por modelo para el procedimiento seleccionado
        fillTabla();
    }

    @Override
    public void procesarModelo() throws ServiceException {
        super.procesarModelo();
        limpiaDatos();
        //Habilitar botones si hay modelo seleccionado
        boolean estado = pantalla.getModelo() != null;
        pantalla.getBtnIncluirModificar().setEnabled(estado);
        pantalla.getBtnEliminar().setEnabled(estado);
        pantalla.getCmbAccion().setSelectedIndex(1); //Avisos
    }

    /**
     *
     */
    private void limpiaModelo() {
        pantalla.getTxtModeloProyecto().setText("");
        pantalla.getTxtModeloProyectoDescrip().setText("");
        pantalla.setModelo(null);
        pantalla.getBtnIncluirModificar().setEnabled(false);
        pantalla.getBtnEliminar().setEnabled(false);
        pantalla.getCmbAccion().setSelectedIndex(-1);
        limpiaDatos();
    }

    private void limpiaDatos() {
        pantalla.getCmbAccion().repaint();
        pantalla.getChkHabilitada().setSelected(true);
        pantalla.getTblValidacion().clearSelection();
    }

    /**
     *
     */
    private void actualizaTabla(boolean cambiosPendientes) {
        // Limpiar los datos del modelo y desactivar botones
        limpiaModelo();
        //
        ValidacionesProgramadasTableModel modelo = (ValidacionesProgramadasTableModel) pantalla.getTblValidacion().getModel();
        modelo.setData(lista);
        modelo.fireTableDataChanged();
        //Indicar si se han realizado cambios (se verifica al cerrar la pantalla)
        pantalla.setCambiosPendientes(cambiosPendientes);
    }

    /**
     *
     */
    private void fillTabla() {
        try {
            // Llamar a p_con_procedimiento con el valor seleccionado en cmbProcedimiento
            ValidacionProgramada procedimiento = (ValidacionProgramada) pantalla.getCmbProcedimiento().getSelectedItem();
            ValidacionService service = (ValidacionService) getService(MDSQLConstants.VALIDACION_SERVICE);
            OutputConsulta<ProgramacionModelo> output = service.consultaProgramacion(procedimiento.getCodigo());
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            lista = output.getLista();
            //
            actualizaTabla(false);
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     *
     */
    private void cargarValidacionesProgramadas() throws ServiceException {
        ValidacionService service = (ValidacionService) getService(MDSQLConstants.VALIDACION_SERVICE);
        OutputConsulta<ValidacionProgramada> output = service.validacionesProgramadas();
        ValidacionProgramadaComboBoxModel model = new ValidacionProgramadaComboBoxModel(output.getLista());
        pantalla.getCmbProcedimiento().setModel(model);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     *
     */
    private void cargarAccion() throws ServiceException {
        ValidacionService service = (ValidacionService) getService(MDSQLConstants.VALIDACION_SERVICE);
        OutputConsulta<CodigoDescripcion> output = service.consultaAcciones();
        CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista());
        pantalla.getCmbAccion().setModel(model);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     *
     */
    private void evtBtnGuardar() {
        try {
            // Si hay procedimiento seleccionado, se invoca a p_mnto_proc_programados
            ValidacionProgramada procedimiento = (ValidacionProgramada) pantalla.getCmbProcedimiento().getSelectedItem();
            ValidacionService service = (ValidacionService) getService(MDSQLConstants.VALIDACION_SERVICE);
            OutputWarning output = service.mntoProcProgramados(procedimiento.getCodigo(), lista);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            //Indicar que no hay cambios pendientes
            pantalla.setCambiosPendientes(false);
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     * Buscar el modelo en la Lista vinculada a la tabla
     *
     * @param codModeloProy
     * @return
     */
    private ProgramacionModelo buscarModeloEnTabla(String codModeloProy) {
        for (ProgramacionModelo p : lista) {
            if (p.getCodigoProyecto().equals(codModeloProy)) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     */
    private void evtBtnIncluirModificar() {
        // Buscar el modelo seleccionado en la tabla
        // Si existía actualizar la acción del elemento, sino añadir nuevo elemento a la tabla
        String codModeloProy = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion Accion = (CodigoDescripcion) pantalla.getCmbAccion().getSelectedItem();

        ProgramacionModelo p = buscarModeloEnTabla(codModeloProy);
        if (p == null) {
            p = new ProgramacionModelo();
            p.setCodigoProyecto(codModeloProy);
            p.setNombreModelo(pantalla.getTxtModeloProyectoDescrip().getText());
            p.setCodUsr(MDSQLAppHelper.getUsuario());
            p.setFecha(new Date());
            lista.add(p);
        }
        p.setTipAccion(Accion.getCodigo());
        p.setMcaHabilitado(MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected()));

        actualizaTabla(true);
    }

    /**
     *
     */
    private void evtBtnEliminar() {
        // Buscar el modelo seleccionado en la tabla
        String codModeloProy = pantalla.getTxtModeloProyecto().getText();
        // Si existía se elimina
        ProgramacionModelo p = buscarModeloEnTabla(codModeloProy);
        if (p != null) {
            lista.remove(p);
        }
        actualizaTabla(true);
    }

}
