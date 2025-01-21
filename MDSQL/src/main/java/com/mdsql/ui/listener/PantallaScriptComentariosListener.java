package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.ReentranteComentarioColumna;
import com.mdsql.bussiness.entities.ScriptInfo;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ReentranteService;
import com.mdsql.ui.PantallaScriptComentarios;
import com.mdsql.ui.model.ComentarioReentranteTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.TipoComentarioColumnaReentrante;
import com.mdval.exceptions.ServiceException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LVARONA
 */
public class PantallaScriptComentariosListener extends ListenerSupport implements ActionListener, ListSelectionListener {

    protected PantallaScriptComentarios pantalla;
    private ReentranteComentarioColumna registroSeleccionado;

    public PantallaScriptComentariosListener(PantallaScriptComentarios pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(pantalla.getBtnCSV())) {
            MDSQLUIHelper.abrirScript(pantalla, pantalla.getTxtCSV());
        } else if (obj.equals(pantalla.getBtnLimpiar())) {
            evtBtnLimpiar();
        } else if (obj.equals(pantalla.getBtnProcesar())) {
            evtBtnProcesar();
        } else if (obj.equals(pantalla.getBtnPersonalizar())) {
            evtBtnPersonalizar();
        } else if (obj.equals(pantalla.getBtnUtilizarGenerico())) {
            evtBtnUtilizarGenerico();
        } else if (obj.equals(pantalla.getBtnGenerar())) {
            evtBtnGenerar();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            evtSalir();
        }
    }

    private void evtBtnGenerar() {
        //Invoca al procedimiento p_genera_cmt_reentrantes
        try {
            ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
            String nombreTabla = pantalla.getTxtTabla().getText();
            String comentarioES = pantalla.getTxtCastellano().getText();
            String comentarioEN = pantalla.getTxtIngles().getText();
            String nombreFichero = pantalla.getTxtCSV().getText();
            if (nombreFichero == null) {
                throw new IOException(MDSQLUIHelper.getKeyTextValue("info.nombre.fichero.vacio"));
            }
            File file = new File(nombreFichero);
            String ruta = file.getParent();
            if (!ruta.endsWith(File.separator)) {
                ruta = ruta.concat(File.separator);
            }

            List<ReentranteComentarioColumna> listaCmtCol = tableModel.getData();
            //Invocamos al servicio
            ReentranteService service = (ReentranteService) getService(MDSQLConstants.REENTRANTE_SERVICE);
            OutputValor<ScriptInfo> output = service.generaCmtReentrantes(nombreTabla, comentarioES, comentarioEN, listaCmtCol);
            // Escribir el fichero
            ScriptInfo script = output.getValor();
            String nuevoNombre = ruta + script.getNombre();
            MDSQLAppHelper.dumpLinesToFile(script.getLineas(), nuevoNombre);
            //Mostramos los avisos
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (IOException | ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    private void evtSalir() {
        pantalla.dispose();
    }

    private void evtBtnProcesar() {
        try {
            ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
            tableModel.setData(null);
            String nombreTabla = pantalla.getTxtTabla().getText();
            String comentarioES = pantalla.getTxtCastellano().getText();
            String comentarioEN = pantalla.getTxtIngles().getText();
            String nombreFichero = pantalla.getTxtCSV().getText();
            List<TextoLinea> fichero = MDSQLAppHelper.writeFileToLines(new File(nombreFichero));
            //Invocamos al servicio
            ReentranteService service = (ReentranteService) getService(MDSQLConstants.REENTRANTE_SERVICE);
            OutputConsulta<ReentranteComentarioColumna> output = service.procesaComentarioReentrantes(nombreTabla, comentarioES, comentarioEN, fichero);
            //Actualizar los datos de la tabla
            tableModel.setData(output.getLista());
            tableModel.fireTableDataChanged();
            // Habilitamos el boton de generar
            pantalla.getBtnGenerar().setEnabled(true);
            //Mostramos los avisos
            MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
        } catch (IOException | ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
    }

    /**
     * Se invoca al seleccionar un registro de la tabla Si el valor de RDO es
     * NOK se activan los botones Personalizar y UtilizarGenerico
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
        List<ReentranteComentarioColumna> lista = tableModel.getData();
        boolean enable = false;
        if (lista != null && !lista.isEmpty()) {
            int row = pantalla.getTblComentario().getSelectedRow();
            if (row >= 0) {
                registroSeleccionado = lista.get(row);
                String RDO_NOK = MDSQLConstants.EstadosRDO.NOK.toString();
                enable = (registroSeleccionado.getRdo().equals(RDO_NOK));
            }
        }
        pantalla.getBtnPersonalizar().setEnabled(enable);
        pantalla.getBtnUtilizarGenerico().setEnabled(enable);
    }

    /**
     * Se cambiará el valor de la columna Tipo a P y se restablece RDO a OK
     */
    private void evtBtnPersonalizar() {
        String RDO_OK = MDSQLConstants.EstadosRDO.OK.toString();
        String tipoPersonalizado = TipoComentarioColumnaReentrante.PERSONALIZADO.getCodigo();

        registroSeleccionado.setTipo(tipoPersonalizado);
        registroSeleccionado.setRdo(RDO_OK);

        ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
        tableModel.fireTableDataChanged();
    }

    /**
     * Se cambia el valor de RDO a OK y se copian los campos genericos en sus
     * respectivos valores de plantilla
     */
    private void evtBtnUtilizarGenerico() {
        String RDO_OK = MDSQLConstants.EstadosRDO.OK.toString();
        String tipoGenerico = TipoComentarioColumnaReentrante.GENERICO.getCodigo();

        registroSeleccionado.setComentario(registroSeleccionado.getComentarioGenerico());
        registroSeleccionado.setTipo(tipoGenerico);
        registroSeleccionado.setRdo(RDO_OK);

        ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
        tableModel.fireTableDataChanged();
    }

    private void evtBtnLimpiar() {
        ComentarioReentranteTableModel tableModel = (ComentarioReentranteTableModel) pantalla.getTblComentario().getModel();
        tableModel.setData(null);
        tableModel.fireTableDataChanged();
        pantalla.getTxtTabla().setText("");
        pantalla.getTxtCastellano().setText("");
        pantalla.getTxtIngles().setText("");
        pantalla.getTxtCSV().setText("");
    }
}
