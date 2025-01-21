package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.ConsultaBD;
import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.entities.InputDescartarScript;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputReparaScript;
import com.mdsql.bussiness.entities.Lanza;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputDescartarScript;
import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.OutputReparaScript;
import com.mdsql.bussiness.entities.OutputScriptPeticion;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.ScriptOld;
import com.mdsql.bussiness.entities.ScriptPeticion;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.EjecucionService;
import com.mdsql.bussiness.service.ReentranteService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.DlgEjecutandoScript;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.SCRIPT_SERVICE)
@Slf4j
public class ScriptServiceImpl extends ServiceSupportScript implements ScriptService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BBDDService bbddService;

    @Autowired
    private EjecucionService ejecucionService;

    @Autowired
    private ReentranteService reentranteService;

    @Override
    public OutputProcesaScript procesarScript(InputProcesaScript inputProcesaScript) throws ServiceException {
        String runSP = createCall("p_procesa_script", 26);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);

            String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);
            String typeConsultasBD = createCallType(MDSQLConstants.T_T_CONSULTAS_BD);

            logProcedure(runSP, inputProcesaScript.getLineasScript(), inputProcesaScript.getPCodigoProyecto(),
                    inputProcesaScript.getPCodigoSubProyecto(), inputProcesaScript.getPCodigoPeticion(),
                    inputProcesaScript.getPCodigoDemanda(), inputProcesaScript.getPCodigoUsr(),
                    inputProcesaScript.getPCodigoUsrPeticion(), inputProcesaScript.getPMcaReprocesa(),
                    inputProcesaScript.getPNombreBBDD(), inputProcesaScript.getPNombreEsquema(),
                    inputProcesaScript.getPMcaHIS(), inputProcesaScript.getPNombreBBDDHIS(),
                    inputProcesaScript.getPNombreEsquemaHis(), inputProcesaScript.getPNombreFichaEntrada(),
                    inputProcesaScript.getPTxtRutaEntrada(), inputProcesaScript.getListaObjetoHis(),
                    inputProcesaScript.getPTxtDescripcion(), txtClaveEncriptada);

            Array arrayLinea = toDBListTextoLinea(conn, inputProcesaScript.getLineasScript());
            Array arrayObjHis = toDBListSeleccionHistorico(conn, inputProcesaScript.getListaObjetoHis());

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
            callableStatement.setString(18, txtClaveEncriptada);

            callableStatement.registerOutParameter(19, Types.NUMERIC);
            callableStatement.registerOutParameter(20, Types.DATE);
            callableStatement.registerOutParameter(21, Types.NUMERIC);
            callableStatement.registerOutParameter(22, Types.VARCHAR);
            callableStatement.registerOutParameter(23, Types.ARRAY, typeScript);
            callableStatement.registerOutParameter(24, Types.ARRAY, typeConsultasBD);

            OutputWarning result = executeStatement(callableStatement);

            BigDecimal idProceso = callableStatement.getBigDecimal(19);
            Date pFechaProceso = callableStatement.getDate(20);
            BigDecimal pCodigoEstadoProceso = callableStatement.getBigDecimal(21);
            String pDescripcionEstadoProceso = callableStatement.getString(22);
            List<Script> scripts = super.fromDBListScript(callableStatement.getArray(23));
            List<ConsultaBD> listaConsultaBD = fromDBListConsultaBD(callableStatement.getArray(24));

            OutputProcesaScript output = new OutputProcesaScript();
            output.setIdProceso(idProceso);
            output.setFechaProceso(pFechaProceso);
            output.setCodigoEstadoProceso(pCodigoEstadoProceso);
            output.setDescripcionEstadoProceso(pDescripcionEstadoProceso);
            output.setListaScripts(scripts);
            output.setListaConsultaBD(listaConsultaBD);
            output.setOutputWarning(result);

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.procesarScript] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     *
     * @param idProceso
     * @param numeroOrden
     * @param txtComentario
     * @param codigoUsuario
     * @return
     */
    @Override
    @SneakyThrows
    public OutputExcepcionScript excepcionScript(BigDecimal idProceso, BigDecimal numeroOrden, String txtComentario,
            String codigoUsuario) {
        String runSP = createCall("p_excepcion_script", MDSQLConstants.CALL_10_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

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

            // Hay avisos
            if (result == 2) {
                outputExcepcionScript.setWarnings(buildException(callableStatement.getArray(10)));
            }

            return outputExcepcionScript;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.excepcionScript] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputReparaScript repararScript(InputReparaScript inputReparaScript) throws ServiceException {
        String runSP = createCall("p_repara_script", MDSQLConstants.CALL_27_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
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

            Array arrayLineaScriptNew = toDBListTextoLinea(conn, inputReparaScript.getScriptNew());
            Array arrayLineaScriptParche = toDBListTextoLinea(conn, inputReparaScript.getScriptParche());
            Array arrayObjHis = toDBListSeleccionHistorico(conn, inputReparaScript.getListaObjetoHis());

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
            callableStatement.registerOutParameter(22, Types.ARRAY, tableLinea);
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

            List<TextoLinea> scriptRepara = fromDBListTextoLinea(callableStatement.getArray(20));
            List<TextoLinea> scriptLanza = fromDBListTextoLinea(callableStatement.getArray(22));
            List<ScriptOld> listaScriptOld = fromDBListScriptOld(callableStatement.getArray(24));
            List<Script> listaScript = super.fromDBListScript(callableStatement.getArray(25));

            String nombreScriptRepara = callableStatement.getString(19);
            String nombreScriptLanza = callableStatement.getString(21);
            String nombreLogRepara = callableStatement.getString(23);

            OutputReparaScript outputProcesaScript = OutputReparaScript.builder().nombreScriptRepara(nombreScriptRepara)
                    .scriptRepara(scriptRepara).nombreScriptLanza(nombreScriptLanza).scriptLanza(scriptLanza)
                    .nombreLogRepara(nombreLogRepara).listaScriptOld(listaScriptOld).listaScript(listaScript).build();

            // Hay avisos
            if (result == 2) {
                outputProcesaScript.setWarnings(buildException(callableStatement.getArray(27)));
            }

            return outputProcesaScript;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.repararScript] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputDescartarScript descartarScript(InputDescartarScript inputDescartarScript) throws ServiceException {
        String runSP = createCall("p_descartar_script", MDSQLConstants.CALL_18_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeScriptOld = createCallType(MDSQLConstants.T_T_SCRIPT_OLD);
            String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);

            String typeError = createCallTypeError();

            logProcedure(runSP, inputDescartarScript.getScript(), inputDescartarScript.getIdProceso(),
                    inputDescartarScript.getCodigoUsuario(), inputDescartarScript.getNombreScript(),
                    inputDescartarScript.getTipoCambio(), inputDescartarScript.getNombreScriptNew(),
                    inputDescartarScript.getTxtRutaNew(), inputDescartarScript.getTxtComentario(),
                    inputDescartarScript.getNombreScriptParche(), inputDescartarScript.getTxtRutaParche(),
                    inputDescartarScript.getScriptParche());

            Array arrayLineaScript = toDBListTextoLinea(conn, inputDescartarScript.getScript());
            Array arrayLineaScriptParche = toDBListTextoLinea(conn, inputDescartarScript.getScriptParche());

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
            List<Script> listaParches = fromDBListScript(callableStatement.getArray(12));
            List<ScriptOld> listaScriptOld = fromDBListScriptOld(callableStatement.getArray(13));
            List<Script> listaScriptNew = fromDBListScript(callableStatement.getArray(14));

            Integer codigoEstadoProceso = callableStatement.getInt(15);
            String descripcionEstadoProceso = callableStatement.getString(16);

            OutputDescartarScript outputDescartarScript = OutputDescartarScript.builder().listaParches(listaParches)
                    .listaScriptOld(listaScriptOld).listaScriptNew(listaScriptNew)
                    .codigoEstadoProceso(codigoEstadoProceso).descripcionEstadoProceso(descripcionEstadoProceso)
                    .build();

            // Hay avisos
            if (result == 2) {
                outputDescartarScript.setWarnings(buildException(callableStatement.getArray(18)));
            }

            return outputDescartarScript;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.descartarScript] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<DetObjeto> detalleObjetosScripts(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException {
        String runSP = createCall("p_detalle_objetos_scripts", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeDetObjeto = createCallType(MDSQLConstants.T_T_DET_OBJETO);

            logProcedure(runSP, idProceso, numeroOrden);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeDetObjeto);

            OutputWarning result = executeStatement(callableStatement);

            List<DetObjeto> lista = new ArrayList<>();
            Array arrayDetObjeto = callableStatement.getArray(3);

            if (arrayDetObjeto != null) {
                Object[] rows = (Object[]) arrayDetObjeto.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    DetObjeto detObjeto = DetObjeto.builder()
                            .numeroSentencia((BigDecimal) cols[0])
                            .nombreObjetoPadre((String) cols[1])
                            .tipoObjetoPadre((String) cols[2])
                            .tipoAccionPadre((String) cols[3])
                            .nombreObjeto((String) cols[4])
                            .nombreObjetoDestino((String) cols[5])
                            .tipoObjeto((String) cols[6])
                            .tipoAccion((String) cols[7])
                            .tipoDato((String) cols[8])
                            .numeroLongitud((BigDecimal) cols[9])
                            .numeroDecimal((BigDecimal) cols[10])
                            .detalle((String) cols[11])
                            .build();
                    lista.add(detObjeto);
                }
            }
            OutputConsulta<DetObjeto> output = new OutputConsulta();
            output.setLista(lista);
            output.setOutputWarning(result);
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.detalleObjetosScripts] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputExcepcionScript excepcionScript(Proceso proceso, Script script, String txtMotivoExcepcion,
            String codUsr) throws ServiceException {
        String runSP = createCall("p_excepcion_script", MDSQLConstants.CALL_10_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

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

            // Hay avisos
            if (result == 2) {
                outputExcepcionScript.setWarnings(buildException(callableStatement.getArray(10)));
            }

            return outputExcepcionScript;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.excepcionScript] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    private String concatRuta(String nombre, String ruta) {
        File file = new File(nombre);
        // Si el fichero no incluye la ruta, añadimos la ruta por defecto
        return (file.getParent() == null && ruta != null) ? ruta.concat(nombre) : nombre;
    }

    // Ejecucion de scripts
    @Override
    public List<TextoLinea> executeScript(BBDD bbdd, Script script, String ruta) throws ServiceException {
        try {
            String nombreEsquema;
            String nombreBBDD;
            String nombreScript = concatRuta(script.getNombreScript(), ruta);
            String lanzaFile;

            // Esto causa reescritura de ficheros en el formato indicado en el atributo
            MDSQLAppHelper.dumpLinesToFile(script.getLineasScript(), nombreScript, script.getCharset_script());

            /**
             * Según sea el tipo de script, si es (SQLH, PDCH), se seleccionará
             * la base de datos o la de histórico para su ejecución
             */
            if (isTipoScriptHistorico(script.getTipoScript())) {
                nombreEsquema = bbdd.getNombreEsquemaHis();
                nombreBBDD = bbdd.getNombreBBDDHis();
            } else {
                nombreEsquema = bbdd.getNombreEsquema();
                nombreBBDD = bbdd.getNombreBBDD();
            }

            // Sólo hay que crear el script lanza si existe el nombre
            if (script.getNombreScriptLanza() != null) {
                lanzaFile = concatRuta(script.getNombreScriptLanza(), ruta);
                MDSQLAppHelper.dumpLinesToFile(script.getLineasScriptLanza(), lanzaFile);
            } else {
                lanzaFile = nombreScript;
            }

            String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema);
            //bbdd.setPassword(password);

            // Ejecución del script
            executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

            // Obtiene el log
            String logFile = concatRuta(script.getNombreScriptLog(), ruta);
            return MDSQLAppHelper.writeFileToLines(new File(logFile));

        } catch (IOException e) {
            LogWrapper.error(log, "[ScriptService.executeScript] Error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts, String ruta) throws ServiceException {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
        if (ruta == null) {
            ruta = session.getSelectedRoute();
        }
        if (!ruta.endsWith(File.separator)) {
            ruta = ruta.concat(File.separator);
        }
        Proceso proceso = session.getProceso();
        proceso.setRutaTrabajo(ruta);
        String codigoUsuario = session.getCodUsr();
        List<OutputRegistraEjecucion> ejecuciones = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(scripts)) {
            for (Script script : scripts) {

                List<TextoLinea> logLinesList = executeScript(bbdd, script, ruta);

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
    }

    @Override
    public List<OutputRegistraEjecucion> executeScriptsReentrante(List<Script> scripts, List<Lanza> lanzas, String ruta) throws ServiceException {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            if (ruta == null) {
                ruta = session.getSelectedRoute();
            }
            if (!ruta.endsWith(File.separator)) {
                ruta = ruta.concat(File.separator);
            }
            Proceso proceso = session.getProceso();
            proceso.setRutaTrabajo(ruta);
            String codigoUsuario = session.getCodUsr();

            // Creamos los ficheros en la ruta antes de ejecutar los lanzas
            if (CollectionUtils.isNotEmpty(scripts)) {
                for (Script script : scripts) {
                    String nombreScript = ruta.concat(script.getNombreScript());
                    // Esto causa reescritura de ficheros en el formato indicado en el atributo
                    MDSQLAppHelper.dumpLinesToFile(script.getLineasScript(), nombreScript, script.getCharset_script());
                }
            }
            // Ejecutamos los lanzas
            List<OutputRegistraEjecucion> ejecuciones = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(lanzas)) {
                for (Lanza lanza : lanzas) {
                    BBDD bbdd = BBDD.builder()
                            .nombreBBDD(lanza.getNomBBDD())
                            .nombreEsquema(lanza.getNomEsquema())
                            .build();

                    String lanzaFileName = ruta.concat(lanza.getNomScript());

                    //Creamos el fichero lanza
                    MDSQLAppHelper.dumpLinesToFile(lanza.getLineasScript(), lanzaFileName);
                    executeLanzaFile(lanza.getNomEsquema(), lanza.getNomBBDD(), lanza.getPassword(), lanzaFileName, lanza.getSettings(), null);
                    //Creamos el fichero log
                    String logFileName = concatRuta(lanza.getNomFicheroLog(), ruta);
                    List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFileName));

                    // Registra la ejecución
                    OutputRegistraEjecucion output
                            = reentranteService.registraEjecucionReentrante(
                                    proceso.getIdProceso(),
                                    lanza.getNumOrden(),
                                    bbdd.getNombreBBDD(),
                                    bbdd.getNombreEsquema(),
                                    codigoUsuario,
                                    logLinesList);

                    output.setNumOrden(lanza.getNumOrden());
                    output.setFechaEjecucion(new Date());

                    //Actualizar el estado de los registros asociados al lanza
                    for (Script script : scripts) {
                        if (lanza.getNumOrden().equals(script.getNumeroOrden())) {
                            script.setCodigoEstadoScript(output.getCodigoEstadoProceso());
                            script.setDescripcionEstadoScript(output.getDescripcionEstadoScript());
                        }
                    }

                    // Añadir la ejecución a la lista resultante
                    ejecuciones.add(output);

                    // Si el script ha dado error, no ejecuta el resto
                    if ("Error".equals(output.getDescripcionEstadoScript())
                            || "Descuadrado".equals(output.getDescripcionEstadoScript())) {
                        break;
                    }

                }
            }
            return ejecuciones;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputRegistraEjecucionType executeScript(BBDD bbdd, String nombreScript, List<TextoLinea> lineas, String nombreFicheroLog)
            throws ServiceException {
        Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);

        String selectedRoute = session.getSelectedRoute();
        String ruta = selectedRoute.concat(File.separator);
        Proceso proceso = session.getProceso();
        proceso.setRutaTrabajo(ruta);
        String codigoUsuario = session.getCodUsr();

        Script scriptLanza = Script.builder().
                nombreScript(nombreScript).
                nombreScriptLog(nombreFicheroLog).
                lineasScript(lineas).
                tipoScript("SQL").
                build();

        List<TextoLinea> logLinesList = executeScript(bbdd, scriptLanza, ruta);

        // Registra la ejecución
        OutputRegistraEjecucionType outputRegistraEjecucion = ejecucionService.registraEjecucionType(
                proceso.getIdProceso(), codigoUsuario, logLinesList);

        return outputRegistraEjecucion;
    }

    @Override
    public OutputRegistraEjecucion executeScriptParche(BBDD bbdd, Script script) throws ServiceException {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String selectedRoute = session.getSelectedRoute();
            String ruta = selectedRoute.concat(File.separator);
            String nombreEsquema;
            String nombreBBDD;

            Proceso proceso = session.getProceso();
            proceso.setRutaTrabajo(ruta);
            String codigoUsuario = session.getCodUsr();

            // Según sea el tipo de script, se seleccionará la base de datos o la de histórico para su ejecución
            if (isTipoScriptHistorico(script.getTipoScript())) {
                nombreEsquema = bbdd.getNombreEsquemaHis();
                nombreBBDD = bbdd.getNombreBBDDHis();
            } else {
                nombreEsquema = bbdd.getNombreEsquema();
                nombreBBDD = bbdd.getNombreBBDD();
            }

            // Sólo hay que crear el script lanza
            String lanzaFile = concatRuta(script.getNombreScriptLanza(), ruta);

            String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema);
            //bbdd.setPassword(password);

            // Ejecución del script
            executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

            // Obtiene el log
            String logFile = concatRuta(script.getNombreScriptLog(), ruta);
            List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

            // Registra la ejecución
            return ejecucionService.registraEjecucionParche(
                    proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList, StringUtils.EMPTY);

        } catch (IOException e) {
            LogWrapper.error(log, "[ScriptService.executeScriptParche] Error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OutputRegistraEjecucion> ejecutarRepararScript(Script script, Boolean isReparacion, Boolean isSameScript,
            OutputReparaScript outputReparaScript) throws ServiceException {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            Proceso proceso = session.getProceso();
            String codigoUsuario = session.getCodUsr();
            String ruta = session.getProceso().getRutaTrabajo();
            BBDD bbdd = session.getProceso().getBbdd();

            String nombreEsquema = StringUtils.isNotBlank(bbdd.getNombreEsquema()) ? bbdd.getNombreEsquema()
                    : bbdd.getNombreEsquemaHis();
            String nombreBBDD = StringUtils.isNotBlank(bbdd.getNombreBBDD()) ? bbdd.getNombreBBDD()
                    : bbdd.getNombreBBDDHis();

            String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema);

            // Esto causa reescritura de ficheros
            MDSQLAppHelper.dumpLinesToFile(outputReparaScript.getScriptRepara(), outputReparaScript.getNombreScriptRepara());

            String lanzaFile = concatRuta(outputReparaScript.getNombreScriptLanza(), ruta);
            MDSQLAppHelper.dumpLinesToFile(outputReparaScript.getScriptLanza(), lanzaFile);

            executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

            String logFile = concatRuta(outputReparaScript.getNombreLogRepara(), ruta);
            List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

            List<OutputRegistraEjecucion> lista = new ArrayList();
            if (isReparacion.equals(Boolean.TRUE)) {
                lista.add(ejecucionService.registraEjecucionParche(proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList, "R"));
            }
            if (isSameScript.equals(Boolean.TRUE)) {
                lista.add(ejecucionService.registraEjecucion(proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList));
            }
            return lista;
        } catch (IOException e) {
            LogWrapper.error(log, e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * @param nombreEsquema
     * @param nombreBBDD
     * @param password
     * @param fileLocation
     * @throws ServiceException
     */
    @Override
    public void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation) throws ServiceException {
        executeLanzaFile(nombreEsquema, nombreBBDD, password, fileLocation, null, null);
    }

    /**
     * @param nombreEsquema
     * @param nombreBBDD
     * @param password
     * @param fileLocation
     * @param mensaje
     * @throws ServiceException
     */
    @Override
    public void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation, String mensaje) throws ServiceException {
        executeLanzaFile(nombreEsquema, nombreBBDD, password, fileLocation, null, mensaje);
    }

    /**
     * Ejecuta un script mostrando una pantalla mientras se procesa
     *
     * @param nombreEsquema
     * @param nombreBBDD
     * @param password
     * @param fileLocation
     * @param settings
     * @param mensaje
     * @throws ServiceException
     */
    @Override
    public void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation, List<TextoLinea> settings, String mensaje) throws ServiceException {
        DlgEjecutandoScript dlg = new DlgEjecutandoScript(nombreEsquema, nombreBBDD, password, fileLocation, settings, mensaje);
        dlg.setLocationRelativeTo(null); // Centrar en la pantalla
        dlg.setVisible(true);
        String errorMessage = dlg.getErrorMessage();
        if (errorMessage != null) {
            throw new ServiceException(errorMessage);
        }
    }

    public void executeLanzaFile_old(String nombreEsquema, String nombreBBDD, String password, String fileLocation, List<TextoLinea> settings) throws ServiceException {

        String batchFile = crearFicheroBat(nombreEsquema, nombreBBDD, password, fileLocation, settings);

        File fileSql = new File(fileLocation);
        if (!fileSql.exists()) {
            throw new ServiceException("No existe el fichero " + fileSql);
        }

        ProcessBuilder processBuilder = new ProcessBuilder(batchFile);
        LogWrapper.debug(log, processBuilder.command());

        Boolean invalidLogon = Boolean.FALSE;
        String lineError = StringUtils.EMPTY;

        //List<TextoLinea> logLines;
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;

                LogWrapper.debug(log, "[ScriptService.executeScriptFile] Inicio Ejecucion fichero: %s", fileLocation);
                while (((line = in.readLine()) != null)) {
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

            if (exitCode == 1 || exitCode == 137) {
                throw new ServiceException(lineError);
            }
            //borraBat(batchFile);
        } catch (IOException | InterruptedException e) {
            LogWrapper.error(log, e.getMessage());
            //En caso de error borrar el fichero bat
            //borraBat(batchFile);
            throw new ServiceException(e);
        } catch (ServiceException e) {
            //borraBat(batchFile);
            throw e;
        }
    }

    /**
     * Genera un fichero bat con el mismo nombre y en la misma carpeta, llama a
     * SQLPlus con los parámetros indicados y establece los settings
     *
     * @param ficheroSql
     * @return
     */
    private String crearFicheroBat(String nombreEsquema, String nombreBBDD, String password, String fileLocation, List<TextoLinea> settings) throws ServiceException {
        try {
            //Construimos el nombre del fichero bat
            File fileSql = new File(fileLocation);
            String nombreBat = fileLocation.substring(0, fileLocation.lastIndexOf(".")) + ".bat";
            List<TextoLinea> lineasBat = new ArrayList();

            // Linea para colocarme en el directorio del script
            //TextoLinea setDirectory = TextoLinea.builder().valor("CD " + fileSql.getParent()).build();
            //lineasBat.add(setDirectory);
            //Si hay Settings añadimos al fichero bat uno por línea
            if (settings != null && !settings.isEmpty()) {
                lineasBat.addAll(settings);
            }
            //Llamado a SQLPlus con sus parámetros indicados
            String connection = String.format(MDSQLConstants.FORMATO_CONEXION, nombreEsquema, password, nombreBBDD);

            //String script = String.format(MDSQLConstants.FORMATO_FICHERO, fileSql.getName());
            String script = String.format(MDSQLConstants.FORMATO_FICHERO, fileSql.getAbsolutePath());

            TextoLinea sqlPlus = TextoLinea.builder().valor(MDSQLConstants.SQL_PLUS + " " + connection + " " + script).build();
            lineasBat.add(sqlPlus);

            //Generar el fichero físico
            MDSQLAppHelper.dumpLinesToFile(lineasBat, nombreBat);

            return nombreBat;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    private List<ScriptOld> fromDBListScriptOld(Array arrayScriptOld) throws SQLException {
        List<ScriptOld> listaScriptOld = new ArrayList<>();

        if (arrayScriptOld != null) {
            Object[] rows = (Object[]) arrayScriptOld.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                ScriptOld scriptOld = ScriptOld.builder().nombreScriptOld((String) cols[0])
                        .nombreScriptNew((String) cols[1]).build();

                listaScriptOld.add(scriptOld);
            }
        }
        return listaScriptOld;
    }

    private Array toDBListSeleccionHistorico(Connection conn, List<SeleccionHistorico> listaObjetoHis) throws SQLException {
        String tableObjHis = createCallType(MDSQLConstants.T_T_OBJ_HIS);
        String recordObjHis = createCallType(MDSQLConstants.T_R_OBJ_HIS);
        Struct[] structObjHis = null;
        if (CollectionUtils.isNotEmpty(listaObjetoHis)) {
            structObjHis = new Struct[listaObjetoHis.size()];

            int arrayIndexObjHis = 0;
            for (SeleccionHistorico data : listaObjetoHis) {
                String mcaVigente = AppHelper.normalizeValueToCheck(data.getVigente());
                String mcaHistorico = AppHelper.normalizeValueToCheck(data.getHistorico());

                structObjHis[arrayIndexObjHis++] = conn.createStruct(recordObjHis,
                        new Object[]{data.getObjeto(), data.getTipo(), mcaVigente, mcaHistorico});
            }
        }

        return ((OracleConnection) conn).createOracleArray(tableObjHis, structObjHis);
    }

    @Override
    public OutputWarning consultaBBDDModelo(BigDecimal idProceso, List<TextoLinea> lineasLog, String codUsr)
            throws ServiceException {
        String runSP = createCall("p_consulta_bbdd_modelo", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, lineasLog, codUsr);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setArray(2, toDBListTextoLinea(conn, lineasLog));
            callableStatement.setString(3, codUsr);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(callableStatement.getInt(4), callableStatement.getArray(5));

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.consultaBBDDModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);

        }
    }

    /**
     * Dado el tipo de script returna TRUE si su extensión es de historico,
     * FALSE en otro caso
     *
     * @param tipoScript
     * @return
     */
    private static boolean isTipoScriptHistorico(String tipoScript) {
        return Arrays.asList(MDSQLConstants.TIPOS_SCRIPT_HISTORICO).contains(tipoScript);
    }

    @Override
    public OutputScriptPeticion consultaScriptsPeticion(String codPeticion, BigDecimal idProceso, BigDecimal codEstado) throws ServiceException {
        String runSP = createCall("p_con_scripts_peti", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeScriptPeti = createCallType(MDSQLConstants.T_T_SCRIPT_PETI);

            logProcedure(runSP, codPeticion, idProceso, codEstado);

            callableStatement.setString(1, codPeticion);
            callableStatement.setBigDecimal(2, idProceso);
            callableStatement.setBigDecimal(3, codEstado);

            callableStatement.registerOutParameter(4, Types.VARCHAR); //p_txt_ruta_entrega
            callableStatement.registerOutParameter(5, Types.ARRAY, typeScriptPeti); //p_scripts_peti

            OutputWarning result = executeStatement(callableStatement);

            OutputScriptPeticion output = new OutputScriptPeticion();
            output.setWarnings(result.getWarnings());
            output.setRuta(callableStatement.getString(4)); //p_txt_ruta_entrega
            output.setLista(fromDBListScriptPeticion(callableStatement.getArray(5))); //p_scripts_peti
            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.consultaScriptsPeticion] Error:  %s", e.getMessage());
            throw new ServiceException(e);

        }
    }

    /**
     * Convierte la información de la estructra t_t_scripts_peti recuperada de
     * BD en una Lista de ScriptPeticion
     *
     * @param array
     * @return
     * @throws SQLException
     */
    private List<ScriptPeticion> fromDBListScriptPeticion(Array array) throws SQLException {
        List<ScriptPeticion> list = new ArrayList<>();

        if (array == null) {
            return list;
        }
        Object[] rows = (Object[]) array.getArray();
        for (Object row : rows) {
            list.add(fromDBScriptPeticion(row));
        }
        return list;
    }

    /**
     * Convierte la información de la estructra t_r_scripts_peti recuperada de
     * BD en ScriptPeticion
     *
     * @param array
     * @return
     * @throws SQLException
     */
    public ScriptPeticion fromDBScriptPeticion(Object array) throws SQLException {
        Object[] cols = ((oracle.jdbc.OracleStruct) array).getAttributes();

        ScriptPeticion item = ScriptPeticion.builder()
                .nombreTabla((String) cols[0])
                .idProceso((BigDecimal) cols[1])
                .numeroOrden((BigDecimal) cols[2])
                .nombreScript((String) cols[3])
                .codigoEstadoScript((BigDecimal) cols[4])
                .descripcionEstadoScript((String) cols[5])
                .fecha((Date) cols[6])
                .codUsr((String) cols[7])
                .mcCambioNombre((String) cols[8]).build();

        return item;
    }

    @Override
    public OutputWarning cambiaNombreScript(BigDecimal idProceso, BigDecimal numOrden, String nomScriptOld, String nomScriptNew, String comentario) throws ServiceException {
        String runSP = createCall("p_cambia_nombre_script", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, numOrden, nomScriptOld, nomScriptNew, comentario);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numOrden);
            callableStatement.setString(3, nomScriptOld);
            callableStatement.setString(4, nomScriptNew);
            callableStatement.setString(5, comentario);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ScriptService.cambiaNombreScript] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

}
