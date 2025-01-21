package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaFicherosReentranteCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colOrden", 75, Integer.class);
        addColumn("colFichero", 500, String.class);

        /*columnIdentifiers.add("Orden");
		columnIdentifiers.add("Fichero");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(500);*/
    }
}
