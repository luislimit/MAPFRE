package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.HistoricoProc;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.InputConsutaHistoricoProceso;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;

/**
 * @author hcarreno
 */
public interface HistoricoService {

    /**
     * @param inputConsutaHistoricoProceso
     * @return HistoricoProcesoList
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<HistoricoProceso> consultarHistoricoObjeto(InputConsutaHistoricoProceso inputConsutaHistoricoProceso) throws ServiceException;

    OutputConsulta<Historico> consultarHistorico(String codigoProyecto, String tipoObjeto) throws ServiceException;

    OutputWarning bajaHistorico(String codigoProyecto, String nombreObjeto, String peticion, String codUsr) throws ServiceException;

    OutputWarning altaHistorico(String codigoProyecto, String nombreObjeto, String tipoObjeto, String historificada, String peticion, String codUsr) throws ServiceException;

    OutputConsulta<HistoricoProc> consultarHistoricoProcesado(BigDecimal idProceso) throws ServiceException;
}
