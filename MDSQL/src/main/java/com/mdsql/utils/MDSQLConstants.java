package com.mdsql.utils;

import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.utils.Constants;
import java.awt.Color;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.Getter;

/**
 * @author federico
 *
 */
public class MDSQLConstants extends Constants {

    public static final String VERSION = "2.0";

    public static final Charset CHARSET_UNX = StandardCharsets.UTF_8;
    public static final Charset CHARSET_WIN = Charset.forName("windows-1252");
    public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    public static final String CP_1252 = "Cp1252";
    public static final String UTF8_SINBOM = "UTF-8";
    public static final String CR = "\r\n";

    public static final Charset[] ALLOWED_CHARSETS = {DEFAULT_CHARSET, StandardCharsets.UTF_8,
        StandardCharsets.ISO_8859_1, CHARSET_WIN};

    public static final String[] TIPOS_SCRIPT_HISTORICO = {"SQLH", "PDCH"};
    public static final String[] TIPOS_SCRIPT_VIGENTE = {"SQL", "PDC"};
    /**
     * App globals
     */
    public static final String ORACLE_OBJECT_DATE_FORMAT_FOR_PROCEDURES = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_FECHA_BUSCADOR_PETICIONES = "yyyyMMdd";
    public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String INFORME_DATE_FORMAT = "yyyyMMdd";
    public static final String SELECTED_ROUTE = "selectedRoute";
    public static final String TOKEN = "TOKEN";
    public static final String PROCESADO_EN_CURSO = "PROCESADO_EN_CURSO";

    public static enum Results {
        ERROR(0, "ERROR"), OK(1, "OK"), WARN(2, "WARN");

        @Getter
        private Integer num;

        @Getter
        private String tipo;

        private Results(Integer num, String tipo) {
            this.num = num;
            this.tipo = tipo;
        }

        public static Results getByTipo(String tipo) {
            for (Results result : Results.values()) {
                if (result.tipo.equals(tipo)) {
                    return result;
                }
            }

            return null;
        }
    }

    /**
     * Report templates
     */
    public static final String LISTADO_HISTORICO_CAMBIOS_TEMPLATE_LOCATION = "/templates/ListadoHistoricoCambios.xlt";
    public static final String LISTADO_HISTORICO_TEMPLATE_LOCATION = "/templates/ListadoHistorico.xlt";
    public static final String LISTADO_SINONIMOS_TEMPLATE_LOCATION = "/templates/ListadoSinonimos.xlt";
    public static final String LISTADO_PERMISOS_TEMPLATE_LOCATION = "/templates/ListadoPermisos.xlt";

    /**
     * Bean names
     */
    public static final String EXCEL_GENERATOR_SERVICE = "excelGeneratorService";
    public static final String TIPO_ELEMENTO_SERVICE = "tipoElementoService";
    public static final String TIPO_DATO_SERVICE = "tipoDatoService";
    public static final String MODELO_SERVICE = "modeloService";
    public static final String INFORME_SERVICE = "informeService";

    // Servicios
    public static final String SCRIPT_SERVICE = "scriptService";
    public static final String PROCESO_SERVICE = "procesoService";
    public static final String AVISO_SERVICE = "avisoService";
    public static final String TYPE_SERVICE = "typeService";
    public static final String BBDD_SERVICE = "bbddService";
    public static final String HISTORICO_SERVICE = "historicoService";
    public static final String UTILS_SERVICE = "utilsService";
    public static final String EJECUCION_SERVICE = "ejecucionService";
    public static final String CUADRE_SERVICE = "cuadreService";
    public static final String ENTREGA_SERVICE = "entregaService";
    public static final String ERRORES_SERVICE = "erroresService";
    public static final String LOG_SERVICE = "logService";
    public static final String CONSULTA_SERVICE = "consultaService";
    public static final String PERMISOS_SERVICE = "permisosService";
    public static final String TIPO_OBJETO_SERVICE = "tipoObjetoService";
    public static final String PROPIETARIO_SERVICE = "propietarioService";
    public static final String ENTORNO_SERVICE = "entornoService";
    public static final String ENTORNOS_PRUEBA_SERVICE = "entornosPruebaService";
    public static final String REENTRANTE_SERVICE = "REENTRANTE_SERVICE";
    public static final String PERMISOS_OBJETO_SERVICE = "PERMISOS_OBJETO_SERVICE";
    public static final String PERMISOS_COLUMNA_SERVICE = "PERMISOS_COLUMNA_SERVICE";
    public static final String PERMISOS_PERSONALIZADOS_SERVICE = "PERMISOS_PERSONALIZADOS_SERVICE";
    public static final String SUBJECTAREA_DIAGRAMA_SERVICE = "SUBJECTAREA_DIAGRAMA_SERVICE";
    public static final String VALIDACION_SERVICE = "VALIDACION_SERVICE";

