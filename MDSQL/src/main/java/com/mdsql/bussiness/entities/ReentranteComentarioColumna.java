package com.mdsql.bussiness.entities;

import java.io.Serializable;

import com.mdval.exceptions.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReentranteComentarioColumna implements Serializable {

    /**
     * Type t_r_cmt_columnas
     */
    private static final long serialVersionUID = -8384575354150091279L;
    
    private String nombreColumna;
    private String tipo; // Valores P y G (Personalizado/Genérico)
    private String Rdo; // Valores OK, NOK, PER, NEW
    private String idioma; // Codigo de idioma
    private String comentario; // Comentario plantilla
    private String comentarioGenerico; // Comentario genérico
}



