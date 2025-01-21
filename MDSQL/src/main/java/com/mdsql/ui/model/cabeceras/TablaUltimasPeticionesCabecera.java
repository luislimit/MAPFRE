package com.mdsql.ui.model.cabeceras;

import java.util.Date;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaUltimasPeticionesCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colPeticion", 100, String.class);
        addColumn("colSolicitado", 100, String.class);
        addColumn("colEstado", 100, String.class);
        addColumn("colFecha", 100, Date.class);
        addColumn("colUsuario", 100, String.class);
        addColumn("colDescripcion", 400, String.class);
        addColumn("colComentarioEntrega", 400, String.class);

        /*columnIdentifiers.add("Petición");
		columnIdentifiers.add("Solicitado");
		columnIdentifiers.add("Estado");
		columnIdentifiers.add("Fecha");
		columnIdentifiers.add("Usuario");
		columnIdentifiers.add("Descripción");
		columnIdentifiers.add("Comentario Entrega");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(Date.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(400);
		columnSizes.add(400);*/
    }
}