    /**
     * DataBase Types and constants
     */
    public static final String FORMATO_CONEXION = "%s/%s@%s";
    public static final String FORMATO_FICHERO = "@\"%s\"";
    public static final String SQL_PLUS = "sqlplus";

    // Tipos de datos de retorno
    public static final String T_R_LINEA = "T_R_LINEA";
    public static final String T_T_LINEA = "T_T_LINEA";
    public static final String T_T_ELEMENTO = "T_T_ELEMENTO";
    public static final String T_T_TIPO_DATO = "T_T_TIPO_DATO";
    public static final String T_R_SUBPROYECTO = "T_R_SUBPROYECTO";
    public static final String T_T_SUBPROYECTO = "T_T_SUBPROYECTO";
    public static final String T_T_MODELO = "T_T_MODELO";
    public static final String T_T_CAMPO_GLOSARIO = "T_T_CAMPO_GLOSARIO";
    public static final String T_T_DET_VALIDACION = "T_T_DET_VALIDACION";
    public static final String T_T_ERROR = "T_T_ERROR";
    public static final String T_T_DET_OBJETO = "T_T_DET_OBJETO";
    public static final String T_T_HIS_PROC = "T_T_HIS_PROC";
    public static final String T_T_INFORME_CAMBIOS = "T_T_INFORME_CAMBIOS";
    public static final String T_T_LOG_EJECUCION = "T_T_LOG_EJECUCION";
    public static final String T_T_TIP_OBJETO = "T_T_TIP_OBJETO";
    public static final String T_T_TIPO_VBLE = "T_T_TIPO_VBLE";
    public static final String T_T_ESTADO = "T_T_ESTADO";
    public static final String T_T_OPERACION = "T_T_OPERACION";

    // nuevas constantes types BBDD
    public static final String T_T_PROCESO = "T_T_PROCESO";
    public static final String T_T_AVISO = "T_T_AVISO";
    public static final String T_T_BBDD = "T_T_BBDD";
    public static final String T_T_OBJ_HIS = "T_T_OBJ_HIS";
    public static final String T_R_OBJ_HIS = "T_R_OBJ_HIS";
    public static final String T_T_SCRIPT = "T_T_SCRIPT";
    public static final String T_R_SCRIPT = "T_R_SCRIPT";
    public static final String T_T_OBJETOS = "T_T_OBJETOS";
    public static final String T_R_OBJETOS = "T_R_OBJETOS";
    public static final String T_T_SCRIPT_EJEC = "T_T_SCRIPT_EJEC";
    public static final String T_T_CUADRE_OPER = "T_T_CUADRE_OPER";
    public static final String T_T_CUADRE_OBJ = "T_T_CUADRE_OBJ";
    public static final String T_T_ERROR_SCRIPT = "T_T_ERROR_SCRIPT";
    public static final String T_T_SCRIPT_PARCHE = "T_T_SCRIPT_PARCHE";
    public static final String T_T_SCRIPT_OLD = "T_T_SCRIPT_OLD";
    public static final String T_T_DET_OBJ_HIS = "T_T_DET_OBJ_HIS";
    public static final String T_T_NIVEL_AVISO = "T_T_NIVEL_AVISO";
    public static final String T_T_ENTORNO = "T_T_ENTORNO";
    public static final String T_T_ENTORNO_PRUEBA = "T_T_ENTORNO_PRUEBA";
    public static final String T_T_VARIABLE = "T_T_VARIABLE";
    public static final String T_T_OWNER_SYN = "T_T_OWNER_SYN";
    public static final String T_T_USR_GRANT = "T_T_USR_GRANT";
    public static final String T_T_PERMISO = "T_T_PERMISO";
    public static final String T_T_PERMISO_GEN = "T_T_PERMISO_GEN";
    public static final String T_T_SINONIMO_GEN = "T_T_SINONIMO_GEN";
    public static final String T_T_SUBJECT_AREA = "T_T_SUBJECT_AREA";
    public static final String T_T_TYPE = "T_T_TYPE";
    public static final String T_T_DIAGRAMA = "T_T_DIAGRAMA";
    public static final String T_T_TABLAS_DIAGRAMA = "T_T_TABLAS_DIAGRAMA";
    public static final String T_T_PERMISO_COL = "T_T_PERMISO_COL";
    public static final String T_T_PROC_PETI = "T_T_PROC_PETI";
    public static final String T_T_FICHERO_ACCION = "T_T_FICHERO_ACCION";
    public static final String T_T_FICHERO_ZIP = "T_T_FICHERO_ZIP";
    public static final String T_T_INFORME = "T_T_INFORME";
    public static final String T_T_TIPOS_INFORME = "T_T_TIPOS_INFORME";
    public static final String T_T_PERMISO_OBJ = "T_T_PERMISO_OBJ";
    public static final String T_T_SINONIMO_OBJ = "T_T_SINONIMO_OBJ";
    public static final String T_T_TIPO_REENTRANTE = "T_T_TIPO_REENTRANTE";
    public static final String T_R_DATOS_REENTRANTE = "T_R_DATOS_REENTRANTE";
    public static final String T_T_DATOS_REENTRANTE = "T_T_DATOS_REENTRANTE";
    public static final String T_T_PARAM_REENTRANTE = "T_T_PARAM_REENTRANTE";
    public static final String T_T_CMT_COLUMNAS = "T_T_CMT_COLUMNAS";
    public static final String T_R_CMT_COLUMNAS = "T_R_CMT_COLUMNAS";
    public static final String T_T_CONSULTAS_BD = "T_T_CONSULTAS_BD";
    public static final String T_T_LANZA = "T_T_LANZA";
    public static final String T_T_SCRIPT_PETI = "T_T_SCRIPT_PETI";
    public static final String T_T_VAL_PROG = "T_T_VAL_PROG";
    public static final String T_R_PROC_MODELO = "T_R_PROC_MODELO";
    public static final String T_T_PROC_MODELO = "T_T_PROC_MODELO";
    public static final String T_T_HISTORICO_PROC = "T_T_HISTORICO_PROC";

