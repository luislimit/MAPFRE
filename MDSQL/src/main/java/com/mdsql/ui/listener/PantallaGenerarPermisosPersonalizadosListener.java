package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaGenerarPermisosPersonalizados;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaGenerarPermisosPersonalizadosListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaGenerarPermisosPersonalizados pantallaGenerarPermisosPersonalizados;
	
	public PantallaGenerarPermisosPersonalizadosListener(PantallaGenerarPermisosPersonalizados pantallaGenerarPermisosPersonalizados) {
		super();
		this.pantallaGenerarPermisosPersonalizados = pantallaGenerarPermisosPersonalizados;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		if (MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGenerar();
		}
		if (MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaGenerarPermisosPersonalizados.dispose();
		}
	}
	
	private void eventBtnBuscar() {
		
	}
	
	private void eventBtnGenerar() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
}
