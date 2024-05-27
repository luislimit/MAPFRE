/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaConsultaPermisosPersonalizadosListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaConsultaPermisosPersonalizados extends DialogSupport {

	 private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    
    @Getter
    private javax.swing.JTable jTable1;
    
    @Getter
    private javax.swing.JTable jTable2;
    
    @Getter
    private javax.swing.JTextField txtColumna;
    
    @Getter
    private javax.swing.JTextField txtFechaDesde;
    
    @Getter
    private javax.swing.JTextField txtFechaHasta;
    
    @Getter
    private javax.swing.JTextField txtFuncionNombre;
    
    @Getter
    private javax.swing.JTextField txtModeloProyecto;
    
    @Getter
    private javax.swing.JTextField txtNombreObjeto;
    
    @Getter
    private javax.swing.JTextField txtPeticion;
    
    @Getter
    private javax.swing.JTextField txtUsuarioModificacion;
    
    @Getter
    private javax.swing.JButton btnBuscar;
    
    @Getter
    private javax.swing.JButton btnBuscarModelo;
    
    @Getter
    private javax.swing.JCheckBox chkHabilitada;
    
    @Getter
    private javax.swing.JComboBox<String> cmbEntorno;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPermiso;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPermiso1;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPermisoSinonimo;
    
    @Getter
    private javax.swing.JComboBox<String> cmbPermisoSinonimo1;
    
    @Getter
    private javax.swing.JComboBox<String> cmbReceptorPermisos;
    
    @Getter
    private javax.swing.JComboBox<String> cmbTipoObjeto;
    
    @Getter
    private javax.swing.JComboBox<String> cmbWithGrant;
    
    @Getter
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

    public PantallaConsultaPermisosPersonalizados(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaConsultaPermisosPersonalizados(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 	jLabel14 = new javax.swing.JLabel();
	        txtModeloProyecto = new javax.swing.JTextField();
	        jLabel1 = new javax.swing.JLabel();
	        txtNombreObjeto = new javax.swing.JTextField();
	        jLabel2 = new javax.swing.JLabel();
	        txtColumna = new javax.swing.JTextField();
	        jLabel3 = new javax.swing.JLabel();
	        btnBuscarModelo = new javax.swing.JButton();
	        jLabel4 = new javax.swing.JLabel();
	        cmbPermisoSinonimo = new javax.swing.JComboBox<>();
	        cmbPermisoSinonimo1 = new javax.swing.JComboBox<>();
	        jLabel5 = new javax.swing.JLabel();
	        cmbWithGrant = new javax.swing.JComboBox<>();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        txtPeticion = new javax.swing.JTextField();
	        txtFuncionNombre = new javax.swing.JTextField();
	        jLabel8 = new javax.swing.JLabel();
	        txtUsuarioModificacion = new javax.swing.JTextField();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        cmbTipoObjeto = new javax.swing.JComboBox<>();
	        jLabel11 = new javax.swing.JLabel();
	        cmbReceptorPermisos = new javax.swing.JComboBox<>();
	        jLabel12 = new javax.swing.JLabel();
	        txtFechaDesde = new javax.swing.JTextField();
	        jLabel13 = new javax.swing.JLabel();
	        cmbPermiso = new javax.swing.JComboBox<>();
	        jLabel15 = new javax.swing.JLabel();
	        cmbPermiso1 = new javax.swing.JComboBox<>();
	        jLabel16 = new javax.swing.JLabel();
	        txtFechaHasta = new javax.swing.JTextField();
	        jLabel17 = new javax.swing.JLabel();
	        cmbEntorno = new javax.swing.JComboBox<>();
	        chkHabilitada = new javax.swing.JCheckBox();
	        btnBuscar = new javax.swing.JButton();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jLabel18 = new javax.swing.JLabel();
	        jLabel19 = new javax.swing.JLabel();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        jTable2 = new javax.swing.JTable();
	        jButton1 = new javax.swing.JButton();
	        
	        setBounds(1366, 930);
	        
	        jScrollPane1.setViewportView(jTable1);
	        jScrollPane2.setViewportView(jTable2);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(25, 25, 25)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel7)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel1))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel10)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addGroup(layout.createSequentialGroup()
	                            .addComponent(jLabel17)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGroup(layout.createSequentialGroup()
	                            .addComponent(jLabel13)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel2)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel5)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cmbPermisoSinonimo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel8)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel11)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel15)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(chkHabilitada)
	                            .addComponent(cmbPermiso1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(178, 178, 178)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel3)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel6)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cmbWithGrant, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel9)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel12)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(jLabel16)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(207, 207, 207))
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(110, 110, 110)
	                        .addComponent(jLabel14))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(53, 53, 53)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel18)
	                            .addComponent(jScrollPane1)
	                            .addComponent(jLabel19)
	                            .addComponent(jScrollPane2)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(585, 585, 585)
	                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(219, 219, 219))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(24, 24, 24)
	                .addComponent(jLabel14)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1)
	                    .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3)
	                    .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2)
	                    .addComponent(btnBuscarModelo))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel4)
	                    .addComponent(cmbPermisoSinonimo1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(cmbWithGrant, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7)
	                    .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8)
	                    .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel9))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel10)
	                    .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel11)
	                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel12))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel13)
	                    .addComponent(cmbPermiso1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel15)
	                    .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel16))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel17)
	                    .addComponent(chkHabilitada))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnBuscar)
	                .addGap(7, 7, 7)
	                .addComponent(jLabel18)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jLabel19)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
	                .addGap(26, 26, 26)
	                .addComponent(jButton1)
	                .addGap(28, 28, 28))
	        );
	 }
	 
	 @Override
	 protected void initEvents() {
		 PantallaConsultaPermisosPersonalizadosListener actionListener = new PantallaConsultaPermisosPersonalizadosListener(this);
		 
		 btnBuscarModelo.setActionCommand(MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR_MODELO);
		 btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_BUSCAR);
		 jButton1.setActionCommand(MDSQLConstants.PANTALLA_CONSULTA_PERMISOS_PERSONALIZADO_CANCELAR);
		 
		 btnBuscarModelo.addActionListener(actionListener);
		 btnBuscar.addActionListener(actionListener);
		 jButton1.addActionListener(actionListener);
	 }
    
	 @Override
	 protected void initModels() {
		 
	 }
    
	 @Override
	 protected void initialState() {
		 
	 }
    
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaConsultaPermisosPersonalizados.title"));

		 jLabel1.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label1"));
		 jLabel2.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label2"));
		 jLabel3.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label3"));
		 jLabel4.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label4"));
		 jLabel5.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label5"));
		 jLabel6.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label6"));
		 jLabel7.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label7"));
		 jLabel8.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label8"));
		 jLabel9.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label9"));
		 jLabel10.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label10"));
		 jLabel11.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label11"));
		 jLabel12.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label12"));
		 jLabel13.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label13"));
		 jLabel14.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label14"));
		 jLabel15.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label15"));
		 jLabel16.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label16"));
		 jLabel17.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label17"));
		 chkHabilitada.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.chkHabilitada"));
		 btnBuscar.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.btnBuscar"));
		 jLabel18.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label18"));
		 jLabel19.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.label19"));
		 jButton1.setText(literales.getLiteral("PantallaConsultaPermisosPersonalizados.jButton1"));
	 }
    
	 /**
		 * 
		 */
	 public void enableButtons(Boolean val) {
			btnBuscarModelo.setEnabled(val);
			btnBuscar.setEnabled(val);
			jButton1.setEnabled(val);
	 }
}
