/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;
import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Permiso;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.bussiness.entities.Sinonimo;
import com.mdsql.ui.listener.PantallaPermisosGeneralesporModeloporTipoObjetoListener;
import com.mdsql.ui.listener.combo.PermisosGeneralesPermisoSinonimoItemListener;
import com.mdsql.ui.listener.combo.PermisosGeneralesTipoObjetoItemListener;
import com.mdsql.ui.listener.tables.PermisosTableListener;
import com.mdsql.ui.listener.tables.SinonimosTableListener;
import com.mdsql.ui.model.PermisoSinonimoComboBoxModel;
import com.mdsql.ui.model.PermisosTableModel;
import com.mdsql.ui.model.SiNoComboBoxModel;
import com.mdsql.ui.model.SinonimosTableModel;
import com.mdsql.ui.model.VigenteHistoricoComboBoxModel;
import com.mdsql.ui.renderer.CmbStringRenderer;
import com.mdsql.ui.renderer.GrantRenderer;
import com.mdsql.ui.renderer.PropietarioRenderer;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author federico
 */
public class PantallaPermisosGeneralesporModeloporTipoObjeto extends DialogSupport {

	 private static final long serialVersionUID = 1L;
	
	 // Variables declaration - do not modify//GEN-BEGIN:variables
	 private javax.swing.JLabel jLabel1;
	 private javax.swing.JLabel jLabel11;
	 private javax.swing.JLabel jLabel12;
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
	 private javax.swing.JLabel jLabel6;
	 private javax.swing.JScrollPane jScrollPane2;
	 private javax.swing.JScrollPane jScrollPane3;
	 
	 @Getter
	 private TableSupport tblPermisos;
	 
	 @Getter
	 private TableSupport tblSinonimos;
	 
	 @Getter
	 private javax.swing.JTextField txtFechaAlta;
	 
	 @Getter
	 private javax.swing.JTextField txtFechaModificacion;
	 
	 @Getter
	 private javax.swing.JTextField txtFuncionNombre;
	 
	 @Getter
	 private javax.swing.JTextField txtCodigoModelo;
	 
	 @Getter
	 private javax.swing.JTextField txtDescripcionModelo;
	 
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
	 private javax.swing.JButton btnInforme;
	 
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
	 private javax.swing.JComboBox<Propietario> cmbPropietarioSinonimo;
	 
	 @Getter
	 private javax.swing.JComboBox<Grant> cmbReceptorPermiso;
	 
	 @Getter
	 private javax.swing.JComboBox<String> cmbTipoObjeto;
	 
	 @Getter
	 private javax.swing.JComboBox<String> cmbWithGrantOpcion;
	 //End of variables declaration//GEN-END:variables

	@Getter
	@Setter
	private Modelo modelo;

	@Getter
	@Setter
	private Permiso permisoSeleccionado;

	@Getter
	@Setter
	private Sinonimo sinonimoSeleccionado;

	 public PantallaPermisosGeneralesporModeloporTipoObjeto(FrameSupport parent, Boolean modal) {
		 super(parent, modal);
	 }

	 public PantallaPermisosGeneralesporModeloporTipoObjeto(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	 }
	 
