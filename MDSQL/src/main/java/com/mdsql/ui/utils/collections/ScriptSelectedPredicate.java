package com.mdsql.ui.utils.collections;

import org.apache.commons.collections.Predicate;

import com.mdsql.bussiness.entities.Script;

public class ScriptSelectedPredicate implements Predicate {

	@Override
	public boolean evaluate(Object object) {
		Script script = (Script) object;
		return script.getSelected();
	}
}
