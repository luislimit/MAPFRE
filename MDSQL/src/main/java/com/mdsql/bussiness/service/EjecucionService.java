package com.mdsql.bussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.mdsql.bussiness.entities.OutputRegistraEjecucion;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionParche;
import com.mdsql.bussiness.entities.OutputRegistraEjecucionType;
import com.mdsql.bussiness.entities.TextoLinea;

/**
 * @author hcarreno
 */
public interface EjecucionService {

    OutputRegistraEjecucion registraEjecucion(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> lineas);

    OutputRegistraEjecucionType registraEjecucionType(BigDecimal idProceso, String codigoUsuario, List<TextoLinea> logScript);

    OutputRegistraEjecucionParche registraEjecucionParche(BigDecimal idProceso, BigDecimal numeroOrden, String codigoUsuario, List<TextoLinea> logScript, String indRepara);

}