	 @Override
	 protected void setupComponents() {
		 jLabel1 = new javax.swing.JLabel();
	     jLabel2 = new javax.swing.JLabel();
	     jLabel3 = new javax.swing.JLabel();
	     jLabel6 = new javax.swing.JLabel();
	     txtDescripcionModelo = new javax.swing.JTextField();
	     cmbPermisoSinonimo = new javax.swing.JComboBox<>();
	     txtPeticion = new javax.swing.JTextField();
	     cmbEntorno = new javax.swing.JComboBox<>();
	     btnGuardar = new javax.swing.JButton();
	     jLabel11 = new javax.swing.JLabel();
	     jScrollPane2 = new javax.swing.JScrollPane();
	     tblPermisos = new TableSupport();
	     jLabel12 = new javax.swing.JLabel();
	     jScrollPane3 = new javax.swing.JScrollPane();
	     tblSinonimos = new TableSupport();
	     btnInforme = new javax.swing.JButton();
	     btnCancelar = new javax.swing.JButton();
	     txtCodigoModelo = new javax.swing.JTextField();
	     jLabel15 = new javax.swing.JLabel();
	     cmbTipoObjeto = new javax.swing.JComboBox<>();
	     jLabel16 = new javax.swing.JLabel();
	     cmbPermiso = new javax.swing.JComboBox<>();
	     jLabel17 = new javax.swing.JLabel();
	     cmbIncluirPDC = new javax.swing.JComboBox<>();
	     jLabel18 = new javax.swing.JLabel();
	     txtFuncionNombre = new javax.swing.JTextField();
	     jLabel19 = new javax.swing.JLabel();
		 cmbReceptorPermiso = new javax.swing.JComboBox<>();
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
	     
	     setBounds(1452, 767);
	     
	     chkHabilitado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	     chkHabilitado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
	     
	     jScrollPane2.setViewportView(tblPermisos);
	     
	     jScrollPane3.setViewportView(tblSinonimos);
	     
	     javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(txtCodigoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(txtDescripcionModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(582, 582, 582)
	                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jScrollPane3)
	                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addGap(18, 18, 18)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                            .addGroup(layout.createSequentialGroup()
	                                                .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                                    .addGroup(layout.createSequentialGroup()
	                                                        .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                        .addGap(18, 18, 18)
	                                                        .addComponent(chkHabilitado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                                                    .addGroup(layout.createSequentialGroup()
	                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                            .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                            .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addComponent(cmbReceptorPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addGroup(layout.createSequentialGroup()
	                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addComponent(jLabel25)
	                                                    .addComponent(jLabel24))
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                    .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addComponent(txtFechaModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                                            .addGroup(layout.createSequentialGroup()
	                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGroup(layout.createSequentialGroup()
	                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addGap(0, 0, Short.MAX_VALUE))))
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(jLabel17)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addComponent(jLabel20)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                            .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(538, 538, 538)
	                        .addComponent(btnInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jLabel11)))
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel12)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtDescripcionModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(txtCodigoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel2)
	                    .addComponent(jLabel17)
	                    .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel20)
	                    .addComponent(cmbWithGrantOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(13, 13, 13)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3)
	                    .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel18)
	                    .addComponent(jLabel22)
	                    .addComponent(txtUsuarioAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel15)
	                    .addComponent(jLabel19)
	                    .addComponent(cmbReceptorPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel23)
	                    .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel16)
	                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel27)
	                    .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel24))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel6)
	                    .addComponent(chkHabilitado)
	                    .addComponent(txtFechaModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel25))
	                .addGap(18, 18, 18)
	                .addComponent(btnGuardar)
	                .addGap(18, 18, 18)
	                .addComponent(jLabel11)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jLabel12)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnCancelar)
	                    .addComponent(btnInforme))
	                .addContainerGap())
	        );
	 }
	 
	 @Override
	 protected void initEvents() {
		 PantallaPermisosGeneralesporModeloporTipoObjetoListener actionListener = new PantallaPermisosGeneralesporModeloporTipoObjetoListener(this);
		 PermisosGeneralesTipoObjetoItemListener cmbTipoObjetoListener = new PermisosGeneralesTipoObjetoItemListener(this);
		 PermisosGeneralesPermisoSinonimoItemListener cmbPermisoSinonimoListener = new PermisosGeneralesPermisoSinonimoItemListener(this);
		 ListSelectionListener permisosSelectionListener = new PermisosTableListener(this);
		 ListSelectionListener sinonimosSelectionListener = new SinonimosTableListener(this);

		 btnGuardar.setActionCommand(MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_GUARDAR);
		 btnInforme.setActionCommand(MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_INFORME);
		 btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_PERMISOS_GENERALES_POR_MODELO_POR_TIPO_OBJETO_CANCELAR);
		 
		 btnGuardar.addActionListener(actionListener);
		 btnInforme.addActionListener(actionListener);
		 btnCancelar.addActionListener(actionListener);

		 cmbTipoObjeto.addItemListener(cmbTipoObjetoListener);
		 cmbPermisoSinonimo.addItemListener(cmbPermisoSinonimoListener);

		 ListSelectionModel rowPM = tblPermisos.getSelectionModel();
		 rowPM.addListSelectionListener(permisosSelectionListener);

		 ListSelectionModel rowSM = tblSinonimos.getSelectionModel();
		 rowSM.addListSelectionListener(sinonimosSelectionListener);

		 this.addOnLoadListener(actionListener);
	 }
	 
	 @Override
	 protected void initModels() {
		 PermisoSinonimoComboBoxModel permisoSinonimoComboBoxModel = new PermisoSinonimoComboBoxModel();
		 VigenteHistoricoComboBoxModel vigenteHistoricoComboBoxModel = new VigenteHistoricoComboBoxModel();

		 cmbWithGrantOpcion.setModel(new SiNoComboBoxModel());
		 cmbWithGrantOpcion.setRenderer(new CmbStringRenderer());
		 cmbPermisoSinonimo.setModel(permisoSinonimoComboBoxModel);
		 cmbPermisoSinonimo.setRenderer(new CmbStringRenderer());
		 cmbIncluirPDC.setModel(new SiNoComboBoxModel());
		 cmbIncluirPDC.setRenderer(new CmbStringRenderer());
		 cmbEntorno.setModel(vigenteHistoricoComboBoxModel);
		 cmbEntorno.setRenderer(new CmbStringRenderer());
		 cmbReceptorPermiso.setRenderer(new GrantRenderer());
		 cmbPropietarioSinonimo.setRenderer(new PropietarioRenderer());

		 Cabecera cabeceraPermisos = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.PERMISOS_GENERALES_TABLA_CABECERA);
		 tblPermisos.initModel(
				 new PermisosTableModel(cabeceraPermisos));

		 Cabecera cabeceraSinonimos = MDSQLUIHelper
				 .createCabeceraTabla(MDSQLConstants.SINONIMOS_GENERALES_TABLA_CABECERA);
		 tblSinonimos.initModel(new SinonimosTableModel(cabeceraSinonimos));
	 }
	 
	 @Override
	 protected void initialState() {
		 txtDescripcionModelo.setEditable(Boolean.FALSE);
		 txtCodigoModelo.setEditable(Boolean.FALSE);
		 txtUsuarioAlta.setEditable(Boolean.FALSE);
		 txtFechaAlta.setEditable(Boolean.FALSE);
		 txtUsuarioModificacion.setEditable(Boolean.FALSE);
		 txtFechaModificacion.setEditable(Boolean.FALSE);

		 MDSQLUIHelper.setSelectedItem(cmbPermisoSinonimo, null);
		 MDSQLUIHelper.setSelectedItem(cmbWithGrantOpcion, literales.getLiteral("no"));
		 MDSQLUIHelper.setSelectedItem(cmbIncluirPDC, literales.getLiteral("si"));

		 btnGuardar.setEnabled(Boolean.TRUE);

		 if (!Objects.isNull(modelo)) {
			 txtDescripcionModelo.setText(modelo.getNombreModelo());
			 txtCodigoModelo.setText(modelo.getCodigoProyecto());
		 }
	 }
	 
	 @Override
	 protected void setupLiterals() {
		 setTitle(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.titulo"));

		 jLabel1.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel1"));
		 jLabel2.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel2"));
		 jLabel3.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel3"));
		 jLabel6.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel6"));
		 chkHabilitado.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel21"));
		 btnGuardar.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.guardar"));
		 jLabel11.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel11"));
		 jLabel12.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel12"));
		 btnInforme.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.informe"));
		 btnCancelar.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.cancelar"));
		 jLabel15.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel15"));
		 jLabel16.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel16"));
		 jLabel17.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel17"));
		 jLabel18.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel18"));
		 jLabel19.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel19"));
		 jLabel20.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel20"));
		 jLabel21.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel21"));
		 jLabel22.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel22"));
		 jLabel23.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel23"));
		 jLabel24.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel24"));
		 jLabel25.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel25"));
		 jLabel27.setText(literales.getLiteral("PantallaPermisosGeneralesporModeloporTipoObjeto.jLabel27"));
	 
	 }
	 
	 /**
		 * 
		 */
	public void enableButtons(Boolean val) {
		btnGuardar.setEnabled(val);
		btnInforme.setEnabled(val);
		btnCancelar.setEnabled(val);
	}
}
