package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;

/**
 * @author hcarreno
 */
public interface AvisoService {

    OutputConsulta<Aviso> consultaAvisosModelo(String codigoProyecto) throws ServiceException;

    OutputConsulta<NivelImportancia> consultaNivelesImportancia() throws ServiceException;

    OutputWarning altaAviso(String codigoProyecto, String desAviso, String txtAviso,
            String codNivelAviso, String codPeticion, String codUsr) throws ServiceException;

    OutputWarning modificarAviso(String codigoProyecto, BigDecimal codigoAviso, String desAviso,
            String txtAviso, String codNivelAviso, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException;

    OutputConsulta<Aviso> consultaAvisosObjeto(String codigoProyecto, String codPeticion, String codNivelAviso,
            String nomObjeto, String mcaHabilitado) throws ServiceException;

    OutputWarning mntoAvisos(String codigoProyecto, String codPeticion, String codNivelAviso, String nomObjeto,
            String mcaHabilitado, BigDecimal codAviso, String titulo, String descripcion, String codUsr) throws ServiceException;
}
