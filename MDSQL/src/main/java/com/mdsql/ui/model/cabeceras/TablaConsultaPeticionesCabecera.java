package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;
import java.util.Date;

/**
 * @author federico
 *
 */
public class TablaConsultaPeticionesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colPeticion", 100, String.class);
        addColumn("colEstado", 100, String.class);
        addColumn("colFecha", 100, Date.class);
        addColumn("colModelo", 100, String.class);
        addColumn("colSubModelo", 100, String.class);
        addColumn("colConErrores", 80, String.class);
        addColumn("colSolicitante", 100, String.class);
        addColumn("colDescripcion", 450, String.class);
        addColumn("colProcesado", 100, String.class);
        addColumn("colUsuario", 100, String.class);
    }
}
