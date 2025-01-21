package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ProgramacionModelo;
import com.mdsql.bussiness.entities.ValidacionProgramada;
import com.mdsql.bussiness.service.ValidacionService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.internal.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LVARONA
 */
@Service(MDSQLConstants.VALIDACION_SERVICE)
@Slf4j
public class ValidacionServiceImpl extends ServiceSupport implements ValidacionService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<ValidacionProgramada> validacionesProgramadas() throws ServiceException {
        String runSP = createCall("p_val_programadas", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLista = createCallType(MDSQLConstants.T_T_VAL_PROG);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeLista);

            OutputWarning result = executeStatement(callableStatement);
            OutputConsulta<ValidacionProgramada> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(fromDBListValidacionProgramada(callableStatement.getArray(1)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ValidacionService.validacionesProgramadas] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<CodigoDescripcion> consultaAcciones() throws ServiceException {
        String runSP = createCall("p_con_acciones", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLista = createCallType(MDSQLConstants.T_T_OPERACION);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeLista);

            OutputWarning result = executeStatement(callableStatement);
            OutputConsulta<CodigoDescripcion> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(fromDBListCodigoDescripcion(callableStatement.getArray(1)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ValidacionService.consultaAcciones] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning mntoProcProgramados(BigDecimal codValidacion, List<ProgramacionModelo> listaProgramacion) throws ServiceException {
        String runSP = createCall("p_mnto_proc_programados", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codValidacion, listaProgramacion);

            callableStatement.setBigDecimal(1, codValidacion);
            callableStatement.setArray(2, toDBProgramacionModelo(conn, listaProgramacion));

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[UtilsServiceImpl.mntoProcProgramados] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<ProgramacionModelo> consultaProgramacion(BigDecimal codValidacion) throws ServiceException {
        String runSP = createCall("p_con_procedimiento", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLista = createCallType(MDSQLConstants.T_T_PROC_MODELO);

            logProcedure(runSP, codValidacion);

            callableStatement.setBigDecimal(1, codValidacion);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeLista);

            OutputWarning result = executeStatement(callableStatement);
            OutputConsulta<ProgramacionModelo> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(fromDBListProgramacionModelo(callableStatement.getArray(2)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[ValidacionService.consultaProgramacion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Convierte Array BD en Lista de ProgramacionModelo
     *
     * @param arrayTipo
     * @return
     * @throws ServiceException
     */
    private List<ProgramacionModelo> fromDBListProgramacionModelo(Array arrayTipo) throws ServiceException {
        try {
            List<ProgramacionModelo> lista = new ArrayList<>();
            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    lista.add(ProgramacionModelo.builder().
                            codigoProyecto((String) cols[0]).
                            nombreModelo((String) cols[1]).
                            mcaHabilitado((String) cols[2]).
                            tipAccion((String) cols[3]).
                            codUsr((String) cols[4]).
                            fecha((Date) cols[5]).
                            build());
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Convierte Array BD en Lista de ValidacionProgramada
     *
     * @param arrayTipo
     * @return
     * @throws ServiceException
     */
    private List<ValidacionProgramada> fromDBListValidacionProgramada(Array arrayTipo) throws ServiceException {
        try {
            List<ValidacionProgramada> lista = new ArrayList<>();
            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    lista.add(ValidacionProgramada.builder().
                            codigo((BigDecimal) cols[0]).
                            nombre((String) cols[1]).
                            descripcion((String) cols[2]).
                            build());
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Dada una lista de ValidacionProgramada lo convierte a Array para pasarlo
     * a la BBDD
     *
     * @param conn
     * @param lista
     * @return
     * @throws SQLException
     */
    public Array toDBProgramacionModelo(Connection conn, List<ProgramacionModelo> lista) throws SQLException {
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        String typeTable = createCallType(MDSQLConstants.T_T_PROC_MODELO);
        String typeRecord = createCallType(MDSQLConstants.T_R_PROC_MODELO);

        Struct[] struct = null;
        if (CollectionUtils.isNotEmpty(lista)) {
            struct = new Struct[lista.size()];
            int index = 0;

            for (ProgramacionModelo data : lista) {
                struct[index++] = conn.createStruct(typeRecord,
                        new Object[]{
                            data.getCodigoProyecto(),
                            data.getNombreModelo(),
                            data.getMcaHabilitado(),
                            data.getTipAccion(),
                            data.getCodUsr(),
                            new java.sql.Date(data.getFecha().getTime())
                        });
            }
        }
        return ((OracleConnection) conn).createOracleArray(typeTable, struct);
    }
}
