package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.OutputAltaHistorico;
import com.mdsql.bussiness.entities.OutputBajaHistorico;
import com.mdsql.bussiness.entities.OutputConsultaHistorico;
import com.mdsql.bussiness.entities.OutputConsultaHistoricoProceso;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface HistoricoService {

    /**
     * @param inputConsutaHistoricoProceso
     * @return HistoricoProcesoList
     */
	OutputConsultaHistoricoProceso consultarHistoricoProceso(InputConsutaHistoricoProceso inputConsutaHistoricoProceso) throws ServiceException;

    OutputConsultaHistorico consultarHistorico(String codigoProyecto, String tipoObjeto) throws ServiceException;

    OutputBajaHistorico bajaHistorico(String codigoProyecto, String nombreObjeto, String peticion, String codUsr) throws ServiceException;

    OutputAltaHistorico altaHistorico(String codigoProyecto, String nombreObjeto, String tipoObjeto, String historificada, String peticion, String codUsr) throws ServiceException;
}
