package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaVariablesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        
        addColumn("colCodigoVariable", 150, String.class);
        addColumn("colValor", 150, String.class);
        addColumn("colTipo", 100, String.class);
        addColumn("colEntorno", 100, String.class);
        addColumn("colValorSustituir", 150, String.class);
        addColumn("colBBDD", 150, String.class);
        addColumn("colHabilitada", 100, String.class);
        addColumn("colUsoInterno", 100, String.class);
        addColumn("colExcepcion", 100, String.class);
        addColumn("colPeticion", 150, String.class);
        
        /*
        addColumnIdentifier("lblCodigoVariable");
        addColumnIdentifier("lblValor");
        addColumnIdentifier("lblTipo");
        addColumnIdentifier("lblEntorno");
        addColumnIdentifier("lblValorSustituir");
        addColumnIdentifier("lblBBDD");
        addColumnIdentifier("lblHabilitada");
        addColumnIdentifier("lblUsoInterno");
        addColumnIdentifier("lblExcepcion");
        addColumnIdentifier("lblPeticion");

        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);
        columnClasses.add(String.class);

        columnSizes.add(150);
        columnSizes.add(150);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(150);
        columnSizes.add(150);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(100);
        columnSizes.add(150);
        */
    }
}
