package com.mdsql.utils;

import java.awt.Color;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.mdval.utils.Constants;

import lombok.Getter;

/**
 * @author federico
 *
 */
public class MDSQLConstants extends Constants {

	public static final Charset CHARSET_UNX = StandardCharsets.UTF_8;
	public static final Charset CHARSET_WIN = Charset.forName("windows-1252");
	public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
	public static final String CP_1252 = "Cp1252";
	public static final String CR = "\r\n";

	public static final Charset[] ALLOWED_CHARSETS = { DEFAULT_CHARSET, StandardCharsets.UTF_8,
			StandardCharsets.ISO_8859_1, CHARSET_WIN };

	/**
	 * App globals
	 */
	
	public static final Integer LARGE_TEXT_LIMIT = 50;
	
	public static final String COD_USR = "CodUsr";
	public static final String SPRING_CONTEXT = "SPRING_CONTEXT";
	public static final String SERVICE_ERROR = "SERVICE_ERROR";
	public static final String TYPE = "TYPE";
	public static final String ERROR = "ERROR";
	public static final String WARN = "WARN";
	public static final String SI = "SI";
	public static final String NO = "NO";
	public static final String S = "S";
	public static final String N = "N";
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
	public static final String VALIDACION_SERVICE = "validacionService";
	public static final String VALOR_PARTICULA_SERVICE = "valorParticulaService";
	public static final String PARTICULA_NORMA_SERVICE = "particulaNormaService";
	public static final String TIPO_ELEMENTO_SERVICE = "tipoElementoService";
	public static final String TIPO_DATO_SERVICE = "tipoDatoService";
	public static final String TIPO_PARTICULA_SERVICE = "tipoParticulaService";
	public static final String MODELO_SERVICE = "modeloService";
	public static final String GLOSARIO_SERVICE = "glosarioService";
	public static final String CAMPOS_GLOSARIO_SERVICE = "camposGlosarioService";
	public static final String NORMA_SERVICE = "normaService";
	public static final String ELEMENTO_NORMA_SERVICE = "elementoNormaService";
	public static final String INFORME_SERVICE = "informeService";
	public static final String MAESTRAS_SERVICE = "maestrasService";

	// TODO remove old bean names
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

	/**
	 * DataBase Types and constants
	 */
	public static final String PAQUETE = "paquete";

	public static final String FORMATO_LLAMADA = "%s.%s";
	public static final String FORMATO_CONEXION = "%s/%s@%s";
	public static final String FORMATO_FICHERO = "@%s";
	public static final String SQL_PLUS = "sqlplus";
	public static final String EXIT = "exit";

	// TODO eliminar sin utilizar al finalizar desarollo
	public static final String T_R_LINEA = "T_R_LINEA";
	public static final String T_T_LINEA = "T_T_LINEA";
	public static final String T_T_VALIDA_PARTICULA = "T_T_VALIDA_PARTICULA";
	public static final String T_T_VAL_PARTICULA = "T_T_VAL_PARTICULA";
	public static final String T_T_ELEMENTO = "T_T_ELEMENTO";
	public static final String T_T_NORMA = "T_T_NORMA";
	public static final String T_T_PARTICULA = "T_T_PARTICULA";
	public static final String T_T_TIPO_DATO = "T_T_TIPO_DATO";
	public static final String T_R_SUBPROYECTO = "T_R_SUBPROYECTO";
	public static final String T_T_SUBPROYECTO = "T_T_SUBPROYECTO";
	public static final String T_T_MODELO = "T_T_MODELO";
	public static final String T_T_CAMPO_GLOSARIO = "T_T_CAMPO_GLOSARIO";
	public static final String T_T_DET_VALIDACION = "T_T_DET_VALIDACION";
	public static final String T_T_ELEMENTO_NORMA = "T_T_ELEMENTO_NORMA";
	public static final String T_T_PARTICULA_NORMA = "T_T_PARTICULA_NORMA";
	public static final String T_T_GLOSARIO = "T_T_GLOSARIO";
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
	public static final String T_T_TYPE = "T_T_TYPE";
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

	// CONSTANTES DE UI

