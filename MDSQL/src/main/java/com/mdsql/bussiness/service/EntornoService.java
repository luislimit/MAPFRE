package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputConsultarEntornos;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EntornoService {


	OutputConsultarEntornos consultarEntornos(String nomBBDD, String nomEsquema, String claveEncriptacion, String mcaHabilitado) throws ServiceException;

    void guardarEntorno(String nomBBDD, String nomEsquema, String claveEncriptacion, String password, String mcaHabilitado, String comentario, String codUsr) throws ServiceException;
}
