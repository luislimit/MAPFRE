package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaModelosCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
                addColumn("colCodigo", 100, String.class);
		addColumn("colDescripcion", 350, String.class);
		addColumn("colEsquema", 150, String.class);
		addColumn("colBBDD", 150, String.class);
		addColumn("colCarpeta", 250, String.class);
		addColumn("colCapaUsrOwn", 150, String.class);
		addColumn("colGeneraVariables", 150, String.class);
		addColumn("colGrantAll", 100, String.class);
		addColumn("colGrantPublic", 100, String.class);
		addColumn("colInhabilitado", 100, String.class);
		addColumn("colObservaciones", 150, String.class);
		addColumn("colEntregaPDC", 150, String.class);
               
            
            /*
		addColumnIdentifier("codProyecto");
		addColumnIdentifier("nombreModelo");
		addColumnIdentifier("nombreEsquema");
		addColumnIdentifier("nombreBbdd");
		addColumnIdentifier("nombreCarpetaAdj");
		addColumnIdentifier("codigoCapaUsrown");
		addColumnIdentifier("mcaVariables");
		addColumnIdentifier("mcaGrantAll");
		addColumnIdentifier("mcaGrantPublic");
		addColumnIdentifier("mcaInh");
		addColumnIdentifier("observaciones");
		addColumnIdentifier("entregaPDC");
		
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
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(350);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(250);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(150);
		columnSizes.add(150);
*/
	}
}
