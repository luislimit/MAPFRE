/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.awt.Dimension;
import java.util.Map;
import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.ui.listener.PantallaProcesadoEnCursoActionListener;
import com.mdsql.ui.listener.tables.UltimasPeticionesTableListener;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.renderer.NivelAvisosTableCellRenderer;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
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
public class PantallaProcesadoEnCurso extends DialogSupport implements PantallaProcesar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7845375531319490239L;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton btnCancelar;
	
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	
	@Getter
	private JTextArea txtDescripcion;
	
	@Getter
	private JTextField txtPeticion;
	
	@Getter
	private JTextField txtSolicitadaPor;

	@Getter
	private JTextField txtModelo;
	
	@Getter
	private JTextField txtEsquema;
	
	@Getter
	private JTextField txtBBDDHistorico;
	
	@Getter
	private JTextField txtEsquemaHistorico;
	
	@Getter
	private JTextField txtDemanda;
	
	@Getter
	private JCheckBox chkGenerarHistorico;
	
	@Getter
	private TableSupport tblNotas;
	
	@Getter
	private TableSupport tblUltimasPeticiones;
	
	@Getter
	private JButton btnVerProcesado;
	
	@Getter
	private JButton btnProcesar;
	
	@Getter
	private JButton btnLimpiar;
	// End of variables declaration//GEN-END:variables
	
	@Getter
	@Setter
	private Modelo modeloSeleccionado;
	
	@Getter
	@Setter
	private SubProyecto subproyectoSeleccionado;
	
	@Getter
	@Setter
	private Aviso avisoSeleccionado;
	
	@Getter
	@Setter
	private Proceso procesoSeleccionado;
	
	@Getter
	@Setter
	private Procesado procesado;
	
	private JTextField txtBBDD;
	
	private JTextField txtSubproyecto;
	
	/**
	 * @param params
	 */
	public PantallaProcesadoEnCurso(FrameSupport parent, Boolean modal) {
		super(parent, modal);
	}

	/**
	 * Creates new form
	 */
	public PantallaProcesadoEnCurso(FrameSupport parent, Boolean modal, Map<String, Object> params) {
		super(parent, modal, params);
	}
	
	@Override
	protected void setupComponents() {
		jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        txtModelo = new JTextField();
        txtPeticion = new JTextField();
        txtSolicitadaPor = new JTextField();
        txtEsquema = new JTextField();
        chkGenerarHistorico = new JCheckBox();
        txtBBDDHistorico = new JTextField();
        txtEsquemaHistorico = new JTextField();
        jScrollPane1 = new JScrollPane();
        txtDescripcion = new JTextArea();
        btnLimpiar = new JButton();
        jButton1 = new JButton();
        jLabel11 = new JLabel();
        jScrollPane2 = new JScrollPane();
        tblNotas = new TableSupport();
        jLabel12 = new JLabel();
        jScrollPane3 = new JScrollPane();
        tblUltimasPeticiones = new TableSupport();
        btnVerProcesado = new JButton();
        btnProcesar = new JButton();
        btnCancelar = new JButton();
        txtDemanda = new JTextField();
        jLabel13 = new JLabel();
        txtSubproyecto = new JTextField();
        txtBBDD = new JTextField();
        
        Dimension dimension = new Dimension(1450, 665);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);
        
        jScrollPane2.setViewportView(tblNotas);
        jScrollPane3.setViewportView(tblUltimasPeticiones);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2, GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, GroupLayout.Alignment.TRAILING))))
                                .addComponent(jLabel9, GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel7, GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkGenerarHistorico)
                            .addComponent(txtPeticion)
                            .addComponent(txtSolicitadaPor)
                            .addComponent(txtEsquema)
                            .addComponent(txtBBDDHistorico)
                            .addComponent(txtEsquemaHistorico)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                            .addComponent(txtDemanda)
                            .addComponent(txtSubproyecto)
                            .addComponent(txtBBDD))))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 957, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(666, 666, 666)
                                .addComponent(btnVerProcesado)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnProcesar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel11))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSubproyecto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeticion, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSolicitadaPor, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEsquema, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtBBDD, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(chkGenerarHistorico)
                            .addComponent(jLabel7))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBBDDHistorico, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEsquemaHistorico, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDemanda, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel12)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnProcesar)
                            .addComponent(btnVerProcesado))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
	
	}
	
	@Override
	protected void initEvents() {
		PantallaProcesadoEnCursoActionListener actionListener = new PantallaProcesadoEnCursoActionListener(this);
		ListSelectionListener ultimasPeticionesSelectionListener = new UltimasPeticionesTableListener(this);

		btnCancelar.setActionCommand(MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_CANCELAR);
		btnVerProcesado.setActionCommand(MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO);

		btnCancelar.addActionListener(actionListener);
		btnVerProcesado.addActionListener(actionListener);
		
		ListSelectionModel ultimasPeticionesRowSM = tblUltimasPeticiones.getSelectionModel();
		ultimasPeticionesRowSM.addListSelectionListener(ultimasPeticionesSelectionListener);
		
		addOnLoadListener(actionListener);
	}

	@Override
	protected void initModels() {
		Cabecera cabeceraNotas = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.PROCESAR_SCRIPT_NOTAS_TABLA_CABECERA);
		tblNotas.initModel(
				new ProcesarScriptNotaTableModel(cabeceraNotas));
		tblNotas.setDefaultRenderer(String.class, new NivelAvisosTableCellRenderer());
		
		Cabecera cabeceraUltimasPeticiones = MDSQLUIHelper
				.createCabeceraTabla(MDSQLConstants.PROCESAR_SCRIPT_ULTIMAS_PETICIONES_TABLA_CABECERA);
		tblUltimasPeticiones.initModel(new ProcesarScriptUltimasPeticionesTableModel(cabeceraUltimasPeticiones));
	}

	@Override
	protected void initialState() {
		txtEsquema.setEditable(false);
        chkGenerarHistorico.setEnabled(false);
        txtBBDDHistorico.setEditable(false);
        txtEsquemaHistorico.setEditable(false);
        btnLimpiar.setEnabled(Boolean.FALSE);
        btnVerProcesado.setEnabled(Boolean.FALSE);
        
        Proceso proceso = (Proceso) params.get("proceso");
        if (!Objects.isNull(proceso)) {
        	txtModelo.setEditable(false);
        	txtSubproyecto.setEditable(false);
        	txtPeticion.setEditable(false);
        	txtSolicitadaPor.setEditable(false);
        	txtBBDD.setEditable(false);
        	txtDemanda.setEditable(false);
        	txtDescripcion.setEditable(false);
        	
        	txtModelo.setText(proceso.getModelo().getCodigoProyecto());
        	txtSubproyecto.setText(proceso.getSubproyecto().getDescripcionSubProyecto());
        	txtPeticion.setText(proceso.getCodigoPeticion());
        	txtSolicitadaPor.setText(proceso.getCodigoUsrPeticion());
        	
        	if (!Objects.isNull(proceso.getBbdd())) {
	        	txtBBDD.setText(proceso.getBbdd().getNombreBBDD());
	        	txtEsquema.setText(proceso.getBbdd().getNombreEsquema());
	        	txtBBDDHistorico.setText(proceso.getBbdd().getNombreBBDDHis());
	        	txtEsquemaHistorico.setText(proceso.getBbdd().getNombreEsquemaHis());
        	}
        	
        	txtDemanda.setText(proceso.getCodigoDemanda());
        	txtDescripcion.setText(proceso.getTxtDescripcion());
        	
        	jButton1.setEnabled(false);
        	btnProcesar.setEnabled(false);
        }
	}

	@Override
	protected void setupLiterals() {
		setTitle(literales.getLiteral("PantallaProcesadoEnCurso.titulo"));
		
		jButton1.setIcon(new ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N
		
		jLabel1.setText(literales.getLiteral("PantallaProcesadoEnCurso.label1"));
		jLabel2.setText(literales.getLiteral("PantallaProcesadoEnCurso.label2"));
		jLabel3.setText(literales.getLiteral("PantallaProcesadoEnCurso.label3"));
		jLabel4.setText(literales.getLiteral("PantallaProcesadoEnCurso.label4"));
		jLabel5.setText(literales.getLiteral("PantallaProcesadoEnCurso.label5"));
		jLabel6.setText(literales.getLiteral("PantallaProcesadoEnCurso.label6"));
		jLabel7.setText(literales.getLiteral("PantallaProcesadoEnCurso.label7"));
		jLabel8.setText(literales.getLiteral("PantallaProcesadoEnCurso.label8"));
		jLabel9.setText(literales.getLiteral("PantallaProcesadoEnCurso.label9"));
		jLabel10.setText(literales.getLiteral("PantallaProcesadoEnCurso.label10"));
		btnLimpiar.setText(literales.getLiteral("PantallaProcesadoEnCurso.limpiar"));
		jLabel11.setText(literales.getLiteral("PantallaProcesadoEnCurso.label11"));
		jLabel12.setText(literales.getLiteral("PantallaProcesadoEnCurso.label12"));
		jLabel13.setText(literales.getLiteral("PantallaProcesadoEnCurso.label13"));
		btnVerProcesado.setText(literales.getLiteral("PantallaProcesadoEnCurso.verProcesado"));
		btnProcesar.setText(literales.getLiteral("PantallaProcesadoEnCurso.procesar"));
		btnCancelar.setText(literales.getLiteral("PantallaProcesadoEnCurso.cancelar"));
	}
}