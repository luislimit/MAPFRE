/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaEjecutarScriptInicialEntornoPruebaListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaEjecutarScriptInicialEntornoPrueba extends DialogSupport {

	 private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    
    @Getter
    private javax.swing.JTextField txtDemanda;
    
    @Getter
    private javax.swing.JTextField txtModeloProyecto;
    
    @Getter
    private javax.swing.JTextField txtPeticion;
    
    @Getter
    private javax.swing.JTextField txtScript;
    
    @Getter
    private javax.swing.JTextField txtSolicitada;
    
    @Getter
    private javax.swing.JButton btnBuscar;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnEjecutar;
    
    @Getter
    private javax.swing.JButton btnFile;
    
    @Getter
    private javax.swing.JButton btnProcesar;
    
    @Getter
    private javax.swing.JCheckBox chkGenerarDROP;
    
    @Getter
    private javax.swing.JComboBox<String> cmbEntornoPrueba;
    
    @Getter
    private javax.swing.JComboBox<String> cmbSubmodelo;
    // End of variables declaration//GEN-END:variables

    public PantallaEjecutarScriptInicialEntornoPrueba(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaEjecutarScriptInicialEntornoPrueba(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
    
	 @Override
	 protected void setupComponents() {
		 	txtModeloProyecto = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        txtPeticion = new javax.swing.JTextField();
	        jLabel3 = new javax.swing.JLabel();
	        txtScript = new javax.swing.JTextField();
	        btnBuscar = new javax.swing.JButton();
	        cmbSubmodelo = new javax.swing.JComboBox<>();
	        jLabel4 = new javax.swing.JLabel();
	        btnFile = new javax.swing.JButton();
	        jLabel5 = new javax.swing.JLabel();
	        txtSolicitada = new javax.swing.JTextField();
	        jLabel6 = new javax.swing.JLabel();
	        txtDemanda = new javax.swing.JTextField();
	        cmbEntornoPrueba = new javax.swing.JComboBox<>();
	        jLabel7 = new javax.swing.JLabel();
	        chkGenerarDROP = new javax.swing.JCheckBox();
	        btnProcesar = new javax.swing.JButton();
	        btnEjecutar = new javax.swing.JButton();
	        btnCancelar = new javax.swing.JButton();
	        
	        setBounds(900, 320);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(14, 14, 14)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jLabel1)
	                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2)
	                    .addComponent(jLabel6)
	                    .addComponent(jLabel7))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtScript, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(cmbEntornoPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                .addComponent(jLabel4)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(cmbSubmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                .addComponent(jLabel3)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(txtSolicitada, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(chkGenerarDROP, javax.swing.GroupLayout.Alignment.TRAILING))))
	                .addContainerGap(34, Short.MAX_VALUE))
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(232, 232, 232))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(28, 28, 28)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(btnFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(txtScript, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(0, 0, Short.MAX_VALUE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel1)
	                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(cmbSubmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel4))
	                        .addGap(0, 0, Short.MAX_VALUE)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel2))
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(txtSolicitada, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel3)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbEntornoPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7)
	                    .addComponent(chkGenerarDROP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(33, 33, 33)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnProcesar)
	                    .addComponent(btnEjecutar)
	                    .addComponent(btnCancelar))
	                .addGap(34, 34, 34))
	        );
	 }
	 
	 @Override
	 protected void initEvents() {
		 PantallaEjecutarScriptInicialEntornoPruebaListener actionListener = new PantallaEjecutarScriptInicialEntornoPruebaListener(this);
		 
		 btnProcesar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_PROCESAR);
		 btnEjecutar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_EJECUTAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_SCRIPT_INICIAL_ENTORNO_PRUEBA_CANCELAR);
		 
		 btnProcesar.addActionListener(actionListener);
		 btnEjecutar.addActionListener(actionListener);
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
		 setTitle(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.titulo"));

		 jLabel1.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel1"));
		 jLabel2.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel2"));
		 jLabel3.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel3"));
		 jLabel4.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel4"));
		 jLabel5.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel5"));
		 jLabel6.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel6"));
		 jLabel7.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.jLabel7"));
		 chkGenerarDROP.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.chkGenerarDROP"));
		 btnProcesar.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.btnProcesar"));
		 btnEjecutar.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.btnEjecutar"));
		 btnCancelar.setText(literales.getLiteral("PantallaEjecutarScriptInicialEntornoPrueba.btnCancelar"));
	 }
    
	 /**
		 * 
		 */
	 public void enableButtons(Boolean val) {
		btnProcesar.setEnabled(val);
		btnEjecutar.setEnabled(val);
		btnCancelar.setEnabled(val);
	 }   
}
