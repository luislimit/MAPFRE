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
public class Historico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3330091903761565934L;
	private String nombreObjeto;
    private String historico;
    private String historificado;
    private String tipoObjeto;
    private String peticion;
    private Date fechaActualizacion;
    private String codigoUsuario;
}
