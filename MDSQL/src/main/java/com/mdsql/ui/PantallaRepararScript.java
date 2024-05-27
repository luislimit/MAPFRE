/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.listener.PantallaRepararScriptListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaRepararScript extends DialogSupport {

    private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    
    @Getter
    private JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
    private PantallaRepararScriptListener pantallaRepararScriptListener;

    @Getter
	private JButton btnAbrirFichero;

    @Getter
	private JTextField txtScript;

    @Getter
	private JButton btnAbrirFicheroReparacion;

    @Getter
	private JTextField txtScriptReparacion;

    @Getter
	private JRadioButton rbtnReprocesar;

    @Getter
	private JRadioButton rbtnNoReprocesar;

    @Getter
	private JRadioButton rbtnEjecutarScriptProcesado;

    @Getter
	private JRadioButton rbtnEjecutarScriptReparacion;
    
    public PantallaRepararScript(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaRepararScript(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }
    
    @Override
   	protected void setupComponents() {
    	jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        rbtnReprocesar = new JRadioButton();
        btnAbrirFichero = new JButton();
        txtScript = new JTextField();
        rbtnNoReprocesar = new JRadioButton();
        jPanel2 = new JPanel();
        rbtnEjecutarScriptProcesado = new JRadioButton();
        rbtnEjecutarScriptReparacion = new JRadioButton();
        btnAbrirFicheroReparacion = new JButton();
        txtScriptReparacion = new JTextField();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        btnAceptar = new JButton();
        btnCancelar = new JButton();
        
        setBounds(1009, 570);
        
        txtScript.setEditable(false);
        txtScriptReparacion.setEditable(false);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtnReprocesar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAbrirFichero)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtScript, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(rbtnNoReprocesar))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnReprocesar)
                    .addComponent(btnAbrirFichero)
                    .addComponent(txtScript, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnNoReprocesar)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnEjecutarScriptProcesado)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbtnEjecutarScriptReparacion)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAbrirFicheroReparacion)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtScriptReparacion, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnEjecutarScriptProcesado)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtScriptReparacion)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrirFicheroReparacion)
                            .addComponent(rbtnEjecutarScriptReparacion))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }
    
    @Override
   	protected void initEvents() {
    	pantallaRepararScriptListener = new PantallaRepararScriptListener(this);
    	
    	btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ACEPTAR);
    	btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_CANCELAR);
    	btnAbrirFichero.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO);
    	btnAbrirFicheroReparacion.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_BTN_ABRIR_FICHERO_REPARACION);
    	rbtnReprocesar.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_REPROCESAR_SCRIPT);
    	rbtnNoReprocesar.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_NO_REPROCESAR_SCRIPT);
    	rbtnEjecutarScriptProcesado.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_PROCESADO);
    	rbtnEjecutarScriptReparacion.setActionCommand(MDSQLConstants.PANTALLA_REPARAR_SCRIPT_RBTN_SCRIPT_REPARACION);
    	
    	btnAceptar.addActionListener(pantallaRepararScriptListener);
    	btnCancelar.addActionListener(pantallaRepararScriptListener);
    	btnAbrirFichero.addActionListener(pantallaRepararScriptListener);
    	btnAbrirFicheroReparacion.addActionListener(pantallaRepararScriptListener);
    	rbtnReprocesar.addActionListener(pantallaRepararScriptListener);
    	rbtnNoReprocesar.addActionListener(pantallaRepararScriptListener);
    	rbtnEjecutarScriptProcesado.addActionListener(pantallaRepararScriptListener);
    	rbtnEjecutarScriptReparacion.addActionListener(pantallaRepararScriptListener);
    	
    	// Group the radioButtons
    	ButtonGroup groupReprocesar = new ButtonGroup();
        groupReprocesar.add(rbtnReprocesar);
        groupReprocesar.add(rbtnNoReprocesar);
        
    	ButtonGroup groupReparacion = new ButtonGroup();
    	groupReparacion.add(rbtnEjecutarScriptProcesado);
    	groupReparacion.add(rbtnEjecutarScriptReparacion);
    }
    
    @Override
	protected void initModels() {
    	
    }
    
    @Override
   	protected void initialState() {
    	rbtnReprocesar.setSelected(Boolean.TRUE);
    	rbtnEjecutarScriptProcesado.setSelected(Boolean.TRUE);
    	btnAbrirFichero.setEnabled(Boolean.TRUE);
    	txtScript.setEnabled(Boolean.TRUE);
    	btnAbrirFicheroReparacion.setEnabled(Boolean.FALSE);
    	txtScriptReparacion.setEnabled(Boolean.FALSE);
    }

   	@Override
   	protected void setupLiterals() {
   		setTitle(literales.getLiteral("PantallaRepararScript.title"));
   		
   		Script script = (Script) getParams().get("script");
   		
   		btnAbrirFichero.setIcon(new ImageIcon(getClass().getResource("/folder-open.png"))); // NOI18N
   		btnAbrirFicheroReparacion.setIcon(new ImageIcon(getClass().getResource("/folder-open.png"))); // NOI18N
   		
   		jLabel1.setText(script.getNombreScript());
        jLabel2.setText(literales.getLiteral("PantallaRepararScript.label2"));
        jLabel3.setText(literales.getLiteral("PantallaRepararScript.label3"));
        jLabel4.setText(literales.getLiteral("PantallaRepararScript.label4"));
        jLabel5.setText(literales.getLiteral("PantallaRepararScript.label5"));
        rbtnReprocesar.setText(literales.getLiteral("PantallaRepararScript.radioButton1"));
        rbtnNoReprocesar.setText(literales.getLiteral("PantallaRepararScript.radioButton2"));
        rbtnEjecutarScriptProcesado.setText(literales.getLiteral("PantallaRepararScript.radioButton3"));
        rbtnEjecutarScriptReparacion.setText(literales.getLiteral("PantallaRepararScript.radioButton4"));
        jLabel6.setText(literales.getLiteral("PantallaRepararScript.label6"));
        jLabel7.setText(literales.getLiteral("PantallaRepararScript.label7"));
        btnAceptar.setText(literales.getLiteral("PantallaRepararScript.aceptar"));
        btnCancelar.setText(literales.getLiteral("PantallaRepararScript.cancelar"));
   	}
}
