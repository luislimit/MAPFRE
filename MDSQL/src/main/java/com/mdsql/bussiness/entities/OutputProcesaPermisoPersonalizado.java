package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mdval.exceptions.ServiceException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author federico
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OutputProcesaPermisoPersonalizado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3938170200951884495L;
	
	private BigDecimal idProceso;
	private Date FechaProceso;
	private BigDecimal CodigoEstadoProceso;
	private String DescripcionEstadoProceso;
        private List<Script> listaScripts;
        // Para los warnings
        private ServiceException serviceException;
}


