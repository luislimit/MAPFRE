package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputScriptPeticion;
import com.mdsql.bussiness.entities.ScriptPeticion;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.PantallaRenombraScript;
import com.mdsql.ui.PantallaVerScriptsPeticion;
import com.mdsql.ui.model.ScriptsPeticionTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaVerScriptsPeticionListener extends ListenerSupport implements ActionListener, ListSelectionListener, OnLoadListener {

    protected PantallaVerScriptsPeticion pantalla;
    private String ruta;
    private String codPeticion;
    private BigDecimal idProceso;
    private BigDecimal codEstado;

    public PantallaVerScriptsPeticionListener(PantallaVerScriptsPeticion pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnCambiarNombre())) {
            evtBtnCambiarNombre();
        } else if (obj.equals(pantalla.getBtnDetalleScript())) {
            evtBtnDetalleScript();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        ScriptPeticion sel = (ScriptPeticion) MDSQLUIHelper.getSelectedTableObject(pantalla.getTblScripts());
        pantalla.getBtnCambiarNombre().setEnabled(sel.getMcCambioNombre().equals("S"));
        pantalla.getBtnDetalleScript().setEnabled(true);
    }

    @Override
    public void onLoad() {
        try {
            if (pantalla.getParams() != null) {
                codPeticion = (String) pantalla.getParams().get(MDSQLConstants.P_IN_COD_PETICION);
                idProceso = (BigDecimal) pantalla.getParams().get(MDSQLConstants.P_IN_PROCESO);
                codEstado = (BigDecimal) pantalla.getParams().get(MDSQLConstants.P_IN_COD_ESTADO);
                pantalla.getTxtPeticion().setText(codPeticion);
            }
            fillTabla();
        } catch (ServiceException e) {
            pantalla.setErrorOnload(Boolean.TRUE);
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    private void fillTabla() throws ServiceException {
        ScriptService service = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
        OutputScriptPeticion output = service.consultaScriptsPeticion(codPeticion, idProceso, codEstado);
        ruta = output.getRuta();
        //Cargamos la tabla con los datos devueltos y refrescamos la tabla
        ScriptsPeticionTableModel model = (ScriptsPeticionTableModel) pantalla.getTblScripts().getModel();
        model.setData(output.getLista());
        model.fireTableDataChanged();

        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
    }

    /**
     *
     */
    private void evtBtnCambiarNombre() {
        ScriptPeticion sel = (ScriptPeticion) MDSQLUIHelper.getSelectedTableObject(pantalla.getTblScripts());
        if (sel == null) {
            //return;
        }
        try {
            Map<String, Object> params = new HashMap();
            params.put(MDSQLConstants.P_IN_RUTA, ruta);
            params.put(MDSQLConstants.P_IN_SCRIPT, sel);
            PantallaRenombraScript dlg = MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaRenombraScript.class, params);
            //Comprobamos si se ha cambiado el nombre para refrescar la tabla
            if ((Boolean) dlg.getReturnParams().get(MDSQLConstants.P_OUT_DATA_CHANGED)) {
                fillTabla();
            }
        } catch (ServiceException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }

    /**
     *
     */
    private void evtBtnDetalleScript() {
        ScriptPeticion sel = (ScriptPeticion) MDSQLUIHelper.getSelectedTableObject(pantalla.getTblScripts());
        if (sel == null) {
            return;
        }
        Map<String, Object> params = new HashMap();
        params.put(MDSQLConstants.P_IN_PROCESO, sel.getIdProceso());
        params.put(MDSQLConstants.P_IN_ORDEN, sel.getNumeroOrden());
        MDSQLUIHelper.showForm(pantalla.getFrameParent(), PantallaDetalleScript.class, params);
    }

}
