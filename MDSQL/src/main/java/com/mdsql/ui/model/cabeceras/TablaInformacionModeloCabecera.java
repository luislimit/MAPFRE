package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaInformacionModeloCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
		addColumn("colNivel", 75, BigDecimal.class);
		addColumn("colTitulo", 400, String.class);
		addColumn("colPeticion", 100, String.class);
		addColumn("colHabilitada", 75, String.class);
		addColumn("colUsuario", 100, String.class);
		addColumn("colActualizado", 100, String.class);

            /*
		columnIdentifiers.add("Nivel");
		columnIdentifiers.add("Titulo");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Habilitado");
		columnIdentifiers.add("Usuario");
		columnIdentifiers.add("Actualizado");
		
		columnClasses.add(BigDecimal.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(400);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);*/
	}
}
