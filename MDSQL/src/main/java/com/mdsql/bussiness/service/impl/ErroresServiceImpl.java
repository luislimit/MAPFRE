package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.ErrorScript;
import com.mdsql.bussiness.entities.OutputErroresScript;
import com.mdsql.bussiness.entities.ScriptParche;
import com.mdsql.bussiness.service.ErroresService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.ERRORES_SERVICE)
@Slf4j
public class ErroresServiceImpl extends ServiceSupport implements ErroresService {

	@Autowired
	private DataSource dataSource;

	@Override
	public OutputErroresScript consultaErroresScript(BigDecimal idProceso, BigDecimal numeroOrden)
			throws ServiceException {
		String runSP = createCall("p_con_errores_script", MDSQLConstants.CALL_06_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeErrorScript = createCallType(MDSQLConstants.T_T_ERROR_SCRIPT);
			String typeScriptParche = createCallType(MDSQLConstants.T_T_SCRIPT_PARCHE);
			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.registerOutParameter(3, Types.ARRAY, typeErrorScript);
			callableStatement.registerOutParameter(4, Types.ARRAY, typeScriptParche);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(5);

			if (result == 0) {
				throw buildException(callableStatement.getArray(6));
			}

			List<ErrorScript> errorScripts = new ArrayList<>();
			Array arrayErrorScripts = callableStatement.getArray(3);

			if (arrayErrorScripts != null) {
				Object[] rows = (Object[]) arrayErrorScripts.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					ErrorScript errorScript = ErrorScript.builder().numeroOrden((BigDecimal) cols[0])
							.fechaError((Date) cols[1]).idProceso((BigDecimal) cols[2])
							.numeroIteracion((BigDecimal) cols[3]).numeroEjecucion((BigDecimal) cols[4])
							.nombreScript((String) cols[5]).numeroSentencia((BigDecimal) cols[6])
							.txtError((String) cols[7]).build();
					log.info("{}", errorScript.toString());
					errorScripts.add(errorScript);
				}
			}

			List<ScriptParche> scriptParches = new ArrayList<>();
			Array arrayScriptParches = callableStatement.getArray(4);

			if (arrayScriptParches != null) {
				Object[] rows = (Object[]) arrayScriptParches.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					ScriptParche errorScript = ScriptParche.builder().numeroOrden((BigDecimal) cols[0])
							.codigoEstadoEjecucion((BigDecimal) cols[1]).descripcionEstadoEjecucion((String) cols[2])
							.fechaScript((Date) cols[3]).idProceso((BigDecimal) cols[4])
							.numeroIteracion((BigDecimal) cols[5]).numeroEjecucion((BigDecimal) cols[6])
							.nombreScript((String) cols[7]).txtComentario((String) cols[8]).build();
					scriptParches.add(errorScript);
				}
			}

			OutputErroresScript outputErroresScript = OutputErroresScript.builder().listaErroresScript(errorScripts)
					.listaScriptParche(scriptParches).build();

			return outputErroresScript;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ErroresService.consultaErroresType] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ErrorScript> consultaErroresType(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException {
		String runSP = createCall("p_con_errores_type", MDSQLConstants.CALL_05_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeErrorScript = createCallType(MDSQLConstants.T_T_ERROR_SCRIPT);
			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.registerOutParameter(3, Types.ARRAY, typeErrorScript);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(4);

			if (result == 0) {
				throw buildException(callableStatement.getArray(5));
			}

			List<ErrorScript> errorScripts = new ArrayList<>();
			Array arrayErrorScripts = callableStatement.getArray(3);

			if (arrayErrorScripts != null) {
				Object[] rows = (Object[]) arrayErrorScripts.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					ErrorScript errorScript = ErrorScript.builder().numeroOrden((BigDecimal) cols[0])
							.fechaError((Date) cols[1]).idProceso((BigDecimal) cols[2])
							.numeroIteracion((BigDecimal) cols[3]).numeroEjecucion((BigDecimal) cols[4])
							.nombreScript((String) cols[5]).numeroSentencia((BigDecimal) cols[6])
							.txtError((String) cols[7]).build();
					errorScripts.add(errorScript);
				}
			}

			return errorScripts;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ErroresService.consultaErroresType] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}
}
