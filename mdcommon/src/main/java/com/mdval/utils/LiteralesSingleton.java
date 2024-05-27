package com.mdval.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author federico
 *
 */
public class LiteralesSingleton {

    private static LiteralesSingleton instance;

    private Properties properties;

    private LiteralesSingleton() throws IOException {
        properties = new Properties();

        //the base folder is ./, the root of the main.properties file  
        String literalesPath = "./literales.properties";

        try (FileInputStream fistream = new FileInputStream(literalesPath)) {
            properties.load(new InputStreamReader(fistream, StandardCharsets.ISO_8859_1));
        }
    }

    public static LiteralesSingleton getInstance() throws IOException {
        if (instance == null) {
            instance = new LiteralesSingleton();
        }
        return instance;
    }

    /**
     * @param key
     * @return
     */
    public String getLiteral(String key) {
        String value = getPropertyValue(properties, key);

        return StringUtils.isNotBlank(value) ? value : StringUtils.EMPTY;
    }

    /**
     * Busca el valor de la clave si el valor tiene el formato ${otra_clave},
     * retorna el valor de la otra_clave, es para permitir definiciones
     * dependientes
     *
     * @param properties
     * @param clave
     * @return
     */
    private static String getPropertyValue(Properties properties, String clave) {
        String valor = properties.getProperty(clave);
        if (valor != null && !valor.isEmpty()) {
            // Define el patrón a buscar
            Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");

            // Crea un objeto Matcher para encontrar coincidencias
            Matcher matcher = pattern.matcher(valor);

            // Verifica si la cadena contiene el patrón
            if (matcher.find()) {
                // Obtiene el valor yyyy del grupo de captura
                String claveRef = matcher.group(1);
                valor = properties.getProperty(claveRef);
            }
        }
        return valor;
    }
}
