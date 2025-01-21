package com.mdsql.bussiness.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author LVARONA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor // Genera un constructor con los campos marcados con @NonNull
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CodigoDescripcion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4166583635098525436L;
    @EqualsAndHashCode.Include
    @NonNull
    private String codigo;

    private String descripcion;

    @Override
    public String toString() {
        return codigo;
    }

}
