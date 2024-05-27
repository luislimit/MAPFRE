package com.mdval.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public class AppHelper {

	/**
	 * Para usar el bean después de obtenerlo, hacer un cast del bean concreto.
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();
		ApplicationContext context = (ApplicationContext) appGlobalSingleton.getProperty(Constants.SPRING_CONTEXT);

		return context.getBean(name);
	}

	/**
	 * Normaliza el valor de SI y NO a S y N
	 * 
	 * @param value
	 * @return
	 */
	public static String normalizeCmbSiNoValue(String value) {
		return "SI".equals(value) ? "S" : "N";
	}

	/**
	 * Normaliza el valor de S y N a SI y NO
	 * 
	 * @param value
	 * @return
	 */
	public static String normalizeSiNoValueToCmb(String value) {
		return "S".equals(value) ? "SI" : "NO";
	}

	/**
	 * Normaliza el valor de S y N a true y false
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean normalizeCheckValue(String value) {
		return "S".equals(value) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Normaliza el valor de true y false a S y N
	 * 
	 * @param value
	 * @return
	 */
	public static String normalizeValueToCheck(Boolean value) {
		return Boolean.TRUE.equals(value) ? "S" : "N";
	}

	/**
	 * @param sCodigo
	 * @return
	 */
	@SneakyThrows
	public static BigDecimal toBigDecimal(String sCodigo, String errMessage) {
		try {
			if (StringUtils.isNotBlank(sCodigo)) {
				Long valor = Long.parseLong(sCodigo);
				return new BigDecimal(valor);
			}

			return null;
		} catch (NumberFormatException e) {
			throw new Exception(errMessage);
		}
	}

	/**
	 * @param num
	 * @return
	 */
	public static String toString(BigDecimal num) {
		return (!Objects.isNull(num)) ? num.toString() : StringUtils.EMPTY;
	}

	/**
	 * @param s
	 * @return
	 */
	public static String toValidString(String s) {
		return (StringUtils.isNotBlank(s)) ? s : StringUtils.EMPTY;
	}

	/**
	 * @param s
	 * @return
	 */
	public static String secureFullTrim(String s) {
		if (!Objects.isNull(s)) {
			StringBuilder sb = new StringBuilder(s);
			String dup = sb.toString();
			dup = dup.trim();
			dup = dup.replaceAll("\\n", "");
			dup = dup.replaceAll("\\r", "");
			return dup;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * @param data
	 */
	public static void serializeToDisk(Object data, String name) {
		try (FileOutputStream fout = new FileOutputStream(name + ".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			oos.writeObject(data);
		} catch (IOException e) {
			LogWrapper.warn(log, e.getMessage());
		}
	}

	/**
	 * @return
	 */
	public static Object deserializeFromDisk(String name) {
		try (FileInputStream fin = new FileInputStream(name + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fin)) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			LogWrapper.warn(log, e.getMessage());
			return null;
		}
	}

	/**
	 * @param input
	 * @param charsetInput
	 * @param charsetOutput
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String parseString(String input, Charset charsetInput, Charset charsetOutput)
			throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try (InputStreamReader in = new InputStreamReader(bis, charsetInput);
				OutputStreamWriter out = new OutputStreamWriter(bos, charsetOutput)) {
			int c = in.read();

			while (c != -1) {
				out.write(c);
				c = in.read();
			}
		} 
		
		String s = new String(bos.toByteArray(), charsetOutput); 
		return s;
	}
}
