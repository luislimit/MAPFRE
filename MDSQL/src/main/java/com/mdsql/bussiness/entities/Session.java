package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.nio.charset.Charset;

import com.mdsql.utils.MDSQLConstants.Procesado;

import lombok.Getter;
import lombok.Setter;

public class Session implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4166583635098525436L;
	
	@Getter
	@Setter
	private Proceso proceso;
	
	@Getter
	@Setter
	private String codUsr;
	
	@Getter
	@Setter
	private String selectedRoute;
	
	@Getter
	@Setter
	private Procesado procesado;
	
	@Getter
	@Setter
	private Charset fileCharset;
	
	@Getter
	@Setter
	private String rutaScript;
}
