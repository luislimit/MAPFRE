package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputActualizarVariablesModelo;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.OutputVariablesModelo;
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
	OutputConsultaModelos consultaModelos(String codigoProyecto, String nombreModelo, String codigoSubProyecto)
			throws ServiceException;

	OutputVariablesModelo consultaVariables(Modelo modelo) throws ServiceException;

	OutputActualizarVariablesModelo actualizarVariableModelo(String codigoProyecto, String codigoVariable, String entorno, String bbdd,
			String tipoVariable, String valorVariable, String valorSustituir, String codigoPeticion, String mcaInterno,
			String mcaHabilitado, String comentario, String codUsr) throws ServiceException;
}
