package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaResumenProcesadoOperacionesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colTipoObjeto", 155, String.class);
        addColumn("colAccion", 100, String.class);
        addColumn("colBBDD", 50, String.class);
        addColumn("colScript", 75, String.class);

        /*columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Script");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(155);
		columnSizes.add(100);
		columnSizes.add(50);
		columnSizes.add(75);*/
    }
}
