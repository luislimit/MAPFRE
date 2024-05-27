package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEntornosPruebasCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nombre entorno");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Esquema");
		columnIdentifiers.add("Tablespace");
		columnIdentifiers.add("Gradoparal");
		columnIdentifiers.add("Descripción");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(450);
	}
}
