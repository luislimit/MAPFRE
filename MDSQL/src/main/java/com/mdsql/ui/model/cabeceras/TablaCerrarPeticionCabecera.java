package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 *
 * @author LVARONA
 */
public class TablaCerrarPeticionCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colTablaProcesado", 150, String.class);
        addColumn("colComentario", 300, String.class);
        addColumn("colEstado", 100, String.class);
        addColumn("colErwin", 80, String.class);
        addColumn("colFecha", 100, String.class);
        addColumn("colUsuario", 100, String.class);
    }

}
