package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EjecucionService {

    OutputRegistraEjecucion registraEjecucion(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> lineas)
                throws ServiceException;

    OutputRegistraEjecucionType registraEjecucionType(BigDecimal idProceso, String codigoUsuario, List<TextoLinea> logScript)
                throws ServiceException;

    OutputRegistraEjecucion registraEjecucionParche(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> logScript, String indRepara)
                throws ServiceException;

}
