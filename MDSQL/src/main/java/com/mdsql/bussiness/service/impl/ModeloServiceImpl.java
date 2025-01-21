package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.*;
import com.mdsql.bussiness.service.ModeloService;
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
import java.util.Objects;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hcarreno
 */
@Service(MDSQLConstants.MODELO_SERVICE)
@Slf4j
public class ModeloServiceImpl extends ServiceSupport implements ModeloService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<Modelo> consultaModelos(String codigoProyecto, String nombreModelo,
            String codigoSubProyecto) throws ServiceException {
        String runSP = createCall("p_con_modelos", 6);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeModelo = createCallType(MDSQLConstants.T_T_MODELO);

            logProcedure(runSP, codigoProyecto, nombreModelo, codigoSubProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, nombreModelo);
            callableStatement.setString(3, codigoSubProyecto);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeModelo);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Modelo> outputConsultaModelos = new OutputConsulta<>();
            outputConsultaModelos.setOutputWarning(result);

            List<Modelo> modelos = new ArrayList<>();
            Array arrayModelos = callableStatement.getArray(4);

            if (arrayModelos != null) {
                Object[] rows = (Object[]) arrayModelos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Modelo modelo = Modelo.builder()
                            .codigoProyecto((String) cols[0])
                            .nombreModelo((String) cols[1])
                            .nombreEsquema((String) cols[2])
                            .nombreBbdd((String) cols[3])
                            .nombreCarpetaAdj((String) cols[4])
                            .codigoCapaUsrown((String) cols[5])
                            .mcaVariables((String) cols[6])
                            .mcaGrantAll((String) cols[7])
                            .mcaGrantPublic((String) cols[8])
                            .mcaInh((String) cols[9])
                            .observaciones((String) cols[10])
                            .entregaPDC((String) cols[11])
                            .mcaHis((String) cols[12])
                            .build();

                    // Lista de subproyectos
                    fillSubproyectos(modelo, cols);

                    modelos.add(modelo);
                }

                outputConsultaModelos.setLista(modelos);
            }

            return outputConsultaModelos;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ModeloService.consultaModelos] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<Variable> consultaVariables(Modelo modelo) throws ServiceException {
        String runSP = createCall("p_con_vbles_modelo", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeVariable = createCallType(MDSQLConstants.T_T_VARIABLE);

            String codigoProyecto = modelo.getCodigoProyecto();
            logProcedure(runSP, codigoProyecto);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeVariable);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Variable> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<Variable> variables = new ArrayList<>();
            Array arrayVariables = callableStatement.getArray(2);

            if (arrayVariables != null) {
                Object[] rows = (Object[]) arrayVariables.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    Variable variable = Variable.builder()
                            .codigoProyecto((String) cols[0])
                            .codigoVariable((String) cols[1])
                            .entorno((String) cols[2])
                            .bbdd((String) cols[3])
                            .tipo((String) cols[4])
                            .valor((String) cols[5])
                            .valorSustituir((String) cols[6])
                            .peticion((String) cols[7])
                            .usoInterno((String) cols[8])
                            .habilitada((String) cols[9])
                            .comentario((String) cols[10])
                            .usrModificacion((String) cols[11])
                            .fechaModificacion((java.util.Date) cols[12])
                            .usrAlta((String) cols[13])
                            .fechaAlta((java.util.Date) cols[14])
                            .mcaExcepcion((String) cols[15])
                            .build();

                    variables.add(variable);
                }
                output.setLista(variables);
            }

            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[ModeloService.consultaVariables] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning actualizarVariableModelo(String codigoProyecto, String codigoVariable, String entorno, String bbdd,
            String tipoVariable, String valorVariable, String valorSustituir, String codigoPeticion,
            String mcaInterno, String mcaHabilitado, String mcaExcepcion, String comentario, String codUsr
    ) throws ServiceException {

        String runSP = createCall("p_mnto_vbles_modelo", 15);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP, codigoProyecto, codigoVariable, entorno, bbdd, tipoVariable, valorVariable,
                    valorSustituir, codigoPeticion, mcaInterno, mcaHabilitado, mcaExcepcion, comentario, codUsr);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, codigoVariable);
            callableStatement.setString(3, entorno);
            callableStatement.setString(4, bbdd);
            callableStatement.setString(5, tipoVariable);
            callableStatement.setString(6, valorVariable);
            callableStatement.setString(7, valorSustituir);
            callableStatement.setString(8, codigoPeticion);
            callableStatement.setString(9, mcaInterno);
            callableStatement.setString(10, mcaHabilitado);
            callableStatement.setString(11, mcaExcepcion);
            callableStatement.setString(12, comentario);
            callableStatement.setString(13, codUsr);

            return executeStatement(callableStatement);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ModeloService.actualizarVariableModelo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * @param modelo
     * @param arraySubProyectos
     * @throws SQLException
     */
    private void fillSubproyectos(Modelo modelo, Object[] cols) throws SQLException {
        try {
            Array arraySubProyectos = (Array) cols[13];
            if (!Objects.isNull(arraySubProyectos)) {
                List<SubProyecto> subProyectos = new ArrayList<>();
                Object[] subs = (Object[]) arraySubProyectos.getArray();
                for (Object sub : subs) {
                    Object[] sub_cols = ((oracle.jdbc.OracleStruct) sub).getAttributes();

                    SubProyecto subProyecto = SubProyecto.builder()
                            .codigoSubProyecto((String) sub_cols[0])
                            .descripcionSubProyecto((String) sub_cols[1])
                            .build();
                    subProyectos.add(subProyecto);
                }

                modelo.setSubproyectos(subProyectos);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ModeloService.fillSubproyectos] Error: %s", e.getMessage());
        }
    }
}
