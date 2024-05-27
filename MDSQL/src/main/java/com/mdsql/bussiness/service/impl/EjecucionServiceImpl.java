package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionParche;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.EjecucionService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.EJECUCION_SERVICE)
@Slf4j
public class EjecucionServiceImpl extends ServiceSupport implements EjecucionService {

    @Autowired
    private DataSource dataSource;


    @Override
    @SneakyThrows
    public OutputRegistraEjecucion registraEjecucion(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> lineas) {
        String runSP = createCall("p_registra_ejecucion", MDSQLConstants.CALL_13_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, numeroOrden, codigoUsuario, lineas);

            Struct[] structLinea = new Struct[lineas.size()];

            int arrayIndexLinea = 0;
            for (TextoLinea data : lineas) {
                structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea,
                        new Object[]{data.getValor()});
            }

            Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

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

            callableStatement.registerOutParameter(12, Types.INTEGER);
            callableStatement.registerOutParameter(13, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(12);

            if (result == 0) {
                throw buildException(callableStatement.getArray(13));
            }
            
            OutputRegistraEjecucion outputRegistraEjecucion = new OutputRegistraEjecucion();
            outputRegistraEjecucion.setResult(result);
            
            // Hay avisos
            if (result == 2) {
            	outputRegistraEjecucion.setServiceException(buildException(callableStatement.getArray(13)));
         	}

            BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(5);
            String descripcionEstadoProceso = callableStatement.getString(6);
            String nombreScript = callableStatement.getString(7);
            BigDecimal codigoEstadoScript = callableStatement.getBigDecimal(8);
            String descripcionEstadoScript = callableStatement.getString(9);
            String txtCuadreOperacion = callableStatement.getString(10);
            String txtCuadreObj = callableStatement.getString(11);

            outputRegistraEjecucion.setCodigoEstadoProceso(codigoEstadoProceso);
            outputRegistraEjecucion.setDescripcionEstadoProceso(descripcionEstadoProceso);
            outputRegistraEjecucion.setNombreScript(nombreScript);
            outputRegistraEjecucion.setCodigoEstadoScript(codigoEstadoScript);
            outputRegistraEjecucion.setDescripcionEstadoScript(descripcionEstadoScript);
            outputRegistraEjecucion.setTxtCuadreOperacion(txtCuadreOperacion);
            outputRegistraEjecucion.setTxtCuadreObj(txtCuadreObj);

            return outputRegistraEjecucion;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EjecucionService.registraEjecucion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public OutputRegistraEjecucionType registraEjecucionType(BigDecimal idProceso, String codigoUsuario, List<TextoLinea> logScript) {
        String runSP = createCall("p_registra_ejecucion_type", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

            String typeError = createCallTypeError();
            String typeType = createCallType(MDSQLConstants.T_T_TYPE);

            logProcedure(runSP, idProceso, codigoUsuario, logScript);

            Struct[] structLinea = new Struct[logScript.size()];

            int arrayIndexLinea = 0;
            for (TextoLinea data : logScript) {
                structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea,
                        new Object[]{data.getValor()});
            }

            Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, codigoUsuario);
            callableStatement.setArray(3, arrayLinea);
            callableStatement.registerOutParameter(4, Types.NUMERIC);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeType);

            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(7);

            if (result == 0) {
                throw buildException(callableStatement.getArray(8));
            }
            
            OutputRegistraEjecucionType outputRegistraEjecucionType = new OutputRegistraEjecucionType();
            outputRegistraEjecucionType.setResult(result);
            
            // Hay avisos
            if (result == 2) {
            	outputRegistraEjecucionType.setServiceException(buildException(callableStatement.getArray(8)));
         	}

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

        } catch (
                SQLException e) {
            LogWrapper.error(log, "[EjecucionService.registraEjecucionType] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public OutputRegistraEjecucionParche registraEjecucionParche(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> logScript, String indRepara) {
        String runSP = createCall("p_registra_ejecucion_parche", MDSQLConstants.CALL_14_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, numeroOrden, codigoUsuario, logScript);

            Struct[] structLinea = new Struct[logScript.size()];

            int arrayIndexLinea = 0;
            for (TextoLinea data : logScript) {
                structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea,
                        new Object[]{data.getValor()});
            }

            Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

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

            callableStatement.registerOutParameter(13, Types.INTEGER);
            callableStatement.registerOutParameter(14, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(13);

            if (result == 0) {
                throw buildException(callableStatement.getArray(14));
            }

            BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(6);
            String descripcionEstadoProceso = callableStatement.getString(7);
            String nombreScript = callableStatement.getString(8);
            BigDecimal codigoEstadoScript = callableStatement.getBigDecimal(9);
            String descripcionEstadoScript = callableStatement.getString(10);
            String txtCuadreOperacion = callableStatement.getString(11);
            String txtCuadreObjeto = callableStatement.getString(12);

            OutputRegistraEjecucionParche outputRegistraEjecucionParche = OutputRegistraEjecucionParche.builder()
                    .codigoEstadoProceso(codigoEstadoProceso)
                    .descripcionEstadoProceso(descripcionEstadoProceso)
                    .nombreScript(nombreScript)
                    .codigoEstadoScript(codigoEstadoScript)
                    .descripcionEstadoScript(descripcionEstadoScript)
                    .txtCuadreOperacion(txtCuadreOperacion)
                    .txtCuadreObjeto(txtCuadreObjeto)
                    .build();

            return outputRegistraEjecucionParche;

        } catch (
                SQLException e) {
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
