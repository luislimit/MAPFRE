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
 * Type T_R_DATOS_REENTRANTE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReentranteDato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private BigDecimal numeroParam;
    private String nombreParam;
    private String valorParam;
}
