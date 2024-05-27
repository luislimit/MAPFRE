package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaConfirmaciónGeneraciónPermisos;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaConfirmaciónGeneraciónPermisosListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaConfirmaciónGeneraciónPermisos pantallaConfirmaciónGeneraciónPermisos;
	
	public PantallaConfirmaciónGeneraciónPermisosListener(PantallaConfirmaciónGeneraciónPermisos pantallaConfirmaciónGeneraciónPermisos) {
		super();
		this.pantallaConfirmaciónGeneraciónPermisos = pantallaConfirmaciónGeneraciónPermisos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_CONFIRMACION_GENERACION_PERMISOS_ACEPTAR.equals(jButton.getActionCommand())) {
			eventBtnAceptar();
		}
		if (MDSQLConstants.PANTALLA_CONFIRMACION_GENERACION_PERMISOS_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaConfirmaciónGeneraciónPermisos.dispose();
		}
	}
	
	private void eventBtnAceptar() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
}
