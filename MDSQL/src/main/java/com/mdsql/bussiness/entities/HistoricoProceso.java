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
public class HistoricoProceso implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3330091903761565934L;
	private String codigoPeticion;
    private String descripcionEstadoProceso;
    private Date fechaProceso;
    private String codigoSubProyecto;
    private String codigoUsuarioPeticion;
    private String codigoUsuario;
    private String tipoAccion;
    private String tipoAccionPadre;
    private String nombreScript;
    private String descripcionEstadoScript;
    private BigDecimal idProceso;
    private BigDecimal numeroOrden;
}
