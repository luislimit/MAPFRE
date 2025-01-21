package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 *
 * @author LVARONA
 */
public class TablaScriptsPeticionCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colTablaProcesado", 150, String.class);
        addColumn("colNombreScript", 300, String.class);
        addColumn("colEstado", 100, String.class);
        addColumn("colFecha", 100, String.class);
        addColumn("colUsuario", 100, String.class);
        /*
        addColumnIdentifier("TablaProcesado");
        addColumnIdentifier("NombreScript");
        addColumnIdentifier("Estado");
        addColumnIdentifier("Fecha");
        addColumnIdentifier("Usuario");

        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);

        columnSizes.add(150);
        columnSizes.add(300);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(100);*/
    }
}
