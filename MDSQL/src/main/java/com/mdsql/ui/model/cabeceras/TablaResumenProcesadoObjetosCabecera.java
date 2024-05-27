package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaResumenProcesadoObjetosCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nombre objeto");
		columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Script");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(335);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);
	}
}
