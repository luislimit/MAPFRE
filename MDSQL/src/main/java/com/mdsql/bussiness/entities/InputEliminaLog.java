package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hcarreno
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InputEliminaLog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 386181220435550826L;
	private BigDecimal idProceso;
    private BigDecimal numeroOrden;
    private BigDecimal numeroIteracion;
    private BigDecimal numeroEjecucion;
    private BigDecimal numeroParche;
    private BigDecimal numeroSentencia;
    private String mcaEliminada;
    private String txtComentario;

}
