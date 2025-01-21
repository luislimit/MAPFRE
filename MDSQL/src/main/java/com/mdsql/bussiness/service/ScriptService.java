package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.entities.InputDescartarScript;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputReparaScript;
import com.mdsql.bussiness.entities.Lanza;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputDescartarScript;
import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.OutputReparaScript;
import com.mdsql.bussiness.entities.OutputScriptPeticion;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface ScriptService {

    OutputProcesaScript procesarScript(InputProcesaScript inputProcesaScript) throws ServiceException;

    OutputExcepcionScript excepcionScript(BigDecimal idProceso, BigDecimal numeroOrden, String txtComentario, String codigoUsuario);

    OutputReparaScript repararScript(InputReparaScript inputReparaScript) throws ServiceException;

    OutputDescartarScript descartarScript(InputDescartarScript inputDescartarScript) throws ServiceException;

    OutputConsulta<DetObjeto> detalleObjetosScripts(BigDecimal idProceso, BigDecimal numeroOrden)  throws ServiceException;

    OutputExcepcionScript excepcionScript(Proceso proceso, Script script, String txtMotivoExcepcion, String codUsr) throws ServiceException;

    /* Ejecuciones de script */    
    List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts, String ruta) throws ServiceException;
    
    List<OutputRegistraEjecucion> executeScriptsReentrante(List<Script> scripts, List<Lanza> lanzas, String ruta) throws ServiceException;

    List<TextoLinea> executeScript(BBDD bbdd, Script script, String ruta) throws ServiceException;
    
    OutputRegistraEjecucionType executeScript(BBDD bbdd, String nombreScript, List<TextoLinea> script, String nombreFicheroLog) throws ServiceException;
    
    OutputRegistraEjecucion executeScriptParche(BBDD bbdd, Script script) throws ServiceException;

    List<OutputRegistraEjecucion> ejecutarRepararScript(Script script, Boolean isReparacion, Boolean isSameScript, OutputReparaScript outputReparaScript)throws ServiceException;
    
    void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation) throws ServiceException;

    void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation, String mensaje) throws ServiceException;
    
    void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation, List<TextoLinea> settings, String mensaje) throws ServiceException;
    
    OutputWarning consultaBBDDModelo(BigDecimal idProceso, List<TextoLinea> lineasLog, String codUsr) throws ServiceException;
    
    OutputScriptPeticion consultaScriptsPeticion (String codPeticion, BigDecimal idProceso, BigDecimal codEstado) throws ServiceException;
                                
    OutputWarning cambiaNombreScript(BigDecimal idProceso, BigDecimal numOrden, String nomScriptOld, String nomScriptNew, String comentario)throws ServiceException; 
}
