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

import com.mdsql.bussiness.entities.OutputConsultaTiposObjeto;
import com.mdsql.bussiness.service.TipoObjetoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Service(MDSQLConstants.TIPO_OBJETO_SERVICE)
@Slf4j
public class TipoObjetoServiceImpl extends ServiceSupport implements TipoObjetoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsultaTiposObjeto consultarTiposObjeto() throws ServiceException {
        String runSP = createCall("p_con_tipos_obj_per", MDSQLConstants.CALL_03_ARGS);

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
            
            OutputConsultaTiposObjeto outputConsultaTiposObjeto = new OutputConsultaTiposObjeto();
            outputConsultaTiposObjeto.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputConsultaTiposObjeto.setServiceException(buildException(callableStatement.getArray(3)));
			}

            List<String> tipos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(1);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    String tipo = (String) cols[0];
                    tipos.add(tipo);
                }
                
                outputConsultaTiposObjeto.setTiposObjeto(tipos);
            }
            return outputConsultaTiposObjeto;
        } catch (SQLException e) {
            LogWrapper.error(log, "[TipoObjetoService.consultarTiposObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> consultarTiposVariable() throws ServiceException {
        String runSP = createCall("p_con_tipos_vbles", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTipoVariable = createCallType(MDSQLConstants.T_T_TIPO_VBLE);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeTipoVariable);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

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
            return tipos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[TipoObjetoService.consultarTiposVariable] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
