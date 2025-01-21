package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.CampoGlosario;
import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.DetValidacion;
import com.mdsql.bussiness.entities.Informe;
import com.mdsql.bussiness.entities.InformeCambioTRN;
import com.mdsql.bussiness.entities.InformeCambios;
import com.mdsql.bussiness.entities.InformeValidacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputParamInformeTRN;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.service.InformeService;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.LogWrapper;
import java.io.IOException;
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
@Service(MDSQLConstants.INFORME_SERVICE)
@Slf4j
public class InformeServiceImpl extends ServiceSupport implements InformeService {

    @Autowired
    private DataSource dataSource;

    @Override
    public InformeValidacion generarInformeValidacion(BigDecimal codigoValidacion) throws ServiceException {
        String runSP = createCall("p_generar_informe_val", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeDetValidacion = createCallType(MDSQLConstants.T_T_DET_VALIDACION);
            String typeCampoGlosario = createCallType(MDSQLConstants.T_T_CAMPO_GLOSARIO);
            String typeError = createCallTypeError();

            logProcedure(runSP, codigoValidacion);

            callableStatement.setBigDecimal(1, codigoValidacion);
            callableStatement.registerOutParameter(2, Types.ARRAY, typeDetValidacion);
            callableStatement.registerOutParameter(3, Types.ARRAY, typeDetValidacion);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeCampoGlosario);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(5);

            if (result == 0) {
                throw buildException(callableStatement.getArray(6));
            }

            Array arrayErroneos = callableStatement.getArray(2);
            Array arrayOtraDefinicion = callableStatement.getArray(3);
            Array arrayDefinicionGlosarios = callableStatement.getArray(4);

            List<DetValidacion> listaErroneos = getListaErroneos(arrayErroneos);
            List<DetValidacion> listaOtraDefinicion = getListaOtraDefinicion(arrayOtraDefinicion);
            List<CampoGlosario> listaDefinicionGlosario = getListaDefinicionGlosario(arrayDefinicionGlosarios);

            return InformeValidacion.builder()
                    .listaErroneos(listaErroneos)
                    .listaOtraDefinicion(listaOtraDefinicion)
                    .listaDefinicionGlosario(listaDefinicionGlosario)
                    .build();

        } catch (SQLException e) {
            LogWrapper.error(log, "[InformeService.generarInformeValidacion] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<InformeCambios> informeCambios(String codigoProyecto, String fechaDesde, String fechaHasta) throws ServiceException {
        String runSP = createCall("p_informe_cambios", MDSQLConstants.CALL_06_ARGS);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeInformeCambios = createCallType(MDSQLConstants.T_T_INFORME_CAMBIOS);
            String typeError = createCallTypeError();

            logProcedure(runSP, codigoProyecto, fechaDesde, fechaHasta);

            callableStatement.setString(1, codigoProyecto);
            setDate(callableStatement, 2, fechaDesde);
            setDate(callableStatement, 3, fechaHasta);
            callableStatement.registerOutParameter(4, Types.ARRAY, typeInformeCambios);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.registerOutParameter(6, Types.ARRAY, typeError);

            callableStatement.execute();

            Integer result = callableStatement.getInt(5);

            if (result == 0) {
                throw buildException(callableStatement.getArray(6));
            }

            OutputConsulta<InformeCambios> outputInformeCambios = new OutputConsulta<>();
            outputInformeCambios.setResult(result);

            // Hay avisos
            if (result == 2) {
                outputInformeCambios.setWarnings(buildException(callableStatement.getArray(6)));
            }

            List<InformeCambios> informeCambios = new ArrayList<>();
            Array arrayInformeCambios = callableStatement.getArray(4);

            if (arrayInformeCambios != null) {
                Object[] rows = (Object[]) arrayInformeCambios.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    InformeCambios historicoProceso = InformeCambios.builder()
                            .codigoPeticion((String) cols[0])
                            .idProceso((BigDecimal) cols[1])
                            .nombreObjetoPadre((String) cols[2])
                            .tipoObjetoPadre((String) cols[3])
                            .tipoAccionPadre((String) cols[4])
                            .nombreObjeto((String) cols[5])
                            .nombreObjetoDestino((String) cols[6])
                            .tipoObjeto((String) cols[7])
                            .tipoAccion((String) cols[8])
                            .tipoDato((String) cols[9])
                            .numeroLongitud((BigDecimal) cols[10])
                            .numeroDecimal((BigDecimal) cols[11])
                            .descripcionEstadoProceso((String) cols[12])
                            .fechaProceso((Date) cols[13])
                            .codigoSubProyecto((String) cols[14])
                            .codigoUsuarioPeticion((String) cols[15])
                            .codigoUsuario((String) cols[16])
                            .descripcionEstadoScript((String) cols[17])
                            .nombreScript((String) cols[18])
                            .build();
                    informeCambios.add(historicoProceso);
                }

                outputInformeCambios.setLista(informeCambios);
            }
            return outputInformeCambios;
        } catch (SQLException e) {
            LogWrapper.error(log, "[InformeService.informeCambios] Error:  %s", e.getMessage());
            throw new ServiceException(e);
        }

    }

    private List<DetValidacion> getListaErroneos(Array arrayErroneos) throws SQLException {
        List<DetValidacion> listaErroneos = new ArrayList<>();
        if (arrayErroneos != null) {
            Object[] rows = (Object[]) arrayErroneos.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                DetValidacion detValidacion = DetValidacion.builder()
                        .numeroValidacion((BigDecimal) cols[0])
                        .numeroElementoValid((BigDecimal) cols[1])
                        .descripcionElemento((String) cols[2])
                        .nombreElemento((String) cols[3])
                        .tipoDato((String) cols[4])
                        .numeroLongitud((BigDecimal) cols[5])
                        .numeroDecimal((BigDecimal) cols[6])
                        .codigoEstadoValid((BigDecimal) cols[7])
                        .txtDescripcionValid((String) cols[8])
                        .build();

                listaErroneos.add(detValidacion);
            }
        }
        return listaErroneos;
    }

    private List<DetValidacion> getListaOtraDefinicion(Array arrayOtraDefinicion) throws SQLException {
        List<DetValidacion> listaOtraDefinicion = new ArrayList<>();
        if (arrayOtraDefinicion != null) {
            Object[] rows = (Object[]) arrayOtraDefinicion.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                DetValidacion detValidacion = DetValidacion.builder()
                        .numeroValidacion((BigDecimal) cols[0])
                        .numeroElementoValid((BigDecimal) cols[1])
                        .descripcionElemento((String) cols[2])
                        .nombreElemento((String) cols[3])
                        .tipoDato((String) cols[4])
                        .numeroLongitud((BigDecimal) cols[5])
                        .numeroDecimal((BigDecimal) cols[6])
                        .codigoEstadoValid((BigDecimal) cols[7])
                        .txtDescripcionValid((String) cols[8])
                        .build();

                listaOtraDefinicion.add(detValidacion);
            }
        }
        return listaOtraDefinicion;
    }

    private List<CampoGlosario> getListaDefinicionGlosario(Array arrayDefinicionGlosarios) throws SQLException {
        List<CampoGlosario> listaDefinicionGlosario = new ArrayList<>();
        if (arrayDefinicionGlosarios != null) {
            Object[] rows = (Object[]) arrayDefinicionGlosarios.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                CampoGlosario campoGlosario = CampoGlosario.builder()
                        .nombreColumna((String) cols[0])
                        .tipoDato((String) cols[1])
                        .numeroLongitud((BigDecimal) cols[2])
                        .numeroDecimal((BigDecimal) cols[3])
                        .codigoGlosario((BigDecimal) cols[4])
                        .mcaExcepcion((String) cols[5])
                        .txtComentario((String) cols[6])
                        .txtExcepcion((String) cols[7])
                        .codigoUsuario((String) cols[8])
                        .fechaActualizacion((Date) cols[9])
                        .build();

                listaDefinicionGlosario.add(campoGlosario);
            }
        }
        return listaDefinicionGlosario;
    }

    @Override
    public OutputConsulta<InformeCambioTRN> generaInformeTRN() throws ServiceException {
        String runSP = createCall("p_genera_informe_TRN", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeInforme = createCallType(MDSQLConstants.T_T_INFORME);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeInforme);

            OutputWarning result = executeStatement(callableStatement);
            OutputConsulta<InformeCambioTRN> output = new OutputConsulta();
            output.setOutputWarning(result);
            output.setLista(getListInformeCambioTRN(callableStatement.getArray(1)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[InformeService.generaInformeTRN] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     *
     * @param array
     * @return
     * @throws SQLException
     */
    private List<InformeCambioTRN> getListInformeCambioTRN(Array array) throws SQLException {
        List<InformeCambioTRN> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                InformeCambioTRN item = InformeCambioTRN.builder()
                        .fechaCambio((Date) cols[0])
                        .nomObjeto((String) cols[1])
                        .tipObjeto((String) cols[2])
                        .nomElemento((String) cols[3])
                        .tipElemento((String) cols[4])
                        .tipCambio((String) cols[5])
                        .detalle((String) cols[6])
                        .codPeticion((String) cols[7])
                        .codUsr((String) cols[8])
                        .nomScript((String) cols[9])
                        .versionado((String) cols[10])
                        .build();

                lista.add(item);
            }
        }
        return lista;
    }

    @Override
    public OutputParamInformeTRN paramInformeTRN() throws ServiceException {
        String runSP = createCall("p_param_informe_TRN", 7);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.VARCHAR); //p_tip_envio
            callableStatement.registerOutParameter(2, Types.VARCHAR); //p_txt_servidor
            //Deben pasar el puerto
            callableStatement.registerOutParameter(3, Types.VARCHAR); //p_txt_usr_ftp
            callableStatement.registerOutParameter(4, Types.VARCHAR); //p_txt_pwd_ftp
            callableStatement.registerOutParameter(5, Types.VARCHAR); //p_txt_ruta

            OutputWarning result = executeStatement(callableStatement);

            OutputParamInformeTRN output = new OutputParamInformeTRN();
            output.setOutputWarning(result);
            String tipoEnvio = callableStatement.getString(1);
            output.setTipEnvio(tipoEnvio);

            output.setServidor(callableStatement.getString(2));
            String puertoStr = callableStatement.getString(3);
            //Si no se indica el puerto asumimos el 21
            int puerto = (puertoStr == null) ? 21 : Integer.parseInt(puertoStr);
            output.setPuerto(puerto);
            //
            output.setUsrFtp(callableStatement.getString(3));
            output.setPwdFtp(callableStatement.getString(4));
            output.setRuta(callableStatement.getString(5));

            // Configuración del FTP desde el fichero de configuración
            if ("FTP".equals(tipoEnvio) && output.getServidor() == null) {
                ConfigurationSingleton conf = ConfigurationSingleton.getInstance();
                output.setServidor(conf.getConfig("ftp.server"));
                puertoStr = conf.getConfig("ftp.port");
                output.setPuerto(Integer.parseInt(puertoStr));
                output.setUsrFtp(conf.getConfig("ftp.user"));
                output.setPwdFtp(conf.getConfig("ftp.password"));
            }

            return output;

        } catch (IOException | SQLException e) {
            LogWrapper.error(log, "[InformeService.paramInformeTRN] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<CodigoDescripcion> consultaTipoInforme() throws ServiceException {
        String runSP = createCall("p_con_tipos_informe", 3);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeList = createCallType(MDSQLConstants.T_T_TIPOS_INFORME);

            logProcedure(runSP);

            callableStatement.registerOutParameter(1, Types.ARRAY, typeList); //p_tipos_informe

            OutputWarning result = executeStatement(callableStatement);

            return fromDBListCodigoDescripcion(callableStatement.getArray(1), result);

        } catch (SQLException e) {
            LogWrapper.error(log, "[InformeService.consultaTipoInforme] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public OutputConsulta<Informe> consultaInforme(
            String codigoProyecto,
            String tipoInforme,
            String nombreObjeto,
            String fechaDesde,
            String fechaHasta,
            String mcaPermisos) throws ServiceException {
        String runSP = createCall("p_con_informe", 9);

        try (Connection conn = dataSource.getConnection(); CallableStatement callableStatement = conn.prepareCall(runSP)) {

            String typeLista = createCallType(MDSQLConstants.T_T_INFORME);

            logProcedure(runSP, codigoProyecto, tipoInforme, nombreObjeto, fechaDesde, fechaHasta, mcaPermisos);

            callableStatement.setString(1, codigoProyecto);
            callableStatement.setString(2, tipoInforme);
            callableStatement.setString(3, nombreObjeto);
            setDate(callableStatement, 4, fechaDesde);
            setDate(callableStatement, 5, fechaHasta);
            callableStatement.setString(6, mcaPermisos);
            callableStatement.registerOutParameter(7, Types.ARRAY, typeLista);

            OutputWarning result = executeStatement(callableStatement);

            OutputConsulta<Informe> output = new OutputConsulta<>();
            output.setOutputWarning(result);
            output.setLista(fromDBListInforme(callableStatement.getArray(7)));

            return output;

        } catch (SQLException e) {
            LogWrapper.error(log, "[InformeService.consultaInforme] Error: %s", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Retorna una lista de informe a partir de lo obtenido desde la BBDD
     *
     * @param callableStatement
     * @param array
     * @return
     * @throws SQLException
     */
    private List<Informe> fromDBListInforme(Array array) throws SQLException {
        List<Informe> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                Informe registro = Informe.builder()
                        .fechaCambio((Date) cols[0])
                        .nombreObjeto((String) cols[1])
                        .tipoObjeto((String) cols[2])
                        .nombreElemento((String) cols[3])
                        .tipoElemento((String) cols[4])
                        .tipoCambio((String) cols[5])
                        .detalle((String) cols[6])
                        .codPeticion((String) cols[7])
                        .codUsr((String) cols[8])
                        .nombreScript((String) cols[9])
                        .versionado((String) cols[10])
                        .build();
                lista.add(registro);
            }
        }
        return lista;
    }
}
