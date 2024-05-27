/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.mdsql.ui.listener.PantallaInformacionModeloListener;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.renderer.NivelAvisosTableCellRenderer;
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
public class PantallaInformacionModelo extends DialogSupport {

    private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    
    @Getter
    private TableSupport tblInformacion;
    
    private JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
    
    public PantallaInformacionModelo(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaInformacionModelo(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }
    
    @Override
   	protected void setupComponents() {
    	jLabel1 = new JLabel();
        txtModelo = new JTextField();
        jScrollPane1 = new JScrollPane();
        tblInformacion = new TableSupport();
        
        setBounds(881, 387);
        
        jScrollPane1.setViewportView(tblInformacion);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

    }
    
    @Override
   	protected void initEvents() {
    	PantallaInformacionModeloListener pantallaInformacionModeloListener = new PantallaInformacionModeloListener(this);
    	addOnLoadListener(pantallaInformacionModeloListener);
    }
    
    @Override
	protected void initModels() {
    	Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.DLG_INFORMACION_MODELO_TABLA_CABECERA);
    	
    	tblInformacion.initModel(new ProcesarScriptNotaTableModel(cabecera));
    	tblInformacion.setDefaultRenderer(String.class, new NivelAvisosTableCellRenderer());
    }
    
    @Override
   	protected void initialState() {
    	txtModelo.setEnabled(Boolean.FALSE);
    	String codigoProyecto = (String) params.get("codigoProyecto");
    	txtModelo.setText(codigoProyecto);
    }

   	@Override
   	protected void setupLiterals() {
   		setTitle(literales.getLiteral("PantallaInformacionModelo.titulo"));
   		jLabel1.setText(literales.getLiteral("PantallaInformacionModelo.label1"));
   	}    
}