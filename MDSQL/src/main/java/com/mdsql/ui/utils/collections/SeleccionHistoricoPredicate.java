package com.mdsql.ui.utils.collections;

import org.apache.commons.collections.Predicate;

import com.mdsql.bussiness.entities.SeleccionHistorico;

public class SeleccionHistoricoPredicate implements Predicate {

	@Override
	public boolean evaluate(Object object) {
		SeleccionHistorico seleccionHistorico = (SeleccionHistorico) object;
		return !seleccionHistorico.getHistorico() && seleccionHistorico.getConfigurado();
	}
}
