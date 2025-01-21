package com.mdsql.bussiness.service.impl;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.Fichero;
import com.mdsql.bussiness.entities.FicheroAccion;
import com.mdsql.bussiness.entities.FicheroZip;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Results;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.ConfigurationSingleton;
import com.mdval.utils.DateFormatter;
import com.mdval.utils.LogWrapper;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author federico
 *
 */
@Slf4j
public class ServiceSupport {

    protected DateFormatter dateFormatter;

    protected DateFormatter oracleDateFormatter;

    protected ConfigurationSingleton configuration;

    protected int cantParameter;

    @SneakyThrows
    public ServiceSupport() {
        dateFormatter = new DateFormatter();
        oracleDateFormatter = new DateFormatter(MDSQLConstants.ORACLE_OBJECT_DATE_FORMAT_FOR_PROCEDURES);
        configuration = ConfigurationSingleton.getInstance();
    }

    /**
     * @param array
     * @return
     * @throws SQLException
     */
    protected ServiceException buildException(Array array) throws SQLException {
        return buildException((Object[]) array.getArray(), MDSQLConstants.Results.ERROR);
    }

    /**
     * @param array
     * @return
     * @throws SQLException
     */
    protected ServiceException buildWarning(Array array) throws SQLException {
        return buildException((Object[]) array.getArray(), MDSQLConstants.Results.WARN);
    }

    /**
     * @param array
     * @return
     * @throws SQLException
     */
    protected List<Object[]> getWarnings(Array array) throws SQLException {
        Object[] items = (Object[]) array.getArray();

        List<Object[]> errors = new ArrayList<>();
        for (Object row : items) {
            Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
            errors.add(cols);
        }
        return errors;
    }

    /**
     * @param array
     * @return
     * @throws SQLException
     */
    protected ServiceException buildException(Object[] array, Results type) throws SQLException {
        ServiceException exception = new ServiceException(type.getNum());

        List<Object[]> errors = new ArrayList<>();
        for (Object row : array) {
            Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
            errors.add(cols);
        }

        exception.setErrors(errors);
        return exception;
    }

    /**
     * @param runSP
     * @param objects
     */
    protected void logProcedure(String runSP, Object... objects) {
        LogWrapper.debug(log, "%s", runSP);

        if (!Objects.isNull(objects) && objects.length > 0) {
            StringBuilder sbArgumentos = new StringBuilder("Parámetros de entrada: \n");
            for (Object o : objects) {
                String value = (!Objects.isNull(o) && !StringUtils.isBlank(o.toString())) ? o.toString() : "NULL";
                sbArgumentos.append("\t").append(value).append("\n");
            }
            LogWrapper.debug(log, "%s", sbArgumentos.toString().trim());
        }
    }

    /**
     * @param structObjetos
     * @throws SQLException
     */
    public void logArrayStruct(Struct[] structObjetos) throws SQLException {
        StringBuilder sb = new StringBuilder();

        for (Struct obj : structObjetos) {
            for (Object attr : obj.getAttributes()) {
                sb.append("\t").append(attr.toString()).append(",");
            }

            sb.append("\n");
        }

        LogWrapper.debug(log, "\n%s", sb.toString());
    }

    /**
     * @param procedure
     * @param callFormat
     * @return
     */
    @SneakyThrows
    protected String createCall(String procedure, String callFormat) {
        String proc = procedure;//configuration.getConfig(procedure);
        String paquete = configuration.getConfig(MDSQLConstants.PAQUETE);
        String llamada = String.format(MDSQLConstants.FORMATO_LLAMADA, paquete, proc).toUpperCase();
        return String.format(callFormat, llamada);
    }

    /**
     * @return
     */
    @SneakyThrows
    protected String createCallTypeError() {
        String paquete = configuration.getConfig(MDSQLConstants.PAQUETE);
        return String.format(MDSQLConstants.FORMATO_LLAMADA, paquete, MDSQLConstants.T_T_ERROR).toUpperCase();
    }

    /**
     * @param type
     * @return
     */
    @SneakyThrows
    protected String createCallType(String type) {
        String paquete = configuration.getConfig(MDSQLConstants.PAQUETE);
        return String.format(MDSQLConstants.FORMATO_LLAMADA, paquete, type).toUpperCase();
    }

