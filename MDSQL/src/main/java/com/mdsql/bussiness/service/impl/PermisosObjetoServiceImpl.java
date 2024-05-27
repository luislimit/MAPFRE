package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputConsultaPermisosSinonimos;
import com.mdsql.bussiness.entities.PermisoObjeto;
import com.mdsql.bussiness.entities.SinonimoObjeto;
import com.mdsql.bussiness.service.PermisosObjetoService;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Results;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service(MDSQLConstants.PERMISOS_OBJETO_SERVICE)
@Slf4j
public class PermisosObjetoServiceImpl extends ServiceSupport implements PermisosObjetoService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsultaPermisosSinonimos consultaPermisosSinonimos(
                        String p_cod_proyecto,
                        String p_nom_objeto,
                        String p_tip_objeto,
                        String p_txt_per_syn, // Permiso/Sinonimo
                        String p_des_entorno,
                        String p_cod_owner_syn, // Propietario sinonimo
                        String p_val_regla_syn, // Funcion nombre
                        String p_val_grant, // Permiso
                        String p_cod_usr_grant, // Receptor permisos 
                        String p_mca_PDC, // Incluir en PDC
                        String p_mca_grant_option,
                        String p_mca_habilitado
    ) throws ServiceException{
        
        String runSP = createCall("p_buscar_per_syn", MDSQLConstants.CALL_16_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermisoObj = createCallType("T_T_PERMISO_OBJ");
            String typeSinonimoObj = createCallType("T_T_SINONIMO_OBJ");
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto,
                                p_nom_objeto,
                                p_tip_objeto,
                                p_txt_per_syn,
                                p_des_entorno,
                                p_cod_owner_syn,
                                p_val_regla_syn,
                                p_val_grant,
                                p_cod_usr_grant,
                                p_mca_PDC,
                                p_mca_grant_option,
                                p_mca_habilitado);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3,  p_tip_objeto);
            callableStatement.setString(4,  p_txt_per_syn);
            callableStatement.setString(5,  p_des_entorno);
            callableStatement.setString(6,  p_cod_owner_syn);
            callableStatement.setString(7,  p_val_regla_syn);
            callableStatement.setString(8,  p_val_grant);
            callableStatement.setString(9,  p_cod_usr_grant);
            callableStatement.setString(10,  p_mca_PDC);
            callableStatement.setString(11,  p_mca_grant_option);
            callableStatement.setString(12,  p_mca_habilitado);
            callableStatement.registerOutParameter(13, Types.ARRAY, typePermisoObj);
            callableStatement.registerOutParameter(14, Types.ARRAY, typeSinonimoObj);
            callableStatement.registerOutParameter(15, Types.INTEGER);
            callableStatement.registerOutParameter(16, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(15);

            if (result.equals(Results.ERROR.getNum())) {
                throw buildException(callableStatement.getArray(16));
            }
            // Recuperar los datos de Permisos
            List<PermisoObjeto> permisosObjeto = new ArrayList<>();
            Array arrayTipo = callableStatement.getArray(13);

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    PermisoObjeto permisoObjeto = PermisoObjeto.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .nomObjeto((String) cols[2])
                            .valGrant((String) cols[3])
                            .desEntorno((String) cols[4])
                            .tipObjeto((String) cols[5])
                            .mcaGrantOption((String) cols[6])
                            .mcaPdc((String) cols[7])
                            .mcaHabilitado((String) cols[8])
                            .codPeticion((String) cols[9])
                            .codUsr((String) cols[10])
                            .fecActu((java.util.Date) cols[11])
                            .codUsrAlta((String) cols[12])
                            .fecAlta((java.util.Date) cols[13])
                            .build();
                    permisosObjeto.add(permisoObjeto);
                }
            }
            //
            List<SinonimoObjeto> sinonimosObjeto = new ArrayList<>();
            Array arraySinonimos = callableStatement.getArray(14);

            if (arraySinonimos != null) {
                Object[] rows = (Object[]) arraySinonimos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    SinonimoObjeto sinonimoObjeto = SinonimoObjeto.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .nomObjeto((String) cols[2])
                            .codOwnerSyn((String) cols[3])
                            .desEntorno((String) cols[4])
                            .tipObjeto((String) cols[5])
                            .valReglaSyn((String) cols[6])
                            .mcaPdc((String) cols[7])
                            .mcaHabilitado((String) cols[8])
                            .codPeticion((String) cols[9])
                            .codUsr((String) cols[10])
                            .fecActu((java.util.Date) cols[11])
                            .codUsrAlta((String) cols[12])
                            .fecAlta((java.util.Date) cols[13])
                            .build();
                    sinonimosObjeto.add(sinonimoObjeto);
                }
            }            
            OutputConsultaPermisosSinonimos output = new OutputConsultaPermisosSinonimos();
            output.setPermisosObjeto(permisosObjeto);
            output.setSinonimosObjeto(sinonimosObjeto);
            output.setResult(result);
            // Hay avisos
	    if (result == 2) {
                output.setServiceException(buildException(callableStatement.getArray(16)));
            }
            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosObjetoService.consultaPermisosSinonimos] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
    
    @Override
    public ServiceException guardarPermisoObjeto(
                        PermisoObjeto permisoObjeto,
                        String p_mca_alta
    ) throws ServiceException{
        String runSP = createCall("p_mnto_per_objeto", MDSQLConstants.CALL_14_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String p_cod_proyecto = permisoObjeto.getCodigoProyecto();
            String p_nom_objeto = permisoObjeto.getNomObjeto();
            String p_cod_usr_grant = permisoObjeto.getCodUsrGrant();
            String p_val_grant = permisoObjeto.getValGrant();
            String p_des_entorno = permisoObjeto.getDesEntorno();
            String p_tip_objeto = permisoObjeto.getTipObjeto();
            String p_mca_grant_option = permisoObjeto.getMcaGrantOption();
            String p_mca_pdc = permisoObjeto.getMcaPdc();
            String p_mca_habilitado = permisoObjeto.getMcaHabilitado();
            String p_cod_peticion = permisoObjeto.getCodPeticion();
            String p_cod_usr = permisoObjeto.getCodUsr();
                        
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto
                                ,p_nom_objeto
                                ,p_cod_usr_grant
                                ,p_val_grant
                                ,p_des_entorno
                                ,p_tip_objeto
                                ,p_mca_grant_option
                                ,p_mca_pdc
                                ,p_mca_habilitado
                                ,p_cod_peticion
                                ,p_cod_usr
                                ,p_mca_alta);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3, p_cod_usr_grant);
            callableStatement.setString(4, p_val_grant);
            callableStatement.setString(5, p_des_entorno);
            callableStatement.setString(6, p_tip_objeto);
            callableStatement.setString(7, p_mca_grant_option);
            callableStatement.setString(8, p_mca_pdc);
            callableStatement.setString(9, p_mca_habilitado);
            callableStatement.setString(10, p_cod_peticion);
            callableStatement.setString(11, p_cod_usr);
            callableStatement.setString(12, p_mca_alta);
            callableStatement.registerOutParameter(13, Types.INTEGER);
            callableStatement.registerOutParameter(14, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(13);

            ServiceException errorOrWarning = null;
            if (!result.equals(Results.OK.getNum())){
                errorOrWarning = buildException(callableStatement.getArray(14));
                if (result.equals(Results.ERROR.getNum())){
                    throw errorOrWarning;
                }
            }
           return errorOrWarning;

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosObjetoService.guardarPermisoObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }        
    }

    @Override
    public ServiceException guardarSinonimoObjeto(
                        SinonimoObjeto sinonimoObjeto,
                        String p_mca_alta
    ) throws ServiceException{
        String runSP = createCall("p_mnto_syn_objeto", MDSQLConstants.CALL_14_ARGS);

        try (Connection conn = dataSource.getConnection();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String p_cod_proyecto = sinonimoObjeto.getCodigoProyecto();
            String p_nom_objeto = sinonimoObjeto.getNomObjeto();
            String p_cod_usr_grant = sinonimoObjeto.getCodUsrGrant();
            String p_cod_owner_syn = sinonimoObjeto.getCodOwnerSyn();
            String p_des_entorno = sinonimoObjeto.getDesEntorno();
            String p_tip_objeto = sinonimoObjeto.getTipObjeto();
            String p_val_regla_syn = sinonimoObjeto.getValReglaSyn();
            String p_mca_pdc = sinonimoObjeto.getMcaPdc();
            String p_mca_habilitado = sinonimoObjeto.getMcaHabilitado();
            String p_cod_peticion = sinonimoObjeto.getCodPeticion();
            String p_cod_usr = sinonimoObjeto.getCodUsr();
                        
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto
                                ,p_nom_objeto
                                ,p_cod_usr_grant
                                ,p_cod_owner_syn
                                ,p_des_entorno
                                ,p_tip_objeto
                                ,p_val_regla_syn
                                ,p_mca_pdc
                                ,p_mca_habilitado
                                ,p_cod_peticion
                                ,p_cod_usr
                                ,p_mca_alta);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3, p_cod_usr_grant);
            callableStatement.setString(4, p_cod_owner_syn);
            callableStatement.setString(5, p_des_entorno);
            callableStatement.setString(6, p_tip_objeto);
            callableStatement.setString(7, p_val_regla_syn);
            callableStatement.setString(8, p_mca_pdc);
            callableStatement.setString(9, p_mca_habilitado);
            callableStatement.setString(10, p_cod_peticion);
            callableStatement.setString(11, p_cod_usr);
            callableStatement.setString(12, p_mca_alta);
            callableStatement.registerOutParameter(13, Types.INTEGER);
            callableStatement.registerOutParameter(14, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(13);

            ServiceException errorOrWarning = null;
            if (!result.equals(Results.OK.getNum())){
                errorOrWarning = buildException(callableStatement.getArray(14));
                if (result.equals(Results.ERROR.getNum())){
                    throw errorOrWarning;
                }
            }
           return errorOrWarning;   

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosObjetoService.guardarSinonimoObjeto] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }        
        
    }
    
}
