package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.AvisoService;
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
@Service(MDSQLConstants.AVISO_SERVICE)
@Slf4j
public class AvisoServiceImpl extends ServiceSupport implements AvisoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<Aviso> consultaAvisosModelo(String codigoProyecto) throws ServiceException {
        String runSP = createCall("p_con_avisos_modelo", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeAviso = createCallType(MDSQLConstants.T_T_AVISO);

            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeAviso);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Aviso> output = new OutputConsulta();
            output.setOutputWarning(result);

            output.setLista(fromDBListAviso(callableStatement.getArray(2)));

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.consultaAvisosModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<NivelImportancia> consultaNivelesImportancia() throws ServiceException {
        String runSP = createCall("p_con_nivel_importancia", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeNivelAviso = createCallType(MDSQLConstants.T_T_NIVEL_AVISO);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeNivelAviso);

            OutputWarning result = executeStatement(callableStatement);

            List<NivelImportancia> niveles = new ArrayList<>();
            Array arrayNiveles = callableStatement.getArray(1);

            if (arrayNiveles != null) {
                Object[] rows = (Object[]) arrayNiveles.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    NivelImportancia nivel = NivelImportancia.builder()
                            .codigoNivelAviso((BigDecimal) cols[0])
                            .descripcionNivelAviso((String) cols[1])
                            .build();

                    niveles.add(nivel);
                }
            }

            OutputConsulta<NivelImportancia> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(niveles);

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.consultaNivelesImportancia] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning altaAviso(String codigoProyecto, String desAviso, String txtAviso, String codNivelAviso, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_alta_avisos_modelo", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, desAviso, txtAviso, codNivelAviso, codPeticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, desAviso);
            callableStatement.setString(3, txtAviso);
            callableStatement.setString(4, codNivelAviso);
            callableStatement.setString(5, codPeticion);
            callableStatement.setString(6, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.altaAviso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning modificarAviso(String codigoProyecto, BigDecimal codigoAviso, String desAviso, String txtAviso, String codNivelAviso, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_mod_avisos_modelo", 10);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, codigoAviso, desAviso, txtAviso, codNivelAviso, mcaHabilitado, codPeticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setBigDecimal(2, codigoAviso);
            callableStatement.setString(3, desAviso);
            callableStatement.setString(4, txtAviso);
            callableStatement.setString(5, codNivelAviso);
            callableStatement.setString(6, mcaHabilitado);
            callableStatement.setString(7, codPeticion);
            callableStatement.setString(8, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.modificarAviso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<Aviso> consultaAvisosObjeto(String codigoProyecto, String codPeticion, String codNivelAviso, String nomObjeto, String mcaHabilitado) throws ServiceException {
        String runSP = createCall("p_con_avisos_objeto", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeAviso = createCallType(MDSQLConstants.T_T_AVISO);

            logProcedure(runSP, codigoProyecto, codPeticion, codNivelAviso, nomObjeto, mcaHabilitado);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, codPeticion);
            callableStatement.setString(3, codNivelAviso);
            callableStatement.setString(4, nomObjeto);
            callableStatement.setString(5, mcaHabilitado);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeAviso);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Aviso> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(fromDBListAviso(callableStatement.getArray(6)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.consultaAvisosObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning mntoAvisos(String codigoProyecto, String codPeticion, String codNivelAviso, String nomObjeto,
            String mcaHabilitado, BigDecimal codAviso, String titulo, String descripcion, String codUsr) throws ServiceException {
        String runSP = createCall("p_mnto_avisos", 11);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, codPeticion, codNivelAviso, nomObjeto,
                    mcaHabilitado, codAviso, titulo, descripcion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, codPeticion);
            callableStatement.setString(3, codNivelAviso);
            callableStatement.setString(4, nomObjeto);
            callableStatement.setString(5, mcaHabilitado);
            callableStatement.setBigDecimal(6, codAviso);
            callableStatement.setString(7, titulo);
            callableStatement.setString(8, descripcion);
            callableStatement.setString(9, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.mntoAvisos] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
