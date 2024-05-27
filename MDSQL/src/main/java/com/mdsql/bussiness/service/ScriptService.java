package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.entities.InputDescartarScript;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputReparaScript;
import com.mdsql.bussiness.entities.OutputDescartarScript;
import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionParche;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.OutputReparaScript;
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

    List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts) throws ServiceException;
    
    List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, OutputReparaScript outputReparaScript) throws ServiceException;
    
    OutputRegistraEjecucionType executeScript(BBDD bbdd, String nombreScript, List<TextoLinea> script, String nombreFicheroLog) throws ServiceException;

    OutputReparaScript repararScript(InputReparaScript inputReparaScript) throws ServiceException;

    OutputDescartarScript descartarScript(InputDescartarScript inputDescartarScript) throws ServiceException;

    List<DetObjeto> detalleObjetosScripts(BigDecimal idProceso, BigDecimal numeroOrden);

    OutputExcepcionScript excepcionScript(Proceso proceso, Script script, String txtMotivoExcepcion, String codUsr) throws ServiceException;

	OutputRegistraEjecucionParche executeScriptParche(BBDD bbdd, Script script) throws ServiceException;
	
	void ejecutarRepararScript(Script script, Boolean isReparacion, Boolean isSameScript, OutputReparaScript outputReparaScript);
	
	void executeLanzaFile(String nombreEsquema, String nombreBBDD, String password, String fileLocation)  throws ServiceException;
}
