package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface BBDDService {

    OutputConsulta<BBDD> consultaBBDDModelo(String codigoProyecto, String codSubproyecto) throws ServiceException;

    String consultaPasswordBBDD(String nombreBBDD, String nombreEsquema, String txtClaveEncriptada) throws ServiceException;

    String consultaPasswordBBDD(String nombreBBDD, String nombreEsquema) throws ServiceException;

    String getClaveEncriptada();
}
