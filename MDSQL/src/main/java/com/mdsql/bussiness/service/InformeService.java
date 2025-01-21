package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.Informe;
import com.mdsql.bussiness.entities.InformeCambioTRN;
import com.mdsql.bussiness.entities.InformeCambios;
import java.math.BigDecimal;
import com.mdsql.bussiness.entities.InformeValidacion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputParamInformeTRN;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface InformeService {

    /**
     * @param codigoValidacion
     * @return InformeValidacion
     * @throws com.mdval.exceptions.ServiceException
     */
    InformeValidacion generarInformeValidacion(BigDecimal codigoValidacion) throws ServiceException;

    /**
     * @param codigoProyecto
     * @param fechaDesde
     * @param fechaHasta
     * @return InformeCambiosList
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsulta<InformeCambios> informeCambios(String codigoProyecto, String fechaDesde, String fechaHasta) throws ServiceException;

    /**
     * Genera el informe de cambios de TRN
     *
     * @return
     * @throws ServiceException
     */
    OutputConsulta<InformeCambioTRN> generaInformeTRN() throws ServiceException;

    OutputParamInformeTRN paramInformeTRN() throws ServiceException;

    OutputConsulta<CodigoDescripcion> consultaTipoInforme() throws ServiceException;

    OutputConsulta<Informe> consultaInforme(
            String codigoProyecto,
            String tipoInforme,
            String nombreObjeto,
            String fechaDesde,
            String fechaHasta,
            String mcaPermisos
    ) throws ServiceException;
}