    // CONSTANTES DE UI
    // Tecla Enter, para los botones
    // public static final String KEY_ENTER = "ENTER";
    // Colores de celdas de aviso
    public enum ColorCeldaNota {
        BAJA(4, "Baja", Color.WHITE), MEDIA(3, "Media", Color.ORANGE), ALTA(2, "Alta", Color.YELLOW),
        CRITICA(1, "Crítica", Color.RED);

        @Getter
        private Integer orden;

        @Getter
        private String name;

        @Getter
        private Color value;

        ColorCeldaNota(Integer orden, String name, Color value) {
            this.orden = orden;
            this.name = name;
            this.value = value;
        }

        public static ColorCeldaNota getByName(String name) {
            for (ColorCeldaNota colorCelda : ColorCeldaNota.values()) {
                if (colorCelda.name.equals(name)) {
                    return colorCelda;
                }
            }
            return null;
        }

        public static ColorCeldaNota getByOrden(Integer orden) {
            for (ColorCeldaNota colorCelda : ColorCeldaNota.values()) {
                if (colorCelda.orden.equals(orden)) {
                    return colorCelda;
                }
            }
            return null;
        }
    }

    public static final Color CELL_SELECTED_BGCOLOR = new Color(184, 207, 229);
    public static final Color TEXT_DISABLED_BGCOLOR = new Color(242, 242, 242);

    // Modos para el procesado
    public enum Procesado {
        SCRIPT, TYPE, REENTRANTE
    }

    // Estados del procesado
    public enum EstadosProcesado {
        SINVALOR(0, " ", null), GENERADO(1, "Generado", null),
        EN_EJECUCION(2, "En ejecución", Color.WHITE), ERROR(3, "Error", Color.RED),
        EJECUTADO(4, "Ejecutado", Color.GREEN), RECHAZADO(5, "Rechazado", Color.YELLOW),
        ENTREGADO(6, "Entregado", Color.BLUE), CERRADO(7, "Cerrado", Color.CYAN),
        INCIDENCIA(8, "Incidencia", Color.RED), EXCLUIDO(9, "Excluido", Color.ORANGE);

        @Getter
        private String name;
        @Getter
        private Integer index;
        @Getter
        private Color color;

        EstadosProcesado(Integer index, String name, Color color) {
            this.index = index;
            this.name = name;
            this.color = color;
        }

