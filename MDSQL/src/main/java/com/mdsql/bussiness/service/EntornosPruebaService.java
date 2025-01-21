package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputProcesa;
import com.mdsql.bussiness.entities.OutputProcesaScriptInicial;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdval.exceptions.ServiceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author hcarreno
 */
public interface EntornosPruebaService {

// p_con_entorno_pruebas : devuelve un listado con los entornos de prueba 
public OutputConsulta<EntornoPrueba> consultarEntornos() throws ServiceException;

//p_mnto_entorno_prueba : mantenimiento de entornos de prueba
public OutputWarning guardarEntorno(EntornoPrueba entornoPrueba, String codUsr)
            throws ServiceException;

// p_procesa_script_inicial: procesado de script inicial en un entorno de prueba
public OutputProcesaScriptInicial procesaScriptInicial(
                List<TextoLinea> p_script,
                String p_cod_proyecto,
                String p_cod_sub_proy,
                String p_cod_peticion,
                String p_cod_demanda,
                String p_cod_usr,
                String p_cod_usr_peticion,
                String p_nom_fich_entrada,
                String p_txt_ruta_entrada,
                String p_nom_entorno,
                String p_mca_drop)throws ServiceException;

// p_registra_script_inicial :registra las operaciones que figuran en el fichero de log generado por Oracle tras la ejecución de un script inicial
public OutputProcesa registraScriptInicial(BigDecimal idProceso, 
                              String codUsr, 
                              List<TextoLinea> logScript)throws ServiceException;
}