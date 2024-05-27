package com.mdsql.ui.utils.collections;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.Script;

/**
 * @author federico
 *
 */
public class UpdateScriptsClosure implements Closure {

	private List<OutputRegistraEjecucion> ejecuciones;
	private BigDecimal numeroOrden;
	private String estado;

	/**
	 * @param ejecuciones
	 */
	public UpdateScriptsClosure(List<OutputRegistraEjecucion> ejecuciones) {
		this.ejecuciones = ejecuciones;
	}
	
	/**
	 * @param ejecuciones
	 */
	public UpdateScriptsClosure(BigDecimal numeroOrden, String estado) {
		this.numeroOrden = numeroOrden;
		this.estado = estado;
	}

	@Override
	public void execute(Object input) {
		Script script = (Script) input;

		if (CollectionUtils.isNotEmpty(ejecuciones)) {
			updateEjecuciones(script);
		}
		
		else if (!Objects.isNull(numeroOrden)) {
			updateEstadoScript(script, numeroOrden, estado);
		}
	}

	/**
	 * @param script
	 * @param nOrden
	 * @param est
	 */
	private void updateEstadoScript(Script script, BigDecimal nOrden, String est) {
		if (script.getNumeroOrden().equals(nOrden)) {
			script.setDescripcionEstadoScript(est);
		}
	}

	/**
	 * @param script
	 */
	private void updateEjecuciones(Script script) {
		for (OutputRegistraEjecucion ej : ejecuciones) {
			if (script.getNumeroOrden().equals(ej.getNumOrden())) {
				script.setCodigoEstadoScript(ej.getCodigoEstadoScript());
				script.setDescripcionEstadoScript(ej.getDescripcionEstadoScript());
				script.setFecha(ej.getFechaEjecucion());
				script.setOperaciones(ej.getTxtCuadreOperacion());
				script.setObjetos(ej.getTxtCuadreObj());

				if ("Ejecutado".equals(ej.getDescripcionEstadoScript())
						|| "Error".equals(ej.getDescripcionEstadoScript())
						|| "Descuadrado".equals(ej.getDescripcionEstadoScript())) {
					script.setSelected(Boolean.FALSE);
				}
			}
		}
	}

}
