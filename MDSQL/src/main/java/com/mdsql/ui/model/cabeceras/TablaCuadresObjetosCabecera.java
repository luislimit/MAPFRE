package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaCuadresObjetosCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add("Nombre Objeto");
		columnIdentifiers.add("Tipo Objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("SCRIPT");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		
		columnSizes.add(300);
		columnSizes.add(200);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(120);
	}
}
