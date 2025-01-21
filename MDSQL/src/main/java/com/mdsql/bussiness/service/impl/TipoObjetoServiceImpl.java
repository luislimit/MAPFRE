package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
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

@Service(MDSQLConstants.TIPO_OBJETO_SERVICE)
@Slf4j
public class TipoObjetoServiceImpl extends ServiceSupport implements TipoObjetoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<String> consultarTiposObjeto() throws ServiceException {
        String runSP = createCall("p_con_tipos_obj_per", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTipoObjeto = createCallType(MDSQLConstants.T_T_TIP_OBJETO);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeTipoObjeto);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<String> outputConsultaTiposObjeto = new OutputConsulta<>();
            outputConsultaTiposObjeto.setOutputWarning(result);

            List<String> tipos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(1);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    String tipo = (String) cols[0];
                    tipos.add(tipo);
                }

                outputConsultaTiposObjeto.setLista(tipos);
            }
            return outputConsultaTiposObjeto;
        } catch (SQLException e) {
            LogWrapper.error(log, "[TipoObjetoService.consultarTiposObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<String> consultarTiposVariable() throws ServiceException {
        String runSP = createCall("p_con_tipos_vbles", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTipoVariable = createCallType(MDSQLConstants.T_T_TIPO_VBLE);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeTipoVariable);

            OutputWarning result = executeStatement(callableStatement);
            OutputConsulta<String> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<String> tipos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(1);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    String tipo = (String) cols[0];
                    tipos.add(tipo);
                }
            }
            output.setLista(tipos);
            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[TipoObjetoService.consultarTiposVariable] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
