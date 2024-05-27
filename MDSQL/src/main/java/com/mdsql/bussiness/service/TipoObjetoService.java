package com.mdsql.bussiness.service;

import java.util.List;

import com.mdsql.bussiness.entities.OutputConsultaTiposObjeto;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface TipoObjetoService {

    OutputConsultaTiposObjeto consultarTiposObjeto() throws ServiceException;

    List<String> consultarTiposVariable() throws ServiceException;
}
