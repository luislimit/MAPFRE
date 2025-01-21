package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputProcesa;
import com.mdsql.bussiness.entities.OutputProcesaScriptInicial;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.EntornosPruebaService;
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

@Service(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE)
@Slf4j
public class EntornosPruebaServiceImpl extends ServiceSupportScript implements EntornosPruebaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<EntornoPrueba> consultarEntornos() throws ServiceException {
        String runSP = createCall("p_con_entorno_pruebas", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeEntorno = createCallType(MDSQLConstants.T_T_ENTORNO_PRUEBA);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeEntorno);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<EntornoPrueba> output = new OutputConsulta();
            output.setOutputWarning(result);

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
                            .gradoParal((BigDecimal) cols[5])
                            .mcaHabilitado((String) cols[6])
                            .build();

                    entornos.add(entorno);
                }
                output.setLista(entornos);
            }
            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.consultarEntornos] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning guardarEntorno(EntornoPrueba entornoPrueba, String codUsr)
            throws ServiceException {
        String runSP = createCall("p_mnto_entorno_prueba", 10);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, entornoPrueba.getNombreEntorno(), entornoPrueba.getBbdd(),
                    entornoPrueba.getEsquema(), entornoPrueba.getDescripcion(),
                    entornoPrueba.getTablespace(), entornoPrueba.getGradoParal(),
                    entornoPrueba.getMcaHabilitado(), codUsr);

            callableStatement.setString(1, entornoPrueba.getNombreEntorno());
            callableStatement.setString(2, entornoPrueba.getBbdd());
            callableStatement.setString(3, entornoPrueba.getEsquema());
            callableStatement.setString(4, entornoPrueba.getDescripcion());
            callableStatement.setString(5, entornoPrueba.getTablespace());
            callableStatement.setBigDecimal(6, entornoPrueba.getGradoParal());
            callableStatement.setString(7, entornoPrueba.getMcaHabilitado());
            callableStatement.setString(8, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.guardarEntorno] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputProcesaScriptInicial procesaScriptInicial(
            List<TextoLinea> p_script,
            String p_cod_proyecto,
            String p_cod_sub_proy,
            String p_cod_peticion,
            String p_cod_demanda,
            String p_cod_usr,
            String p_cod_usr_peticion,
            String p_nom_fich_entrada,
            String p_txt_ruta_entrada,
            String p_nom_entorno,
            String p_mca_drop) throws ServiceException {

        String runSP = createCall("p_procesa_script_inicial", 21);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String typeScript = createCallType(MDSQLConstants.T_T_SCRIPT);

            logProcedure(runSP, p_script, p_cod_proyecto, p_cod_sub_proy,
                    p_cod_peticion, p_cod_demanda, p_cod_usr, p_cod_usr_peticion,
                    p_nom_fich_entrada, p_txt_ruta_entrada, p_nom_entorno, p_mca_drop);

            Array arrayLineas = toDBListTextoLinea(conn, p_script);

            callableStatement.setArray(1, arrayLineas);
            callableStatement.setString(2, p_cod_proyecto);
            callableStatement.setString(3, p_cod_sub_proy);
            callableStatement.setString(4, p_cod_peticion);
            callableStatement.setString(5, p_cod_demanda);
            callableStatement.setString(6, p_cod_usr);
            callableStatement.setString(7, p_cod_usr_peticion);
            callableStatement.setString(8, p_nom_fich_entrada);
            callableStatement.setString(9, p_txt_ruta_entrada);
            callableStatement.setString(10, p_nom_entorno);
            callableStatement.setString(11, p_mca_drop);
            // Variables de retorno
            callableStatement.registerOutParameter(12, Types.INTEGER); //p_id_proceso
            callableStatement.registerOutParameter(13, Types.DATE);    //p_fec_proceso
            callableStatement.registerOutParameter(14, Types.VARCHAR); // p_nom_script_lanza
            callableStatement.registerOutParameter(15, Types.ARRAY, typeLinea); //p_script_lanza
            callableStatement.registerOutParameter(16, Types.VARCHAR); // p_nom_script_log
            callableStatement.registerOutParameter(17, Types.INTEGER); // p_cod_estado_proc
            callableStatement.registerOutParameter(18, Types.VARCHAR); // p_des_estado_proc
            callableStatement.registerOutParameter(19, Types.ARRAY, typeScript); //p_lista_scripts
            callableStatement.execute();

            OutputWarning result = executeStatement(callableStatement);

            OutputProcesaScriptInicial output = new OutputProcesaScriptInicial();
            output.setOutputWarning(result);
            output.setIdProceso(callableStatement.getBigDecimal(12));
            output.setFechaProceso(callableStatement.getDate(13));
            output.setNombreScriptLanza(callableStatement.getString(14));
            output.setScriptLanza(fromDBListTextoLinea(callableStatement.getArray(15)));
            output.setNombreScriptLog(callableStatement.getString(16));
            output.setCodigoEstadoProceso(callableStatement.getBigDecimal(17));
            output.setDescripcionEstadoProceso(callableStatement.getString(18));
            output.setListaScripts(fromDBListScript(callableStatement.getArray(19)));

            return output;

        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.procesaScriptInicial] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputProcesa registraScriptInicial(BigDecimal idProceso, String codUsr, List<TextoLinea> logScript)
            throws ServiceException {
        String runSP = createCall("p_registra_script_inicial", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, codUsr, logScript);

            Array arrayLogScript = toDBListTextoLinea(conn, logScript);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, codUsr);
            callableStatement.setArray(3, arrayLogScript);
            // Variables de retorno
            callableStatement.registerOutParameter(4, Types.INTEGER); // p_cod_estado_proc
            callableStatement.registerOutParameter(5, Types.VARCHAR); // p_des_estado_proc

            OutputWarning result = executeStatement(callableStatement);

            OutputProcesa output = new OutputProcesa();
            output.setOutputWarning(result);
            output.setIdProceso(idProceso);
            output.setCodigoEstadoProceso(callableStatement.getBigDecimal(4));
            output.setDescripcionEstadoProceso(callableStatement.getString(5));

            return output;

        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[EntornosPruebaService.registraScriptInicial] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
