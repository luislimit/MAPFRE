package com.mdsql.bussiness.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor // Genera un constructor con los campos marcados con @NonNull
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
public class BBDD implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7745175229547275319L;
    
    @EqualsAndHashCode.Include
    @NonNull    
    private String nombreBBDD;
    
    private String nombreEsquema;
    private String nombreBBDDHis;
    private String nombreEsquemaHis;
    private String mcaDefecto;
    private String password;
    
    @Override
    public String toString(){
        return nombreBBDD;
    }
}
