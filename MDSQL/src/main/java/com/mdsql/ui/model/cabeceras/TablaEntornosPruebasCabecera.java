package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaEntornosPruebasCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
                addColumn("colNombreEntorno", 100, String.class);
		addColumn("colBBDD", 100, String.class);
		addColumn("colEsquema", 100, String.class);
		addColumn("colTablespace", 100, String.class);
		addColumn("colGradoParal", 100, String.class);
		addColumn("colDescripcion", 450, String.class);
                addColumn("colHabilitada", 100, String.class);            
                        
		/*columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblNombreEntorno"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblBBDD"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblEsquema"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblTablespace"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblGradoParal"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblDescripcion"));
                columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblHabilitada"));
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
                columnClasses.add(String.class);
		
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(450);
                columnSizes.add(100);*/
	}
}
