package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaDetalleScriptCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Sentencia");
		columnIdentifiers.add("Objeto padre");
		columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("Objeto");
		columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("Tipo dato");
		columnIdentifiers.add("Precisión");
		columnIdentifiers.add("Escala");
		
		columnClasses.add(BigDecimal.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		
		columnSizes.add(75);
		columnSizes.add(250);
		columnSizes.add(150);
		columnSizes.add(120);
		columnSizes.add(200);
		columnSizes.add(150);
		columnSizes.add(120);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(75);
	}
}