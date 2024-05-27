package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EntornoPrueba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -72890274682898477L;

	private String nombreEntorno;
	private String bbdd;
	private String esquema;
	private String tablespace;
	private String gradoParal;
	private String descripcion;
	private String mcaHabilitado; 

}
