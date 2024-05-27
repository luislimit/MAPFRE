package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Output implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8077635735910629830L;
	
	@Getter
	@Setter
	protected List<Object[]> warnings;
}
