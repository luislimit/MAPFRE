/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;
import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.ui.listener.PantallaEjecutarTypesActionListener;
import com.mdsql.ui.listener.tables.TypesTableListener;
import com.mdsql.ui.model.TypesTableModel;
import com.mdsql.ui.renderer.TypesTableCellRenderer;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
/**
 *
 * @author USUARIO1
 */
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;
import lombok.Setter;

public class PantallaEjecutarTypes extends DialogSupport {
	
	 /**
	  * 
	  */
    private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel jLabel8;
	private JTextField txtDemanda;
	private JScrollPane jScrollPane2;
	private JLabel jLabel13;
	private JLabel jLabel14;
	
	@Getter
	private JTextField txtIdProcesado;
	
    @Getter
	private JTextField txtEstadoEjecucion;

    @Getter
	private JTextField txtModelo;

    @Getter
	private TableSupport tblTypes;
    
	@Getter
	private JTextField txtSubmodelo;

    @Getter
	private JTextField txtSD;

    @Getter
	private JTextField txtSolicitadaPor;

    @Getter
	private JTextField txtEsquema;

    @Getter
	private JTextField txtBBDD;
    
    @Getter
    private JButton btnRechazar;
    
    @Getter
    private JButton btnVerCuadres;
    
    @Getter
    private JButton btnVerErrores;
    
    @Getter
    private JButton btnAceptar;
    
    @Getter
    private JButton btnCancelar;
    
    @Getter
	@Setter
	private Proceso proceso;
    
	@Getter
	@Setter
	private com.mdsql.bussiness.entities.Type seleccionado;
    
    // End of variables declaration//GEN-END:variables
    
    public PantallaEjecutarTypes(FrameSupport parent, Boolean modal) {
    	super(parent, modal);
    }
    
    public PantallaEjecutarTypes(FrameSupport parent, Boolean modal, Map<String, Object> params) {
    	super(parent, modal, params);
    }
    
