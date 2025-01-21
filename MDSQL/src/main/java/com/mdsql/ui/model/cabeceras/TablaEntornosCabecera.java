package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEntornosCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colBBDD", 150, String.class);
        addColumn("colEsquema", 150, String.class);
        addColumn("colPassword", 150, String.class);
        addColumn("colComentario", 450, String.class);
        addColumn("colHabilitada", 100, String.class);

        /*columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Esquema");
		columnIdentifiers.add("Password");
		columnIdentifiers.add("Comentario");
		columnIdentifiers.add("Habilitado");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(450);
		columnSizes.add(100);*/
    }
}
