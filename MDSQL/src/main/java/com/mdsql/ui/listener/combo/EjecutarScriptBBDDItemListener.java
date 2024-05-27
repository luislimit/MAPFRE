package com.mdsql.ui.listener.combo;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.ui.PantallaEjecutarScripts;

public class EjecutarScriptBBDDItemListener extends BBDDItemListener {
	
	private PantallaEjecutarScripts pantallaEjecutarScripts;
	
	public EjecutarScriptBBDDItemListener(PantallaEjecutarScripts pantallaEjecutarScripts) {
		super();
		this.pantallaEjecutarScripts = pantallaEjecutarScripts;
	}

	@Override
	public void processItem(BBDD item) {
		pantallaEjecutarScripts.getTxtEsquema().setText(item.getNombreEsquema());
		pantallaEjecutarScripts.getTxtBBDDHistorico().setText(item.getNombreBBDDHis());
		pantallaEjecutarScripts.getTxtEsquemaHistorico().setText(item.getNombreEsquemaHis());
	}

}
