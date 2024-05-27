/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.utils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.SubProyectoComboBoxModel;
import com.mdsql.ui.renderer.SubProyectoRenderer;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JComboBox;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class ListenerSupportModelo extends ListenerSupport implements ActionListener, OnLoadListener {

    protected DialogSupportModelo dialogSupportModelo;

    public ListenerSupportModelo(DialogSupportModelo dialogSupportModelo) {
        this.dialogSupportModelo = dialogSupportModelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(dialogSupportModelo.getBtnCancelar())) {
            evtBtnCancelar();
        } else if (obj.equals(dialogSupportModelo.getBtnModeloProyecto())) {
            evtBtnSearchModel();
        }
    }

    public void evtBtnCancelar() {
        dialogSupportModelo.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_CANCELAR);
        dialogSupportModelo.dispose();
    }

    protected void evtBtnSearchModel() {
        try {
            clearForm();
            Modelo modelo = null;
            Map<String, Object> params = new HashMap<>();

            String codigoProyecto = dialogSupportModelo.getTxtModeloProyecto().getText();

            List<Modelo> modelos = buscarModelos(codigoProyecto, null, null);
            if (modelos.size() == 1) {
                modelo = modelos.get(0);
            } else {
                if (StringUtils.isNotBlank(codigoProyecto)) {
                    params.put("codigoProyecto", codigoProyecto);
                }
                PantallaSeleccionModelos pantallaSeleccionModelos;
                pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(dialogSupportModelo.getFrameParent(),
                        MDSQLConstants.CMD_SEARCH_MODEL, params);
                if (!pantallaSeleccionModelos.getErrorOnload()) {
                    MDSQLUIHelper.show(pantallaSeleccionModelos);
                    modelo = pantallaSeleccionModelos.getSeleccionado();
                }
            }
            establecerModelo(modelo);
        } catch (ServiceException e) {
            Map<String, Object> errParams;
            errParams = MDSQLUIHelper.buildError(e);
            MDSQLUIHelper.showPopup(dialogSupportModelo.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }

    private void establecerModelo(Modelo modelo) throws ServiceException {
        if (!Objects.isNull(modelo)) {
            dialogSupportModelo.setModelo(modelo);
            if (dialogSupportModelo.getTxtModeloProyecto() != null) {
                dialogSupportModelo.getTxtModeloProyecto().setText(modelo.getCodigoProyecto());
            }
            if (dialogSupportModelo.getTxtModeloProyectoDescrip() != null) {
                dialogSupportModelo.getTxtModeloProyectoDescrip().setText(modelo.getNombreModelo());
            }
            procesarModelo();
        }
    }

    /**
     * @param codModelo
     * @param nombreModelo
     * @param codSubmodelo
     * @return
     * @throws ServiceException
     */
    private List<Modelo> buscarModelos(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
        ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);

        OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);

        // Hay avisos
        if (outputConsultaModelos.getResult() == 2) {
            ServiceException serviceException = outputConsultaModelos.getServiceException();
            Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
            MDSQLUIHelper.showPopup(dialogSupportModelo.getFrameParent(), MDSQLConstants.CMD_WARN, params);
        }
        return outputConsultaModelos.getModelos();
    }

    /**
     * @param modelo
     */
    private void fillCmbSubModelos() {
        Modelo modelo = dialogSupportModelo.getModelo();
        if (modelo == null) {
            return;
        }
        List<SubProyecto> subProyectos = modelo.getSubproyectos();
        if (CollectionUtils.isNotEmpty(subProyectos)) {
            SubProyectoComboBoxModel modelSubProyectos = new SubProyectoComboBoxModel(subProyectos);
            dialogSupportModelo.getCmbSubModelo().setModel(modelSubProyectos);
            // Si el modelo solo tiene un solo submodelo, se seleccionará directamente en el
            // combo.
            if (subProyectos.size() == 1) {
                dialogSupportModelo.setSubModelo(subProyectos.get(0));
                dialogSupportModelo.getCmbSubModelo().setSelectedIndex(0);
            }
        }
    }

    public void procesarModelo() throws ServiceException {
        if (dialogSupportModelo.getCmbSubModelo() != null) {
            fillCmbSubModelos();
        }
    }

    @Override
    public void onLoad() {
        //inicializamos los listener por defecto de los objetos
        if (dialogSupportModelo.getBtnCancelar() != null) {
            dialogSupportModelo.getBtnCancelar().addActionListener(this);
        }
        if (dialogSupportModelo.getBtnModeloProyecto() != null) {
            dialogSupportModelo.getBtnModeloProyecto().addActionListener(this);
        }
        if (dialogSupportModelo.getCmbSubModelo() != null) {
            dialogSupportModelo.getCmbSubModelo().setRenderer(new SubProyectoRenderer());
        }

        //Cargar el parámetro del modelo
        if (dialogSupportModelo.getParams() != null) {
            try {
                Modelo modelo = (Modelo) dialogSupportModelo.getParams().get(MDSQLConstants.P_IN_MODELO);
                establecerModelo(modelo);
            } catch (ServiceException e) {
                dialogSupportModelo.setErrorOnload(Boolean.TRUE);
                Map<String, Object> errParams;
                errParams = MDSQLUIHelper.buildError(e);
                MDSQLUIHelper.showPopup(dialogSupportModelo.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
            }
        }
    }

    public void clearForm() {
        JComboBox cmbSubModelo = dialogSupportModelo.getCmbSubModelo();
        if (cmbSubModelo != null) {
            int index  = cmbSubModelo.getModel() != null && cmbSubModelo.getModel().getSize()==1?0:-1;
            cmbSubModelo.setSelectedIndex(index);
        }
        dialogSupportModelo.getContentPane().repaint();
    }

    /**
     * Seleccionar el texto en el Combo CmbSubModelo
     *
     * @param codigo
     */
    public void setValueCmbSubModelo(String codigo) {
        JComboBox comboBox = dialogSupportModelo.getCmbSubModelo();
        int idx = -1;
        for (int i = 1; i < comboBox.getModel().getSize(); i++) {
            SubProyecto subProyecto = (SubProyecto) comboBox.getItemAt(i);
            if (subProyecto != null) {
                if (subProyecto.getCodigoSubProyecto().equals(codigo)) {
                    idx = i;
                    break;
                }
            }
        }
        comboBox.setSelectedIndex(idx);
    }

    /**
     *
     * @return Codigo seleccionado en el CmbSubModelo
     */
    public String getValueCmbSubModelo() {
        SubProyecto subProyecto = (SubProyecto) dialogSupportModelo.getCmbSubModelo().getSelectedItem();
        return (subProyecto != null) ? subProyecto.getCodigoSubProyecto() : null;
    }
}
