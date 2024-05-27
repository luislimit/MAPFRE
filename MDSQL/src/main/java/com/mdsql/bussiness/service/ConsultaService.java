package com.mdsql.bussiness.service;

import java.util.List;

import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.Operacion;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface ConsultaService {

    /**
     * @return TipoObjetoList
     */
    List<String> consultaTiposObjeto() throws ServiceException;

    /**
     * @return EstadoList
     */
    List<Estado> consultaEstadosProcesado() throws ServiceException;

    /**
     * @return EstadoList
     */
    List<Estado> consultaEstadosScript() throws ServiceException;

    /**
     * @return OperacionList
     */
    List<Operacion> consultaOperaciones() throws ServiceException;


}
