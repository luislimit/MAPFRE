/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.bussiness.service;

/**
 *
 * @author Luis-Enrique.Varona
 */
import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputDatosReentrante;
import com.mdsql.bussiness.entities.OutputProcesaReentrante;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputValor;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.ReentranteComentarioColumna;
import com.mdsql.bussiness.entities.ReentranteInfo;
import com.mdsql.bussiness.entities.ReentranteParametro;
import com.mdsql.bussiness.entities.ScriptInfo;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author LVARONA
 */
public interface ReentranteService {

    OutputConsulta<CodigoDescripcion> consultaTipoReentrante() throws ServiceException;

    OutputConsulta<ReentranteParametro> consultaDatosReentrante(BigDecimal idProceso) throws ServiceException;

    OutputConsulta<ReentranteParametro> consultaParametroReentrante(String tipoReentrante) throws ServiceException;

    OutputDatosReentrante datosReentrante() throws ServiceException;

    OutputConsulta<ReentranteComentarioColumna> procesaComentarioReentrantes(
            String nombreTabla,
            String comentarioES,
            String comentarioEN,
            List<TextoLinea> fichero
    ) throws ServiceException;

    // Procedimiento que valida los parámetros de entrada de un script reentrante (TRN)
    OutputWarning validaParamOperacion(ReentranteInfo reentranteInfo) throws ServiceException;

    // Procedimiento que genera el script de comentarios
    OutputValor<ScriptInfo> generaCmtReentrantes(
            String nombreTabla,
            String comentarioEs,
            String comentarioEn,
            List<ReentranteComentarioColumna> listaCmtCol) throws ServiceException;

    //Procedimiento que procesa un script reentrante (TRN) 
    OutputProcesaReentrante procesaScriptReentrante(
            String codProyecto,
            String codSubProyecto,
            String codPeticion,
            String codDemanda,
            String codUsr,
            String codUsrPeticion,
            String nomBBDD,
            String nomEsquema,
            ReentranteInfo reentranteInfo,
            String descripcion
    ) throws ServiceException;

    //Registra las operaciones que figuran en el fichero de log tras la ejecución de un script reentrante
    OutputRegistraEjecucion registraEjecucionReentrante(
            BigDecimal idProceso,
            BigDecimal numOrden,
            String nomBBDD,
            String nomEsquema,
            String codUsr,
            List<TextoLinea> logScript
    ) throws ServiceException;
}
