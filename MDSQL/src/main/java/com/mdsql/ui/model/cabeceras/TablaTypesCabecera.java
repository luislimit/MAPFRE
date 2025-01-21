package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaTypesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colOrden", 75, Integer.class);
        addColumn("colObjeto", 500, String.class);

        /*columnIdentifiers.add("Orden");
		columnIdentifiers.add("Objeto");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(500);*/
    }
}
