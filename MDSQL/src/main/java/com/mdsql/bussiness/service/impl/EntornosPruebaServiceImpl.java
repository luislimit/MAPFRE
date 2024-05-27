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

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.bussiness.entities.InputMntoEntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsultarEntornosPrueba;
import com.mdsql.bussiness.entities.OutputMntoEntornoPrueba;
import com.mdsql.bussiness.service.EntornosPruebaService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Service(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE)
@Slf4j
public class EntornosPruebaServiceImpl extends ServiceSupport implements EntornosPruebaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsultarEntornosPrueba consultarEntornos() throws ServiceException {
        String runSP = createCall("p_con_entorno_pruebas", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {
        	
        	String typeEntorno = createCallType(MDSQLConstants.T_T_ENTORNO_PRUEBA);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeEntorno);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

            OutputConsultarEntornosPrueba outputConsultarEntornosPrueba = new OutputConsultarEntornosPrueba();
            outputConsultarEntornosPrueba.setResult(result);
			
			// Hay avisos
			if (result == 2) {
				outputConsultarEntornosPrueba.setServiceException(buildException(callableStatement.getArray(3)));
			}
            
            List<EntornoPrueba> entornos = new ArrayList<>();
            Array arrayEntornos = callableStatement.getArray(1);

            if (arrayEntornos != null) {
                Object[] rows = (Object[]) arrayEntornos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    EntornoPrueba entorno = EntornoPrueba.builder()
                    		.nombreEntorno((String) cols[0])
                            .bbdd((String) cols[1])
                            .esquema((String) cols[2])
                            .descripcion((String) cols[3])
                            .tablespace((String) cols[4])
                            .gradoParal((String) cols[5])
                            .mcaHabilitado((String) cols[6])
                            .build();

                    entornos.add(entorno);
                }
                
                outputConsultarEntornosPrueba.setEntornos(entornos);
            }
            return outputConsultarEntornosPrueba;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.consultarEntornos] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

	@Override
	public OutputMntoEntornoPrueba guardarEntorno(InputMntoEntornoPrueba inputMntoEntornoPrueba, String codUsr)
			throws ServiceException {
		String runSP = createCall("p_mnto_entorno_prueba", MDSQLConstants.CALL_10_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

        	String typeError = createCallTypeError();

            logProcedure(runSP, inputMntoEntornoPrueba.getNombreEntorno(), inputMntoEntornoPrueba.getBbdd(), 
            		inputMntoEntornoPrueba.getEsquema(), inputMntoEntornoPrueba.getDescripcion(),
            		inputMntoEntornoPrueba.getTablespace(), inputMntoEntornoPrueba.getGradoParal(),
            		inputMntoEntornoPrueba.getMcaHabilitado(), codUsr);

            callableStatement.setString(1, inputMntoEntornoPrueba.getNombreEntorno());
            callableStatement.setString(2, inputMntoEntornoPrueba.getBbdd());
            callableStatement.setString(3, inputMntoEntornoPrueba.getEsquema());
            callableStatement.setString(4, inputMntoEntornoPrueba.getDescripcion());
            callableStatement.setString(5, inputMntoEntornoPrueba.getTablespace());
            callableStatement.setString(6, inputMntoEntornoPrueba.getGradoParal());
            callableStatement.setString(7, inputMntoEntornoPrueba.getMcaHabilitado());
            callableStatement.setString(8, codUsr);

            callableStatement.registerOutParameter(9, Types.INTEGER);
            callableStatement.registerOutParameter(10, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(9);

            if (result == 0) {
                throw buildException(callableStatement.getArray(10));
            }
            
            OutputMntoEntornoPrueba outputMntoEntornoPrueba = new OutputMntoEntornoPrueba();
            outputMntoEntornoPrueba.setResult(result);
            
            // Hay avisos
            if (result == 2) {
            	outputMntoEntornoPrueba.setServiceException(buildException(callableStatement.getArray(3)));
         	}

            return outputMntoEntornoPrueba;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.guardarEntorno] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
	}
}
