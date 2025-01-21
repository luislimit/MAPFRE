package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputValidaUsuario;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface UtilsService {

   
    /**
     *
     * @param codigoUsuario
     * @param numVersion
     * @return
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputValidaUsuario validaUsuario(String codigoUsuario, String numVersion) throws ServiceException;

}
