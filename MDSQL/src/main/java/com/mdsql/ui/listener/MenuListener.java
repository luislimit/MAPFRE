package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.UIHelper;

/**
 * El listener del menú principal usa la factoría de creación de diálogos
 * pasándole la opción del menú seleccionada, con lo que esta clase ya no 
 * crecerá en código.
 * 
 * @author federico
 *
 */
public class MenuListener implements ActionListener {
	
	private FrameSupport frameParent;
	
	public MenuListener(FrameSupport frameParent) {
		super();
		this.frameParent = frameParent;
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		DialogSupport dialog = MDSQLUIHelper.createDialog(frameParent, item.getActionCommand());
		UIHelper.show(dialog);
	}
}
