package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.ConsultaBD;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.utils.LogWrapper;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LVARONA
 */
@Slf4j
public class ServiceSupportScript extends ServiceSupport {

    /**
     * Convierte un array de BBDD a la estructura Lista de TextoLinea
     *
     * @param arrayScript
     * @return
     * @throws SQLException
     */
    public List<TextoLinea> fromDBListTextoLinea(Array arrayScript) throws SQLException {
        try {
            if (!Objects.isNull(arrayScript)) {
                List<TextoLinea> arrayTextoLinea = new ArrayList<>();
                Object[] subs = (Object[]) arrayScript.getArray();
                for (Object sub : subs) {
                    Object[] texto_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();
                    String linea = (String) texto_cols[0];

                    TextoLinea textoLinea = TextoLinea.builder().valor(StringUtils.EMPTY).build();
                    if (StringUtils.isNotBlank(linea)) {
                        textoLinea = TextoLinea.builder().valor(linea).build();
                    }

                    arrayTextoLinea.add(textoLinea);
                }
                return arrayTextoLinea;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ServiceSupportScript.arrayToTextoLineas] Error: %s", e.getMessage());
        }
        return null;
    }

    /**
     * Convierte un Array con la estructura de un script a un objeto Script
     *
     * @param array
     * @return
     * @throws SQLException
     */
    public Script fromDBScript(Object array) throws SQLException {
        Object[] cols = ((oracle.jdbc.OracleStruct) array).getAttributes();

        Script script = Script.builder()
                .tipoScript((String) cols[0])
                .lineasScript(fromDBListTextoLinea((Array) cols[1]))
                .nombreScript((String) cols[2])
                .codigoEstadoScript((BigDecimal) cols[3])
                .descripcionEstadoScript((String) cols[4])
                .numeroOrden((BigDecimal) cols[5])
                .nombreScriptLanza((String) cols[6])
                .lineasScriptLanza(fromDBListTextoLinea((Array) cols[7]))
                .nombreScriptLog((String) cols[8])
                .charset_script((String) cols[9])
                .build();

        return script;
    }

    /**
     * Convierte una estructura de la BBDD en una Lista de Scripts
     *
     * @param arrayScripts
     * @return
     * @throws SQLException
     */
    public List<Script> fromDBListScript(Array arrayScripts) throws SQLException {

        List<Script> scripts = new ArrayList<>();
        if (arrayScripts != null) {
            Object[] rows = (Object[]) arrayScripts.getArray();
            for (Object row : rows) {
                /*Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                Script script = Script.builder().tipoScript((String) cols[0]).nombreScript((String) cols[2])
                        .codigoEstadoScript((BigDecimal) cols[3]).descripcionEstadoScript((String) cols[4])
                        .numeroOrden((BigDecimal) cols[5]).nombreScriptLanza((String) cols[6])
                        .txtScriptLanza((String) cols[7]).nombreScriptLog((String) cols[8]).build();

                fillScriptLines(script, cols);
                scripts.add(script);
                 */
                scripts.add(fromDBScript(row));
            }
        }
        return scripts;
    }

    /**
     * A partir de una Lista de TextoLinea crea la estructura para guardar en la
     * BBDD
     *
     * @param conn
     * @param lineas
     * @return
     * @throws SQLException
     */
    public Array toDBListTextoLinea(Connection conn, List<TextoLinea> lineas) throws SQLException {
        if (lineas == null || lineas.isEmpty()) {
            return null;
        }
        String typeTableLinea = createCallType(MDSQLConstants.T_T_LINEA);
        Struct[] structLinea = new Struct[lineas.size()];
        String typeRecordLinea = createCallType(MDSQLConstants.T_R_LINEA);

        int arrayIndexLinea = 0;
        for (TextoLinea data : lineas) {
            structLinea[arrayIndexLinea++] = conn.createStruct(typeRecordLinea,
                    new Object[]{data.getValor()});
        }
        return ((OracleConnection) conn).createOracleArray(typeTableLinea, structLinea);
    }

    /**
     *
     * @param conn
     * @param script
     * @return
     * @throws SQLException
     */
    public Struct toDBScript(Connection conn, Script script) throws SQLException {
        if (script == null) {
            return null;
        }
        String typeRecordScript = createCallType(MDSQLConstants.T_R_SCRIPT);

        Array textoLineas = toDBListTextoLinea(conn, script.getLineasScript());
        return conn.createStruct(typeRecordScript,
                new Object[]{script.getTipoScript(),
                    textoLineas,
                    script.getNombreScript(),
                    script.getCodigoEstadoScript(),
                    script.getDescripcionEstadoScript(),
                    script.getNumeroOrden(),
                    script.getNombreScriptLanza(),
                    script.getLineasScriptLanza(),
                    script.getNombreScriptLog(),
                    script.getCharset_script()
                });
    }

    /**
     * Data una lista de scripts lo convierte a Array para pasarlo a la BBDD
     *
     * @param conn
     * @param scripts
     * @return
     * @throws SQLException
     */
    public Array toDBListScript(Connection conn, List<Script> scripts) throws SQLException {
        if (scripts == null || scripts.isEmpty()) {
            return null;
        }
        String typeTableScript = createCallType(MDSQLConstants.T_T_SCRIPT);

        Struct[] struct = null;
        if (CollectionUtils.isNotEmpty(scripts)) {
            struct = new Struct[scripts.size()];

            int index = 0;
            for (Script data : scripts) {
                struct[index++] = toDBScript(conn, data);
            }
        }
        return ((OracleConnection) conn).createOracleArray(typeTableScript, struct);
    }

    public List<ConsultaBD> fromDBListConsultaBD(Array arrayConsultaBD) throws SQLException {
        List<ConsultaBD> listaConsultaBD = new ArrayList<>();

        if (arrayConsultaBD != null) {
            Object[] rows = (Object[]) arrayConsultaBD.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                ConsultaBD consultaBD = ConsultaBD.builder()
                        .nombreBBDD((String) cols[0])
                        .nombreEsquema((String) cols[1])
                        .password((String) cols[2])
                        .nombreScript((String) cols[3])
                        .nombreScriptLog((String) cols[5])
                        .build();

                consultaBD.setScript(fromDBListTextoLinea((Array) cols[4]));
                listaConsultaBD.add(consultaBD);
            }
        }
        return listaConsultaBD;
    }

}
