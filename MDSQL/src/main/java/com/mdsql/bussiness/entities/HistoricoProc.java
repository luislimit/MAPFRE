package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HistoricoProc implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3330091903761565934L;
    private Date fechaCambio;
    private String tipoCambio;
    private String valorCambio;
    private String codUsr;
    private String descripcion;
}
