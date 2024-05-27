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
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.OutputAltaHistorico;
import com.mdsql.bussiness.entities.OutputBajaHistorico;
import com.mdsql.bussiness.entities.OutputConsultaHistorico;
import com.mdsql.bussiness.entities.OutputConsultaHistoricoProceso;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.HISTORICO_SERVICE)
@Slf4j
public class HistoricoServiceImpl extends ServiceSupport implements HistoricoService {

    @Autowired
    private DataSource dataSource;


    @Override
    @SneakyThrows(ServiceException.class)
    public OutputConsultaHistoricoProceso consultarHistoricoProceso(InputConsutaHistoricoProceso inputConsutaHistoricoProceso) {
        String runSP = createCall("p_con_historico_objeto", MDSQLConstants.CALL_14_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHisProc = createCallType(MDSQLConstants.T_T_HIS_PROC);
            String typeError = createCallTypeError();

            logProcedure(runSP, inputConsutaHistoricoProceso.getCodigoProyecto(), inputConsutaHistoricoProceso.getNombreObjetoPadre(), inputConsutaHistoricoProceso.getTipoObjetoPadre(), inputConsutaHistoricoProceso.getTipoAccionPadre()
                    , inputConsutaHistoricoProceso.getNombreObjeto(), inputConsutaHistoricoProceso.getTipoObjeto(), inputConsutaHistoricoProceso.getTipoAccion(), inputConsutaHistoricoProceso.getFechaDesde()
                    , inputConsutaHistoricoProceso.getFechaHasta(), inputConsutaHistoricoProceso.getCodigoEstadoProceso(), inputConsutaHistoricoProceso.getCodigoEstadoScript());

            callableStatement.setString(1, inputConsutaHistoricoProceso.getCodigoProyecto());
            callableStatement.setString(2, inputConsutaHistoricoProceso.getNombreObjetoPadre());
            callableStatement.setString(3, inputConsutaHistoricoProceso.getTipoObjetoPadre());
            callableStatement.setString(4, inputConsutaHistoricoProceso.getTipoAccionPadre());
            callableStatement.setString(5, inputConsutaHistoricoProceso.getNombreObjeto());
            callableStatement.setString(6, inputConsutaHistoricoProceso.getTipoObjeto());
            callableStatement.setString(7, inputConsutaHistoricoProceso.getTipoAccion());
            
            Date fechaDesde = inputConsutaHistoricoProceso.getFechaDesde();
            if (!Objects.isNull(fechaDesde)) {
            	callableStatement.setDate(8, new java.sql.Date(fechaDesde.getTime()));
            }
            else {
            	callableStatement.setDate(8, null);
            }
            
            Date fechaHasta = inputConsutaHistoricoProceso.getFechaHasta();
            if (!Objects.isNull(fechaHasta)) {
            	callableStatement.setDate(9, new java.sql.Date(fechaHasta.getTime()));
            }
            else {
            	callableStatement.setDate(9, null);
            }
            
            callableStatement.setBigDecimal(10, inputConsutaHistoricoProceso.getCodigoEstadoProceso());
            callableStatement.setBigDecimal(11, inputConsutaHistoricoProceso.getCodigoEstadoScript());
            callableStatement.registerOutParameter(12, Types.ARRAY, typeHisProc);
            callableStatement.registerOutParameter(13, Types.INTEGER);
            callableStatement.registerOutParameter(14, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(13);

            if (result == 0) {
                throw buildException(callableStatement.getArray(14));
            }
            
            OutputConsultaHistoricoProceso outputConsultaHistoricoProceso = new OutputConsultaHistoricoProceso();
            outputConsultaHistoricoProceso.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputConsultaHistoricoProceso.setServiceException(buildException(callableStatement.getArray(14)));
			}

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
                
                outputConsultaHistoricoProceso.setHistoricoProcesos(historicoProcesos);
            }
            return outputConsultaHistoricoProceso;
        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.consultarHistoricoProceso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsultaHistorico consultarHistorico(String codigoProyecto, String tipoObjeto) throws ServiceException {
        String runSP = createCall("p_con_obj_historico", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeHis = createCallType(MDSQLConstants.T_T_DET_OBJ_HIS);
            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, tipoObjeto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, tipoObjeto);

            callableStatement.registerOutParameter(3, Types.ARRAY, typeHis);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(4);

            if (result == 0) {
                throw buildException(callableStatement.getArray(5));
            }
            
            OutputConsultaHistorico outputConsultaHistorico = new OutputConsultaHistorico();
            outputConsultaHistorico.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputConsultaHistorico.setServiceException(buildException(callableStatement.getArray(5)));
			}

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
                outputConsultaHistorico.setHistorico(historicos);
            }
            return outputConsultaHistorico;
        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.consultarHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputBajaHistorico bajaHistorico(String codigoProyecto, String nombreObjeto, String peticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_baja_obj_historico", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, nombreObjeto, peticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, nombreObjeto);
            callableStatement.setString(3, peticion);
            callableStatement.setString(4, codUsr);

            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(5);

            if (result == 0) {
                throw buildException(callableStatement.getArray(6));
            }
            
            OutputBajaHistorico outputBajaHistorico = new OutputBajaHistorico();
            outputBajaHistorico.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputBajaHistorico.setServiceException(buildException(callableStatement.getArray(6)));
			}

			return outputBajaHistorico;

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.bajaHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputAltaHistorico altaHistorico(String codigoProyecto, String nombreObjeto, String tipoObjeto, String historificada, String peticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_alta_obj_historico", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, nombreObjeto, tipoObjeto, historificada, peticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, nombreObjeto);
            callableStatement.setString(3, tipoObjeto);
            callableStatement.setString(4, historificada);
            callableStatement.setString(5, peticion);
            callableStatement.setString(6, codUsr);

            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(7);

            if (result == 0) {
                throw buildException(callableStatement.getArray(8));
            }
            
            OutputAltaHistorico outputAltaHistorico = new OutputAltaHistorico();
            outputAltaHistorico.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputAltaHistorico.setServiceException(buildException(callableStatement.getArray(8)));
			}

			return outputAltaHistorico;

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.altaHistorico] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
