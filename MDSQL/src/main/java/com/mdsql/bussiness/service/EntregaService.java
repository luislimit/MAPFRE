package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputConsulta;
import java.math.BigDecimal;

import com.mdsql.bussiness.entities.OutputConsultaEntrega;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputConfirmaCierre;
import com.mdsql.bussiness.entities.ProcesadoPeticion;
import com.mdsql.bussiness.entities.FicheroAccion;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EntregaService {


    /**
     * @param codigoProyecto
     * @param idProceso
     * @return OutputConsultaEntrega
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsultaEntrega consultaRutaEntrega(String codigoProyecto, BigDecimal idProceso)  throws ServiceException;

    /**
     * @param idProceso
     * @param codigoUsuario
     * @param comentario
     * @param versionErwin
     * @return descripcionEstadoProceso
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputValor<String> entregarPeticion(BigDecimal idProceso, String codigoUsuario, String comentario, String versionErwin) throws ServiceException;

    /**
     * 
     * @param codPeticion
     * @return
     * @throws ServiceException 
     */
    OutputConsulta<ProcesadoPeticion> consultaProcesadosPeticion(String codPeticion) throws ServiceException;
    
    /**
     * 
     * @param codPeticion
     * @return
     * @throws ServiceException 
     */
    OutputFicherosPeticion  prepararCierre(String codPeticion) throws ServiceException;
    
    OutputConfirmaCierre confirmaCierre(String codPeticion, String descripcion, String versionado, String Erwin) throws ServiceException;
   
}
