package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Luis-Enrique.Varona
 * Type t_r_param_reentrante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReentranteParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String tipoParam;
    private BigDecimal numeroParam;
    private String nombreParam;
    private String textoParam;
    
    //Este campo no está en t_r_param_reentrante, se añade para facilitar 
    //la gestion de la pantalla de seleccion de Reentrante
    private String valorParam;
}
