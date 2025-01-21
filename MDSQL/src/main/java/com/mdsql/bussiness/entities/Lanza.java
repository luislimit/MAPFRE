package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Luis-Enrique.Varona
 * Type t_r_lanza
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Lanza implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal numOrden; // Número de orden del script al que pertenece
    private String nomScript; // Nombre del script lanzador
    private List<TextoLinea> lineasScript; // Contenido del script lanzador
    private String nomFicheroLog; // Nombre del fichero de log
    private String nomEsquema; // Esquema donde se ejecuta
    private String nomBBDD; // BBDD donde se ejecuta
    private String password; // Contraseña
    private List<TextoLinea> settings; // Setting adicionales a ejecutar en la consola antes de abrir sqlplus    
}
