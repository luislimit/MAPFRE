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
public class LogEjecucion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 126031777836246207L;
	private String nombreScript;
    private BigDecimal idProceso;
    private BigDecimal numeroOrden;
    private BigDecimal numeroIteracion;
    private BigDecimal numeroEjecucion;
    private BigDecimal numeroParche;
    private BigDecimal numeroSentencia;
    private String tipoObjeto;
    private String tipoAccion;
    private String nombreObjeto;
    private String descripcionEstadoEjecucion;
    private String mcaEliminada;
    private String txtObsElimina;

}
