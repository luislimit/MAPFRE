package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaDetalleScriptCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colSentencia", 75, BigDecimal.class);
        addColumn("colObjetoPadre", 250, String.class);
        addColumn("colTipoObjeto", 150, String.class);
        addColumn("colAccion", 120, String.class);
        addColumn("colObjeto", 200, String.class);
        addColumn("colDetalle", 150, String.class);
        addColumn("colTipoObjeto", 120, String.class);
        addColumn("colAccion", 120, String.class);
        addColumn("colTipoDato", 100, String.class);
        addColumn("colPrecision", 75, BigDecimal.class);
        addColumn("colEscala", 75, BigDecimal.class);
        
        /*
        addColumnIdentifier("Sentencia");
        addColumnIdentifier("ObjetoPadre");
        addColumnIdentifier("TipoObjeto");
        addColumnIdentifier("Accion");
        addColumnIdentifier("Objeto");
        addColumnIdentifier("Detalle");
        addColumnIdentifier("TipoObjeto");
        addColumnIdentifier("Accion");
        addColumnIdentifier("TipoDato");
        addColumnIdentifier("Precision");
        addColumnIdentifier("Escala");

        columnClasses.add(BigDecimal.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(BigDecimal.class);
        columnClasses.add(BigDecimal.class);

        columnSizes.add(75);
        columnSizes.add(250);
        columnSizes.add(150);
        columnSizes.add(120);
        columnSizes.add(200);
        columnSizes.add(150);
        columnSizes.add(120);
        columnSizes.add(100);
        columnSizes.add(75);
        columnSizes.add(75);
*/
    }
}
