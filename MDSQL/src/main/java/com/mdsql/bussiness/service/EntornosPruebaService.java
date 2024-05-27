package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.InputMntoEntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsultarEntornosPrueba;
import com.mdsql.bussiness.entities.OutputMntoEntornoPrueba;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface EntornosPruebaService {


	OutputConsultarEntornosPrueba consultarEntornos() throws ServiceException;

    OutputMntoEntornoPrueba guardarEntorno(InputMntoEntornoPrueba inputMntoEntornoPrueba, String codUsr) throws ServiceException;
}
