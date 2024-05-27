/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import com.mdsql.ui.listener.PantallaMantenimientoPermisosPorObjetoListener;
import com.mdsql.ui.listener.combo.MantenimientoPermisosPorObjetoPermisoSinonimoItemListener;
import com.mdsql.ui.listener.combo.MantenimientoPermisosPorObjetoTipoObjetoItemListener;
import com.mdsql.ui.model.PermisoSinonimoComboBoxModel;
import com.mdsql.ui.model.SiNoComboBoxModel;
import com.mdsql.ui.model.VigenteHistoricoComboBoxModel;
import com.mdsql.ui.renderer.CmbStringRenderer;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author federico
 */
public class PantallaMantenimientoPermisosPorObjeto extends DialogSupport {

	private static final long serialVersionUID = 1L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;

    @Getter
    private javax.swing.JTextField txtFechaAlta;

    @Getter
    private javax.swing.JTextField txtFechaModificacion;

    @Getter
    private javax.swing.JTextField txtFuncionNombre;

    @Getter
    private javax.swing.JTextField txtModelo1;

    @Getter
    private javax.swing.JTextField txtModeloProyecto;

    @Getter
    private javax.swing.JTextArea txtNombreObjeto;

    @Getter
    private javax.swing.JTextField txtPeticion;

    @Getter
    private javax.swing.JTextField txtUsuarioAlta;

    @Getter
    private javax.swing.JTextField txtUsuarioModificacion;

    @Getter
    private javax.swing.JButton btnCancelar;

    @Getter
    private javax.swing.JButton btnGuardar;

    @Getter
    private javax.swing.JCheckBox chkHabilitado;

    @Getter
    private javax.swing.JComboBox<String> cmbEntorno;

    @Getter
    private javax.swing.JComboBox<String> cmbIncluirPDC;

    @Getter
    private javax.swing.JComboBox<String> cmbPermiso;

    @Getter
    private javax.swing.JComboBox<String> cmbPermisoSinonimo;

    @Getter
    private javax.swing.JComboBox<String> cmbPropietarioSinonimo;

    @Getter
    private javax.swing.JComboBox<String> cmbPropietarioSinonimo1;

    @Getter
    private javax.swing.JComboBox<String> cmbTipoObjeto;

    @Getter
    private javax.swing.JComboBox<String> cmbWithGrantOpcion;
    // End of variables declaration//GEN-END:variables
	
