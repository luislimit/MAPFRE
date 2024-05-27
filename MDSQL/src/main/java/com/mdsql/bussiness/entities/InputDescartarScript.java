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
public class InputDescartarScript implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3399482633457595754L;
	private List<TextoLinea> script;
    private BigDecimal idProceso;
    private String codigoUsuario;
    private String nombreScript;
    private String tipoCambio;
    private String nombreScriptNew;
    private String txtRutaNew;
    private String txtComentario;
    private String nombreScriptParche;
    private String txtRutaParche;
    private List<TextoLinea> scriptParche;

}
