package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SubProyecto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3697494311301739263L;
	
	private String codigoSubProyecto;
    private String descripcionSubProyecto;
}
