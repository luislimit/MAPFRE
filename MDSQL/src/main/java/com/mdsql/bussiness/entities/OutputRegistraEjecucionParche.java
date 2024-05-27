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
public class OutputRegistraEjecucionParche implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1379177878327861639L;
	private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private String nombreScript;
    private BigDecimal codigoEstadoScript;
    private String descripcionEstadoScript;
    private String txtCuadreOperacion;
    private String txtCuadreObjeto;

}
