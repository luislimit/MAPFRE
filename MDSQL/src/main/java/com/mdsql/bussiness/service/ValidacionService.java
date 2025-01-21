package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ProgramacionModelo;
import com.mdsql.bussiness.entities.ValidacionProgramada;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author LVARONA
 */
public interface ValidacionService {

    OutputConsulta<ValidacionProgramada> validacionesProgramadas() throws ServiceException;

    OutputConsulta<CodigoDescripcion> consultaAcciones() throws ServiceException;

    OutputConsulta<ProgramacionModelo> consultaProgramacion(BigDecimal codValidacion) throws ServiceException;

    OutputWarning mntoProcProgramados(BigDecimal codValidacion, List<ProgramacionModelo> listaProgramacion) throws ServiceException;

}
