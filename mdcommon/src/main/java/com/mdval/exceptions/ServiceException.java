package com.mdval.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;

/**
 * @author federico
 *
 */
@StandardException
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8287632943709257608L;

	@Getter
	@Setter
	private transient List<Object[]> errors;
	
	@Getter
	@Setter
	private Integer type;
	
	/**
	 * 
	 */
	public ServiceException(Integer type) {
		super();
		this.type = type;
	}
}