        public static EstadosProcesado getByName(String name) {
            for (EstadosProcesado estadosProcesado : EstadosProcesado.values()) {
                if (estadosProcesado.name.equals(name)) {
                    return estadosProcesado;
                }
            }
            return null;
        }

        public static EstadosProcesado getByCode(BigDecimal codigo) {
            for (EstadosProcesado estadosProcesado : EstadosProcesado.values()) {
                if (estadosProcesado.getIndex() == codigo.intValue()) {
                    return estadosProcesado;
                }
            }
            return null;
        }

    }

    // Estados del script
    public enum EstadosScript {
        SINVALOR(0, " ", Color.GRAY), PENDIENTE(1, "Pendiente", Color.WHITE), EJECUTADO(2, "Ejecutado", Color.GREEN), ERROR(3, "Error", Color.RED),
        DESCUADRADO(4, "Descuadrado", new Color(229, 206, 184)), REPARADO(5, "Reparado", Color.BLUE),
        DESCARTADO(6, "Descartado", Color.ORANGE), EXCEPCION(7, "Excepción", Color.YELLOW);

        @Getter
        private Integer index;

        @Getter
        private String name;

        @Getter
        private Color color;

        EstadosScript(Integer index, String name, Color color) {
            this.index = index;
            this.name = name;
            this.color = color;
        }

        public static EstadosScript getByName(String name) {
            for (EstadosScript estadosScript : EstadosScript.values()) {
                if (estadosScript.name.equals(name)) {
                    return estadosScript;
                }
            }
            return null;
        }
    }

    // Estados RDO para Reentrantes
    public enum EstadosRDO {
        OK, NOK, PER, NEW
    }

    public enum TipoComentarioColumnaReentrante {
        PERSONALIZADO("P", "lblPersonalizado"), GENERICO("G", "lblGenerico");
        @Getter
        String codigo;

        @Getter
        String nombre;

        TipoComentarioColumnaReentrante(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = MDSQLUIHelper.getKeyTextValue(nombre);
        }
    }

    /**
     * Menu de la aplicación
     */
    public static final String MNU_PERMISOS_GENERALES = "MNU_PERMISOS_GENERALES";
    public static final String MNU_ENTORNOS = "MNU_ENTORNOS";
    public static final String MNU_VARIABLES = "MNU_VARIABLES";
    public static final String MNU_CONSULTA_PERMISOS = "MNU_CONSULTA_PERMISOS";
    public static final String MNU_MANTENIMIENTO_PERMISOS = "MNU_MANTENIMIENTO_PERMISOS";
    public static final String MNU_GENERAR_PERMISOS = "MNU_GENERAR_PERMISOS";

    public static final String MNU_CONSULTA_HISTORICO_CAMBIOS = "MNU_CONSULTA_HISTORICO_CAMBIOS";
    public static final String MNU_CONSULTA_HISTORICO_CAMBIOS_MODELO = "MNU_CONSULTA_HISTORICO_CAMBIOS_MODELO";
    public static final String MNU_CONSULTA_PETICIONES = "MNU_CONSULTA_PETICIONES";

    public static final String MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS = "MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS";
    public static final String MNU_MANTENIMIENTO_SCRIPT_INICIAL = "MNU_MANTENIMIENTO_SCRIPT_INICIAL";
    public static final String MNU_EJECUCION_SCRIPT_INICIAL = "MNU_EJECUCION_SCRIPT_INICIAL";
    public static final String MNU_NOTAS_MODELOS = "MNU_NOTAS_MODELOS";
    public static final String MNU_CONFIGURACION_ENTORNOS_PRUEBA = "MNU_CONFIGURACION_ENTORNOS_PRUEBA";
    public static final String MNU_MANTENIMIENTO_HISTORICO = "MNU_MANTENIMIENTO_HISTORICO";

    public static final String MNU_AVISOS_VALIDACIONES = "MNU_AVISOS_VALIDACIONES";
    public static final String MNU_AVISOS_OBJETO = "MNU_AVISOS_OBJETO";
    public static final String MNU_VALIDACIONES_PROGRAMADAS = "MNU_VALIDACIONES_PROGRAMADAS";
    public static final String MNU_DIAGRAMAS = "MNU_DIAGRAMAS";
    public static final String MNU_MANTENIMIENTO_DIAGRAMAS = "MNU_MANTENIMIENTO_DIAGRAMAS";
    public static final String MNU_CONSULTA_DIAGRAMAS = "MNU_CONSULTA_DIAGRAMAS";

