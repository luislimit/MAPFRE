package com.mdsql.bussiness.entities;

//import com.mdval.exceptions.ServiceException;
//import com.mdval.exceptions.ServiceException;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author LVARONA
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputProcesaReentrante extends OutputProcesaScript implements Serializable {

    private static final long serialVersionUID = -8077635735910629830L;

    private List<Lanza> listaLanza;
}




