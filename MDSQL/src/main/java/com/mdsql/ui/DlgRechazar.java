/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.mdsql.ui.listener.DlgRechazarListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;

import lombok.Getter;

/**
 *
 * @author federico
 */
public class DlgRechazar extends DialogSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3792975274329612636L;
	private JPanel jPanel1;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JPanel jPanel3;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	@Getter
	private JTextArea txtMotivoRechazo;

    /**
     * Creates new form DlgErrores
     */
    public DlgRechazar(FrameSupport parent, boolean modal) {
        super(parent, modal);
    }
    
    /**
     * Creates new form DlgErrores
     */
    public DlgRechazar(FrameSupport parent, boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

	@Override
	protected void setupComponents() {
		jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jPanel2 = new JPanel();
        jScrollPane1 = new JScrollPane();
        txtMotivoRechazo = new JTextArea();
        jPanel3 = new JPanel();
        btnAceptar = new JButton();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));

        jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        jLabel1.setFont(new Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/info.png"))); // NOI18N
        jLabel1.setIconTextGap(10);
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jPanel2.setLayout(new BorderLayout());

        txtMotivoRechazo.setColumns(20);
        txtMotivoRechazo.setLineWrap(true);
        txtMotivoRechazo.setRows(5);
        jScrollPane1.setViewportView(txtMotivoRechazo);

        jPanel2.add(jScrollPane1, BorderLayout.CENTER);

        getContentPane().add(jPanel2, BorderLayout.CENTER);

        jPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel3.add(btnAceptar);
        jPanel3.add(btnCancelar);

        getContentPane().add(jPanel3, BorderLayout.PAGE_END);
	}

	@Override
	protected void initEvents() {
		ActionListener actionListener = new DlgRechazarListener(this);
		
		btnAceptar.setActionCommand(MDSQLConstants.DLG_RECHAZAR_BTN_ACEPTAR);
		btnCancelar.setActionCommand(MDSQLConstants.DLG_RECHAZAR_BTN_CANCELAR);
		
		btnAceptar.addActionListener(actionListener);
		btnCancelar.addActionListener(actionListener);
	}

	@Override
	protected void setupLiterals() {
		jLabel1.setText("Motivo del rechazo");
		btnAceptar.setText("ACEPTAR");
		btnCancelar.setText("CANCELAR");
	}

	@Override
	protected void initModels() {}

	@Override
	protected void initialState() {}
}
