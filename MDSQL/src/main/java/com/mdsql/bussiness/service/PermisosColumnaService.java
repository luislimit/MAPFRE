package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputConsultaPermisosColumna;
import com.mdsql.bussiness.entities.PermisoColumna;
import com.mdval.exceptions.ServiceException;

/**
 * @author LVARONA
 */
public interface PermisosColumnaService {

    OutputConsultaPermisosColumna consultaPermisos(
                        String p_cod_proyecto,
                        String p_nom_objeto,
                        String p_nom_columna,
                        String p_des_entorno,
                        String p_val_grant, // Permiso
                        String p_cod_usr_grant, // Receptor permisos 
                        String p_mca_grant_option,
                        String p_mca_pdc, // Incluir en PDC
                        String p_mca_habilitado
    ) throws ServiceException;
    
    OutputConsultaPermisosColumna guardarPermiso(
                        PermisoColumna permisoColumna,
                        String p_mca_alta
    ) throws ServiceException;
}