    /**
     * Comandos que activan dialogos
     */
    public static final String CMD_EJECUTAR_SCRIPT = "CMD_EJECUTAR_SCRIPT";

    public static final String CMD_ALTA_MODELOS = "CMD_ALTA_MODELOS";
    public static final String CMD_MODIFICACION_MODELOS = "CMD_MODIFICACION_MODELOS";
    public static final String CMD_BUSCAR_MODELOS = "CMD_BUSCAR_MODELOS";
    public static final String CMD_PROCESADO_EN_CURSO = "CMD_PROCESADO_EN_CURSO";
    public static final String CMD_ENTREGAR_SCRIPT = "CMD_ENTREGAR_SCRIPT";
    public static final String CMD_RECHAZAR_PROCESADO = "CMD_RECHAZAR_PROCESADO";
    public static final String CMD_EJECUTAR_TYPE = "CMD_EJECUTAR_TYPE";
    public static final String CMD_AJUSTAR_LOG_EJECUCION = "CMD_AJUSTAR_LOG_EJECUCION";
    public static final String CMD_LIMPIAR_SESION = "CMD_LIMPIAR_SESION";
    public static final String CMD_LIMPIAR_SCRIPT = "CMD_LIMPIAR_SCRIPT";

    /**
     * Panel principal
     */
    public static final String PANEL_PRINCIPAL_BTN_SEARCH = "PANEL_PRINCIPAL_BTN_SEARCH";
    public static final String PANEL_PRINCIPAL_BTN_LOAD_SCRIPT = "PANEL_PRINCIPAL_BTN_LOAD_SCRIPT";
    public static final String PANEL_PRINCIPAL_BTN_VALIDAR = "PANEL_PRINCIPAL_BTN_VALIDAR";
    public static final String PANEL_PRINCIPAL_BTN_LIMPIAR_VALIDACION = "PANEL_PRINCIPAL_BTN_LIMPIAR_VALIDACION";
    public static final String PANEL_PRINCIPAL_BTN_LIMPIAR_TODO = "PANEL_PRINCIPAL_BTN_LIMPIAR_TODO";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_BUSCAR = "PANTALLA_SELECCION_MODELOS_BTN_BUSCAR";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_NOTAS = "PANTALLA_SELECCION_MODELOS_BTN_NOTAS";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES = "PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_COLUMNA = "PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_COLUMNA";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_OBJETO = "PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_OBJETO";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR = "PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR";
    public static final String PANTALLA_SELECCION_MODELOS_BTN_VARIABLES = "PANTALLA_SELECCION_MODELOS_BTN_VARIABLES";

