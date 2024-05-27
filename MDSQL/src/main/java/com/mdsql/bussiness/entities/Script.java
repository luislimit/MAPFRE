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
public class Script implements Serializable, Scriptable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3317596703100222456L;
	
	private String tipoScript;
    private List<TextoLinea> lineasScript;
    private String nombreScript;
    private BigDecimal codigoEstadoScript;
    private String descripcionEstadoScript;
    private BigDecimal numeroOrden;
    private String nombreScriptLanza;
    private String txtScriptLanza;
    private String nombreScriptLog;
    private String operaciones;
    private String objetos;
    private Date fecha;
    private Boolean selected;
}
