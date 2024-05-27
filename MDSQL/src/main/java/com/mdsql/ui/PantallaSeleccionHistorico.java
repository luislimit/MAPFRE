/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import com.mdsql.ui.listener.PantallaSeleccionHistoricoListener;
import com.mdsql.ui.listener.tables.SeleccionHistoricoTableItemListener;
import com.mdsql.ui.model.SeleccionHistoricoTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.model.cabeceras.CheckBoxHeader;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;

/**
 *
 * @author federico
 */
public class PantallaSeleccionHistorico extends DialogSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4469486461122163193L;
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCancelar;
    private JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
	
    @Getter
   	private TableSupport tblHistorico;

    @Getter
   	private JButton btnAddHistorico;

    @Getter
   	private JButton btnGenerarHistorico;
       
    @Getter
    private PantallaSeleccionHistoricoListener pantallaSeleccionHistoricoListener;
	
    public PantallaSeleccionHistorico(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }
    
    public PantallaSeleccionHistorico(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

    @Override
	protected void setupComponents() {
    	
    	jScrollPane1 = new JScrollPane();
        tblHistorico = new TableSupport();
        btnAddHistorico = new JButton();
        btnGenerarHistorico = new JButton();
        btnCancelar = new JButton();
        
        setBounds(1418, 586); 

        jScrollPane1.setViewportView(tblHistorico);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1171, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddHistorico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddHistorico)
                    .addComponent(btnGenerarHistorico)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }
    
    @Override
	protected void initEvents() {
		pantallaSeleccionHistoricoListener = new PantallaSeleccionHistoricoListener(this);
		
		btnAddHistorico.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_ADD);
		btnGenerarHistorico.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_GENERAR);
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_CANCELAR);
		
		btnAddHistorico.addActionListener(pantallaSeleccionHistoricoListener);
		btnGenerarHistorico.addActionListener(pantallaSeleccionHistoricoListener);
		btnCancelar.addActionListener(pantallaSeleccionHistoricoListener);
		
		this.addOnLoadListener(pantallaSeleccionHistoricoListener);
	}

	@Override
	protected void initModels() {
		Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.SELECCION_HISTORICO_TABLA_CABECERA);
		SeleccionHistoricoTableModel model = new SeleccionHistoricoTableModel(cabecera);
		tblHistorico.initModel(model);
		tblHistorico.setColumnWidths(cabecera);
		
		TableColumn tc = tblHistorico.getColumnModel().getColumn(0);
		tc.setCellEditor(tblHistorico.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tblHistorico.getDefaultRenderer(Boolean.class));
		tc.setHeaderRenderer(new CheckBoxHeader(new SeleccionHistoricoTableItemListener(model, tblHistorico),
				cabecera.getIdentifierAt(0), Boolean.FALSE));

		tc = tblHistorico.getColumnModel().getColumn(3);
		tc.setCellEditor(tblHistorico.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tblHistorico.getDefaultRenderer(Boolean.class));
		tc.setHeaderRenderer(new CheckBoxHeader(new SeleccionHistoricoTableItemListener(model, tblHistorico),
				cabecera.getIdentifierAt(3), Boolean.FALSE));

		tc = tblHistorico.getColumnModel().getColumn(4);
		tc.setCellEditor(tblHistorico.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tblHistorico.getDefaultRenderer(Boolean.class));
		tc.setHeaderRenderer(new CheckBoxHeader(new SeleccionHistoricoTableItemListener(model, tblHistorico),
				cabecera.getIdentifierAt(4), model.checkAllVigente()));
	}	
		
	@Override
	protected void initialState() {}

	@Override
	protected void setupLiterals() {
		setTitle(literales.getLiteral("PantallaSeleccionHistorico.titulo"));
		
		btnAddHistorico.setText(literales.getLiteral("PantallaSeleccionHistorico.addHistorico"));
		btnGenerarHistorico.setText(literales.getLiteral("PantallaSeleccionHistorico.generarHistorico"));
		btnCancelar.setText(literales.getLiteral("PantallaSeleccionHistorico.cancelar"));
	}
}
