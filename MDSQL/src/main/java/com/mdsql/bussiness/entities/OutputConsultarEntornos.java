package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import com.mdval.exceptions.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OutputConsultarEntornos implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8077635735910629830L;
	
	private List<Entorno> entornos;
    
    private Integer result;
    private ServiceException serviceException;
}
