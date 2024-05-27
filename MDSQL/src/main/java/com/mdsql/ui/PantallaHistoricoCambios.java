/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mdsql.ui;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Estado;
import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.bussiness.entities.Operacion;
import com.mdsql.ui.listener.PantallaHistoricoCambiosListener;
import com.mdsql.ui.listener.tables.PantallaHistoricoObjetosTableListener;
import com.mdsql.ui.model.HistoricoObjetoTableModel;
import com.mdsql.ui.renderer.EstadoRenderer;
import com.mdsql.ui.renderer.OperacionRenderer;
import com.mdsql.ui.renderer.TipoObjetoRenderer;
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
public class PantallaHistoricoCambios extends DialogSupport {

private static final long serialVersionUID = 1L;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBuscar;
    private JButton btnBuscarModelo;
    private JButton btnCancelar;
    private JButton btnInforme;
    
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    
    private JScrollPane jScrollPane1;
    
    @Getter
    private TableSupport tblHistoricoObjetos;
    
    @Getter
    private JTextField txtDesde;
    
    @Getter
    private JTextField txtHasta;
    
    @Getter
    private JTextField txtObjeto;
    
    @Getter
    private JTextField txtObjetoPadre;
    
    @Getter
	private JComboBox<String> cmbTipoObjeto;

    @Getter
	private JComboBox<Operacion> cmbOperacion;

    @Getter
	private JComboBox<Estado> cmbEstadoScript;

    @Getter
	private JComboBox<String> cmbTipoObjetoPadre;

    @Getter
	private JComboBox<Operacion> cmbOperacionPadre;

    @Getter
	private JComboBox<Estado> cmbEstadoProcesado;
    // End of variables declaration//GEN-END:variables
    
    @Getter
    private JTextField txtModelo;
    
    @Getter
    private JButton btnResumen;
    
    @Getter
    private JButton btnVerDetalle;
    
    @Getter
    @Setter
    private HistoricoProceso seleccionado;

    public PantallaHistoricoCambios(FrameSupport parent, Boolean modal) {
		super(parent, modal);
	}

	public PantallaHistoricoCambios(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	}

