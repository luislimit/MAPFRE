package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputConsultaProcesado;
import com.mdsql.bussiness.entities.OutputFicherosPeticion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author hcarreno
 */
public interface ProcesoService {

    /**
     * @param inputSeleccionarProcesados
     * @return
     * @throws ServiceException
     */
    OutputConsulta<Proceso> seleccionarProcesados(InputSeleccionarProcesados inputSeleccionarProcesados) throws ServiceException;

    /**
     * @param codProyecto
     * @param lineas
     * @return
     * @throws ServiceException
     */
    OutputConsulta<SeleccionHistorico> seleccionarHistorico(String codProyecto, List<TextoLinea> lineas) throws ServiceException;

    /**
     * @param listaObjetos
     * @param codigoProyecto
     * @param codigoPeticion
     * @param codigoUsuario
     * @return
     */
    OutputWarning altaHistorico(List<SeleccionHistorico> listaObjetos, String codigoProyecto,
            String codigoPeticion, String codigoUsuario) throws ServiceException;

    /**
     * @param idProceso
     *
     * @return OutputConsultaProcesado
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputConsultaProcesado consultaProcesado(BigDecimal idProceso) throws ServiceException;

    /**
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @return
     * @throws ServiceException
     */
    OutputWarning rechazarProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException;

    /**
     * Marca un procesado como excluido
     *
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @return
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputValor<String> excluirProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException;

    /**
     * Marca un procesado como incidencia
     *
     * @param idProceso
     * @param txtComentario
     * @param codUsr
     * @return
     * @throws com.mdval.exceptions.ServiceException
     */
    OutputValor<String> incidenciaProcesado(BigDecimal idProceso, String txtComentario, String codUsr) throws ServiceException;

    /*
    OutputValor <Procesado> consultaTipoProceso(BigDecimal idProceso) throws ServiceException;
     */
    OutputFicherosPeticion consultaFicherosAccion(Integer codEstado, BigDecimal idProceso) throws ServiceException;

    OutputWarning ejecutarFicherosAccion(OutputFicherosPeticion output, boolean raiseException) throws ServiceException;
}
