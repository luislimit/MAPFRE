package com.mdsql.ui.listener.combo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import com.mdsql.ui.utils.ListenerSupport;

public abstract class PermisoSinonimoItemListener extends ListenerSupport implements ItemListener {

	public PermisoSinonimoItemListener() {
		super();
	}
	
	@Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          
          if (!Objects.isNull(item)) {
        	  String value = (String) item;
        	  processItem(value);
          }
       }
    }  
	
	public abstract void processItem(String item);
}