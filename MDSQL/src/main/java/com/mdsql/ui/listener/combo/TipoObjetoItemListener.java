package com.mdsql.ui.listener.combo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.mdsql.ui.utils.ListenerSupport;

public abstract class TipoObjetoItemListener extends ListenerSupport implements ItemListener {

	public TipoObjetoItemListener() {
		super();
	}
	
	@Override
    public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();
			
			String tipoObjeto = (String) item;
			processItem(tipoObjeto);
		}
		
		if (event.getStateChange() == ItemEvent.DESELECTED) {
			processDeselected();
		}
    }  
	
	public abstract void processItem(String item);
	
	public abstract void processDeselected();
}