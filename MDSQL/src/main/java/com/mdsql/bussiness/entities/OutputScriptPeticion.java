package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 *
 * @author LVARONA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputScriptPeticion extends OutputWarning implements Serializable {

    private static final long serialVersionUID = 3938170200951884495L;

    private String ruta; 
    private List<ScriptPeticion> lista;
}