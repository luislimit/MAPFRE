package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.InputEliminaLog;
import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface LogService {

    /**
     * @param idProceso
     * @param numeroOrden
     * @return LogEjecucionList
     */
    List<LogEjecucion> logEjecucion(BigDecimal idProceso, BigDecimal numeroOrden) throws ServiceException;

    /**
     * @param inputEliminaLog
     */
    ServiceException eliminaLog(InputEliminaLog inputEliminaLog) throws ServiceException;

}
