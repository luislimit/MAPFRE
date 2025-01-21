package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.Date;

/**
 * @author federico
 *
 */
public class TablaValidacionesProgramadasCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colModelo", 100, String.class);
        addColumn("colDescripcionModelo", 400, String.class);
        addColumn("colAccion", 100, String.class);
        addColumn("colHabilitada", 100, String.class);
        addColumn("colFecha", 100, Date.class);
        addColumn("colUsuario", 100, String.class);
    }
}
