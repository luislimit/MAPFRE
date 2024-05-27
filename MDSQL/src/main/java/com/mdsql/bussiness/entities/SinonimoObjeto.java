package com.mdsql.bussiness.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SinonimoObjeto {
    private String codigoProyecto;

    private String codUsrGrant;
    
    private String nomObjeto;

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