    public static final String PANTALLA_SELECCION_HISTORICA_BTN_ADD = "PANTALLA_SELECCION_HISTORICA_BTN_ADD";
    public static final String PANTALLA_SELECCION_HISTORICA_BTN_GENERAR = "PANTALLA_SELECCION_HISTORICA_BTN_GENERAR";
    public static final String PANTALLA_SELECCION_HISTORICA_BTN_CANCELAR = "PANTALLA_SELECCION_HISTORICA_BTN_CANCELAR";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_RECHAZAR = "FRM_DEFINICION_SCRIPTS_BTN_RECHAZAR";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_VER_LOG = "FRM_DEFINICION_SCRIPTS_BTN_VER_LOG";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_DETALLE_SCRIPT = "FRM_DEFINICION_SCRIPTS_BTN_DETALLE_SCRIPT";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_DESCARTAR = "FRM_DEFINICION_SCRIPTS_BTN_DESCARTAR";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_REPARAR = "FRM_DEFINICION_SCRIPTS_BTN_REPARAR";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_VER_CUADROS = "FRM_DEFINICION_SCRIPTS_BTN_VER_CUADROS";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_VER_ERRORES = "FRM_DEFINICION_SCRIPTS_BTN_VER_ERRORES";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_EXCEPCION = "FRM_DEFINICION_SCRIPTS_BTN_EXCEPCION";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_ACEPTAR = "FRM_DEFINICION_SCRIPTS_BTN_ACEPTAR";
    public static final String FRM_DEFINICION_SCRIPTS_BTN_CANCELAR = "FRM_DEFINICION_SCRIPTS_BTN_CANCELAR";

    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_RECHAZAR = "PANTALLA_EJECUTAR_SCRIPTS_BTN_RECHAZAR";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_LOG = "PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_LOG";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_DETALLE_SCRIPT = "PANTALLA_EJECUTAR_SCRIPTS_BTN_DETALLE_SCRIPT";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_DESCARTAR = "PANTALLA_EJECUTAR_SCRIPTS_BTN_DESCARTAR";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_REPARAR = "PANTALLA_EJECUTAR_SCRIPTS_BTN_REPARAR";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_CUADRES = "PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_CUADRES";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_ERRORES = "PANTALLA_EJECUTAR_SCRIPTS_BTN_VER_ERRORES";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_EXCEPCION = "PANTALLA_EJECUTAR_SCRIPTS_BTN_EXCEPCION";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR = "PANTALLA_EJECUTAR_SCRIPTS_BTN_ACEPTAR";
    public static final String PANTALLA_EJECUTAR_SCRIPTS_BTN_CANCELAR = "PANTALLA_EJECUTAR_SCRIPTS_BTN_CANCELAR";
    public static final String FRAME_PRINCIPAL_LOAD_SCRIPT = "FRAME_PRINCIPAL_LOAD_SCRIPT";
    public static final String FRAME_PRINCIPAL_CARGAR_SCRIPT_OBJETOS = "FRAME_PRINCIPAL_CARGAR_SCRIPT_OBJETOS";
    public static final String FRAME_PRINCIPAL_PROCESAR_SCRIPT = "FRAME_PRINCIPAL_PROCESAR_SCRIPT";
    public static final String FRAME_PRINCIPAL_SAVE = "FRAME_PRINCIPAL_SAVE";
    public static final String FRAME_PRINCIPAL_EXECUTE = "FRAME_PRINCIPAL_EXECUTE";
    public static final String FRAME_PRINCIPAL_ENTREGAR_PROCESADO = "FRAME_PRINCIPAL_ENTREGAR_PROCESADO";
    public static final String FRAME_PRINCIPAL_LIMPIAR_SCRIPT = "FRAME_PRINCIPAL_LIMPIAR_SCRIPT";
    public static final String FRAME_PRINCIPAL_LIMPIAR_SESION = "FRAME_PRINCIPAL_LIMPIAR_SESION";
    public static final String FRAME_PRINCIPAL_BTN_UNDO = "FRAME_PRINCIPAL_BTN_UNDO";
    public static final String FRAME_PRINCIPAL_BTN_REDO = "FRAME_PRINCIPAL_BTN_REDO";
    public static final String FRAME_PRINCIPAL_BTN_CUT = "FRAME_PRINCIPAL_BTN_CUT";
    public static final String FRAME_PRINCIPAL_BTN_COPY = "FRAME_PRINCIPAL_BTN_COPY";
    public static final String FRAME_PRINCIPAL_BTN_PASTE = "FRAME_PRINCIPAL_BTN_PASTE";
    public static final String FRAME_PRINCIPAL_BTN_CERRAR = "FRAME_PRINCIPAL_BTN_CERRAR";
    public static final String FRAME_PRINCIPAL_PROCESADO_CURSO = "FRAME_PRINCIPAL_PROCESADO_CURSO";
    public static final String FRAME_PRINCIPAL_REFRESCAR_FICHERO = "FRAME_PRINCIPAL_REFRESCAR_FICHERO";
    public static final String FRAME_PRINCIPAL_INFORMACION_MODELO = "FRAME_PRINCIPAL_INFORMACION_MODELO";
    public static final String FRAME_PRINCIPAL_LOAD_REENTRANTE = "FRAME_PRINCIPAL_LOAD_REENTRANTE";
    public static final String FRAME_PRINCIPAL_COMENTARIO_REENTRANTE = "FRAME_PRINCIPAL_COMENTARIO_REENTRANTE";

    public static final String PANTALLA_INFORMACION_MODELO_SCRIPT_LIMPIAR = "PANTALLA_INFORMACION_MODELO_SCRIPT_LIMPIAR";
    public static final String PANTALLA_INFORMACION_MODELO_VER_PROCESADO = "PANTALLA_INFORMACION_MODELO_VER_PROCESADO";

    public static final String PANTALLA_PROCESADO_SCRIPT_SEARCH_MODEL = "PANTALLA_PROCESADO_SCRIPT_SEARCH_MODEL";
    public static final String PANTALLA_PROCESADO_SCRIPT_CANCELAR = "PANTALLA_PROCESADO_SCRIPT_CANCELAR";
    public static final String PANTALLA_PROCESADO_SCRIPT_PROCESAR = "PANTALLA_PROCESADO_SCRIPT_PROCESAR";
    public static final String PANTALLA_PROCESADO_SCRIPT_LIMPIAR = "PANTALLA_PROCESADO_SCRIPT_LIMPIAR";
    public static final String PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO = "PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO";

