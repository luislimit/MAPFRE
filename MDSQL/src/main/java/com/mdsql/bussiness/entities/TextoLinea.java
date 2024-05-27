package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TextoLinea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8337839762045714934L;
	private String valor;

}
