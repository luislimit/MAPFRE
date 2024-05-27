/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.listener.PantallaMantenimientoVariablesListener;
import com.mdsql.ui.listener.tables.VariablesTableListener;
import com.mdsql.ui.model.SiNoComboBoxModel;
import com.mdsql.ui.model.VariableTableModel;
import com.mdsql.ui.model.VigenteHistoricoComboBoxModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.LiteralesSingleton;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import com.mdval.ui.utils.TableSupport;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author federico
 */
public class PantallaMantenimientoVariables extends DialogSupport {

	 	private static final long serialVersionUID = 1L;
	 
	 	// Variables declaration - do not modify//GEN-BEGIN:variables
	    private javax.swing.JLabel jLabel10;
	    private javax.swing.JLabel jLabel11;
	    private javax.swing.JLabel jLabel12;
	    private javax.swing.JLabel jLabel13;
	    private javax.swing.JLabel jLabel15;
	    private javax.swing.JLabel jLabel16;
	    private javax.swing.JLabel jLabel17;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel7;
	    private javax.swing.JLabel jLabel8;
	    private javax.swing.JLabel jLabel9;
	    private javax.swing.JScrollPane jScrollPane1;
	    
	    @Getter
	    private TableSupport tblVariables;
	    
	    @Getter
	    private javax.swing.JTextField txtBBDD;
	    
	    @Getter
	    private javax.swing.JTextField txtCodigoVariable;
	    
	    @Getter
	    private javax.swing.JTextField txtComentario;
	    
	    @Getter
	    private javax.swing.JTextField txtFechaAlta;
	    
	    @Getter
	    private javax.swing.JTextField txtFechaModificacion;
	    
	    @Getter
	    private javax.swing.JTextField txtModeloProyecto;
	    
	    @Getter
	    private javax.swing.JTextField txtCodigoProyecto;
	    
	    @Getter
	    private javax.swing.JTextField txtPeticion;
	    
	    @Getter
	    private javax.swing.JTextField txtUsuarioAlta;
	    
	    @Getter
	    private javax.swing.JTextField txtUsuarioModificacion;
	    
	    @Getter
	    private javax.swing.JTextField txtValorVariable;
	    
	    @Getter
	    private javax.swing.JTextField txtValorVariableSustituir;
	    
	    @Getter
	    private javax.swing.JButton btnCancelar;
	    
	    @Getter
	    private javax.swing.JButton btnGuardar;
	    
	    @Getter
	    private javax.swing.JCheckBox chkHabilitada;
	    
	    @Getter
	    private javax.swing.JCheckBox chkUsoPermisos;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbEntorno;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbTipoVariable;
	    
	    @Getter
	    private javax.swing.JComboBox<String> cmbUsoInterno;
	    // End of variables declaration//GEN-END:variables

		@Getter
		@Setter
		private Modelo modelo;

	    public PantallaMantenimientoVariables(FrameSupport parent, Boolean modal) {
			 super(parent, modal);
		 }

		 public PantallaMantenimientoVariables(FrameSupport parent, Boolean modal, Map<String, Object> params) {
			super(parent, modal, params);
		 }
	 
