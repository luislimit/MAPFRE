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
public class OutputConsultaEntrega implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2931355001183634051L;
	private String txtRutaEntrega;
    private String nombreFicheroVigente;
    private String nombreFicheroHistorico;
    private String nombreFicheroType;

}
