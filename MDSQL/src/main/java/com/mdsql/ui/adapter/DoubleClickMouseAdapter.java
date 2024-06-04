package com.mdsql.ui.adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author LVARONA
 */
public class DoubleClickMouseAdapter extends MouseAdapter{
    DoubleClickable listener;
    

    public void setListener(DoubleClickable listener) {
        this.listener = listener;
    }
 
    
   @Override
    public void mouseClicked(MouseEvent e){
     if (e.getClickCount() == 2)    {
         listener.evtOnDoubleClick();
     }
    }
}
