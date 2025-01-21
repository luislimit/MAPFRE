package com.mdsql.ui.model.cabeceras;

import com.mdval.ui.model.cabeceras.Cabecera;

/**
 * @author LVARONA
 *
 */
public class TablaSinonimosObjetoCabecera extends Cabecera {

    @Override
    public void setupCabecera() {
        addColumn("colReceptor", 110, String.class);
        addColumn("colTipoObjeto", 100, String.class);
        addColumn("colNombreObjeto", 200, String.class);

        addColumn("colEntorno", 90, String.class);
        addColumn("colPropietario", 100, String.class);
        addColumn("colIncluirPDC", 100, String.class);

        addColumn("colHabilitada", 100, String.class);
        addColumn("colPeticion", 190, String.class);
        addColumn("colFuncionNombre", 100, String.class);

        addColumn("colUsuarioAlta", 100, String.class);
        addColumn("colFechaAlta", 100, String.class);
        addColumn("colUsuarioModificacion", 120, String.class);
        addColumn("colFechaModificacion", 120, String.class);

        /*columnIdentifiers.add("Receptor");
		columnIdentifiers.add("Tipo Objeto");
                columnIdentifiers.add("Nombre Objeto");
		
                columnIdentifiers.add("Entorno");
                columnIdentifiers.add("Propietario");
		columnIdentifiers.add("Incluir en PDC");
		
                columnIdentifiers.add("Habilitada");
		columnIdentifiers.add("Petición");
		columnIdentifiers.add("Función Nombre");

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
		
		columnSizes.add(110);
		columnSizes.add(100);//Tipo Objeto	
                columnSizes.add(200); //Nombre Objeto
                
		columnSizes.add(90);
		columnSizes.add(100);
		columnSizes.add(100);
		
                columnSizes.add(100);
		columnSizes.add(190); //Peticion
		columnSizes.add(100); //Funcion Nombre
                
		columnSizes.add(100);
		columnSizes.add(100);
		columnSizes.add(120);
		columnSizes.add(120);        */
    }
}
