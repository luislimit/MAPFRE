package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.mdval.exceptions.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OutputRegistraEjecucionType implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7527719835595056930L;
	private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private List<Type> listaType;

    private Integer result;
    private ServiceException serviceException;
}
