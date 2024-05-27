package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.OutputConsultaEntrega;
import com.mdsql.bussiness.service.EntregaService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.ENTREGA_SERVICE)
@Slf4j
public class EntregaServiceImpl extends ServiceSupport implements EntregaService {

    @Autowired
    private DataSource dataSource;


    @Override
    @SneakyThrows
    public OutputConsultaEntrega consultaRutaEntrega(String codigoProyecto, BigDecimal idProceso) {
        String runSP = createCall("p_con_ruta_entrega", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, idProceso);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setBigDecimal(2, idProceso);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);

            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(7);

            if (result == 0) {
                throw buildException(callableStatement.getArray(8));
            }

            String txtRutaEntrega = callableStatement.getString(3);
            String nombreFicheroVigente = callableStatement.getString(4);
            String nombreFicheroHistorico = callableStatement.getString(5);
            String nombreFicheroType = callableStatement.getString(6);


            OutputConsultaEntrega outputConsultaEntrega = OutputConsultaEntrega.builder()
                    .txtRutaEntrega(txtRutaEntrega)
                    .nombreFicheroVigente(nombreFicheroVigente)
                    .nombreFicheroHistorico(nombreFicheroHistorico)
                    .nombreFicheroType(nombreFicheroType)
                    .build();

            return outputConsultaEntrega;

        } catch (
                SQLException e) {
            LogWrapper.error(log, "[EntregaService.consultaRutaEntrega] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public String entregarPeticion(BigDecimal idProceso, String codigoUsuario, String txtComentario) throws ServiceException {
        String runSP = createCall("p_entregar_peticion", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, codigoUsuario, txtComentario);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, codigoUsuario);
            callableStatement.setString(3, txtComentario);
            callableStatement.registerOutParameter(4, Types.VARCHAR);

            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(5);

            if (result == 0) {
                throw buildException(callableStatement.getArray(6));
            }

            return callableStatement.getString(4);

        } catch (
                SQLException e) {
            LogWrapper.error(log, "[EntregaService.entregarPeticion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
