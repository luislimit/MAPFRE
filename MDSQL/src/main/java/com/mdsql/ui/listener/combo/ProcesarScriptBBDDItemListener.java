package com.mdsql.ui.listener.combo;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.ui.PantallaProcesarScript;

public class ProcesarScriptBBDDItemListener extends BBDDItemListener {
	
	private PantallaProcesarScript pantallaProcesarScript;
	
	public ProcesarScriptBBDDItemListener(PantallaProcesarScript pantallaProcesarScript) {
		super();
		this.pantallaProcesarScript = pantallaProcesarScript;
	}

	@Override
	public void processItem(BBDD item) {
		pantallaProcesarScript.getTxtEsquema().setText(item.getNombreEsquema());
		pantallaProcesarScript.getTxtBBDDHistorico().setText(item.getNombreBBDDHis());
		pantallaProcesarScript.getTxtEsquemaHistorico().setText(item.getNombreEsquemaHis());
	}

}
