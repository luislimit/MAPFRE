package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputConsultaEntrega extends OutputWarning implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2931355001183634051L;
    private String txtRutaEntrega;
    private String nombreFicheroVigente;
    private String nombreFicheroHistorico;
    private String nombreFicheroType;
}
