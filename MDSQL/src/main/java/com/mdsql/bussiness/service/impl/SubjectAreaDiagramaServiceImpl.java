package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdsql.bussiness.service.SubjectAreaDiagramaService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LVARONA
 */
@Service(MDSQLConstants.SUBJECTAREA_DIAGRAMA_SERVICE)
@Slf4j
public class SubjectAreaDiagramaServiceImpl extends ServiceSupport implements SubjectAreaDiagramaService {

    @Autowired
    private DataSource dataSource;

    @Override
    public OutputConsulta<CodigoDescripcion> ConsultaSubjectAreasModelo(String codProyecto) throws ServiceException {
        String runSP = createCall("p_con_subjectareas_modelo", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeList = createCallType(MDSQLConstants.T_T_SUBJECT_AREA);

            logProcedure(runSP, codProyecto);

            callableStatement.setString(1, codProyecto);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeList);

            OutputWarning outputWarning = executeStatement(callableStatement);

            return fromDBListCodigoDescripcion(callableStatement.getArray(2), outputWarning);

        } catch (SQLException e) {
            LogWrapper.error(log, "[SubjectAreaDiagramaService.ConsultaSubjectAreasModelo] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning guardarSubjectArea(String codProyecto, String codigo, String Descripcion, String usuario) throws ServiceException {
        String runSP = createCall("p_alta_sa", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codigo, Descripcion, usuario);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codigo);
            callableStatement.setString(3, Descripcion);
            callableStatement.setString(4, usuario);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(
                    callableStatement.getInt(5),
                    callableStatement.getArray(6));

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<CodigoDescripcion> ConsultaDiagramas(String codProyecto, String codSubjectArea) throws ServiceException {
        String runSP = createCall("p_con_diagramas_sa", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeList = createCallType(MDSQLConstants.T_T_DIAGRAMA);

            logProcedure(runSP, codProyecto, codSubjectArea);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeList);

            OutputWarning outputWarning = executeStatement(callableStatement);

            return fromDBListCodigoDescripcion(callableStatement.getArray(3), outputWarning);

        } catch (SQLException e) {
            LogWrapper.error(log, "[SubjectAreaDiagramaService.ConsultaDiagramas] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning guardarDiagrama(
            String codProyecto, String codSubjectArea, String codigo, String Descripcion, String usuario
    ) throws ServiceException {

        String runSP = createCall("p_alta_diagrama", MDSQLConstants.CALL_07_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, codigo, Descripcion, usuario);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, codigo);
            callableStatement.setString(4, Descripcion);
            callableStatement.setString(5, usuario);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.registerOutParameter(7, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(
                    callableStatement.getInt(6),
                    callableStatement.getArray(7));

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<TablasDiagrama> consultaTablasDiagrama(String codProyecto, String codSubjectArea, String codDiagrama) throws ServiceException {
        String runSP = createCall("p_con_tablas_diagrama", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeList = createCallType(MDSQLConstants.T_T_TABLAS_DIAGRAMA);
            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, codDiagrama);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, codDiagrama);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeList);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            return trataRespuestaTablasDiagrama(
                    callableStatement.getArray(4),
                    callableStatement.getInt(5),
                    callableStatement.getArray(6)
            );
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning deshabilitaTablaDiagrama(String codProyecto, String codSubjectArea, String codDiagrama, String nomTabla, String comentario, String codPeticion, String usuario) throws ServiceException {
        String runSP = createCall("p_del_tabla_diagrama", MDSQLConstants.CALL_09_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, codDiagrama, nomTabla, comentario, codPeticion, usuario);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, codDiagrama);
            callableStatement.setString(4, nomTabla);
            callableStatement.setString(5, comentario);
            callableStatement.setString(6, codPeticion);
            callableStatement.setString(7, usuario);
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(
                    callableStatement.getInt(8),
                    callableStatement.getArray(9));

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning mantenimientoDiagrama(String codProyecto, String codSubjectArea, String desSubjectArea, String codDiagrama, String desDiagrama, String usuario) throws ServiceException {
        String runSP = createCall("p_mnto_diagrama", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, desSubjectArea, codDiagrama, desDiagrama, usuario);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, desSubjectArea);
            callableStatement.setString(4, codDiagrama);
            callableStatement.setString(5, desDiagrama);
            callableStatement.setString(6, usuario);
            callableStatement.registerOutParameter(7, Types.INTEGER);
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(
                    callableStatement.getInt(7),
                    callableStatement.getArray(8));

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<TablasDiagrama> buscaDiagramas(
            String codProyecto, String codSubjectArea, String codDiagrama, String nomTabla,
            String codPeticion, String fecDesde, String fecHasta, String mostrarInh
    ) throws ServiceException {
        String runSP = createCall("p_busca_diagramas", MDSQLConstants.CALL_11_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeList = createCallType(MDSQLConstants.T_T_TABLAS_DIAGRAMA);
            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, codDiagrama, nomTabla, codPeticion, fecDesde, fecHasta, mostrarInh);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, codDiagrama);
            callableStatement.setString(4, nomTabla);
            callableStatement.setString(5, codPeticion);
            callableStatement.setString(6, fecDesde);
            callableStatement.setString(7, fecHasta);
            callableStatement.setString(8, mostrarInh);

            callableStatement.registerOutParameter(9, Types.ARRAY, typeList);
            callableStatement.registerOutParameter(10, Types.INTEGER);
            callableStatement.registerOutParameter(11, Types.ARRAY, typeError);

            callableStatement.execute();

            return trataRespuestaTablasDiagrama(
                    callableStatement.getArray(9),
                    callableStatement.getInt(10),
                    callableStatement.getArray(11)
            );
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    private OutputConsulta<TablasDiagrama> trataRespuestaTablasDiagrama(
            Array arrayTipo, Integer result, Array arrayError
    ) throws ServiceException, SQLException {
        ServiceException errorWarning = buildException(arrayError);

        if (result == 0) {
            throw errorWarning;
        }

        List<TablasDiagrama> lista = new ArrayList<>();

        if (arrayTipo != null) {
            Object[] rows = (Object[]) arrayTipo.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                lista.add(
                        TablasDiagrama.builder().
                                codSubjectArea((String) cols[0]).
                                desSubjectArea((String) cols[1]).
                                codDiagrama((String) cols[2]).
                                desDiagrama((String) cols[3]).
                                nomTabla((String) cols[4]).
                                comentario((String) cols[5]).
                                mcaInh((String) cols[6]).
                                codPeticion((String) cols[7]).
                                codUsr((String) cols[8]).
                                fecActu((java.util.Date) cols[9]).
                                build());
            }
        }
        OutputConsulta<TablasDiagrama> output = new OutputConsulta();
        output.setLista(lista);
        output.setResult(result);
        output.setWarnings(errorWarning);
        return output;
    }

    @Override
    public OutputWarning guardaTablasDiagrama(
            String codProyecto, String codSubjectArea, String codDiagrama, String nomTabla,
            String comentario, String codPeticion, String usuario
    ) throws ServiceException {
        String runSP = createCall("p_alta_tabla_diagrama", MDSQLConstants.CALL_09_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeError = createCallTypeError();

            logProcedure(runSP, codProyecto, codSubjectArea, codDiagrama, nomTabla, comentario, codPeticion, usuario);

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubjectArea);
            callableStatement.setString(3, codDiagrama);
            callableStatement.setString(4, nomTabla);
            callableStatement.setString(5, comentario);
            callableStatement.setString(6, codPeticion);
            callableStatement.setString(7, usuario);
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.ARRAY, typeError);

            callableStatement.execute();

            return getOutputWarning(
                    callableStatement.getInt(8),
                    callableStatement.getArray(9));

        } catch (SQLException e) {
            throw new ServiceException(e);
        }

    }

}
