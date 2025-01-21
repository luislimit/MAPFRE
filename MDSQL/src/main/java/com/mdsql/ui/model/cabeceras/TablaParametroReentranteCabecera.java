package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

public class TablaParametroReentranteCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colParametro", 100, String.class);
        addColumn("colValor", 120, String.class);
        /*columnIdentifiers.add("Parámetro");
		columnIdentifiers.add("Valor");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(120);*/
    }
}
