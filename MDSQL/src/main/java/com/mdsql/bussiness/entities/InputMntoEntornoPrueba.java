package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hcarreno
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InputMntoEntornoPrueba implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5934430341543066426L;
	
    private String nombreEntorno;
    private String bbdd;
    private String esquema;
    private String descripcion;
    private String tablespace;
    private String gradoParal;
    private String mcaHabilitado;
}
