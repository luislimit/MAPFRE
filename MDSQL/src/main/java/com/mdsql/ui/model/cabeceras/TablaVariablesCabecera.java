package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaVariablesCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Código Variable");
		columnIdentifiers.add("Valor");
		columnIdentifiers.add("Tipo");
		columnIdentifiers.add("Entorno");
		columnIdentifiers.add("Valor Sustituir");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Habilitada");
		columnIdentifiers.add("Uso Interno");
		columnIdentifiers.add("Petición");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(150);
	}
}