    public PantallaMantenimientoPermisosPorObjeto(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaMantenimientoPermisosPorObjeto(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	
	 @Override
	 protected void setupComponents() {
		 	jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        txtModeloProyecto = new javax.swing.JTextField();
	        cmbPermisoSinonimo = new javax.swing.JComboBox<>();
	        txtPeticion = new javax.swing.JTextField();
	        cmbEntorno = new javax.swing.JComboBox<>();
	        btnGuardar = new javax.swing.JButton();
	        btnCancelar = new javax.swing.JButton();
	        jLabel14 = new javax.swing.JLabel();
	        txtModelo1 = new javax.swing.JTextField();
	        jLabel15 = new javax.swing.JLabel();
	        cmbTipoObjeto = new javax.swing.JComboBox<>();
	        jLabel16 = new javax.swing.JLabel();
	        cmbPermiso = new javax.swing.JComboBox<>();
	        jLabel17 = new javax.swing.JLabel();
	        cmbIncluirPDC = new javax.swing.JComboBox<>();
	        jLabel18 = new javax.swing.JLabel();
	        txtFuncionNombre = new javax.swing.JTextField();
	        jLabel19 = new javax.swing.JLabel();
	        cmbPropietarioSinonimo1 = new javax.swing.JComboBox<>();
	        jLabel20 = new javax.swing.JLabel();
	        cmbWithGrantOpcion = new javax.swing.JComboBox<>();
	        jLabel21 = new javax.swing.JLabel();
	        chkHabilitado = new javax.swing.JCheckBox();
	        jLabel22 = new javax.swing.JLabel();
	        txtUsuarioAlta = new javax.swing.JTextField();
	        jLabel23 = new javax.swing.JLabel();
	        txtFechaAlta = new javax.swing.JTextField();
	        jLabel24 = new javax.swing.JLabel();
	        txtUsuarioModificacion = new javax.swing.JTextField();
	        jLabel25 = new javax.swing.JLabel();
	        txtFechaModificacion = new javax.swing.JTextField();
	        jLabel27 = new javax.swing.JLabel();
	        cmbPropietarioSinonimo = new javax.swing.JComboBox<>();
	        jLabel4 = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        txtNombreObjeto = new javax.swing.JTextArea();
	        
	        setBounds(1366, 768);
	        
	        jScrollPane1.setViewportView(txtNombreObjeto);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(37, 37, 37)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel14)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addGap(107, 107, 107)
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                .addComponent(txtPeticion)
	                                                .addComponent(cmbPermisoSinonimo, 0, 301, Short.MAX_VALUE)))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel6)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel15)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel16)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                                .addComponent(jLabel18)
	                                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                                .addComponent(jLabel27)
	                                                .addComponent(jLabel19)
	                                                .addComponent(jLabel21))
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                .addComponent(cmbPropietarioSinonimo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(chkHabilitado))))
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel25)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtFechaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel24)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addComponent(jLabel23)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
	                                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                                .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                        .addComponent(jLabel2)
	                                        .addComponent(jLabel1)
	                                        .addComponent(jLabel3)
	                                        .addComponent(jLabel4))
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                            .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                        .addComponent(jScrollPane1))))))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(533, 533, 533)
	                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(40, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(22, 22, 22)
	                .addComponent(jLabel14)
	                .addGap(41, 41, 41)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel4)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel2)
	                                    .addComponent(jLabel17))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel3)
	                                    .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel18))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(cmbPropietarioSinonimo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addComponent(jLabel19))
	                                        .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(jLabel15)
	                                        .addGap(4, 4, 4)))
	                                .addGap(12, 12, 12)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel16)
	                                    .addComponent(jLabel27)
	                                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                            .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addComponent(jLabel6))
	                            .addComponent(jLabel21)
	                            .addComponent(chkHabilitado)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel20))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel22)
	                            .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(10, 10, 10)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel23)
	                            .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(10, 10, 10)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel24)
	                            .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jLabel25)
	                            .addComponent(txtFechaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(43, 43, 43)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnGuardar)
	                    .addComponent(btnCancelar))
	                .addContainerGap(195, Short.MAX_VALUE))
	        );
	 }
	
	 @Override
	 protected void initEvents() {
		 PantallaMantenimientoPermisosPorObjetoListener actionListener = new PantallaMantenimientoPermisosPorObjetoListener(this);
		 MantenimientoPermisosPorObjetoTipoObjetoItemListener cmbTipoObjetoListener = new MantenimientoPermisosPorObjetoTipoObjetoItemListener(this);
		 MantenimientoPermisosPorObjetoPermisoSinonimoItemListener cmbPermisoSinonimoListener = new MantenimientoPermisosPorObjetoPermisoSinonimoItemListener(this);

		 btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_GUARDAR);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_MANTENIMIENTO_PERMISOS_POR_OBJETO_CANCELAR);
		 
		 btnGuardar.addActionListener(actionListener);
		 btnCancelar.addActionListener(actionListener);

		 cmbTipoObjeto.addItemListener(cmbTipoObjetoListener);
		 cmbPermisoSinonimo.addItemListener(cmbPermisoSinonimoListener);

		 this.addOnLoadListener(actionListener);
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 protected void initModels() {
		 SiNoComboBoxModel siNoComboBoxModel = new SiNoComboBoxModel();
		 PermisoSinonimoComboBoxModel permisoSinonimoComboBoxModel = new PermisoSinonimoComboBoxModel();
		 VigenteHistoricoComboBoxModel vigenteHistoricoComboBoxModel = new VigenteHistoricoComboBoxModel();

		 cmbWithGrantOpcion.setModel(siNoComboBoxModel);
		 cmbWithGrantOpcion.setRenderer(new CmbStringRenderer());
		 cmbPermisoSinonimo.setModel(permisoSinonimoComboBoxModel);
		 cmbPermisoSinonimo.setRenderer(new CmbStringRenderer());
		 cmbIncluirPDC.setModel(siNoComboBoxModel);
		 cmbIncluirPDC.setRenderer(new CmbStringRenderer());
		 cmbEntorno.setModel(vigenteHistoricoComboBoxModel);
		 cmbEntorno.setRenderer(new CmbStringRenderer());
	 }
	 
	 @Override
	 protected void initialState() {
		 
	 }
	 
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.titulo"));

		 jLabel1.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel1"));
		 jLabel2.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel2"));
		 jLabel3.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel3"));
		 btnGuardar.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.btnGuardar"));
		 btnCancelar.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.btnCancelar"));
		 jLabel15.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel15"));
		 jLabel16.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel16"));
		 jLabel17.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel17"));
		 jLabel18.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel18"));
		 jLabel19.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel19"));
		 jLabel20.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel20"));
		 jLabel21.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel21"));
		 jLabel22.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel22"));
		 jLabel23.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel23"));
		 jLabel24.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel24"));
		 jLabel25.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel25"));
		 jLabel4.setText(literales.getLiteral("PantallaMantenimientoPermisosPorObjeto.jLabel4"));
	 }
	 
	/**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnGuardar.setEnabled(val);
		btnCancelar.setEnabled(val);
	} 
}
