package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Modelo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1060226242769948540L;
	
	private String codigoProyecto;
	private String nombreModelo;
	private String nombreEsquema;
	private String nombreBbdd;
	private String nombreCarpetaAdj;
	private String codigoCapaUsrown;
	private String mcaVariables;
	private String mcaGrantAll;
	private String mcaGrantPublic;
	private String mcaInh;
	private String mcaHis;
	private String observaciones;
	private String entregaPDC;
	private List<SubProyecto> subproyectos;
}
