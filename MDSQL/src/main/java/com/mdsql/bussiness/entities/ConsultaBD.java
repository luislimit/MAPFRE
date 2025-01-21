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
public class ConsultaBD implements Serializable {

    private static final long serialVersionUID = 7745175229547275315L;
    private String nombreBBDD;
    private String nombreEsquema;
    private String password;
    private String nombreScript;
    private List<TextoLinea> script; 
    private String nombreScriptLog;
}
