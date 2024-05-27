package com.mdsql.bussiness.service;

import java.util.List;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Propietario;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface PropietarioService {

    List<Propietario> consultarPropietariosSinonimo(Modelo modelo) throws ServiceException;

    List<Grant> consultarReceptoresModelo(Modelo modelo) throws ServiceException;
}
