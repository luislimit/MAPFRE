package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.OutputConsultaPermisosPersonalizados;
import com.mdsql.bussiness.entities.OutputProcesaPermisoPersonalizado;
import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.Script;
import com.mdval.exceptions.ServiceException;
import java.util.List;
/**
 * @author LVARONA
 */
public interface PermisosPersonalizadosService {

    OutputConsultaPermisosPersonalizados consulta(
                String p_cod_proyecto,
                String p_nom_objeto,
                String p_nom_columna,
                String p_txt_per_syn, // Permiso/Sinonimo
                String p_des_entorno,
                String p_cod_owner_syn, // Propietario sinonimo
                String p_val_regla_syn, // Funcion nombre
                String p_val_grant, // Permiso
                String p_tip_objeto,
                String p_cod_usr_grant, // Receptor permisos
                String p_cod_peticion,
                String p_mca_grant_option,
                String p_mca_pdc,
                String p_mca_habilitado,
                String p_cod_usr, // Usuario de modificación
                String p_fec_desde,
                String p_fec_hasta    
    ) throws ServiceException;
    
    OutputConsultaPermisosPersonalizados consultaPermisoSinonimo(
            String p_cod_proyecto, 
            String p_cod_sub_proy, 
            String p_nom_objeto, 
            String p_tip_objeto,  
            String p_cod_peticion
    ) throws ServiceException;

    
    OutputProcesaPermisoPersonalizado procesa(
            String p_cod_proyecto,
            String p_cod_sub_proy,
            String p_tip_objeto, // Viene de la pantalla anterior
            String p_nom_objeto, // Viene de la pantalla anterior
            String p_cod_peticion_ant, // Viene de la pantalla anterior
            String p_cod_peticion,
            String p_cod_demanda,
            String p_nom_BBDD,
            String p_cod_usr_peticion,
            String p_txt_comentario,
            String p_mca_generales,
            String p_mca_resto_per,
            String p_ruta_salida,
            String p_cod_usr) throws ServiceException;
    
    
    List<OutputRegistraEjecucion> executeScripts(BBDD bbdd, List<Script> scripts, String ruta) throws ServiceException;    
}


