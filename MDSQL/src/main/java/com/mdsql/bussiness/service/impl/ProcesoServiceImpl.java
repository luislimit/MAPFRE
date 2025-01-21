package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.FicheroAccion;
import com.mdsql.bussiness.entities.FicheroZip;
import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputConsultaProcesado;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;
import java.io.IOException;
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
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.PROCESO_SERVICE)
@Slf4j
public class ProcesoServiceImpl extends ServiceSupport implements ProcesoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<Proceso> seleccionarProcesados(InputSeleccionarProcesados inputSeleccionarProcesados)
            throws ServiceException {
        String runSP = createCall("p_sel_procesados", 22);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeProceso = createCallType(MDSQLConstants.T_T_PROCESO);

            logProcedure(runSP,
                    inputSeleccionarProcesados.getPCodigoPeticion(),
                    inputSeleccionarProcesados.getIdProceso(),
                    inputSeleccionarProcesados.getPCodigoUsuarioPeticion(),
                    inputSeleccionarProcesados.getPFechaInicio(),
                    inputSeleccionarProcesados.getPFechaFin(),
                    inputSeleccionarProcesados.getPCodigoUsuario(),
                    inputSeleccionarProcesados.getPCodigoproyecto(),
                    inputSeleccionarProcesados.getPCodigoSubProyecto(),
                    inputSeleccionarProcesados.getMcaGenerado(),
                    inputSeleccionarProcesados.getMcaEjecutado(),
                    inputSeleccionarProcesados.getMcaCerrado(),
                    inputSeleccionarProcesados.getMcaEnEjecucion(),
                    inputSeleccionarProcesados.getMcaRechazado(),
                    inputSeleccionarProcesados.getMcaIncidencia(),
                    inputSeleccionarProcesados.getMcaError(),
                    inputSeleccionarProcesados.getMcaEntregado(),
                    inputSeleccionarProcesados.getMcaExcluido(),
                    inputSeleccionarProcesados.getMcaConErrores(),
                    inputSeleccionarProcesados.getPUltimas());

            callableStatement.setString(1, inputSeleccionarProcesados.getPCodigoPeticion());
            callableStatement.setBigDecimal(2, inputSeleccionarProcesados.getIdProceso());

            callableStatement.setString(3, inputSeleccionarProcesados.getPCodigoUsuarioPeticion());
            setDate(callableStatement, 4, inputSeleccionarProcesados.getPFechaInicio());
            setDate(callableStatement, 5, inputSeleccionarProcesados.getPFechaFin());
            callableStatement.setString(6, inputSeleccionarProcesados.getPCodigoUsuario());
            callableStatement.setString(7, inputSeleccionarProcesados.getPCodigoproyecto());
            callableStatement.setString(8, inputSeleccionarProcesados.getPCodigoSubProyecto());

            callableStatement.setString(9, inputSeleccionarProcesados.getMcaGenerado());
            callableStatement.setString(10, inputSeleccionarProcesados.getMcaEjecutado());
            callableStatement.setString(11, inputSeleccionarProcesados.getMcaCerrado());
            callableStatement.setString(12, inputSeleccionarProcesados.getMcaEnEjecucion());
            callableStatement.setString(13, inputSeleccionarProcesados.getMcaRechazado());
            callableStatement.setString(14, inputSeleccionarProcesados.getMcaIncidencia());
            callableStatement.setString(15, inputSeleccionarProcesados.getMcaError());
            callableStatement.setString(16, inputSeleccionarProcesados.getMcaEntregado());
            callableStatement.setString(17, inputSeleccionarProcesados.getMcaExcluido());
            callableStatement.setString(18, inputSeleccionarProcesados.getMcaConErrores());
            callableStatement.setBigDecimal(19, inputSeleccionarProcesados.getPUltimas());
            callableStatement.registerOutParameter(20, Types.ARRAY, typeProceso);

            OutputWarning outputWarning = executeStatement(callableStatement);

            List<Proceso> procesos = new ArrayList<>();
            Array arrayProcesos = callableStatement.getArray(20);

            if (arrayProcesos != null) {
                Object[] rows = (Object[]) arrayProcesos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    BigDecimal idProceso = (BigDecimal) cols[0];

                    // Mapear el tipo de proceso
                    String tipo = (String) cols[17];
                    Procesado procesado;
                    switch (tipo) {
                        case "S":
                            procesado = Procesado.SCRIPT;
                            break;
                        case "O":
                            procesado = Procesado.TYPE;
                            break;
                        case "R":
                            procesado = Procesado.REENTRANTE;
                            break;
                        default:
                            throw new ServiceException("Tipo de procesado desconocido '" + tipo + "' idProceso:" + idProceso);
                    }

                    // Construir el objeto del proceso
                    Proceso proceso = Proceso.builder()
                            .idProceso(idProceso)
                            .codigoPeticion((String) cols[1])
                            .codigoUsrPeticion((String) cols[2])
                            .fechaInicio((Date) cols[3])
                            .codigoUsr((String) cols[4])
                            .codigoEstadoProceso((BigDecimal) cols[5])
                            .descripcionEstadoProceso((String) cols[6])
                            .mcaInicial((String) cols[7])
                            .txtDescripcion((String) cols[8])
                            .txtObservacionEntrega((String) cols[9])
                            .mcaErrores((String) cols[10])
                            .codProyecto((String) cols[11])
                            .codSubproyecto((String) cols[12])
                            .mcaRechazar((String) cols[13])
                            .mcaExcluir((String) cols[14])
                            .mcaIncidencia((String) cols[15])
                            .mcaEntregar((String) cols[16])
                            .tipo(procesado)
                            .build();

                    procesos.add(proceso);
                }
            }
            OutputConsulta<Proceso> output = new OutputConsulta();
            output.setOutputWarning(outputWarning);
            output.setLista(procesos);

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.seleccionarProcesados] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<SeleccionHistorico> seleccionarHistorico(String codProyecto, List<TextoLinea> lineas)
            throws ServiceException {
        String runSP = createCall("p_sel_historico", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHistorico = createCallType(MDSQLConstants.T_T_OBJ_HIS);
            String trLinea = createCallType(MDSQLConstants.T_R_LINEA);
            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);

            if (CollectionUtils.isEmpty(lineas)) {
                throw new ServiceException("Falta el script a procesar", null);
            }

            // El script se manda manda línea a línea
            Struct[] struct = new Struct[lineas.size()];

            int arrayIndex = 0;
            for (TextoLinea linea : lineas) {
                struct[arrayIndex++] = conn.createStruct(trLinea, new Object[]{linea.getValor()});
            }

            Array arrayLineas = ((OracleConnection) conn).createOracleArray(tableLinea, struct);

            logProcedure(runSP, lineas, codProyecto);

            callableStatement.setArray(1, arrayLineas);
            callableStatement.setString(2, codProyecto);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeHistorico);

            callableStatement.execute();

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<SeleccionHistorico> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<SeleccionHistorico> seleccion = new ArrayList<>();
            Array arraySeleccion = callableStatement.getArray(3);

            if (arraySeleccion != null) {
                Object[] rows = (Object[]) arraySeleccion.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    SeleccionHistorico seleccionHistorico = new SeleccionHistorico();

                    seleccionHistorico.setObjeto((String) cols[0]);
                    seleccionHistorico.setTipo((String) cols[1]);

                    Boolean historico = AppHelper.normalizeCheckValue((String) cols[3]);
                    seleccionHistorico.setHistorico(historico);

                    seleccionHistorico.setConfigurado(historico);
                    seleccionHistorico.setEditable(!historico);

                    // Vigente siempre a TRUE
                    seleccionHistorico.setVigente(Boolean.TRUE);

                    seleccion.add(seleccionHistorico);
                }
            }
            output.setLista(seleccion);

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.seleccionarHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning altaHistorico(List<SeleccionHistorico> listaObjetos, String codigoProyecto, String codigoPeticion,
            String codigoUsuario) throws ServiceException {
        String runSP = createCall("p_alta_historico", 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableObjetos = createCallType(MDSQLConstants.T_T_OBJETOS);
            String recordObjetos = createCallType(MDSQLConstants.T_R_OBJETOS);

            logProcedure(runSP, listaObjetos, codigoProyecto, codigoPeticion, codigoUsuario);

            Struct[] structObjetos = new Struct[listaObjetos.size()];

            int arrayIndexLinea = 0;
            for (SeleccionHistorico data : listaObjetos) {
                structObjetos[arrayIndexLinea++] = conn.createStruct(recordObjetos,
                        new Object[]{data.getTipo(), data.getObjeto()});
            }

            LogWrapper.debug(log, "Añadir a histórico: ");
            logArrayStruct(structObjetos);

            Array arrayObjetos = ((OracleConnection) conn).createOracleArray(tableObjetos, structObjetos);

            callableStatement.setArray(1, arrayObjetos);
            callableStatement.setString(2, codigoProyecto);
            callableStatement.setString(3, codigoPeticion);
            callableStatement.setString(4, codigoUsuario);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.altaHistorico] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsultaProcesado consultaProcesado(BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_procesado", 22);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeScriptEjecutado = createCallType(MDSQLConstants.T_T_SCRIPT_EJEC);

            logProcedure(runSP, idProceso);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.VARCHAR);
            callableStatement.registerOutParameter(9, Types.VARCHAR);
            callableStatement.registerOutParameter(10, Types.NUMERIC);
            callableStatement.registerOutParameter(11, Types.VARCHAR);
            callableStatement.registerOutParameter(12, Types.VARCHAR);
            callableStatement.registerOutParameter(13, Types.DATE);
            callableStatement.registerOutParameter(14, Types.VARCHAR);
            callableStatement.registerOutParameter(15, Types.VARCHAR);
            callableStatement.registerOutParameter(16, Types.VARCHAR);
            callableStatement.registerOutParameter(17, Types.ARRAY, typeScriptEjecutado);
            callableStatement.registerOutParameter(18, Types.VARCHAR);
            callableStatement.registerOutParameter(19, Types.VARCHAR);
            callableStatement.registerOutParameter(20, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsultaProcesado outputConsultaProcesado = new OutputConsultaProcesado();
            outputConsultaProcesado.setResult(result.getResult());
            outputConsultaProcesado.setServiceException(result.getWarnings());

            List<ScriptEjecutado> scriptEjecutados = new ArrayList<>();
            Array arrayScriptsEjecutados = callableStatement.getArray(17);

            if (arrayScriptsEjecutados != null) {
                Object[] rows = (Object[]) arrayScriptsEjecutados.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    ScriptEjecutado scriptEjecutado = ScriptEjecutado.builder()
                            .numeroOrden((BigDecimal) cols[0])
                            .codigoEstadoScript((BigDecimal) cols[1])
                            .descripcionEstadoScript((String) cols[2])
                            .fechaEjecucion((Date) cols[3])
                            .txtCuadreOperacion((String) cols[4])
                            .txtCueadreObj((String) cols[5])
                            .nombreScript((String) cols[6])
                            .mcaErrores((String) cols[7])
                            .build();

                    scriptEjecutados.add(scriptEjecutado);
                }
            }

            outputConsultaProcesado.setNombreModelo(callableStatement.getString(2));
            outputConsultaProcesado.setCodigoUsrPeticion(callableStatement.getString(3));
            outputConsultaProcesado.setNombreBBDDHistorico(callableStatement.getString(4));
            outputConsultaProcesado.setDescripcionSubProyecto(callableStatement.getString(5));
            outputConsultaProcesado.setNombreEsquema(callableStatement.getString(6));
            outputConsultaProcesado.setNombreesquemaHistorico(callableStatement.getString(7));
            outputConsultaProcesado.setCodigoPeticion(callableStatement.getString(8));
            outputConsultaProcesado.setNombreBBDD(callableStatement.getString(9));
            outputConsultaProcesado.setCodigoEstadoProceso(callableStatement.getBigDecimal(10));
            outputConsultaProcesado.setDescripcionEstadoProceso(callableStatement.getString(11));
            outputConsultaProcesado.setCodigoUsuario(callableStatement.getString(12));
            outputConsultaProcesado.setFechaProceso(callableStatement.getDate(13));
            outputConsultaProcesado.setTxtComentario(callableStatement.getString(14));
            outputConsultaProcesado.setMcaInicial(callableStatement.getString(15));
            outputConsultaProcesado.setTxtRutaEntrada(callableStatement.getString(16));
            outputConsultaProcesado.setListaScriptsEjecutados(scriptEjecutados);

            outputConsultaProcesado.setVersionErwin(callableStatement.getString(18));
            outputConsultaProcesado.setVersionado(callableStatement.getString(19));
            outputConsultaProcesado.setDescripcion(callableStatement.getString(20));

            return outputConsultaProcesado;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.consultaProcesado] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Rechazar procesados
     *
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @return
     * @throws ServiceException
     */
    @Override
    public OutputWarning rechazarProcesado(
            BigDecimal idProceso,
            String txtComentario,
            String codUsr
    ) throws ServiceException {

        String runSP = createCall("p_rechazar_procesado", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, txtComentario, codUsr);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, txtComentario);
            callableStatement.setString(3, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.rechazarProcesado] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputValor<String> excluirProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException {
        return marcarProcesado("p_excluir_procesado", "excluirProcesado", idProceso, txtComentario, codUsr);
    }

    @Override
    public OutputValor<String> incidenciaProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException {
        return marcarProcesado("p_incidencia_procesado", "incidenciaProcesado", idProceso, txtComentario, codUsr);
    }

    /**
     * Trata el marcado de Procesos
     *
     * @param plName
     * @param javaName
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @return
     * @throws ServiceException
     */
    private OutputValor<String> marcarProcesado(
            String plName,
            String javaName,
            BigDecimal idProceso,
            String txtComentario,
            String codUsr
    ) throws ServiceException {

        String runSP = createCall(plName, 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, txtComentario, codUsr);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, txtComentario);
            callableStatement.setString(3, codUsr);
            callableStatement.registerOutParameter(4, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputValor<String> output = new OutputValor();
            output.setOutputWarning(result);
            output.setValor(callableStatement.getString(4));
            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService." + javaName + "] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputFicherosPeticion consultaFicherosAccion(Integer codEstado, BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_fichero_accion", 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeFicheroAccion = createCallType(MDSQLConstants.T_T_FICHERO_ACCION);
            String typeFicheroZip = createCallType(MDSQLConstants.T_T_FICHERO_ZIP);

            logProcedure(runSP, codEstado, idProceso);

            //callableStatement.setInt(1, codEstado);
            callableStatement.setBigDecimal(1, new BigDecimal(codEstado));
            callableStatement.setBigDecimal(2, idProceso);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeFicheroAccion);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeFicheroZip);

            OutputWarning result = executeStatement(callableStatement);

            OutputFicherosPeticion output = new OutputFicherosPeticion();
            output.setOutputWarning(result);
            output.setFicherosAccion(fromDBListFicheroAccion(callableStatement.getArray(3)));
            output.setFicherosZip(fromDBListFicheroZip(callableStatement.getArray(4)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.prepararCierre] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     *
     * @param output
     * @param raiseException
     * @return
     * @throws ServiceException
     */
    @Override
    public OutputWarning ejecutarFicherosAccion(OutputFicherosPeticion output, boolean raiseException) throws ServiceException {
        OutputWarning result = null;
        List<Object[]> errors = new ArrayList<>();
        // Verificar la existencia de los archivos
        for (FicheroAccion ficheroAccion : output.getFicherosAccion()) {
            try {
                ficheroAccion.validar();
            } catch (IOException ex) {
                trataErrorFicheros(ex, errors, raiseException);
            }
        }
        // Verificar la existencia de los archivos incluidos en el Zip
        for (FicheroZip ficheroZip : output.getFicherosZip()) {
            try {
                ficheroZip.validar();
            } catch (IOException ex) {
                trataErrorFicheros(ex, errors, raiseException);
            }
        }

        //Mover/Copiar/Renombrar los archivos
        for (FicheroAccion ficheroAccion : output.getFicherosAccion()) {
            try {
                ficheroAccion.ejecutar();
            } catch (IOException ex) {
                trataErrorFicheros(ex, errors, raiseException);
            }
        }

        // Crear los ficheros Zip si procede
        for (FicheroZip ficheroZip : output.getFicherosZip()) {
            try {
                ficheroZip.crear();
            } catch (IOException ex) {
                trataErrorFicheros(ex, errors, raiseException);
            }
        }
        if (!errors.isEmpty()) {
            ServiceException warning = new ServiceException();
            warning.setErrors(errors);
            warning.setType(MDSQLConstants.Results.WARN.getNum());
            result = OutputWarning.builder().warnings(warning).build();
        }
        return result;
    }

    /**
     *
     * @param ex
     * @param errors
     * @param raiseException
     * @throws ServiceException
     */
    private void trataErrorFicheros(IOException ex, List<Object[]> errors, boolean raiseException) throws ServiceException {
        if (raiseException) {
            throw new ServiceException(ex);
        } else {
            String[] arrString = {ex.getMessage()};
            if (errors.isEmpty() || !errors.contains(arrString)) {
                errors.add(arrString);
            }
        }
    }

}