    public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_RECHAZAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_RECHAZAR";
    public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_CUADRES = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_CUADRES";
    public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_ERRORES = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_ERRORES";
    public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_ACEPTAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_ACEPTAR";
    public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_CANCELAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_CANCELAR";

    public static final String PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR = "PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR";
    public static final String PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES = "PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES";
    public static final String PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES = "PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES";
    public static final String PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR = "PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR";
    public static final String PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR = "PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR";

    public static final String PANTALLA_PROCESADO_CURSO_VER_LOG = "PANTALLA_PROCESADO_CURSO_VER_LOG";
    public static final String PANTALLA_PROCESADO_CURSO_VER_ERRORES = "PANTALLA_PROCESADO_CURSO_VER_ERRORES";
    public static final String PANTALLA_PROCESADO_CURSO_DETALLE_SCRIPT = "PANTALLA_PROCESADO_CURSO_DETALLE_SCRIPT";
    public static final String PANTALLA_PROCESADO_CURSO_ENTREGAR = "PANTALLA_PROCESADO_CURSO_ENTREGAR";
    public static final String PANTALLA_PROCESADO_CURSO_CANCELAR = "PANTALLA_PROCESADO_CURSO_CANCELAR";

    public static final String PANTALLA_RESUMEN_PROCESADO_ENTREGAR = "PANTALLA_RESUMEN_PROCESADO_ENTREGAR";
    public static final String PANTALLA_RESUMEN_PROCESADO_VER_ERRORES = "PANTALLA_RESUMEN_PROCESADO_VER_ERRORES";
    public static final String PANTALLA_RESUMEN_PROCESADO_DETALLE_SCRIPT = "PANTALLA_RESUMEN_PROCESADO_DETALLE_SCRIPT";
    public static final String PANTALLA_RESUMEN_PROCESADO_VER_LOG = "PANTALLA_RESUMEN_PROCESADO_VER_LOG";

    public static final String PANTALLA_REPARAR_SCRIPT_BTN_ACEPTAR = "PANTALLA_REPARAR_SCRIPT_BTN_ACEPTAR";
    public static final String PANTALLA_REPARAR_SCRIPT_BTN_CANCELAR = "PANTALLA_REPARAR_SCRIPT_BTN_CANCELAR";
    public static final String PANTALLA_REPARAR_SCRIPT_RBTN_REPROCESAR_SCRIPT = "PANTALLA_REPARAR_SCRIPT_RBTN_REPROCESAR_SCRIPT";
    public static final String PANTALLA_REPARAR_SCRIPT_RBTN_NO_REPROCESAR_SCRIPT = "PANTALLA_REPARAR_SCRIPT_RBTN_NO_REPROCESAR_SCRIPT";
    public static final String PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_PROCESADO = "PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_PROCESADO";
    public static final String PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_REPARACION = "PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_REPARACION";
    public static final String PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO = "PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO";
    public static final String PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO_REPARACION = "PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO_REPARACION";

    public static final String PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PROCESAR = "PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PROCESAR";
    public static final String PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PARCHE = "PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PARCHE";
    public static final String PANTALLA_DESCARTAR_SCRIPT_RBTN_REDUCIR = "PANTALLA_DESCARTAR_SCRIPT_RBTN_REDUCIR";
    public static final String PANTALLA_DESCARTAR_SCRIPT_RBTN_AMPLIAR = "PANTALLA_DESCARTAR_SCRIPT_RBTN_AMPLIAR";
    public static final String PANTALLA_DESCARTAR_SCRIPT_BTN_ACEPTAR = "PANTALLA_DESCARTAR_SCRIPT_BTN_ACEPTAR";
    public static final String PANTALLA_DESCARTAR_SCRIPT_BTN_CANCELAR = "PANTALLA_DESCARTAR_SCRIPT_BTN_CANCELAR";

    public static final String PANTALLA_RESUMEN_PROCESADO_CANCELAR = "PANTALLA_RESUMEN_PROCESADO_CANCELAR";

    public static final String PANTALLA_DETALLE_SCRIPT_CANCELAR = "PANTALLA_DETALLE_SCRIPT_CANCELAR";

