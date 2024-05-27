package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScriptEjecutado implements Serializable, Scriptable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3936669930934375288L;
	
	private BigDecimal numeroOrden;
    private BigDecimal codigoEstadoScript;
    private String descripcionEstadoScript;
    private Date fechaEjecucion;
    private String txtCuadreOperacion;
    private String txtCueadreObj;
    private String nombreScript;
    private String mcaErrores;

}
