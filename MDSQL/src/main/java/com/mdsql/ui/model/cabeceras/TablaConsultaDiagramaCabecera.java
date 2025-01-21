package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author LVARONA
 *
 */
public class TablaConsultaDiagramaCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colTabla", 150, String.class);
        addColumn("colSubjectArea", 100, String.class);
        addColumn("colDiagrama", 100, String.class);
        addColumn("colComentario", 200, String.class);
        addColumn("colEliminado", 100, String.class);
        addColumn("colPeticion", 100, String.class);
        addColumn("colFecha", 100, String.class);
        addColumn("colUsuario", 100, String.class);
        /*columnIdentifiers.add(literales.getLiteral("tblDiagramas.colTabla"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colSubjectArea"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colDiagrama"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colComentario"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colEliminado"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colPeticion"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colFecha"));
		columnIdentifiers.add(literales.getLiteral("tblDiagramas.colUsuario"));
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);                
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);                
		
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(200);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);*/
    }
}
