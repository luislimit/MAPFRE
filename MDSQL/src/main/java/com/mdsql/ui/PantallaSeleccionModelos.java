/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.listener.PantallaSeleccionModelosListener;
import com.mdsql.ui.listener.tables.PantallaSeleccionModelosTableListener;
import com.mdsql.ui.model.SeleccionModelosTableModel;
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
public class PantallaSeleccionModelos extends DialogSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8063673324639880723L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBuscar;
    @Getter
    private JButton btnNotas;
    @Getter
    private JButton btnPermisosGenerales;
    private JButton btnPermisosPorColumna;
    private JButton btnPermisosPorObjeto;
    @Getter
    private JButton btnVariables;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel lblTitulo;

    @Getter
    private JScrollPane jScrollPane2;
    
    @Getter
    private JTextField txtCodModelo;
    
    @Getter
    private JTextField txtNombreModelo;
    
    @Getter
    private JTextField txtCodSubmodelo;
    
    @Getter
    private TableSupport tblModelos;
    
    @Getter
    private JButton btnSeleccionar;
    
    @Getter
    @Setter
    private Modelo seleccionado;
    // End of variables declaration//GEN-END:variables
	
    @Getter
	private PantallaSeleccionModelosListener pantallaSeleccionModelosListener; 
	
	public PantallaSeleccionModelos(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaSeleccionModelos(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

    @Override
   	protected void setupComponents() {
    	
        btnBuscar = new JButton();
    	btnNotas = new JButton();
    	btnPermisosGenerales = new JButton();
    	btnPermisosPorColumna = new JButton();
    	btnPermisosPorObjeto = new JButton();
    	btnSeleccionar = new JButton();
    	btnVariables = new JButton();
    	jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        txtCodModelo = new JTextField();
        txtNombreModelo = new JTextField();
        txtCodSubmodelo = new JTextField();
        jScrollPane2 = new JScrollPane();
        tblModelos = new TableSupport();
        lblTitulo = new JLabel();
    	
        setBounds(1550, 500);
        
        jScrollPane2.setViewportView(tblModelos);
        
        GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTitulo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodSubmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(410, 410, 410))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnNotas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVariables)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPermisosPorColumna)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPermisosPorObjeto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPermisosGenerales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(txtCodModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodSubmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnPermisosGenerales)
                    .addComponent(btnPermisosPorObjeto)
                    .addComponent(btnPermisosPorColumna)
                    .addComponent(btnVariables)
                    .addComponent(btnNotas))
                .addContainerGap())
        );
    }
    
    @Override
   	protected void initEvents() {
   		pantallaSeleccionModelosListener = new PantallaSeleccionModelosListener(this);
   		ListSelectionListener listSelectionListener = new PantallaSeleccionModelosTableListener(this);
   		
   		btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_BUSCAR);
   		btnNotas.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_NOTAS);
   		btnPermisosGenerales.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_GENERALES);
   		btnPermisosPorColumna.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_COLUMNA);
   		btnPermisosPorObjeto.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_PERMISOS_POR_OBJETO);
   		btnSeleccionar.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_SELECCIONAR);
   		btnVariables.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_MODELOS_BTN_VARIABLES);
   		
   		btnBuscar.addActionListener(pantallaSeleccionModelosListener);
   		btnNotas.addActionListener(pantallaSeleccionModelosListener);
   		btnPermisosGenerales.addActionListener(pantallaSeleccionModelosListener);
   		btnPermisosPorColumna.addActionListener(pantallaSeleccionModelosListener);
   		btnPermisosPorObjeto.addActionListener(pantallaSeleccionModelosListener);
   		btnSeleccionar.addActionListener(pantallaSeleccionModelosListener);
   		btnVariables.addActionListener(pantallaSeleccionModelosListener);
   		
   		this.addOnLoadListener(pantallaSeleccionModelosListener);
   		
   		ListSelectionModel rowSM = tblModelos.getSelectionModel();
   		rowSM.addListSelectionListener(listSelectionListener);
   	}

   	@Override
   	protected void initModels() {
   		Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.DLG_SELECCION_MODELOS_TABLA_CABECERA);
   		tblModelos.initModel(new SeleccionModelosTableModel(cabecera));
   	}

   	@Override
   	protected void initialState() {
   		String codigoProyecto = (String) params.get("codigoProyecto");
   		if (StringUtils.isNotBlank(codigoProyecto)) {
   			txtCodModelo.setText(codigoProyecto);
   		}
   		
   		btnSeleccionar.setEnabled(Boolean.FALSE);	
   		
   		// Los botones adicionales estarán deshabilitados por defecto
   		btnNotas.setEnabled(Boolean.FALSE);
        btnPermisosGenerales.setEnabled(Boolean.FALSE);
        btnPermisosPorColumna.setEnabled(Boolean.FALSE);
        btnPermisosPorObjeto.setEnabled(Boolean.FALSE);
        btnSeleccionar.setEnabled(Boolean.FALSE);
        btnVariables.setEnabled(Boolean.FALSE);
   	}

   	@Override
   	protected void setupLiterals() {
   		setTitle(literales.getLiteral("PantallaSeleccionModelos.titulo"));
   		
   		lblTitulo.setText(literales.getLiteral("PantallaSeleccionModelos.lblTitulo"));
   		jLabel3.setText(literales.getLiteral("PantallaSeleccionModelos.label3"));
   		jLabel4.setText(literales.getLiteral("PantallaSeleccionModelos.label4"));
   		jLabel5.setText(literales.getLiteral("PantallaSeleccionModelos.label5"));
        btnBuscar.setText(literales.getLiteral("PantallaSeleccionModelos.buscar"));
        btnNotas.setText(literales.getLiteral("PantallaSeleccionModelos.notas"));
        btnPermisosGenerales.setText(literales.getLiteral("PantallaSeleccionModelos.permisosGenerales"));
        btnPermisosPorColumna.setText(literales.getLiteral("PantallaSeleccionModelos.permisosColumna"));
        btnPermisosPorObjeto.setText(literales.getLiteral("PantallaSeleccionModelos.permisosObjeto"));
        btnSeleccionar.setText(literales.getLiteral("PantallaSeleccionModelos.seleccionar"));
        btnVariables.setText(literales.getLiteral("PantallaSeleccionModelos.variables"));
   	}
}