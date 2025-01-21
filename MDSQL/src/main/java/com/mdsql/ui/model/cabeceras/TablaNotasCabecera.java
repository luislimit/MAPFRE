package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.Date;

/**
 * @author federico
 *
 */
public class TablaNotasCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colNivel", 75, String.class);
        addColumn("colTitulo", 400, String.class);
        addColumn("colPeticion", 100, String.class);
        addColumn("colHabilitado", 75, String.class);
        addColumn("colUsuario", 100, String.class);
        addColumn("colActualizado", 100, Date.class);

        /*		addColumnIdentifier("Nivel");
		addColumnIdentifier("Título");
		addColumnIdentifier("Petición");
		addColumnIdentifier("Habilitado");
		addColumnIdentifier("Usuario");
		addColumnIdentifier("Actualizado");

		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);

		columnSizes.add(75);
		columnSizes.add(400);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);
         */
    }
}
