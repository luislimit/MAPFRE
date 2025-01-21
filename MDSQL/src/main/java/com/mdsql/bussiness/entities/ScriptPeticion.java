package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LVARONA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScriptPeticion implements Serializable, Scriptable {

    /**
     *
     */
    private static final long serialVersionUID = -3317596703100222456L;

    private String nombreTabla;
    private BigDecimal idProceso;
    private BigDecimal numeroOrden;
    private String nombreScript;
    private BigDecimal codigoEstadoScript;
    private String descripcionEstadoScript;
    private Date fecha;
    private String codUsr;
    private String mcCambioNombre;
}
