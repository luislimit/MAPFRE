package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mdval.exceptions.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OutputConsultaProcesado implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8077635735910629830L;
	private String nombreModelo;
    private String codigoUsrPeticion;
    private String nombreBBDDHistorico;
    private String descripcionSubProyecto;
    private String nombreEsquema;
    private String nombreesquemaHistorico;
    private String codigoPeticion;
    private String nombreBBDD;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private String codigoUsuario;
    private Date fechaProceso;
    private String txtComentario;
    private String mcaInicial;
    private String txtRutaEntrada;
    private List<ScriptEjecutado> listaScriptsEjecutados;
    
    private Integer result;
    private ServiceException serviceException;
}
