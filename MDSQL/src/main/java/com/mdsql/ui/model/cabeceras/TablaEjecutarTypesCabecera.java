package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEjecutarTypesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colOrden", 75, Integer.class);
        addColumn("colEstado", 100, String.class);
        addColumn("colFecha", 100, Date.class);
        addColumn("colDrop", 50, String.class);
        addColumn("colTYS", 50, String.class);
        addColumn("colTYB", 50, String.class);
        addColumn("colPDC", 50, String.class);
        addColumn("colObjetoType", 700, String.class);

        /*columnIdentifiers.add("Orden");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Drop");
		columnIdentifiers.add("TYS");
		columnIdentifiers.add("TYB");
		columnIdentifiers.add("PDC");
		columnIdentifiers.add("Objeto Type");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(75);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(700);*/
    }
}
