package com.mdsql.ui.utils.collections;

import java.util.List;

import org.apache.commons.collections.Closure;

import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.Type;

/**
 * @author federico
 *
 */
public class UpdateTypesScriptsClosure implements Closure {

	private OutputRegistraEjecucionType ejecucion;

	/**
	 * @param ejecuciones
	 */
	public UpdateTypesScriptsClosure(OutputRegistraEjecucionType ejecucion) {
		this.ejecucion = ejecucion;
	}

	@Override
	public void execute(Object input) {
		Type type = (Type) input;

		updateEjecuciones(type);
	}

	/**
	 * @param script
	 */
	private void updateEjecuciones(Type type) {
		List<Type> types = ejecucion.getListaType();
 		
		for (Type typeEj : types) {
			if (type.getNumeroOrdenType().equals(typeEj.getNumeroOrdenType())) {
				type.setCodigoEstadoScript(typeEj.getCodigoEstadoScript());
				type.setDescripcionEstadoScript(typeEj.getDescripcionEstadoScript());
				type.setFechaCambio(typeEj.getFechaCambio());
				type.setDROP(typeEj.getDROP());
				type.setPDC(typeEj.getPDC());
				type.setTYS(typeEj.getTYS());
				type.setTYB(typeEj.getTYB());
			}
		}
	}

}
