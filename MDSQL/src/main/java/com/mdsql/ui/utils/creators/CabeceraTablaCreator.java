package com.mdsql.ui.utils.creators;

import java.util.Map;

import com.mdsql.ui.model.cabeceras.*;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * Factory method para gestionar la creación centralizada de la cabecera de 
 * títulos de las tablas.
 * 
 * @author federico
 *
 */
public class CabeceraTablaCreator extends Creator {
	
	private String item;
	
	public CabeceraTablaCreator(String item) {
		this.item = item;
	}
	
	@Override
	public Object factoryMethod() {
		Cabecera cabecera = null;
		
		if (MDSQLConstants.DLG_SELECCION_MODELOS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaModelosCabecera();
		}
		
		if (MDSQLConstants.PROCESAR_SCRIPT_NOTAS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaNotasCabecera();
		}
		
		if (MDSQLConstants.PROCESAR_SCRIPT_ULTIMAS_PETICIONES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaUltimasPeticionesCabecera();
		}
		
		if (MDSQLConstants.SELECCION_HISTORICO_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaSeleccionHistoricoCabecera();
		}
		
		if (MDSQLConstants.RESUMEN_PROCESADO_SCRIPTS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaResumenProcesadoScriptsCabecera();
		}
		
		if (MDSQLConstants.RESUMEN_PROCESADO_OBJETOS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaResumenProcesadoObjetosCabecera();
		}
		
		if (MDSQLConstants.RESUMEN_PROCESADO_OPERACIONES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaResumenProcesadoOperacionesCabecera();
		}
		
		if (MDSQLConstants.FRAME_PRINCIPAL_TYPES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaTypesCabecera();
		}
		
		if (MDSQLConstants.SCRIPTS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaScriptsCabecera();
		}
		
		if (MDSQLConstants.TYPES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaEjecutarTypesCabecera();
		}
		
		if (MDSQLConstants.VER_ERRORES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaErroresCabecera();
		}
		
		if (MDSQLConstants.VER_PARCHES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaParchesCabecera();
		}
		
		if (MDSQLConstants.VER_CUADRES_OPERACIONES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaCuadresOperacionesCabecera();
		}
		
		if (MDSQLConstants.VER_CUADRES_OBJETOS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaCuadresObjetosCabecera();
		}
		
		if (MDSQLConstants.DLG_DETALLE_SCRIPT_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaDetalleScriptCabecera();
		}
		
		if (MDSQLConstants.DLG_CONSULTA_PETICIONES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaConsultaPeticionesCabecera();
		}
		
		if (MDSQLConstants.DLG_INFORMACION_MODELO_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaInformacionModeloCabecera();
		}
		
		if (MDSQLConstants.DLG_AJUSTAR_LOG_EJECUCION_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaAjustarLogEjecucionCabecera();
		}
		
		if (MDSQLConstants.DLG_HISTORICO_CAMBIOS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaHistoricoObjetosCabecera();
		}

		if (MDSQLConstants.PERMISOS_GENERALES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaPermisosCabecera();
		}

		if (MDSQLConstants.SINONIMOS_GENERALES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaSinonimosCabecera();
		}

		if (MDSQLConstants.MNTO_HISTORICO_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaHistoricoCabecera();
		}

		if (MDSQLConstants.NOTAS_MODELO_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaNotasModeloCabecera();
		}

		if (MDSQLConstants.MNTO_ENTORNOS_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaEntornosCabecera();
		}

		if (MDSQLConstants.MNTO_VARIABLES_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaVariablesCabecera();
		}
		
		if (MDSQLConstants.MNTO_ENTORNOS_PRUEBA_TABLA_CABECERA.equals(item)) {
			cabecera = new TablaEntornosPruebasCabecera();
		}

		return cabecera;
	}

	/**
	 *
	 */
	@Override
	public Object factoryMethod(Map<String, Object> params) {
		return null;
	}
}
