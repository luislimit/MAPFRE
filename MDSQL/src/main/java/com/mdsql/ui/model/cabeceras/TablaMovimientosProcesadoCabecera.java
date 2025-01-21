package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 *
 * @author LVARONA
 */
public class TablaMovimientosProcesadoCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colFecha", 100, String.class);
        addColumn("colCambio", 200, String.class);
        addColumn("colValor", 350, String.class);
        addColumn("colUsuario", 100, String.class);
    }

}
