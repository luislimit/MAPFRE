package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.Date;

import com.mdsql.bussiness.entities.InformeValidacion;
import com.mdsql.bussiness.entities.OutputInformeCambios;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface InformeService {

    /**
     * @param codigoValidacion
     * @return InformeValidacion
     */
    InformeValidacion generarInformeValidacion(BigDecimal codigoValidacion);

    /**
     * @param codigoProyecto
     * @param fechaDesde
     * @param fechaHasta
     * @return InformeCambiosList
     */
    OutputInformeCambios informeCambios(String codigoProyecto, Date fechaDesde, Date fechaHasta) throws ServiceException;

}
