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
public class OutputProcesaScript extends OutputProcesa {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1098930944566895366L;
	private List<Script> listaScripts;

}
