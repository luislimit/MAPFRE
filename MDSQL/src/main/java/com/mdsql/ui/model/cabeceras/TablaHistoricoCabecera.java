package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

import java.util.Date;

/**
 * @author federico
 *
 */
public class TablaHistoricoCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nombre Objeto");
		columnIdentifiers.add("Historificado");
		columnIdentifiers.add("Tipo Objeto");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Usuario");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		
		columnSizes.add(450);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
	}
}
