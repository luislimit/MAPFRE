/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.bussiness.entities;

/**
 *
 * @author LVARONA
 */


import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReentranteInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -72890274682898477L;

        private String scriptCambio;
        private String scriptCreacion;
        private String scriptComentarios;
        private String tipoOperacion;
        private String nombreTabla;
        private List <ReentranteParametro> parametros;
        
        // En principio contiene la ruta del primero de los ficheros indicados
        // el orden de búsqueda será Cambio, Creacion, Comentarios
        private String ruta;
}
