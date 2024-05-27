package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaInformacionModeloCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nivel");
		columnIdentifiers.add("Titulo");
		columnIdentifiers.add("Descripción");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Habilitado");
		columnIdentifiers.add("Usuario");
		columnIdentifiers.add("Actualizado");
		
		columnClasses.add(BigDecimal.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(400);
		columnSizes.add(400);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);
	}
}
