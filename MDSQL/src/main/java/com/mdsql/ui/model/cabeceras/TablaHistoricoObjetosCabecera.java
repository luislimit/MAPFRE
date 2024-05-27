package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaHistoricoObjetosCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Submodelo");
		columnIdentifiers.add("Solicitado");
		columnIdentifiers.add("Usuario");
		columnIdentifiers.add("Operación");
		columnIdentifiers.add("Oper.Padre");
		columnIdentifiers.add("Script");
		columnIdentifiers.add("Estado");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(50);
		columnSizes.add(80);
		columnSizes.add(100);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(75);
		columnSizes.add(75);
		columnSizes.add(400);
		columnSizes.add(100);
	}
}
