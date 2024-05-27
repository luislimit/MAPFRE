/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaMantenimientoPermisosPorColumnaListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaMantenimientoPermisosPorColumna extends DialogSupport {

	 private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    
    @Getter
    private javax.swing.JTextArea txtColumna;
    
    @Getter
    private javax.swing.JTextField txtFecha;
    
    @Getter
    private javax.swing.JTextField txtModeloProyecto;
    
    @Getter
    private javax.swing.JTextField txtModeloProyecto2;
    
    @Getter
    private javax.swing.JTextField txtPeticion;
    
    @Getter
    private javax.swing.JTextField txtTabla;
    
    @Getter
    private javax.swing.JTextField txtUsuario;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnGuardar;
    
    @Getter
    private javax.swing.JCheckBox chkHabilitada;
    
    @Getter
    private javax.swing.JComboBox<String> cmbEntorno;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPDC;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPermiso;
    
    @Getter
    private javax.swing.JComboBox<String> cmbReceptorPermisos;
    
    @Getter
    private javax.swing.JComboBox<String> cmbWithGrant;
    // End of variables declaration//GEN-END:variables
    
    public PantallaMantenimientoPermisosPorColumna(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaMantenimientoPermisosPorColumna(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 	jLabel1 = new javax.swing.JLabel();
	        txtModeloProyecto = new javax.swing.JTextField();
	        txtTabla = new javax.swing.JTextField();
	        txtModeloProyecto2 = new javax.swing.JTextField();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        txtPeticion = new javax.swing.JTextField();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        txtColumna = new javax.swing.JTextArea();
	        jLabel4 = new javax.swing.JLabel();
	        cmbPermiso = new javax.swing.JComboBox<>();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        cmbReceptorPermisos = new javax.swing.JComboBox<>();
	        jLabel7 = new javax.swing.JLabel();
	        cmbEntorno = new javax.swing.JComboBox<>();
	        cmbWithGrant = new javax.swing.JComboBox<>();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        cmbPDC = new javax.swing.JComboBox<>();
	        chkHabilitada = new javax.swing.JCheckBox();
	        jLabel10 = new javax.swing.JLabel();
	        txtUsuario = new javax.swing.JTextField();
	        jLabel11 = new javax.swing.JLabel();
	        txtFecha = new javax.swing.JTextField();
	        btnGuardar = new javax.swing.JButton();
	        btnCancelar = new javax.swing.JButton();
	        
	        setBounds(1366, 520);
	        
	        jScrollPane1.setViewportView(txtColumna);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(51, 51, 51)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel14)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel1)
	                            .addComponent(jLabel2)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel5)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel10))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                    .addComponent(txtTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                    .addComponent(jLabel3)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(txtModeloProyecto2, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(jLabel6)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addGap(92, 92, 92)
	                                            .addComponent(jLabel11)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(cmbWithGrant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(277, 277, 277)
	                                        .addComponent(jLabel9)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(cmbPDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                .addGap(40, 40, 40)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(chkHabilitada)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(jLabel7)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                .addGap(92, 92, 92))))))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(482, 482, 482)
	                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(20, 20, 20)
	                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(17, 17, 17)
	                .addComponent(jLabel14)
	                .addGap(31, 31, 31)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(txtModeloProyecto2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2)
	                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(jLabel6)
	                    .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7)
	                    .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbWithGrant, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8)
	                    .addComponent(cmbPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel9)
	                    .addComponent(chkHabilitada))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel10)
	                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel11))
	                .addGap(31, 31, 31)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnGuardar)
	                    .addComponent(btnCancelar))
	                .addContainerGap(75, Short.MAX_VALUE))
	        );
	 }
    
	 @Override
	 protected void initEvents() {
		 PantallaMantenimientoPermisosPorColumnaListener actionListener = new PantallaMantenimientoPermisosPorColumnaListener(this);
		 
		 btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_GUARDAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_COLUMNA_CANCELAR);
		 
		 btnGuardar.addActionListener(actionListener);
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
		 setTitle(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.title"));

		 jLabel1.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label1"));
		 jLabel2.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label2"));
		 jLabel3.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label3"));
		 jLabel4.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label4"));
		 jLabel5.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label5"));
		 jLabel6.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label6"));
		 jLabel7.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label7"));
		 jLabel8.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label8"));
		 jLabel9.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label9"));
		 chkHabilitada.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.chkHabilitada"));
		 jLabel10.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label10"));
		 jLabel11.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.label11"));
		 btnGuardar.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.btnGuardar"));
		 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoPermisosPorColumna.btnCancelar"));
	 }
    
	 /**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnGuardar.setEnabled(val);
		btnCancelar.setEnabled(val);
	}
}
