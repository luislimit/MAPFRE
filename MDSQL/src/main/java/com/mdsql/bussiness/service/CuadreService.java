package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface CuadreService {

	/**
	 * @param idProceso
	 * @param numeroOrden
	 *
	 * @return cuadreOperacionList
	 */
	List<CuadreOperacion> consultaCuadreOperacionesScript(BigDecimal idProceso, BigDecimal numeroOrden)
			throws ServiceException;

	/**
	 * @param idProceso
	 * @param numeroOrden
	 *
	 * @return cuadreObjetoList
	 */
	List<CuadreObjeto> consultaCuadreOperacionesObjetoScript(BigDecimal idProceso, BigDecimal numeroOrden)
			throws ServiceException;

}
