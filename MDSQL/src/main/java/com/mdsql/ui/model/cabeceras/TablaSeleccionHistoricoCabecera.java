package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaSeleccionHistoricoCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Configurado");
		columnIdentifiers.add("Objeto");
		columnIdentifiers.add("Tipo");
		columnIdentifiers.add("Histórico");
		columnIdentifiers.add("Vigente");
		
		columnClasses.add(Boolean.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Boolean.class);
		columnClasses.add(Boolean.class);
		
		columnSizes.add(100);
		columnSizes.add(700);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(75);
	}
}
