package com.mdsql.bussiness.entities;

import java.io.Serializable;
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
public class OutputDescartarScript implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4622111798571136344L;
	private List<Script> listaParches;
    private List<ScriptOld> listaScriptOld;
    private List<Script> listaScriptNew;
    private Integer codigoEstadoProceso;
    private String descripcionEstadoProceso;

}
