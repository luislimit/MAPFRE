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
public class InformeCambioTRN implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4825049703857074013L;
    
    private Date    fechaCambio;
    private String  nomObjeto;
    private String  tipObjeto;
    private String  nomElemento;
    private String  tipElemento;	 
    private String  tipCambio;
    private String  detalle;
    private String  codPeticion;
    private String  codUsr;
    private String  nomScript;
    private String  versionado;
}
