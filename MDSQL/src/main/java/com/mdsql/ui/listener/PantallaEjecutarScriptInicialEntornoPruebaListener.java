package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaEjecutarScriptInicialEntornoPrueba;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaEjecutarScriptInicialEntornoPruebaListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaEjecutarScriptInicialEntornoPrueba pantallaEjecutarScriptInicialEntornoPrueba;
	
	public PantallaEjecutarScriptInicialEntornoPruebaListener(PantallaEjecutarScriptInicialEntornoPrueba pantallaEjecutarScriptInicialEntornoPrueba) {
		super();
		this.pantallaEjecutarScriptInicialEntornoPrueba = pantallaEjecutarScriptInicialEntornoPrueba;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_PROCESAR.equals(jButton.getActionCommand())) {
			eventBtnProcesar();
		}

		if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_EJECUTAR.equals(jButton.getActionCommand())) {
			eventBtnEjecutar();
		}

		if (MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaEjecutarScriptInicialEntornoPrueba.dispose();
		}
	}
	
	private void eventBtnProcesar() {
		
	}
	
	private void eventBtnEjecutar() {
		
	}

	@Override
	public void onLoad() {
		
	}
}
