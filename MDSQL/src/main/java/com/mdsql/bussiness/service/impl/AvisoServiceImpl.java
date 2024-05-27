package com.mdsql.bussiness.service.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;


/**
 * @author hcarreno
 */
@Service(MDSQLConstants.AVISO_SERVICE)
@Slf4j
public class AvisoServiceImpl extends ServiceSupport implements AvisoService {

    @Autowired
    private DataSource dataSource;


    @Override
    public List<Aviso> consultaAvisosModelo(String codigoProyecto) throws ServiceException {
        String runSP = createCall("p_con_avisos_modelo", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeAviso = createCallType(MDSQLConstants.T_T_AVISO);
            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeAviso);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<Aviso> avisos = new ArrayList<>();
            Array arrayAvisos = callableStatement.getArray(2);

            if (arrayAvisos != null) {
                Object[] rows = (Object[]) arrayAvisos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Aviso aviso = Aviso.builder()
                            .codigoAviso((BigDecimal) cols[2])
                            .descripcionAviso((String) cols[3])
                            .txtAviso((String) cols[4])
                            .codigoPeticion((String) cols[5])
                            .fechaAlta((java.util.Date) cols[6])
                            .codigoUsrAlta((String) cols[7])
                            .mcaHabilitado((String) cols[8])
                            .fechaActualizacion((Date) cols[9])
                            .codigoUsuario((String) cols[10])
                            .build();

                    NivelImportancia nivelImportancia = NivelImportancia.builder()
                            .codigoNivelAviso((BigDecimal) cols[0])
                            .descripcionNivelAviso((String) cols[1])
                            .build();
                    aviso.setNivelImportancia(nivelImportancia);

                    avisos.add(aviso);
                }
            }
            return avisos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.consultaAvisosModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<NivelImportancia> consultaNivelesImportancia() throws ServiceException {
        String runSP = createCall("p_con_nivel_importancia", MDSQLConstants.CALL_03_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeNivelAviso = createCallType(MDSQLConstants.T_T_NIVEL_AVISO);
            String typeError = createCallTypeError();

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeNivelAviso);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(2);

            if (result == 0) {
                throw buildException(callableStatement.getArray(3));
            }

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
            return niveles;
        } catch (SQLException e) {
            LogWrapper.error(log, "[AvisoService.consultaNivelesImportancia] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void altaAviso(String codigoProyecto, String descripcionAviso, String txtAviso, String codNivelAviso, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_alta_avisos_modelo", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, descripcionAviso, txtAviso, codNivelAviso, codPeticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, descripcionAviso);
            callableStatement.setString(3, txtAviso);
            callableStatement.setString(4, codNivelAviso);
            callableStatement.setString(5, codPeticion);
            callableStatement.setString(6, codUsr);

            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(7);

            if (result == 0) {
                throw buildException(callableStatement.getArray(8));
            }

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.altaAviso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void modificarAviso(String codigoProyecto, BigDecimal codigoAviso, String descripcionAviso, String txtAviso, String codNivelAviso, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_mod_avisos_modelo", MDSQLConstants.CALL_10_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, codigoAviso, descripcionAviso, txtAviso, codNivelAviso, mcaHabilitado, codPeticion, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setBigDecimal(2, codigoAviso);
            callableStatement.setString(3, descripcionAviso);
            callableStatement.setString(4, txtAviso);
            callableStatement.setString(5, codNivelAviso);
            callableStatement.setString(6, mcaHabilitado);
            callableStatement.setString(7, codPeticion);
            callableStatement.setString(8, codUsr);

            callableStatement.registerOutParameter(9, Types.INTEGER);
            callableStatement.registerOutParameter(10, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(9);

            if (result == 0) {
                throw buildException(callableStatement.getArray(10));
            }

        } catch (SQLException e) {
            LogWrapper.error(log, "[HistoricoService.modificarAviso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

}
