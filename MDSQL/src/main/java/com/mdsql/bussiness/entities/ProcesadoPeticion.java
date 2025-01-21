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
public class ProcesadoPeticion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2012801467618072649L;

    private BigDecimal idProceso;
    private String nomTabla;
    private String descripcion;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private String codUsr;
    private Date fecha;
    private String mcaRechazar;
    private String mcaExcluir;
    private String erwin;
}
