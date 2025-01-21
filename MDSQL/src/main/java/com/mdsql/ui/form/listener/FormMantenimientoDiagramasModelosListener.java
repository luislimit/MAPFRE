package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdsql.bussiness.service.SubjectAreaDiagramaService;
import com.mdsql.ui.form.FormEliminarTablasDiagrama;
import com.mdsql.ui.form.FormMantenimientoDiagrama;
import com.mdsql.ui.form.FormNuevoDiagrama;
import com.mdsql.ui.form.FormMantenimientoDiagramasModelos;
import com.mdsql.ui.form.FormNuevoSubjectArea;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.DiagramasTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author LVARONA
 */
public class FormMantenimientoDiagramasModelosListener extends ListenerSupportModelo implements ActionListener, ListSelectionListener {

    protected FormMantenimientoDiagramasModelos pantalla;

    public FormMantenimientoDiagramasModelosListener(FormMantenimientoDiagramasModelos pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnNuevoSubjectArea())) {
            evtBtnNuevoSubjectArea();
        } else if (obj.equals(pantalla.getBtnNuevoDiagrama())) {
            evtBtnNuevoDiagrama();
        } else if (obj.equals(pantalla.getBtnIncluir())) {
            evtBtnIncluir();
        } else if (obj.equals(pantalla.getBtnEliminar())) {
            evtBtnEliminar();
        } else if (obj.equals(pantalla.getBtnGuardar())) {
            evtBtnGuardar();
        } else if (obj.equals(pantalla.getCmbSubjectArea())) {
            evtCmbSubjectArea();
        } else if (obj.equals(pantalla.getCmbDiagrama())) {
            evtCmbDiagrama();
        } else {
            super.actionPerformed(e);
        }
    }

    /**
     * Se dispara al cambiar el elemento seleccionado en la tabla tblDiagramas
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        pantalla.getBtnEliminar().setEnabled(true);
    }

    /**
     *
     * @return Objeto TablasDiagrama seleccionado de la tabla tblDiagramas
     */
    private TablasDiagrama getTblDiagramasSelection() {
        int row = pantalla.getTblDiagramas().getSelectedRow();
        TablasDiagrama tablasDiagrama = null;
        if (row >= 0) {
            pantalla.getBtnEliminar().setEnabled(true);
            DiagramasTableModel tableModel = (DiagramasTableModel) pantalla.getTblDiagramas().getModel();
            tablasDiagrama = tableModel.getData().get(row);
        }
        return tablasDiagrama;
    }

    private void evtBtnNuevoSubjectArea() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_COD_MODELO, codProyecto);

        FormNuevoSubjectArea dialogo;
        dialogo = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormNuevoSubjectArea.class, params);

        String result = (String) dialogo.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (result != null && result.equals(MDSQLConstants.BTN_GUARDAR)) {
            cargarSubjectArea();
        }
    }

    private void evtBtnNuevoDiagrama() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();

        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_COD_MODELO, codProyecto);
        params.put(MDSQLConstants.P_IN_COD_SUBJECT_AREA, subjectArea.getCodigo());

        FormNuevoDiagrama dialogo;
        dialogo = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormNuevoDiagrama.class, params);

        String result = (String) dialogo.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (result != null && result.equals(MDSQLConstants.BTN_GUARDAR)) {
            evtCmbSubjectArea();
        }
    }

    private void evtBtnIncluir() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();
        CodigoDescripcion diagrama = (CodigoDescripcion) pantalla.getCmbDiagrama().getSelectedItem();

        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_COD_MODELO, codProyecto);
        params.put(MDSQLConstants.P_IN_COD_SUBJECT_AREA, subjectArea.getCodigo());
        params.put(MDSQLConstants.P_IN_COD_DIAGRAMA, diagrama.getCodigo());

        FormMantenimientoDiagrama dialogo;
        dialogo = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormMantenimientoDiagrama.class, params);

        String result = (String) dialogo.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (result != null && result.equals(MDSQLConstants.BTN_GUARDAR)) {
            fillTblDiagramas(codProyecto, subjectArea.getCodigo(), diagrama.getCodigo());
        }
    }

    private void evtBtnEliminar() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        // Recuperar seleccionada en la tabla
        TablasDiagrama td = getTblDiagramasSelection();
        String subjectArea = td.getCodSubjectArea();
        String diagrama = td.getCodDiagrama();
        //Traspaso de parámetros
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_COD_MODELO, codProyecto);
        params.put(MDSQLConstants.P_IN_OBJETO, getTblDiagramasSelection());

        FormEliminarTablasDiagrama dialogo
                = MDSQLUIHelper.showForm(pantalla.getFrameParent(), FormEliminarTablasDiagrama.class, params);

        String result = (String) dialogo.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON);
        if (result != null && result.equals(MDSQLConstants.BTN_GUARDAR)) {
            fillTblDiagramas(codProyecto, subjectArea, diagrama);
        }
    }

    private void evtBtnGuardar() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();
        CodigoDescripcion diagrama = (CodigoDescripcion) pantalla.getCmbDiagrama().getSelectedItem();
        //
        String codSubjectArea = (subjectArea != null) ? subjectArea.getCodigo() : null;
        String codDiagrama = (diagrama != null) ? diagrama.getCodigo() : null;
        String desSubjectArea = pantalla.getTxtDescripcionSubjectArea().getText();
        String desDiagrama = pantalla.getTxtDescripcionDiagrama().getText();

        String usuario = MDSQLAppHelper.getUsuario();
        OutputWarning output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);
            //Guardar las descripciones de SubjectArea y Diagrama en BBDD
            output = service.mantenimientoDiagrama(codProyecto, codSubjectArea, desSubjectArea, codDiagrama, desDiagrama, usuario);
            // Actualizamos los datos también en memoria
            if (subjectArea != null) {
                subjectArea.setDescripcion(desSubjectArea);
            }
            if (diagrama != null) {
                diagrama.setDescripcion(desDiagrama);
            }

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void cargarSubjectArea() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        OutputConsulta<CodigoDescripcion> output;

        clearSubjectArea(false); //Limpiar subjectArea y sus dependencias 

        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.ConsultaSubjectAreasModelo(codProyecto);

            if (CollectionUtils.isNotEmpty(output.getLista())) {
                CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista(), false);
                pantalla.getCmbSubjectArea().setModel(model);
                //Si sólo tiene un elemento lo seleccionamos
                /*if (output.getLista().size() == 1) {
                    pantalla.getCmbSubjectArea().setSelectedIndex(0);
                }*/
            }
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtCmbSubjectArea() {
        clearSubjectArea(true); //Limpiar las dependencias 
        if (pantalla.getCmbSubjectArea().getSelectedIndex() < 0) {
            return;
        }
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();

        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        //Actualizamos y habilitamos la descripcion del Subject Area
        setEditableText(pantalla.getTxtDescripcionSubjectArea(), subjectArea.getDescripcion());

        OutputConsulta<CodigoDescripcion> output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.ConsultaDiagramas(codProyecto, subjectArea.getCodigo());

            CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista(), false);
            pantalla.getCmbDiagrama().setModel(model);
            //Si sólo tiene un elemento lo seleccionamos
            /*if (output.getLista().size() == 1) {
                pantalla.getCmbDiagrama().setSelectedIndex(0);
            }*/
            pantalla.getBtnNuevoDiagrama().setEnabled(true);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtCmbDiagrama() {
        if (pantalla.getCmbDiagrama().getSelectedIndex() < 0) {
            pantalla.getTxtDescripcionDiagrama().setText("");
            return;
        }
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();
        CodigoDescripcion diagrama = (CodigoDescripcion) pantalla.getCmbDiagrama().getSelectedItem();

        // Recuperar información de pantalla
        String codProyecto = pantalla.getTxtModeloProyecto().getText();

        //Actualizamos y habilitamos la descripcion del Diagrama
        setEditableText(pantalla.getTxtDescripcionDiagrama(), diagrama.getDescripcion());

        // Actualizamos los datos de la tabla
        fillTblDiagramas(codProyecto, subjectArea.getCodigo(), diagrama.getCodigo());
        // Habilitamos botón Incluir
        pantalla.getBtnIncluir().setEnabled(true);
    }

    private void fillTblDiagramas(String codProyecto, String codSubjectArea, String codDiagrama) {
        // Actualizamos los datos de la tabla
        OutputConsulta<TablasDiagrama> output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.consultaTablasDiagrama(codProyecto, codSubjectArea, codDiagrama);

            DiagramasTableModel tableModel = (DiagramasTableModel) pantalla.getTblDiagramas().getModel();
            tableModel.clearData();
            if (output.getLista() != null) {
                tableModel.setData(output.getLista());
            }
            pantalla.getBtnEliminar().setEnabled(false);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void procesarModelo() throws ServiceException {
        //Limpiamos combo Subject Area y sus dependencias
        clearSubjectArea(false);
        //Activamos los botones asociados al modelo
        pantalla.getBtnNuevoSubjectArea().setEnabled(true);
        pantalla.getBtnGuardar().setEnabled(true);
        cargarSubjectArea();
    }

    private void clearSubjectArea(boolean soloDependencias) {
        //Limpiamos combo Subject Area (si se indica) y sus dependencias
        if (!soloDependencias) {
            pantalla.getCmbSubjectArea().setModel(new CodigoDescripcionComboBoxModel());
        }
        pantalla.getTxtDescripcionSubjectArea().setText("");
        MDSQLUIHelper.setReadOnlyText(pantalla.getTxtDescripcionSubjectArea());

        pantalla.getCmbDiagrama().setModel(new CodigoDescripcionComboBoxModel());
        pantalla.getTxtDescripcionDiagrama().setText("");
        MDSQLUIHelper.setReadOnlyText(pantalla.getTxtDescripcionDiagrama());

        ((DiagramasTableModel) pantalla.getTblDiagramas().getModel()).clearData();
        pantalla.getBtnNuevoDiagrama().setEnabled(false);
        pantalla.getBtnIncluir().setEnabled(false);
    }

    /* Establece el texto del textArea y lo pone como habilitado */
    private void setEditableText(JTextArea textArea, String texto) {
        textArea.setEditable(true);
        textArea.setText(texto);
        textArea.setBackground(UIManager.getColor("TextArea.background"));
    }
}
