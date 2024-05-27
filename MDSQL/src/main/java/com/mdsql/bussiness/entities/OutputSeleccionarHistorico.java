package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hcarreno
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OutputSeleccionarHistorico extends Output implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6646020965239635755L;
	
	private List<SeleccionHistorico> seleccion;
}
