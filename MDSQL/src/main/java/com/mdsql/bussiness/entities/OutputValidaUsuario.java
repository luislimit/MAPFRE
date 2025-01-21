package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author federico
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputValidaUsuario extends OutputWarning implements Serializable {

    private static final long serialVersionUID = 3938170200951884495L;

    private List<Estado> estados;
    private String tieneEnCurso; // Valores posibles S/N
    private Integer delayMensaje;
}
