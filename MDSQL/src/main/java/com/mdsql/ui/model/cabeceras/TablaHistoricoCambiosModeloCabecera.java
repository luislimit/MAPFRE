package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaHistoricoCambiosModeloCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colFecha", 80, Date.class);
        addColumn("colObjeto", 150, String.class);
        addColumn("colTipo", 80, String.class);
        addColumn("colElemento", 80, String.class);
        addColumn("colTipo", 30, String.class);
        addColumn("colCambio", 20, String.class);
        addColumn("colDetalleCambio", 100, String.class);
        addColumn("colPeticion", 100, String.class);
        addColumn("colSolicitante", 80, String.class);
        addColumn("colScript", 300, String.class);

        /*
        addColumnIdentifier("lblFecha");
        addColumnIdentifier("lblObjeto");
        addColumnIdentifier("lblTipo");
        addColumnIdentifier("lblElemento");
        addColumnIdentifier("lblTipo");
        addColumnIdentifier("lblCambio");
        addColumnIdentifier("lblDetalleCambio");
        addColumnIdentifier("lblPeticion");
        addColumnIdentifier("lblSolicitante");
        addColumnIdentifier("lblScript");

        columnClasses.add(Date.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);

        columnSizes.add(100);
        columnSizes.add(200);
        columnSizes.add(100);
        columnSizes.add(200);
        columnSizes.add(100);
        columnSizes.add(300);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(200);*/
    }
}
