package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaMantenimientoPermisosPorObjeto;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaMantenimientoPermisosPorObjetoListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto;
	
	public PantallaMantenimientoPermisosPorObjetoListener(PantallaMantenimientoPermisosPorObjeto pantallaMantenimientoPermisosPorObjeto) {
		super();
		this.pantallaMantenimientoPermisosPorObjeto = pantallaMantenimientoPermisosPorObjeto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoPermisosPorObjeto.dispose();
		}
	}
	
	private void eventBtnGuardar() {
		
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}
}
