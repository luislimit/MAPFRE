package com.mdsql.ui.form.listener;

import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdsql.bussiness.service.SubjectAreaDiagramaService;
import com.mdsql.ui.form.FormEliminarTablasDiagrama;
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
public class FormEliminarTablasDiagramaListener extends ListenerSupport implements ActionListener{

    protected FormEliminarTablasDiagrama pantalla;

    public FormEliminarTablasDiagramaListener(FormEliminarTablasDiagrama pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnAceptar())) {
            evtAceptar();
        }else if (obj.equals(pantalla.getBtnCancelar())){
            evtSalir();
        }
    }

    private void evtAceptar() {
        if (!MDSQLUIHelper.confirmAction(pantalla.getFrameParent(), "confirmacion.mensaje")){
            return;
        }
        String codProyecto = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_MODELO);
        TablasDiagrama td = (TablasDiagrama) pantalla.getParams().get(MDSQLConstants.P_IN_OBJETO);
        String codPeticion = pantalla.getTxtPeticion().getText();
        String comentario = pantalla.getTxtDescripcion().getText();        
        String usuario = MDSQLAppHelper.getUsuario();        
        //
        OutputWarning output;
        try {
            SubjectAreaDiagramaService service = (SubjectAreaDiagramaService) getService(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE);
           
            output = service.deshabilitaTablaDiagrama(
                            codProyecto, 
                            td.getCodSubjectArea(), 
                            td.getCodDiagrama(), 
                            td.getNomTabla(),
                            comentario, 
                            codPeticion, 
                            usuario);
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
