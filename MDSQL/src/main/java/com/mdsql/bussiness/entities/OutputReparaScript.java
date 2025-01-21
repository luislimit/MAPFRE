package com.mdsql.bussiness.entities;

import com.mdval.exceptions.ServiceException;
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
public class OutputReparaScript implements Serializable {

    private static final long serialVersionUID = 1276065065767986360L;
    private String nombreScriptRepara;
    private List<TextoLinea> scriptRepara;
    private String nombreScriptLanza;
    private List<TextoLinea> scriptLanza;
    private String nombreLogRepara;
    private List<ScriptOld> listaScriptOld;
    private List<Script> listaScript;

    private Integer result;
    private ServiceException warnings;    
}
