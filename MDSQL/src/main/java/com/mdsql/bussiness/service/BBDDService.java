package com.mdsql.bussiness.service;

import java.util.List;

import com.mdsql.bussiness.entities.BBDD;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface BBDDService {

    List<BBDD> consultaBBDDModelo(String codigoProyecto, String codSubproyecto) throws ServiceException;

    String consultaPasswordBBDD(String nombreBBDD, String nombreEsquema, String txtClaveEncriptada);

}
