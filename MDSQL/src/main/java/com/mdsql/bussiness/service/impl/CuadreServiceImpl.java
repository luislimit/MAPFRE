package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.CuadreService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.CUADRE_SERVICE)
@Slf4j
public class CuadreServiceImpl extends ServiceSupport implements CuadreService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<CuadreOperacion> consultaCuadreOperacionesScript(BigDecimal idProceso, BigDecimal numeroOrden)
            throws ServiceException {
        String runSP = createCall("p_con_cuadre_oper_script", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeCuadreOperaciones = createCallType(MDSQLConstants.T_T_CUADRE_OPER);

            logProcedure(runSP, idProceso, numeroOrden);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeCuadreOperaciones);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<CuadreOperacion> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<CuadreOperacion> lista = new ArrayList<>();

            Array arrayCuadreOperaciones = callableStatement.getArray(3);

            if (arrayCuadreOperaciones != null) {
                Object[] rows = (Object[]) arrayCuadreOperaciones.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    CuadreOperacion cuadreOperacion = CuadreOperacion.builder().tipoObjeto((String) cols[0])
                            .tipoAccion((String) cols[1]).numeroOperacionBBDD((BigDecimal) cols[2])
                            .numeroOperacionScript((BigDecimal) cols[3]).build();

                    lista.add(cuadreOperacion);
                }
            }
            output.setLista(lista);
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[CuadreService.consultaCuadreOperacionesScript] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<CuadreObjeto> consultaCuadreOperacionesObjetoScript(BigDecimal idProceso, BigDecimal numeroOrden)
            throws ServiceException {
        String runSP = createCall("p_con_cuadre_obj_script", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeCuadreObjetoScript = createCallType(MDSQLConstants.T_T_CUADRE_OBJ);

            logProcedure(runSP, idProceso, numeroOrden);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeCuadreObjetoScript);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<CuadreObjeto> output = new OutputConsulta<>();
            output.setOutputWarning(result);

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
            output.setLista(cuadreObjetos);
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[CuadreService.consultaCuadreOperacionesObjetoScript] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
