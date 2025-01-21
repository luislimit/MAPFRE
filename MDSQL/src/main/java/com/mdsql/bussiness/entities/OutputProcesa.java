package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author federico
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputProcesa extends OutputWarning implements Serializable {

    private static final long serialVersionUID = 3938170200951884495L;

    private BigDecimal idProceso;
    private Date fechaProceso;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;

}
