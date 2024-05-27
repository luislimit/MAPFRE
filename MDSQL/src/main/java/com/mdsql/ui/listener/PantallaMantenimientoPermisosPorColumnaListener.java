package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaMantenimientoPermisosPorColumna;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaMantenimientoPermisosPorColumnaListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoPermisosPorColumna pantallaMantenimientoPermisosPorColumna;
	
	public PantallaMantenimientoPermisosPorColumnaListener(PantallaMantenimientoPermisosPorColumna pantallaMantenimientoPermisosPorColumna) {
		super();
		this.pantallaMantenimientoPermisosPorColumna = pantallaMantenimientoPermisosPorColumna;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoPermisosPorColumna.dispose();
		}
	}
	
	private void eventBtnGuardar() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
}
