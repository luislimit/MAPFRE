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
public class Entorno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -72890274682898477L;

	private String bbdd;
	private String esquema;
	private String password;
	private String comentario;
	private String habilitado;

}
