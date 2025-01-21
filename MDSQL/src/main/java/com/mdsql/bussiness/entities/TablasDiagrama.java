package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LVARONA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TablasDiagrama implements Serializable {

    private static final long serialVersionUID = -4825049703857074013L;

    String codSubjectArea;
    String desSubjectArea;
    String codDiagrama;
    String desDiagrama;
    String nomTabla;
    String comentario;
    String mcaInh;
    String codPeticion;
    String codUsr;
    Date fecActu;
}
