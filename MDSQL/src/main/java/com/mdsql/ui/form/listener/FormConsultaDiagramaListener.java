package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdsql.bussiness.service.SubjectAreaDiagramaService;
import com.mdsql.ui.form.FormConsultaDiagrama;
import com.mdsql.ui.model.CodigoDescripcionComboBoxModel;
import com.mdsql.ui.model.DiagramasConsultaTableModel;
import com.mdsql.ui.utils.ListenerSupportModelo;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author LVARONA
 */
public class FormConsultaDiagramaListener extends ListenerSupportModelo implements ActionListener {

    protected FormConsultaDiagrama pantalla;

    public FormConsultaDiagramaListener(FormConsultaDiagrama pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnBuscar())) {
            evtBtnBuscar();
        } else if (obj.equals(pantalla.getCmbSubjectArea())) {
            evtCmbSubjectArea();
        } else {
            super.actionPerformed(e);
        }
    }

    private void cargarSubjectArea() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        OutputConsulta<CodigoDescripcion> output;

        clearSubjectAreaDeps(); //Limpiar subjectArea y sus dependencias 

        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.ConsultaSubjectAreasModelo(codProyecto);

            if (CollectionUtils.isNotEmpty(output.getLista())) {
                CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista());
                pantalla.getCmbSubjectArea().setModel(model);
                //Si sólo tiene un elemento lo seleccionamos
                if (output.getLista().size() == 1) {
                    pantalla.getCmbSubjectArea().setSelectedIndex(0);
                }
            }
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtCmbSubjectArea() {
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();

        clearSubjectAreaDeps(); //Limpiar las dependencias 
        
        if (subjectArea == null || subjectArea.getCodigo() == null){
            return;
        }

        OutputConsulta<CodigoDescripcion> output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.ConsultaDiagramas(codProyecto, subjectArea.getCodigo());

            CodigoDescripcionComboBoxModel model = new CodigoDescripcionComboBoxModel(output.getLista());
            pantalla.getCmbDiagrama().setModel(model);
            //Si sólo tiene un elemento lo seleccionamos
            if (output.getLista().size() == 1) {
                pantalla.getCmbDiagrama().setSelectedIndex(0);
            }

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void evtBtnBuscar() {
        // Actualizamos los datos de la tabla
        String codProyecto = pantalla.getTxtModeloProyecto().getText();
        CodigoDescripcion subjectArea = (CodigoDescripcion) pantalla.getCmbSubjectArea().getSelectedItem();
        CodigoDescripcion diagrama = (CodigoDescripcion) pantalla.getCmbDiagrama().getSelectedItem();
        String codSubjectArea = subjectArea==null?null:subjectArea.getCodigo();
        String codDiagrama = diagrama==null?null:diagrama.getCodigo();
        String nomTabla = pantalla.getTxtTabla().getText();
        String codPeticion = pantalla.getTxtPeticion().getText();
        String fecDesde = pantalla.getTxtDesde().getText(); 
        String fecHasta = pantalla.getTxtHasta().getText();
        String mostrarInh = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkMostrarEliminados().isSelected());
        OutputConsulta<TablasDiagrama> output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);

            output = service.buscaDiagramas(codProyecto, codSubjectArea, codDiagrama, nomTabla, codPeticion, fecDesde, fecHasta, mostrarInh);

            DiagramasConsultaTableModel tableModel = (DiagramasConsultaTableModel) pantalla.getTblDiagramas().getModel();
            tableModel.clearData();
            if (output.getLista() != null) {
                tableModel.setData(output.getLista());
            }

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void procesarModelo() throws ServiceException {
        /*pantalla.getCmbSubjectArea().setModel(new CodigoDescripcionComboBoxModel());
        clearSubjectAreaDeps();*/
        cargarSubjectArea();
    }

    private void clearSubjectAreaDeps() {
        //Limpiamos dependencias de Subject Area 
        pantalla.getCmbDiagrama().setModel(new CodigoDescripcionComboBoxModel());
        ((DiagramasConsultaTableModel) pantalla.getTblDiagramas().getModel()).clearData();
    }

    @Override
    public void clearForm() {
        pantalla.getCmbSubjectArea().setSelectedIndex(-1);
        //pantalla.getCmbDiagrama().setSelectedIndex(0);
        pantalla.getTxtTabla().setText("");
        pantalla.getTxtPeticion().setText("");
        pantalla.getTxtDesde().setText("");
        pantalla.getTxtHasta().setText("");
        pantalla.getChkMostrarEliminados().setSelected(false); 
        pantalla.repaint();
    }

    
}
