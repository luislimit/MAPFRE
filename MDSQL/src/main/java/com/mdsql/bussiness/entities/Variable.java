package com.mdsql.bussiness.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Variable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -72890274682898477L;

	private String codigoProyecto;
	private String codigoVariable;
	private String valor;
	private String tipo;
	private String entorno;
	private String valorSustituir;
	private String bbdd;
	private String habilitada;
	private String usoInterno;
	private String peticion;
	private String usrAlta;
	private Date fechaAlta;
	private String usrModificacion;
	private Date fechaModificacion;
	private String comentario;

}
