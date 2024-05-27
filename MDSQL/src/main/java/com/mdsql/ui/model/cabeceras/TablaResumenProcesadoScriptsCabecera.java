package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaResumenProcesadoScriptsCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Orden");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Operaciones");
		columnIdentifiers.add("Objetos");
		columnIdentifiers.add("Con error");
		columnIdentifiers.add("Script");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(75);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(550);
	}
}
