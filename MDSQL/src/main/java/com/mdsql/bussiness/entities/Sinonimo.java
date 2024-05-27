package com.mdsql.bussiness.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Sinonimo {

    private String codigoProyecto;

    private String codUsrGrant;

    private String codOwnerSyn;

    private String desEntorno;

    private String tipObjeto;

    private String valReglaSyn;

    private String mcaPdc;

    private String mcaHabilitado;

    private String codPeticion;

    private String codUsr;

    private Date fecActu;

    private String codUsrAlta;

    private Date fecAlta;
}
