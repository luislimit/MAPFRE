package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Type implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal numeroOrdenType;
	private BigDecimal codigoEstadoScript;
	private String descripcionEstadoScript;
	private Date fechaCambio;
	private BigDecimal numeroEjecucion;
	private String TYS;
	private String TYB;
	private String PDC;
	private String DROP;
	private String nombreObjeto;
	private List<ScriptType> scriptType;

}
