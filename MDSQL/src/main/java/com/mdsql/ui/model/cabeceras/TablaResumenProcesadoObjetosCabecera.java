package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaResumenProcesadoObjetosCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colObjeto", 335, String.class);
        addColumn("colTipoObjeto",150, String.class);
        addColumn("colAccion", 100, String.class);
        addColumn("colBBDD", 75, String.class);
        addColumn("colScript", 100, String.class);
        /*columnIdentifiers.add("Nombre objeto");
		columnIdentifiers.add("Tipo objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("Script");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(335);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(100);*/
    }
}
