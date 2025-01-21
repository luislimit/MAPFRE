package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OutputRegistraEjecucionType extends OutputWarning implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7527719835595056930L;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private List<Type> listaType;

}
