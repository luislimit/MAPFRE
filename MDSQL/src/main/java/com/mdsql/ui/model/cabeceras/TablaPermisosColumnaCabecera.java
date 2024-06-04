package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author LVARONA
 *
 */
public class TablaPermisosColumnaCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
		columnIdentifiers.add("Receptor");
                columnIdentifiers.add("Nombre Objeto");
                columnIdentifiers.add("Columna");
                columnIdentifiers.add("Tipo Objeto");                
                
		columnIdentifiers.add("Permiso");
		columnIdentifiers.add("Entorno");
		columnIdentifiers.add("Grant Option");

                columnIdentifiers.add("Incluir en PDC");
		columnIdentifiers.add("Habilitada");
		columnIdentifiers.add("Petición");

                columnIdentifiers.add("Usuario Alta");
                columnIdentifiers.add("Fecha Alta");
                columnIdentifiers.add("Usuario Modificación");
                columnIdentifiers.add("Fecha Modificación");                    
		
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
		columnClasses.add(String.class);
		columnClasses.add(String.class);

                columnSizes.add(110); //Receptor
		columnSizes.add(100);		
		columnSizes.add(100);
                columnSizes.add(100);

                columnSizes.add(100); //Permiso
		columnSizes.add(90); //Entorno
		columnSizes.add(100);
		
                columnSizes.add(100); //Incluir en PDC
		columnSizes.add(90); // Habilitada
		columnSizes.add(200); //Petición
                
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(120);
		columnSizes.add(120);                     
	}
}


