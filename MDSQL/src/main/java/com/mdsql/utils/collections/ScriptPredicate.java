package com.mdsql.utils.collections;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.ArrayUtils;

import com.mdsql.bussiness.entities.Script;

/**
 * @author federico
 *
 */
public class ScriptPredicate implements Predicate {
	
	private String[] types;

	public ScriptPredicate(String[] types) {
		super();
		this.types = types;
	}



	@Override
	public boolean evaluate(Object object) {
		Script input = (Script) object;
		
		return ArrayUtils.contains( types, input.getTipoScript() );
	}

}
