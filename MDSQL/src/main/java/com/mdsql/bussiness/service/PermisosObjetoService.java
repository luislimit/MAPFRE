package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.OutputConsultaPermisosSinonimos;
import com.mdsql.bussiness.entities.PermisoObjeto;
import com.mdsql.bussiness.entities.SinonimoObjeto;
import com.mdval.exceptions.ServiceException;

/**
 * @author hcarreno
 */
public interface PermisosObjetoService {

    OutputConsultaPermisosSinonimos consultaPermisosSinonimos(
                        String p_cod_proyecto,
                        String p_nom_objeto,
                        String p_tip_objeto,
                        String p_txt_per_syn, // Permiso/Sinonimo
                        String p_des_entorno,
                        String p_cod_owner_syn, // Propietario sinonimo
                        String p_val_regla_syn, // Funcion nombre
                        String p_val_grant, // Permiso
                        String p_cod_usr_grant, // Receptor permisos 
                        String p_mca_PDC, // Incluir en PDC
                        String p_mca_grant_option,
                        String p_mca_habilitado
    ) throws ServiceException;
    
    ServiceException guardarPermisoObjeto(
                        PermisoObjeto permisoObjeto,
                        String p_mca_alta
    ) throws ServiceException;

    ServiceException guardarSinonimoObjeto(
                        SinonimoObjeto sinonimoObjeto,
                        String p_mca_alta
    ) throws ServiceException;
}
