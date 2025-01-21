package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.HistoricoProc;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.HISTORICO_SERVICE)
@Slf4j
public class HistoricoServiceImpl extends ServiceSupport implements HistoricoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<HistoricoProceso> consultarHistoricoObjeto(InputConsutaHistoricoProceso inputConsutaHistoricoProceso) throws ServiceException {
        String runSP = createCall("p_con_historico_objeto", 14);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHisProc = createCallType(MDSQLConstants.T_T_HIS_PROC);

            logProcedure(runSP, inputConsutaHistoricoProceso.getCodigoProyecto(), inputConsutaHistoricoProceso.getNombreObjetoPadre(), inputConsutaHistoricoProceso.getTipoObjetoPadre(), inputConsutaHistoricoProceso.getTipoAccionPadre(),
                    inputConsutaHistoricoProceso.getNombreObjeto(), inputConsutaHistoricoProceso.getTipoObjeto(), inputConsutaHistoricoProceso.getTipoAccion(), inputConsutaHistoricoProceso.getFechaDesde(),
                    inputConsutaHistoricoProceso.getFechaHasta(), inputConsutaHistoricoProceso.getCodigoEstadoProceso(), inputConsutaHistoricoProceso.getCodigoEstadoScript());

            callableStatement.setString(1, inputConsutaHistoricoProceso.getCodigoProyecto());
            callableStatement.setString(2, inputConsutaHistoricoProceso.getNombreObjetoPadre());
            callableStatement.setString(3, inputConsutaHistoricoProceso.getTipoObjetoPadre());
            callableStatement.setString(4, inputConsutaHistoricoProceso.getTipoAccionPadre());
            callableStatement.setString(5, inputConsutaHistoricoProceso.getNombreObjeto());
            callableStatement.setString(6, inputConsutaHistoricoProceso.getTipoObjeto());
            callableStatement.setString(7, inputConsutaHistoricoProceso.getTipoAccion());

            setDate(callableStatement, 8, inputConsutaHistoricoProceso.getFechaDesde());
            setDate(callableStatement, 9, inputConsutaHistoricoProceso.getFechaHasta());
            callableStatement.setBigDecimal(10, inputConsutaHistoricoProceso.getCodigoEstadoProceso());
            callableStatement.setBigDecimal(11, inputConsutaHistoricoProceso.getCodigoEstadoScript());
            callableStatement.registerOutParameter(12, Types.ARRAY, typeHisProc);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<HistoricoProceso> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<HistoricoProceso> historicoProcesos = new ArrayList<>();
            Array arrayHistoricoProceso = callableStatement.getArray(12);

            if (arrayHistoricoProceso != null) {
                Object[] rows = (Object[]) arrayHistoricoProceso.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    HistoricoProceso historicoProceso = HistoricoProceso.builder()
                            .codigoPeticion((String) cols[0])
                            .descripcionEstadoProceso((String) cols[1])
                            .fechaProceso((java.util.Date) cols[2])
                            .codigoSubProyecto((String) cols[3])
                            .codigoUsuarioPeticion((String) cols[4])
                            .codigoUsuario((String) cols[5])
                            .tipoAccion((String) cols[6])
                            .tipoAccionPadre((String) cols[7])
                            .nombreScript((String) cols[8])
                            .descripcionEstadoScript((String) cols[9])
                            .idProceso((BigDecimal) cols[10])
                            .numeroOrden((BigDecimal) cols[11])
                            .build();
                    historicoProcesos.add(historicoProceso);
                }

                output.setLista(historicoProcesos);
            }
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.consultarHistoricoProceso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<Historico> consultarHistorico(String codigoProyecto, String tipoObjeto) throws ServiceException {
        String runSP = createCall("p_con_obj_historico", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHis = createCallType(MDSQLConstants.T_T_DET_OBJ_HIS);

            logProcedure(runSP, codigoProyecto, tipoObjeto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, tipoObjeto);

            callableStatement.registerOutParameter(3, Types.ARRAY, typeHis);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Historico> output = new OutputConsulta();
            output.setOutputWarning(result);

            List<Historico> historicos = new ArrayList<>();
            Array arrayHistoricoProceso = callableStatement.getArray(3);

            if (arrayHistoricoProceso != null) {
                Object[] rows = (Object[]) arrayHistoricoProceso.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Historico historico = Historico.builder()
                            .nombreObjeto((String) cols[0])
                            .tipoObjeto((String) cols[1])
                            .historico((String) cols[2])
                            .historificado((String) cols[3])
                            .peticion((String) cols[4])
                            .codigoUsuario((String) cols[5])
                            .fechaActualizacion((java.util.Date) cols[6])
                            .build();
                    historicos.add(historico);
                }
                output.setLista(historicos);
            }
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.consultarHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning bajaHistorico(String codigoProyecto, String nombreObjeto, String peticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_baja_obj_historico", 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, nombreObjeto, peticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, nombreObjeto);
            callableStatement.setString(3, peticion);
            callableStatement.setString(4, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.bajaHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning altaHistorico(String codigoProyecto, String nombreObjeto, String tipoObjeto, String historificada, String peticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_alta_obj_historico", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, nombreObjeto, tipoObjeto, historificada, peticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, nombreObjeto);
            callableStatement.setString(3, tipoObjeto);
            callableStatement.setString(4, historificada);
            callableStatement.setString(5, peticion);
            callableStatement.setString(6, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.altaHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<HistoricoProc> consultarHistoricoProcesado(BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_historico_proc", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHisProc = createCallType(MDSQLConstants.T_T_HISTORICO_PROC);

            logProcedure(runSP, idProceso);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeHisProc);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<HistoricoProc> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<HistoricoProc> historicoProcesos = new ArrayList<>();
            Array arrayHistoricoProceso = callableStatement.getArray(2);

            if (arrayHistoricoProceso != null) {
                Object[] rows = (Object[]) arrayHistoricoProceso.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    //TODO: Pendiente de obtener la descripcion (descomentar la linea)
                    HistoricoProc historicoProceso = HistoricoProc.builder()
                            .fechaCambio((Date) cols[0])
                            .tipoCambio((String) cols[1])
                            .valorCambio((String) cols[2])
                            .codUsr((String) cols[3])
                            //.descripcion((String) cols[4])
                            .build();
                    historicoProcesos.add(historicoProceso);
                }
                output.setLista(historicoProcesos);
            }
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.consultarHistoricoProcesado] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
