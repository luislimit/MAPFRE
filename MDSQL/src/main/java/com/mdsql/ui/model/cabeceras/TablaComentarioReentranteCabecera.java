package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 *
 * @author LVARONA
 */
public class TablaComentarioReentranteCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colCampo", 100, String.class);
        addColumn("colIdioma", 100, String.class);
        addColumn("colTipo", 120, String.class);
        addColumn("colRDO", 120, String.class);
        addColumn("colPlantilla", 200, String.class);
        addColumn("colGenerico", 200, String.class);

        /*addColumnIdentifier("Campo");
        addColumnIdentifier("Idioma");
        addColumnIdentifier("Tipo");
        addColumnIdentifier("RDO");
        addColumnIdentifier("Pantilla");
        addColumnIdentifier("Generico");

        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);

        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(120);
        columnSizes.add(120);
        columnSizes.add(200);
        columnSizes.add(200);*/
    }
}
