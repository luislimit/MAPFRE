package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import com.mdval.exceptions.ServiceException;

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
public class InputProcesaType implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3412126707355550178L;
	private List<TextoLinea> lineasScript;
    private String pCodigoProyecto;
    private String pCodigoSubProyecto;
    private String pCodigoPeticion;
    private String pCodigoDemanda;
    private String pCodigoUsr;
    private String pCodigoUsrPeticion;
    private String pNombreBBDD;
    private String pNombreEsquema;
    private String pNombreBBDDHIS;
	private String pNombreEsquemaHis;
    private String pNombreFichaEntrada;
    private String pTxtRutaEntrada;
    private String pTxtDescripcion;

    // Para los warnings
    private ServiceException serviceException;

}
