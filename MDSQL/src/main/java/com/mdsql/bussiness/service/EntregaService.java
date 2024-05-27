package com.mdsql.bussiness.service;

import java.math.BigDecimal;

import com.mdsql.bussiness.entities.OutputConsultaEntrega;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EntregaService {


    /**
     * @param codigoProyecto
     * @param idProceso
     * @return OutputConsultaEntrega
     */
    OutputConsultaEntrega consultaRutaEntrega(String codigoProyecto, BigDecimal idProceso);

    /**
     * @param idProceso
     * @param codigoUsuario
     * @param txtComentario
     * @return descripcionEstadoProceso
     */
    String entregarPeticion(BigDecimal idProceso, String codigoUsuario, String txtComentario) throws ServiceException;


}
