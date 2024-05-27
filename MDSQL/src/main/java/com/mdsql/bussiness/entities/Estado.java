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
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -72890274682898477L;
	private BigDecimal codigoEstado;
	private String descripcionEstado;

}
