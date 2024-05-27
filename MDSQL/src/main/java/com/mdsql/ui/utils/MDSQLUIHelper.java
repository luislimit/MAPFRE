package com.mdsql.ui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.model.ScriptsTableModel;
import com.mdsql.ui.utils.creators.CabeceraTablaCreator;
import com.mdsql.ui.utils.creators.Creator;
import com.mdsql.ui.utils.creators.DialogCreator;
import com.mdsql.ui.utils.creators.FrameCreator;
import com.mdsql.utils.LiteralesSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.UIHelper;
import com.mdval.utils.Constants;
import com.mdval.utils.LogWrapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class MDSQLUIHelper extends UIHelper {

    /**
     * @param item
     * @return
     */
    public DialogSupport createDialog(FrameSupport frameParent, String item) {
        Creator dialogCreator = new DialogCreator(frameParent, item);
        return (DialogSupport) dialogCreator.factoryMethod(null);
    }

    /**
     * @param frameParent
     * @param item
     * @param params
     * @return
     */
    public DialogSupport createDialog(FrameSupport frameParent, String item, Map<String, Object> params) {
        Creator dialogCreator = new DialogCreator(frameParent, item);
        return (DialogSupport) dialogCreator.factoryMethod(params);
    }

    /**
     * @param item
     * @return
     */
    public FrameSupport createFrame(String item, Boolean modal) {
        Creator frameCreator = new FrameCreator(item, modal);
        return (FrameSupport) frameCreator.factoryMethod(null);
    }

    /**
     * @param item
     * @return
     */
    public FrameSupport createFrame(FrameSupport parent, String item, Boolean modal) {
        Creator frameCreator = new FrameCreator(parent, item, modal);
        return (FrameSupport) frameCreator.factoryMethod(null);
    }

    /**
     * @param item
     * @param params
     * @return
     */
    public FrameSupport createFrame(String item, Boolean modal, Map<String, Object> params) {
        Creator frameCreator = new FrameCreator(item, modal);
        return (FrameSupport) frameCreator.factoryMethod(params);
    }

    /**
     * @param parent
     * @param item
     * @param params
     * @return
     */
    public FrameSupport createFrame(FrameSupport parent, String item, Boolean modal,
            Map<String, Object> params) {
        Creator frameCreator = new FrameCreator(parent, item, modal);
        return (FrameSupport) frameCreator.factoryMethod(params);
    }

    /**
     * @param item
     * @return
     */
    public Cabecera createCabeceraTabla(String item) {
        Creator cabeceraTablaCreator = new CabeceraTablaCreator(item);
        return (Cabecera) cabeceraTablaCreator.factoryMethod();
    }

    /**
     * @param frame
     * @param cmd
     * @param params
     */
    public void showPopup(FrameSupport frame, String cmd, Map<String, Object> params) {
        DialogSupport dialog = createDialog(frame, cmd, params);
        UIHelper.show(dialog);
    }

    /**
     * @param e
     * @return
     */
    public Map<String, Object> buildError(Exception e) {
        Map<String, Object> params = new HashMap<>();

        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            if (!Objects.isNull(serviceException.getType()) && serviceException.getType().equals(2)) {
                params.put(MDSQLConstants.TYPE, MDSQLConstants.CMD_WARN);
                params.put(Constants.SERVICE_ERROR, e);
            } else {
                params.put(MDSQLConstants.TYPE, MDSQLConstants.CMD_ERROR);
                params.put(Constants.SERVICE_ERROR, e);
            }
        } else {
            params.put(Constants.ERROR, e);
        }
        return params;
    }

    /**
     * @param script
     * @return
     */
    public List<TextoLinea> toTextoLineas(JTextArea txtArea) {
        List<TextoLinea> lineas = new ArrayList<>();

        for (String line : txtArea.getText().split("\\n")) {
            TextoLinea linea = new TextoLinea();
            linea.setValor(line);

            lineas.add(linea);
        }

        return lineas;
    }

    @SneakyThrows
    public List<TextoLinea> toTextoLineas(File file, Charset inCharset) {
        List<TextoLinea> lineas = new ArrayList<>();

        try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), inCharset); BufferedReader br = new BufferedReader(in)) {
            String line;
            while ((line = br.readLine()) != null) {
                TextoLinea linea = new TextoLinea();
                linea.setValor(line);

                lineas.add(linea);
            }

            return lineas;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * @param model
     * @return
     */
    public Boolean isAnySelected(ScriptsTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            Script scr = model.getSelectedRow(i);
            if (scr.getSelected()) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * @return
     */
    public JFileChooser getJFileChooser(String rutaInicial) throws IOException {
        LiteralesSingleton literales = LiteralesSingleton.getInstance();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(literales.getLiteral("panelPrincipal.tituloChooser"));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(new File(rutaInicial));
        chooser.setAcceptAllFileFilterUsed(false);

        return chooser;
    }

    /**
     * @param rutaInicial
     * @param textField
     * @param file
     * @param frameParent
     */
    public File abrirScript(String rutaInicial, JTextField textField, FrameSupport frameParent) {
        try {
            JFileChooser chooser = MDSQLUIHelper.getJFileChooser(rutaInicial);
            if (chooser.showOpenDialog(frameParent) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String rutaArchivo = file.getAbsolutePath();
                LogWrapper.debug(log, "Archivo seleccionado: %s", rutaArchivo);
                textField.setText(rutaArchivo);

                return file;
            }

            return null;
        } catch (IOException e) {
            Map<String, Object> params = buildError(e);
            showPopup(frameParent, Constants.CMD_ERROR, params);
            return null;
        }
    }

    /**
     * @param warnings
     * @return
     */
    public Map<String, Object> buildWarnings(List<Object[]> warnings) {
        Map<String, Object> params = new HashMap<>();

        params.put(Constants.WARN, warnings);
        params.put(MDSQLConstants.TYPE, MDSQLConstants.CMD_WARN);

        return params;
    }

    /**
     * @param warnings
     * @param frameSupport
     */
    public void showWarningsIfExists(List<Object[]> warnings, FrameSupport frameSupport) {
        if (CollectionUtils.isNotEmpty(warnings)) {
            Map<String, Object> params = MDSQLUIHelper.buildWarnings(warnings);
            MDSQLUIHelper.showPopup(frameSupport, MDSQLConstants.CMD_WARN, params);
        }
    }

    /**
     * @param field
     * @param value
     * @param limit
     */
    public void resetText(JTextField field, String value, Integer limit) {
        field.setHorizontalAlignment(JTextField.LEFT);

        if (!Objects.isNull(limit) && value.length() > limit) {
            String textToShow = value.substring(0, limit).concat("...");
            field.setText(textToShow);
            field.setToolTipText(value);
        } else {
            field.setText(value);
        }

        field.setCaretPosition(0);
    }

    /**
     * @param field
     */
    public void resetCursor(JTextField field) {
        field.setCaretPosition(0);
    }

    /**
     * @param field
     */
    public void resetCursor(JTextArea field) {
        field.setCaretPosition(0);
    }

    @SuppressWarnings("rawtypes")
    public void setSelectedItem(JComboBox cmb, Object selected) {
        cmb.setSelectedItem(selected);
        cmb.repaint();
    }

    public void putScriptsOn(FramePrincipal framePrincipal, List<Script> scripts) {
        for (Script script : scripts) {
            if ("SQL".equals(script.getTipoScript())) {
                framePrincipal.getIfrmSQLModificado().setTitle(script.getNombreScript());
                framePrincipal.getTxtSQLModificado().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtSQLModificado());
                framePrincipal.getIfrmLanzaSQLModificado().setTitle(script.getNombreScriptLanza());
                framePrincipal.getTxtLanzaSQLModificado().setText(script.getTxtScriptLanza());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtSQLModificado());
            }

            if ("PDC".equals(script.getTipoScript())) {
                framePrincipal.getIfrmPDC().setTitle(script.getNombreScript());
                framePrincipal.getTxtPDC().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtPDC());
                framePrincipal.getIfrmLanzaPDC().setTitle(script.getNombreScriptLanza());
                framePrincipal.getTxtLanzaPDC().setText(script.getTxtScriptLanza());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtPDC());
            }

            if ("SQLH".equals(script.getTipoScript())) {
                framePrincipal.getIfrmSQLH().setTitle(script.getNombreScript());
                framePrincipal.getTxtSQLH().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtSQLH());
                framePrincipal.getIfrmLanzaSQLH().setTitle(script.getNombreScriptLanza());
                framePrincipal.getTxtLanzaSQLH().setText(script.getTxtScriptLanza());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtSQLH());
            }

            if ("PDCH".equals(script.getTipoScript())) {
                framePrincipal.getIfrmPDCH().setTitle(script.getNombreScript());
                framePrincipal.getTxtPDCH().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtPDCH());
                framePrincipal.getIfrmLanzaPDCH().setTitle(script.getNombreScriptLanza());
                framePrincipal.getTxtLanzaPDCH().setText(script.getTxtScriptLanza());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtPDCH());
            }
        }
    }

    public static void setTableModel(JTable tabla, DefaultTableModel model) {
        tabla.setModel(model);
        ajustaCabeceraTabla(tabla, model.getCabecera());
    }

    public static void ajustaCabeceraTabla(JTable table, Cabecera cabecera) {
        List<Integer> widths = cabecera.getColumnSizes();

        if (CollectionUtils.isNotEmpty(widths)) {
            for (int i = 0; i < widths.size(); i++) {
                if (i < table.getColumnModel().getColumnCount()) {
                    Integer width = widths.get(i);
                    if (!Objects.isNull(width)) {
                        table.getColumnModel().getColumn(i).setPreferredWidth(width);
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static Object getSelectedTableObject(JTable tabla) {
        ListSelectionModel lsm = (ListSelectionModel) tabla.getSelectionModel();
        Integer index = lsm.getMinSelectionIndex();

        return ((DefaultTableModel) tabla.getModel()).getSelectedRow(index);
    }

    public static <T> T showForm(FrameSupport frameParent, Class<T> clase, Map<String, Object> params) {
        T dialog = null;
        Constructor<T> constructor;
        try {
            // Obtener el constructor que coincide con los tipos de los argumentos
            // Crear una nueva instancia utilizando el constructor y los argumentos proporcionados
            if (params != null) {
                constructor = clase.getConstructor(FrameSupport.class, Boolean.class, Map.class);
                dialog = constructor.newInstance(frameParent, true, params);
            } else {
                constructor = clase.getConstructor(FrameSupport.class, Boolean.class);
                dialog = constructor.newInstance(frameParent, true);
            }
            // Mostramos el formulario
            UIHelper.show((JDialog) dialog);
            //((JDialog)dialog).setVisible(Boolean.TRUE);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MDSQLUIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dialog;
    }

    public static JDialog showForm(FrameSupport frameParent, String className) {
        JDialog dialog = null;
        try {
            // Carga la clase utilizando el nombre
            Class<?> clase = Class.forName(className);
            dialog = (JDialog) showForm(frameParent, clase, null);

        } catch (ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            Logger.getLogger(MDSQLUIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Retorna el dialogo creado
        return dialog;
    }

    public Boolean confirmAction(JFrame frame, String key) throws IOException{
        LiteralesSingleton literales= LiteralesSingleton.getInstance();
        String titulo = literales.getLiteral("confirmacion.titulo");
        String message = literales.getLiteral(key);
        // Crear el array de opciones
        Object[] options = {"Sí", "No"};

        // Mostrar el cuadro de diálogo con el botón "Cancel" como predeterminado
        int result = JOptionPane.showOptionDialog(
                frame,
                message,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]); // El tercer elemento ("Cancel") es el predeterminado

        // Manejar la respuesta del usuario
        return result == JOptionPane.YES_OPTION;
    }

    public void showWarnings(DialogSupport pantalla, ServiceException serviceException) {
        if (serviceException != null) {
            Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_WARN, params);
        }
    }

    public void showErrors(DialogSupport pantalla, Exception exception) {
        if (exception != null) {
            Map<String, Object> errParams;
            errParams = MDSQLUIHelper.buildError(exception);
            MDSQLUIHelper.showPopup(pantalla.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
        }
    }
    
    public void setReadOnlyText(JTextComponent textComponent){
          textComponent.setEditable(false);
          textComponent.setBackground(MDSQLConstants.TEXT_DISABLED_BGCOLOR);
    }
}
