package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Permiso;
import com.mdsql.bussiness.entities.Sinonimo;
import com.mdsql.bussiness.service.PermisosService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service(MDSQLConstants.PERMISOS_SERVICE)
@Slf4j
public class PermisosServiceImpl extends ServiceSupport implements PermisosService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Permiso> consultaPermisosGenerales(Modelo modelo) throws ServiceException {
        String runSP = createCall("p_con_per_generales", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermiso = createCallType(MDSQLConstants.T_T_PERMISO_GEN);
            String typeError = createCallTypeError();

            String codigoProyecto = modelo.getCodigoProyecto();
            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typePermiso);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<Permiso> permisos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(2);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Permiso permiso = Permiso.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .valGrant((String) cols[2])
                            .desEntorno((String) cols[3])
                            .tipObjeto((String) cols[4])
                            .mcaGrantOption((String) cols[5])
                            .mcaPdc((String) cols[6])
                            .mcaHabilitado((String) cols[7])
                            .codPeticion((String) cols[8])
                            .codUsr((String) cols[9])
                            .fecActu((java.util.Date) cols[10])
                            .codUsrAlta((String) cols[11])
                            .fecAlta((java.util.Date) cols[12])
                            .build();
                    permisos.add(permiso);
                }
            }
            return permisos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosService.consultaPermisosGenerales] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Sinonimo> consultaSinonimosGenerales(Modelo modelo) throws ServiceException {
        String runSP = createCall("p_con_syn_generales", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeSinonimo = createCallType(MDSQLConstants.T_T_SINONIMO_GEN);
            String typeError = createCallTypeError();

            String codigoProyecto = modelo.getCodigoProyecto();
            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeSinonimo);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<Sinonimo> sinonimos = new ArrayList<>();
            Array arraySinonimos = callableStatement.getArray(2);

            if (arraySinonimos != null) {
                Object[] rows = (Object[]) arraySinonimos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Sinonimo sinonimo = Sinonimo.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .codOwnerSyn((String) cols[2])
                            .desEntorno((String) cols[3])
                            .tipObjeto((String) cols[4])
                            .valReglaSyn((String) cols[5])
                            .mcaPdc((String) cols[6])
                            .mcaHabilitado((String) cols[7])
                            .codPeticion((String) cols[8])
                            .codUsr((String) cols[9])
                            .fecActu((java.util.Date) cols[10])
                            .codUsrAlta((String) cols[11])
                            .fecAlta((java.util.Date) cols[12])
                            .build();
                    sinonimos.add(sinonimo);
                }
            }
            return sinonimos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosService.consultaSinonimosGenerales] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> consultarPermisosPorTipoObjeto(String tipoObjeto) throws ServiceException {
        String runSP = createCall("p_con_per_tipo_obj", MDSQLConstants.CALL_04_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermiso = createCallType(MDSQLConstants.T_T_PERMISO);
            String typeError = createCallTypeError();

            logProcedure(runSP, tipoObjeto);

            callableStatement.setString(1, tipoObjeto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typePermiso);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(3);

            if (result == 0) {
                throw buildException(callableStatement.getArray(4));
            }

            List<String> tipos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(2);

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
            LogWrapper.error(log, "[PermisosService.consultarPermisosPorTipoObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Permiso> guardarPermiso(String codProyecto, String codUsrGrant, String valGrant, String desEntorno, String tipoObjeto, String mcaGrantOption, String mcaIncluirPDC, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_mnto_per_general", MDSQLConstants.CALL_13_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermiso = createCallType(MDSQLConstants.T_T_PERMISO_GEN);
            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codUsrGrant, valGrant, desEntorno, tipoObjeto, mcaGrantOption, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codUsrGrant);
            callableStatement.setString(3, valGrant);
            callableStatement.setString(4, desEntorno);
            callableStatement.setString(5, tipoObjeto);
            callableStatement.setString(6, mcaGrantOption);
            callableStatement.setString(7, mcaIncluirPDC);
            callableStatement.setString(8, mcaHabilitado);
            callableStatement.setString(9, codPeticion);
            callableStatement.setString(10, codUsr);

            callableStatement.registerOutParameter(11, Types.ARRAY, typePermiso);
            callableStatement.registerOutParameter(12, Types.INTEGER);
            callableStatement.registerOutParameter(13, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(12);

            if (result == 0) {
                throw buildException(callableStatement.getArray(13));
            }

            List<Permiso> permisos = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(11);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Permiso permiso = Permiso.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .valGrant((String) cols[2])
                            .desEntorno((String) cols[3])
                            .tipObjeto((String) cols[4])
                            .mcaGrantOption((String) cols[5])
                            .mcaPdc((String) cols[6])
                            .mcaHabilitado((String) cols[7])
                            .codPeticion((String) cols[8])
                            .codUsr((String) cols[9])
                            .fecActu((java.util.Date) cols[10])
                            .codUsrAlta((String) cols[11])
                            .fecAlta((java.util.Date) cols[12])
                            .build();
                    permisos.add(permiso);
                }
            }
            return permisos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosService.guardarPermiso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Sinonimo> guardarSinonimo(String codProyecto, String codUsrGrant, String codOwnerSyn, String desEntorno, String tipoObjeto, String funcionNombre, String mcaIncluirPDC, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException {
        String runSP = createCall("p_mnto_syn_general", MDSQLConstants.CALL_13_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeSinonimo = createCallType(MDSQLConstants.T_T_SINONIMO_GEN);
            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codUsrGrant, codOwnerSyn, desEntorno, tipoObjeto, funcionNombre, mcaIncluirPDC, mcaHabilitado, codPeticion, codUsr);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codUsrGrant);
            callableStatement.setString(3, codOwnerSyn);
            callableStatement.setString(4, desEntorno);
            callableStatement.setString(5, tipoObjeto);
            callableStatement.setString(6, funcionNombre);
            callableStatement.setString(7, mcaIncluirPDC);
            callableStatement.setString(8, mcaHabilitado);
            callableStatement.setString(9, codPeticion);
            callableStatement.setString(10, codUsr);

            callableStatement.registerOutParameter(11, Types.ARRAY, typeSinonimo);
            callableStatement.registerOutParameter(12, Types.INTEGER);
            callableStatement.registerOutParameter(13, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(12);

            if (result == 0) {
                throw buildException(callableStatement.getArray(13));
            }

            List<Sinonimo> sinonimos = new ArrayList<>();
            Array arraySinonimos = callableStatement.getArray(11);

            if (arraySinonimos != null) {
                Object[] rows = (Object[]) arraySinonimos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    Sinonimo sinonimo = Sinonimo.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .codOwnerSyn((String) cols[2])
                            .desEntorno((String) cols[3])
                            .tipObjeto((String) cols[4])
                            .valReglaSyn((String) cols[5])
                            .mcaPdc((String) cols[6])
                            .mcaHabilitado((String) cols[7])
                            .codPeticion((String) cols[8])
                            .codUsr((String) cols[9])
                            .fecActu((java.util.Date) cols[10])
                            .codUsrAlta((String) cols[11])
                            .fecAlta((java.util.Date) cols[12])
                            .build();
                    sinonimos.add(sinonimo);
                }
            }
            return sinonimos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosService.guardarSinonimo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
