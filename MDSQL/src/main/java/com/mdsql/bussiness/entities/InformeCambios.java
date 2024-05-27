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
public class InformeCambios implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4825049703857074013L;
	private String codigoPeticion;
    private BigDecimal idProceso;
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
    private String descripcionEstadoProceso;
    private Date fechaProceso;
    private String codigoSubProyecto;
    private String codigoUsuarioPeticion;
    private String codigoUsuario;
    private String descripcionEstadoScript;
    private String nombreScript;

}
