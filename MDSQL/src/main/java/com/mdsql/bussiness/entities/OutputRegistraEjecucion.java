package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputRegistraEjecucion extends OutputWarning implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private String nombreScript;
    private BigDecimal numOrden;
    private BigDecimal codigoEstadoScript;
    private String descripcionEstadoScript;
    private String txtCuadreOperacion;
    private String txtCuadreObj;
    private Date fechaEjecucion;
}
