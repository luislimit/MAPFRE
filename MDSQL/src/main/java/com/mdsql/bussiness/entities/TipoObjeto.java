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
public class TipoObjeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376181529297266850L;
	private String codigoTipoObjeto;
	private String tipoObjeto;

}
