package com.mdsql.bussiness.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Paths;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.entities.InputDescartarScript;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputReparaScript;
import com.mdsql.bussiness.entities.OutputDescartarScript;
import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionParche;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.OutputReparaScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.ScriptOld;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.EjecucionService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.SCRIPT_SERVICE)
@Slf4j
public class ScriptServiceImpl extends ServiceSupport implements ScriptService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BBDDService bbddService;

	@Autowired
	private EjecucionService ejecucionService;

	@Override
	public OutputProcesaScript procesarScript(InputProcesaScript inputProcesaScript) throws ServiceException {
		String runSP = createCall("p_procesa_script", MDSQLConstants.CALL_24_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
			String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

			String tableObjHis = createCallType(MDSQLConstants.T_T_OBJ_HIS);
			String recordObjHis = createCallType(MDSQLConstants.T_R_OBJ_HIS);

			String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);

			String typeError = createCallTypeError();

			logProcedure(runSP, inputProcesaScript.getLineasScript(), inputProcesaScript.getPCodigoProyecto(),
					inputProcesaScript.getPCodigoSubProyecto(), inputProcesaScript.getPCodigoPeticion(),
					inputProcesaScript.getPCodigoDemanda(), inputProcesaScript.getPCodigoUsr(),
					inputProcesaScript.getPCodigoUsrPeticion(), inputProcesaScript.getPMcaReprocesa(),
					inputProcesaScript.getPNombreBBDD(), inputProcesaScript.getPNombreEsquema(),
					inputProcesaScript.getPMcaHIS(), inputProcesaScript.getPNombreBBDDHIS(),
					inputProcesaScript.getPNombreEsquemaHis(), inputProcesaScript.getPNombreFichaEntrada(),
					inputProcesaScript.getPTxtRutaEntrada(), inputProcesaScript.getListaObjetoHis(),
					inputProcesaScript.getPTxtDescripcion());

			Struct[] structLinea = new Struct[inputProcesaScript.getLineasScript().size()];

			int arrayIndexLinea = 0;
			for (TextoLinea data : inputProcesaScript.getLineasScript()) {
				structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea, new Object[] { data.getValor() });
			}

			Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

			Array arrayObjHis = null;
			Struct[] structObjHis = null;
			if (CollectionUtils.isNotEmpty(inputProcesaScript.getListaObjetoHis())) {
				structObjHis = new Struct[inputProcesaScript.getListaObjetoHis().size()];

				int arrayIndexObjHis = 0;
				for (SeleccionHistorico data : inputProcesaScript.getListaObjetoHis()) {
					String mcaVigente = AppHelper.normalizeValueToCheck(data.getVigente());
					String mcaHistorico = AppHelper.normalizeValueToCheck(data.getHistorico());

					structObjHis[arrayIndexObjHis++] = conn.createStruct(recordObjHis,
							new Object[] { data.getObjeto(), data.getTipo(), mcaVigente, mcaHistorico });
				}
			}

			arrayObjHis = ((OracleConnection) conn).createOracleArray(tableObjHis, structObjHis);

			callableStatement.setArray(1, arrayLinea);
			callableStatement.setString(2, inputProcesaScript.getPCodigoProyecto());
			callableStatement.setString(3, inputProcesaScript.getPCodigoSubProyecto());
			callableStatement.setString(4, inputProcesaScript.getPCodigoPeticion());
			callableStatement.setString(5, inputProcesaScript.getPCodigoDemanda());
			callableStatement.setString(6, inputProcesaScript.getPCodigoUsr());
			callableStatement.setString(7, inputProcesaScript.getPCodigoUsrPeticion());
			callableStatement.setString(8, inputProcesaScript.getPMcaReprocesa());
			callableStatement.setString(9, inputProcesaScript.getPNombreBBDD());
			callableStatement.setString(10, inputProcesaScript.getPNombreEsquema());
			callableStatement.setString(11, inputProcesaScript.getPMcaHIS());
			callableStatement.setString(12, inputProcesaScript.getPNombreBBDDHIS());
			callableStatement.setString(13, inputProcesaScript.getPNombreEsquemaHis());
			callableStatement.setString(14, inputProcesaScript.getPNombreFichaEntrada());
			callableStatement.setString(15, inputProcesaScript.getPTxtRutaEntrada());
			callableStatement.setArray(16, arrayObjHis);
			callableStatement.setString(17, inputProcesaScript.getPTxtDescripcion());
			callableStatement.registerOutParameter(18, Types.NUMERIC);
			callableStatement.registerOutParameter(19, Types.DATE);
			callableStatement.registerOutParameter(20, Types.NUMERIC);
			callableStatement.registerOutParameter(21, Types.VARCHAR);
			callableStatement.registerOutParameter(22, Types.ARRAY, typeScript);

			callableStatement.registerOutParameter(23, Types.INTEGER);
			callableStatement.registerOutParameter(24, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(23);

			if (result == 0) {
				throw buildException(callableStatement.getArray(24));
			}

			List<Script> scripts = new ArrayList<>();
			Array arrayScripts = callableStatement.getArray(22);

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

			BigDecimal idProceso = callableStatement.getBigDecimal(18);
			Date pFechaProceso = callableStatement.getDate(19);
			BigDecimal pCodigoEstadoProceso = callableStatement.getBigDecimal(20);
			String pDescripcionEstadoProceso = callableStatement.getString(21);

			OutputProcesaScript outputProcesaScript = new OutputProcesaScript();
			outputProcesaScript.setIdProceso(idProceso);
			outputProcesaScript.setPFechaProceso(pFechaProceso);
			outputProcesaScript.setPCodigoEstadoProceso(pCodigoEstadoProceso);
			outputProcesaScript.setPDescripcionEstadoProceso(pDescripcionEstadoProceso);
			outputProcesaScript.setListaScripts(scripts);

			return outputProcesaScript;

		} catch (SQLException e) {
			LogWrapper.error(log, "[ScriptService.procesarScript] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
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

	@Override
	@SneakyThrows
	public OutputExcepcionScript excepcionScript(BigDecimal idProceso, BigDecimal numeroOrden, String txtComentario,
			String codigoUsuario) {
		String runSP = createCall("p_excepcion_script", MDSQLConstants.CALL_10_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden, txtComentario, codigoUsuario);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.setString(3, txtComentario);
			callableStatement.setString(4, codigoUsuario);
			callableStatement.registerOutParameter(5, Types.NUMERIC);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.NUMERIC);
			callableStatement.registerOutParameter(8, Types.VARCHAR);

			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(9);

			if (result == 0) {
				throw buildException(callableStatement.getArray(10));
			}

			BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(5);
			String descripcionEstadoProceso = callableStatement.getString(6);
			BigDecimal codigoEstadoScript = callableStatement.getBigDecimal(7);
			String descripcionEstadoScript = callableStatement.getString(8);

			OutputExcepcionScript outputExcepcionScript = OutputExcepcionScript.builder()
					.codigoEstadoProceso(codigoEstadoProceso).descripcionEstadoProceso(descripcionEstadoProceso)
					.codigoEstadoScript(codigoEstadoScript).descripcionEstadoScript(descripcionEstadoScript).build();

			return outputExcepcionScript;

		} catch (SQLException e) {
			LogWrapper.error(log, "[ScriptService.excepcionScript] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts) throws ServiceException {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String selectedRoute = session.getSelectedRoute();
			String ruta = selectedRoute.concat(File.separator);
			String nombreEsquema = StringUtils.EMPTY;
			String nombreBBDD = StringUtils.EMPTY;

			Proceso proceso = session.getProceso();
			proceso.setRutaTrabajo(ruta);
			String codigoUsuario = session.getCodUsr();
			List<OutputRegistraEjecucion> ejecuciones = new ArrayList<>();
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);

			if (CollectionUtils.isNotEmpty(scripts)) {
				for (Script script : scripts) {
					// Esto causa reescritura de ficheros
					MDSQLAppHelper.dumpLinesToFile(script.getLineasScript(), Paths.get(ruta.concat(script.getNombreScript())).toFile());

					/**
					 * Según sea el tipo de script (SQL, PDC, SQLH, PDCH), se seleccionará la base
					 * de datos o la de histórico para su ejecución
					 */
					if ("SQL".equals(script.getTipoScript()) || "PDC".equals(script.getTipoScript())) {
						nombreEsquema = bbdd.getNombreEsquema();
						nombreBBDD = bbdd.getNombreBBDD();
					}

					if ("SQLH".equals(script.getTipoScript()) || "PDCH".equals(script.getTipoScript())) {
						nombreEsquema = bbdd.getNombreEsquemaHis();
						nombreBBDD = bbdd.getNombreBBDDHis();
					}

					// Sólo hay que crear el script lanza
					String lanzaFile = ruta.concat(script.getNombreScriptLanza());
					MDSQLAppHelper.writeToFile(script.getTxtScriptLanza().concat(System.lineSeparator()), Paths.get(lanzaFile).toFile());

					String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
					//bbdd.setPassword(password);

					// Ejecución del script
					executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

					// Obtiene el log
					String logFile = ruta.concat(script.getNombreScriptLog());
					List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

					// Registra la ejecución
					OutputRegistraEjecucion outputRegistraEjecucion = ejecucionService.registraEjecucion(
							proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList);
					outputRegistraEjecucion.setNumOrden(script.getNumeroOrden());
					outputRegistraEjecucion.setFechaEjecucion(new Date());
					ejecuciones.add(outputRegistraEjecucion);

					// Si el script ha dado error, no ejecuta el resto
					if ("Error".equals(outputRegistraEjecucion.getDescripcionEstadoScript())
							|| "Descuadrado".equals(outputRegistraEjecucion.getDescripcionEstadoScript())) {
						break;
					}

				}
			}

			return ejecuciones;
		} catch (IOException e) {
			LogWrapper.error(log, "[ScriptService.executeScripts] Error", e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public OutputRegistraEjecucionParche executeScriptParche(BBDD bbdd, Script script) throws ServiceException {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String selectedRoute = session.getSelectedRoute();
			String ruta = selectedRoute.concat(File.separator);
			String nombreEsquema = StringUtils.EMPTY;
			String nombreBBDD = StringUtils.EMPTY;

			Proceso proceso = session.getProceso();
			proceso.setRutaTrabajo(ruta);
			String codigoUsuario = session.getCodUsr();
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);

			/**
			 * Según sea el tipo de script (SQL, PDC, SQLH, PDCH), se seleccionará la base
			 * de datos o la de histórico para su ejecución
			 */
			if ("SQL".equals(script.getTipoScript()) || "PDC".equals(script.getTipoScript())) {
				nombreEsquema = bbdd.getNombreEsquema();
				nombreBBDD = bbdd.getNombreBBDD();
			}

			// Sólo hay que crear el script lanza
			String lanzaFile = ruta.concat(script.getNombreScriptLanza());

			String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
			//bbdd.setPassword(password);

			// Ejecución del script
			executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

			// Obtiene el log
			String logFile = ruta.concat(script.getNombreScriptLog());
			List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

			// Registra la ejecución
			OutputRegistraEjecucionParche outputRegistraEjecucion = ejecucionService.registraEjecucionParche(
					proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList, StringUtils.EMPTY);

			return outputRegistraEjecucion;
		} catch (IOException e) {
			LogWrapper.error(log, "[ScriptService.executeScriptParche] Error", e);
			throw new ServiceException(e);
		}
	}

	@SneakyThrows
	public void ejecutarRepararScript(Script script, Boolean isReparacion, Boolean isSameScript,
			OutputReparaScript outputReparaScript) {
		// TODO return OutputRegistraEjecucion or OutputRegistraEjecucionParche
		// separate?

		Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
		Proceso proceso = session.getProceso();
		String codigoUsuario = session.getCodUsr();
		String ruta = session.getProceso().getRutaTrabajo();
		BBDD bbdd = session.getProceso().getBbdd();
		
		ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
		String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);
		
		String nombreEsquema = StringUtils.isNotBlank(bbdd.getNombreEsquema()) ? bbdd.getNombreEsquema()
				: bbdd.getNombreEsquemaHis();
		String nombreBBDD = StringUtils.isNotBlank(bbdd.getNombreBBDD()) ? bbdd.getNombreBBDD()
				: bbdd.getNombreBBDDHis();
		
		String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
		
		// Esto causa reescritura de ficheros
		MDSQLAppHelper.dumpLinesToFile(outputReparaScript.getScriptRepara(), Paths.get(ruta.concat(outputReparaScript.getNombreScriptRepara())).toFile());

		String lanzaFile = ruta.concat(outputReparaScript.getNombreScriptLanza());
		MDSQLAppHelper.dumpStringToFile(outputReparaScript.getScriptLanza(), Paths.get(lanzaFile).toFile());
		
		executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);
		
		String logFile = ruta.concat(outputReparaScript.getNombreLogRepara());
		List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

		if (isReparacion.equals(Boolean.TRUE)) {
			// TODO como se relaciona la listaScript con la listaScript old para obtener el
			// nombre del log del script y el numero de orden
			OutputRegistraEjecucionParche outputRegistraEjecucion = ejecucionService.registraEjecucionParche(proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList, "R");
			// listOutputLogs.add(outputRegistraEjecucion);
		}
		
		if (isSameScript.equals(Boolean.TRUE)) {
			// TODO como se relaciona la listaScript con la listaScript old para obtener el
			// nombre del log del script y el numero de orden
			OutputRegistraEjecucion outputRegistraEjecucion = ejecucionService.registraEjecucion(proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList);
			// listOutputLogs.add(outputRegistraEjecucion);
		}

	}

	@Override
	public OutputReparaScript repararScript(InputReparaScript inputReparaScript) throws ServiceException {
		String runSP = createCall("p_repara_script", MDSQLConstants.CALL_27_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
			String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);
			String tableObjHis = createCallType(MDSQLConstants.T_T_OBJ_HIS);
			String recordObjHis = createCallType(MDSQLConstants.T_R_OBJ_HIS);

			String typeScriptOld = createCallType(MDSQLConstants.T_T_SCRIPT_OLD);
			String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);

			String typeError = createCallTypeError();

			logProcedure(runSP, inputReparaScript.getIdProceso(), inputReparaScript.getNumeroOrden(),
					inputReparaScript.getCodigoUsuario(), inputReparaScript.getMcaReprocesa(),
					inputReparaScript.getMcaMismoScript(), inputReparaScript.getNombreScriptNew(),
					inputReparaScript.getTxtRutaNew(), inputReparaScript.getScriptNew(),
					inputReparaScript.getTxtComentario(), inputReparaScript.getNombreScriptParche(),
					inputReparaScript.getTxtRutaParche(), inputReparaScript.getScriptParche(),
					inputReparaScript.getNombreBBDD(), inputReparaScript.getNombreEsquema(),
					inputReparaScript.getPMcaHis(), inputReparaScript.getNombreBBDDHis(),
					inputReparaScript.getNombreEsquemaHis(), inputReparaScript.getListaObjetoHis());

			Array arrayLineaScriptNew = null;
			Struct[] structLineaScriptNew = {};
			if (!Objects.isNull(inputReparaScript.getScriptNew())) {
				structLineaScriptNew = new Struct[inputReparaScript.getScriptNew().size()];

				int arrayIndexLinea = 0;
				for (TextoLinea data : inputReparaScript.getScriptNew()) {
					structLineaScriptNew[arrayIndexLinea++] = conn.createStruct(recordLinea,
							new Object[] { data.getValor() });
				}
				
				arrayLineaScriptNew = ((OracleConnection) conn).createOracleArray(tableLinea, structLineaScriptNew);
			}
			else {
				arrayLineaScriptNew = ((OracleConnection) conn).createOracleArray(tableLinea, structLineaScriptNew);
			}
			
			Array arrayLineaScriptParche = null;
			Struct[] structLineaScriptParche = {};
			if (!Objects.isNull(inputReparaScript.getScriptParche())) {
				structLineaScriptParche = new Struct[inputReparaScript.getScriptParche().size()];
				
				int arrayIndexLineaParche = 0;
				for (TextoLinea data : inputReparaScript.getScriptParche()) {
					structLineaScriptParche[arrayIndexLineaParche++] = conn.createStruct(recordLinea,
							new Object[] { data.getValor() });
				}

				arrayLineaScriptParche = ((OracleConnection) conn).createOracleArray(tableLinea,
						structLineaScriptParche);
			}
			else {
				arrayLineaScriptParche = ((OracleConnection) conn).createOracleArray(tableLinea, structLineaScriptParche);
			}
			
			Array arrayObjHis = null;
			Struct[] structObjHis = {};
			if (CollectionUtils.isNotEmpty(inputReparaScript.getListaObjetoHis())) {
				structObjHis = new Struct[inputReparaScript.getListaObjetoHis().size()];

				int arrayIndexObjHis = 0;
				for (SeleccionHistorico data : inputReparaScript.getListaObjetoHis()) {
					String mcaVigente = AppHelper.normalizeValueToCheck(data.getVigente());
					String mcaHistorico = AppHelper.normalizeValueToCheck(data.getHistorico());

					structObjHis[arrayIndexObjHis++] = conn.createStruct(recordObjHis,
							new Object[] { data.getObjeto(), data.getTipo(), mcaVigente, mcaHistorico });
				}
				
				arrayObjHis = ((OracleConnection) conn).createOracleArray(tableObjHis, structObjHis);
			}
			else {
				arrayObjHis = ((OracleConnection) conn).createOracleArray(tableObjHis, structObjHis);
			}

			callableStatement.setBigDecimal(1, inputReparaScript.getIdProceso());
			callableStatement.setBigDecimal(2, inputReparaScript.getNumeroOrden());
			callableStatement.setString(3, inputReparaScript.getCodigoUsuario());
			callableStatement.setString(4, inputReparaScript.getMcaReprocesa());
			callableStatement.setString(5, inputReparaScript.getMcaMismoScript());
			callableStatement.setString(6, inputReparaScript.getNombreScriptNew());
			callableStatement.setString(7, inputReparaScript.getTxtRutaNew());
			callableStatement.setArray(8, arrayLineaScriptNew);
			callableStatement.setString(9, inputReparaScript.getTxtComentario());
			callableStatement.setString(10, inputReparaScript.getNombreScriptParche());
			callableStatement.setString(11, inputReparaScript.getTxtRutaParche());
			callableStatement.setArray(12, arrayLineaScriptParche);
			
			// Los nuevos parámetros
			callableStatement.setString(13, inputReparaScript.getNombreBBDD());
			callableStatement.setString(14, inputReparaScript.getNombreEsquema());
			callableStatement.setString(15, inputReparaScript.getPMcaHis());
			callableStatement.setString(16, inputReparaScript.getNombreBBDDHis());
			callableStatement.setString(17, inputReparaScript.getNombreEsquemaHis());
			
			// El array de objetos de historico
			callableStatement.setArray(18, arrayObjHis);
			
			callableStatement.registerOutParameter(19, Types.VARCHAR);
			callableStatement.registerOutParameter(20, Types.ARRAY, tableLinea);
			callableStatement.registerOutParameter(21, Types.VARCHAR);
			callableStatement.registerOutParameter(22, Types.VARCHAR);
			callableStatement.registerOutParameter(23, Types.VARCHAR);
			callableStatement.registerOutParameter(24, Types.ARRAY, typeScriptOld);
			callableStatement.registerOutParameter(25, Types.ARRAY, typeScript);

			callableStatement.registerOutParameter(26, Types.INTEGER);
			callableStatement.registerOutParameter(27, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(26);

			if (result == 0) {
				throw buildException(callableStatement.getArray(27));
			}

			List<TextoLinea> scriptRepara = new ArrayList<>();
			Array arrayScriptRepara = callableStatement.getArray(20);

			if (arrayScriptRepara != null) {
				Object[] rows = (Object[]) arrayScriptRepara.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					TextoLinea textoLinea = TextoLinea.builder().valor((String) cols[0]).build();

					scriptRepara.add(textoLinea);
				}
			}

			String scriptLanza = callableStatement.getString(22);

			List<ScriptOld> listaScriptOld = new ArrayList<>();
			Array arrayScriptOld = callableStatement.getArray(24);

			if (arrayScriptOld != null) {
				Object[] rows = (Object[]) arrayScriptOld.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					ScriptOld scriptOld = ScriptOld.builder().nombreScriptOld((String) cols[0])
							.nombreScriptNew((String) cols[1]).build();

					listaScriptOld.add(scriptOld);
				}
			}

			List<Script> listaScript = new ArrayList<>();
			Array arrayScript = callableStatement.getArray(25);

			if (arrayScript != null) {
				Object[] rows = (Object[]) arrayScript.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					Script script = Script.builder().tipoScript((String) cols[0]).nombreScript((String) cols[2])
							.codigoEstadoScript((BigDecimal) cols[3]).descripcionEstadoScript((String) cols[4])
							.numeroOrden((BigDecimal) cols[5]).nombreScriptLanza((String) cols[6])
							.txtScriptLanza((String) cols[7]).nombreScriptLog((String) cols[8]).build();

					fillLineasScript(script, cols);

					listaScript.add(script);
				}
			}

			String nombreScriptRepara = callableStatement.getString(19);
			String nombreScriptLanza = callableStatement.getString(21);
			String nombreLogRepara = callableStatement.getString(23);

			OutputReparaScript outputProcesaScript = OutputReparaScript.builder().nombreScriptRepara(nombreScriptRepara)
					.scriptRepara(scriptRepara).nombreScriptLanza(nombreScriptLanza).scriptLanza(scriptLanza)
					.nombreLogRepara(nombreLogRepara).listaScriptOld(listaScriptOld).listaScript(listaScript).build();

			return outputProcesaScript;

		} catch (SQLException e) {
			LogWrapper.error(log, "[ScriptService.repararScript] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * @param script
	 * @param cols
	 * @throws SQLException
	 */
	private void fillLineasScript(Script script, Object[] cols) throws SQLException {
		try {
			Array arrayLineasScript = (Array) cols[1];
			if (!Objects.isNull(arrayLineasScript)) {
				List<TextoLinea> textoLineas = new ArrayList<>();
				Object[] subs = (Object[]) arrayLineasScript.getArray();
				for (Object sub : subs) {
					Object[] sub_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();

					String linea = (String) sub_cols[0];

					TextoLinea textoLinea = TextoLinea.builder().valor(StringUtils.EMPTY).build();
					if (StringUtils.isNotBlank(linea)) {
						textoLinea = TextoLinea.builder().valor(linea).build();
					}
					textoLineas.add(textoLinea);
				}

				script.setLineasScript(textoLineas);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LogWrapper.error(log, "[ScriptService.fillLineasScript] Error: %s", e.getMessage());
		}
	}

	@Override
	public OutputDescartarScript descartarScript(InputDescartarScript inputDescartarScript) throws ServiceException {
		String runSP = createCall("p_descartar_script", MDSQLConstants.CALL_18_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
			String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

			String typeScriptOld = createCallType(MDSQLConstants.T_T_SCRIPT_OLD);
			String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);

			String typeError = createCallTypeError();

			logProcedure(runSP, inputDescartarScript.getScript(), inputDescartarScript.getIdProceso(),
					inputDescartarScript.getCodigoUsuario(), inputDescartarScript.getNombreScript(),
					inputDescartarScript.getTipoCambio(), inputDescartarScript.getNombreScriptNew(),
					inputDescartarScript.getTxtRutaNew(), inputDescartarScript.getTxtComentario(),
					inputDescartarScript.getNombreScriptParche(), inputDescartarScript.getTxtRutaParche(),
					inputDescartarScript.getScriptParche());

			Struct[] structLineaScript = new Struct[inputDescartarScript.getScript().size()];

			int arrayIndexLinea = 0;
			for (TextoLinea data : inputDescartarScript.getScript()) {
				structLineaScript[arrayIndexLinea++] = conn.createStruct(recordLinea, new Object[] { data.getValor() });
			}
			Array arrayLineaScript = ((OracleConnection) conn).createOracleArray(tableLinea, structLineaScript);

			Array arrayLineaScriptParche = null;
			Struct[] structLineaScriptParche = null;
			if (CollectionUtils.isNotEmpty(inputDescartarScript.getScriptParche())) {
				structLineaScriptParche = new Struct[inputDescartarScript.getScriptParche().size()];

				int arrayIndexLineaParche = 0;
				for (TextoLinea data : inputDescartarScript.getScriptParche()) {
					structLineaScriptParche[arrayIndexLineaParche++] = conn.createStruct(recordLinea,
						new Object[] { data.getValor() });
				}
			}
			
			arrayLineaScriptParche = ((OracleConnection) conn).createOracleArray(tableLinea,
					structLineaScriptParche);
			
			callableStatement.setArray(1, arrayLineaScript);
			callableStatement.setBigDecimal(2, inputDescartarScript.getIdProceso());
			callableStatement.setString(3, inputDescartarScript.getCodigoUsuario());
			callableStatement.setString(4, inputDescartarScript.getNombreScript());
			callableStatement.setString(5, inputDescartarScript.getTipoCambio());
			callableStatement.setString(6, inputDescartarScript.getNombreScriptNew());
			callableStatement.setString(7, inputDescartarScript.getTxtRutaNew());
			callableStatement.setString(8, inputDescartarScript.getTxtComentario());
			callableStatement.setString(9, inputDescartarScript.getNombreScriptParche());
			callableStatement.setString(10, inputDescartarScript.getTxtRutaParche());
			callableStatement.setArray(11, arrayLineaScriptParche);
			callableStatement.registerOutParameter(12, Types.ARRAY, typeScript);
			callableStatement.registerOutParameter(13, Types.ARRAY, typeScriptOld);
			callableStatement.registerOutParameter(14, Types.ARRAY, typeScript);
			callableStatement.registerOutParameter(15, Types.NUMERIC);
			callableStatement.registerOutParameter(16, Types.VARCHAR);

			callableStatement.registerOutParameter(17, Types.INTEGER);
			callableStatement.registerOutParameter(18, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(17);

			if (result == 0) {
				throw buildException(callableStatement.getArray(18));
			}

			List<Script> listaParches = new ArrayList<>();
			Array arrayParches = callableStatement.getArray(12);

			if (arrayParches != null) {
				Object[] rows = (Object[]) arrayParches.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					Script script = Script.builder().tipoScript((String) cols[0]).nombreScript((String) cols[2])
							.codigoEstadoScript((BigDecimal) cols[3]).descripcionEstadoScript((String) cols[4])
							.numeroOrden((BigDecimal) cols[5]).nombreScriptLanza((String) cols[6])
							.txtScriptLanza((String) cols[7]).nombreScriptLog((String) cols[8]).build();

					fillLineasScript(script, cols);

					listaParches.add(script);
				}
			}

			List<ScriptOld> listaScriptOld = new ArrayList<>();
			Array arrayScriptOld = callableStatement.getArray(13);

			if (arrayScriptOld != null) {
				Object[] rows = (Object[]) arrayScriptOld.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					ScriptOld scriptOld = ScriptOld.builder().nombreScriptOld((String) cols[0])
							.nombreScriptNew((String) cols[1]).build();

					listaScriptOld.add(scriptOld);
				}
			}

			List<Script> listaScriptNew = new ArrayList<>();

			Array arrayScriptNew = callableStatement.getArray(14);

			if (arrayScriptNew != null) {
				Object[] rows = (Object[]) arrayScriptNew.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					Script script = Script.builder().tipoScript((String) cols[0]).nombreScript((String) cols[2])
							.codigoEstadoScript((BigDecimal) cols[3]).descripcionEstadoScript((String) cols[4])
							.numeroOrden((BigDecimal) cols[5]).nombreScriptLanza((String) cols[6])
							.txtScriptLanza((String) cols[7]).nombreScriptLog((String) cols[8]).build();

					fillLineasScript(script, cols);

					listaScriptNew.add(script);
				}
			}

			Integer codigoEstadoProceso = callableStatement.getInt(15);
			String descripcionEstadoProceso = callableStatement.getString(16);

			OutputDescartarScript outputDescartarScript = OutputDescartarScript.builder().listaParches(listaParches)
					.listaScriptOld(listaScriptOld).listaScriptNew(listaScriptNew)
					.codigoEstadoProceso(codigoEstadoProceso).descripcionEstadoProceso(descripcionEstadoProceso)
					.build();

			return outputDescartarScript;

		} catch (SQLException e) {
			LogWrapper.error(log, "[ScriptService.descartarScript] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	@SneakyThrows
	public List<DetObjeto> detalleObjetosScripts(BigDecimal idProceso, BigDecimal numeroOrden) {
		String runSP = createCall("p_detalle_objetos_scripts", MDSQLConstants.CALL_05_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeDetObjeto = createCallType(MDSQLConstants.T_T_DET_OBJETO);

			String typeError = createCallTypeError();

			logProcedure(runSP, idProceso, numeroOrden);

			callableStatement.setBigDecimal(1, idProceso);
			callableStatement.setBigDecimal(2, numeroOrden);
			callableStatement.registerOutParameter(3, Types.ARRAY, typeDetObjeto);

			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(4);

			if (result == 0) {
				throw buildException(callableStatement.getArray(5));
			}

			List<DetObjeto> detObjetoList = new ArrayList<>();
			Array arrayDetObjeto = callableStatement.getArray(3);

			if (arrayDetObjeto != null) {
				Object[] rows = (Object[]) arrayDetObjeto.getArray();
				for (Object row : rows) {
					Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

					DetObjeto detObjeto = DetObjeto.builder().numeroSentencia((BigDecimal) cols[0])
							.nombreObjetoPadre((String) cols[1]).tipoObjetoPadre((String) cols[2])
							.tipoAccionPadre((String) cols[3]).nombreObjeto((String) cols[4])
							.nombreObjetoDestino((String) cols[5]).tipoObjeto((String) cols[6])
							.tipoAccion((String) cols[7]).tipoDato((String) cols[8])
							.numeroLongitud((BigDecimal) cols[9]).numeroDecimal((BigDecimal) cols[10]).build();

					detObjetoList.add(detObjeto);
				}
			}
			return detObjetoList;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ScriptService.detalleObjetosScripts] Error: %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * @param nombreEsquema
	 * @param nombreBBDD
	 * @param password
	 * @param fileLocation
	 * @param logFile
	 * @param charset
	 * @throws ServiceException
	 */
	public void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation)  throws ServiceException {
		String connection = String.format(MDSQLConstants.FORMATO_CONEXION, nombreEsquema, password, nombreBBDD);
		
		ProcessBuilder processBuilder = new ProcessBuilder(MDSQLConstants.SQL_PLUS, connection,
				String.format(MDSQLConstants.FORMATO_FICHERO, fileLocation));
		LogWrapper.debug(log, processBuilder.command());
		
		Boolean invalidLogon = Boolean.FALSE;
		String lineError = StringUtils.EMPTY;

		List<TextoLinea> logLines = new ArrayList<>();
		
		processBuilder.redirectErrorStream(true);
		
		try {
			Process process = processBuilder.start();
	
			try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
	
				LogWrapper.debug(log, "[ScriptService.executeScriptFile] Inicio Ejecucion fichero: %s", fileLocation);
				while (((line = in.readLine()) != null)) {
					TextoLinea textoLinea = TextoLinea.builder().valor(line).build();
					logLines.add(textoLinea);
					LogWrapper.debug(log, line);
					
					// Error de clave incorrecta
					if (line.contains("ORA-01017")) {
						invalidLogon = Boolean.TRUE;
						lineError = line;
						break;
					}
				}
				
				if (invalidLogon) {
					process.destroyForcibly();
				}
			} catch (IOException e) {
				LogWrapper.error(log, e.getMessage());
				throw new ServiceException(e);
			}
	
			// Si se fuerza la parada del proceso, se sale con error 137, en Windows es 1
			int exitCode = process.waitFor();
			
			LogWrapper.debug(log, "[ScriptService.executeScriptFile] Fin Ejecucion exitCode: %s", exitCode);
			// Si ha dado error, escribe el fichero de log
			//MDSQLAppHelper.dumpLinesToFile(logLines, Paths.get(logFile).toFile());
			
			if (exitCode == 1 || exitCode == 137) {
				throw new ServiceException(lineError);
			}
		} catch (IOException | InterruptedException e) {
			LogWrapper.error(log, e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public OutputExcepcionScript excepcionScript(Proceso proceso, Script script, String txtMotivoExcepcion,
			String codUsr) throws ServiceException {
		String runSP = createCall("p_excepcion_script", MDSQLConstants.CALL_10_ARGS);

		try (Connection conn = dataSource.getConnection();
				CallableStatement callableStatement = conn.prepareCall(runSP)) {

			String typeError = createCallTypeError();

			logProcedure(runSP, proceso.getIdProceso(), script.getNumeroOrden(), txtMotivoExcepcion, codUsr);

			callableStatement.setBigDecimal(1, proceso.getIdProceso());
			callableStatement.setBigDecimal(2, script.getNumeroOrden());
			callableStatement.setString(3, txtMotivoExcepcion);
			callableStatement.setString(4, codUsr);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.ARRAY, typeError);

			callableStatement.execute();

			Integer result = callableStatement.getInt(9);

			if (result == 0) {
				throw buildException(callableStatement.getArray(10));
			}

			BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(5);
			String descripcionEstadoProceso = callableStatement.getString(6);
			BigDecimal codigoEstadoScript = callableStatement.getBigDecimal(7);
			String descripcionEstadoScript = callableStatement.getString(8);

			OutputExcepcionScript outputExcepcionScript = OutputExcepcionScript.builder()
					.codigoEstadoProceso(codigoEstadoProceso).descripcionEstadoProceso(descripcionEstadoProceso)
					.codigoEstadoScript(codigoEstadoScript).descripcionEstadoScript(descripcionEstadoScript).build();

			return outputExcepcionScript;
		} catch (SQLException e) {
			LogWrapper.error(log, "[ProcesoService.rechazarProcesado] Error:  %s", e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public OutputRegistraEjecucionType executeScript(BBDD bbdd, String nombreScript, List<TextoLinea> script, String nombreFicheroLog)
			throws ServiceException {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			
			String selectedRoute = session.getSelectedRoute();
			String ruta = selectedRoute.concat(File.separator);
			Proceso proceso = session.getProceso();
			proceso.setRutaTrabajo(ruta);
			String codigoUsuario = session.getCodUsr();
			String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);
			
			// Sólo hay que crear el script lanza
			String lanzaFile = ruta.concat(nombreScript);
			MDSQLAppHelper.dumpLinesToFile(script, Paths.get(lanzaFile).toFile());
	
			String password = bbddService.consultaPasswordBBDD(bbdd.getNombreBBDD(), bbdd.getNombreEsquema(), txtClaveEncriptada);
	
			// Ejecución del script
			executeLanzaFile(bbdd.getNombreEsquema(), bbdd.getNombreBBDD(), password, lanzaFile);
	
			// Obtiene el log
			String logFile = ruta.concat(nombreFicheroLog);
			List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));
	
			// Registra la ejecución
			OutputRegistraEjecucionType outputRegistraEjecucion = ejecucionService.registraEjecucionType(
					proceso.getIdProceso(), codigoUsuario, logLinesList);
			
			return outputRegistraEjecucion;
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, OutputReparaScript outputReparaScript) throws ServiceException {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String selectedRoute = session.getSelectedRoute();
			String ruta = selectedRoute.concat(File.separator);
			String nombreEsquema = StringUtils.EMPTY;
			String nombreBBDD = StringUtils.EMPTY;

			Proceso proceso = session.getProceso();
			proceso.setRutaTrabajo(ruta);
			String codigoUsuario = session.getCodUsr();
			List<OutputRegistraEjecucion> ejecuciones = new ArrayList<>();
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);

			if (CollectionUtils.isNotEmpty(outputReparaScript.getListaScript())) {
				for (Script script : outputReparaScript.getListaScript()) {
					// Esto causa reescritura de ficheros
					MDSQLAppHelper.dumpLinesToFile(script.getLineasScript(), Paths.get(ruta.concat(script.getNombreScript())).toFile());

					/**
					 * Según sea el tipo de script (SQL, PDC, SQLH, PDCH), se seleccionará la base
					 * de datos o la de histórico para su ejecución
					 */
					if ("SQL".equals(script.getTipoScript()) || "PDC".equals(script.getTipoScript())) {
						nombreEsquema = bbdd.getNombreEsquema();
						nombreBBDD = bbdd.getNombreBBDD();
					}

					if ("SQLH".equals(script.getTipoScript()) || "PDCH".equals(script.getTipoScript())) {
						nombreEsquema = bbdd.getNombreEsquemaHis();
						nombreBBDD = bbdd.getNombreBBDDHis();
					}

					// Sólo hay que crear el script lanza
					String lanzaFile = ruta.concat(script.getNombreScriptLanza());
					MDSQLAppHelper.writeToFile(script.getTxtScriptLanza().concat(System.lineSeparator()), Paths.get(lanzaFile).toFile());

					String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
					//bbdd.setPassword(password);

					// Ejecución del script
					executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

					// Obtiene el log
					String logFile = ruta.concat(script.getNombreScriptLog());
					List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

					// Registra la ejecución
					OutputRegistraEjecucion outputRegistraEjecucion = ejecucionService.registraEjecucion(
							proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList);
					outputRegistraEjecucion.setNumOrden(script.getNumeroOrden());
					outputRegistraEjecucion.setFechaEjecucion(new Date());
					ejecuciones.add(outputRegistraEjecucion);

					// Si el script ha dado error, no ejecuta el resto
					if ("Error".equals(outputRegistraEjecucion.getDescripcionEstadoScript())
							|| "Descuadrado".equals(outputRegistraEjecucion.getDescripcionEstadoScript())) {
						break;
					}

				}
			}

			return ejecuciones;
		} catch (IOException e) {
			LogWrapper.error(log, "[ScriptService.executeScripts] Error", e);
			throw new ServiceException(e);
		}
	}

}
