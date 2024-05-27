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
public class OutputInformeCambios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8384575354150091276L;
	
	private Integer result;
    private List<InformeCambios> listaCambios;
    private ServiceException serviceException;

}
