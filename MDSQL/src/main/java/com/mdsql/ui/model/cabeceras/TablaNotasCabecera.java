package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaNotasCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nivel");
		columnIdentifiers.add("Título");
		columnIdentifiers.add("Descripción");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Habilitado");
		columnIdentifiers.add("Usuario");
		columnIdentifiers.add("Actualizado");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		
		columnSizes.add(75);
		columnSizes.add(400);
		columnSizes.add(400);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);
	}
}
