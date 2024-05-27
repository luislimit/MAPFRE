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
public class Objeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8271362075599903713L;
	
	private String nombreObjeto;
	private String tipoObjeto;
	private String accion;
	private String bbdd;
	private String script;

}
