/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.utils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.listener.text.TxtModeloProyectoListener;
import com.mdsql.ui.model.SubProyectoComboBoxModel;
import com.mdsql.ui.renderer.CmbSubProyectoRenderer;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JButton;
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
        } else if (obj.equals(dialogSupportModelo.getBtnLimpiar())) {
            clearForm();
        }
    }

    public void evtBtnCancelar() {
        dialogSupportModelo.getReturnParams().put(MDSQLConstants.P_OUT_EXIT_BUTTON, MDSQLConstants.BTN_CANCELAR);
        dialogSupportModelo.dispose();
    }

    public void evtBtnSearchModel() {
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
                /*PantallaSeleccionModelos pantallaSeleccionModelos;
                pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(dialogSupportModelo.getFrameParent(),
                        MDSQLConstants.CMD_SEARCH_MODEL, params);
                if (!pantallaSeleccionModelos.getErrorOnload()) {
                    MDSQLUIHelper.show(pantallaSeleccionModelos);
                    modelo = pantallaSeleccionModelos.getSeleccionado();
                }*/
                PantallaSeleccionModelos pantallaSeleccionModelos = MDSQLUIHelper.showForm(dialogSupportModelo.getFrameParent(), PantallaSeleccionModelos.class, params);
                if (pantallaSeleccionModelos != null){
                    modelo = pantallaSeleccionModelos.getSeleccionado();
                }
            }
            establecerModelo(modelo);
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(dialogSupportModelo, e);
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

        OutputConsulta<Modelo> output = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);

        // Si Hay avisos, se muestran
        MDSQLUIHelper.showWarnings(dialogSupportModelo, output.getWarnings());

        return output.getLista();
    }

    public void fillCmbSubModelos(Modelo modelo) {
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
        Modelo modelo = dialogSupportModelo.getModelo();
        if (dialogSupportModelo.getCmbSubModelo() != null) {
            fillCmbSubModelos(modelo);
        }
    }

    @Override
    public void onLoad() {
        dialogSupportModelo.setFormListener(this);
        if (dialogSupportModelo.getTxtModeloProyecto() != null) {
            //Añadimos listener para tratar cambios en el valor del modelo
            TxtModeloProyectoListener ls = new TxtModeloProyectoListener(dialogSupportModelo);
            dialogSupportModelo.getTxtModeloProyecto().addActionListener(ls);
            dialogSupportModelo.getTxtModeloProyecto().addFocusListener(ls);
        }
        //inicializamos los listener por defecto de los botones
        addComponentActionListener(dialogSupportModelo.getBtnCancelar());
        addComponentActionListener(dialogSupportModelo.getBtnModeloProyecto());

        if (dialogSupportModelo.getCmbSubModelo() != null) {
            dialogSupportModelo.getCmbSubModelo().setRenderer(new CmbSubProyectoRenderer());
        }

        //Cargar el parámetro del modelo
        if (dialogSupportModelo.getParams() != null) {
            try {
                Modelo modelo = (Modelo) dialogSupportModelo.getParams().get(MDSQLConstants.P_IN_MODELO);
                establecerModelo(modelo);
            } catch (ServiceException e) {
                dialogSupportModelo.setErrorOnload(Boolean.TRUE);
                MDSQLUIHelper.showErrors(dialogSupportModelo, e);
            }
        }
    }

    private void addComponentActionListener(JButton button) {
        if (button == null || (button.getActionListeners() != null && button.getActionListeners().length > 0)) {
            return;
        }
        button.addActionListener(this);
    }

    /**
     * Se ejecuta al cambiar el modelo
     */
    public void clearDepsModelo() {
        JComboBox cmbSubModelo = dialogSupportModelo.getCmbSubModelo();
        if (cmbSubModelo != null) {
            cmbSubModelo.setSelectedIndex(-1);
            cmbSubModelo.setModel(new SubProyectoComboBoxModel());
        }
        if (dialogSupportModelo.getTxtModeloProyectoDescrip() != null) {
            dialogSupportModelo.getTxtModeloProyectoDescrip().setText("");
        }
    }

    public void clearForm() {
        JComboBox cmbSubModelo = dialogSupportModelo.getCmbSubModelo();
        if (cmbSubModelo != null) {
            int index = cmbSubModelo.getModel() != null && cmbSubModelo.getModel().getSize() == 1 ? 0 : -1;
            cmbSubModelo.setSelectedIndex(index);
        }

        dialogSupportModelo.getContentPane().repaint();
    }
}
