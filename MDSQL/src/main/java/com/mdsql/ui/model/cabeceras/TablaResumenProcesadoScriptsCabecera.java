package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaResumenProcesadoScriptsCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
		addColumn("colOrden", 50, Integer.class);
		addColumn("colEstado", 50, String.class);
		addColumn("colFecha", 50, Date.class);
		addColumn("colOperacion", 75, String.class);
		addColumn("colObjeto", 50, String.class);
		addColumn("colConErrores", 50, String.class);
		addColumn("colScript", 550, String.class);
		
		/*columnIdentifiers.add("Orden");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Operaciones");
		columnIdentifiers.add("Objetos");
		columnIdentifiers.add("Con error");
		columnIdentifiers.add("Script");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(75);
		columnSizes.add(50);
		columnSizes.add(50);
		columnSizes.add(550);*/
	}
}
