package com.mdsql.utils.collections;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.ArrayUtils;

import com.mdsql.bussiness.entities.Script;

/**
 * @author federico
 *
 */
public class ScriptPredicate implements Predicate {
	
	private final String[] types;
        private final boolean exclude;

        /**
         * 
         * @param types lista de tipos
         * @param exclude Si es TRUE se asumen todo los tipos que NO están en la lista
         *                Si es FALSE se asumen los tipos incluidos en la lista
         */
	public ScriptPredicate(String[] types, boolean exclude) {
		super();
		this.types = types;
                this.exclude = exclude;
	}



	@Override
	public boolean evaluate(Object object) {
		Script input = (Script) object;
		if (exclude){
                    return !ArrayUtils.contains( types, input.getTipoScript() );
                }
		return ArrayUtils.contains( types, input.getTipoScript() );
	}

}