	// Tecla Enter, para los botones
	public static final String KEY_ENTER = "ENTER";

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
				if (colorCelda.orden == orden) {
					return colorCelda;
				}
			}

			return null;
		}
	}

	public static final Color CELL_SELECTED_BGCOLOR = new Color(184, 207, 229);

	// Modos para el procesado
	public enum Procesado {
		SCRIPT, TYPE
	}
	
	// Estados del procesado
	public enum EstadosProcesado {
		SINVALOR(0, " "), GENERADO(1, "Generado"), EN_EJECUCION(2, "En ejecución"), ERROR(3, "Error"),
		EJECUTADO(4, "Ejecutado"), RECHAZADO(5, "Rechazado"), ENTREGADO(6, "Entregado");

		@Getter
		private String name;
		
		@Getter
		private Integer index;

		EstadosProcesado(Integer index, String name) {
			this.index = index;
			this.name = name;
		}

		public static EstadosProcesado getByName(String name) {
			for (EstadosProcesado estadosProcesado : EstadosProcesado.values()) {
				if (estadosProcesado.name.equals(name)) {
					return estadosProcesado;
				}
			}

			return null;
		}
	}
	
	// Estados del script
	public enum EstadosScript {
		SINVALOR (0, " ", Color.GRAY), PENDIENTE(1, "Pendiente", Color.WHITE), EJECUTADO(2, "Ejecutado", Color.GREEN), ERROR(3, "Error", Color.RED),
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
	
	// Estados del Ttipo objeto
	public enum TiposObjeto {
		SINVALOR(0, " "), TABLA(1, "Tabla"), COLUMNA(2, "Columna"), COMENTARIO(3, "Comentario"),
		VISTA(4, "Vista"), VISTAMATERIALIZADA(5, "Vista Materializada"), SECUENCIA(6, "Secuencia"), PK(7, "PK");

		@Getter
		private String name;
		
		@Getter
		private Integer index;

		TiposObjeto(Integer index, String name) {
			this.index = index;
			this.name = name;
		}

		public static TiposObjeto getByName(String name) {
			for (TiposObjeto tiposObjeto : TiposObjeto.values()) {
				if (tiposObjeto.name.equals(name)) {
					return tiposObjeto;
				}
			}

			return null;
		}
	}
	
	// Estados de operacion
	public enum Operaciones {
		SINVALOR(0, " "), CREACION(1, "Creación"), MODIFICACION(2, "Modificación"), BORRADO(3, "Borrado");

		@Getter
		private String name;
		
		@Getter
		private Integer index;

		Operaciones(Integer index, String name) {
			this.index = index;
			this.name = name;
		}

		public static Operaciones getByName(String name) {
			for (Operaciones operacion : Operaciones.values()) {
				if (operacion.name.equals(name)) {
					return operacion;
				}
			}

			return null;
		}
	}
	// TODO - Crear estados type

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
	public static final String MNU_CONSULTA_PETICIONES = "MNU_CONSULTA_PETICIONES";
	public static final String MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS = "MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS";
	public static final String MNU_MANTENIMIENTO_SCRIPT_INICIAL = "MNU_MANTENIMIENTO_SCRIPT_INICIAL";
	public static final String MNU_EJECUCION_SCRIPT_INICIAL = "MNU_EJECUCION_SCRIPT_INICIAL";
	public static final String MNU_NOTAS_MODELOS = "MNU_NOTAS_MODELOS";
	public static final String MNU_CONFIGURACION_ENTORNOS_PRUEBA = "MNU_CONFIGURACION_ENTORNOS_PRUEBA";
	public static final String MNU_MANTENIMIENTO_HISTORICO = "MNU_MANTENIMIENTO_HISTORICO";

	/**
	 * Comandos que activan dialogos
	 */
	public static final String CMD_INICIAR_APP = "CMD_INICIAR_APP";
	public static final String CMD_PANEL_PRINCIPAL_TAB_EXCEPCIONES = "CMD_PANEL_PRINCIPAL_TAB_EXCEPCIONES";
	public static final String CMD_ALTA_GLOSARIOS = "CMD_ALTA_GLOSARIOS";
	public static final String CMD_MODIFICACION_GLOSARIOS = "CMD_MODIFICACION_GLOSARIOS";
	public static final String CMD_LOAD_SCRIPT = "CMD_LOAD_SCRIPT";
	public static final String CMD_PROCESAR_SCRIPT = "CMD_PROCESAR_SCRIPT";
	public static final String CMD_EJECUTAR_SCRIPT = "CMD_EJECUTAR_SCRIPT";
	public static final String CMD_SEARCH_MODEL = "CMD_SEARCH_MODEL";
	public static final String CMD_SELECCION_HISTORICO = "CMD_SELECCION_HISTORICO";
	public static final String CMD_RESUMEN_PROCESADO = "CMD_RESUMEN_PROCESADO";
	public static final String CMD_ALTA_GLOSARIO_CAMPOS = "CMD_ALTA_GLOSARIO_CAMPOS";
	public static final String CMD_MODIFICACION_GLOSARIO_CAMPOS = "CMD_MODIFICACION_GLOSARIO_CAMPOS";
	public static final String CMD_ALTA_NORMAS = "CMD_ALTA_NORMAS";
	public static final String CMD_MODIFICACION_NORMAS = "CMD_MODIFICACION_NORMAS";
	public static final String CMD_ALTA_ELEMENTOS = "CMD_ALTA_ELEMENTOS";
	public static final String CMD_MODIFICACION_ELEMENTOS = "CMD_MODIFICACION_ELEMENTOS";
	public static final String CMD_ALTA_TIPOS_PARTICULA = "CMD_ALTA_TIPOS_PARTICULA";
	public static final String CMD_MODIFICACION_TIPOS_PARTICULA = "CMD_MODIFICACION_TIPOS_PARTICULA";
	public static final String CMD_ALTA_VALORES_PARTICULAS = "CMD_ALTA_VALORES_PARTICULAS";
	public static final String CMD_BAJA_VALORES_PARTICULAS = "CMD_BAJA_VALORES_PARTICULAS";
	public static final String CMD_MODIFICACION_VALORES_PARTICULAS = "CMD_MODIFICACION_VALORES_PARTICULAS";
	public static final String CMD_ALTA_MANTENIMIENTO_PARTICULAS = "CMD_ALTA_MANTENIMIENTO_PARTICULAS";
	public static final String CMD_MODIFICACION_MANTENIMIENTO_PARTICULAS = "CMD_MODIFICACION_MANTENIMIENTO_PARTICULAS";
	public static final String CMD_ALTA_MODELOS = "CMD_ALTA_MODELOS";
	public static final String CMD_MODIFICACION_MODELOS = "CMD_MODIFICACION_MODELOS";
	public static final String CMD_BUSCAR_MODELOS = "CMD_BUSCAR_MODELOS";
	public static final String CMD_PROCESADO_EN_CURSO = "CMD_PROCESADO_EN_CURSO";
	public static final String CMD_ENTREGAR_SCRIPT = "CMD_ENTREGAR_SCRIPT";
	public static final String CMD_VER_ERRORES_SCRIPT = "CMD_VER_ERRORES_SCRIPT";
	public static final String CMD_DETALLE_SCRIPT = "CMD_DETALLE_SCRIPT";
	public static final String CMD_VER_CUADRES_SCRIPT = "CMD_VER_CUADRES_SCRIPT";
	public static final String CMD_REPARAR_SCRIPT = "CMD_REPARAR_SCRIPT";
	public static final String CMD_DESCARTAR_SCRIPT = "CMD_DESCARTAR_SCRIPT";
	public static final String CMD_EXCEPCION_SCRIPT = "CMD_EXCEPCION_SCRIPT";
	public static final String CMD_RECHAZAR_PROCESADO = "CMD_RECHAZAR_PROCESADO";
	public static final String CMD_EJECUTAR_TYPE = "CMD_EJECUTAR_TYPE";
	public static final String CMD_AJUSTAR_LOG_EJECUCION = "CMD_AJUSTAR_LOG_EJECUCION";
	public static final String CMD_INFORMACION_MODELO = "CMD_INFORMACION_MODELO";
	public static final String CMD_LIMPIAR_SESION = "CMD_LIMPIAR_SESION";
	public static final String CMD_LIMPIAR_SCRIPT = "CMD_LIMPIAR_SCRIPT";

	public static final String CMD_PERMISOS_GENERALES = "CMD_PERMISOS_GENERALES";

	public static final String CMD_PERMISOS_OBJETO = "CMD_PERMISOS_OBJETO";

	public static final String CMD_MNTO_PERMISOS_OBJETO = "CMD_MNTO_PERMISOS_OBJETO";

	public static final String CMD_MNTO_HISTORICO = "CMD_MNTO_HISTORICO";

	public static final String CMD_HISTORICO_ALTA = "CMD_HISTORICO_ALTA";
	public static final String CMD_HISTORICO_BAJA = "CMD_HISTORICO_BAJA";

	public static final String CMD_MNTO_NOTAS = "CMD_MNTO_NOTAS";

	public static final String CMD_MNTO_ENTORNOS = "CMD_MNTO_ENTORNOS";

	public static final String CMD_MNTO_VARIABLES = "CMD_MNTO_VARIABLES";
	
	public static final String CMD_MNTO_ENTORNOS_PRUEBAS = "CMD_MNTO_ENTORNOS_PRUEBAS";
	
	public static final String CMD_MNTO_SCRIPT_INICIAL = "CMD_MNTO_SCRIPT_INICIAL";

	/**
	 * Panel principal
	 */
	public static final String PANEL_PRINCIPAL_BTN_SEARCH = "PANEL_PRINCIPAL_BTN_SEARCH";
	public static final String PANEL_PRINCIPAL_BTN_LOAD_SCRIPT = "PANEL_PRINCIPAL_BTN_LOAD_SCRIPT";
	public static final String PANEL_PRINCIPAL_BTN_VALIDAR = "PANEL_PRINCIPAL_BTN_VALIDAR";
	public static final String PANEL_PRINCIPAL_BTN_LIMPIAR_VALIDACION = "PANEL_PRINCIPAL_BTN_LIMPIAR_VALIDACION";
	public static final String PANEL_PRINCIPAL_BTN_LIMPIAR_TODO = "PANEL_PRINCIPAL_BTN_LIMPIAR_TODO";
	public static final String DETALLE_VALIDACION_TABLA_CABECERA = "DETALLE_VALIDACION_TABLA_CABECERA";

	public static final String NOTAS_MODELO_TABLA_CABECERA = "NOTAS_MODELO_TABLA_CABECERA";

	public static final String MNTO_ENTORNOS_TABLA_CABECERA = "MNTO_ENTORNOS_TABLA_CABECERA";
	public static final String MNTO_ENTORNOS_PRUEBA_TABLA_CABECERA = "MNTO_ENTORNOS_PRUEBA_TABLA_CABECERA";

	public static final String MNTO_VARIABLES_TABLA_CABECERA = "MNTO_VARIABLES_TABLA_CABECERA";

	public static final String PANEL_RESULTADOS_BTN_MARCAR_EXCEPCION = "PANEL_RESULTADOS_BTN_MARCAR_EXCEPCION";
	public static final String PANEL_RESULTADOS_BTN_ADD_GLOSARIO = "PANEL_RESULTADOS_BTN_ADD_GLOSARIO";
	public static final String PANEL_RESULTADOS_BTN_ADD_TODOS_GLOSARIO = "PANEL_RESULTADOS_BTN_ADD_TODOS_GLOSARIO";
	public static final String PANEL_RESULTADOS_BTN_GENERAR_LOG = "PANEL_RESULTADOS_BTN_GENERAR_LOG";

	/**
	 * DlgIdentificador
	 */
	public static final String DLG_IDENTIFICADOR_BTN_ACEPTAR = "DLG_IDENTIFICADOR_BTN_ACEPTAR";

	/**
	 * DlgExcepciones
	 */
	public static final String DLG_EXCEPCIONES_BTN_ACEPTAR = "DLG_EXCEPCIONES_BTN_ACEPTAR";
	public static final String DLG_EXCEPCIONES_BTN_CANCELAR = "DLG_EXCEPCIONES_BTN_CANCELAR";

	/**
	 * DlgDefinicionGlosarios constantes
	 */
	public static final String FRM_DEFINICION_GLOSARIOS_SELECCIONADO = "FRM_DEFINICION_GLOSARIOS_SELECCIONADO";
	public static final String FRM_DEFINICION_GLOSARIOS_TABLA_GLOSARIOS_CABECERA = "FRM_DEFINICION_GLOSARIOS_TABLA_GLOSARIOS_CABECERA";
	public static final String FRM_DEFINICION_GLOSARIOS_BTN_BUSCAR = "FRM_DEFINICION_GLOSARIOS_BTN_BUSCAR";
	public static final String FRM_DEFINICION_GLOSARIOS_BTN_ALTA = "FRM_DEFINICION_GLOSARIOS_BTN_ALTA";
	public static final String FRM_DEFINICION_GLOSARIOS_BTN_MODIFICACION = "FRM_DEFINICION_GLOSARIOS_BTN_MODIFICACION";
	public static final String FRM_DEFINICION_GLOSARIOS_BTN_SELECCIONAR = "FRM_DEFINICION_GLOSARIOS_BTN_SELECCIONAR";

	/**
	 * DlgAltaModificacionGlosarios
	 */
	public static final String DLG_ALTA_MODIFICACION_GLOSARIOS_BTN_ACEPTAR = "DLG_ALTA_MODIFICACION_GLOSARIOS_BTN_ACEPTAR";
	public static final String DLG_ALTA_MODIFICACION_GLOSARIOS_BTN_CANCELAR = "DLG_ALTA_MODIFICACION_GLOSARIOS_BTN_CANCELAR";

	/**
	 * FrmGlosarioCampos
	 */
	public static final String FRM_GLOSARIO_CAMPOS_CAMPO_SELECCIONADO = "FRM_GLOSARIO_CAMPOS_CAMPO_SELECCIONADO";
	public static final String FRM_GLOSARIO_CAMPOS_GLOSARIO_SELECCIONADO = "FRM_GLOSARIO_CAMPOS_GLOSARIO_SELECCIONADO";
	public static final String FRM_GLOSARIO_CAMPOS_TABLA_CAMPO_CABECERA = "FRM_GLOSARIO_CAMPOS_TABLA_CAMPO_CABECERA";
	public static final String FRM_GLOSARIO_CAMPOS_TABLA_MODELO_CABECERA = "FRM_GLOSARIO_CAMPOS_TABLA_MODELO_CABECERA";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_BUSCAR_GLOSARIO = "FRM_GLOSARIO_CAMPOS_BTN_BUSCAR_GLOSARIO";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_BUSCAR = "FRM_GLOSARIO_CAMPOS_BTN_BUSCAR";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_ALTA = "FRM_GLOSARIO_CAMPOS_BTN_ALTA";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_BAJA = "FRM_GLOSARIO_CAMPOS_BTN_BAJA";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_MODIFICACION = "FRM_GLOSARIO_CAMPOS_BTN_MODIFICACION";
	public static final String FRM_GLOSARIO_CAMPOS_BTN_IMPRIMIR = "FRM_GLOSARIO_CAMPOS_BTN_IMPRIMIR";

	/**
	 * DlgAltaModificacionCampos
	 */
	public static final String DLG_ALTA_MODIFICACION_CAMPOS_BTN_ACEPTAR = "DLG_ALTA_MODIFICACION_CAMPOS_BTN_ACEPTAR";
	public static final String DLG_ALTA_MODIFICACION_CAMPOS_BTN_CANCELAR = "DLG_ALTA_MODIFICACION_CAMPOS_BTN_CANCELAR";

	public static final String DLG_BAJA_CAMPO_GLOSARIO_BTN_ACEPTAR = "DLG_BAJA_CAMPO_GLOSARIO_BTN_ACEPTAR";
	public static final String DLG_BAJA_CAMPO_GLOSARIO_BTN_CANCELAR = "DLG_BAJA_CAMPO_GLOSARIO_BTN_CANCELAR";

	/**
	 * DlgBajaGlosario
	 */
	public static final String DLG_BAJA_GLOSARIO_BTN_ACEPTAR = "DLG_BAJA_GLOSARIO_BTN_ACEPTAR";
	public static final String DLG_BAJA_GLOSARIO_BTN_CANCELAR = "DLG_BAJA_GLOSARIO_BTN_CANCELAR";

	/**
	 * FrmDefinicionNormas
	 */
	public static final String FRM_DEFINICION_NORMAS_SELECCIONADA = "FRM_DEFINICION_NORMAS_SELECCIONADA";
	public static final String FRM_DEFINICION_NORMAS_TABLA_NORMAS_CABECERA = "FRM_DEFINICION_NORMAS_TABLA_NORMAS_CABECERA";
	public static final String FRM_DEFINICION_NORMAS_BTN_BUSCAR = "FRM_DEFINICION_NORMAS_BTN_BUSCAR";
	public static final String FRM_DEFINICION_NORMAS_BTN_ALTA = "FRM_DEFINICION_NORMAS_BTN_ALTA";
	public static final String FRM_DEFINICION_NORMAS_BTN_MODIFICACION = "FRM_DEFINICION_NORMAS_BTN_MODIFICACION";

	/**
	 * DlgAltaModificacionNormas
	 */
	public static final String DLG_MODIFICACION_NORMAS_ELEMENTO_SELECCIONADO = "DLG_MODIFICACION_NORMAS_ELEMENTO_SELECCIONADO";
	public static final String DLG_MODIFICACION_NORMAS_TABLA_ELEMENTOS_CABECERA = "DLG_MODIFICACION_NORMAS_TABLA_ELEMENTOS_CABECERA";
	public static final String DLG_MODIFICACION_NORMAS_TABLA_PARTICULAS_CABECERA = "DLG_MODIFICACION_NORMAS_TABLA_PARTICULAS_CABECERA";
	public static final String DLG_MODIFICACION_NORMAS_BTN_ALTA_ELEMENTO = "DLG_MODIFICACION_NORMAS_BTN_ALTA_ELEMENTO";
	public static final String DLG_MODIFICACION_NORMAS_BTN_BAJA_ELEMENTO = "DLG_MODIFICACION_NORMAS_BTN_BAJA_ELEMENTO";
	public static final String DLG_MODIFICACION_NORMAS_BTN_MODIFICACION_ELEMENTO = "DLG_MODIFICACION_NORMAS_BTN_MODIFICACION_ELEMENTO";
	public static final String DLG_MODIFICACION_NORMAS_BTN_ACEPTAR = "DLG_MODIFICACION_NORMAS_BTN_ACEPTAR";
	public static final String DLG_MODIFICACION_NORMAS_BTN_CANCELAR = "DLG_MODIFICACION_NORMAS_BTN_CANCELAR";

	/**
	 * FrmDefinicionElementos
	 */
	public static final String FRM_DEFINICION_ELEMENTOS_SELECCIONADO = "FRM_DEFINICION_ELEMENTOS_SELECCIONADO";
	public static final String FRM_DEFINICION_ELEMENTOS_TABLA_TIPOS_ELEMENTO_CABECERA = "FRM_DEFINICION_ELEMENTOS_TABLA_TIPOS_ELEMENTO_CABECERA";
	public static final String FRM_DEFINICION_ELEMENTOS_BTN_BUSCAR = "FRM_DEFINICION_ELEMENTOS_BTN_BUSCAR";
	public static final String FRM_DEFINICION_ELEMENTOS_BTN_ALTA = "FRM_DEFINICION_ELEMENTOS_BTN_ALTA";
	public static final String FRM_DEFINICION_ELEMENTOS_BTN_MODIFICACION = "FRM_DEFINICION_ELEMENTOS_BTN_MODIFICACION";

	/**
	 * DlgAltaModificacionTiposElemento
	 */
	public static final String DLG_ALTA_MODIFICACION_ELEMENTOS_BTN_ACEPTAR = "DLG_ALTA_MODIFICACION_ELEMENTOS_BTN_ACEPTAR";
	public static final String DLG_ALTA_MODIFICACION_ELEMENTOS_BTN_CANCELAR = "DLG_ALTA_MODIFICACION_ELEMENTOS_BTN_CANCELAR";

	/**
	 * FrmDefinicionElementosNorma
	 */
	public static final String FRM_DEFINICION_ELEMENTOS_NORMA_TABLA_ELEMENTOS_CABECERA = "FRM_DEFINICION_ELEMENTOS_NORMA_TABLA_ELEMENTOS_CABECERA";
	public static final String FRM_DEFINICION_ELEMENTOS_NORMA_BTN_BUSCAR = "FRM_DEFINICION_ELEMENTOS_NORMA_BTN_BUSCAR";

	public static final String FRM_DEFINICION_TIPOS_PARTICULA_SELECCIONADO = "FRM_DEFINICION_TIPOS_PARTICULA_SELECCIONADO";
	public static final String FRM_DEFINICION_TIPOS_PARTICULA_TABLA_TIPOS_CABECERA = "FRM_DEFINICION_TIPOS_PARTICULA_TABLA_TIPOS_CABECERA";
	public static final String FRM_DEFINICION_TIPOS_PARTICULA_BTN_BUSCAR = "FRM_DEFINICION_TIPOS_PARTICULA_BTN_BUSCAR";
	public static final String FRM_DEFINICION_TIPOS_PARTICULA_BTN_ALTA = "FRM_DEFINICION_TIPOS_PARTICULA_BTN_ALTA";
	public static final String FRM_DEFINICION_TIPOS_PARTICULA_BTN_MODIFICACION = "FRM_DEFINICION_TIPOS_PARTICULA_BTN_MODIFICACION";

	public static final String DLG_ALTA_MODIFICACION_TIPOS_PARTICULA_BTN_ACEPTAR = "DLG_ALTA_MODIFICACION_TIPOS_PARTICULA_BTN_ACEPTAR";
	public static final String DLG_ALTA_MODIFICACION_TIPOS_PARTICULA_BTN_CANCELAR = "DLG_ALTA_MODIFICACION_TIPOS_PARTICULA_BTN_CANCELAR";

	public static final String FRM_DEFINICION_PARTICULAS_NORMA_CABECERA = "FRM_DEFINICION_PARTICULAS_NORMA_CABECERA";
	public static final String FRM_DEFINICION_PARTICULAS_NORMA_BTN_BUSCAR = "FRM_DEFINICION_PARTICULAS_NORMA_BTN_BUSCAR";

	public static final String FRM_VALORES_PARTICULAS_CABECERA = "FRM_VALORES_PARTICULAS_CABECERA";
	public static final String FRM_VALORES_PARTICULAS_SELECCIONADA = "FRM_VALORES_PARTICULAS_SELECCIONADA";
	public static final String FRM_VALORES_PARTICULAS_BTN_BUSCAR = "FRM_VALORES_PARTICULAS_BTN_BUSCAR";
	public static final String FRM_VALORES_PARTICULAS_BTN_ALTA = "FRM_VALORES_PARTICULAS_BTN_ALTA";
	public static final String FRM_VALORES_PARTICULAS_BTN_MODIFICACION = "FRM_VALORES_PARTICULAS_BTN_MODIFICACION";

	public static final String FRM_MANTENIMIENTO_PARTICULAS_TIPO_SELECCIONADO = "FRM_MANTENIMIENTO_PARTICULAS_TIPO_SELECCIONADO";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_VALOR_SELECCIONADO = "FRM_MANTENIMIENTO_PARTICULAS_VALOR_SELECCIONADO";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_BTN_ALTA = "FRM_MANTENIMIENTO_PARTICULAS_BTN_ALTA";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_BTN_BAJA = "FRM_MANTENIMIENTO_PARTICULAS_BTN_BAJA";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_BTN_MODIFICACION = "FRM_MANTENIMIENTO_PARTICULAS_BTN_MODIFICACION";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_BTN_ACEPTAR = "FRM_MANTENIMIENTO_PARTICULAS_BTN_ACEPTAR";
	public static final String FRM_MANTENIMIENTO_PARTICULAS_BTN_CANCELAR = "FRM_MANTENIMIENTO_PARTICULAS_BTN_CANCELAR";

	public static final String DLG_ALTA_MODIFICACION_VALORES_PARTICULA_BTN_ACEPTAR = "DLG_ALTA_MODIFICACION_VALORES_PARTICULA_BTN_ACEPTAR";
	public static final String DLG_ALTA_MODIFICACION_VALORES_PARTICULA_BTN_CANCELAR = "DLG_ALTA_MODIFICACION_VALORES_PARTICULA_BTN_CANCELAR";

	public static final String FRM_DEFINICION_MODELOS_SELECCIONADO = "FRM_DEFINICION_MODELOS_SELECCIONADO";
	public static final String FRM_DEFINICION_MODELOS_TABLA_CABECERA = "FRM_DEFINICION_MODELOS_TABLA_CABECERA";
	public static final String FRM_DEFINICION_MODELOS_BTN_BUSCAR = "FRM_DEFINICION_MODELOS_BTN_BUSCAR";
	public static final String FRM_DEFINICION_MODELOS_BTN_ALTA = "FRM_DEFINICION_MODELOS_BTN_ALTA";
	public static final String FRM_DEFINICION_MODELOS_BTN_BAJA = "FRM_DEFINICION_MODELOS_BTN_BAJA";
	public static final String FRM_DEFINICION_MODELOS_BTN_MODIFICACION = "FRM_DEFINICION_MODELOS_BTN_MODIFICACION";
	public static final String FRM_DEFINICION_MODELOS_BTN_SELECCIONAR = "FRM_DEFINICION_MODELOS_BTN_SELECCIONAR";

	public static final String FRM_MANTENIMIENTO_MODELOS_SUBPROYECTO_TABLA_CABECERA = "FRM_MANTENIMIENTO_MODELOS_SUBPROYECTO_TABLA_CABECERA";
	public static final String FRM_MANTENIMIENTO_MODELOS_BTN_BUSCAR_GLOSARIO = "FRM_MANTENIMIENTO_MODELOS_BTN_BUSCAR_GLOSARIO";
	public static final String FRM_MANTENIMIENTO_MODELOS_BTN_ADD_SUBMODELO = "FRM_MANTENIMIENTO_MODELOS_BTN_ADD_SUBMODELO";
	public static final String FRM_MANTENIMIENTO_MODELOS_BTN_REMOVE_SUBMODELO = "FRM_MANTENIMIENTO_MODELOS_BTN_REMOVE_SUBMODELO";
	public static final String FRM_MANTENIMIENTO_MODELOS_BTN_ACEPTAR = "FRM_MANTENIMIENTO_MODELOS_BTN_ACEPTAR";
	public static final String FRM_MANTENIMIENTO_MODELOS_BTN_CANCELAR = "FRM_MANTENIMIENTO_MODELOS_BTN_CANCELAR";

	public static final String FRM_COMPROBACION_NOMBRE_ELEMENTO_TABLA_CABECERA = "FRM_COMPROBACION_NOMBRE_ELEMENTO_TABLA_CABECERA";
	public static final String FRM_COMPROBACION_NOMBRE_ELEMENTO_BTN_BUSCAR = "FRM_COMPROBACION_NOMBRE_ELEMENTO_BTN_BUSCAR";
	public static final String FRM_COMPROBACION_NOMBRE_ELEMENTO_BTN_COMPROBAR = "FRM_COMPROBACION_NOMBRE_ELEMENTO_BTN_COMPROBAR";

	public static final String PANTALLA_BUSCADOR_FICHEROS_BTN_ACEPTAR = "PANTALLA_BUSCADOR_FICHEROS_BTN_ACEPTAR";
	public static final String PANTALLA_BUSCADOR_FICHEROS_BTN_CANCELAR = "PANTALLA_BUSCADOR_FICHEROS_BTN_CANCELAR";

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
	public static final String FRM_DEFINICION_SELECCION_TABLA_CABECERA = "FRM_DEFINICION_SELECCION_TABLA_CABECERA";

	public static final String FRM_DEFINICION_SCRIPTS_TABLA_CABECERA = "FRM_DEFINICION_SCRIPTS_TABLA_CABECERA";
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
	public static final String SCRIPTS_TABLA_CABECERA = "SCRIPTS_TABLA_CABECERA";

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
	public static final String FRAME_PRINCIPAL_TYPES_TABLA_CABECERA = "FRAME_PRINCIPAL_TYPES_TABLA_CABECERA";
	public static final String FRAME_PRINCIPAL_PROCESADO_CURSO = "FRAME_PRINCIPAL_PROCESADO_CURSO";
	public static final String FRAME_PRINCIPAL_REFRESCAR_FICHERO = "FRAME_PRINCIPAL_REFRESCAR_FICHERO";
	public static final String FRAME_PRINCIPAL_INFORMACION_MODELO = "FRAME_PRINCIPAL_INFORMACION_MODELO";

	public static final String PANTALLA_INFORMACION_MODELO_SCRIPT_LIMPIAR = "PANTALLA_INFORMACION_MODELO_SCRIPT_LIMPIAR";
	public static final String PANTALLA_INFORMACION_MODELO_VER_PROCESADO = "PANTALLA_INFORMACION_MODELO_VER_PROCESADO";
	
	public static final String PANTALLA_PROCESADO_SCRIPT_SEARCH_MODEL = "PANTALLA_PROCESADO_SCRIPT_SEARCH_MODEL";
	public static final String PANTALLA_PROCESADO_SCRIPT_CANCELAR = "PANTALLA_PROCESADO_SCRIPT_CANCELAR";
	public static final String PANTALLA_PROCESADO_SCRIPT_PROCESAR = "PANTALLA_PROCESADO_SCRIPT_PROCESAR";
	public static final String PANTALLA_PROCESADO_SCRIPT_LIMPIAR = "PANTALLA_PROCESADO_SCRIPT_LIMPIAR";
	public static final String PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO = "PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO";

	public static final String FRM_DEFINICION_EJECUTAR_TYPES_TABLA_CABECERA = "FRM_DEFINICION_EJECUTAR_TYPES_TABLA_CABECERA";
	public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_RECHAZAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_RECHAZAR";
	public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_CUADRES = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_CUADRES";
	public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_ERRORES = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_VER_ERRORES";
	public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_ACEPTAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_ACEPTAR";
	public static final String FRM_DEFINICION_EJECUTAR_TYPES_BTN_CANCELAR = "FRM_DEFINICION_EJECUTAR_TYPES_BTN_CANCELAR";

	public static final String TYPES_TABLA_CABECERA = "TYPES_TABLA_CABECERA";
	public static final String PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR = "PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR";
	public static final String PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES = "PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES";
	public static final String PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES = "PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES";
	public static final String PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR = "PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR";
	public static final String PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR = "PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR";

	public static final String DLG_SELECCION_MODELOS_TABLA_CABECERA = "DLG_SELECCION_MODELOS_TABLA_CABECERA";
	public static final String PROCESAR_SCRIPT_NOTAS_TABLA_CABECERA = "PROCESAR_SCRIPT_NOTAS_TABLA_CABECERA";
	public static final String PROCESAR_SCRIPT_ULTIMAS_PETICIONES_TABLA_CABECERA = "PROCESAR_SCRIPT_ULTIMAS_PETICIONES_TABLA_CABECERA";
	public static final String SELECCION_HISTORICO_TABLA_CABECERA = "SELECCION_HISTORICO_TABLA_CABECERA";
	public static final String VER_ERRORES_TABLA_CABECERA = "VER_ERRORES_TABLA_CABECERA";
	public static final String VER_PARCHES_TABLA_CABECERA = "VER_PARCHES_TABLA_CABECERA";
	public static final String VER_CUADRES_OPERACIONES_TABLA_CABECERA = "VER_CUADRES_OPERACIONES_TABLA_CABECERA";
	public static final String VER_CUADRES_OBJETOS_TABLA_CABECERA = "VER_CUADRES_OBJETOS_TABLA_CABECERA";
	public static final String RESUMEN_PROCESADO_SCRIPTS_TABLA_CABECERA = "RESUMEN_PROCESADO_SCRIPTS_TABLA_CABECERA";

	public static final String PERMISOS_GENERALES_TABLA_CABECERA = "PERMISOS_GENERALES_TABLA_CABECERA";

	public static final String SINONIMOS_GENERALES_TABLA_CABECERA = "SINONIMOS_GENERALES_TABLA_CABECERA";

	public static final String PANTALLA_PROCESADO_CURSO_VER_LOG = "PANTALLA_PROCESADO_CURSO_VER_LOG";
	public static final String PANTALLA_PROCESADO_CURSO_VER_ERRORES = "PANTALLA_PROCESADO_CURSO_VER_ERRORES";
	public static final String PANTALLA_PROCESADO_CURSO_DETALLE_SCRIPT = "PANTALLA_PROCESADO_CURSO_DETALLE_SCRIPT";
	public static final String PANTALLA_PROCESADO_CURSO_ENTREGAR = "PANTALLA_PROCESADO_CURSO_ENTREGAR";
	public static final String PANTALLA_PROCESADO_CURSO_CANCELAR = "PANTALLA_PROCESADO_CURSO_CANCELAR";

	public static final String PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR = "PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR";

	public static final String PANTALLA_VER_CUADRES_SCRIPT_BTN_CANCELAR = "PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR";

	public static final String RESUMEN_PROCESADO_OBJETOS_TABLA_CABECERA = "RESUMEN_PROCESADO_OBJETOS_TABLA_CABECERA";
	public static final String RESUMEN_PROCESADO_OPERACIONES_TABLA_CABECERA = "RESUMEN_PROCESADO_OPERACIONES_TABLA_CABECERA";
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

	public static final String PANTALLA_CONSULTA_PETICIONES_BUSCAR = "PANTALLA_CONSULTA_PETICIONES_BUSCAR";
	public static final String PANTALLA_CONSULTA_PETICIONES_CARGAR_PROCESADO = "PANTALLA_CONSULTA_PETICIONES_CARGAR_PROCESADO";
	public static final String PANTALLA_CONSULTA_PETICIONES_CANCELAR = "PANTALLA_CONSULTA_PETICIONES_CANCELAR";

	public static final String PANTALLA_DETALLE_SCRIPT_CANCELAR = "PANTALLA_DETALLE_SCRIPT_CANCELAR";

	public static final String PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO = "PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO";
	public static final String PANTALLA_HISTORICO_CAMBIOS_BUSCAR = "PANTALLA_HISTORICO_CAMBIOS_BUSCAR";
	public static final String PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS = "PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS";
	public static final String PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT = "PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT";
	public static final String PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO = "PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO";
	public static final String PANTALLA_HISTORICO_CAMBIOS_CANCELAR = "PANTALLA_HISTORICO_CAMBIOS_CANCELAR";
	public static final String DLG_HISTORICO_CAMBIOS_TABLA_CABECERA = "DLG_HISTORICO_CAMBIOS_TABLA_CABECERA";

	public static final String DLG_EXCEPTION_BTN_ACEPTAR = "DLG_EXCEPTION_BTN_ACEPTAR";
	public static final String DLG_EXCEPTION_BTN_CANCELAR = "DLG_EXCEPTION_BTN_CANCELAR";

	public static final String DLG_RECHAZAR_BTN_ACEPTAR = "DLG_RECHAZAR_BTN_ACEPTAR";
	public static final String DLG_RECHAZAR_BTN_CANCELAR = "DLG_RECHAZAR_BTN_CANCELAR";

	public static final String PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR = "PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR";
	public static final String PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR = "PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR";
	public static final String DLG_DETALLE_SCRIPT_TABLA_CABECERA = "DLG_DETALLE_SCRIPT_TABLA_CABECERA";

	public static final String DLG_CONSULTA_PETICIONES_TABLA_CABECERA = "DLG_CONSULTA_PETICIONES_TABLA_CABECERA";

	public static final String DLG_INFORMACION_MODELO_TABLA_CABECERA = "DLG_INFORMACION_MODELO_TABLA_CABECERA";

	public static final String DLG_AJUSTAR_LOG_EJECUCION_TABLA_CABECERA = "DLG_AJUSTAR_LOG_EJECUCION_TABLA_CABECERA";
	
	public static final String PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_GUARDAR = "PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_GUARDAR";
	public static final String PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_INFORME = "PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_INFORME";
	public static final String PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_CANCELAR = "PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_CANCELAR";
	
	public static final String PANTALLA_DETALLE_PERMISOS_POR_OBJETO_BUSCAR = "PANTALLA_DETALLE_PERMISOS_POR_OBJETO_BUSCAR";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_OBJETO_ALTA = "PANTALLA_DETALLE_PERMISOS_POR_OBJETO_ALTA";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_OBJETO_MODIFICACION = "PANTALLA_DETALLE_PERMISOS_POR_OBJETO_MODIFICACION";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_OBJETO_INFORME = "PANTALLA_DETALLE_PERMISOS_POR_OBJETO_INGORME";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_OBJETO_CANCELAR = "PANTALLA_DETALLE_PERMISOS_POR_OBJETO_CANCELAR";
	
	public static final String PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_BUSCAR = "PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_BUSCAR";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_ALTA = "PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_ALTA";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_MODIFICACION = "PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_MODIFICACION";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_INFORME = "PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_INGORME";
	public static final String PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_CANCELAR = "PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_CANCELAR";
	
	public static final String PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_GUARDAR = "PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_GUARDAR";
	public static final String PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_CANCELAR = "PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_CANCELAR";
	
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO = "PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO";
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR = "PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR";
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_ALTA = "PANTALLA_MANTENIMIENTO_HISTORICO_ALTA";
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_BAJA = "PANTALLA_MANTENIMIENTO_HISTORICO_BAJA";
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_INFORME = "PANTALLA_MANTENIMIENTO_HISTORICO_INFORME";
	public static final String PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR = "PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR";

	public static final String MNTO_HISTORICO_TABLA_CABECERA = "MNTO_HISTORICO_TABLA_CABECERA";

	public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR";
	public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR";
	public static final String PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO = "PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO";

	public static final String PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR = "PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR";
	public static final String PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR = "PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR";
	
	public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR";
	public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR";
	public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR";
	
	public static final String PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_PROCESAR = "PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_PROCESAR";
	public static final String PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_EJECUTAR = "PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_EJECUTAR";
	public static final String PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_CANCELAR = "PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_CANCELAR";
	
	public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_GUARDAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_GUARDAR";
	public static final String PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_CANCELAR = "PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_CANCELAR";
	
	public static final String PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_GUARDAR = "PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_GUARDAR";
	public static final String PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_CANCELAR = "PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_CANCELAR";
	
	public static final String PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR_MODELO = "PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR_MODELO";
	public static final String PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR = "PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR";
	public static final String PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_CANCELAR = "PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_CANCELAR";

	public static final String PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_BUSCAR = "PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_BUSCAR";
	public static final String PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_GUARDAR = "PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_GUARDAR";
	public static final String PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_CANCELAR = "PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_CANCELAR";


	public static final String PANTALLA_CONFIRMACION_GENERACION_PERMISOS_ACEPTAR = "PANTALLA_CONFIRMACION_GENERACION_PERMISOS_ACEPTAR";
	public static final String PANTALLA_CONFIRMACION_GENERACION_PERMISOS_CANCELAR = "PANTALLA_CONFIRMACION_GENERACION_PERMISOS_CANCELAR";

	public static final String PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR = "PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR";
	public static final String PANTALLA_HISTORICO_BAJA_BTN_CANCELAR = "PANTALLA_HISTORICO_BAJA_BTN_CANCELAR";

	public static final String PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR = "PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR";
	public static final String PANTALLA_HISTORICO_ALTA_BTN_CANCELAR = "PANTALLA_HISTORICO_ALTA_BTN_CANCELAR";

	public static final String PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO = "PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO";
}