    /**
     * Genera el resultado para un procedimiento de servicio que retorne
     * OutputConsulta
     *
     * @param arrayTipo
     * @param outputWarning
     * @return
     * @throws ServiceException
     */
    public OutputConsulta<CodigoDescripcion> fromDBListCodigoDescripcion(
            Array arrayTipo, OutputWarning outputWarning
    ) throws ServiceException {
        OutputConsulta<CodigoDescripcion> output = new OutputConsulta();
        output.setLista(fromDBListCodigoDescripcion(arrayTipo));
        output.setOutputWarning(outputWarning);
        return output;
    }

    /**
     * Retorna una lista de CodigoDescripcion
     *
     * @param arrayTipo
     * @return
     * @throws ServiceException
     */
    public List<CodigoDescripcion> fromDBListCodigoDescripcion(Array arrayTipo) throws ServiceException {
        try {
            List<CodigoDescripcion> lista = new ArrayList<>();
            if (arrayTipo != null) {
                Object[] rows = (Object[]) arrayTipo.getArray();
                for (Object row : rows) {
                    Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                    String codigo = (String) cols[0];
                    String descripcion = (String) cols[1];
                    lista.add(CodigoDescripcion.builder().
                            codigo(codigo).
                            descripcion(descripcion).build());
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Retorna el tipo OutputWarning a partir de los datos aportados
     *
     * @param result
     * @param arrError
     * @return
     * @throws ServiceException
     */
    public OutputWarning getOutputWarning(Integer result, Array arrError) throws ServiceException {
        try {
            if (result == 0) {
                throw buildException(arrError);
            }
            OutputWarning output = new OutputWarning();
            output.setResult(result);

            // Hay avisos
            if (result == 2) {
                output.setWarnings(buildException(arrError));
            }
            return output;
        } catch (SQLException e) {
            Logger.getLogger(ServiceSupport.class.getName()).log(Level.SEVERE, null, e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param procedure
     * @param cantParam
     * @return
     */
    @SneakyThrows
    protected String createCall(String procedure, int cantParam) {
        String callFormat = getCallFormat(cantParam);
        return createCall(procedure, callFormat);
    }

    /**
     * Construye la cadena con el formato de llamada para cantParam = 2 retorna
     * {call %s(?,?)}
     *
     * @param cantParam
     * @return
     */
    private String getCallFormat(int cantParameter) {
        this.cantParameter = cantParameter;
        if (cantParameter <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{call %s(");
        for (int i = 0; i < cantParameter; i++) {
            sb.append("?");
            if (i < cantParameter - 1) {
                sb.append(",");
            }
        }
        sb.append(")}");
        return sb.toString();
    }

    /**
     * Ejecuta la sentencia y retorna el resultado de la ejecución, asume que el
     * penúltimo parámetro es Result y el último la lista de errores IMPORTANTE:
     * Se debe haber creado el stamente con createCall(String procedure, int
     * cantParam)
     *
     * @param callableStatement
     * @return
     * @throws ServiceException
     */
    public OutputWarning executeStatement(CallableStatement callableStatement) throws ServiceException {
        try {
            String typeError = createCallTypeError();
            callableStatement.registerOutParameter(cantParameter - 1, Types.INTEGER); // Result
            callableStatement.registerOutParameter(cantParameter, Types.ARRAY, typeError);

            callableStatement.execute();

            int result = callableStatement.getInt(cantParameter - 1);
            Array arrError = callableStatement.getArray(cantParameter);

            return getOutputWarning(result, arrError);

        } catch (SQLException e) {
            Logger.getLogger(ServiceSupport.class.getName()).log(Level.SEVERE, null, e);
            throw new ServiceException(e);
        }
    }

    public List<Estado> fromDBListEstado(CallableStatement callableStatement, Array array) throws SQLException {
        List<Estado> estados = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                Estado estado = Estado.builder()
                        .codigoEstado((BigDecimal) cols[0])
                        .descripcionEstado((String) cols[1])
                        .build();
                estados.add(estado);
            }
        }
        return estados;
    }

    /**
     *
     * @param array
     * @param emptyFirst
     * @return
     * @throws java.sql.SQLException
     */
    public List<String> fromDBListString(Array array, boolean emptyFirst) throws SQLException {
        List<String> lista = new ArrayList<>();
        if (emptyFirst) {
            lista.add(StringUtils.EMPTY);
        }

        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                lista.add((String) cols[0]);
            }
        }
        return lista;
    }

    /**
     * Establece la fecha para el llamado a Oracle, recibe String en formato
     * DD/MM/YYYY
     *
     * @param callableStatement
     * @param index
     * @param strFechaDDMMYYYY
     * @throws ServiceException
     * @throws SQLException
     */
    public void setDate(CallableStatement callableStatement, int index, String strFechaDDMMYYYY) throws ServiceException, SQLException {
        if (strFechaDDMMYYYY != null && !strFechaDDMMYYYY.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha;
            try {
                fecha = sdf.parse(strFechaDDMMYYYY);
                callableStatement.setDate(index, new java.sql.Date(fecha.getTime()));
            } catch (ParseException ex) {
                throw new ServiceException("Error al convertir la fecha " + strFechaDDMMYYYY + ", el formato esperado es DD/MM/YYYY");
            }
        } else {
            callableStatement.setDate(index, null);
        }
    }

    /**
     * Convierte un Array de BBDD una lista de FicheroAccion
     *
     * @param array
     * @return
     * @throws SQLException
     */
    public List<FicheroAccion> fromDBListFicheroAccion(Array array) throws SQLException {

        List<FicheroAccion> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                FicheroAccion f = FicheroAccion.builder()
                        .nombreOrigen((String) cols[0])
                        .nombreDestino((String) cols[1])
                        .codAccion((String) cols[2])
                        .build();
                lista.add(f);
            }
        }
        return lista;
    }

    /**
     * Convierte un Array de BBDD una lista de FicheroZip
     *
     * @param array
     * @return
     * @throws SQLException
     */
    public List<FicheroZip> fromDBListFicheroZip(Array array) throws SQLException {

        List<FicheroZip> lista = new ArrayList<>();
        if (array != null) {
            Object[] rows = (Object[]) array.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();
                FicheroZip f = new FicheroZip();
                f.setNombre((String) cols[0]);
                f.setFicheros(fromDBListFichero((Array) cols[1]));
                lista.add(f);
            }
        }
        return lista;
    }

    private List<Fichero> fromDBListFichero(Array array) throws SQLException {
        try {
            if (!Objects.isNull(array)) {
                List<Fichero> listFichero = new ArrayList<>();
                Object[] subs = (Object[]) array.getArray();
                for (Object sub : subs) {
                    // Object[] nombres = ((oracle.jdbc.OracleStruct) sub).getAttributes();
                    String nombre = (String) sub;
                    Fichero fichero = Fichero.builder().nombre(nombre).build();
                    listFichero.add(fichero);
                }
                return listFichero;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LogWrapper.error(log, "[ServiceSupport.fromDBListFichero] Error: %s", e.getMessage());
        }
        return null;
    }

    /**
     * Convierte un Array de BBDD una lista de Avisos
     *
     * @param arrayAvisos
     * @return List de Aviso
     * @throws SQLException
     */
    public List<Aviso> fromDBListAviso(Array arrayAvisos) throws SQLException {
        List<Aviso> avisos = new ArrayList<>();
        if (arrayAvisos != null) {
            Object[] rows = (Object[]) arrayAvisos.getArray();
            for (Object row : rows) {
                Object[] cols = ((oracle.jdbc.OracleStruct) row).getAttributes();

                Aviso aviso = Aviso.builder()
                        .codigoAviso((BigDecimal) cols[2])
                        .titulo((String) cols[3])
                        .descripcion((String) cols[4])
                        .codigoPeticion((String) cols[5])
                        .fechaAlta((java.util.Date) cols[6])
                        .codigoUsrAlta((String) cols[7])
                        .mcaHabilitado((String) cols[8])
                        .fechaActualizacion((Date) cols[9])
                        .codigoUsuario((String) cols[10])
                        .nombreObjeto((String) cols[11])
                        .build();

                NivelImportancia nivelImportancia = NivelImportancia.builder()
                        .codigoNivelAviso((BigDecimal) cols[0])
                        .descripcionNivelAviso((String) cols[1])
                        .build();
                aviso.setNivelImportancia(nivelImportancia);

                avisos.add(aviso);
            }
        }
        return avisos;
    }

}
