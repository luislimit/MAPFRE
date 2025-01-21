package com.mdsql.ui.model.cabeceras;

import java.math.BigDecimal;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author federico
 *
 */
public class TablaAjustarLogEjecucionCabecera extends Cabecera {

    @Override
    public void setupCabecera() {

        addColumn("colScript", 300, String.class);
        addColumn("colProcesado", 90, BigDecimal.class);
        addColumn("colIteracion", 90, BigDecimal.class);
        addColumn("colEjecucion", 90, BigDecimal.class);
        addColumn("colSentencia", 90, BigDecimal.class);
        addColumn("colTipoObjeto", 200, String.class);
        addColumn("colAccion", 50, String.class);
        addColumn("colNombre", 200, String.class);
        addColumn("colEstado", 90, String.class);
        addColumn("colEliminado", 90, String.class);
        addColumn("colComentario", 500, String.class);

        /*addColumnIdentifier("Script");
		addColumnIdentifier("Procesado");
		addColumnIdentifier("Iteración");
		addColumnIdentifier("Ejecución");
		addColumnIdentifier("Sentencia");
		addColumnIdentifier("Tipo objeto");
		addColumnIdentifier("Acción");
		addColumnIdentifier("Nombre");
		addColumnIdentifier("Estado");
		addColumnIdentifier("Eliminado");
		addColumnIdentifier("Comentario");
		
		columnClasses.add(String.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(BigDecimal.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(300);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(200);
		columnSizes.add(50);
		columnSizes.add(200);
		columnSizes.add(90);
		columnSizes.add(90);
		columnSizes.add(500);*/
    }
}
