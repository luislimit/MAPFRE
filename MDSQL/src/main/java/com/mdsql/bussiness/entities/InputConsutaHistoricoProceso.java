package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class InputConsutaHistoricoProceso implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5934430341543066426L;
	
    private String codigoProyecto;
    private String nombreObjetoPadre;
    private String tipoObjetoPadre;
    private String tipoAccionPadre;
    private String nombreObjeto;
    private String tipoObjeto;
    private String tipoAccion;
    private Date fechaDesde;
    private Date fechaHasta;
    private BigDecimal codigoTipoObjeto;
    private BigDecimal codigoOperacion;
    private BigDecimal codigoEstadoProceso;
    private BigDecimal codigoEstadoScript;

}
