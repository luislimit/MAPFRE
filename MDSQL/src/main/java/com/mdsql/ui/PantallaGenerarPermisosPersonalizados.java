/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaGenerarPermisosPersonalizadosListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaGenerarPermisosPersonalizados extends DialogSupport {

		private static final long serialVersionUID = 1L;
	 
	 	// Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel14;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    
	    @Getter
	    private javax.swing.JTable tblPermsos;
	    
	    @Getter
	    private javax.swing.JTable tblSinonimos;
	    
	    @Getter
	    private javax.swing.JTextField txtModeloProyecto;
	    
	    @Getter
	    private javax.swing.JTextField txtModeloProyecto1;
	    
	    @Getter
	    private javax.swing.JTextField txtPeticion;
	    
	    @Getter
	    private javax.swing.JButton btnBuscar;
	    
	    @Getter
	    private javax.swing.JButton btnBuscarModelo;
	    
	    @Getter
	    private javax.swing.JButton btnCancelar;
	    
	    @Getter
	    private javax.swing.JButton btnGenerar;
	    
	    @Getter
	    private javax.swing.JComboBox<String> jComboBox1;
	    // End of variables declaration//GEN-END:variables
	 
	    public PantallaGenerarPermisosPersonalizados(FrameSupport parent, Boolean modal) {
			 super(parent, modal);
		 }

		 public PantallaGenerarPermisosPersonalizados(FrameSupport parent, Boolean modal, Map<String, Object> params) {
			super(parent, modal, params);
		 }
		 
		 @Override
		 protected void setupComponents() {
			 	jLabel14 = new javax.swing.JLabel();
		        jLabel1 = new javax.swing.JLabel();
		        txtModeloProyecto = new javax.swing.JTextField();
		        btnBuscarModelo = new javax.swing.JButton();
		        txtModeloProyecto1 = new javax.swing.JTextField();
		        jLabel2 = new javax.swing.JLabel();
		        jComboBox1 = new javax.swing.JComboBox<>();
		        jLabel3 = new javax.swing.JLabel();
		        jLabel4 = new javax.swing.JLabel();
		        txtPeticion = new javax.swing.JTextField();
		        btnBuscar = new javax.swing.JButton();
		        jScrollPane1 = new javax.swing.JScrollPane();
		        tblSinonimos = new javax.swing.JTable();
		        jScrollPane2 = new javax.swing.JScrollPane();
		        tblPermsos = new javax.swing.JTable();
		        jLabel5 = new javax.swing.JLabel();
		        jLabel6 = new javax.swing.JLabel();
		        btnGenerar = new javax.swing.JButton();
		        btnCancelar = new javax.swing.JButton();
		        
		        setBounds(1366, 768);
		        
		        jScrollPane1.setViewportView(tblSinonimos);
		        jScrollPane2.setViewportView(tblPermsos);
		        
		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(42, 42, 42)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jLabel14)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                    .addComponent(jLabel1)
		                                    .addComponent(jLabel2))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addGroup(layout.createSequentialGroup()
		                                        .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                        .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                    .addComponent(txtModeloProyecto1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                .addGap(129, 129, 129)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                    .addComponent(jLabel3)
		                                    .addGroup(layout.createSequentialGroup()
		                                        .addGap(18, 18, 18)
		                                        .addComponent(jLabel4)))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(586, 586, 586)
		                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(50, 50, 50)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel6)
		                                    .addComponent(jLabel5))
		                                .addGap(0, 0, Short.MAX_VALUE)))))
		                .addGap(116, 116, 116))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(512, 512, 512)
		                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(layout.createSequentialGroup()
		                    .addGap(52, 52, 52)
		                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
		                    .addGap(114, 114, 114)))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(20, 20, 20)
		                .addComponent(jLabel14)
		                .addGap(31, 31, 31)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel4)))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(btnBuscarModelo)
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addComponent(jLabel1))
		                            .addComponent(jLabel3))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(txtModeloProyecto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel2))))
		                .addGap(27, 27, 27)
		                .addComponent(btnBuscar)
		                .addGap(5, 5, 5)
		                .addComponent(jLabel5)
		                .addGap(222, 222, 222)
		                .addComponent(jLabel6)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(btnGenerar)
		                    .addComponent(btnCancelar))
		                .addContainerGap(51, Short.MAX_VALUE))
		            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(layout.createSequentialGroup()
		                    .addGap(247, 247, 247)
		                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addContainerGap(321, Short.MAX_VALUE)))
		        );
		 }
	 
		 @Override
		 protected void initEvents() {
			 PantallaGenerarPermisosPersonalizadosListener actionListener = new PantallaGenerarPermisosPersonalizadosListener(this);
			 
			 btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_BUSCAR);
			 btnGenerar.setActionCommand(MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_GUARDAR);
			 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_GENERAR_PERMISOS_PERSONALIZADO_CANCELAR);
			 
			 btnBuscar.addActionListener(actionListener);
			 btnGenerar.addActionListener(actionListener);
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
			 setTitle(literales.getLiteral("PantallaGenerarPermisosPersonalizados.title"));

			 jLabel1.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label1"));
			 jLabel2.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label2"));
			 jLabel3.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label3"));
			 jLabel4.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label4"));
			 btnBuscar.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.btnBuscar"));
			 jLabel5.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label5"));
			 jLabel6.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.label6"));
			 btnGenerar.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.btnGenerar"));
			 btnCancelar.setText(literales.getLiteral("PantallaGenerarPermisosPersonalizados.btnCancelar"));
		 }
	 
		 /**
			 * 
			 */
		 public void enableButtons(Boolean val) {
			btnBuscar.setEnabled(val);
			btnGenerar.setEnabled(val);
			btnCancelar.setEnabled(val);
		 }
}
