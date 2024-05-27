package com.mdsql.ui.listener.combo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.ui.utils.ListenerSupport;

public abstract class BBDDItemListener extends ListenerSupport implements ItemListener {
	
	public BBDDItemListener() {
		super();
	}
	
	@Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          
          if (!Objects.isNull(item)) {
        	  BBDD bbdd = (BBDD) item;
        	  processItem(bbdd);
          }
       }
    }  
	
	public abstract void processItem(BBDD item);
}