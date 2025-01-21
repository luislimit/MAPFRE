package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputValidaUsuario;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.UtilsService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.UTILS_SERVICE)
@Slf4j
public class UtilsServiceImpl extends ServiceSupport implements UtilsService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputValidaUsuario validaUsuario(String codigoUsuario, String numVersion) throws ServiceException {
        String runSP = createCall("p_valida_usuario", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeEstado = createCallType(MDSQLConstants.T_T_ESTADO);

            logProcedure(runSP, codigoUsuario, numVersion);

            callableStatement.setString(1, codigoUsuario);
            callableStatement.setString(2, numVersion);

            callableStatement.registerOutParameter(3, Types.VARCHAR); // TieneEnCurso
            callableStatement.registerOutParameter(4, Types.ARRAY, typeEstado); // Lista de estados
            callableStatement.registerOutParameter(5, Types.INTEGER); // Lista de estados

            OutputWarning result = executeStatement(callableStatement);

            OutputValidaUsuario output = new OutputValidaUsuario();
            output.setOutputWarning(result);
            output.setTieneEnCurso(callableStatement.getString(3));
            output.setEstados(fromDBListEstado(callableStatement, callableStatement.getArray(4)));
            output.setDelayMensaje(callableStatement.getInt(5));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[UtilsServiceImpl.validaUsuario] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

}
