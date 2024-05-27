package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.InputProcesaType;
import com.mdsql.bussiness.entities.OutputProcesaType;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface TypeService {

    OutputProcesaType procesarType(InputProcesaType inputProcesaType) throws ServiceException;

}
