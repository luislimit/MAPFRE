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

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.OutputConsultaProcesado;
import com.mdsql.bussiness.entities.OutputSeleccionarHistorico;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.PROCESO_SERVICE)
@Slf4j
public class ProcesoServiceImpl extends ServiceSupport implements ProcesoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Proceso> seleccionarProcesados(InputSeleccionarProcesados inputSeleccionarProcesados)
            throws ServiceException {
        String runSP = createCall("p_sel_procesados", MDSQLConstants.CALL_12_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeProceso = createCallType(MDSQLConstants.T_T_PROCESO);
            String typeError = createCallTypeError();

            logProcedure(runSP, inputSeleccionarProcesados.getPCodigoPeticion(),
                    inputSeleccionarProcesados.getPCodigoUsuarioPeticion(),
                    inputSeleccionarProcesados.getPFechaInicio(), inputSeleccionarProcesados.getPFechaFin(),
                    inputSeleccionarProcesados.getPCodigoUsuario(), inputSeleccionarProcesados.getPCodigoproyecto(),
                    inputSeleccionarProcesados.getPCodigoSubProyecto(),
                    inputSeleccionarProcesados.getPDescripcionEstadoProceso(),
                    inputSeleccionarProcesados.getPUltimas());

            callableStatement.setString(1, inputSeleccionarProcesados.getPCodigoPeticion());
            callableStatement.setString(2, inputSeleccionarProcesados.getPCodigoUsuarioPeticion());
            callableStatement.setDate(3, (java.sql.Date) inputSeleccionarProcesados.getPFechaInicio());
            callableStatement.setDate(4, (java.sql.Date) inputSeleccionarProcesados.getPFechaFin());
            callableStatement.setString(5, inputSeleccionarProcesados.getPCodigoUsuario());
            callableStatement.setString(6, inputSeleccionarProcesados.getPCodigoproyecto());
            callableStatement.setString(7, inputSeleccionarProcesados.getPCodigoSubProyecto());
            callableStatement.setString(8, inputSeleccionarProcesados.getPDescripcionEstadoProceso());
            callableStatement.setBigDecimal(9, inputSeleccionarProcesados.getPUltimas());
            callableStatement.registerOutParameter(10, Types.ARRAY, typeProceso);
            callableStatement.registerOutParameter(11, Types.INTEGER);
            callableStatement.registerOutParameter(12, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(11);

            if (result == 0) {
                throw buildException(callableStatement.getArray(12));
            }

            List<Proceso> procesos = new ArrayList<>();
            Array arrayProcesos = callableStatement.getArray(10);

            if (arrayProcesos != null) {
                Object[] rows = (Object[]) arrayProcesos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Proceso proceso = Proceso.builder().idProceso((BigDecimal) cols[0]).codigoPeticion((String) cols[1])
                            .codigoUsrPeticion((String) cols[2]).fechaInicio((Date) cols[3]).codigoUsr((String) cols[4])
                            .codigoEstadoProceso((BigDecimal) cols[5]).descripcionEstadoProceso((String) cols[6])
                            .mcaInicial((String) cols[7]).txtDescripcion((String) cols[8])
                            .txtObservacionEntrega((String) cols[9]).mcaErrores((String) cols[10])
                            .codProyecto((String) cols[11]).codSubproyecto((String) cols[12]).build();

                    procesos.add(proceso);
                }
            }

            return procesos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.seleccionarProcesados] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputSeleccionarHistorico seleccionarHistorico(String codProyecto, List<TextoLinea> lineas)
            throws ServiceException {
        String runSP = createCall("p_sel_historico", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHistorico = createCallType(MDSQLConstants.T_T_OBJ_HIS);
            String trLinea = createCallType(MDSQLConstants.T_R_LINEA);
            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String typeError = createCallTypeError();

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
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(4);

            if (result == 0) {
                throw buildException(callableStatement.getArray(5));
            }
            
            OutputSeleccionarHistorico outputSeleccionarHistorico = new OutputSeleccionarHistorico();
            if (result == 2) {
                List<Object[]> warnings = getWarnings(callableStatement.getArray(5));
                outputSeleccionarHistorico.setWarnings(warnings);
            }

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
            outputSeleccionarHistorico.setSeleccion(seleccion);

            return outputSeleccionarHistorico;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.seleccionarHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public ServiceException altaHistorico(List<SeleccionHistorico> listaObjetos, String codigoProyecto, String codigoPeticion,
                              String codigoUsuario) {
        String runSP = createCall("p_alta_historico", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableObjetos = createCallType(MDSQLConstants.T_T_OBJETOS);
            String recordObjetos = createCallType(MDSQLConstants.T_R_OBJETOS);

            String typeError = createCallTypeError();

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
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(5);

            if (result == 0) {
            	ServiceException serviceException = buildException(callableStatement.getArray(6));
            	return serviceException;
            }
            
            if (result == 2) {
				ServiceException serviceException = buildWarning(callableStatement.getArray(6));
				return serviceException;
			}
            
            return null;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.altaHistorico] Error: %s", e.getMessage());
            return new ServiceException(e);
        }

    }

	@Override
    public OutputConsultaProcesado consultaProcesado(BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_procesado", MDSQLConstants.CALL_19_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {


            String typeError = createCallTypeError();
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

            callableStatement.registerOutParameter(18, Types.INTEGER);
            callableStatement.registerOutParameter(19, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(18);

            if (result == 0) {
                throw buildException(callableStatement.getArray(19));
            }
            
            OutputConsultaProcesado outputConsultaProcesado = new OutputConsultaProcesado();
            outputConsultaProcesado.setResult(result);
            
            if (result == 2) { 
            	outputConsultaProcesado.setServiceException(buildException(callableStatement.getArray(19)));
            }

            String nombreModelo = callableStatement.getString(2);
            String codigoUsrPeticion = callableStatement.getString(3);
            String nombreBBDDHistorico = callableStatement.getString(4);
            String descripcionSubProyecto = callableStatement.getString(5);
            String nombreEsquema = callableStatement.getString(6);
            String nombreesquemaHistorico = callableStatement.getString(7);
            String codigoPeticion = callableStatement.getString(8);
            String nombreBBDD = callableStatement.getString(9);
            BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(10);
            String descripcionEstadoProceso = callableStatement.getString(11);
            String codigoUsuario = callableStatement.getString(12);
            Date fechaProceso = callableStatement.getDate(13);
            String txtComentario = callableStatement.getString(14);
            String mcaInicial = callableStatement.getString(15);
            String txtRutaEntrada = callableStatement.getString(16);


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

            outputConsultaProcesado.setNombreModelo(nombreModelo);
            outputConsultaProcesado.setCodigoUsrPeticion(codigoUsrPeticion);
            outputConsultaProcesado.setNombreBBDDHistorico(nombreBBDDHistorico);
            outputConsultaProcesado.setDescripcionSubProyecto(descripcionSubProyecto);
            outputConsultaProcesado.setNombreEsquema(nombreEsquema);
            outputConsultaProcesado.setNombreesquemaHistorico(nombreesquemaHistorico);
            outputConsultaProcesado.setCodigoPeticion(codigoPeticion);
            outputConsultaProcesado.setNombreBBDD(nombreBBDD);
            outputConsultaProcesado.setCodigoEstadoProceso(codigoEstadoProceso);
            outputConsultaProcesado.setDescripcionEstadoProceso(descripcionEstadoProceso);
            outputConsultaProcesado.setCodigoUsuario(codigoUsuario);
            outputConsultaProcesado.setFechaProceso(fechaProceso);
            outputConsultaProcesado.setTxtComentario(txtComentario);
            outputConsultaProcesado.setMcaInicial(mcaInicial);
            outputConsultaProcesado.setTxtRutaEntrada(txtRutaEntrada);
            outputConsultaProcesado.setListaScriptsEjecutados(scriptEjecutados);

            return outputConsultaProcesado;

        } catch (
                SQLException e) {
            LogWrapper.error(log, "[ProcesoService.consultaProcesado] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

	@Override
	public void rechazarProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException {
		String runSP = createCall("p_rechazar_procesado", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, txtComentario, codUsr);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, txtComentario);
            callableStatement.setString(3, codUsr);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(4);

            if (result == 0) {
                throw buildException(callableStatement.getArray(5));
            }
        } catch (SQLException e) {
            LogWrapper.error(log, "[ProcesoService.rechazarProcesado] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
	}


}
