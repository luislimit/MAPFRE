package com.mdsql.bussiness.service;

import java.math.BigDecimal;

/**
 * @author hcarreno
 */
public interface UtilsService {

    /**
     * @param idProceso
     * @param txtComentario
     * @param codigoUsuario
     * @return
     */
    void rechazarProcesado(BigDecimal idProceso, String txtComentario, String codigoUsuario);

}
