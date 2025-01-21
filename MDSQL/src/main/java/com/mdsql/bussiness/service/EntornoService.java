package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.Entorno;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EntornoService {

    OutputConsulta<Entorno> consultarEntornos(String nomBBDD, String nomEsquema, String claveEncriptacion, String mcaHabilitado) throws ServiceException;

    OutputWarning guardarEntorno(String nomBBDD, String nomEsquema, String claveEncriptacion, String password, String mcaHabilitado, String comentario, String codUsr) throws ServiceException;
}
