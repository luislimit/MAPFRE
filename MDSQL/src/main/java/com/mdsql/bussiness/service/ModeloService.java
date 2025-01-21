package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Variable;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface ModeloService {

    /**
     * @param codigoProyecto
     * @param nombreModelo
     * @param codigoSubProyecto
     * @return
     * @throws ServiceException
     */
    OutputConsulta<Modelo> consultaModelos(String codigoProyecto, String nombreModelo, String codigoSubProyecto)
            throws ServiceException;

    OutputConsulta<Variable> consultaVariables(Modelo modelo) throws ServiceException;

    OutputWarning actualizarVariableModelo(String codigoProyecto, String codigoVariable, String entorno, String bbdd,
            String tipoVariable, String valorVariable, String valorSustituir, String codigoPeticion, String mcaInterno,
            String mcaHabilitado, String mcaExcepcion, String comentario, String codUsr) throws ServiceException;
}
