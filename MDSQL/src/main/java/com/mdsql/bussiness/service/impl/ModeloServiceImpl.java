package com.mdsql.bussiness.service.impl;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import com.mdsql.bussiness.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.MODELO_SERVICE)
@Slf4j
public class ModeloServiceImpl extends ServiceSupport implements ModeloService {

	@Autowired
	private DataSource dataSource;

	@Override
	public OutputConsultaModelos consultaModelos(String codigoProyecto, String nombreModelo,
			String codigoSubProyecto) throws ServiceException {
		String runSP = createCall("p_con_modelos", MDSQLConstants.CALL_06_ARGS);

		try (Connection conn = dataSource.getConnection();
			 CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeModelo = createCallType(MDSQLConstants.T_T_MODELO);
			String typeError = createCallTypeError();

			logProcedure(runSP, codigoProyecto, nombreModelo, codigoSubProyecto);

			callableStatement.setString(1, codigoProyecto);
			callableStatement.setString(2, nombreModelo);
			callableStatement.setString(3, codigoSubProyecto);
			callableStatement.registerOutParameter(4, Types.ARRAY, typeModelo);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(5);

			if (result == 0) {
				throw buildException(callableStatement.getArray(6));
			}

			OutputConsultaModelos outputConsultaModelos = new OutputConsultaModelos();
			outputConsultaModelos.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputConsultaModelos.setServiceException(buildException(callableStatement.getArray(6)));
			}
			
			List<Modelo> modelos = new ArrayList<>();
			Array arrayModelos = callableStatement.getArray(4);
			
			if (arrayModelos != null) {
				Object[] rows = (Object[]) arrayModelos.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					Modelo modelo = Modelo.builder()
							.codigoProyecto((String) cols[0])
							.nombreModelo((String) cols[1])
							.nombreEsquema((String) cols[2])
							.nombreBbdd((String) cols[3])
							.nombreCarpetaAdj((String) cols[4])
							.codigoCapaUsrown((String) cols[5])
							.mcaVariables((String) cols[6])
							.mcaGrantAll((String) cols[7])
							.mcaGrantPublic((String) cols[8])
							.mcaInh((String) cols[9])
							.observaciones((String) cols[10])
							.entregaPDC((String) cols[11])
							.mcaHis((String) cols[12])
							.build();
					
					// Lista de subproyectos
					fillSubproyectos(modelo, cols);
					
					modelos.add(modelo);
				}
				
				outputConsultaModelos.setModelos(modelos);
			}

			return outputConsultaModelos;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ModeloService.consultaModelos] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public OutputVariablesModelo consultaVariables(Modelo modelo) throws ServiceException {
		String runSP = createCall("p_con_vbles_modelo", MDSQLConstants.CALL_04_ARGS);

		try (Connection conn = dataSource.getConnection();
			 CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeVariable = createCallType(MDSQLConstants.T_T_VARIABLE);
			String typeError = createCallTypeError();

			String codigoProyecto = modelo.getCodigoProyecto();
			logProcedure(runSP, codigoProyecto);

			callableStatement.setString(1, codigoProyecto);
			callableStatement.registerOutParameter(2, Types.ARRAY, typeVariable);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(3);

			if (result == 0) {
				throw buildException(callableStatement.getArray(4));
			}
			
			OutputVariablesModelo outputVariablesModelo = new OutputVariablesModelo();
			outputVariablesModelo.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputVariablesModelo.setServiceException(buildException(callableStatement.getArray(4)));
			}

			List<Variable> variables = new ArrayList<>();
			Array arrayVariables = callableStatement.getArray(2);

			if (arrayVariables != null) {
				Object[] rows = (Object[]) arrayVariables.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					Variable variable = Variable.builder()
							.codigoProyecto((String) cols[0])
							.codigoVariable((String) cols[1])
							.entorno((String) cols[2])
							.bbdd((String) cols[3])
							.tipo((String) cols[4])
							.valor((String) cols[5])
							.valorSustituir((String) cols[6])
							.peticion((String) cols[7])
							.usoInterno((String) cols[8])
							.habilitada((String) cols[9])
							.comentario((String) cols[10])
							.usrModificacion((String) cols[11])
							.fechaModificacion((java.util.Date) cols[12])
							.usrAlta((String) cols[13])
							.fechaAlta((java.util.Date) cols[14])
							.build();

					variables.add(variable);
				}
				
				outputVariablesModelo.setVariables(variables);
			}
			
			return outputVariablesModelo;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ModeloService.consultaVariables] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public OutputActualizarVariablesModelo actualizarVariableModelo(String codigoProyecto, String codigoVariable, String entorno, String bbdd, String tipoVariable, String valorVariable, String valorSustituir, String codigoPeticion, String mcaInterno, String mcaHabilitado, String comentario, String codUsr) throws ServiceException {
		String runSP = createCall("p_mnto_vbles_modelo", MDSQLConstants.CALL_14_ARGS);

		try (Connection conn = dataSource.getConnection();
			 CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeError = createCallTypeError();

			logProcedure(runSP, codigoProyecto, codigoVariable, entorno, bbdd, tipoVariable, valorVariable, valorSustituir, codigoPeticion, mcaInterno, mcaHabilitado, comentario, codUsr);

			callableStatement.setString(1, codigoProyecto);
			callableStatement.setString(2, codigoVariable);
			callableStatement.setString(3, entorno);
			callableStatement.setString(4, bbdd);
			callableStatement.setString(5, tipoVariable);
			callableStatement.setString(6, valorVariable);
			callableStatement.setString(7, valorSustituir);
			callableStatement.setString(8, codigoPeticion);
			callableStatement.setString(9, mcaInterno);
			callableStatement.setString(10, mcaHabilitado);
			callableStatement.setString(11, comentario);
			callableStatement.setString(12, codUsr);

			callableStatement.registerOutParameter(13, Types.INTEGER);
			callableStatement.registerOutParameter(14, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(13);

			if (result == 0) {
				throw buildException(callableStatement.getArray(14));
			}
			
			OutputActualizarVariablesModelo outputActualizarVariablesModelo = new OutputActualizarVariablesModelo();
			outputActualizarVariablesModelo.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputActualizarVariablesModelo.setServiceException(buildException(callableStatement.getArray(14)));
			}

			return outputActualizarVariablesModelo;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ModeloService.actualizarVariableModelo] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * @param modelo
	 * @param arraySubProyectos
	 * @throws SQLException
	 */
	private void fillSubproyectos(Modelo modelo, Object[] cols) throws SQLException {
		try {
			Array arraySubProyectos = (Array) cols[13];
			if (!Objects.isNull(arraySubProyectos)) {
				List<SubProyecto> subProyectos = new ArrayList<>();
				Object[] subs = (Object[]) arraySubProyectos.getArray();
				for (Object sub : subs) {
					Object[] sub_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();
	
					SubProyecto subProyecto = SubProyecto.builder()
							.codigoSubProyecto((String) sub_cols[0])
							.descripcionSubProyecto((String) sub_cols[1])
							.build();
					subProyectos.add(subProyecto);
				}
				
				modelo.setSubproyectos(subProyectos);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LogWrapper.error(log, "[ModeloService.fillSubproyectos] Error: %s", e.getMessage());
		}
	}
}
