/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.mdsql.ui.listener.PantallaBuscadorFicherosListener;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaBuscadorFicheros extends DialogSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6174427890699657674L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel jLabel1;

	@Getter
	private JTextField txtRuta;
	// End of variables declaration//GEN-END:variables

	@Getter
	private PantallaBuscadorFicherosListener pantallaBuscadorFicherosListener;

	public PantallaBuscadorFicheros(FrameSupport parent, Boolean modal) {
		super(parent, modal);
	}

	public PantallaBuscadorFicheros(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	}

	@Override
	protected void setupComponents() {
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		jLabel1 = new JLabel();
		txtRuta = new JTextField();
		
		setBounds(502, 140);

		txtRuta.setPreferredSize(new Dimension(64, 41));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(btnAceptar)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnCancelar)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
								.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnCancelar)
								.addComponent(btnAceptar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}

	@Override
	protected void initEvents() {
		pantallaBuscadorFicherosListener = new PantallaBuscadorFicherosListener(this);

		btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_BUSCADOR_FICHEROS_BTN_ACEPTAR);
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_BUSCADOR_FICHEROS_BTN_CANCELAR);

		btnAceptar.addActionListener(pantallaBuscadorFicherosListener);
		btnCancelar.addActionListener(pantallaBuscadorFicherosListener);
	}

	@Override
	protected void initModels() {
	}

	@Override
	protected void initialState() {
		try {
			ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
			String rutaInicial = configuration.getConfig("RutaDefectoScripts");
			txtRuta.setText(rutaInicial);
		} catch (IOException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(this.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	@Override
	protected void setupLiterals() {
		setTitle(literales.getLiteral("PantallaBuscadorFicheros.title"));

		jLabel1.setText(literales.getLiteral("PantallaBuscadorFicheros.label1"));
		btnAceptar.setText(literales.getLiteral("PantallaBuscadorFicheros.aceptar"));
		btnCancelar.setText(literales.getLiteral("PantallaBuscadorFicheros.cancelar"));
	}
}
