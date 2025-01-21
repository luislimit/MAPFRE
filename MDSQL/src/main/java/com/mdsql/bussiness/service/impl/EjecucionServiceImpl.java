package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.EjecucionService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.EJECUCION_SERVICE)
@Slf4j
public class EjecucionServiceImpl extends ServiceSupportScript implements EjecucionService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputRegistraEjecucion registraEjecucion(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> lineas)
            throws ServiceException {
        String runSP = createCall("p_registra_ejecucion", 13);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, numeroOrden, codigoUsuario, lineas);

            Array arrayLinea = toDBListTextoLinea(conn, lineas);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.setString(3, codigoUsuario);
            callableStatement.setArray(4, arrayLinea);
            callableStatement.registerOutParameter(5, Types.NUMERIC);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.NUMERIC);
            callableStatement.registerOutParameter(9, Types.VARCHAR);
            callableStatement.registerOutParameter(10, Types.VARCHAR);
            callableStatement.registerOutParameter(11, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputRegistraEjecucion outputRegistraEjecucion = new OutputRegistraEjecucion();
            outputRegistraEjecucion.setOutputWarning(result);
            outputRegistraEjecucion.setCodigoEstadoProceso(callableStatement.getBigDecimal(5));
            outputRegistraEjecucion.setDescripcionEstadoProceso(callableStatement.getString(6));
            outputRegistraEjecucion.setNombreScript(callableStatement.getString(7));
            outputRegistraEjecucion.setCodigoEstadoScript(callableStatement.getBigDecimal(8));
            outputRegistraEjecucion.setDescripcionEstadoScript(callableStatement.getString(9));
            outputRegistraEjecucion.setTxtCuadreOperacion(callableStatement.getString(10));
            outputRegistraEjecucion.setTxtCuadreObj(callableStatement.getString(11));

            return outputRegistraEjecucion;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EjecucionService.registraEjecucion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputRegistraEjecucionType registraEjecucionType(BigDecimal idProceso, String codigoUsuario, List<TextoLinea> logScript)
            throws ServiceException {
        String runSP = createCall("p_registra_ejecucion_type", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeType = createCallType(MDSQLConstants.T_T_TYPE);

            logProcedure(runSP, idProceso, codigoUsuario, logScript);

            Array arrayLinea = toDBListTextoLinea(conn, logScript);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, codigoUsuario);
            callableStatement.setArray(3, arrayLinea);
            callableStatement.registerOutParameter(4, Types.NUMERIC);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeType);

            OutputWarning result = executeStatement(callableStatement);

            OutputRegistraEjecucionType outputRegistraEjecucionType = new OutputRegistraEjecucionType();
            outputRegistraEjecucionType.setOutputWarning(result);

            BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(4);
            String descripcionEstadoProceso = callableStatement.getString(5);

            List<Type> types = new ArrayList<>();
            Array arrayTypes = callableStatement.getArray(6);

            if (arrayTypes != null) {
                Object[] rows = (Object[]) arrayTypes.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Type type = Type.builder()
                            .numeroOrdenType((BigDecimal) cols[0])
                            .codigoEstadoScript((BigDecimal) cols[1])
                            .descripcionEstadoScript((String) cols[2])
                            .fechaCambio((Date) cols[3])
                            .numeroEjecucion((BigDecimal) cols[4])
                            .TYS((String) cols[5])
                            .TYB((String) cols[6])
                            .PDC((String) cols[7])
                            .DROP((String) cols[8])
                            .nombreObjeto((String) cols[9])
                            .build();

                    fillScripType(type, cols);

                    types.add(type);
                }
            }
            outputRegistraEjecucionType.setCodigoEstadoProceso(codigoEstadoProceso);
            outputRegistraEjecucionType.setDescripcionEstadoProceso(descripcionEstadoProceso);
            outputRegistraEjecucionType.setListaType(types);

            return outputRegistraEjecucionType;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EjecucionService.registraEjecucionType] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputRegistraEjecucion registraEjecucionParche(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> logScript, String indRepara)
            throws ServiceException {
        String runSP = createCall("p_registra_ejecucion_parche", 14);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, numeroOrden, codigoUsuario, logScript);

            Array arrayLinea = toDBListTextoLinea(conn, logScript);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.setString(3, codigoUsuario);
            callableStatement.setArray(4, arrayLinea);
            callableStatement.setString(5, indRepara);
            callableStatement.registerOutParameter(6, Types.NUMERIC);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.VARCHAR);
            callableStatement.registerOutParameter(9, Types.NUMERIC);
            callableStatement.registerOutParameter(10, Types.VARCHAR);
            callableStatement.registerOutParameter(11, Types.VARCHAR);
            callableStatement.registerOutParameter(12, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputRegistraEjecucion output = new OutputRegistraEjecucion();
            output.setOutputWarning(result);
            output.setCodigoEstadoProceso(callableStatement.getBigDecimal(6));
            output.setDescripcionEstadoProceso(callableStatement.getString(7));
            output.setNombreScript(callableStatement.getString(8));
            output.setCodigoEstadoScript(callableStatement.getBigDecimal(9));
            output.setDescripcionEstadoScript(callableStatement.getString(10));
            output.setTxtCuadreOperacion(callableStatement.getString(11));
            output.setTxtCuadreObj(callableStatement.getString(12));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EjecucionService.registraEjecucionParche] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * @param type
     * @param cols
     * @throws SQLException
     */
    private void fillScripType(Type type, Object[] cols) throws SQLException {
        try {
            Array arrayTypes = (Array) cols[10];
            if (!Objects.isNull(arrayTypes)) {
                List<ScriptType> scriptTypes = new ArrayList<>();
                Object[] subs = (Object[]) arrayTypes.getArray();
                for (Object sub : subs) {
                    Object[] sub_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();

                    ScriptType scriptType = ScriptType.builder()
                            .nombreScript((String) sub_cols[1])
                            .tipoScript((String) sub_cols[2])
                            .build();
                    //fill script type lines
                    fillScriptTypeLines(scriptType, sub_cols);
                    scriptTypes.add(scriptType);
                }

                type.setScriptType(scriptTypes);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EjecucionService.fillScripType] Error: %s", e.getMessage());
        }
    }

    /**
     * @param scriptType
     * @param cols
     * @throws SQLException
     */
    private void fillScriptTypeLines(ScriptType scriptType, Object[] cols) throws SQLException {
        try {
            Array arrayLines = (Array) cols[0];
            if (!Objects.isNull(arrayLines)) {
                List<TextoLinea> textoLineas = new ArrayList<>();
                Object[] subs = (Object[]) arrayLines.getArray();
                for (Object sub : subs) {
                    Object[] sub_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();

                    TextoLinea textoLinea = TextoLinea.builder()
                            .valor((String) sub_cols[0])
                            .build();

                    textoLineas.add(textoLinea);
                }

                scriptType.setTxtScript(textoLineas);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EjecucionService.fillScriptTypeLines] Error: %s", e.getMessage());
        }
    }

}
