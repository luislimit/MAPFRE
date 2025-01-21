package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author LVARONA
 *
 */
public class TablaDiagramaCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
            addColumn("colTabla",100, String.class);
            addColumn("colComentario",350,  String.class);
            addColumn("colPeticion", 150, String.class);
            addColumn("colFecha", 100, String.class);
            addColumn("colUsuario", 100, String.class);
            
		/*addColumnIdentifier("Tabla");
		addColumnIdentifier("Comentario");
		addColumnIdentifier("Peticion");
		addColumnIdentifier("Fecha");
		addColumnIdentifier("Usuario");
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		columnClasses.add(String.class);                
		
		columnSizes.add(100);
		columnSizes.add(350);
		columnSizes.add(150);
		columnSizes.add(100);
		columnSizes.add(100);*/
	}
}
