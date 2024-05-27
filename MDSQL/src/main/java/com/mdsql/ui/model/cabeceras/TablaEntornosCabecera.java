package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEntornosCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Esquema");
		columnIdentifiers.add("Password");
		columnIdentifiers.add("Comentario");
		columnIdentifiers.add("Habilitado");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(450);
		columnSizes.add(100);
	}
}
