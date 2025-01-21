package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.ConsultaService;
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
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.CONSULTA_SERVICE)
@Slf4j
public class ConsultaServiceImpl extends ServiceSupport implements ConsultaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<String> consultaTiposObjeto() throws ServiceException {
        String runSP = createCall("p_con_tipos_objeto", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTipoObjeto = createCallType(MDSQLConstants.T_T_TIP_OBJETO);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeTipoObjeto);

            executeStatement(callableStatement);

            // La lista contiene un primer elemento vacío
            return super.fromDBListString(callableStatement.getArray(1), true);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaTiposObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<Estado> consultaEstadosProcesado() throws ServiceException {
        String runSP = createCall("p_con_estados_proc", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeEstado = createCallType(MDSQLConstants.T_T_ESTADO);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeEstado);

            OutputWarning outputWarning = executeStatement(callableStatement);

            OutputConsulta<Estado> output = new OutputConsulta();
            output.setLista(fromDBListEstado(callableStatement, callableStatement.getArray(1)));
            output.setWarnings(outputWarning.getWarnings());

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaEstadosProcesado] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    @SneakyThrows
    public List<Estado> consultaEstadosScript() {
        String runSP = createCall("p_con_estados_scrip", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

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
    public OutputConsulta<CodigoDescripcion> consultaOperaciones() throws ServiceException {
        String runSP = createCall("p_con_operaciones", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLista = createCallType(MDSQLConstants.T_T_OPERACION);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeLista);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<CodigoDescripcion> output = new OutputConsulta<>();
            output.setOutputWarning(result);
            output.setLista(fromDBListCodigoDescripcion(callableStatement.getArray(1)));

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ConsultaService.consultaOperaciones] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
