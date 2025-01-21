/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Lanza;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputDatosReentrante;
import com.mdsql.bussiness.entities.OutputProcesaReentrante;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ReentranteComentarioColumna;
import com.mdsql.bussiness.entities.ReentranteDato;
import com.mdsql.bussiness.entities.ReentranteInfo;
import com.mdsql.bussiness.entities.ReentranteParametro;
import com.mdsql.bussiness.entities.ScriptInfo;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.ReentranteService;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(MDSQLConstants.REENTRANTE_SERVICE)
@Slf4j
public class ReentranteServiceImpl extends ServiceSupportScript implements ReentranteService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BBDDService bbddService;

    /**
     * Devuelve la lista de tipologías de scripts reentrantes
     *
     * @return
     * @throws ServiceException
     */
    @Override
    public OutputConsulta<CodigoDescripcion> consultaTipoReentrante() throws ServiceException {

        String runSP = createCall("p_con_tipo_reentrante", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeResult = createCallType(MDSQLConstants.T_T_TIPO_REENTRANTE);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeResult);

            OutputWarning outputWarning = executeStatement(callableStatement);

            return fromDBListCodigoDescripcion(callableStatement.getArray(1), outputWarning);

        } catch (SQLException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.consultaTipoReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<ReentranteParametro> consultaDatosReentrante(BigDecimal idProceso) throws ServiceException {
        String runSP = createCall("p_con_datos_reentrante", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTableDatos = createCallType(MDSQLConstants.T_T_DATOS_REENTRANTE);

            logProcedure(runSP, idProceso);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeTableDatos);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<ReentranteParametro> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<ReentranteParametro> listDatos = new ArrayList<>();
            Array arrDatos = callableStatement.getArray(2);

            if (arrDatos != null) {
                Object[] rows = (Object[]) arrDatos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    ReentranteParametro dato = ReentranteParametro.builder().
                            numeroParam((BigDecimal) cols[0]).
                            nombreParam((String) cols[1]).
                            valorParam((String) cols[2]).
                            build();

                    listDatos.add(dato);
                }

                output.setLista(listDatos);
            }
            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.consultaDatosReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<ReentranteParametro> consultaParametroReentrante(String tipoReentrante) throws ServiceException {
        String runSP = createCall("p_con_param_reentrante", 4);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeResult = createCallType(MDSQLConstants.T_T_PARAM_REENTRANTE);

            logProcedure(runSP, tipoReentrante);

            callableStatement.setString(1, tipoReentrante);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeResult);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<ReentranteParametro> output = new OutputConsulta<>();
            output.setOutputWarning(result);

            List<ReentranteParametro> listDatos = new ArrayList<>();
            Array arrDatos = callableStatement.getArray(2);

            if (arrDatos != null) {
                Object[] rows = (Object[]) arrDatos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    ReentranteParametro dato = ReentranteParametro.builder().
                            tipoParam((String) cols[0]).
                            numeroParam((BigDecimal) cols[1]).
                            nombreParam((String) cols[2]).
                            textoParam((String) cols[3]).
                            build();

                    listDatos.add(dato);
                }

                output.setLista(listDatos);
            }
            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.consultaParametroReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputDatosReentrante datosReentrante() throws ServiceException {
        String runSP = createCall("p_datos_reentrante", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.VARCHAR); //Proyecto
            callableStatement.registerOutParameter(2, Types.VARCHAR); //SubProyecto
            callableStatement.registerOutParameter(3, Types.VARCHAR); //Nombre BBDD

            OutputWarning result = executeStatement(callableStatement);

            OutputDatosReentrante output = new OutputDatosReentrante();
            output.setCodProyecto(callableStatement.getString(1));
            output.setCodSubProyecto(callableStatement.getString(2));
            output.setNombreBBDD(callableStatement.getString(3));
            output.setOutputWarning(result);

            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.datosReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<ReentranteComentarioColumna> procesaComentarioReentrantes(
            String nombreTabla,
            String comentarioES,
            String comentarioEN,
            List<TextoLinea> fichero) throws ServiceException {

        String runSP = createCall("p_procesa_cmt_reentrantes", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeListComentarioCol = createCallType(MDSQLConstants.T_T_CMT_COLUMNAS);
            String typeTableLineas = createCallType(MDSQLConstants.T_T_LINEA);

            logProcedure(runSP, nombreTabla, comentarioES, comentarioEN, fichero);

            callableStatement.setString(1, nombreTabla);
            callableStatement.setString(2, comentarioES);
            callableStatement.setString(3, comentarioEN);
            setArray(callableStatement, 4, toDBListTextoLinea(conn, fichero), typeTableLineas);

            callableStatement.registerOutParameter(5, Types.ARRAY, typeListComentarioCol); //p_lista_cmt_columnas

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<ReentranteComentarioColumna> output = new OutputConsulta();
            output.setOutputWarning(result);

            List<ReentranteComentarioColumna> listDatos = new ArrayList<>();
            Array arrDatos = callableStatement.getArray(5);

            if (arrDatos != null) {
                Object[] rows = (Object[]) arrDatos.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                    ReentranteComentarioColumna dato = ReentranteComentarioColumna.builder().
                            nombreColumna((String) cols[0]).
                            tipo((String) cols[1]).
                            Rdo((String) cols[2]).
                            idioma((String) cols[3]).
                            comentario((String) cols[4]).
                            comentarioGenerico((String) cols[5]).
                            build();
                    listDatos.add(dato);
                }
                output.setLista(listDatos);
            }
            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.procesaComentarioReentrantes] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputWarning validaParamOperacion(ReentranteInfo reentranteInfo) throws ServiceException {

        String runSP = createCall("p_valida_param_operacion", 5);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTableDatos = createCallType(MDSQLConstants.T_T_DATOS_REENTRANTE);

            String tipoReentrante = reentranteInfo.getTipoOperacion();
            String nombreTabla = reentranteInfo.getNombreTabla();
            List<ReentranteDato> listaParam = getListReentranteDato(reentranteInfo);

            logProcedure(runSP, tipoReentrante, nombreTabla, listaParam);

            Array arrayListaParam = listaReentranteDatoToDB(conn, listaParam);
            callableStatement.setString(1, tipoReentrante);
            callableStatement.setString(2, nombreTabla);
            setArray(callableStatement, 3, arrayListaParam, typeTableDatos);

            return executeStatement(callableStatement);

        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.validaParamOperacion] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputValor<ScriptInfo> generaCmtReentrantes(
            String nombreTabla,
            String comentarioES,
            String comentarioEN,
            List<ReentranteComentarioColumna> listaCmtCol
    ) throws ServiceException {

        String runSP = createCall("p_genera_cmt_reentrantes", 8);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTableLineas = createCallType(MDSQLConstants.T_T_LINEA);

            logProcedure(runSP, nombreTabla, comentarioES, comentarioEN, listaCmtCol);

            callableStatement.setString(1, nombreTabla);
            callableStatement.setString(2, comentarioES);
            callableStatement.setString(3, comentarioEN);
            setArray(callableStatement, 4, toDBListReentranteComentarioColumna(conn, listaCmtCol), typeTableLineas);

            callableStatement.registerOutParameter(5, Types.ARRAY, typeTableLineas); //p_script_CMT
            callableStatement.registerOutParameter(6, Types.VARCHAR); //p_nom_script_CMT

            OutputWarning result = executeStatement(callableStatement);

            ScriptInfo scriptInfo = new ScriptInfo();
            scriptInfo.setLineas(fromDBListTextoLinea(callableStatement.getArray(5)));
            scriptInfo.setNombre(callableStatement.getString(6));

            OutputValor<ScriptInfo> output = new OutputValor();
            output.setValor(scriptInfo);
            output.setWarnings(result.getWarnings());

            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.generaCmtReentrantes] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }

    }

    @Override
    public OutputProcesaReentrante procesaScriptReentrante(
            String codProyecto,
            String codSubProyecto,
            String codPeticion,
            String codDemanda,
            String codUsr,
            String codUsrPeticion,
            String nomBBDD,
            String nomEsquema,
            ReentranteInfo reentranteInfo,
            String descripcion
    ) throws ServiceException {

        String runSP = createCall("p_procesa_script_reentrante", 32);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTableScript = createCallType(MDSQLConstants.T_T_SCRIPT);
            String typeTableConsultasBD = createCallType(MDSQLConstants.T_T_CONSULTAS_BD);
            String typeTableDatos = createCallType(MDSQLConstants.T_T_DATOS_REENTRANTE);
            String typeTableLineas = createCallType(MDSQLConstants.T_T_LINEA);
            String typeTableLanza = createCallType(MDSQLConstants.T_T_LANZA);

            ScriptInfo scriptInfoCambios = getScriptInfo(reentranteInfo.getScriptCambio());
            ScriptInfo scriptInfoCreacion = getScriptInfo(reentranteInfo.getScriptCreacion());
            ScriptInfo scriptInfoComentarios = getScriptInfo(reentranteInfo.getScriptComentarios());
            List<ReentranteDato> listaReentranteDato = getListReentranteDato(reentranteInfo);

            String claveEncript = bbddService.getClaveEncriptada();

            logProcedure(runSP,
                    codProyecto,
                    codSubProyecto,
                    codPeticion,
                    codDemanda,
                    codUsr,
                    codUsrPeticion,
                    nomBBDD,
                    nomEsquema,
                    scriptInfoCambios.getLineas(),
                    scriptInfoCambios.getNombre(),
                    scriptInfoCambios.getRuta(),
                    scriptInfoCreacion.getLineas(),
                    scriptInfoCreacion.getNombre(),
                    scriptInfoCreacion.getRuta(),
                    scriptInfoComentarios.getLineas(),
                    scriptInfoComentarios.getNombre(),
                    scriptInfoComentarios.getRuta(),
                    reentranteInfo.getRuta(), //p_txt_ruta_entrada
                    reentranteInfo.getNombreTabla(),
                    reentranteInfo.getTipoOperacion(),
                    listaReentranteDato,
                    descripcion,
                    claveEncript
            );

            callableStatement.setString(1, codProyecto);
            callableStatement.setString(2, codSubProyecto);
            callableStatement.setString(3, codPeticion);
            callableStatement.setString(4, codDemanda);
            callableStatement.setString(5, codUsr);
            callableStatement.setString(6, codUsrPeticion);
            callableStatement.setString(7, nomBBDD);
            callableStatement.setString(8, nomEsquema);
            setArray(callableStatement, 9, toDBListTextoLinea(conn, scriptInfoCambios.getLineas()), typeTableLineas);
            callableStatement.setString(10, scriptInfoCambios.getNombre());
            callableStatement.setString(11, scriptInfoCambios.getRuta());
            setArray(callableStatement, 12, toDBListTextoLinea(conn, scriptInfoCreacion.getLineas()), typeTableLineas);
            callableStatement.setString(13, scriptInfoCreacion.getNombre());
            callableStatement.setString(14, scriptInfoCreacion.getRuta());
            setArray(callableStatement, 15, toDBListTextoLinea(conn, scriptInfoComentarios.getLineas()), typeTableLineas);
            callableStatement.setString(16, scriptInfoComentarios.getNombre());
            callableStatement.setString(17, scriptInfoComentarios.getRuta());
            callableStatement.setString(18, reentranteInfo.getRuta());
            callableStatement.setString(19, reentranteInfo.getNombreTabla());
            callableStatement.setString(20, reentranteInfo.getTipoOperacion());
            setArray(callableStatement, 21, toDBListReentranteDato(conn, listaReentranteDato), typeTableDatos);
            callableStatement.setString(22, descripcion);
            callableStatement.setString(23, claveEncript);
            // Parámetros de salida
            callableStatement.registerOutParameter(24, Types.NUMERIC); //p_id_proceso
            callableStatement.registerOutParameter(25, Types.DATE); //p_fec_proceso
            callableStatement.registerOutParameter(26, Types.NUMERIC); //p_cod_estado_proc
            callableStatement.registerOutParameter(27, Types.VARCHAR); // p_des_estado_proc
            callableStatement.registerOutParameter(28, Types.ARRAY, typeTableScript); //p_lista_scripts
            callableStatement.registerOutParameter(29, Types.ARRAY, typeTableLanza); //p_lista_lanzas
            callableStatement.registerOutParameter(30, Types.ARRAY, typeTableConsultasBD); //p_lista_consultas_BD

            OutputWarning result = executeStatement(callableStatement);

            OutputProcesaReentrante output = new OutputProcesaReentrante();
            output.setOutputWarning(result);

            output.setIdProceso(callableStatement.getBigDecimal(24));           //p_id_proceso
            output.setFechaProceso(callableStatement.getDate(25));              //p_fec_proceso
            output.setCodigoEstadoProceso(callableStatement.getBigDecimal(26)); //p_cod_estado_proc
            output.setDescripcionEstadoProceso(callableStatement.getString(27));// p_des_estado_proc
            output.setListaScripts(fromDBListScript(callableStatement.getArray(28)));//p_lista_scripts
            output.setListaLanza(fromDBListLanza(callableStatement.getArray(29)));//p_lista_Lanza
            output.setListaConsultaBD(fromDBListConsultaBD(callableStatement.getArray(30))); //p_lista_consultas_BD

            return output;
        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.procesaScriptReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    private ScriptInfo getScriptInfo(String fileName) throws ServiceException {
        ScriptInfo scriptInfo = new ScriptInfo();
        if (fileName != null && !fileName.isEmpty()) {
            try {
                File file = new File(fileName);
                scriptInfo.setNombre(file.getName());
                scriptInfo.setRuta(file.getParent());
                scriptInfo.setLineas(MDSQLAppHelper.writeFileToLines(file));
                return scriptInfo;
            } catch (IOException ex) {
                throw new ServiceException(ex);
            }
        }
        return scriptInfo;
    }

    @Override
    public OutputRegistraEjecucion registraEjecucionReentrante(
            BigDecimal idProceso,
            BigDecimal numOrden,
            String nomBBDD,
            String nomEsquema,
            String codUsr,
            List<TextoLinea> logScript
    ) throws ServiceException {

        String runSP = createCall("p_registra_ejecucion_reentrante", 15);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeTableLineas = createCallType(MDSQLConstants.T_T_LINEA);

            logProcedure(runSP, idProceso, numOrden, nomBBDD, nomEsquema, codUsr, logScript);

            Array arrayLinea = toDBListTextoLinea(conn, logScript);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numOrden);
            callableStatement.setString(3, nomBBDD);
            callableStatement.setString(4, nomEsquema);
            callableStatement.setString(5, codUsr);
            setArray(callableStatement, 6, arrayLinea, typeTableLineas);
            callableStatement.registerOutParameter(7, Types.NUMERIC);//p_cod_estado_proc
            callableStatement.registerOutParameter(8, Types.VARCHAR); //p_des_estado_proc
            callableStatement.registerOutParameter(9, Types.VARCHAR); //p_nom_script
            callableStatement.registerOutParameter(10, Types.NUMERIC); //p_cod_estado_scrip
            callableStatement.registerOutParameter(11, Types.VARCHAR); //p_des_estado_scrip
            callableStatement.registerOutParameter(12, Types.VARCHAR); //p_txt_cuadre_oper
            callableStatement.registerOutParameter(13, Types.VARCHAR); //p_txt_cuadre_obj

            OutputWarning result = executeStatement(callableStatement);

            OutputRegistraEjecucion output = new OutputRegistraEjecucion();
            output.setOutputWarning(result);
            output.setCodigoEstadoProceso(callableStatement.getBigDecimal(7));
            output.setDescripcionEstadoProceso(callableStatement.getString(8));
            output.setNombreScript(callableStatement.getString(9));
            output.setCodigoEstadoScript(callableStatement.getBigDecimal(10));
            output.setDescripcionEstadoScript(callableStatement.getString(11));
            output.setTxtCuadreOperacion(callableStatement.getString(12));
            output.setTxtCuadreObj(callableStatement.getString(13));

            return output;

        } catch (SQLException | IndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.registraEjecucionReentrante] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Convierte una lista de reentranteDato en Array de BD
     *
     * @param conn
     * @param lista
     * @return
     * @throws ServiceException
     */
    private Array listaReentranteDatoToDB(Connection conn, List<ReentranteDato> lista) throws ServiceException {
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        try {
            Struct[] structArray = new Struct[lista.size()];
            String recordType = createCallType(MDSQLConstants.T_R_DATOS_REENTRANTE);
            String tableType = createCallType(MDSQLConstants.T_T_DATOS_REENTRANTE);

            if (CollectionUtils.isNotEmpty(lista)) {
                structArray = new Struct[lista.size()];

                int arrayIndex = 0;
                for (ReentranteDato data : lista) {

                    structArray[arrayIndex++] = conn.createStruct(recordType,
                            new Object[]{data.getNumeroParam(), data.getNombreParam(), data.getValorParam()});

                }
            }
            return ((OracleConnection) conn).createOracleArray(tableType, structArray);
        } catch (SQLException e) {
            LogWrapper.error(log, "[ReentranteServiceImpl.listaReentranteDatoToDB] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Genera la estructura para enviar a la BD para una lista de comentarios de
     * columnas
     *
     * @param conn
     * @param lista
     * @return
     * @throws SQLException
     */
    private Array toDBListReentranteComentarioColumna(Connection conn, List<ReentranteComentarioColumna> lista) throws SQLException {
        String typeRecord = createCallType(MDSQLConstants.T_R_CMT_COLUMNAS);
        String typeTable = createCallType(MDSQLConstants.T_T_CMT_COLUMNAS);
        Struct[] structLinea = new Struct[lista.size()];

        int arrayIndexLinea = 0;
        for (ReentranteComentarioColumna data : lista) {
            structLinea[arrayIndexLinea++] = conn.createStruct(typeRecord,
                    new Object[]{
                        data.getNombreColumna(),
                        data.getTipo(),
                        data.getRdo(),
                        data.getIdioma(),
                        data.getComentario(),
                        data.getComentarioGenerico()
                    });
        }
        return ((oracle.jdbc.internal.OracleConnection) conn).createOracleArray(typeTable, structLinea);
    }

    /**
     * Genera la estructura para enviar a la BD para una lista de Datos
     * Reentrante
     *
     * @param conn
     * @param lista
     * @return
     * @throws SQLException
     */
    private Array toDBListReentranteDato(Connection conn, List<ReentranteDato> lista) throws SQLException {
        String typeRecord = createCallType(MDSQLConstants.T_R_DATOS_REENTRANTE);
        String typeTable = createCallType(MDSQLConstants.T_T_DATOS_REENTRANTE);
        Struct[] structLinea = new Struct[lista.size()];

        int arrayIndexLinea = 0;
        for (ReentranteDato data : lista) {
            structLinea[arrayIndexLinea++] = conn.createStruct(typeRecord,
                    new Object[]{
                        data.getNumeroParam(),
                        data.getNombreParam(),
                        data.getValorParam()
                    });
        }
        return ((oracle.jdbc.internal.OracleConnection) conn).createOracleArray(typeTable, structLinea);
    }

    /**
     * A partir de la definición de parámetros, genera una lista de los que
     * tienen valor
     */
    private List<ReentranteDato> getListReentranteDato(ReentranteInfo reentranteInfo) {
        List<ReentranteDato> listaReentranteDato = new ArrayList();
        if (reentranteInfo.getParametros() != null) {
            for (ReentranteParametro param : reentranteInfo.getParametros()) {
                listaReentranteDato.add(ReentranteDato.builder().
                        nombreParam(param.getNombreParam()).
                        numeroParam(param.getNumeroParam()).
                        valorParam(param.getValorParam()).
                        build());
            }
        }
        return listaReentranteDato;
    }

    /**
     * Genera un objeto de tipo ReentranteLanza a partir de la una estructura de
     * BD
     *
     * @param array
     * @return
     * @throws SQLException
     */
    private Lanza fromDBLanza(Object array) throws SQLException {

        Object[] cols = ((oracle.jdbc.OracleStruct) array).getAttributes();

        return Lanza.builder().
                numOrden((BigDecimal) cols[0]).
                nomScript((String) cols[1]).
                lineasScript(fromDBListTextoLinea((Array) cols[2])).
                nomFicheroLog((String) cols[3]).
                nomEsquema((String) cols[4]).
                nomBBDD((String) cols[5]).
                password((String) cols[6]).
                settings(fromDBListTextoLinea((Array) cols[7])).
                build();
    }

    /**
     * Genera una lista de objetos de tipo ReentranteLanza a partir de la una
     * estructura de BD
     *
     * @param array
     * @return
     * @throws SQLException
     */
    private List<Lanza> fromDBListLanza(Array array) throws SQLException {
        List<Lanza> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                lista.add(fromDBLanza(row));
            }
        }
        return lista;
    }

    /**
     * Tratamiento de arrays vacíos al pasar como parámetro
     *
     * @param callableStatement
     * @param position
     * @param array
     * @throws SQLException
     */
    private void setArray(CallableStatement callableStatement, int position, Array array, String typeDB) throws SQLException {
        if (array == null) {
            callableStatement.setNull(position, java.sql.Types.ARRAY, typeDB);
        } else {
            callableStatement.setArray(position, array);
        }
    }
}
