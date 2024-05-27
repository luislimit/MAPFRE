package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.utils.LogWrapper;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LVARONA
 */
@Slf4j
public class ServiceSupportScript extends ServiceSupport {

    /**
     * 
     * @param arrayScripts
     * @return
     * @throws SQLException 
     */
    public List<Script> arrayToListScript(Array arrayScripts) throws SQLException {

        List<Script> scripts = new ArrayList<>();
        if (arrayScripts != null) {
            Object[] rows = (Object[]) arrayScripts.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                Script script = Script.builder().tipoScript((String) cols[0]).nombreScript((String) cols[2])
                        .codigoEstadoScript((BigDecimal) cols[3]).descripcionEstadoScript((String) cols[4])
                        .numeroOrden((BigDecimal) cols[5]).nombreScriptLanza((String) cols[6])
                        .txtScriptLanza((String) cols[7]).nombreScriptLog((String) cols[8]).build();

                fillScriptLines(script, cols);

                scripts.add(script);
            }
        }
        return scripts;
    }

    /**
     * @param script
     * @param cols
     * @throws SQLException
     */
    private void fillScriptLines(Script script, Object[] cols) throws SQLException {
        try {
            Array arrayScript = (Array) cols[1];
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

                script.setLineasScript(arrayTextoLinea);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ScriptService.fillScriptLines] Error: %s", e.getMessage());
        }
    }
}
