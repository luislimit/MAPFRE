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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdsql.ui.listener.PantallaAjustarLogEjecucionListener;
import com.mdsql.ui.listener.tables.PantallaAjustarLogTableListener;
import com.mdsql.ui.model.AjustarLogEjecucionTableModel;
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
 * @author USUARIO1
 */
public class PantallaAjustarLogEjecucion extends DialogSupport {

	private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCancelar;
    
    @Getter
    private JButton btnEliminar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    
    @Getter
    private TableSupport tblAjustarLog;
    
    @Getter
    private JTextField txtComentario;
    // End of variables declaration//GEN-END:variables
 
    @Getter
    @Setter
    private LogEjecucion seleccionado;
    
   	public PantallaAjustarLogEjecucion(FrameSupport parent, Boolean modal) {
   		super(parent, modal);
   	}

   	public PantallaAjustarLogEjecucion(FrameSupport parent, Boolean modal, Map<String, Object> params) {
   		super(parent, modal, params);
   	}
    
   	@Override
	protected void setupComponents() {
   		jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        tblAjustarLog = new TableSupport();
        jLabel2 = new JLabel();
        txtComentario = new JTextField();
        btnEliminar = new JButton();
        btnCancelar = new JButton();
        
        setBounds(1300, 500);
        
        jScrollPane1.setViewportView(tblAjustarLog);
        
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39)
                                .addComponent(txtComentario))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 367, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addGap(15, 15, 15))
        );

        pack();
   	}
	
   	@Override
   	protected void initEvents() {
   		PantallaAjustarLogEjecucionListener pantallaAjustarLogEjecucionListener = new PantallaAjustarLogEjecucionListener(this);
   		ListSelectionListener listSelectionListener = new PantallaAjustarLogTableListener(this);
   	
   		btnEliminar.setActionCommand(MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_ELIMINAR);
   		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_AJUSTAR_LOG_EJECUCION_CANCELAR);
   		
   		btnEliminar.addActionListener(pantallaAjustarLogEjecucionListener);
   		btnCancelar.addActionListener(pantallaAjustarLogEjecucionListener);
   		
   		this.addOnLoadListener(pantallaAjustarLogEjecucionListener);
   		
   		ListSelectionModel rowSM = tblAjustarLog.getSelectionModel();
		rowSM.addListSelectionListener(listSelectionListener);
    }
    
    @Override
	protected void initModels() {
    	Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.DLG_AJUSTAR_LOG_EJECUCION_TABLA_CABECERA);
    	tblAjustarLog.initModel(new AjustarLogEjecucionTableModel(cabecera));
    }
    
    @Override
   	protected void initialState() {
    	Boolean consulta = (Boolean) this.getParams().get("consulta");
    	
    	btnEliminar.setEnabled(Boolean.FALSE);
    	
    	if (consulta) {
    		txtComentario.setEnabled(Boolean.FALSE);
    	}
    }

   	@Override
   	protected void setupLiterals() {
   		setTitle(literales.getLiteral("PantallaAjustarLogEjecucion.titulo"));
   		
   		jLabel1.setText(literales.getLiteral("PantallaAjustarLogEjecucion.label1"));
   		jLabel2.setText(literales.getLiteral("PantallaAjustarLogEjecucion.label2"));
        btnEliminar.setText(literales.getLiteral("PantallaAjustarLogEjecucion.eliminar"));
        btnCancelar.setText(literales.getLiteral("PantallaAjustarLogEjecucion.cancelar"));
   	}   
}
