package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.OutputConfirmaCierre;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputConsultaEntrega;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ProcesadoPeticion;
import com.mdsql.bussiness.service.EntregaService;
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
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.ENTREGA_SERVICE)
@Slf4j
public class EntregaServiceImpl extends ServiceSupport implements EntregaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsultaEntrega consultaRutaEntrega(String codigoProyecto, BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_ruta_entrega", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, idProceso);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setBigDecimal(2, idProceso);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            String txtRutaEntrega = callableStatement.getString(3);
            String nombreFicheroVigente = callableStatement.getString(4);
            String nombreFicheroHistorico = callableStatement.getString(5);
            String nombreFicheroType = callableStatement.getString(6);

            OutputConsultaEntrega output = new OutputConsultaEntrega();
            output.setOutputWarning(result);
            output.setTxtRutaEntrega(txtRutaEntrega);
            output.setNombreFicheroVigente(nombreFicheroVigente);
            output.setNombreFicheroHistorico(nombreFicheroHistorico);
            output.setNombreFicheroType(nombreFicheroType);

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.consultaRutaEntrega] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputValor<String> entregarPeticion(BigDecimal idProceso, String codigoUsuario, String comentario, String versionErwin) throws ServiceException {
        String runSP = createCall("p_entregar_peticion", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, idProceso, codigoUsuario, comentario, versionErwin);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setString(2, codigoUsuario);
            callableStatement.setString(3, comentario);
            callableStatement.setString(4, versionErwin);

            callableStatement.registerOutParameter(5, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputValor<String> output = new OutputValor();
            output.setResult(result.getResult());
            output.setWarnings(result.getWarnings());
            output.setValor(callableStatement.getString(5));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.entregarPeticion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<ProcesadoPeticion> consultaProcesadosPeticion(String codPeticion) throws ServiceException {
        String runSP = createCall("p_con_procesado_peti", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeProcPeti = createCallType(MDSQLConstants.T_T_PROC_PETI);

            logProcedure(runSP, codPeticion);

            callableStatement.setString(1, codPeticion);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeProcPeti);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<ProcesadoPeticion> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(fromDBListProcesadoPeticion(callableStatement.getArray(2)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.consultaProcesadosPeticion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Convierte un Objeto Array de BBDD en un objeto de tipo ProcesadoPeticion
     *
     * @param array
     * @return
     * @throws SQLException
     */
    private ProcesadoPeticion fromDBProcesadoPeticion(Object array) throws SQLException {
        Object[] cols = ((oracle.jdbc.OracleStruct) array).getAttributes();

        return ProcesadoPeticion.builder()
                .idProceso((BigDecimal) cols[0])
                .nomTabla((String) cols[1])
                .descripcion((String) cols[2])
                .codigoEstadoProceso((BigDecimal) cols[3])
                .descripcionEstadoProceso((String) cols[4])
                .codUsr((String) cols[5])
                .fecha((Date) cols[6])
                .mcaRechazar((String) cols[7])
                .mcaExcluir((String) cols[8])
                .erwin((String) cols[9])
                .build();
    }

    /**
     * Convierte un Array de BBDD una lista de ProcesadoPeticion
     *
     * @param array
     * @return
     * @throws SQLException
     */
    public List<ProcesadoPeticion> fromDBListProcesadoPeticion(Array array) throws SQLException {

        List<ProcesadoPeticion> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {

                lista.add(fromDBProcesadoPeticion(row));
            }
        }
        return lista;
    }

    @Override
    public OutputFicherosPeticion prepararCierre(String codPeticion) throws ServiceException {
        String runSP = createCall("p_preparar_cierre", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeFicheroAccion = createCallType(MDSQLConstants.T_T_FICHERO_ACCION);
            String typeFicheroZip = createCallType(MDSQLConstants.T_T_FICHERO_ZIP);

            logProcedure(runSP, codPeticion);

            callableStatement.setString(1, codPeticion);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeFicheroAccion);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeFicheroZip);

            OutputWarning result = executeStatement(callableStatement);

            OutputFicherosPeticion output = new OutputFicherosPeticion();
            output.setOutputWarning(result);
            output.setFicherosAccion(fromDBListFicheroAccion(callableStatement.getArray(2)));
            output.setFicherosZip(fromDBListFicheroZip(callableStatement.getArray(3)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.prepararCierre] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConfirmaCierre confirmaCierre(String codPeticion, String descripcion, String versionado, String Erwin) throws ServiceException {
        String runSP = createCall("p_confirma_cierre", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codPeticion, descripcion, versionado, Erwin);

            callableStatement.setString(1, codPeticion);
            callableStatement.setString(2, descripcion);
            callableStatement.setString(3, versionado);
            callableStatement.setString(4, Erwin);
            callableStatement.registerOutParameter(5, Types.VARCHAR);

            OutputWarning result = executeStatement(callableStatement);

            OutputConfirmaCierre output = new OutputConfirmaCierre();
            output.setOutputWarning(result);
            output.setMcaInformeTRN(callableStatement.getString(5));

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[EntregaService.confirmaCierre] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }
}
