package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaDetallePermisosPorColumna;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaDetallePermisosPorColumnaListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaDetallePermisosPorColumna pantallaDetallePermisosPorColumna;
	
	public PantallaDetallePermisosPorColumnaListener(PantallaDetallePermisosPorColumna pantallaDetallePermisosPorColumna) {
		super();
		this.pantallaDetallePermisosPorColumna = pantallaDetallePermisosPorColumna;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_ALTA.equals(jButton.getActionCommand())) {
			eventBtnAlta();
		}
		
		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_MODIFICACION.equals(jButton.getActionCommand())) {
			eventBtnModificacion();
		}

		if (MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_INFORME.equals(jButton.getActionCommand())) {
			eventBtnInforme();
		}

		if (MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaDetallePermisosPorColumna.dispose();
		}
	}
	
	private void eventBtnBuscar() {
		
	}
	
	private void eventBtnAlta() {
			
	}
	
	private void eventBtnModificacion() {
		
	}
	
	private void eventBtnInforme() {
		
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}
}
