package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaConsultaPermisosPersonalizados;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaConsultaPermisosPersonalizadosListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaConsultaPermisosPersonalizados pantallaConsultaPermisosPersonalizados;
	
	public PantallaConsultaPermisosPersonalizadosListener(PantallaConsultaPermisosPersonalizados pantallaConsultaPermisosPersonalizados) {
		super();
		this.pantallaConsultaPermisosPersonalizados = pantallaConsultaPermisosPersonalizados;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR_MODELO.equals(jButton.getActionCommand())) {
			eventBtnBuscarModelo();
		}
		if (MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		if (MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaConsultaPermisosPersonalizados.dispose();
		}
	}
	
	private void eventBtnBuscarModelo() {
		
	}
	
	private void eventBtnBuscar() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
}
