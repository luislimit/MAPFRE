/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.awt.Color;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;

import com.mdsql.ui.listener.PantallaDescartarScriptListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaDescartarScript extends DialogSupport {

    private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAbrirScriptProcesar;
    
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JRadioButton rbtnReducir;
    private JRadioButton rbtnAmpliar;
    private JScrollPane jScrollPane1;
    
    @Getter
    private JTextField txtScriptProcesar;
    
    @Getter
    private JButton btnAbrirScriptParche;
    
    @Getter
    private JTextField txtScriptParche;
    
    @Getter
    private JTextArea txtComentario;
    // End of variables declaration//GEN-END:variables
    
    
    public PantallaDescartarScript(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaDescartarScript(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }
    
    @Override
   	protected void setupComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        btnAbrirScriptProcesar = new JButton();
        txtScriptProcesar = new JTextField();
        jLabel3 = new JLabel();
        jPanel1 = new JPanel();
        rbtnReducir = new JRadioButton();
        rbtnAmpliar = new JRadioButton();
        btnAbrirScriptParche = new JButton();
        txtScriptParche = new JTextField();
        jLabel4 = new JLabel();
        jScrollPane1 = new JScrollPane();
        txtComentario = new JTextArea();
        btnAceptar = new JButton();
        btnCancelar = new JButton();

        setBounds(965, 462);

        txtScriptProcesar.setEditable(false);
        
        jPanel1.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 1, true)));

        txtScriptParche.setEditable(false);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnAmpliar)
                            .addComponent(rbtnReducir))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnAbrirScriptParche)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtScriptParche)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnReducir)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnAmpliar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAbrirScriptParche)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtScriptParche))
                .addContainerGap())
        );

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane1.setViewportView(txtComentario);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(btnAbrirScriptProcesar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtScriptProcesar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnAbrirScriptProcesar)
                    .addComponent(txtScriptProcesar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }
    
    @Override
   	protected void initEvents() {
    	ActionListener pantallaDescartarScriptListener = new PantallaDescartarScriptListener(this);
    	
    	btnAbrirScriptProcesar.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PROCESAR);
    	btnAbrirScriptParche.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_BTN_ABRIR_PARCHE);
    	rbtnReducir.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_RBTN_REDUCIR);
    	rbtnAmpliar.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_RBTN_AMPLIAR);
    	btnAceptar.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_BTN_ACEPTAR);
    	btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_DESCARTAR_SCRIPT_BTN_CANCELAR);
    	
    	btnAbrirScriptProcesar.addActionListener(pantallaDescartarScriptListener);
    	btnAbrirScriptParche.addActionListener(pantallaDescartarScriptListener);
    	btnAceptar.addActionListener(pantallaDescartarScriptListener);
    	btnCancelar.addActionListener(pantallaDescartarScriptListener);
    	
    	// Group the radioButtons
    	ButtonGroup group = new ButtonGroup();
        group.add(rbtnReducir);
        group.add(rbtnAmpliar);
        
        rbtnReducir.addActionListener(pantallaDescartarScriptListener);
        rbtnAmpliar.addActionListener(pantallaDescartarScriptListener);
    }
    
    @Override
   	protected void initModels() {
       	
    }
       
    @Override
    protected void initialState() {
    	rbtnReducir.setSelected(Boolean.TRUE);
    	btnAbrirScriptParche.setEnabled(Boolean.FALSE);
    	txtScriptParche.setEnabled(Boolean.FALSE);
    }

    @Override
    protected void setupLiterals() {
    	setTitle(literales.getLiteral("PantallaDescartarScript.title"));
    	
    	btnAbrirScriptProcesar.setIcon(new ImageIcon(getClass().getResource("/folder-open.png"))); // NOI18N
    	btnAbrirScriptParche.setIcon(new ImageIcon(getClass().getResource("/folder-open.png"))); // NOI18N
    	
    	jLabel1.setText(literales.getLiteral("PantallaDescartarScript.label1"));
        jLabel2.setText(literales.getLiteral("PantallaDescartarScript.label2"));
        jLabel3.setText(literales.getLiteral("PantallaDescartarScript.label3"));
        rbtnReducir.setText(literales.getLiteral("PantallaDescartarScript.radioButton1"));
        rbtnAmpliar.setText(literales.getLiteral("PantallaDescartarScript.radioButton2"));
        jLabel4.setText(literales.getLiteral("PantallaDescartarScript.label4"));
        btnAceptar.setText(literales.getLiteral("PantallaDescartarScript.aceptar"));
        btnCancelar.setText(literales.getLiteral("PantallaDescartarScript.cancelar"));
    }
}
