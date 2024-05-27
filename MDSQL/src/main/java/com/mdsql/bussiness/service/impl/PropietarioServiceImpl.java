package com.mdsql.bussiness.service.impl;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.bussiness.service.PropietarioService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Service(MDSQLConstants.PROPIETARIO_SERVICE)
@Slf4j
public class PropietarioServiceImpl extends ServiceSupport implements PropietarioService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Propietario> consultarPropietariosSinonimo(Modelo modelo) throws ServiceException {
        String runSP = createCall("p_con_propietario_per_modelo", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeOwnerSyn = createCallType(MDSQLConstants.T_T_OWNER_SYN);
            String typeError = createCallTypeError();

            String codigoProyecto = modelo.getCodigoProyecto();
            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeOwnerSyn);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<Propietario> propietarios = new ArrayList<>();
            Array arrayPropietarios = callableStatement.getArray(2);

            if (arrayPropietarios != null) {
                Object[] rows = (Object[]) arrayPropietarios.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Propietario propietario = Propietario.builder()
                            .codPropietario((String) cols[0])
                            .desPropietario((String) cols[1])
                            .build();

                    propietarios.add(propietario);
                }
            }
            return propietarios;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PropietarioService.consultarPropietariosSinonimo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Grant> consultarReceptoresModelo(Modelo modelo) throws ServiceException {
        String runSP = createCall("p_con_receptor_per_modelo", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeOwnerSyn = createCallType(MDSQLConstants.T_T_USR_GRANT);
            String typeError = createCallTypeError();

            String codigoProyecto = modelo.getCodigoProyecto();
            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeOwnerSyn);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<Grant> grants = new ArrayList<>();
            Array arrayGrants = callableStatement.getArray(2);

            if (arrayGrants != null) {
                Object[] rows = (Object[]) arrayGrants.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Grant grant = Grant.builder()
                            .codGrant((String) cols[0])
                            .desGrant((String) cols[1])
                            .build();

                    grants.add(grant);
                }
            }
            return grants;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PropietarioService.consultarReceptoresModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
