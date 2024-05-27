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
public class InputSeleccionarProcesados implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6491053159448299653L;
	
	private String pCodigoPeticion;
    private String pCodigoUsuarioPeticion;
    private Date pFechaInicio;
    private Date pFechaFin;
    private String pCodigoUsuario;
    private String pCodigoproyecto;
    private String pCodigoSubProyecto;
    private String pDescripcionEstadoProceso;
    private BigDecimal pUltimas;

}
