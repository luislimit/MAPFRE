/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.listener.PantallaHistoricoBajaListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 *
 * @author USUARIO1
 */
public class PantallaHistoricoBaja extends DialogSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -6174427890699657674L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JLabel jLabel1;
	@Getter
	private javax.swing.JTextField txtPeticion;
	// End of variables declaration//GEN-END:variables

	@Getter
	@Setter
	private Historico seleccionado;

	@Getter
	@Setter
	private Modelo modeloSeleccionado;

	public PantallaHistoricoBaja(FrameSupport parent, Boolean modal) {
		super(parent, modal);
	}

	public PantallaHistoricoBaja(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	}

	@Override
	protected void setupComponents() {
		btnAceptar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		txtPeticion = new javax.swing.JTextField();

		this.setBounds(510, 130);

		txtPeticion.setPreferredSize(new java.awt.Dimension(64, 41));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

	@Override
	protected void initEvents() {
		PantallaHistoricoBajaListener pantallaHistoricoBajaListener = new PantallaHistoricoBajaListener(this);

		btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR);
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_CANCELAR);

		btnAceptar.addActionListener(pantallaHistoricoBajaListener);
		btnCancelar.addActionListener(pantallaHistoricoBajaListener);
	}

	@Override
	protected void initModels() {
	}

	@Override
	protected void initialState() {
		seleccionado = (Historico) params.get("historico");
		modeloSeleccionado = (Modelo) params.get("modelo");
	}

	@Override
	protected void setupLiterals() {
		btnAceptar.setText("ACEPTAR");

		btnCancelar.setText("CANCELAR");

		jLabel1.setText("Petición:");
	}
}
