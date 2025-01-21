package com.mdsql.bussiness.entities;

import com.mdsql.utils.MDSQLAppHelper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
/**
 *
 * @author LVARONA
 */
public class FicheroAccion implements Serializable {

    private static final long serialVersionUID = 1060226242769948540L;
    //Acciones posibles
    private static final String COPIAR = "C";
    private static final String MOVER = "M";
    private static final String RENOMBRAR = "R";
    private static final String CREAR_RENOMBRAR = "X";
    private static final String ELIMINAR = "E";

    private String nombreOrigen;
    private String nombreDestino;
    private String codAccion;

    /**
     *
     * @throws IOException
     */
    public void validar() throws IOException {
        String msgError = "";
        if (!MDSQLAppHelper.existeFichero(nombreOrigen)) {
            msgError = "No existe el fichero origen : " + nombreOrigen;
        }
        /*if (!codAccion.equals(ELIMINAR) && MDSQLAppHelper.existeFichero(nombreDestino)) {
            if (msgError.isEmpty()) {
                msgError = msgError + "\n";
            }
            msgError = msgError + "Ya existe el fichero destino : " + nombreDestino;
        }*/
        if (!codAccion.equals(MOVER) && codAccion.equals(COPIAR) && codAccion.equals(RENOMBRAR) && codAccion.equals(ELIMINAR) && !codAccion.equals(CREAR_RENOMBRAR)) {
            if (msgError.isEmpty()) {
                msgError = msgError + "\n";
            }
            msgError = msgError + "El tipo de operación no es válido : " + codAccion;
        }
        if (!msgError.isEmpty()) {
            throw new IOException(msgError);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void ejecutar() throws IOException {
        String msg = "";
        try {
            switch (codAccion) {
                case COPIAR:
                    msg = "copiando archivo " + nombreOrigen + " a " + nombreDestino;
                    Files.copy(Paths.get(nombreOrigen), Paths.get(nombreDestino), StandardCopyOption.REPLACE_EXISTING);
                    break;
                case MOVER:
                    msg = "moviendo archivo " + nombreOrigen + " a " + nombreDestino;
                    Files.move(Paths.get(nombreOrigen), Paths.get(nombreDestino), StandardCopyOption.REPLACE_EXISTING);
                    break;
                case CREAR_RENOMBRAR:
                    //Comprueba si la carpeta existe, si no existe la creamos
                    File file = new File(nombreDestino);
                    File carpeta = file.getParentFile();
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                case RENOMBRAR:
                    msg = "renombrando archivo " + nombreOrigen + " como " + nombreDestino;
                    Files.move(Paths.get(nombreOrigen), Paths.get(nombreDestino), StandardCopyOption.REPLACE_EXISTING);
                    break;
                case ELIMINAR:
                    msg = "borrando archivo " + nombreOrigen;
                    Files.delete(Paths.get(nombreOrigen));
                default:
                    msg = " accion desconocida [" + codAccion + "] para los ficheros " + nombreOrigen + " y " + nombreDestino;
                    throw new IOException();
            }
        } catch (IOException e) {
            throw new IOException("Se ha producido un error " + msg);
        }
    }
}
