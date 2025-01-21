package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;

/**
 * @author hcarreno
 */
public interface CuadreService {

    /**
     * @param idProceso
     * @param numeroOrden
     *
     * @return
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<CuadreOperacion> consultaCuadreOperacionesScript(BigDecimal idProceso, BigDecimal numeroOrden)
            throws ServiceException;

    /**
     * @param idProceso
     * @param numeroOrden
     *
     * @return cuadreObjetoList
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<CuadreObjeto> consultaCuadreOperacionesObjetoScript(BigDecimal idProceso, BigDecimal numeroOrden)
            throws ServiceException;

}
