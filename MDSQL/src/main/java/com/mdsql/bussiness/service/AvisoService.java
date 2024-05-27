package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface AvisoService {


    List<Aviso> consultaAvisosModelo(String codigoProyecto) throws ServiceException;

    List<NivelImportancia> consultaNivelesImportancia() throws ServiceException;

    void altaAviso(String codigoProyecto, String descripcionAviso, String txtAviso, String codNivelAviso, String codPeticion, String codUsr) throws ServiceException;

    void modificarAviso(String codigoProyecto, BigDecimal codigoAviso, String descripcionAviso, String txtAviso, String codNivelAviso, String mcaHabilitado, String codPeticion, String codUsr) throws ServiceException;
}
