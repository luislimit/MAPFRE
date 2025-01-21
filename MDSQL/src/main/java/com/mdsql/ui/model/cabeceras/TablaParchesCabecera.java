package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaParchesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colOrden", 100, Integer.class);
        addColumn("colEstado", 120, String.class);
        addColumn("colFecha", 120, Date.class);
        addColumn("colProcesado", 120, String.class);
        addColumn("colEjecucion", 120, String.class);
        addColumn("colIteracion", 120, String.class);
        addColumn("colScript", 350, String.class);
        addColumn("colComentario", 500, String.class);
        /*	columnIdentifiers.add("Orden");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Procesado");
		columnIdentifiers.add("Ejecucion");
		columnIdentifiers.add("Iteracion");
		columnIdentifiers.add("Script");
		columnIdentifiers.add("Comentario");
		
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Integer.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(120);
		columnSizes.add(350);
		columnSizes.add(500);*/
    }
}
