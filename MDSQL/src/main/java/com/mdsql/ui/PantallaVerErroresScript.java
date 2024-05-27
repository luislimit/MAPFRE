/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.mdsql.ui.listener.PantallaVerErroresScriptListener;
import com.mdsql.ui.model.VerErroresScriptTableModel;
import com.mdsql.ui.model.VerParchesScriptTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;

/**
 *
 * @author USUARIO1
 */
public class PantallaVerErroresScript extends DialogSupport {

	private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCancelar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    
    @Getter
    private TableSupport tblErroresScript;
    
    @Getter
    private TableSupport tblParches;
    // End of variables declaration//GEN-END:variables

    public PantallaVerErroresScript(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaVerErroresScript(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }
    
    @Override
   	protected void setupComponents() {
    	jScrollPane1 = new JScrollPane();
        tblErroresScript = new TableSupport();
        jScrollPane2 = new JScrollPane();
        tblParches = new TableSupport();
        jLabel1 = new JLabel();
        btnCancelar = new JButton();
    	
        setBounds(1300, 600);
        
        jScrollPane1.setViewportView(tblErroresScript);
        if (tblErroresScript.getColumnModel().getColumnCount() > 0) {
            tblErroresScript.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblErroresScript.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblErroresScript.getColumnModel().getColumn(2).setPreferredWidth(75);
            tblErroresScript.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblErroresScript.getColumnModel().getColumn(4).setPreferredWidth(75);
            tblErroresScript.getColumnModel().getColumn(5).setPreferredWidth(300);
            tblErroresScript.getColumnModel().getColumn(6).setPreferredWidth(1005);
        }
        
        jScrollPane2.setViewportView(tblParches);
        if (tblParches.getColumnModel().getColumnCount() > 0) {
            tblParches.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblParches.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblParches.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblParches.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblParches.getColumnModel().getColumn(4).setPreferredWidth(150);
            tblParches.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblParches.getColumnModel().getColumn(6).setPreferredWidth(350);
            tblParches.getColumnModel().getColumn(7).setPreferredWidth(600);
        }
        
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );

        pack();
    }
	
    @Override
	protected void initEvents() {
		PantallaVerErroresScriptListener actionListener = new PantallaVerErroresScriptListener(this);
		
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR);

		btnCancelar.addActionListener(actionListener);
		
		this.addOnLoadListener(actionListener);
	}
    
    @Override
	protected void initModels() {
		Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.VER_ERRORES_TABLA_CABECERA);
		tblErroresScript.initModel(new VerErroresScriptTableModel(cabecera));
	
		cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.VER_PARCHES_TABLA_CABECERA);
		tblParches.initModel(new VerParchesScriptTableModel(cabecera));
    }	
    
    @Override
	protected void initialState() {}

	@Override
	protected void setupLiterals() {
		setTitle(literales.getLiteral("PantallaVerErroresScipt.titulo"));
		
		jLabel1.setText(literales.getLiteral("PantallaVerErroresScipt.label1"));
		btnCancelar.setText(literales.getLiteral("PantallaVerErroresScipt.cancelar"));
	}
}