    public static final String PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO = "PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO";
    public static final String PANTALLA_HISTORICO_CAMBIOS_BUSCAR = "PANTALLA_HISTORICO_CAMBIOS_BUSCAR";
    public static final String PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS = "PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS";
    public static final String PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT = "PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT";
    public static final String PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO = "PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO";
    public static final String PANTALLA_HISTORICO_CAMBIOS_CANCELAR = "PANTALLA_HISTORICO_CAMBIOS_CANCELAR";

    public static final String PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR = "PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR";
    public static final String PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR = "PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR";

    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO = "PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO";
    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR = "PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR";
    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_ALTA = "PANTALLA_MANTENIMIENTO_HISTORICO_ALTA";
    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BAJA = "PANTALLA_MANTENIMIENTO_HISTORICO_BAJA";
    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_INFORME = "PANTALLA_MANTENIMIENTO_HISTORICO_INFORME";
    public static final String PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR = "PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR";

    public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR";
    public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR";
    public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO";

    public static final String PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR = "PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR";
    public static final String PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR = "PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR";

    public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR";
    public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR";
    public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR";

    public static final String PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR = "PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR";
    public static final String PANTALLA_HISTORICO_BAJA_BTN_CANCELAR = "PANTALLA_HISTORICO_BAJA_BTN_CANCELAR";

    public static final String PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR = "PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR";
    public static final String PANTALLA_HISTORICO_ALTA_BTN_CANCELAR = "PANTALLA_HISTORICO_ALTA_BTN_CANCELAR";

    public static final String PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO = "PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO";

    // Posibles valores para el parámetro de salida P_OUT_EXIT_BUTTON
    public static final String BTN_GUARDAR = "BTN_GUARDAR";
    public static final String BTN_ACEPTAR = "BTN_ACEPTAR";
    public static final String BTN_CANCELAR = "BTN_CANCELAR";
    public static final String BTN_EJECUTAR = "BTN_EJECUTAR";

    //Parametros de entrada de los formularios
    public static final String P_IN_MODELO = "modelo";
    public static final String P_IN_COD_MODELO = "P_IN_COD_MODELO";
    public static final String P_IN_OPCION_MENU = "P_IN_OPCION_MENU";
    public static final String P_IN_OBJETO = "P_IN_OBJETO";
    public static final String P_IN_NOM_OBJETO = "P_IN_NOM_OBJETO";
    public static final String P_IN_SUB_MODELO = "P_IN_SUB_MODELO";
    public static final String P_IN_TIP_OBJETO = "P_IN_TIP_OBJETO";
    public static final String P_IN_COD_ESTADO = "P_IN_COD_ESTADO";
    public static final String P_IN_COD_PETICION = "P_IN_COD_PETICION";
    public static final String P_IN_COD_SUBJECT_AREA = "P_IN_COD_SUBJECT_AREA";
    public static final String P_IN_COD_DIAGRAMA = "P_IN_COD_DIAGRAMA";
    public static final String P_IN_DESCRIPCION = "P_IN_DESCRIPCION";
    public static final String P_IN_ENTREGAR = "entregar";
    public static final String P_IN_PROCESO = "proceso";
    public static final String P_IN_ORDEN = "numeroOrden";
    public static final String P_IN_TITULO = "P_IN_TITULO";
    public static final String P_IN_RUTA = "P_IN_RUTA";
    public static final String P_IN_USUARIO = "P_IN_USUARIO";
    public static final String P_IN_LISTA = "P_IN_LISTA";
    public static final String P_IN_SCRIPT = "script";
    public static final String P_IN_CLAVE_CONFIRMACION = "P_IN_CLAVE_CONFIRMACION";

    //Parametros de salida de los formularios
    public static final String P_OUT_EXIT_BUTTON = "P_OUT_EXIT_BUTTON";
    public static final String P_OUT_DATA_CHANGED = "P_OUT_DATA_CHANGED";
    public static final String P_OUT_TEXTO = "P_OUT_TEXTO";
    public static final String P_OUT_PROCESO = "proceso";

    public static final String P_OUT_SCRIPT_CREACION = "P_OUT_SCRIPT_CREACION";
    public static final String P_OUT_SCRIPT_COMENTARIO = "P_OUT_SCRIPT_COMENTARIO";
    public static final String P_OUT_SCRIPT_CAMBIO = "P_OUT_SCRIPT_CAMBIO";
}
