package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.SubjectAreaDiagramaService;
import com.mdsql.ui.form.FormMantenimientoDiagrama;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LVARONA
 */
public class FormMantenimientoDiagramaListener extends ListenerSupport implements ActionListener{

    protected FormMantenimientoDiagrama pantalla;

    public FormMantenimientoDiagramaListener(FormMantenimientoDiagrama pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnGuardar())) {
            evtGuardar();
        }else if (obj.equals(pantalla.getBtnCancelar())){
            evtSalir();
        }
    }

    private void evtGuardar() {
        String codProyecto = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_MODELO);
        String codSubjectArea = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_SUBJECT_AREA);
        String codDiagrama = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_DIAGRAMA);
        String codPeticion = pantalla.getTxtPeticion().getText();
        String tablas = pantalla.getTxtTablas().getText();        
        String comentario = pantalla.getTxtComentario().getText();        
        String usuario = MDSQLAppHelper.getUsuario();        
        //
        OutputWarning output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);
           
            output = service.guardaTablasDiagrama(codProyecto, codSubjectArea, codDiagrama, tablas, comentario, codPeticion, usuario);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            //Indicar que se ha procesado correctamente
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_GUARDAR);    
            evtSalir();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
    
    private void evtSalir(){
        pantalla.dispose();
    }
}
