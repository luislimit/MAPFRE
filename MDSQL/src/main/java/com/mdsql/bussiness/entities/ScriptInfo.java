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
public class ScriptInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<TextoLinea> lineas;
    private String nombre;
    private String ruta;
}
