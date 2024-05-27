package com.mdsql.bussiness.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hcarreno
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputProcesaType extends OutputProcesa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6168650459690301885L;
	
	private String pNombreScriptLanza;
	private String pTxtScriptLanza;
	private String pNombreScriptLog;

	private List<TextoLinea> txtScriptLanza;
    private List<Type> listaType;
}
