package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaMantenimientoAvisos;
import com.mdsql.ui.model.NivelesImportanciaComboBoxModel;
import com.mdsql.ui.utils.ListenerSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.DateFormatter;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

/**
 *
 * @author LVARONA
 */
public class PantallaMantenimientoAvisosListener extends ListenerSupportModeloPermiso implements OnLoadListener {

    protected PantallaMantenimientoAvisos pantalla;
    private BigDecimal codAviso = null;

    public PantallaMantenimientoAvisosListener(PantallaMantenimientoAvisos pantalla) {
        super(pantalla);
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnGuardar())) {
            evtGuardar();
        } else {
            super.actionPerformed(e);
        }
    }

    private void evtGuardar() {
        try {
            String codigoProyecto = pantalla.getTxtModeloProyecto().getText();
            String codPeticion = pantalla.getTxtPeticion().getText();
            NivelImportancia nivelImportancia = (NivelImportancia) pantalla.getCmbImportancia().getSelectedItem();
            String codNivelAviso = nivelImportancia.getCodigoNivelAviso().toString();
            String nomObjeto = pantalla.getTxtNombreObjeto().getText();
            String mcaHabilitado = MDSQLAppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
            String titulo = pantalla.getTxtTitulo().getText();
            String descripcion = pantalla.getTxtDescripcion().getText();
            String codUsr = MDSQLAppHelper.getUsuario();

            // Invocar a p_mnto_avisos
            AvisoService service = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
            OutputWarning output = service.mntoAvisos(codigoProyecto, codPeticion, codNivelAviso, nomObjeto,
                    mcaHabilitado, codAviso, titulo, descripcion, codUsr);
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
            //Si todo es correcto, cerrar la pantalla e indicar que se han producido cambios
            pantalla.getReturnParams().put(MDSQLConstants.P_OUT_DATA_CHANGED, true);
            pantalla.dispose();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    @Override
    public void onLoad() {
        try {
            Modelo modelo = (Modelo) pantalla.getParams().get(MDSQLConstants.P_IN_MODELO);
            if (modelo != null) {
                pantalla.getTxtModeloProyecto().setText(modelo.getCodigoProyecto());
                pantalla.getTxtModeloProyectoDescrip().setText(modelo.getNombreModelo());
            }
            cargarNivelesImportancia();
            //Recuperar el objeto si viene de modificar e inicializar la pantalla
            Aviso aviso = (Aviso) pantalla.getParams().get(MDSQLConstants.P_IN_OBJETO);
            if (aviso != null) {
                codAviso = aviso.getCodigoAviso();
                dateFormatter = new DateFormatter();
                pantalla.getTxtPeticion().setText(aviso.getCodigoPeticion());
                pantalla.getCmbImportancia().setSelectedItem(aviso.getNivelImportancia());
                pantalla.getTxtNombreObjeto().setText(aviso.getNombreObjeto());
                pantalla.getChkHabilitada().setSelected(MDSQLAppHelper.normalizeCheckValue(aviso.getMcaHabilitado()));
                pantalla.getTxtTitulo().setText(aviso.getTitulo());
                pantalla.getTxtDescripcion().setText(aviso.getDescripcion());
                //
                pantalla.getTxtUsuarioAlta().setText(aviso.getCodigoUsrAlta());
                pantalla.getTxtUsuarioModificacion().setText(aviso.getCodigoUsuario());
                pantalla.getTxtFechaAlta().setText(dateFormatter.dateToString(aviso.getFechaAlta()));
                pantalla.getTxtFechaModificacion().setText(dateFormatter.dateToString(aviso.getFechaActualizacion()));
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void cargarNivelesImportancia() throws ServiceException {
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);

        OutputConsulta<NivelImportancia> output = avisoService.consultaNivelesImportancia();
        NivelesImportanciaComboBoxModel nivelesImportanciaComboBoxModel = new NivelesImportanciaComboBoxModel(output.getLista());
        pantalla.getCmbImportancia().setModel(nivelesImportanciaComboBoxModel);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }
}
