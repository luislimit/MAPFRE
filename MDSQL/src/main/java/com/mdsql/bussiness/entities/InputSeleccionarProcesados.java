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
    private BigDecimal idProceso;
    private String pCodigoUsuarioPeticion;
    private String pFechaInicio;
    private String pFechaFin;
    private String pCodigoUsuario;
    private String pCodigoproyecto;
    private String pCodigoSubProyecto;
    //private String pDescripcionEstadoProceso;
    private BigDecimal pUltimas;

    // Valores S/N en cada caso
    private String mcaGenerado;
    private String mcaEjecutado;
    private String mcaCerrado;
    private String mcaEnEjecucion;
    private String mcaRechazado;
    private String mcaIncidencia;
    private String mcaError;
    private String mcaEntregado;
    private String mcaExcluido;
    // 
    private String mcaConErrores;

}
