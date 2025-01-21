package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaMantenimientoNotasModelos;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.NivelesImportanciaComboBoxModel;
import com.mdsql.ui.model.NotasModeloTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JButton;
import org.apache.commons.lang3.StringUtils;

public class PantallaMantenimientoNotasModelosListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaMantenimientoNotasModelos pantalla;

    public PantallaMantenimientoNotasModelosListener(PantallaMantenimientoNotasModelos pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR.equals(jButton.getActionCommand())) {
            eventBtnGuardar();
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
            eventBtnBuscarModelo();
            cargarModelo(pantalla.getModeloSeleccionado());
        }

        if (MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR.equals(jButton.getActionCommand())) {
            pantalla.dispose();
        }
    }

    private void eventBtnBuscarModelo() {
        Modelo seleccionado;
        Map<String, Object> params = new HashMap<>();
        params.put("opcion", "mntoNotasModelos");

        String codigoProyecto = pantalla.getTxtCodigoProyecto().getText();

        if (StringUtils.isNotBlank(codigoProyecto)) {
            params.put("codigoProyecto", codigoProyecto);
        }

        /*PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantallaMantenimientoNotasModelos.getFrameParent(),
				MDSQLConstants.CMD_SEARCH_MODEL, params);
		MDSQLUIHelper.show(pantallaSeleccionModelos);*/
        PantallaSeleccionModelos pantallaSeleccionModelos = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaSeleccionModelos.class, params);
        seleccionado = pantallaSeleccionModelos.getSeleccionado();
        pantalla.setModeloSeleccionado(seleccionado);
    }

    private void eventBtnGuardar() {
        try {
            Aviso avisoSeleccionado = pantalla.getAvisoSeleccionado();

            if (!Objects.isNull(avisoSeleccionado)) {
                modificacion(avisoSeleccionado);
            } else {
                alta();
            }

            clearForm();
            clearList();
            cargarAvisosModelo(pantalla.getModeloSeleccionado());
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void alta() throws ServiceException {
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String codUsr = session.getCodUsr();

        Modelo modelo = pantalla.getModeloSeleccionado();

        if (!Objects.isNull(modelo)) {
            String codigoProyecto = modelo.getCodigoProyecto();
            String txtAviso = pantalla.getTxtDescripcion().getText();
            String desAviso = pantalla.getTxtTitulo().getText();

            String codNivelAviso = StringUtils.EMPTY;
            NivelImportancia nivelAviso = (NivelImportancia) pantalla.getCmbImportancia().getSelectedItem();
            if (!Objects.isNull(nivelAviso)) {
                codNivelAviso = nivelAviso.getCodigoNivelAviso().toString();
            }

            String codPeticion = pantalla.getTxtPeticion().getText();

            avisoService.altaAviso(codigoProyecto, desAviso, txtAviso, codNivelAviso, codPeticion, codUsr);
        }
    }

    private void modificacion(Aviso avisoSeleccionado) throws ServiceException {
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String codUsr = session.getCodUsr();

        Modelo modelo = pantalla.getModeloSeleccionado();
        String codigoProyecto = modelo.getCodigoProyecto();
        BigDecimal codigoAviso = avisoSeleccionado.getCodigoAviso();
        String txtAviso = pantalla.getTxtDescripcion().getText();
        String desAviso = pantalla.getTxtTitulo().getText();

        String codNivelAviso = StringUtils.EMPTY;
        NivelImportancia nivelAviso = (NivelImportancia) pantalla.getCmbImportancia().getSelectedItem();
        if (!Objects.isNull(nivelAviso)) {
            codNivelAviso = nivelAviso.getCodigoNivelAviso().toString();
        }

        String mcaHabilitado = AppHelper.normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
        String codPeticion = pantalla.getTxtPeticion().getText();

        avisoService.modificarAviso(codigoProyecto, codigoAviso, desAviso, txtAviso, codNivelAviso, mcaHabilitado, codPeticion, codUsr);
    }

    private void cargarModelo(Modelo modeloSeleccionado) {
        try {
            clearForm();
            clearList();

            if (!Objects.isNull(modeloSeleccionado)) {
                pantalla.getTxtCodigoProyecto().setText(modeloSeleccionado.getCodigoProyecto());
                pantalla.getTxtModeloProyecto().setText(modeloSeleccionado.getNombreModelo());

                cargarAvisosModelo(modeloSeleccionado);
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void cargarAvisosModelo(Modelo modeloSeleccionado) throws ServiceException {
        if (!Objects.isNull(modeloSeleccionado)) {
            // Limpiar la tabla de avisos
            NotasModeloTableModel tableModel = (NotasModeloTableModel) pantalla.getTblNotasModelos().getModel();
            tableModel.clearData();

            // Hacer la consulta
            AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
            OutputConsulta<Aviso> output = avisoService.consultaAvisosModelo(modeloSeleccionado.getCodigoProyecto());
            tableModel.setData(output.getLista());
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        }
    }

    private void cargarNivelesImportancia() throws ServiceException {
        AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);

        OutputConsulta<NivelImportancia> output = avisoService.consultaNivelesImportancia();
        NivelesImportanciaComboBoxModel nivelesImportanciaComboBoxModel = new NivelesImportanciaComboBoxModel(output.getLista());
        pantalla.getCmbImportancia().setModel(nivelesImportanciaComboBoxModel);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    private void clearForm() {
        pantalla.getTxtPeticion().setText(StringUtils.EMPTY);
        MDSQLUIHelper.setSelectedItem(pantalla.getCmbImportancia(), null);
        pantalla.getChkHabilitada().setSelected(Boolean.FALSE);
        pantalla.getTxtTitulo().setText(StringUtils.EMPTY);
        pantalla.getTxtDescripcion().setText(StringUtils.EMPTY);
        pantalla.getTxtUsuarioAlta().setText(StringUtils.EMPTY);
        pantalla.getTxtFechaAlta().setText(StringUtils.EMPTY);
        pantalla.getTxtUsuarioModificacion().setText(StringUtils.EMPTY);
        pantalla.getTxtFechaModificacion().setText(StringUtils.EMPTY);

        pantalla.getBtnGuardar().setEnabled(Boolean.TRUE);
    }

    private void clearList() {
        NotasModeloTableModel tableModel = (NotasModeloTableModel) pantalla
                .getTblNotasModelos().getModel();
        tableModel.clearData();
    }

    @Override
    public void onLoad() {
        try {
            cargarNivelesImportancia();
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }
}
