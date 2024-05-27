/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaConfirmaciónGeneraciónPermisosListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaConfirmaciónGeneraciónPermisos extends DialogSupport {

	 private static final long serialVersionUID = 1L;	
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    
    @Getter
    private javax.swing.JTextArea txtComentario;
    
    @Getter
    private javax.swing.JTextField txtDemanda;
    
    @Getter
    private javax.swing.JTextField txtPeticion;
    
    @Getter
    private javax.swing.JTextField txtPeticion1;
    
    @Getter
    private javax.swing.JTextField txtRuta;
    
    @Getter
    private javax.swing.JButton btnAceptar;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnRuta;
    
    @Getter
    private javax.swing.JCheckBox chkGenerarPermisos;
    
    @Getter
    private javax.swing.JCheckBox chkGenerarRestoPermisos;
    
    @Getter
    private javax.swing.JComboBox<String> cmbBBDD;
    // End of variables declaration//GEN-END:variables

    public PantallaConfirmaciónGeneraciónPermisos(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaConfirmaciónGeneraciónPermisos(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 	txtPeticion = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        chkGenerarPermisos = new javax.swing.JCheckBox();
	        txtPeticion1 = new javax.swing.JTextField();
	        jLabel2 = new javax.swing.JLabel();
	        chkGenerarRestoPermisos = new javax.swing.JCheckBox();
	        cmbBBDD = new javax.swing.JComboBox<>();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        txtDemanda = new javax.swing.JTextField();
	        jLabel5 = new javax.swing.JLabel();
	        txtRuta = new javax.swing.JTextField();
	        btnRuta = new javax.swing.JButton();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        txtComentario = new javax.swing.JTextArea();
	        jLabel6 = new javax.swing.JLabel();
	        btnAceptar = new javax.swing.JButton();
	        btnCancelar = new javax.swing.JButton();

	        setBounds(770, 320);
	        
	        jScrollPane1.setViewportView(txtComentario);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(16, 16, 16)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel2)
	                                    .addComponent(jLabel3))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addComponent(cmbBBDD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                    .addComponent(txtPeticion1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
	                        .addGap(83, 83, 83)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(chkGenerarPermisos)
	                            .addComponent(chkGenerarRestoPermisos)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(25, 25, 25)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel5)
	                            .addComponent(jLabel6))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(btnRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(265, 265, 265)
	                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(41, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(25, 25, 25)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1)
	                    .addComponent(chkGenerarPermisos))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtPeticion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2)
	                    .addComponent(chkGenerarRestoPermisos))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(btnRuta))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnAceptar)
	                    .addComponent(btnCancelar))
	                .addContainerGap(11, Short.MAX_VALUE))
	        );
	 }
    
	 @Override
	 protected void initEvents() {
		 PantallaConfirmaciónGeneraciónPermisosListener actionListener = new PantallaConfirmaciónGeneraciónPermisosListener(this);
		 
		 btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_CONFIRMACION_GENERACION_PERMISOS_ACEPTAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_CONFIRMACION_GENERACION_PERMISOS_CANCELAR);
		 
		 btnAceptar.addActionListener(actionListener);
		 btnCancelar.addActionListener(actionListener);
	 }
    
	 @Override
	 protected void initModels() {
		 
	 }
    
	 @Override
	 protected void initialState() {
		 
	 }
    
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.title"));

		 jLabel1.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label1"));
		 chkGenerarPermisos.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.chkGenerarPermisos"));
		 jLabel2.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label2"));
		 chkGenerarRestoPermisos.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.chkGenerarRestoPermisos"));
		 jLabel3.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label3"));
		 jLabel4.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label4"));
		 jLabel5.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label5"));
		 jLabel6.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.label6"));
		 btnAceptar.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.btnAceptar"));
		 btnCancelar.setText(literales.getLiteral("PantallaConfirmaciónGeneraciónPermisos.btnCancelar"));
	 }
    
	 /**
		 * 
		 */
	 public void enableButtons(Boolean val) {
		btnAceptar.setEnabled(val);
		btnCancelar.setEnabled(val);
	 }
}
