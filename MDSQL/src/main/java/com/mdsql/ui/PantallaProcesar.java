/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.Proceso;
import com.mdval.ui.utils.TableSupport;

/**
 *
 * @author federico
 */
public interface PantallaProcesar {

	TableSupport getTblUltimasPeticiones();
	
	void setProcesoSeleccionado(Proceso proceso);
	
	JButton getBtnVerProcesado();
}
