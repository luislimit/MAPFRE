package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaModelosCabecera extends Cabecera {
	
	public void setupCabecera() {
		columnIdentifiers.add(literales.getLiteral("tblModelos.codProyecto"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.nombreModelo"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.nombreEsquema"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.nombreBbdd"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.nombreCarpetaAdj"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.codigoCapaUsrown"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.mcaVariables"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.mcaGrantAll"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.mcaGrantPublic"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.mcaInh"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.observaciones"));
		columnIdentifiers.add(literales.getLiteral("tblModelos.entregaPDC"));
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(350);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(250);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(150);
		columnSizes.add(150);
	}
}
