/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.listener.PantallaMantenimientoHistoricoListener;
import com.mdsql.ui.listener.tables.HistoricoTableListener;
import com.mdsql.ui.model.HistoricoTableModel;
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
public class PantallaMantenimientoHistorico extends DialogSupport {

	private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    
    @Getter
    private TableSupport tblMantenimientoHistorico;
    
    @Getter
    private javax.swing.JTextField txtModelo;
    
    @Getter
    private javax.swing.JButton btnAlta;
    
    @Getter
    private javax.swing.JButton btnBaja;
    
    @Getter
    private javax.swing.JButton btnBuscar;
    
    @Getter
    private javax.swing.JButton btnBuscarModelo;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnInforme;
    
    @Getter
    private javax.swing.JComboBox<String> cmbTipoObjeto;
    // End of variables declaration//GEN-END:variables

	@Getter
	@Setter
	private Modelo modeloSeleccionado;

	@Getter
	@Setter
	private Historico seleccionado;
	
    public PantallaMantenimientoHistorico(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaMantenimientoHistorico(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 	jLabel3 = new javax.swing.JLabel();
		 	txtModelo = new javax.swing.JTextField();
	        btnInforme = new javax.swing.JButton();
	        btnCancelar = new javax.swing.JButton();
	        jLabel15 = new javax.swing.JLabel();
	        cmbTipoObjeto = new javax.swing.JComboBox<>();
	        btnBuscarModelo = new javax.swing.JButton();
	        btnBuscar = new javax.swing.JButton();
	        btnAlta = new javax.swing.JButton();
	        btnBaja = new javax.swing.JButton();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        tblMantenimientoHistorico = new TableSupport();
	        
	        setBounds(1366, 768);
	        
	        jScrollPane1.setViewportView(tblMantenimientoHistorico);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(153, 153, 153)
	                        .addComponent(jLabel3)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(73, 73, 73)
	                        .addComponent(jLabel15)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(585, 585, 585)
	                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(397, 397, 397)
	                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(btnInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1361, Short.MAX_VALUE)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jLabel15)
	                        .addGap(4, 4, 4))
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel3)
	                        .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(32, 32, 32)
	                .addComponent(btnBuscar)
	                .addGap(18, 18, 18)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnInforme)
	                    .addComponent(btnCancelar)
	                    .addComponent(btnAlta)
	                    .addComponent(btnBaja))
	                .addGap(45, 45, 45))
	        );

	 }
	
	 @Override
	 protected void initEvents() {
		 PantallaMantenimientoHistoricoListener actionListener = new PantallaMantenimientoHistoricoListener(this);
		 ListSelectionListener listSelectionListener = new HistoricoTableListener(this);
		 
		 btnBuscarModelo.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR_MODELO);
		 btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BUSCAR);
		 btnAlta.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_ALTA);
		 btnBaja.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_BAJA);
		 btnInforme.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_INFORME);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_HISTORICO_CANCELAR);
		 
		 btnBuscarModelo.addActionListener(actionListener);
		 btnBuscar.addActionListener(actionListener);
		 btnAlta.addActionListener(actionListener);
		 btnBaja.addActionListener(actionListener);
		 btnInforme.addActionListener(actionListener);
		 btnCancelar.addActionListener(actionListener);

		 ListSelectionModel rowPM = tblMantenimientoHistorico.getSelectionModel();
		 rowPM.addListSelectionListener(listSelectionListener);

		 this.addOnLoadListener(actionListener);
	 }
	
	 @Override
	 protected void initModels() {
		 Cabecera cabeceraHistorico = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.MNTO_HISTORICO_TABLA_CABECERA);
		 tblMantenimientoHistorico.initModel(
				 new HistoricoTableModel(cabeceraHistorico));
	 }
	 
	 @Override
	 protected void initialState() {
		btnBaja.setEnabled(Boolean.FALSE);
	 }
	 
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaMantenimientoHistorico.titulo"));

		 jLabel3.setText(literales.getLiteral("PantallaMantenimientoHistorico.jLabel3"));
		 btnBuscar.setText(literales.getLiteral("PantallaMantenimientoHistorico.btnBuscar"));
		 btnInforme.setText(literales.getLiteral("PantallaMantenimientoHistorico.btnInforme"));
		 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoHistorico.btnCancelar"));
		 jLabel15.setText(literales.getLiteral("PantallaMantenimientoHistorico.jLabel15"));
		 btnAlta.setText(literales.getLiteral("PantallaMantenimientoHistorico.btnAlta"));
		 btnBaja.setText(literales.getLiteral("PantallaMantenimientoHistorico.btnBaja"));

		 btnBuscarModelo.setIcon(new ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N

	 }
	
	 /**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnBuscarModelo.setEnabled(val);
		btnBuscar.setEnabled(val);
		btnAlta.setEnabled(val);
		btnBaja.setEnabled(val);
		btnInforme.setEnabled(val);
		btnCancelar.setEnabled(val);
	}
}