		 @Override
		 protected void setupComponents() {
			 	jLabel3 = new javax.swing.JLabel();
		        txtModeloProyecto = new javax.swing.JTextField();
		        btnCancelar = new javax.swing.JButton();
		        jLabel15 = new javax.swing.JLabel();
		        cmbTipoVariable = new javax.swing.JComboBox<>();
		        btnGuardar = new javax.swing.JButton();
		        jScrollPane1 = new javax.swing.JScrollPane();
		        tblVariables = new TableSupport();
		        jLabel4 = new javax.swing.JLabel();
		        txtCodigoVariable = new javax.swing.JTextField();
		        chkHabilitada = new javax.swing.JCheckBox();
		        txtCodigoProyecto = new javax.swing.JTextField();
		        jLabel10 = new javax.swing.JLabel();
		        txtPeticion = new javax.swing.JTextField();
		        jLabel16 = new javax.swing.JLabel();
		        cmbEntorno = new javax.swing.JComboBox<>();
		        jLabel5 = new javax.swing.JLabel();
		        txtValorVariable = new javax.swing.JTextField();
		        jLabel6 = new javax.swing.JLabel();
		        txtValorVariableSustituir = new javax.swing.JTextField();
		        jLabel7 = new javax.swing.JLabel();
		        txtBBDD = new javax.swing.JTextField();
		        chkUsoPermisos = new javax.swing.JCheckBox();
		        jLabel17 = new javax.swing.JLabel();
		        cmbUsoInterno = new javax.swing.JComboBox<>();
		        jLabel8 = new javax.swing.JLabel();
		        txtUsuarioAlta = new javax.swing.JTextField();
		        jLabel9 = new javax.swing.JLabel();
		        txtFechaAlta = new javax.swing.JTextField();
		        jLabel11 = new javax.swing.JLabel();
		        txtUsuarioModificacion = new javax.swing.JTextField();
		        jLabel12 = new javax.swing.JLabel();
		        txtFechaModificacion = new javax.swing.JTextField();
		        jLabel13 = new javax.swing.JLabel();
		        txtComentario = new javax.swing.JTextField();
		        
		        setBounds(1366, 768);
		        
		        jScrollPane1.setViewportView(tblVariables);
		        
		        chkHabilitada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		        chkHabilitada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
		        
		        chkUsoPermisos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		        chkUsoPermisos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(566, 566, 566)
		                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(0, 0, Short.MAX_VALUE))
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(583, 583, 583)
		                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(0, 0, Short.MAX_VALUE))
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                                .addComponent(jLabel3)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(txtCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(18, 18, 18)
		                                .addComponent(txtModeloProyecto))
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                                .addGap(852, 852, 852)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addComponent(cmbUsoInterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                    .addComponent(txtFechaModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(txtComentario)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(txtCodigoVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(cmbTipoVariable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(cmbEntorno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addGroup(layout.createSequentialGroup()
		                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                            .addComponent(chkHabilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                            .addComponent(chkUsoPermisos))
		                                        .addGap(0, 0, Short.MAX_VALUE))
		                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
		                                                .addComponent(txtValorVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
		                                                .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
		                                                .addComponent(txtValorVariableSustituir, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		                                        .addGap(319, 319, 319)))))))
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(jLabel3)
		                        .addComponent(txtCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(cmbUsoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(jLabel11)
		                            .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(jLabel12)
		                            .addComponent(txtFechaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(txtValorVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel5)
		                                    .addComponent(jLabel17))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(txtValorVariableSustituir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel6)
		                                    .addComponent(jLabel8)))
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(jLabel4)
		                                    .addComponent(txtCodigoVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(jLabel10)
		                                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(cmbTipoVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel15)
		                                    .addComponent(jLabel7)
		                                    .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel9))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                    .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jLabel16)
		                                    .addComponent(chkHabilitada))))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(chkUsoPermisos)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel13))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnGuardar)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
		                .addGap(18, 18, 18)
		                .addComponent(btnCancelar)
		                .addContainerGap())
		        );
		 }
	 
		 @Override
		 protected void initEvents() {
			 PantallaMantenimientoVariablesListener actionListener = new PantallaMantenimientoVariablesListener(this);
			 ListSelectionListener listSelectionListener = new VariablesTableListener(this);

			 btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_VARIABLES_GUARDAR);
			 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_VARIABLES_CANCELAR);
			 
			 btnGuardar.addActionListener(actionListener);
			 btnCancelar.addActionListener(actionListener);

			 ListSelectionModel rowPM = tblVariables.getSelectionModel();
			 rowPM.addListSelectionListener(listSelectionListener);

			 this.addOnLoadListener(actionListener);
		 }
		 
		 @Override
		 protected void initModels() {
			 SiNoComboBoxModel siNoComboBoxModel = new SiNoComboBoxModel();
			 VigenteHistoricoComboBoxModel vigenteHistoricoComboBoxModel = new VigenteHistoricoComboBoxModel();

			 cmbEntorno.setModel(vigenteHistoricoComboBoxModel);
			 cmbUsoInterno.setModel(siNoComboBoxModel);

			 Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.MNTO_VARIABLES_TABLA_CABECERA);
			 tblVariables.initModel(
					 new VariableTableModel(cabecera));
		 }
		 
		 @Override
		 protected void initialState() {
			try {
				 LiteralesSingleton literales = LiteralesSingleton.getInstance();

				txtModeloProyecto.setEditable(Boolean.FALSE);
				txtCodigoProyecto.setEditable(Boolean.FALSE);
				txtUsuarioAlta.setEditable(Boolean.FALSE);
				txtFechaAlta.setEditable(Boolean.FALSE);
				txtUsuarioModificacion.setEditable(Boolean.FALSE);
				txtFechaModificacion.setEditable(Boolean.FALSE);

				MDSQLUIHelper.setSelectedItem(cmbUsoInterno, literales.getLiteral("no"));

				if (!Objects.isNull(modelo)) {
					txtCodigoProyecto.setText(modelo.getCodigoProyecto());
					txtModeloProyecto.setText(modelo.getNombreModelo());
				}
			} catch (IOException e) {}
		 }
		 
		 @Override
		 protected void setupLiterals() {
			 setTitle(literales.getLiteral("PantallaMantenimientoVariables.titulo"));

			 jLabel3.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel3"));
			 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoVariables.btnCancelar"));
			 jLabel15.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel15"));
			 btnGuardar.setText(literales.getLiteral("PantallaMantenimientoVariables.btnGuardar"));
			 jLabel4.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel4"));
			 chkHabilitada.setText(literales.getLiteral("PantallaMantenimientoVariables.chkHabilitada"));
			 jLabel10.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel10"));
			 jLabel16.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel16"));
			 jLabel5.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel5"));
			 jLabel6.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel6"));
			 jLabel7.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel7"));
			 chkUsoPermisos.setText(literales.getLiteral("PantallaMantenimientoVariables.chkUsoPermisos"));
			 jLabel17.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel17"));
			 jLabel8.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel8"));
			 jLabel9.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel9"));
			 jLabel11.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel11"));
			 jLabel12.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel12"));
			 jLabel13.setText(literales.getLiteral("PantallaMantenimientoVariables.jLabel13"));
		 }
	 
		 /**
			 * 
			 */
		public void enableButtons(Boolean val) {
			btnGuardar.setEnabled(val);
			btnCancelar.setEnabled(val);
		}
}
