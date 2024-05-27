/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.NivelImportancia;
import com.mdsql.ui.listener.PantallaMantenimientoNotasModelosListener;
import com.mdsql.ui.listener.tables.NotasModeloTableListener;
import com.mdsql.ui.model.NotasModeloTableModel;
import com.mdsql.ui.renderer.NivelImportanciaRenderer;
import com.mdsql.ui.utils.MDSQLUIHelper;
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
public class PantallaMantenimientoNotasModelos extends DialogSupport {
	
	private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private TableSupport tblNotasModelos;
    
    @Getter
    private javax.swing.JTextArea txtDescripcion;
    
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
    private javax.swing.JTextField txtTitulo;
    
    @Getter
    private javax.swing.JTextField txtUsuarioAlta;
    
    @Getter
    private javax.swing.JTextField txtUsuarioModificacion;
    
    @Getter
    private javax.swing.JButton btnBuscarModelo;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnGuardar;
    
    @Getter
    private javax.swing.JCheckBox chkHabilitada;
    
    @Getter
    private javax.swing.JComboBox<NivelImportancia> cmbImportancia;
    // End of variables declaration//GEN-END:variables

	@Getter
	@Setter
	private Modelo modeloSeleccionado;

	@Getter
	@Setter
	private Aviso avisoSeleccionado;
    
    public PantallaMantenimientoNotasModelos(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaMantenimientoNotasModelos(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 jLabel3 = new javax.swing.JLabel();
		 txtModeloProyecto = new javax.swing.JTextField();
		 btnCancelar = new javax.swing.JButton();
		 jLabel14 = new javax.swing.JLabel();
		 jLabel15 = new javax.swing.JLabel();
		 cmbImportancia = new javax.swing.JComboBox<>();
		 btnBuscarModelo = new javax.swing.JButton();
		 btnGuardar = new javax.swing.JButton();
		 jScrollPane1 = new javax.swing.JScrollPane();
		 tblNotasModelos = new TableSupport();
		 jLabel4 = new javax.swing.JLabel();
		 txtPeticion = new javax.swing.JTextField();
		 chkHabilitada = new javax.swing.JCheckBox();
		 txtCodigoProyecto = new javax.swing.JTextField();
		 jLabel5 = new javax.swing.JLabel();
		 txtTitulo = new javax.swing.JTextField();
		 jScrollPane2 = new javax.swing.JScrollPane();
		 txtDescripcion = new javax.swing.JTextArea();
		 jLabel1 = new javax.swing.JLabel();
		 jLabel6 = new javax.swing.JLabel();
		 txtUsuarioAlta = new javax.swing.JTextField();
		 jLabel7 = new javax.swing.JLabel();
		 txtFechaAlta = new javax.swing.JTextField();
		 jLabel8 = new javax.swing.JLabel();
		 txtUsuarioModificacion = new javax.swing.JTextField();
		 txtFechaModificacion = new javax.swing.JTextField();
		 jLabel9 = new javax.swing.JLabel();

		 this.setBounds(1370, 770);

		 jScrollPane1.setViewportView(tblNotasModelos);

		 chkHabilitada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

		 txtDescripcion.setColumns(20);
		 txtDescripcion.setRows(5);
		 jScrollPane2.setViewportView(txtDescripcion);

		 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(91, 91, 91)
	                        .addComponent(jLabel14))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                            .addComponent(jLabel3)
	                                            .addComponent(jLabel4)
	                                            .addComponent(jLabel1)
	                                            .addComponent(jLabel6))
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
	                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                        .addComponent(jLabel5)
	                                        .addGap(15, 15, 15)))
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(txtCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(txtModeloProyecto))
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addGroup(layout.createSequentialGroup()
	                                                .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(jLabel7)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(jLabel8)))
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                        .addComponent(jLabel9)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(txtFechaModificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(jLabel15)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(cmbImportancia, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                        .addComponent(chkHabilitada))
	                                    .addComponent(txtTitulo)
	                                    .addComponent(jScrollPane2)))
	                            .addComponent(jScrollPane1))))
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(492, 492, 492)
	                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(22, 22, 22)
	                .addComponent(jLabel14)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel3)
	                        .addComponent(txtCodigoProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(30, 30, 30)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbImportancia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel15)
	                    .addComponent(jLabel4)
	                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(chkHabilitada))
	                .addGap(27, 27, 27)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6)
	                    .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel7)
	                    .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8)
	                    .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel9)
	                    .addComponent(txtFechaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(29, 29, 29)
	                .addComponent(btnGuardar)
	                .addGap(18, 18, 18)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
	                .addGap(18, 18, 18)
	                .addComponent(btnCancelar)
	                .addContainerGap())
	        );
	 }
    
	 @Override
	 protected void initEvents() {
		 PantallaMantenimientoNotasModelosListener actionListener = new PantallaMantenimientoNotasModelosListener(this);
		 ListSelectionListener listSelectionListener = new NotasModeloTableListener(this);

		 btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_GUARDAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_CANCELAR);
		 btnBuscarModelo.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_NOTAS_MODELOS_BUSCAR_MODELO);
		 
		 btnGuardar.addActionListener(actionListener);
		 btnCancelar.addActionListener(actionListener);
		 btnBuscarModelo.addActionListener(actionListener);

		 ListSelectionModel rowPM = tblNotasModelos.getSelectionModel();
		 rowPM.addListSelectionListener(listSelectionListener);
		 
		 this.addOnLoadListener(actionListener);
	 }
    
	 @Override
	 protected void initModels() {
		cmbImportancia.setRenderer(new NivelImportanciaRenderer());

		 Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.NOTAS_MODELO_TABLA_CABECERA);
		 tblNotasModelos.initModel(
				 new NotasModeloTableModel(cabecera));
	 }
	 
	 @Override
	 protected void initialState() {
		txtModeloProyecto.setEditable(Boolean.FALSE);
		txtModeloProyecto.setEnabled(Boolean.FALSE);
		txtUsuarioAlta.setEditable(Boolean.FALSE);
		txtFechaAlta.setEditable(Boolean.FALSE);
		txtUsuarioModificacion.setEditable(Boolean.FALSE);
		txtFechaModificacion.setEditable(Boolean.FALSE);

		btnGuardar.setEnabled(Boolean.TRUE);
	 }
	 
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaMantenimientoNotasModelos.titulo"));

		 jLabel1.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel1"));
		 jLabel3.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel3"));
		 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.btnCancelar"));
		 jLabel15.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel15"));
		 btnGuardar.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.btnGuardar"));
		 jLabel4.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel4"));
		 chkHabilitada.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.chkHabilitada"));
		 jLabel5.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel5"));
		 jLabel6.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel6"));
		 jLabel7.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel7"));
		 jLabel8.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel8"));
		 jLabel9.setText(literales.getLiteral("PantallaMantenimientoNotasModelos.jLabel9"));

		 btnBuscarModelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
	 }
    
	 /**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnGuardar.setEnabled(val);
		btnCancelar.setEnabled(val);
	}
}
