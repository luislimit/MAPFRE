/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaDetallePermisosPorColumnaListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author federico
 */
public class PantallaDetallePermisosPorColumna extends DialogSupport {

	 	private static final long serialVersionUID = 1L;
	
	 	// Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel11;
	    private javax.swing.JLabel jLabel14;
	    private javax.swing.JLabel jLabel16;
	    private javax.swing.JLabel jLabel19;
	    private javax.swing.JLabel jLabel20;
	    private javax.swing.JLabel jLabel21;
	    private javax.swing.JLabel jLabel27;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JScrollPane jScrollPane2;
	    private javax.swing.JTable tblPermisos;
	    
	    @Getter
	    private javax.swing.JTextField txtColumna;
	    
	    @Getter
	    private javax.swing.JTextField txtModelo1;
	    
	    @Getter
	    private javax.swing.JTextField txtModeloProyecto;
	    
	    @Getter
	    private javax.swing.JTextField txtTabla;
	    
	    @Getter
	    private javax.swing.JButton btnAlta;
	    
	    @Getter
	    private javax.swing.JButton btnBuscar;
	    
	    @Getter
	    private javax.swing.JButton btnCancelar;
	    
	    @Getter
	    private javax.swing.JButton btnModificacion;
	    
	    @Getter
	    private javax.swing.JButton btnProcesar;
	    
	    @Getter
	    private javax.swing.JCheckBox chkHabilitado;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbEntorno;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbIncluirPDC;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbPermiso;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbPropietarioSinonimo1;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbWithGrantOpcion;
	    // End of variables declaration//GEN-END:variables
	
	    public PantallaDetallePermisosPorColumna(FrameSupport parent, Boolean modal) {
			 super(parent, modal);
		 }

		 public PantallaDetallePermisosPorColumna(FrameSupport parent, Boolean modal, Map<String, Object> params) {
			super(parent, modal, params);
		 }
		 
