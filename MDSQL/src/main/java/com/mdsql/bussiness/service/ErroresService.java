package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.ErrorScript;
import com.mdsql.bussiness.entities.OutputErroresScript;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface ErroresService {

	OutputErroresScript consultaErroresScript(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException;

	List<ErrorScript> consultaErroresType(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException;

}
