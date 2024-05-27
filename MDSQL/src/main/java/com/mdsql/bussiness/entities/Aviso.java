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
public class Aviso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3697494311301739263L;

    private NivelImportancia nivelImportancia;
    private BigDecimal codigoAviso;
    private String descripcionAviso;
    private String txtAviso;
    private String codigoPeticion;
    private Date fechaAlta;
    private String codigoUsrAlta;
    private String mcaHabilitado;
    private Date fechaActualizacion;
    private String codigoUsuario;

}
