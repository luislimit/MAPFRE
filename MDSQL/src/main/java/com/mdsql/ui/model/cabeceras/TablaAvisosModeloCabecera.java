package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author LVARONA
 *
 */
public class TablaAvisosModeloCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colImportancia", 80, String.class);
        addColumn("colNombreObjeto", 200, String.class);
        addColumn("colTitulo", 100, String.class);
        addColumn("colHabilitada", 80, String.class);
        addColumn("colPeticion", 100, String.class);
        addColumn("colDescripcion", 100, String.class);
    }
}
