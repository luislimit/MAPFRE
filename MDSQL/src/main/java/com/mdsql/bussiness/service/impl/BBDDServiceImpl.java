package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.BBDDService;
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

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.BBDD_SERVICE)
@Slf4j
public class BBDDServiceImpl extends ServiceSupport implements BBDDService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<BBDD> consultaBBDDModelo(String codigoProyecto, String codSubproyecto) throws ServiceException {
        String runSP = createCall("p_con_bbdd_modelo", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeBBDD = createCallType(MDSQLConstants.T_T_BBDD);

            logProcedure(runSP, codigoProyecto, codSubproyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, codSubproyecto);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeBBDD);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<BBDD> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<BBDD> bbdds = new ArrayList<>();
            Array arrayBBDDs = callableStatement.getArray(3);

            if (arrayBBDDs != null) {
                Object[] rows = (Object[]) arrayBBDDs.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    BBDD bbdd = BBDD.builder()
                            .nombreBBDD((String) cols[0])
                            .nombreEsquema((String) cols[1])
                            .nombreBBDDHis((String) cols[2])
                            .nombreEsquemaHis((String) cols[3])
                            .mcaDefecto((String) cols[4])
                            .build();

                    bbdds.add(bbdd);
                }
            }
            output.setLista(bbdds);

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[BBDDService.consultaBBDDModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public String consultaPasswordBBDD(String nombreBBDD, String nombreEsquema, String txtClaveEncriptada) throws ServiceException {
        String runSP = createCall("p_con_pass_bbdd", 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, nombreBBDD, nombreEsquema, txtClaveEncriptada);

            callableStatement.setString(1, nombreBBDD);
            callableStatement.setString(2, nombreEsquema);
            callableStatement.setString(3, txtClaveEncriptada);
            callableStatement.registerOutParameter(4, Types.VARCHAR);

            executeStatement(callableStatement);

            return callableStatement.getString(4);

        } catch (SQLException e) {
            LogWrapper.error(log, "[BBDDService.consultaPasswordBBDD] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public String consultaPasswordBBDD(String nombreBBDD, String nombreEsquema) throws ServiceException {
        String txtClaveEncriptada = getClaveEncriptada();
        return consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
    }

    @Override
    public String getClaveEncriptada() {
        return configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);
    }
}
