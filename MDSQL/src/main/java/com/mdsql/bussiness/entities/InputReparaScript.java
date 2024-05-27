package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hcarreno
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InputReparaScript implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1601631126723337861L;
	private BigDecimal idProceso;
    private BigDecimal numeroOrden;
    private String codigoUsuario;
    private String mcaReprocesa;
    private String mcaMismoScript;
    private String nombreScriptNew;
    private String txtRutaNew;
    private List<TextoLinea> scriptNew;
    private String txtComentario;
    private String nombreScriptParche;
    private String txtRutaParche;
    private List<TextoLinea> scriptParche;
    
    private String nombreBBDD;
    private String nombreEsquema;
    private String pMcaHis;
    private String nombreBBDDHis;
    private String nombreEsquemaHis;
    
    private List<SeleccionHistorico> listaObjetoHis;
}
