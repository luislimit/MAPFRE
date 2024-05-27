package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.service.UtilsService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.UTILS_SERVICE)
@Slf4j
public class UtilsServiceImpl extends ServiceSupport implements UtilsService {

    @Autowired
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void rechazarProcesado(BigDecimal idProceso, String txtComentario, String codigoUsuario) {
        String runSP = createCall("p_rechazar_procesado", MDSQLConstants.CALL_05_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, txtComentario, codigoUsuario);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, txtComentario);
            callableStatement.setString(3, codigoUsuario);

            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(4);

            if (result == 0) {
                throw buildException(callableStatement.getArray(5));
            }
        } catch (SQLException e) {
            LogWrapper.error(log, "[UtilsService.rechazarProcesado] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
