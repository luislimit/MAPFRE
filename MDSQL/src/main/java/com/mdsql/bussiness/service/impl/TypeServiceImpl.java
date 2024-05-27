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

import com.mdsql.bussiness.entities.InputProcesaType;
import com.mdsql.bussiness.entities.OutputProcesaType;
import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.TypeService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.TYPE_SERVICE)
@Slf4j
public class TypeServiceImpl extends ServiceSupport implements TypeService {

    @Autowired
    private DataSource dataSource;


    @Override
    public OutputProcesaType procesarType(InputProcesaType inputProcesaType) throws ServiceException {
        String runSP = createCall("p_procesa_type", MDSQLConstants.CALL_22_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

            String typeType = createCallType(MDSQLConstants.T_T_TYPE);

            String typeError = createCallTypeError();

            logProcedure(runSP, inputProcesaType.getLineasScript(), inputProcesaType.getPCodigoProyecto(), inputProcesaType.getPCodigoSubProyecto(), inputProcesaType.getPCodigoPeticion(),
                    inputProcesaType.getPCodigoDemanda(), inputProcesaType.getPCodigoUsr(), inputProcesaType.getPCodigoUsrPeticion(), inputProcesaType.getPNombreBBDD(), inputProcesaType.getPNombreEsquema(),
                    inputProcesaType.getPNombreFichaEntrada(), inputProcesaType.getPTxtRutaEntrada(), inputProcesaType.getPTxtDescripcion());

            Struct[] structLinea = new Struct[inputProcesaType.getLineasScript().size()];

            int arrayIndexLinea = 0;
            for (TextoLinea data : inputProcesaType.getLineasScript()) {
                structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea,
                        new Object[]{data.getValor()});
            }

            Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

            callableStatement.setArray(1, arrayLinea);
            callableStatement.setString(2, inputProcesaType.getPCodigoProyecto());
            callableStatement.setString(3, inputProcesaType.getPCodigoSubProyecto());
            callableStatement.setString(4, inputProcesaType.getPCodigoPeticion());
            callableStatement.setString(5, inputProcesaType.getPCodigoDemanda());
            callableStatement.setString(6, inputProcesaType.getPCodigoUsr());
            callableStatement.setString(7, inputProcesaType.getPCodigoUsrPeticion());
            callableStatement.setString(8, inputProcesaType.getPNombreBBDD());
            callableStatement.setString(9, inputProcesaType.getPNombreEsquema());
            callableStatement.setString(10, inputProcesaType.getPNombreFichaEntrada());
            callableStatement.setString(11, inputProcesaType.getPTxtRutaEntrada());
            callableStatement.setString(12, inputProcesaType.getPTxtDescripcion());
            callableStatement.registerOutParameter(13, Types.NUMERIC);
            callableStatement.registerOutParameter(14, Types.DATE);
            callableStatement.registerOutParameter(15, Types.NUMERIC);
            callableStatement.registerOutParameter(16, Types.VARCHAR);
            callableStatement.registerOutParameter(17, Types.VARCHAR);
            callableStatement.registerOutParameter(18, Types.ARRAY, tableLinea);
            callableStatement.registerOutParameter(19, Types.VARCHAR);
            callableStatement.registerOutParameter(20, Types.ARRAY, typeType);

            callableStatement.registerOutParameter(21, Types.INTEGER);
            callableStatement.registerOutParameter(22, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(21);

            if (result == 0) {
                throw buildException(callableStatement.getArray(22));
            }


            List<Type> types = new ArrayList<>();
            Array arrayTypes = callableStatement.getArray(20);

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

            List<TextoLinea> scriptLanza = new ArrayList<>();
            Array arrayLanza = callableStatement.getArray(18);

            if (arrayLanza != null) {
                Object[] rows = (Object[]) arrayLanza.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    TextoLinea linea = TextoLinea.builder()
                            .valor((String) cols[0])
                            .build();

                    scriptLanza.add(linea);
                }
            }

            BigDecimal idProceso = callableStatement.getBigDecimal(13);
            Date pFechaProceso = callableStatement.getDate(14);
            BigDecimal pCodigoEstadoProceso = callableStatement.getBigDecimal(15);
            String pDescripcionEstadoProceso = callableStatement.getString(16);
            String nombreScriptLanza = callableStatement.getString(17);
            String nombreScripLog = callableStatement.getString(19);

            OutputProcesaType outputProcesaType = new OutputProcesaType();
            outputProcesaType.setIdProceso(idProceso);
            outputProcesaType.setPFechaProceso(pFechaProceso);
            outputProcesaType.setPCodigoEstadoProceso(pCodigoEstadoProceso);
            outputProcesaType.setPDescripcionEstadoProceso(pDescripcionEstadoProceso);
            outputProcesaType.setPNombreScriptLanza(nombreScriptLanza);
            outputProcesaType.setPNombreScriptLog(nombreScripLog);
            outputProcesaType.setTxtScriptLanza(scriptLanza);
            outputProcesaType.setListaType(types);
            
            return outputProcesaType;

        } catch (
                SQLException e) {
            LogWrapper.error(log, "[TypeService.procesarScript] Error: %s", e.getMessage());
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
            LogWrapper.error(log, "[TypeService.fillScripType] Error: %s", e.getMessage());
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
            LogWrapper.error(log, "[TypeService.fillScriptTypeLines] Error: %s", e.getMessage());
        }
    }
}
