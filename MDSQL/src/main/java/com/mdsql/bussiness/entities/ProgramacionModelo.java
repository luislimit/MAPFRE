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
public class ProgramacionModelo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1060226242769948540L;

    private String codigoProyecto;
    private String nombreModelo;
    private String mcaHabilitado;
    private String tipAccion;
    private String codUsr;
    private Date fecha;
}
