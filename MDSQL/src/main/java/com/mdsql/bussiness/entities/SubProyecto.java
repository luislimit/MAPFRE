package com.mdsql.bussiness.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor // Genera un constructor con los campos marcados con @NonNull
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubProyecto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3697494311301739263L;

    @EqualsAndHashCode.Include
    @NonNull
    private String codigoSubProyecto;

    private String descripcionSubProyecto;

    @Override
    public String toString() {
        return codigoSubProyecto;
    }
}
