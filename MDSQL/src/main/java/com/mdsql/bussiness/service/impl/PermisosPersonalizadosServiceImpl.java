package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputConsultaPermisosPersonalizados;
import com.mdsql.bussiness.entities.OutputProcesaPermisoPersonalizado;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SinonimoObjeto;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.PermisosPersonalizadosService;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import oracle.jdbc.internal.OracleConnection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Service(MDSQLConstants.PERMISOS_PERSONALIZADOS_SERVICE)
@Slf4j
public class PermisosPersonalizadosServiceImpl extends ServiceSupportScript implements PermisosPersonalizadosService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BBDDService bbddService;

    @Override
    public OutputConsultaPermisosPersonalizados consulta(
            String p_cod_proyecto,
            String p_nom_objeto,
            String p_nom_columna,
            String p_txt_per_syn, // Permiso/Sinonimo
            String p_des_entorno,
            String p_cod_owner_syn, // Propietario sinonimo
            String p_val_regla_syn, // Funcion nombre
            String p_val_grant, // Permiso
            String p_tip_objeto,
            String p_cod_usr_grant, // Receptor permisos
            String p_cod_peticion,
            String p_mca_grant_option,
            String p_mca_pdc,
            String p_mca_habilitado,
            String p_cod_usr, // Usuario de modificación
            String p_fec_desde,
            String p_fec_hasta
    ) throws ServiceException {
        String runSP = createCall("p_con_per_personalizados", MDSQLConstants.CALL_21_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermisoCol = createCallType("T_T_PERMISO_COL");
            String typeSinonimoObj = createCallType("T_T_SINONIMO_OBJ");
            String typeError = createCallTypeError();

            logProcedure(runSP,
                    p_cod_proyecto,
                    p_nom_objeto,
                    p_nom_columna,
                    p_txt_per_syn, // Permiso/Sinonimo
                    p_des_entorno,
                    p_cod_owner_syn, // Propietario sinonimo
                    p_val_regla_syn, // Funcion nombre
                    p_val_grant, // Permiso
                    p_tip_objeto,
                    p_cod_usr_grant, // Receptor permisos
                    p_cod_peticion,
                    p_mca_grant_option,
                    p_mca_pdc,
                    p_mca_habilitado,
                    p_cod_usr, // Usuario de modificación
                    p_fec_desde,
                    p_fec_hasta);

            dateFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_nom_objeto);
            callableStatement.setString(3, p_nom_columna);
            callableStatement.setString(4, p_txt_per_syn);
            callableStatement.setString(5, p_des_entorno);
            callableStatement.setString(6, p_cod_owner_syn);
            callableStatement.setString(7, p_val_regla_syn);
            callableStatement.setString(8, p_val_grant);
            callableStatement.setString(9, p_tip_objeto);
            callableStatement.setString(10, p_cod_usr_grant);
            callableStatement.setString(11, p_cod_peticion);
            callableStatement.setString(12, p_mca_grant_option);
            callableStatement.setString(13, p_mca_pdc);
            callableStatement.setString(14, p_mca_habilitado);
            callableStatement.setString(15, p_cod_usr);
            callableStatement.setDate(16, StringToSqlDate(p_fec_desde));
            callableStatement.setDate(17, StringToSqlDate(p_fec_hasta));

            callableStatement.registerOutParameter(18, Types.ARRAY, typePermisoCol);
            callableStatement.registerOutParameter(19, Types.ARRAY, typeSinonimoObj);
            callableStatement.registerOutParameter(20, Types.INTEGER);
            callableStatement.registerOutParameter(21, Types.ARRAY, typeError);

            callableStatement.execute();

            Array arrayPermisos = callableStatement.getArray(18);
            Array arraySinonimos = callableStatement.getArray(19);
            Integer result = callableStatement.getInt(20);
            Array errores = callableStatement.getArray(21);

            return trataRespuesta(arrayPermisos, arraySinonimos, result, errores);
        } catch (SQLException | ParseException e) {
            LogWrapper.error(log, "[PermisosPersonalizadosService.consulta] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     *
     * @param p_cod_proyecto
     * @param p_cod_sub_proy
     * @param p_nom_objeto
     * @param p_tip_objeto
     * @param p_cod_peticion
     * @return
     * @throws ServiceException
     */
    @Override
    public OutputConsultaPermisosPersonalizados consultaPermisoSinonimo(
            String p_cod_proyecto,
            String p_cod_sub_proy,
            String p_nom_objeto,
            String p_tip_objeto,
            String p_cod_peticion
    ) throws ServiceException {
        String runSP = createCall("p_buscar_per_syn_per", MDSQLConstants.CALL_09_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typePermisoCol = createCallType("T_T_PERMISO_COL");
            String typeSinonimoObj = createCallType("T_T_SINONIMO_OBJ");
            String typeError = createCallTypeError();

            logProcedure(runSP, p_cod_proyecto, p_cod_sub_proy, p_nom_objeto, p_tip_objeto, p_cod_peticion);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_cod_sub_proy);
            callableStatement.setString(3, p_nom_objeto);
            callableStatement.setString(4, p_tip_objeto);
            callableStatement.setString(5, p_cod_peticion);
            callableStatement.registerOutParameter(6, Types.ARRAY, typePermisoCol);
            callableStatement.registerOutParameter(7, Types.ARRAY, typeSinonimoObj);
            callableStatement.registerOutParameter(8, Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.ARRAY, typeError);

            callableStatement.execute();

            Array arrayPermisos = callableStatement.getArray(6);
            Array arraySinonimos = callableStatement.getArray(7);
            Integer result = callableStatement.getInt(8);
            Array errores = callableStatement.getArray(9);

            return trataRespuesta(arrayPermisos, arraySinonimos, result, errores);

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosPersonalizadosService.consultaPermisoSinonimo] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }

    }

    private OutputConsultaPermisosPersonalizados trataRespuesta(Array arrayPermisos, Array arraySinonimos, Integer result, Array arrayErrores) throws ServiceException, SQLException {

        if (result == 0) {
            throw buildException(arrayErrores);
        }

        List<PermisoColumna> permisosColumna = new ArrayList<>();
        System.out.println("Recuperando Permisos ");
        if (arrayPermisos != null) {
            Object[] rows = (Object[]) arrayPermisos.getArray();
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

        System.out.println("Recuperando Sinonimos ");
        //
        List<SinonimoObjeto> sinonimosObjeto = new ArrayList<>();

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

        OutputConsultaPermisosPersonalizados output = new OutputConsultaPermisosPersonalizados();
        output.setPermisosColumna(permisosColumna);
        output.setSinonimosObjeto(sinonimosObjeto);
        output.setResult(result);
        // Hay avisos
        if (result == 2) {
            output.setServiceException(buildException(arrayErrores));
        }
        return output;
    }

    public java.sql.Date StringToSqlDate(String stringDate) throws ParseException {
        java.sql.Date sqlDate = null;

        if (stringDate != null && !stringDate.isEmpty()) {
            long longDate = dateFormatter.stringToDate(stringDate).getTime();
            sqlDate = new java.sql.Date(longDate);
        }
        return sqlDate;
    }

    @Override
    public OutputProcesaPermisoPersonalizado procesa(
            String p_cod_proyecto,
            String p_cod_sub_proy,
            String p_tip_objeto, // Viene de la pantalla anterior
            String p_nom_objeto, // Viene de la pantalla anterior
            String p_cod_peticion_ant, // Viene de la pantalla anterior
            String p_cod_peticion,
            String p_cod_demanda,
            String p_nom_BBDD,
            String p_cod_usr_peticion,
            String p_txt_comentario,
            String p_mca_generales,
            String p_mca_resto_per,
            String p_ruta_salida,
            String p_cod_usr) throws ServiceException {
        String runSP = createCall("p_procesa_per_per", MDSQLConstants.CALL_21_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeScript = createCallType("T_T_SCRIPT");
            String typeError = createCallTypeError();

            logProcedure(runSP,
                    p_cod_proyecto,
                    p_cod_sub_proy,
                    p_tip_objeto, // Viene de la pantalla anterior
                    p_nom_objeto, // Viene de la pantalla anterior
                    p_cod_peticion_ant, // Viene de la pantalla anterior
                    p_cod_peticion,
                    p_cod_demanda,
                    p_nom_BBDD,
                    p_cod_usr_peticion,
                    p_txt_comentario,
                    p_mca_generales,
                    p_mca_resto_per,
                    p_ruta_salida,
                    p_cod_usr);

            callableStatement.setString(1, p_cod_proyecto);
            callableStatement.setString(2, p_cod_sub_proy);
            callableStatement.setString(3, p_tip_objeto);
            callableStatement.setString(4, p_nom_objeto);
            callableStatement.setString(5, p_cod_peticion_ant);
            callableStatement.setString(6, p_cod_peticion);
            callableStatement.setString(7, p_cod_demanda);
            callableStatement.setString(8, p_nom_BBDD);
            callableStatement.setString(9, p_cod_usr_peticion);
            callableStatement.setString(10, p_txt_comentario);
            callableStatement.setString(11, p_mca_generales);
            callableStatement.setString(12, p_mca_resto_per);
            callableStatement.setString(13, p_ruta_salida);
            callableStatement.setString(14, p_cod_usr);
            callableStatement.registerOutParameter(15, Types.BIGINT);           //p_id_proceso
            callableStatement.registerOutParameter(16, Types.DATE);             //p_fec_proceso
            callableStatement.registerOutParameter(17, Types.BIGINT);           //p_cod_estado_proc
            callableStatement.registerOutParameter(18, Types.VARCHAR);          //p_des_estado_proc
            callableStatement.registerOutParameter(19, Types.ARRAY, typeScript);//p_lista_scripts
            callableStatement.registerOutParameter(20, Types.INTEGER);          //p_resultado
            callableStatement.registerOutParameter(21, Types.ARRAY, typeError); //p_lista_errores

            callableStatement.execute();
            //Obtener datos de salida
            BigDecimal p_id_proceso = callableStatement.getBigDecimal(15);
            Date p_fec_proceso = callableStatement.getDate(16);
            BigDecimal p_cod_estado_proc = callableStatement.getBigDecimal(17);
            String p_des_estado_proc = callableStatement.getString(18);
            Array arrayScripts = callableStatement.getArray(19);
            List<Script> listScripts = arrayToListScript(arrayScripts);

            Integer result = callableStatement.getInt(20);
            Array arrayErrores = callableStatement.getArray(21);
            if (result == 0) {
                throw buildException(arrayErrores);
            }
            //Cargar datos de salida 
            OutputProcesaPermisoPersonalizado output = new OutputProcesaPermisoPersonalizado();
            output.setIdProceso(p_id_proceso);
            output.setFechaProceso(p_fec_proceso);
            output.setCodigoEstadoProceso(p_cod_estado_proc);
            output.setDescripcionEstadoProceso(p_des_estado_proc);
            output.setListaScripts(listScripts);
            // Hay avisos
            if (result == 2) {
                output.setServiceException(buildException(arrayErrores));
            }
            return output;
        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosPersonalizadosService.procesa] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @SneakyThrows
    private OutputRegistraEjecucion registraEjecucion(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> lineas) throws ServiceException {
        String runSP = createCall("p_registra_per_personalizados", MDSQLConstants.CALL_08_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String tableLinea = createCallType(MDSQLConstants.T_T_LINEA);
            String recordLinea = createCallType(MDSQLConstants.T_R_LINEA);

            String typeError = createCallTypeError();

            logProcedure(runSP, idProceso, numeroOrden, codigoUsuario, lineas);

            Struct[] structLinea = new Struct[lineas.size()];

            int arrayIndexLinea = 0;
            for (TextoLinea data : lineas) {
                structLinea[arrayIndexLinea++] = conn.createStruct(recordLinea,
                        new Object[]{data.getValor()});
            }

            Array arrayLinea = ((OracleConnection) conn).createOracleArray(tableLinea, structLinea);

            callableStatement.setBigDecimal(1, idProceso);
            callableStatement.setBigDecimal(2, numeroOrden);
            callableStatement.setString(3, codigoUsuario);
            callableStatement.setArray(4, arrayLinea);
            callableStatement.registerOutParameter(5, Types.NUMERIC); //p_cod_estado_proc
            callableStatement.registerOutParameter(6, Types.VARCHAR); //p_des_estado_proc
            callableStatement.registerOutParameter(7, Types.INTEGER); //p_resultado
            callableStatement.registerOutParameter(8, Types.ARRAY, typeError); //p_lista_errores

            callableStatement.execute();

            Integer result = callableStatement.getInt(7);
            Array arrayErrores = callableStatement.getArray(8);

            if (result == 0) {
                throw buildException(arrayErrores);
            }

            OutputRegistraEjecucion outputRegistraEjecucion = new OutputRegistraEjecucion();
            outputRegistraEjecucion.setResult(result);

            // Hay avisos
            if (result == 2) {
                outputRegistraEjecucion.setServiceException(buildException(arrayErrores));
            }

            BigDecimal codigoEstadoProceso = callableStatement.getBigDecimal(5);
            String descripcionEstadoProceso = callableStatement.getString(6);

            outputRegistraEjecucion.setCodigoEstadoProceso(codigoEstadoProceso);
            outputRegistraEjecucion.setDescripcionEstadoProceso(descripcionEstadoProceso);

            return outputRegistraEjecucion;

        } catch (SQLException e) {
            LogWrapper.error(log, "[PermisosPersonalizadosService.registraEjecucion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts, String ruta) throws ServiceException {
        try {
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String codigoUsuario = session.getCodUsr();
            Proceso proceso = session.getProceso();

            String nombreEsquema = StringUtils.EMPTY;
            String nombreBBDD = StringUtils.EMPTY;

            List<OutputRegistraEjecucion> ejecuciones = new ArrayList<>();
            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            String txtClaveEncriptada = configuration.getConfig(MDSQLConstants.TOKEN).substring(17, 29);

            String separator = File.separator;
            if (!ruta.endsWith(separator)) {
                ruta = ruta.concat(separator);
            }

            if (CollectionUtils.isNotEmpty(scripts)) {
                for (Script script : scripts) {
                    //Verificamos que estén indicados tanto el nombre como el contenido
                    if (script.getNombreScript() != null && script.getLineasScript() != null) {
                        // Esto causa reescritura de ficheros
                        MDSQLAppHelper.dumpLinesToFile(script.getLineasScript(), Paths.get(ruta.concat(script.getNombreScript())).toFile());
                        /**
                         * Según sea el tipo de script (SQL, PDC, SQLH, PDCH),
                         * se seleccionará la base de datos o la de histórico
                         * para su ejecución
                         */
                        if ("SQL".equals(script.getTipoScript()) || "PDC".equals(script.getTipoScript())) {
                            nombreEsquema = bbdd.getNombreEsquema();
                            nombreBBDD = bbdd.getNombreBBDD();
                        }

                        if ("SQLH".equals(script.getTipoScript()) || "PDCH".equals(script.getTipoScript())) {
                            nombreEsquema = bbdd.getNombreEsquemaHis();
                            nombreBBDD = bbdd.getNombreBBDDHis();
                        }

                        // Sólo hay que crear el script lanza
                        String lanzaFile = ruta.concat(script.getNombreScriptLanza());
                        MDSQLAppHelper.writeToFile(script.getTxtScriptLanza().concat(System.lineSeparator()), Paths.get(lanzaFile).toFile());

                        String password = bbddService.consultaPasswordBBDD(nombreBBDD, nombreEsquema, txtClaveEncriptada);
                        //bbdd.setPassword(password);

                        // Ejecución del script
                        executeLanzaFile(nombreEsquema, nombreBBDD, password, lanzaFile);

                        // Obtiene el log
                        String logFile = ruta.concat(script.getNombreScriptLog());
                        List<TextoLinea> logLinesList = MDSQLAppHelper.writeFileToLines(new File(logFile));

                        // Registra la ejecución, al primer fallo dispara la excepcion
                        OutputRegistraEjecucion outputRegistraEjecucion = registraEjecucion(
                                proceso.getIdProceso(), script.getNumeroOrden(), codigoUsuario, logLinesList);
                        outputRegistraEjecucion.setNumOrden(script.getNumeroOrden());
                        outputRegistraEjecucion.setFechaEjecucion(new java.util.Date());
                        ejecuciones.add(outputRegistraEjecucion);
                    }
                }
            }
            return ejecuciones;
        } catch (IOException e) {
            LogWrapper.error(log, "[PermisosPersonalizadosService.executeScripts] Error", e);
            throw new ServiceException(e);
        }
    }

    public void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation) throws ServiceException {
        String connection = String.format(MDSQLConstants.FORMATO_CONEXION, nombreEsquema, password, nombreBBDD);

        ProcessBuilder processBuilder = new ProcessBuilder(MDSQLConstants.SQL_PLUS, connection,
                String.format(MDSQLConstants.FORMATO_FICHERO, fileLocation));
        LogWrapper.debug(log, processBuilder.command());

        Boolean invalidLogon = Boolean.FALSE;
        String lineError = StringUtils.EMPTY;

        List<TextoLinea> logLines = new ArrayList<>();

        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;

                LogWrapper.debug(log, "[ScriptService.executeScriptFile] Inicio Ejecucion fichero: %s", fileLocation);
                while (((line = in.readLine()) != null)) {
                    TextoLinea textoLinea = TextoLinea.builder().valor(line).build();
                    logLines.add(textoLinea);
                    LogWrapper.debug(log, line);

                    // Error de clave incorrecta
                    if (line.contains("ORA-01017")) {
                        invalidLogon = Boolean.TRUE;
                        lineError = line;
                        break;
                    }
                }

                if (invalidLogon) {
                    process.destroyForcibly();
                }
            } catch (IOException e) {
                LogWrapper.error(log, e.getMessage());
                throw new ServiceException(e);
            }

            // Si se fuerza la parada del proceso, se sale con error 137, en Windows es 1
            int exitCode = process.waitFor();

            LogWrapper.debug(log, "[ScriptService.executeScriptFile] Fin Ejecucion exitCode: %s", exitCode);
            // Si ha dado error, escribe el fichero de log
            //MDSQLAppHelper.dumpLinesToFile(logLines, Paths.get(logFile).toFile());

            if (exitCode == 1 || exitCode == 137) {
                throw new ServiceException(lineError);
            }
        } catch (IOException | InterruptedException e) {
            LogWrapper.error(log, e.getMessage());
            throw new ServiceException(e);
        }
    }
}