    public void setupComponents() {
        
    	jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        txtIdProcesado = new JTextField();
        txtEstadoEjecucion = new JTextField();
        txtSubmodelo = new JTextField();
        txtSolicitadaPor = new JTextField();
        txtDemanda = new JTextField();
        btnRechazar = new JButton();
        jScrollPane2 = new JScrollPane();
        tblTypes = new TableSupport();
        btnAceptar = new JButton();
        btnCancelar = new JButton();
        txtModelo = new JTextField();
        jLabel13 = new JLabel();
        txtSD = new JTextField();
        txtEsquema = new JTextField();
        jLabel14 = new JLabel();
        btnVerCuadres = new JButton();
        btnVerErrores = new JButton();
        txtBBDD = new JTextField();
        
        setBounds(1430, 513);

        
        jScrollPane2.setViewportView(tblTypes);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstadoEjecucion, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProcesado, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubmodelo, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSD, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSolicitadaPor, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEsquema, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBBDD, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDemanda, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRechazar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerCuadres, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerErrores, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdProcesado, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstadoEjecucion, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubmodelo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSD, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSolicitadaPor, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEsquema, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBBDD, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDemanda, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRechazar)
                            .addComponent(btnVerCuadres)
                            .addComponent(btnVerErrores))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }

    @Override
    protected void initEvents() {      
    	PantallaEjecutarTypesActionListener actionListener = new PantallaEjecutarTypesActionListener(this);
    	TypesTableListener typesTableListener = new TypesTableListener(this);
    	
    	btnRechazar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_RECHAZAR);
    	btnVerCuadres.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_VER_CUADRES);
    	btnVerErrores.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_VER_ERRORES);
    	btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_ACEPTAR);
    	btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_EJECUTAR_TYPES_BTN_CANCELAR);
    	
    	btnRechazar.addActionListener(actionListener);
    	btnVerCuadres.addActionListener(actionListener);
    	btnVerErrores.addActionListener(actionListener);
    	btnAceptar.addActionListener(actionListener);
    	btnCancelar.addActionListener(actionListener);
    	
    	ListSelectionModel typesRowSM = tblTypes.getSelectionModel();
    	typesRowSM.addListSelectionListener(typesTableListener);
    	
    	this.addOnLoadListener(actionListener);
    }
    
    @Override
    protected void initModels() {
    	Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.TYPES_TABLA_CABECERA);
    	tblTypes.initModel(new TypesTableModel(cabecera));
    	
    	tblTypes.setDefaultRenderer(String.class, new TypesTableCellRenderer());
    }
    
    @Override
	protected void initialState() {
    	txtIdProcesado.setEditable(Boolean.FALSE);
    	txtEstadoEjecucion.setEditable(Boolean.FALSE);
		txtModelo.setEditable(Boolean.FALSE);
		txtSubmodelo.setEditable(Boolean.FALSE);
		txtSD.setEditable(Boolean.FALSE);
		txtSolicitadaPor.setEditable(Boolean.FALSE);
		txtEsquema.setEditable(Boolean.FALSE);
		txtBBDD.setEditable(Boolean.FALSE);
		txtDemanda.setEditable(Boolean.FALSE);
    	
    	txtIdProcesado.setHorizontalAlignment(JTextField.LEFT);
    	txtEstadoEjecucion.setHorizontalAlignment(JTextField.LEFT);
		txtModelo.setHorizontalAlignment(JTextField.LEFT);
		txtSubmodelo.setHorizontalAlignment(JTextField.LEFT);
		txtSD.setHorizontalAlignment(JTextField.LEFT);
		txtSolicitadaPor.setHorizontalAlignment(JTextField.LEFT);
		txtEsquema.setHorizontalAlignment(JTextField.LEFT);
		txtBBDD.setHorizontalAlignment(JTextField.LEFT);
		txtDemanda.setHorizontalAlignment(JTextField.LEFT);
		
		enableButtons(Boolean.FALSE);
		
		if (!Objects.isNull(proceso)) {
			txtEstadoEjecucion.setText(proceso.getDescripcionEstadoProceso());
			
			MDSQLUIHelper.resetText(txtModelo, proceso.getModelo().getCodigoProyecto(), 20);			
			MDSQLUIHelper.resetText(txtSubmodelo, proceso.getSubproyecto().getDescripcionSubProyecto(), 20);
			
			txtSD.setText(proceso.getCodigoPeticion());
			txtSolicitadaPor.setText(proceso.getCodigoUsrPeticion());
			
			if (!Objects.isNull(proceso.getBbdd())) {
				txtEsquema.setText(proceso.getBbdd().getNombreEsquema());
				txtBBDD.setText(proceso.getBbdd().getNombreBBDD());
			}
				
			txtDemanda.setText(proceso.getCodigoDemanda());
		}
	}
    
    @Override
    protected void setupLiterals() {
    	setTitle(literales.getLiteral("PantallaEjecutarTypes.title"));
    	jLabel1.setText(literales.getLiteral("PantallaEjecutarTypes.label1"));
        jLabel2.setText(literales.getLiteral("PantallaEjecutarTypes.label2"));
        jLabel3.setText(literales.getLiteral("PantallaEjecutarTypes.label3"));
        jLabel4.setText(literales.getLiteral("PantallaEjecutarTypes.label4"));
        jLabel5.setText(literales.getLiteral("PantallaEjecutarTypes.label5"));
        jLabel7.setText(literales.getLiteral("PantallaEjecutarTypes.label7"));
        jLabel8.setText(literales.getLiteral("PantallaEjecutarTypes.label8"));
        btnRechazar.setText(literales.getLiteral("PantallaEjecutarTypes.rechazar"));
        btnAceptar.setText(literales.getLiteral("PantallaEjecutarTypes.aceptar"));
        btnCancelar.setText(literales.getLiteral("PantallaEjecutarTypes.cancelar"));
        jLabel13.setText(literales.getLiteral("PantallaEjecutarTypes.label13"));
        jLabel14.setText(literales.getLiteral("PantallaEjecutarTypes.label14"));
        btnVerCuadres.setText(literales.getLiteral("PantallaEjecutarTypes.verCuadres"));
        btnVerErrores.setText(literales.getLiteral("PantallaEjecutarTypes.verErrores"));
    }
    
    /**
	 * 
	 */
	public void enableButtons(Boolean val) {
		btnVerErrores.setEnabled(val);
		btnVerCuadres.setEnabled(val);
	}
}