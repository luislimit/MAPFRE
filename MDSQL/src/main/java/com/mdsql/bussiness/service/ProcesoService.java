package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.OutputConsultaProcesado;
import com.mdsql.bussiness.entities.OutputSeleccionarHistorico;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface ProcesoService {

	/**
	 * @param inputSeleccionarProcesados
	 * @return
	 * @throws ServiceException
	 */
	List<Proceso> seleccionarProcesados(InputSeleccionarProcesados inputSeleccionarProcesados) throws ServiceException;

	/**
	 * @param codProyecto
	 * @param lineas
	 * @return
	 * @throws ServiceException
	 */
	OutputSeleccionarHistorico seleccionarHistorico(String codProyecto, List<TextoLinea> lineas) throws ServiceException;

	
	/**
	 * @param listaObjetos
	 * @param codigoProyecto
	 * @param codigoPeticion
	 * @param codigoUsuario
	 * @return
	 */
	ServiceException altaHistorico(List<SeleccionHistorico> listaObjetos, String codigoProyecto, String codigoPeticion, String codigoUsuario);

    /**
     * @param idProceso
     *
     * @return OutputConsultaProcesado
     */
    OutputConsultaProcesado consultaProcesado(BigDecimal idProceso) throws ServiceException;

    /**
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @throws ServiceException
     */
    void rechazarProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException;
}
