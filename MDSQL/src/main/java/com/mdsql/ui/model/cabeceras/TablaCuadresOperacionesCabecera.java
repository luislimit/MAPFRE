package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaCuadresOperacionesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colTipoObjeto", 200, String.class);
        addColumn("colAccion", 120, String.class);
        addColumn("colBBDD", 120, BigDecimal.class);
        addColumn("colScript", 120, BigDecimal.class);

        /*columnIdentifiers.add("Tipo Objeto");
		columnIdentifiers.add("Acción");
		columnIdentifiers.add("BBDD");
		columnIdentifiers.add("SCRIPT");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		
		columnSizes.add(200);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(120);*/
    }
}
