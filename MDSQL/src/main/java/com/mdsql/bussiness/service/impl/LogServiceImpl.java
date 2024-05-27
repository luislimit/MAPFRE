package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.InputEliminaLog;
import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdsql.bussiness.service.LogService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.LOG_SERVICE)
@Slf4j
public class LogServiceImpl extends ServiceSupport implements LogService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<LogEjecucion> logEjecucion(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException {
        String runSP = createCall("p_log_ejecucion", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLogEjecucion = createCallType(MDSQLConstants.T_T_LOG_EJECUCION);
            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, numeroOrden);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeLogEjecucion);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(4);

            if (result == 0) {
                throw buildException(callableStatement.getArray(5));
            }

            List<LogEjecucion> logEjecuciones = new ArrayList<>();
            Array arrayLogEjecucion = callableStatement.getArray(3);

            if (arrayLogEjecucion != null) {
                Object[] rows = (Object[]) arrayLogEjecucion.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    LogEjecucion logEjecucion = LogEjecucion.builder()
                            .nombreScript((String) cols[0])
                            .idProceso((BigDecimal) cols[1])
                            .numeroOrden((BigDecimal) cols[2])
                            .numeroIteracion((BigDecimal) cols[3])
                            .numeroEjecucion((BigDecimal) cols[4])
                            .numeroParche((BigDecimal) cols[5])
                            .numeroSentencia((BigDecimal) cols[6])
                            .tipoObjeto((String) cols[7])
                            .tipoAccion((String) cols[8])
                            .nombreObjeto((String) cols[9])
                            .descripcionEstadoEjecucion((String) cols[10])
                            .mcaEliminada((String) cols[11])
                            .txtObsElimina((String) cols[12])
                            .build();
                    logEjecuciones.add(logEjecucion);
                }
            }
            return logEjecuciones;
        } catch (SQLException e) {
            LogWrapper.error(log, "[LogService.logEjecucion] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public ServiceException eliminaLog(InputEliminaLog inputEliminaLog) throws ServiceException {
        String runSP = createCall("p_elimina_log", MDSQLConstants.CALL_09_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, inputEliminaLog.getIdProceso(), inputEliminaLog.getNumeroOrden(), inputEliminaLog.getNumeroIteracion(), inputEliminaLog.getNumeroEjecucion(),
                    inputEliminaLog.getNumeroParche(), inputEliminaLog.getNumeroSentencia(), inputEliminaLog.getTxtComentario());

            callableStatement.setBigDecimal(1, inputEliminaLog.getIdProceso());
            callableStatement.setBigDecimal(2, inputEliminaLog.getNumeroOrden());
            callableStatement.setBigDecimal(3, inputEliminaLog.getNumeroIteracion());
            callableStatement.setBigDecimal(4, inputEliminaLog.getNumeroEjecucion());
            callableStatement.setBigDecimal(5, inputEliminaLog.getNumeroParche());
            callableStatement.setBigDecimal(6, inputEliminaLog.getNumeroSentencia());
            callableStatement.setString(7, inputEliminaLog.getTxtComentario());
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(8);

            if (result == 0) {
            	ServiceException serviceException = buildException(callableStatement.getArray(9));
            	return serviceException;
            }
            
            if (result == 2) {
				ServiceException serviceException = buildWarning(callableStatement.getArray(9));
				return serviceException;
			}

            return null;
        } catch (SQLException e) {
            LogWrapper.error(log, "[LogService.eliminaLog] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
