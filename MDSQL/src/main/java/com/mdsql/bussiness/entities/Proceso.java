package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Proceso implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2012801467618032649L;
	
	private BigDecimal idProceso;
	private Modelo modelo;
	private String codProyecto;
	private String codSubproyecto;
	private SubProyecto subproyecto;
    private String codigoPeticion;
    private String codigoUsrPeticion;
    private BBDD bbdd;
    private Date fechaInicio;
    private String codigoUsr;
    private BigDecimal codigoEstadoProceso;
    private String descripcionEstadoProceso;
    private String mcaInicial;
    private String txtDescripcion;
    private String txtObservacionEntrega;
    private String codigoDemanda;
    private String mcaErrores;
    private String rutaTrabajo;
    private String rutaScript;
    
    private List<BBDD> bbdds;
    private List<Script> scripts;
    private List<Type> types;
    
    // Para el procesado de Types, el script lanza va aparte
    private Script scriptLanza;
    private String ficheroLog;
}
