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

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.service.CuadreService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.CUADRE_SERVICE)
@Slf4j
public class CuadreServiceImpl extends ServiceSupport implements CuadreService {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<CuadreOperacion> consultaCuadreOperacionesScript(BigDecimal idProceso, BigDecimal numeroOrden)
			throws ServiceException {
		String runSP = createCall("p_con_cuadre_oper_script", MDSQLConstants.CALL_05_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeCuadreOperaciones = createCallType(MDSQLConstants.T_T_CUADRE_OPER);
			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.registerOutParameter(3, Types.ARRAY, typeCuadreOperaciones);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(4);

			if (result == 0) {
				throw buildException(callableStatement.getArray(5));
			}

			List<CuadreOperacion> cuadreOperacions = new ArrayList<>();
			Array arrayCuadreOperaciones = callableStatement.getArray(3);

			if (arrayCuadreOperaciones != null) {
				Object[] rows = (Object[]) arrayCuadreOperaciones.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					CuadreOperacion cuadreOperacion = CuadreOperacion.builder().tipoObjeto((String) cols[0])
							.tipoAccion((String) cols[1]).numeroOperacionBBDD((BigDecimal) cols[2])
							.numeroOperacionScript((BigDecimal) cols[3]).build();

					cuadreOperacions.add(cuadreOperacion);
				}
			}

			return cuadreOperacions;
		} catch (SQLException e) {
			LogWrapper.error(log, "[CuadreService.consultaCuadreOperacionesScript] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	@SneakyThrows
	public List<CuadreObjeto> consultaCuadreOperacionesObjetoScript(BigDecimal idProceso, BigDecimal numeroOrden)
			throws ServiceException {
		String runSP = createCall("p_con_cuadre_obj_script", MDSQLConstants.CALL_05_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeCuadreObjetoScript = createCallType(MDSQLConstants.T_T_CUADRE_OBJ);
			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.registerOutParameter(3, Types.ARRAY, typeCuadreObjetoScript);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(4);

			if (result == 0) {
				throw buildException(callableStatement.getArray(5));
			}

			List<CuadreObjeto> cuadreObjetos = new ArrayList<>();
			Array arrayCuadreOperaciones = callableStatement.getArray(3);

			if (arrayCuadreOperaciones != null) {
				Object[] rows = (Object[]) arrayCuadreOperaciones.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					CuadreObjeto cuadreObjeto = CuadreObjeto.builder().nombreObjeto((String) cols[0]).tipoObjeto((String) cols[1])
							.tipoAccion((String) cols[2]).numeroOperacionBBDD((BigDecimal) cols[3])
							.numeroOperacionScript((BigDecimal) cols[4]).build();

					cuadreObjetos.add(cuadreObjeto);
				}
			}

			return cuadreObjetos;
		} catch (SQLException e) {
			LogWrapper.error(log, "[CuadreService.consultaCuadreOperacionesObjetoScript] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}
}
