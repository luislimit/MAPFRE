package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEjecutarTypesCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Orden");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Drop");
		columnIdentifiers.add("TYS");
		columnIdentifiers.add("TYB");
		columnIdentifiers.add("PDC");
		columnIdentifiers.add("Objeto Type");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(700);
	}
}
