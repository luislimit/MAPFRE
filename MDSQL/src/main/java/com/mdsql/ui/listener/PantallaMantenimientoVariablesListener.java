package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.ui.PantallaMantenimientoVariables;
import com.mdsql.ui.model.*;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class PantallaMantenimientoVariablesListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaMantenimientoVariables pantalla;

    public PantallaMantenimientoVariablesListener(PantallaMantenimientoVariables pantallaMantenimientoVariables) {
        super();
        this.pantalla = pantallaMantenimientoVariables;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnGuardar())) {
            eventBtnGuardar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    private void eventBtnGuardar() {
        try {
            ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codUsr = session.getCodUsr();
            Modelo modelo = pantalla.getModelo();

            String codigoProyecto = modelo.getCodigoProyecto();
            String codigoVariable = pantalla.getTxtCodigoVariable().getText();
            String entorno = (String) pantalla.getCmbEntorno().getSelectedItem();
            String bbdd = pantalla.getTxtBBDD().getText();
            String tipoVariable = (String) pantalla.getCmbTipoVariable().getSelectedItem();
            String valorVariable = pantalla.getTxtValorVariable().getText();
            String valorSustituir = pantalla.getTxtValorSustituir().getText();
            String codigoPeticion = pantalla.getTxtPeticion().getText();
            String mcaInterno = (String) pantalla.getCmbUsoInterno().getSelectedItem();
            String mcaHabilitado = AppHelper
                    .normalizeValueToCheck(pantalla.getChkHabilitada().isSelected());
            String mcaExcepcion = AppHelper
                    .normalizeValueToCheck(pantalla.getChkExcepcion().isSelected());
            String comentario = pantalla.getTxtComentario().getText();

            OutputWarning output = modeloService.actualizarVariableModelo(codigoProyecto, codigoVariable, entorno, bbdd, tipoVariable,
                    valorVariable, valorSustituir, codigoPeticion, mcaInterno, mcaHabilitado, mcaExcepcion, comentario, codUsr);

            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

            clearForm();
            actualizarVariables(modelo);
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void clearForm() {
        pantalla.getTxtCodigoVariable().setText(StringUtils.EMPTY);
        pantalla.getTxtValorVariable().setText(StringUtils.EMPTY);

        pantalla.getCmbUsoInterno().setSelectedIndex(1); // NO

        pantalla.getTxtPeticion().setText(StringUtils.EMPTY);
        pantalla.getTxtValorSustituir().setText(StringUtils.EMPTY);

        pantalla.getCmbTipoVariable().setSelectedIndex(-1);

        pantalla.getTxtBBDD().setText(StringUtils.EMPTY);

        pantalla.getCmbEntorno().setSelectedIndex(-1);

        pantalla.getTxtComentario().setText(StringUtils.EMPTY);
        pantalla.getChkHabilitada().setSelected(Boolean.FALSE);
        pantalla.getChkUsoPermisos().setSelected(Boolean.FALSE);

        pantalla.getTxtUsuarioAlta().setText(StringUtils.EMPTY);
        pantalla.getTxtFechaAlta().setText(StringUtils.EMPTY);
        pantalla.getTxtUsuarioModificacion().setText(StringUtils.EMPTY);
        pantalla.getTxtFechaModificacion().setText(StringUtils.EMPTY);
    }

    @Override
    public void onLoad() {
        clearForm();
        try {
            TipoObjetoService tipoObjetoService = (TipoObjetoService) getService(MDSQLConstants.TIPO_OBJETO_SERVICE);

            Modelo modelo = (Modelo) pantalla.getParams().get("modelo");
            pantalla.setModelo(modelo);
            if (modelo != null) {
                pantalla.getTxtModeloProyecto().setText(modelo.getCodigoProyecto());
                pantalla.getTxtModeloProyectoDescrip().setText(modelo.getNombreModelo());

                actualizarVariables(modelo);

                // Rellenar combos
                OutputConsulta<String> output = tipoObjetoService.consultarTiposVariable();
                MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

                List<String> tipos = output.getLista();

                if (CollectionUtils.isNotEmpty(tipos)) {
                    StringComboBoxModel tipoObjetoComboBoxModel = new StringComboBoxModel(tipos);
                    pantalla.getCmbTipoVariable().setModel(tipoObjetoComboBoxModel);
                }
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
        }
    }

    private void actualizarVariables(Modelo modelo) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

        OutputConsulta<Variable> output = modeloService.consultaVariables(modelo);

        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        if (CollectionUtils.isNotEmpty(output.getLista())) {
            fillVariables(output.getLista());
        }
    }

    private void fillVariables(List<Variable> variables) {
        // Obtiene el modelo y lo actualiza
        VariableTableModel tableModel = (VariableTableModel) pantalla.getTblVariables()
                .getModel();
        tableModel.clearData();
        tableModel.setData(variables);
        tableModel.fireTableDataChanged();
    }
}
