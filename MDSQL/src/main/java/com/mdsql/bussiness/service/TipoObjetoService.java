package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputConsulta;

import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface TipoObjetoService {

    OutputConsulta<String> consultarTiposObjeto() throws ServiceException;

    OutputConsulta<String> consultarTiposVariable() throws ServiceException;
}
