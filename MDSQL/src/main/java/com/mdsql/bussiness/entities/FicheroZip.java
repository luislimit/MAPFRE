package com.mdsql.bussiness.entities;

import com.mdsql.utils.MDSQLAppHelper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.collections.CollectionUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
/**
 *
 * @author LVARONA
 */
public class FicheroZip implements Serializable {

    private static final long serialVersionUID = 1060226242769948540L;

    private String nombre;
    private List<Fichero> ficheros;

    /**
     *
     * @throws IOException
     */
    public void validar() throws IOException {
        if (MDSQLAppHelper.existeFichero(nombre)) {
            throw new IOException("Ya existe el fichero: " + nombre);
        }
        for (Fichero fichero : ficheros) {
            if (!MDSQLAppHelper.existeFichero(fichero.getNombre())) {
                throw new IOException("No existe el fichero: " + fichero.getNombre());
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void crear() throws IOException {
        ZipFile zipFile = new ZipFile(nombre);
        if (CollectionUtils.isNotEmpty(ficheros)) {
            for (Fichero fichero : ficheros) {
                zipFile.addFile(new File(fichero.getNombre()));
            }
        }
    }
}