		 @Override
		 protected void setupComponents() {
			 	jLabel1 = new javax.swing.JLabel();
		        jLabel3 = new javax.swing.JLabel();
		        jLabel6 = new javax.swing.JLabel();
		        txtModeloProyecto = new javax.swing.JTextField();
		        txtTabla = new javax.swing.JTextField();
		        cmbEntorno = new javax.swing.JComboBox<>();
		        btnBuscar = new javax.swing.JButton();
		        jLabel11 = new javax.swing.JLabel();
		        jScrollPane2 = new javax.swing.JScrollPane();
		        tblPermisos = new javax.swing.JTable();
		        btnProcesar = new javax.swing.JButton();
		        btnCancelar = new javax.swing.JButton();
		        jLabel14 = new javax.swing.JLabel();
		        txtModelo1 = new javax.swing.JTextField();
		        jLabel16 = new javax.swing.JLabel();
		        cmbPermiso = new javax.swing.JComboBox<>();
		        cmbIncluirPDC = new javax.swing.JComboBox<>();
		        jLabel19 = new javax.swing.JLabel();
		        cmbPropietarioSinonimo1 = new javax.swing.JComboBox<>();
		        jLabel20 = new javax.swing.JLabel();
		        cmbWithGrantOpcion = new javax.swing.JComboBox<>();
		        jLabel21 = new javax.swing.JLabel();
		        chkHabilitado = new javax.swing.JCheckBox();
		        jLabel27 = new javax.swing.JLabel();
		        jLabel4 = new javax.swing.JLabel();
		        txtColumna = new javax.swing.JTextField();
		        btnAlta = new javax.swing.JButton();
		        btnModificacion = new javax.swing.JButton();
		        
		        setBounds(1366, 768);
		        
		        jScrollPane2.setViewportView(tblPermisos);
		        
		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(608, 608, 608)
		                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
		                        .addGap(20, 20, 20)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jLabel11)
		                            .addComponent(jScrollPane2)))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap(69, Short.MAX_VALUE)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jLabel14)
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                                .addGroup(layout.createSequentialGroup()
		                                    .addGap(42, 42, 42)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                        .addComponent(jLabel16)
		                                        .addComponent(jLabel27))
		                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(txtTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                        .addGroup(layout.createSequentialGroup()
		                                            .addComponent(jLabel20)
		                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                            .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(layout.createSequentialGroup()
		                                            .addGap(16, 16, 16)
		                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                                .addGroup(layout.createSequentialGroup()
		                                                    .addComponent(jLabel4)
		                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                                    .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                                .addGroup(layout.createSequentialGroup()
		                                                    .addComponent(jLabel19)
		                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                                    .addComponent(cmbPropietarioSinonimo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
		                                    .addGap(49, 49, 49)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addGroup(layout.createSequentialGroup()
		                                            .addComponent(jLabel21)
		                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                            .addComponent(chkHabilitado))
		                                        .addGroup(layout.createSequentialGroup()
		                                            .addGap(13, 13, 13)
		                                            .addComponent(jLabel6)
		                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                            .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                                .addGroup(layout.createSequentialGroup()
		                                    .addComponent(jLabel3)
		                                    .addGap(1175, 1175, 1175))
		                                .addGroup(layout.createSequentialGroup()
		                                    .addComponent(jLabel1)
		                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                    .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE))))))
		                .addGap(40, 40, 40))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(434, 434, 434))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(10, 10, 10)
		                .addComponent(jLabel14)
		                .addGap(28, 28, 28)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel1))
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(txtTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel3))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel16))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel27)))
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel6))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel21)
		                                    .addComponent(chkHabilitado))))
		                        .addGap(53, 53, 53)
		                        .addComponent(jLabel11)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel4))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(cmbPropietarioSinonimo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel19))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel20))
		                        .addGap(27, 27, 27)
		                        .addComponent(btnBuscar)))
		                .addGap(30, 30, 30)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(btnCancelar)
		                    .addComponent(btnProcesar)
		                    .addComponent(btnModificacion)
		                    .addComponent(btnAlta))
		                .addGap(40, 40, 40))
		        );
		 }
	
		 @Override
		 protected void initEvents() {
			 PantallaDetallePermisosPorColumnaListener actioListener = new PantallaDetallePermisosPorColumnaListener(this);
			 
			 btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_BUSCAR);
			 btnAlta.setActionCommand(MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_ALTA);
			 btnModificacion.setActionCommand(MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_MODIFICACION);
			 btnProcesar.setActionCommand(MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_INFORME);
			 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_DETALLE_PERMISOS_POR_COLUMNA_CANCELAR);
			 
			 btnBuscar.addActionListener(actioListener);
			 btnAlta.addActionListener(actioListener);
			 btnModificacion.addActionListener(actioListener);
			 btnProcesar.addActionListener(actioListener);
			 btnCancelar.addActionListener(actioListener);
		 }
		 
		 @SuppressWarnings("unchecked")
		 @Override
		 protected void initModels() {
			 
		 }
		 
		 @Override
		 protected void initialState() {
			 
		 }
		 
		 @Override
		 protected void setupLiterals() {
			 setTitle(literales.getLiteral("PantallaDetallePermisosPorColumna.title"));

			 jLabel1.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label1"));
			 jLabel3.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label3"));
			 jLabel6.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label6"));
			 btnBuscar.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.btnBuscar"));
			 jLabel11.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label11"));
			 btnProcesar.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.btnProcesar"));
			 btnCancelar.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.btnCancelar"));
			 jLabel16.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label16"));
			 jLabel19.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label19"));
			 jLabel20.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label20"));
			 jLabel21.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label21"));
			 jLabel27.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label27"));
			 jLabel4.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.label4"));
			 btnAlta.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.btnAlta"));
			 btnModificacion.setText(literales.getLiteral("PantallaDetallePermisosPorColumna.btnModificacion"));
		 }
		 
		 /**
			 * 
			 */
		public void enableButtons(Boolean val) {
			btnBuscar.setEnabled(val);
			btnAlta.setEnabled(val);
			btnModificacion.setEnabled(val);
			btnProcesar.setEnabled(val);
			btnCancelar.setEnabled(val);
		}   
}
