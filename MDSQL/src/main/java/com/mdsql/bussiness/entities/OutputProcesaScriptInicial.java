package com.mdsql.bussiness.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author LVARONA
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputProcesaScriptInicial extends OutputProcesaScript{

    private static final long serialVersionUID = 3938170201951884498L;
   
    private String nombreScriptLog;
    private String nombreScriptLanza;
    private List<TextoLinea> scriptLanza;
}
