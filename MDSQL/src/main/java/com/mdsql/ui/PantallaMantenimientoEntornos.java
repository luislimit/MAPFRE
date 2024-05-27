/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaMantenimientoEntornosListener;
import com.mdsql.ui.listener.tables.EntornosTableListener;
import com.mdsql.ui.model.EntornoTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import com.mdval.ui.utils.TableSupport;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author federico
 */
public class PantallaMantenimientoEntornos extends DialogSupport {
	
	private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    
    @Getter
    private TableSupport tblMantenimientoEntornos;
    
    @Getter
    private javax.swing.JTextField txtBBDD;
    
    @Getter
    private javax.swing.JTextField txtComentario;
    
    @Getter
    private javax.swing.JTextField txtEsquema;
    
    @Getter
    private javax.swing.JTextField txtPassword;
    
    @Getter
    private javax.swing.JButton btnBuscar;
    
    @Getter
    private javax.swing.JButton btnCancelar;
    
    @Getter
    private javax.swing.JButton btnGrabar;
    
    @Getter
    private javax.swing.JCheckBox chkHabilitada;
    // End of variables declaration//GEN-END:variables
	
    public PantallaMantenimientoEntornos(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaMantenimientoEntornos(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	
	 @Override
	 protected void setupComponents() {
		 jLabel3 = new javax.swing.JLabel();
		 txtBBDD = new javax.swing.JTextField();
		 btnGrabar = new javax.swing.JButton();
		 btnCancelar = new javax.swing.JButton();
		 btnBuscar = new javax.swing.JButton();
		 jScrollPane1 = new javax.swing.JScrollPane();
		 tblMantenimientoEntornos = new TableSupport();
		 jLabel4 = new javax.swing.JLabel();
		 txtEsquema = new javax.swing.JTextField();
		 jLabel5 = new javax.swing.JLabel();
		 txtPassword = new javax.swing.JTextField();
		 chkHabilitada = new javax.swing.JCheckBox();
		 jLabel6 = new javax.swing.JLabel();
		 txtComentario = new javax.swing.JTextField();

		 this.setBounds(1108, 766);


		 jScrollPane1.setViewportView(tblMantenimientoEntornos);

		 chkHabilitada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

		 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jScrollPane1)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jLabel4)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(txtEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jLabel5)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(chkHabilitada))
	                                    .addComponent(txtComentario)))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(388, 388, 388)
	                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(400, 400, 400)
	                                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addGap(0, 0, Short.MAX_VALUE)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3)
	                    .addComponent(jLabel4)
	                    .addComponent(txtEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5)
	                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(chkHabilitada))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6)
	                    .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnBuscar)
	                .addGap(18, 18, 18)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnGrabar)
	                    .addComponent(btnCancelar))
	                .addContainerGap())
	        );
	 }
	
	 @Override
	 protected void initEvents() {
		 PantallaMantenimientoEntornosListener actioListener = new PantallaMantenimientoEntornosListener(this);
		 ListSelectionListener listSelectionListener = new EntornosTableListener(this);
		 
		 btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR);
		 btnGrabar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR);
		 
		 btnBuscar.addActionListener(actioListener);
		 btnGrabar.addActionListener(actioListener);
		 btnCancelar.addActionListener(actioListener);

		 ListSelectionModel rowPM = tblMantenimientoEntornos.getSelectionModel();
		 rowPM.addListSelectionListener(listSelectionListener);
	 }
	 
	 @Override
	 protected void initModels() {
		 Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.MNTO_ENTORNOS_TABLA_CABECERA);
		 tblMantenimientoEntornos.initModel(
				 new EntornoTableModel(cabecera));
	 }
	 
	 @Override
	 protected void initialState() {
		btnGrabar.setEnabled(Boolean.TRUE);
	 }
	 
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaMantenimientoEntornos.titulo"));

		 jLabel3.setText(literales.getLiteral("PantallaMantenimientoEntornos.jLabel3"));
		 btnGrabar.setText(literales.getLiteral("PantallaMantenimientoEntornos.btnGrabar"));
		 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoEntornos.btnCancelar"));
		 btnBuscar.setText(literales.getLiteral("PantallaMantenimientoEntornos.btnBuscar"));
		 jLabel4.setText(literales.getLiteral("PantallaMantenimientoEntornos.jLabel4"));
		 jLabel5.setText(literales.getLiteral("PantallaMantenimientoEntornos.jLabel5"));
		 chkHabilitada.setText(literales.getLiteral("PantallaMantenimientoEntornos.chkHabilitada"));
		 jLabel6.setText(literales.getLiteral("PantallaMantenimientoEntornos.jLabel6"));
	 
	 }
	 
	 /**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnBuscar.setEnabled(val);
		btnGrabar.setEnabled(val);
		btnCancelar.setEnabled(val);
	}
}
