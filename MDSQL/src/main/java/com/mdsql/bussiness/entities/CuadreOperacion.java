package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CuadreOperacion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1868061244918428945L;
	private String tipoObjeto;
    private String tipoAccion;
    private BigDecimal numeroOperacionBBDD;
    private BigDecimal numeroOperacionScript;

}
