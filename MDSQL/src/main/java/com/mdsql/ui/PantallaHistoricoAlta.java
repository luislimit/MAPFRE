/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.listener.PantallaHistoricoAltaListener;
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
public class PantallaHistoricoAlta extends DialogSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = -6174427890699657674L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;

	@Getter
	private javax.swing.JCheckBox chkHistorificada;

	@Getter
	private javax.swing.JComboBox<String> cmbTipoObjeto;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	@Getter
	private javax.swing.JTextField txtModelo;

	@Getter
	private javax.swing.JTextField txtNombreObjeto;

	@Getter
	private javax.swing.JTextField txtPeticion;
	// End of variables declaration//GEN-END:variables

	@Getter
	@Setter
	private Modelo modeloSeleccionado;

	public PantallaHistoricoAlta(FrameSupport parent, Boolean modal) {
		super(parent, modal);
	}

	public PantallaHistoricoAlta(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	}

	@Override
	protected void setupComponents() {
		btnAceptar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		txtPeticion = new javax.swing.JTextField();
		txtNombreObjeto = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		chkHistorificada = new javax.swing.JCheckBox();
		jLabel15 = new javax.swing.JLabel();
		cmbTipoObjeto = new javax.swing.JComboBox<>();
		jLabel3 = new javax.swing.JLabel();
		txtModelo = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		
		setBounds(673, 226);
		
		chkHistorificada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHistorificada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

		txtPeticion.setPreferredSize(new java.awt.Dimension(64, 41));

		txtNombreObjeto.setPreferredSize(new java.awt.Dimension(64, 41));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(chkHistorificada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPeticion, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(cmbTipoObjeto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(chkHistorificada)
                .addGap(18, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
	}

	@Override
	protected void initEvents() {
		PantallaHistoricoAltaListener pantallaHistoricoAltaListener = new PantallaHistoricoAltaListener(this);

		btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_ACEPTAR);
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_ALTA_BTN_CANCELAR);
		jButton1.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_ALTA_BUSCAR_MODELO);

		btnAceptar.addActionListener(pantallaHistoricoAltaListener);
		btnCancelar.addActionListener(pantallaHistoricoAltaListener);
		jButton1.addActionListener(pantallaHistoricoAltaListener);

		this.addOnLoadListener(pantallaHistoricoAltaListener);
	}

	@Override
	protected void initModels() {
	}

	@Override
	protected void initialState() {
	}

	@Override
	protected void setupLiterals() {
		btnAceptar.setText("ACEPTAR");
		btnCancelar.setText("CANCELAR");
		jLabel1.setText("Petición:");
		jLabel2.setText("Nombre Objeto:");
		chkHistorificada.setText("Historificada por SMD");
		jLabel15.setText("Tipo de Objeto");
		jLabel3.setText("Modelo o proyecto");
		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
	}
}
