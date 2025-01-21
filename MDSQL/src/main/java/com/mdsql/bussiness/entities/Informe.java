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
public class Informe implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4825049703857074013L;

    private Date fechaCambio;
    private String nombreObjeto;
    private String tipoObjeto;
    private String nombreElemento;
    private String tipoElemento;
    private String tipoCambio;
    private String detalle;
    private String codPeticion;
    private String codUsr;
    private String nombreScript;
    private String versionado;
}