	@Override
	protected void setupComponents() {
		jLabel1 = new JLabel();
        txtModelo = new JTextField();
        btnBuscarModelo = new JButton();
        jLabel2 = new JLabel();
        txtObjeto = new JTextField();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        cmbTipoObjeto = new JComboBox<>();
        cmbOperacion = new JComboBox<>();
        jLabel5 = new JLabel();
        cmbEstadoScript = new JComboBox<>();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        cmbTipoObjetoPadre = new JComboBox<>();
        jLabel8 = new JLabel();
        cmbOperacionPadre = new JComboBox<>();
        jLabel9 = new JLabel();
        cmbEstadoProcesado = new JComboBox<>();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        btnBuscar = new JButton();
        jScrollPane1 = new JScrollPane();
        tblHistoricoObjetos = new TableSupport();
        btnInforme = new JButton();
        btnVerDetalle = new JButton();
        btnResumen = new JButton();
        btnCancelar = new JButton();
        txtObjetoPadre = new JTextField();
        txtDesde = new JTextField();
        txtHasta = new JTextField();

        
        setBounds(1300, 680);
        
        jScrollPane1.setViewportView(tblHistoricoObjetos);
        
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResumen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cmbOperacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnBuscarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(26, 26, 26)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbOperacionPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbTipoObjetoPadre, javax.swing.GroupLayout.Alignment.LEADING, 0, 195, Short.MAX_VALUE)
                                        .addComponent(cmbEstadoScript, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtObjetoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbEstadoProcesado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDesde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarModelo))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(cmbEstadoScript, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstadoProcesado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(txtObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtObjetoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTipoObjetoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbOperacionPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInforme)
                    .addComponent(btnVerDetalle)
                    .addComponent(btnResumen)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }

	@Override
	protected void initEvents() {
		PantallaHistoricoCambiosListener pantallaHistoricoCambiosListener = new PantallaHistoricoCambiosListener(this);
		ListSelectionListener listSelectionListener = new PantallaHistoricoObjetosTableListener(this);
		
		btnBuscarModelo.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR_MODELO);
		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_CANCELAR);
		btnBuscar.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_BUSCAR);
		btnInforme.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_INFORME_CAMBIOS);
		btnResumen.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_RESUMEN_PROCESADO);
		btnVerDetalle.setActionCommand(MDSQLConstants.PANTALLA_HISTORICO_CAMBIOS_VER_DETALLE_SCRIPT);
		
		btnBuscarModelo.addActionListener(pantallaHistoricoCambiosListener);
		btnCancelar.addActionListener(pantallaHistoricoCambiosListener);
		btnBuscar.addActionListener(pantallaHistoricoCambiosListener);
		btnInforme.addActionListener(pantallaHistoricoCambiosListener);
		btnResumen.addActionListener(pantallaHistoricoCambiosListener);
		btnVerDetalle.addActionListener(pantallaHistoricoCambiosListener);
		
		ListSelectionModel rowSM = tblHistoricoObjetos.getSelectionModel();
   		rowSM.addListSelectionListener(listSelectionListener);
   		
   		addOnLoadListener(pantallaHistoricoCambiosListener);
	}

	@Override
	protected void setupLiterals() {
		setTitle(literales.getLiteral("PantallaHistoricoCambios.title"));
		
		jLabel1.setText(literales.getLiteral("PantallaHistoricoCambios.label1"));
		jLabel2.setText(literales.getLiteral("PantallaHistoricoCambios.label2"));
		jLabel3.setText(literales.getLiteral("PantallaHistoricoCambios.label3"));
		jLabel4.setText(literales.getLiteral("PantallaHistoricoCambios.label4"));
		jLabel5.setText(literales.getLiteral("PantallaHistoricoCambios.label5"));
		jLabel6.setText(literales.getLiteral("PantallaHistoricoCambios.label6"));
		jLabel7.setText(literales.getLiteral("PantallaHistoricoCambios.label7"));
		jLabel8.setText(literales.getLiteral("PantallaHistoricoCambios.label8"));
		jLabel9.setText(literales.getLiteral("PantallaHistoricoCambios.label9"));
		jLabel10.setText(literales.getLiteral("PantallaHistoricoCambios.label10"));
		jLabel11.setText(literales.getLiteral("PantallaHistoricoCambios.label11"));
		
		btnBuscarModelo.setIcon(new ImageIcon(getClass().getResource("/loupe.png")));
		btnBuscar.setText(literales.getLiteral("PantallaHistoricoCambios.buscar"));
		btnInforme.setText(literales.getLiteral("PantallaHistoricoCambios.informe"));
		btnVerDetalle.setText(literales.getLiteral("PantallaHistoricoCambios.verDetalle"));
		btnResumen.setText(literales.getLiteral("PantallaHistoricoCambios.resumen"));
		btnCancelar.setText(literales.getLiteral("PantallaHistoricoCambios.cancelar")); 
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initModels() {
		cmbEstadoScript.setRenderer(new EstadoRenderer());
        cmbEstadoProcesado.setRenderer(new EstadoRenderer());
        cmbTipoObjeto.setRenderer(new TipoObjetoRenderer());
        cmbTipoObjetoPadre.setRenderer(new TipoObjetoRenderer());
        cmbOperacion.setRenderer(new OperacionRenderer());
        cmbOperacionPadre.setRenderer(new OperacionRenderer());
		
        Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.DLG_HISTORICO_CAMBIOS_TABLA_CABECERA);
        tblHistoricoObjetos.initModel(new HistoricoObjetoTableModel(cabecera));
	}

	@Override
	protected void initialState() {
		btnResumen.setEnabled(Boolean.FALSE);
		btnVerDetalle.setEnabled(Boolean.FALSE);
	}
	
}