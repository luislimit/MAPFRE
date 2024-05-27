package com.mdsql.ui.utils.creators;

import java.util.Map;

import com.mdsql.ui.*;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

/**
 * Factory method para gestionar la creación centralizada de cuadros emergentes
 * de la aplicación. Se le pasa al constructor la ventana padre y la cadena de la
 * opción del menú principal que activa al emergente solicitado.
 * 
 * @author federico
 *
 */
public class DialogCreator extends Creator {
	
	private String option;
	
	private FrameSupport frameParent;
	
	private Boolean modal;
	
	public DialogCreator(FrameSupport frameParent, String option) {
		this.modal = Boolean.TRUE;
		this.option = option;
		this.frameParent = frameParent;
	}
	
	/**
	 *
	 */
	@Override
	public Object factoryMethod() {
		return null;
	}

	/**
	 *
	 */
	@Override
	public Object factoryMethod(Map<String, Object> params) {
		DialogSupport dialog = null;
		
		if (MDSQLConstants.CMD_LOAD_SCRIPT.equals(option)) {
			dialog = new PantallaBuscadorFicheros(frameParent, modal);
		}
		
		if (MDSQLConstants.CMD_ERROR.equals(option)) {
			dialog = new DlgErrores(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_WARN.equals(option)) {
			dialog = new DlgErrores(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_PROCESADO_EN_CURSO.equals(option)) {
			dialog = new PantallaProcesadoEnCurso(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_PROCESAR_SCRIPT.equals(option)) {
			dialog = new PantallaProcesarScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_SEARCH_MODEL.equals(option)) {
			dialog = new PantallaSeleccionModelos(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_SELECCION_HISTORICO.equals(option)) {
			dialog = new PantallaSeleccionHistorico(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_EJECUTAR_SCRIPT.equals(option)) {
			dialog = new PantallaEjecutarScripts(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_RESUMEN_PROCESADO.equals(option)) {
			dialog = new PantallaResumenProcesado(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_VER_ERRORES_SCRIPT.equals(option)) {
			dialog = new PantallaVerErroresScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_DETALLE_SCRIPT.equals(option)) {
			dialog = new PantallaDetalleScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_VER_CUADRES_SCRIPT.equals(option)) {
			dialog = new PantallaVerCuadresScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_REPARAR_SCRIPT.equals(option)) {
			dialog = new PantallaRepararScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_DESCARTAR_SCRIPT.equals(option)) {
			dialog = new PantallaDescartarScript(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_EXCEPCION_SCRIPT.equals(option)) {
			dialog = new DlgExcepcion(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_RECHAZAR_PROCESADO.equals(option)) {
			dialog = new DlgRechazar(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_EJECUTAR_TYPE.equals(option)) {
			dialog = new PantallaEjecutarTypes(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_AJUSTAR_LOG_EJECUCION.equals(option)) {
			dialog = new PantallaAjustarLogEjecucion(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_INFORMACION_MODELO.equals(option)) {
			dialog = new PantallaInformacionModelo(frameParent, modal, params);
		}
		
		if (MDSQLConstants.MNU_CONSULTA_HISTORICO_CAMBIOS.equals(option)) {
			dialog = new PantallaHistoricoCambios(frameParent, modal, params);
		}
		
		if (MDSQLConstants.MNU_CONSULTA_PETICIONES.equals(option)) {
			dialog = new PantallaConsultaPeticiones(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_PERMISOS_GENERALES.equals(option)) {
			dialog = new PantallaPermisosGeneralesporModeloporTipoObjeto(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_PERMISOS_OBJETO.equals(option)) {
			dialog = new PantallaDetallePermisosPorObjeto(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_MNTO_PERMISOS_OBJETO.equals(option)) {
			dialog = new PantallaMantenimientoPermisosPorObjeto(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_MNTO_HISTORICO.equals(option)) {
			dialog = new PantallaMantenimientoHistorico(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_HISTORICO_BAJA.equals(option)) {
			dialog = new PantallaHistoricoBaja(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_HISTORICO_ALTA.equals(option)) {
			dialog = new PantallaHistoricoAlta(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_MNTO_NOTAS.equals(option)) {
			dialog = new PantallaMantenimientoNotasModelos(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_MNTO_ENTORNOS.equals(option)) {
			dialog = new PantallaMantenimientoEntornos(frameParent, modal, params);
		}

		if (MDSQLConstants.CMD_MNTO_VARIABLES.equals(option)) {
			dialog = new PantallaMantenimientoVariables(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_MNTO_ENTORNOS_PRUEBAS.equals(option)) {
			dialog = new PantallaMantenimientoEntornosPrueba(frameParent, modal, params);
		}
		
		if (MDSQLConstants.CMD_MNTO_SCRIPT_INICIAL.equals(option)) {
			dialog = new PantallaEjecutarScriptInicialEntornoPrueba(frameParent, modal, params);
		}

		return dialog;
	}
}
