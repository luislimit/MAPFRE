package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.mdsql.ui.PantallaBuscadorFicheros;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.utils.MDSQLConstants;

public class PantallaBuscadorFicherosListener extends ListenerSupport implements ActionListener {

	private PantallaBuscadorFicheros pantallaBuscadorFicheros;

	public PantallaBuscadorFicherosListener(PantallaBuscadorFicheros pantallaBuscadorFicheros) {
		super();
		this.pantallaBuscadorFicheros = pantallaBuscadorFicheros;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_BUSCADOR_FICHEROS_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			pantallaBuscadorFicheros.getReturnParams().put("RutaInicial",
					pantallaBuscadorFicheros.getTxtRuta().getText());
			pantallaBuscadorFicheros.dispose();
		}

		if (MDSQLConstants.PANTALLA_BUSCADOR_FICHEROS_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaBuscadorFicheros.dispose();
		}
	}
}
