package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdval.exceptions.ServiceException;
import java.util.List;

/**
 * @author hcarreno
 */
public interface ConsultaService {

    /**
     * @return TipoObjetoList
     * @throws com.mdval.exceptions.ServiceException
     */
    List<String> consultaTiposObjeto() throws ServiceException;

    /**
     * @return EstadoList
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<Estado> consultaEstadosProcesado() throws ServiceException;

    /**
     * @return EstadoList
     * @throws com.mdval.exceptions.ServiceException
     */
    List<Estado> consultaEstadosScript() throws ServiceException;

    /**
     * @return OperacionList
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<CodigoDescripcion> consultaOperaciones() throws ServiceException;

}
