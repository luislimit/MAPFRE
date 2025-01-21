package com.mdsql.utils;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.utils.AppGlobalSingleton;
import com.mdval.utils.AppHelper;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.LogWrapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JTextArea;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * @author federico
 *
 */
@UtilityClass
@Slf4j
public class MDSQLAppHelper extends AppHelper {

    /**
     * @param key
     * @return
     */
    public Object getGlobalProperty(String key) {
        return AppGlobalSingleton.getInstance().getProperty(key);
    }

    /**
     * @param key
     * @param value
     */
    public void setGlobalProperty(String key, Object value) {
        AppGlobalSingleton.getInstance().setProperty(key, value);
    }

    /**
     * @param file
     * @return
     * @throws IOException
     */
    public Charset detectCharsetFromFile(File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            Charset charset = Charset.forName(new TikaEncodingDetector().guessEncoding(is));
            return charset;
        } catch (UnsupportedCharsetException e) {
            throw new IOException("Error en el juego de caracteres del fichero " + file.getName() + " :: " + e.getMessage());
        }
    }

    /**
     * @param content
     * @param file
     * @throws IOException
     */
    private void writeToFile(String content, File file, String charSet) throws IOException {
        String strBuffer = content;
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charSet == null ? MDSQLConstants.CP_1252 : charSet))) {
            writer.write(strBuffer);
            writer.flush();
        }
    }

    /**
     * @param file
     * @return
     * @throws IOException
     */
    public String writeFileToString(File file) throws IOException {
        StringBuilder strBuffer = new StringBuilder(StringUtils.EMPTY);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), MDSQLConstants.CP_1252))) {
            // reader = new BufferedReader(new FileReader(csvFile));
            String line;

            while ((line = reader.readLine()) != null) {
                strBuffer.append(line).append(MDSQLConstants.CR);
            }

            // Before return, removes the last CR
            return strBuffer.toString().trim();
        }
    }

    public List<TextoLinea> writeFileToLines(File file) throws IOException {
        List<TextoLinea> linesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), MDSQLConstants.CP_1252))) {
            // reader = new BufferedReader(new FileReader(csvFile));
            String line;

            while ((line = reader.readLine()) != null) {
                //REVISAR :: No incluir las líneas vacías al fichero
                //if (line != null && !line.isEmpty()) {
                TextoLinea textoLinea = TextoLinea.builder().valor(line).build();
                linesList.add(textoLinea);
                //}
            }

            return linesList;
        }
    }

    /**
     * @param fileName
     */
    public void createEmptyFile(String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();

            LogWrapper.debug(log, "Fichero creado: %s", fileName);
        } catch (IOException e) {
        }
    }

    /**
     * @param parent
     * @param nameFolder
     * @return
     * @throws java.io.IOException
     */
    public Path createFolder(String parent, String nameFolder) throws IOException {
        Path folderToCreate = Paths.get(parent, nameFolder);
        Files.createDirectories(folderToCreate);
        return folderToCreate;
    }

    /**
     * @param lineas
     * @param txtScript
     */
    public void dumpContentToText(List<TextoLinea> lineas, JTextArea txtScript) {
        StringBuffer strBuffer = toStringBuffer(lineas);
        txtScript.setText(strBuffer.toString());
        txtScript.setCaretPosition(0);
    }

    /**
     * @param lineas
     * @param fileName
     * @throws java.io.IOException
     */
    public void dumpLinesToFile(List<TextoLinea> lineas, String fileName) throws IOException {
        dumpLinesToFile(lineas, fileName, MDSQLConstants.CP_1252);
    }

    /**
     * @param lineas
     * @param fileName
     * @param charSet
     * @throws java.io.IOException
     */
    public void dumpLinesToFile(List<TextoLinea> lineas, String fileName, String charSet) throws IOException {
        StringBuffer strBuffer = toStringBuffer(lineas);
        File file = Paths.get(fileName).toFile();
        writeToFile(strBuffer.toString(), file, charSet);
    }

    /**
     * @param lineas
     * @return
     */
    private StringBuffer toStringBuffer(List<TextoLinea> lineas) {
        StringBuffer strBuffer = new StringBuffer(StringUtils.EMPTY);

        for (int i = 0; i < lineas.size(); i++) {
            // Si hay una línea en blanco no la imprime, pero sí el salto de línea
            if (!Objects.isNull(lineas.get(i).getValor())) {
                // Elimina el carácter CR del final
                String rtrim = StringUtils.stripEnd(lineas.get(i).getValor(), null);
                strBuffer.append(rtrim);
            }

            // Quita el último salto de línea
            if (i < lineas.size() - 1) {
                strBuffer.append(MDSQLConstants.CR);
            }
        }

        return strBuffer;
    }

    /**
     * @param file
     * @param txtScript
     * @throws IOException
     */
    public void dumpContentToText(File file, JTextArea txtScript) throws IOException {
        // Detecta el juego de caracteres del archivo y lo guarda para su posterior uso
        Charset charset = detectCharsetFromFile(file);
        LogWrapper.debug(log, "Juego de caracteres: %s", charset.toString());
        String content = writeFileToString(file);
        txtScript.setText(content);
        txtScript.setCaretPosition(0);
    }

    /**
     * @param file
     * @param txtScript
     * @throws java.io.IOException
     */
    public void dumpTextToFile(JTextArea txtScript, File file) throws IOException {
        String content = txtScript.getText();

        writeToFile(content, file, null);
    }

    /**
     * @param nombreScriptLanza
     * @param scriptLanza
     * @return
     */
    public Script createScript(String nombreScriptLanza, List<TextoLinea> scriptLanza) {
        Script script = new Script();

        script.setNombreScript(nombreScriptLanza);
        script.setLineasScript(scriptLanza);
        script.setNumeroOrden(new BigDecimal(1));

        return script;
    }

    /**
     * @return @throws java.io.IOException
     * @throws java.io.IOException
     */
    public String getRutaEntregados() throws IOException {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        String carpetaEntregados = (String) ConfigurationSingleton.getInstance().getConfig("CarpetaEntregaFicheros");
        return session.getSelectedRoute() + File.separator + carpetaEntregados;
    }

    /**
     *
     * @param ruta
     * @throws IOException
     */
    public void checkRuta(String ruta) throws IOException {
        if (ruta == null || ruta.isEmpty()) {
            throw new IOException("Nombre de la carpeta está vacío");
        }

        File rootFolder = new File(ruta);

        if (!rootFolder.exists() || !rootFolder.isDirectory()) {
            String msg = String.format("%s no existe o no es una carpeta", ruta);
            throw new IOException(msg);
        }
    }

    public String obtenerClaveEncriptacion(String claveEncriptacion) throws IndexOutOfBoundsException {
        if (claveEncriptacion.length() < 29) {
            throw new IndexOutOfBoundsException("La clave de encriptación debe ser mayor que 29 caracteres");
        }

        Integer begin = 17;
        return claveEncriptacion.substring(begin, begin + 12);
    }

    public Proceso buildProceso(BigDecimal idProceso) {
        return Proceso.builder().idProceso(idProceso)
                .build();
    }

    public String getLogFor(String nombreScript) {
        String name = nombreScript.substring(0, nombreScript.lastIndexOf("."));
        return name.concat(".log");
    }

    public void renombrarArchivo(File f, String nuevoNombre) {
        File newFile = new File(nuevoNombre);
        f.renameTo(newFile);
    }

    /**
     *
     * @return Nombre de la BBDD sobre la que se ejecuta la aplicación
     */
    public String getDatabaseName() {
        ConfigurationSingleton configuration;
        String bbdd = "";
        try {
            configuration = ConfigurationSingleton.getInstance();
            bbdd = configuration.getConfig("dataSource.url");
            int inicio = bbdd.indexOf("//");
            if (inicio != -1) {
                bbdd = bbdd.substring(inicio + 2);
            }
        } catch (IOException ex) {
            LogWrapper.error(log, ex.getMessage());
        }
        return bbdd;
    }

    public String getUsuario() {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        return session.getCodUsr();
    }

    public static String[] separateFileNameAndExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) { // No hay extensión
            return new String[]{fileName, ""};
        } else {
            String name = fileName.substring(0, lastDotIndex);
            String extension = fileName.substring(lastDotIndex + 1);
            return new String[]{name, extension};
        }
    }

    public static void copyFileFTP(String fileName, String server, int port, String user, String pass) throws IOException {
        FTPClient ftpClient = new FTPClient();

        try {
            // Conectar al servidor FTP
            ftpClient.connect(server, port);
            boolean login = ftpClient.login(user, pass);

            if (!login) {
                throw new IOException("Login failed!");
            }

            // Configurar el modo de transferencia (Binario para archivos)
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Ruta del archivo local a enviar
            String localFilePath = fileName;

            // Nombre con el que se guardará el archivo en el servidor FTP
            File file = new File(fileName);
            String remoteFilePath = "FtpUser/" + file.getName();

            // Subir el archivo
            InputStream inputStream = new FileInputStream(localFilePath);

            System.out.println("Iniciando la subida del archivo...");
            boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
            if (!done) {
                throw new IOException("Error al subir el archivo.");
            }
            System.out.println("El archivo se ha subido correctamente.");

            // Logout del servidor FTP
            ftpClient.logout();

        } catch (IOException ex) {
            LogWrapper.error(log, ex.getMessage());
            throw ex;
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
    }

    /**
     *
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    public boolean existeFichero(String fileName) throws IOException {
        File file = new File(fileName);
        File carpeta = new File(file.getParent());
        if (!carpeta.exists()) {
            throw new IOException("No existe la carpeta => " + carpeta.toString());
        }
        return (file.exists());
    }
}
