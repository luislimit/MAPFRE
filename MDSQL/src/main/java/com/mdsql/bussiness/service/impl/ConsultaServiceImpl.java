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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.Operacion;
import com.mdsql.bussiness.service.ConsultaService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.CONSULTA_SERVICE)
@Slf4j
public class ConsultaServiceImpl extends ServiceSupport implements ConsultaService {

    @Autowired
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public List<String> consultaTiposObjeto() {
        String runSP = createCall("p_con_tipos_objeto", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTipoObjeto = createCallType(MDSQLConstants.T_T_TIP_OBJETO);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeTipoObjeto);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

            List<String> tipoObjetos = new ArrayList<>();
            tipoObjetos.add(StringUtils.EMPTY);
            Array arrayTipoObjetos = callableStatement.getArray(1);

            if (arrayTipoObjetos != null) {
                Object[] rows = (Object[]) arrayTipoObjetos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    tipoObjetos.add((String) cols[0]);
                }
            }
            return tipoObjetos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaTiposObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public List<Estado> consultaEstadosProcesado() {
        String runSP = createCall("p_con_estados_proc", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeEstado = createCallType(MDSQLConstants.T_T_ESTADO);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeEstado);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

            List<Estado> estados = new ArrayList<>();
            estados.add(new Estado(null, StringUtils.EMPTY));
            
            Array arrayEstados = callableStatement.getArray(1);

            if (arrayEstados != null) {
                Object[] rows = (Object[]) arrayEstados.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Estado estado = Estado.builder()
                            .codigoEstado((BigDecimal) cols[0])
                            .descripcionEstado((String) cols[1])
                            .build();
                    estados.add(estado);
                }
            }
            return estados;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaEstadosProcesado] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public List<Estado> consultaEstadosScript() {
        String runSP = createCall("p_con_estados_scrip", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeOperacion = createCallType(MDSQLConstants.T_T_ESTADO);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeOperacion);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

            List<Estado> estados = new ArrayList<>();
            estados.add(new Estado(null, StringUtils.EMPTY));
            
            Array arrayEstados = callableStatement.getArray(1);

            if (arrayEstados != null) {
                Object[] rows = (Object[]) arrayEstados.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Estado estado = Estado.builder()
                            .codigoEstado((BigDecimal) cols[0])
                            .descripcionEstado((String) cols[1])
                            .build();
                    estados.add(estado);
                }
            }
            return estados;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaEstadosScript] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public List<Operacion> consultaOperaciones() {
        String runSP = createCall("p_con_operaciones", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeEstado = createCallType(MDSQLConstants.T_T_OPERACION);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeEstado);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

            List<Operacion> operaciones = new ArrayList<>();
            Operacion nullOperacion = Operacion.builder().descripcionAccion(StringUtils.EMPTY).tipoAccion(null).build();
            operaciones.add(nullOperacion);
            
            Array arrayOperaciones = callableStatement.getArray(1);

            if (arrayOperaciones != null) {
                Object[] rows = (Object[]) arrayOperaciones.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Operacion operacion = Operacion.builder()
                            .tipoAccion((String) cols[0])
                            .descripcionAccion((String) cols[1])
                            .build();
                    operaciones.add(operacion);
                }
            }
            return operaciones;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaOperaciones] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
