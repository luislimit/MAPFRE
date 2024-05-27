package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaAjustarLogEjecucionCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Script");
		columnIdentifiers.add("Procesado");
		columnIdentifiers.add("Iteración");
		columnIdentifiers.add("Ejecución");
		columnIdentifiers.add("Sentencia");
		columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("Nombre");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Eliminado");
		columnIdentifiers.add("Comentario");
		
		columnClasses.add(String.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(300);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(200);
		columnSizes.add(50);
		columnSizes.add(200);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(500);
	}
}
