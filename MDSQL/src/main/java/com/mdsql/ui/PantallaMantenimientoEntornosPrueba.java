/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.mdsql.ui.listener.PantallaMantenimientoEntornosPruebaListener;
import com.mdsql.ui.listener.tables.EntornosPruebaTableListener;
import com.mdsql.ui.model.EntornosPruebaTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;

/**
 *
 * @author federico
 */
public class PantallaMantenimientoEntornosPrueba extends DialogSupport {

	 	private static final long serialVersionUID = 1L;

	 	// Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel13;
	    private javax.swing.JLabel jLabel14;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel7;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JScrollPane jScrollPane2;
	    
	    @Getter
	    private TableSupport tblMantenimientoEntornosPrueba;
	    
	    @Getter
	    private javax.swing.JTextField txtBBDD;
	    
	    @Getter
	    private javax.swing.JTextArea txtDescripcion;
	    
	    @Getter
	    private javax.swing.JTextField txtEsquema;
	    
	    @Getter
	    private javax.swing.JTextField txtGradoparal;
	    
	    @Getter
	    private javax.swing.JTextField txtNombreEntorno;
	    
	    @Getter
	    private javax.swing.JTextField txtTablespace;
	    
	    @Getter
	    private javax.swing.JButton btnCancelar;
	    
	    @Getter
	    private javax.swing.JButton btnGuardar;
	    
	    @Getter
	    private javax.swing.JCheckBox chkHabilitada;
	    // End of variables declaration//GEN-END:variables

	    public PantallaMantenimientoEntornosPrueba(FrameSupport parent, Boolean modal) {
			 super(parent, modal);
		 }

		public PantallaMantenimientoEntornosPrueba(FrameSupport parent, Boolean modal, Map<String, Object> params) {
			super(parent, modal, params);
		}
		 
		@Override
		protected void setupComponents() {
			jLabel3 = new javax.swing.JLabel();
	        txtNombreEntorno = new javax.swing.JTextField();
	        btnCancelar = new javax.swing.JButton();
	        jLabel14 = new javax.swing.JLabel();
	        btnGuardar = new javax.swing.JButton();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        tblMantenimientoEntornosPrueba = new TableSupport();
	        chkHabilitada = new javax.swing.JCheckBox();
	        jLabel5 = new javax.swing.JLabel();
	        txtEsquema = new javax.swing.JTextField();
	        jLabel6 = new javax.swing.JLabel();
	        txtTablespace = new javax.swing.JTextField();
	        jLabel7 = new javax.swing.JLabel();
	        txtBBDD = new javax.swing.JTextField();
	        jLabel13 = new javax.swing.JLabel();
	        txtGradoparal = new javax.swing.JTextField();
	        jScrollPane2 = new javax.swing.JScrollPane();
	        txtDescripcion = new javax.swing.JTextArea();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        
	        setBounds(1366, 768);
	        
	        jScrollPane1.setViewportView(tblMantenimientoEntornosPrueba);
	        
	        txtDescripcion.setColumns(20);
	        txtDescripcion.setRows(5);
	        jScrollPane2.setViewportView(txtDescripcion);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(396, 396, 396)
	                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel2))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(334, 334, 334))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel6)
	                                .addGap(44, 44, 44)
	                                .addComponent(txtTablespace, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jLabel13)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(txtGradoparal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(305, 305, 305)))
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jScrollPane1)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(jLabel3)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(jLabel14)
	                                            .addComponent(txtNombreEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                    .addComponent(jLabel1)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(jLabel7)
	                                            .addGap(71, 71, 71)
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                                .addGroup(layout.createSequentialGroup()
	                                                    .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                                    .addComponent(jLabel5)
	                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                    .addComponent(txtEsquema))
	                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                        .addComponent(chkHabilitada)))
	                                .addGap(0, 0, Short.MAX_VALUE)))
	                        .addContainerGap())))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel14)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtNombreEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(txtEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel1)
	                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtTablespace, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6)
	                    .addComponent(jLabel13)
	                    .addComponent(txtGradoparal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(chkHabilitada))
	                .addGap(18, 18, 18)
	                .addComponent(btnGuardar)
	                .addGap(14, 14, 14)
	                .addComponent(jLabel2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnCancelar)
	                .addContainerGap())
	        );
		}
	    
		@Override
		protected void initEvents() {
			PantallaMantenimientoEntornosPruebaListener actionListener = new PantallaMantenimientoEntornosPruebaListener(this);
			ListSelectionListener listSelectionListener = new EntornosPruebaTableListener(this);
			
			btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_GUARDAR);
			btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_CANCELAR);
			
			btnGuardar.addActionListener(actionListener);
			btnCancelar.addActionListener(actionListener);
			
			ListSelectionModel rowPM = tblMantenimientoEntornosPrueba.getSelectionModel();
			rowPM.addListSelectionListener(listSelectionListener);
			
			this.addOnLoadListener(actionListener);
		}
	    
		@Override
		protected void initModels() {
			Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.MNTO_ENTORNOS_PRUEBA_TABLA_CABECERA);
			tblMantenimientoEntornosPrueba.initModel(new EntornosPruebaTableModel(cabecera));
		}
	    
		@Override
		protected void initialState() {
			btnGuardar.setEnabled(Boolean.FALSE);
		}
	    
		@Override
		protected void setupLiterals() {
			 setTitle(literales.getLiteral("PantallaMantenimientoEntornosPrueba.titulo"));

			 jLabel3.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel3"));
			 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.btnCancelar"));
			 btnGuardar.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.btnGuardar"));
			 chkHabilitada.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.chkHabilitada"));
			 jLabel5.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel5"));
			 jLabel6.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel6"));
			 jLabel7.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel7"));
			 jLabel13.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel13"));
			 jLabel1.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel1"));
			 jLabel2.setText(literales.getLiteral("PantallaMantenimientoEntornosPrueba.jLabel2"));
		}
	    
		/**
			 * 
			 */
		public void enableButtons(Boolean val) {
			btnGuardar.setEnabled(val);
			btnCancelar.setEnabled(val);
		}
}
