package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaNotasModeloCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Importancia");
		columnIdentifiers.add("Título");
		columnIdentifiers.add("Habilitado");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Descripción");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(450);
		columnSizes.add(100);
		columnSizes.add(150);
		columnSizes.add(450);
	}
}
