package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputConsultaPermisosColumna;
import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdsql.bussiness.service.PermisosColumnaService;
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
 * @author LVARONA
 */
@Service(MDSQLConstants.PERMISOS_COLUMNA_SERVICE)
@Slf4j
public class PermisosColumnaServiceImpl extends ServiceSupport implements PermisosColumnaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsultaPermisosColumna consultaPermisos(
            String p_cod_proyecto,
            String p_nom_objeto,
            String p_nom_columna,
            String p_des_entorno,
            String p_val_grant, // Permiso
            String p_cod_usr_grant, // Receptor permisos 
            String p_mca_grant_option,
            String p_mca_pdc, // Incluir en PDC
            String p_mca_habilitado
    ) throws ServiceException {
        
        String runSP = createCall("p_con_per_columna", MDSQLConstants.CALL_12_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermisoCol = createCallType("T_T_PERMISO_COL");
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto,
                    p_nom_objeto,
                    p_nom_columna,
                    p_des_entorno,
                    p_val_grant,
                    p_cod_usr_grant,
                    p_mca_grant_option,
                    p_mca_pdc,
                    p_mca_habilitado);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3, p_nom_columna);
            callableStatement.setString(4, p_des_entorno);
            callableStatement.setString(5, p_val_grant);
            callableStatement.setString(6, p_cod_usr_grant);
            callableStatement.setString(7, p_mca_grant_option);
            callableStatement.setString(8, p_mca_pdc);
            callableStatement.setString(9, p_mca_habilitado);
            callableStatement.registerOutParameter(10, Types.ARRAY, typePermisoCol);
            callableStatement.registerOutParameter(11, Types.INTEGER);
            callableStatement.registerOutParameter(12, Types.ARRAY, typeError);

            callableStatement.execute();
  
            Array arrayTipo = callableStatement.getArray(10);
            Integer result = callableStatement.getInt(11);
            Array errores = callableStatement.getArray(12);
            
            return trataRespuesta(arrayTipo, result, errores);

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosColumnaService.consultaPermisos] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsultaPermisosColumna guardarPermiso(
            PermisoColumna permisoColumna,
            String p_mca_alta
    ) throws ServiceException {
        String runSP = createCall("p_mnto_per_columna", MDSQLConstants.CALL_15_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String p_cod_proyecto = permisoColumna.getCodigoProyecto();
            String p_nom_objeto = permisoColumna.getNomObjeto();
            String p_nom_columna = permisoColumna.getNomColumna();
            String p_des_entorno = permisoColumna.getDesEntorno();
            String p_val_grant = permisoColumna.getValGrant();
            String p_cod_usr_grant = permisoColumna.getCodUsrGrant();
            String p_mca_pdc = permisoColumna.getMcaPdc();
            String p_mca_grant_option = permisoColumna.getMcaGrantOption();
            String p_mca_habilitado = permisoColumna.getMcaHabilitado();
            String p_cod_peticion = permisoColumna.getCodPeticion();
            String p_cod_usr = permisoColumna.getCodUsr();
            
            String typePermisoCol = createCallType("T_T_PERMISO_COL");
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto,
                    p_nom_objeto,
                    p_nom_columna,
                    p_des_entorno,
                    p_val_grant,
                    p_cod_usr_grant,
                    p_mca_pdc,
                    p_mca_grant_option,
                    p_mca_habilitado,
                    p_cod_peticion,
                    p_cod_usr,
                    p_mca_alta);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3, p_nom_columna);
            callableStatement.setString(4, p_des_entorno);
            callableStatement.setString(5, p_val_grant);
            callableStatement.setString(6, p_cod_usr_grant);
            callableStatement.setString(7, p_mca_pdc);
            callableStatement.setString(8, p_mca_grant_option);
            callableStatement.setString(9, p_mca_habilitado);
            callableStatement.setString(10, p_cod_peticion);
            callableStatement.setString(11, p_cod_usr);
            callableStatement.setString(12, p_mca_alta);
            callableStatement.registerOutParameter(13, Types.ARRAY, typePermisoCol);
            callableStatement.registerOutParameter(14, Types.INTEGER);
            callableStatement.registerOutParameter(15, Types.ARRAY, typeError);

            callableStatement.execute();
  
            Array arrayTipo = callableStatement.getArray(13);
            Integer result = callableStatement.getInt(14);
            Array errores = callableStatement.getArray(15);
            
            return trataRespuesta(arrayTipo, result, errores);

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosColumnaService.guardarPermiso] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    private OutputConsultaPermisosColumna trataRespuesta(Array arrayTipo, Integer result, Array errores) throws ServiceException,SQLException {
            if (result == 0) {
                throw buildException(errores);
            }
            // Recuperar los datos de Permisos
            List<PermisoColumna> permisosColumna = new ArrayList<>();

            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    PermisoColumna permisoColumna = PermisoColumna.builder()
                            .codigoProyecto((String) cols[0])
                            .codUsrGrant((String) cols[1])
                            .nomObjeto((String) cols[2])
                            .nomColumna((String) cols[3])
                            .valGrant((String) cols[4])
                            .desEntorno((String) cols[5])
                            .tipObjeto((String) cols[6])
                            .mcaGrantOption((String) cols[7])
                            .mcaPdc((String) cols[8])
                            .mcaHabilitado((String) cols[9])
                            .codPeticion((String) cols[10])
                            .codUsr((String) cols[11])
                            .fecActu((java.util.Date) cols[12])
                            .codUsrAlta((String) cols[13])
                            .fecAlta((java.util.Date) cols[14])
                            .build();
                    permisosColumna.add(permisoColumna);
                }
            }
            OutputConsultaPermisosColumna output = new OutputConsultaPermisosColumna();
            output.setPermisosColumna(permisosColumna);
            output.setResult(result);
            // Hay avisos
            if (result == 2) {
                output.setServiceException(buildException(errores));
            }
            return output;
    }
}
