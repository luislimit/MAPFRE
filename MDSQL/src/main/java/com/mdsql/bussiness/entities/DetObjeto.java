package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DetObjeto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2593685243467047327L;
	
	private BigDecimal numeroSentencia;
    private String nombreObjetoPadre;
    private String tipoObjetoPadre;
    private String tipoAccionPadre;
    private String nombreObjeto;
    private String nombreObjetoDestino;
    private String tipoObjeto;
    private String tipoAccion;
    private String tipoDato;
    private BigDecimal numeroLongitud;
    private BigDecimal numeroDecimal;
    private String bbdd;
    private String script;

}
