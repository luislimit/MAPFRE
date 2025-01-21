package com.mdsql.ui.utils;

import com.mdsql.bussiness.entities.InformeCambioTRN;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.OutputParamInformeTRN;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ExcelGeneratorService;
import com.mdsql.bussiness.service.InformeService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.DlgErrores;
import com.mdsql.ui.DlgSolicitaTexto;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.PantallaBuscadorFicheros;
import com.mdsql.ui.model.ScriptsTableModel;
import com.mdsql.ui.renderer.TableSelectionRenderer;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.EstadosProcesado;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.UIHelper;
import com.mdval.utils.AppHelper;
import static com.mdval.utils.AppHelper.getBean;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.Constants;
import com.mdval.utils.LiteralesSingleton;
import com.mdval.utils.LogWrapper;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
@Slf4j
public class MDSQLUIHelper extends UIHelper {

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
     * @param txtArea
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
                MDSQLAppHelper.dumpContentToText(script.getLineasScriptLanza(), framePrincipal.getTxtLanzaSQLModificado());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtSQLModificado());
            }

            if ("PDC".equals(script.getTipoScript())) {
                framePrincipal.getIfrmPDC().setTitle(script.getNombreScript());
                framePrincipal.getTxtPDC().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtPDC());
                framePrincipal.getIfrmLanzaPDC().setTitle(script.getNombreScriptLanza());
                MDSQLAppHelper.dumpContentToText(script.getLineasScriptLanza(), framePrincipal.getTxtLanzaPDC());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtPDC());
            }

            if ("SQLH".equals(script.getTipoScript())) {
                framePrincipal.getIfrmSQLH().setTitle(script.getNombreScript());
                framePrincipal.getTxtSQLH().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtSQLH());
                framePrincipal.getIfrmLanzaSQLH().setTitle(script.getNombreScriptLanza());
                MDSQLAppHelper.dumpContentToText(script.getLineasScriptLanza(), framePrincipal.getTxtLanzaSQLH());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtSQLH());
            }

            if ("PDCH".equals(script.getTipoScript())) {
                framePrincipal.getIfrmPDCH().setTitle(script.getNombreScript());
                framePrincipal.getTxtPDCH().setText(StringUtils.EMPTY);
                MDSQLAppHelper.dumpContentToText(script.getLineasScript(), framePrincipal.getTxtPDCH());
                framePrincipal.getIfrmLanzaPDCH().setTitle(script.getNombreScriptLanza());
                MDSQLAppHelper.dumpContentToText(script.getLineasScriptLanza(), framePrincipal.getTxtLanzaPDCH());
                MDSQLUIHelper.resetCursor(framePrincipal.getTxtPDCH());
            }
        }
    }

    public static void setTableModelRenderer(JTable tabla, DefaultTableModel model, DefaultTableCellRenderer renderer) {
        tabla.setModel(model);
        ajustaCabeceraTabla(tabla, model.getCabecera());
        if (renderer == null) {
            renderer = new TableSelectionRenderer();
        }
        tabla.setRowSelectionAllowed(true);
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.setDefaultRenderer(String.class, renderer);
    }

    public static void ajustaCabeceraTabla(JTable table, Cabecera cabecera) {
        List<Integer> widths = cabecera.getColumnSizes();

        if (CollectionUtils.isNotEmpty(widths)) {
            for (int i = 0; i < widths.size(); i++) {
                if (i < table.getColumnModel().getColumnCount()) {
                    Integer width = widths.get(i);
                    if (!Objects.isNull(width)) {
                        table.getColumnModel().getColumn(i).setPreferredWidth(width);
                        // Si tiene indicada longitud preferida 0, es que desea ocultarla
                        if (width == 0) {
                            table.getColumnModel().getColumn(i).setMinWidth(0);
                            table.getColumnModel().getColumn(i).setMaxWidth(0);
                        }
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
            //Comprobar si ha ocurrido un error en el evento OnLoad, en ese caso no se muestra
            if (dialog instanceof DialogSupport) {
                DialogSupport dialogSupport = (DialogSupport) dialog;
                if (dialogSupport.getErrorOnload()) {
                    return null;
                }
            }
            // Leer parámetro con las dimensiones del formulario
            String dimensiones = getKeyTextValue(clase.getSimpleName() + ".size");
            if (dimensiones != null && !dimensiones.isEmpty()) {
                String[] dimArr = dimensiones.split(",");
                if (dimArr.length == 2) {
                    try {
                        int ancho = Integer.parseInt(dimArr[0].trim());
                        int alto = Integer.parseInt(dimArr[1].trim());
                        ((JDialog) dialog).setSize(ancho, alto);
                    } catch (NumberFormatException e) {
                        LogWrapper.error(log, "ERROR en formato del literal [" + dimensiones + "]:", e);
                    }
                }
            }
            // Mostramos el formulario
            UIHelper.show((JDialog) dialog);
            //((JDialog)dialog).setVisible(Boolean.TRUE);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MDSQLUIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dialog;
    }

    private void setDialogSize(JDialog dialog) {
        // Leer parámetro con las dimensiones del formulario
        Class clase = dialog.getClass();
        String dimensiones = getKeyTextValue(clase.getSimpleName() + ".size");
        if (dimensiones != null && !dimensiones.isEmpty()) {
            String[] dimArr = dimensiones.split(",");
            if (dimArr.length == 2) {
                try {
                    int ancho = Integer.parseInt(dimArr[0].trim());
                    int alto = Integer.parseInt(dimArr[1].trim());
                    dialog.setSize(ancho, alto);
                } catch (NumberFormatException e) {
                    LogWrapper.error(log, "ERROR en formato del literal [" + dimensiones + "]:", e);
                }
            }
        }
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

    public Boolean confirmAction(Component frame, String keyText) {
        String titulo = getKeyTextValue("confirmacion.titulo");
        String message = getKeyTextValue(keyText);
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

    private static FrameSupport getFrameSupport(Component component) {
        if (component instanceof DialogSupport) {
            return ((DialogSupport) component).getFrameParent();
        }
        return (FrameSupport) component;
    }

    public static void showWarnings(Component component, ServiceException serviceException) {
        if (serviceException != null && !serviceException.getErrors().isEmpty()) {
            Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
            MDSQLUIHelper.showForm(getFrameSupport(component), DlgErrores.class, params);
        }
    }

    public static void showErrors(Component component, Exception exception) {
        if (exception != null) {
            Map<String, Object> params;
            params = MDSQLUIHelper.buildError(exception);
            MDSQLUIHelper.showForm(getFrameSupport(component), DlgErrores.class, params);
        }
    }

    public static void setReadOnlyText(JTextComponent textComponent) {
        textComponent.setEditable(false);
        if (textComponent instanceof JTextArea) {
            textComponent.setBackground(MDSQLConstants.TEXT_DISABLED_BGCOLOR);
        }
    }

    /**
     *
     * @param keyText
     * @return
     */
    public static String getKeyTextValue(String keyText) {
        String keyTextValue = null;
        try {
            LiteralesSingleton literales = LiteralesSingleton.getInstance();
            keyTextValue = literales.getLiteral(keyText);
            if (keyTextValue == null || keyTextValue.isEmpty()) {
                keyTextValue = keyText;
            }

        } catch (IOException ex) {
        }
        return keyTextValue;
    }

    /**
     *
     * @param component
     * @param keyText
     */
    public void showMessage(Component component, String keyText) {
        String keyTextValue = getKeyTextValue(keyText);

        if (keyText.startsWith("error.")) {
            Exception e = new Exception(keyTextValue);
            showErrors(component, e);
        } else if ((keyText.startsWith("aviso."))) {
            //Construimos la ServiceException con el mensaje de aviso
            String[] arrTexto = {keyTextValue};
            List<Object[]> lista = new ArrayList();
            lista.add(arrTexto);
            ServiceException e = new ServiceException();
            e.setErrors(lista);
            showWarnings(component, e);
        } else {
            JOptionPane.showMessageDialog(component, keyTextValue);
        }
    }

    /**
     * @param rutaInicial
     * @return
     * @throws java.io.IOException
     */
    public static JFileChooser getJFileChooser(String rutaInicial) throws IOException {
        LiteralesSingleton literales = LiteralesSingleton.getInstance();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(literales.getLiteral("panelPrincipal.tituloChooser"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (rutaInicial != null) {
            chooser.setCurrentDirectory(new File(rutaInicial));
        }
        chooser.setAcceptAllFileFilterUsed(false);

        return chooser;
    }

    /**
     * @param component
     * @param textField
     * @return
     */
    public File abrirScript(Component component, JTextField textField) {
        return abrirScript(component, textField, null);
    }

    /**
     *
     * @param component
     * @param textField
     * @param defRuta
     * @return
     */
    public File abrirScript(Component component, JTextField textField, String defRuta) {
        FrameSupport frameParent = getFrameSupport(component);
        String textValue = textField.getText();
        String rutaInicial;
        if (textValue == null && defRuta != null) {
            rutaInicial = defRuta;
        } else {
            rutaInicial = getRutaInicial(textValue);
        }
        try {
            JFileChooser chooser = getJFileChooser(rutaInicial);
            if (chooser.showOpenDialog(frameParent) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (!file.isFile()) {
                    throw new IOException("Debe seleccionar un fichero");
                }
                String rutaArchivo = file.getAbsolutePath();
                LogWrapper.debug(log, "Archivo seleccionado: %s", rutaArchivo);
                textField.setText(rutaArchivo);
                Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
                session.setSelectedRoute(file.getParent());
                return file;
            }
            return null;
        } catch (IOException e) {
            showErrors(frameParent, e);
            return null;
        }
    }

    /**
     * Retorna la ruta del parámetro si no es null y existe Si no existe, toma
     * la de la sesion, si fuera null retorna el valor del parametro de
     * configuracion
     *
     * @param defRuta
     * @return
     */
    private static String getRutaInicial(String defRuta) {
        String ruta = defRuta;
        if (ruta != null && !ruta.isEmpty()) {
            File file = new File(ruta);
            if (file.exists()) {
                return file.isDirectory() ? file.getAbsolutePath() : file.getParent();
            }
            File carpeta = new File(file.getParent());
            if (carpeta.exists()) {
                return carpeta.getAbsolutePath();
            }
        }
        //Si no hay ruta, tomamos la de la sesión
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        ruta = session.getSelectedRoute();
        if (ruta == null || ruta.isEmpty()) {
            try {
                //Si no hay ruta en la sesión tomamos el valor por defecto del fichero de configuración
                ConfigurationSingleton configuration;
                configuration = ConfigurationSingleton.getInstance();
                ruta = configuration.getConfig("RutaDefectoScripts");
            } catch (IOException ex) {
                Logger.getLogger(MDSQLUIHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ruta;
    }

    /**
     * Muestra una pantalla de selección de ruta, previamente inicializada
     *
     * @param textField
     */
    public static void seleccionarRuta(JTextField textField) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        String ruta = MDSQLUIHelper.getRutaInicial(textField.getText());
        if (ruta != null) {
            fc.setCurrentDirectory(new File(ruta));
        }
        int state = fc.showOpenDialog(fc);
        if (state == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file.isFile()) {
                ruta = file.getParent();
            } else {
                ruta = file.getAbsolutePath();
            }
            textField.setText(ruta);
        }
    }

    /**
     * @param component
     * @return
     * @throws java.io.IOException
     */
    public File loadScript(Component component) throws IOException {
        FrameSupport framePrincipal = getFrameSupport(component);
        File file = null;

        /*DialogSupport dialog = MDSQLUIHelper.createDialog(framePrincipal, MDSQLConstants.CMD_LOAD_SCRIPT);
        MDSQLUIHelper.show(dialog);*/
        PantallaBuscadorFicheros dialog = MDSQLUIHelper.showForm(framePrincipal, PantallaBuscadorFicheros.class, null);

        String rutaInicial = (String) dialog.getReturnParams().get("RutaInicial");
        if (StringUtils.isNotBlank(rutaInicial)) {
            file = selectFile(framePrincipal, rutaInicial);
        }
        return file;
    }

    /**
     * @param component
     * @param rutaInicial
     * @return
     * @throws java.io.IOException
     */
    public File selectFile(Component component, String rutaInicial) throws IOException {
        File file = null;

        JFileChooser chooser = getJFileChooser(rutaInicial);
        if (chooser.showOpenDialog(component) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            LogWrapper.debug(log, "Archivo seleccionado: %s", file.getAbsolutePath());
            String ruta = file.getParent();

            Session session;
            session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            session.setSelectedRoute(ruta);
            LogWrapper.debug(log, "Ruta global: %s", session.getSelectedRoute());
        }
        return file;
    }

    /**
     * @param pantalla
     * @param rutaInicial
     * @param claveTitulo
     * @param extensiones Descripcion,extension1, extension2...
     * @return Retorna el fichero seleccionado, null en otro caso
     * @throws java.io.IOException
     */
    public File selectNewFile(Component pantalla, String rutaInicial, String claveTitulo, String... extensiones) throws IOException {
        File outFile = null;

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(rutaInicial));
        chooser.setDialogTitle(getKeyTextValue(claveTitulo));

        // Si se indican extensiones a mostrar, las añadimos
        if (extensiones.length > 1) {
            String descripcion = extensiones[0];
            String[] arrExtensiones = Arrays.copyOfRange(extensiones, 1, extensiones.length);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(descripcion, arrExtensiones);
            chooser.setFileFilter(filter);
        }

        if (chooser.showSaveDialog(pantalla) == JFileChooser.APPROVE_OPTION) {
            outFile = chooser.getSelectedFile();
            if (outFile.exists() && !confirmAction(pantalla, "confirmacion.sobreescribir.fichero")) {
                outFile = null;
            }
        }
        return outFile;
    }

    /**
     * Dado un color de fondo, retorna el color del texto
     *
     * @param colorFondo
     * @return
     */
    public static Color getColorContraste(Color colorFondo) {
        Color[] letraBlanca = {Color.BLUE, Color.RED};
        if (Arrays.asList(letraBlanca).contains(colorFondo)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    /**
     * Exporta los datos de una tabla a un fichero Excel
     *
     * @param component
     * @param table
     * @return
     * @throws java.io.IOException
     */
    public File exportTableToExcel(Component component, JTable table) throws IOException {
        //Verificar si hay datos para exportar
        if (table.getModel().getRowCount() == 0) {
            showMessage(component, "info.tabla.vacia");
            return null;
        }
        //Recuperar la ruta por defecto para la exportación
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String ruta = session.getSelectedRoute();
        //Solicitar el nombre del fichero
        File file = selectFile(component, ruta);
        //Si no se indica o existe y no quiere sobrescribir, cancelamos el proceso
        if (file == null || (file.exists() && !MDSQLUIHelper.confirmAction(component, "confirmacion.sobreescribir.fichero"))) {
            return null;
        }
        //Recuperar servicio de exportación
        ExcelGeneratorService service = (ExcelGeneratorService) AppHelper.getBean(MDSQLConstants.EXCEL_GENERATOR_SERVICE);
        service.exportTableToExcel(file.getAbsolutePath(), table);
        //Mostrar el fichero generado, tras confirmación
        if (MDSQLUIHelper.confirmAction(component, "confirmacion.generacion.fichero")) {
            Desktop.getDesktop().open(file);
        }
        //Retornamos el fichero generado
        return file;
    }

    /**
     * Exporta los datos de una Lista de Objetos a un fichero Excel
     *
     * @param component
     * @param codPlantilla
     * @param filas
     * @param fileName puede ser NULL
     * @return
     * @throws com.mdval.exceptions.ServiceException
     */
    public File exportListToExcel(Component component, String codPlantilla, List<Object> filas, String fileName) throws ServiceException {
        try {
            File file;
            if (fileName == null) {
                //Recuperar la ruta por defecto para la exportación
                Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
                String ruta = session.getSelectedRoute();
                //Mostrar diálogo para solicitar nombre del fichero
                file = selectFile(component, ruta);
                //Si no se indica o existe y no quiere sobrescribir, cancelamos el proceso
                if (file == null || (file.exists() && !MDSQLUIHelper.confirmAction(component, "confirmacion.sobreescribir.fichero"))) {
                    return null;
                }
            } else {
                file = new File(fileName);
            }
            //Recuperar la ruta de las plantillas
            String carpetaPlantillas = (String) ConfigurationSingleton.getInstance().getConfig("CarpetaPlantillas");
            if (carpetaPlantillas == null || carpetaPlantillas.isEmpty()) {
                throw new IOException("No se ha definido la clave [CarpetaPlantillas] en el fichero Configuration.properties");
            }
            if (!carpetaPlantillas.endsWith(File.separator)) {
                carpetaPlantillas = carpetaPlantillas.concat(File.separator);
            }
            File carpeta = new File(carpetaPlantillas);
            if (!carpeta.exists()) {
                throw new IOException("No existe la ruta de las plantillas definida en el fichero Configuration.properties => " + carpetaPlantillas);
            }
            // Recuperar el nombre de la plantilla a partir de su codigo
            String plantilla = (String) ConfigurationSingleton.getInstance().getConfig(codPlantilla);
            if (plantilla == null || plantilla.isEmpty()) {
                throw new IOException("No se ha definido la clave [" + codPlantilla + "] en el fichero Configuration.properties");
            }
            plantilla = carpetaPlantillas.concat(plantilla);
            File plantillaFile = new File(plantilla);
            if (!plantillaFile.exists()) {
                throw new IOException("No existe la plantilla => " + plantilla);
            }
            //Recuperar servicio de exportación
            ExcelGeneratorService service = (ExcelGeneratorService) AppHelper.getBean(MDSQLConstants.EXCEL_GENERATOR_SERVICE);
            //Mostramos cursor mientras estamos procesando ...
            component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            service.exportListToExcel(plantilla, file, filas);
            component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            //Mostrar el fichero generado, tras confirmación
            if (MDSQLUIHelper.confirmAction(component, "confirmacion.generacion.fichero")) {
                Desktop.getDesktop().open(file);
            }
            return file;
        } catch (IOException e) {
            component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            throw new ServiceException(e);
        }
    }

    /**
     * Solicita un texto, se indica el titulo del texto solicitado
     *
     * @param frameSupport
     * @param claveTitulo
     * @param claveConfirmacion
     * @return
     */
    public String solicitaTexto(FrameSupport frameSupport, String claveTitulo, String claveConfirmacion) {
        Map<String, Object> params = new HashMap<>();
        params.put(MDSQLConstants.P_IN_TITULO, MDSQLUIHelper.getKeyTextValue(claveTitulo));
        params.put(MDSQLConstants.P_IN_CLAVE_CONFIRMACION, MDSQLUIHelper.getKeyTextValue(claveConfirmacion));
        DlgSolicitaTexto dialog = MDSQLUIHelper.showForm(frameSupport, DlgSolicitaTexto.class, params);
        if (dialog.getReturnParams().get(MDSQLConstants.P_OUT_EXIT_BUTTON).equals(MDSQLConstants.BTN_ACEPTAR)) {
            return (String) dialog.getReturnParams().get(MDSQLConstants.P_OUT_TEXTO);
        }
        return null;
    }

    /**
     *
     * @param pantalla
     * @param idProceso
     * @param estado
     * @throws com.mdval.exceptions.ServiceException
     */
    public void cambioEstadoProcesado(DialogSupport pantalla, BigDecimal idProceso, EstadosProcesado estado) throws ServiceException {

        String titulo = "";
        String confirma = "confirmacion.mensaje";
        switch (estado) {
            case RECHAZADO:
                titulo = "titulo.motivo.rechazo";
                break;
            case INCIDENCIA:
                titulo = "titulo.motivo.incidencia";
                break;
            case EXCLUIDO:
                titulo = "titulo.motivo.exclusion";
        }
        String texto = solicitaTexto(pantalla.getFrameParent(), titulo, confirma);
        if (texto == null) {
            return;
        }
        ProcesoService procesoService = (ProcesoService) AppHelper.getBean(MDSQLConstants.PROCESO_SERVICE);
        // Recuperar los archivos del proceso
        OutputFicherosPeticion outputFicheros = procesoService.consultaFicherosAccion(estado.getIndex(), idProceso);
        showWarnings(pantalla, outputFicheros.getWarnings());

        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

        ServiceException warnings = null;
        OutputValor<String> outputEstado = null;
        switch (estado) {
            case RECHAZADO:
                OutputWarning output = procesoService.rechazarProcesado(idProceso, texto, session.getCodUsr());
                warnings = output.getWarnings();
                break;
            case INCIDENCIA:
                outputEstado = procesoService.incidenciaProcesado(idProceso, texto, session.getCodUsr());
                warnings = outputEstado.getWarnings();
                break;
            case EXCLUIDO:
                outputEstado = procesoService.excluirProcesado(idProceso, texto, session.getCodUsr());
                warnings = outputEstado.getWarnings();
        }
        if (warnings != null) {
            showWarnings(pantalla, warnings);
            // Ejecutamos las acciones de los ficheros sin disparar excepción
            OutputWarning result = procesoService.ejecutarFicherosAccion(outputFicheros, false);
            showWarnings(pantalla, result.getWarnings());
        }
        //Comprobamos si hay que generar el informeTRN para incidencia y excluido
        if (outputEstado != null && outputEstado.getValor().equals("S")) {
            generaInformeTRN(pantalla);
        }
    }

    /**
     *
     * @param pantalla
     * @throws ServiceException
     */
    public void generaInformeTRN(DialogSupport pantalla) throws ServiceException {
        try {
            InformeService informeService = (InformeService) getBean(MDSQLConstants.INFORME_SERVICE);

            //Mostramos cursor mientras estamos procesando ...
            pantalla.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            OutputConsulta<InformeCambioTRN> outputInforme = informeService.generaInformeTRN();
            showWarnings(pantalla, outputInforme.getWarnings());

            //Obtener la ruta donde copiaremos el informe Excel
            OutputParamInformeTRN outputParam = informeService.paramInformeTRN();
            showWarnings(pantalla, outputParam.getWarnings());

            String fileName = outputParam.getRuta();
            fileName = fileName == null ? "." : fileName;
            if (!fileName.endsWith(File.separator)) {
                fileName = fileName + File.separator;
            }
            fileName = fileName + "informeTRN.xlsx";

            List<Object> lista = new ArrayList(outputInforme.getLista());
            //Restablecemos el cursor
            pantalla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            // Exportar los datos a Excel
            File fileInformeTRN = exportListToExcel(pantalla, "PlantillaInformeTRN", lista, fileName);

            //Comprobamos si se ha creado el fichero
            if (fileInformeTRN == null) {
                return;
            }

            //Copiar el fichero dependiendo del destino
            try {
                //Mostramos cursor mientras estamos procesando ...
                pantalla.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if ("FTP".equals(outputParam.getTipEnvio())) { //(Valores FTP / NAS)
                    MDSQLAppHelper.copyFileFTP(
                            fileInformeTRN.getAbsolutePath(),
                            outputParam.getServidor(),
                            outputParam.getPuerto(),
                            outputParam.getUsrFtp(),
                            outputParam.getPwdFtp());
                } else {
                    String ruta = outputParam.getRuta();
                    // Verifica la validez de la ruta, dispara IOException
                    MDSQLAppHelper.checkRuta(ruta);
                    if (!ruta.endsWith(File.separator)) {
                        ruta = ruta.concat(File.separator);
                    }
                    File destino = new File(ruta.concat(fileInformeTRN.getName()));

                    Files.copy(fileInformeTRN.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                //Restablecemos el cursor
                pantalla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } catch (IOException e) {
                //Restablecemos el cursor
                pantalla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                showMessage(pantalla, "error.copia.informe.cambios");
            }
        } catch (ServiceException e) {
            pantalla.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            throw e;
        }
    }

}
