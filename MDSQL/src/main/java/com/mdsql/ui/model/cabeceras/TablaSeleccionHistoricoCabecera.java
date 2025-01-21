package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaSeleccionHistoricoCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colConfigurado", 100, Boolean.class);
        addColumn("colObjeto", 700, String.class);
        addColumn("colTipo", 100, String.class);
        addColumn("colHistorico", 75, Boolean.class);
        addColumn("colVigente", 75, Boolean.class);

        /*columnIdentifiers.add("Configurado");
		columnIdentifiers.add("Objeto");
		columnIdentifiers.add("Tipo");
		columnIdentifiers.add("Histórico");
		columnIdentifiers.add("Vigente");
		
		columnClasses.add(Boolean.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Boolean.class);
		columnClasses.add(Boolean.class);
		
		columnSizes.add(100);
		columnSizes.add(700);
		columnSizes.add(100);
		columnSizes.add(75);
		columnSizes.add(75);*/
    }
}
