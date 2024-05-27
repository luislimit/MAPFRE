/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.UndoManager;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.ui.listener.EditorEventHandler;
import com.mdsql.ui.listener.FramePrincipalActionListener;
import com.mdsql.ui.listener.FramePrincipalWindowListener;
import com.mdsql.ui.listener.tables.ListaObjetosTableListener;
import com.mdsql.ui.menu.MainMenuBar;
import com.mdsql.ui.model.FramePrincipalTypesTableModel;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.InternalFrameSupport;
import com.mdval.ui.utils.TableSupport;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author federico
 */
public class FramePrincipal extends FrameSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543072851506727342L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	@Getter
	private JButton btnLoadScript;
	
	@Getter
	private JButton btnInformacionModelo;
	
	@Getter
	private JButton btnCargarScriptObjetos;
	
	@Getter
	private JButton btnProcesarScript;
	
	@Getter
	private JButton btnSave;
	
	@Getter
	private JButton btnExecute;
	
	@Getter
	private JButton btnEntregarProcesado;
	
	@Getter
	private JButton btnLimpiarScripts;
	
	@Getter
	private JButton btnLimpiarSesion;
	
	@Getter
	private JButton btnProcesadoEnCurso;
	
	@Getter
	private JButton btnRefrescarFichero;

	@Getter
	private InternalFrameSupport jInternalFrame6;
	
	private JPanel jPanel1;
	private JPanel panelVigente;
	private JPanel panelHistorico;
	private JPanel panelTypes;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JToolBar.Separator jSeparator1;
	private JToolBar.Separator jSeparator2;
	private JToolBar.Separator jSeparator4;
	private JSplitPane jSplitPane1;
	private JToolBar jToolBar1;
	// End of variables declaration//GEN-END:variables

	@Getter
	private JInternalFrame frmSQLScript;

	@Getter
	private JTextArea txtSQLCode;

	@Getter
	private JTabbedPane tabPanel;

	@Getter
	@Setter
	private Procesado procesado;

	@Getter
	@Setter
	private Boolean hasChanged = Boolean.FALSE; // el estado del documento actual, no modificado por defecto

	@Getter
	private UndoManager undoManager; // instancia de UndoManager (administrador de edición)

	@Getter
	private EditorEventHandler editorEventHandler;

	@Getter
	@Setter
	private File currentFile;

	private JToolBar jToolBar2;

	private JButton btnUndo;

	private JButton btnRedo;

	private Separator jSeparator3;

	private JButton btnCopy;

	private JButton btnCut;

	private JButton btnPaste;

	@Getter
	private JInternalFrame ifrmSQLModificado;

	private JScrollPane jScrollPane3;

	@Getter
	private JTextArea txtSQLModificado;

	@Getter
	private JInternalFrame ifrmPDC;

	private JScrollPane jScrollPane4;

	@Getter
	private JTextArea txtPDC;

	@Getter
	private JInternalFrame ifrmLanzaSQLModificado;

	private JScrollPane jScrollPane5;

	@Getter
	private JTextArea txtLanzaSQLModificado;

	@Getter
	private JInternalFrame ifrmLanzaPDC;

	private JScrollPane jScrollPane6;

	@Getter
	private JTextArea txtLanzaPDC;

	@Getter
	private JInternalFrame ifrmSQLH;

	private JScrollPane jScrollPane7;

	@Getter
	private JTextArea txtSQLH;

	@Getter
	private JInternalFrame ifrmPDCH;

	private JScrollPane jScrollPane8;

	@Getter
	private JTextArea txtPDCH;

	@Getter
	private JInternalFrame ifrmLanzaSQLH;

	private JScrollPane jScrollPane9;

	@Getter
	private JTextArea txtLanzaSQLH;

	@Getter
	private JInternalFrame ifrmLanzaPDCH;

	private JScrollPane jScrollPane10;

	@Getter
	private JTextArea txtLanzaPDCH;

	private JInternalFrame ifrmListaObjetos;

	@Getter
	private TableSupport tblListaObjetos;

	@Getter
	private JInternalFrame ifrmTYS;

	private JScrollPane jScrollPane12;

	@Getter
	private JTextArea txtScriptTYS;

	@Getter
	private JInternalFrame ifrmTYB;

	private JScrollPane jScrollPane13;

	@Getter
	private JTextArea txtScriptTYB;

	@Getter
	private JInternalFrame ifrmLanzador;

	private JScrollPane jScrollPane11;

	@Getter
	private JTextArea txtScriptLanza;

	@Getter
	private JInternalFrame internalFramePDC;

	private JScrollPane jScrollPane14;

	@Getter
	private JTextArea txtScriptPDC;
	
	@Getter
	@Setter
	private com.mdsql.bussiness.entities.Type typeSeleccionado;

	/**
	 * Creates new form Principal
	 */
	public FramePrincipal() {
		super();
	}

	@Override
	protected void setupComponents() {

		jToolBar1 = new JToolBar();
        btnLoadScript = new JButton();
        jSeparator1 = new JToolBar.Separator();
        btnCargarScriptObjetos = new JButton();
        jSeparator2 = new JToolBar.Separator();
        btnProcesarScript = new JButton();
        btnSave = new JButton();
        btnExecute = new JButton();
        btnEntregarProcesado = new JButton();
        btnLimpiarScripts = new JButton();
        btnLimpiarSesion = new JButton();
        jSeparator4 = new JToolBar.Separator();
        btnProcesadoEnCurso = new JButton();
        btnRefrescarFichero = new JButton();
        btnInformacionModelo = new JButton();
        jSplitPane1 = new JSplitPane();
        frmSQLScript = new JInternalFrame();
        jScrollPane1 = new JScrollPane();
        txtSQLCode = new JTextArea();
        jToolBar2 = new JToolBar();
        btnUndo = new JButton();
        btnRedo = new JButton();
        jSeparator3 = new JToolBar.Separator();
        btnCopy = new JButton();
        btnCut = new JButton();
        btnPaste = new JButton();
        jPanel1 = new JPanel();
        tabPanel = new JTabbedPane();
        panelVigente = new JPanel();
        ifrmSQLModificado = new JInternalFrame();
        jScrollPane3 = new JScrollPane();
        txtSQLModificado = new JTextArea();
        ifrmPDC = new JInternalFrame();
        jScrollPane4 = new JScrollPane();
        txtPDC = new JTextArea();
        ifrmLanzaSQLModificado = new JInternalFrame();
        jScrollPane5 = new JScrollPane();
        txtLanzaSQLModificado = new JTextArea();
        ifrmLanzaPDC = new JInternalFrame();
        jScrollPane6 = new JScrollPane();
        txtLanzaPDC = new JTextArea();
        panelHistorico = new JPanel();
        ifrmSQLH = new JInternalFrame();
        jScrollPane7 = new JScrollPane();
        txtSQLH = new JTextArea();
        ifrmPDCH = new JInternalFrame();
        jScrollPane8 = new JScrollPane();
        txtPDCH = new JTextArea();
        ifrmLanzaSQLH = new JInternalFrame();
        jScrollPane9 = new JScrollPane();
        txtLanzaSQLH = new JTextArea();
        ifrmLanzaPDCH = new JInternalFrame();
        jScrollPane10 = new JScrollPane();
        txtLanzaPDCH = new JTextArea();
        panelTypes = new JPanel();
        ifrmListaObjetos = new JInternalFrame();
        jScrollPane2 = new JScrollPane();
        tblListaObjetos = new TableSupport();
        ifrmTYS = new JInternalFrame();
        jScrollPane12 = new JScrollPane();
        txtScriptTYS = new JTextArea();
        ifrmTYB = new JInternalFrame();
        jScrollPane13 = new JScrollPane();
        txtScriptTYB = new JTextArea();
        ifrmLanzador = new JInternalFrame();
        jScrollPane11 = new JScrollPane();
        txtScriptLanza = new JTextArea();
        internalFramePDC = new JInternalFrame();
        jScrollPane14 = new JScrollPane();
        txtScriptPDC = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);
        jToolBar1.setMargin(new Insets(3, 3, 3, 3));

        btnLoadScript.setIcon(new ImageIcon(getClass().getResource("/script.png"))); // NOI18N
        btnLoadScript.setToolTipText("Cargar script");
        btnLoadScript.setFocusable(false);
        btnLoadScript.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLoadScript.setMaximumSize(new Dimension(60, 60));
        btnLoadScript.setMinimumSize(new Dimension(60, 60));
        btnLoadScript.setPreferredSize(new Dimension(60, 60));
        btnLoadScript.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnLoadScript);
        jToolBar1.add(jSeparator1);

        btnCargarScriptObjetos.setFocusable(false);
        btnCargarScriptObjetos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCargarScriptObjetos.setMaximumSize(new Dimension(60, 60));
        btnCargarScriptObjetos.setMinimumSize(new Dimension(60, 60));
        btnCargarScriptObjetos.setPreferredSize(new Dimension(60, 60));
        btnCargarScriptObjetos.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnCargarScriptObjetos);
        jToolBar1.add(jSeparator2);

        btnProcesarScript.setFocusable(false);
        btnProcesarScript.setHorizontalTextPosition(SwingConstants.CENTER);
        btnProcesarScript.setMaximumSize(new Dimension(60, 60));
        btnProcesarScript.setMinimumSize(new Dimension(60, 60));
        btnProcesarScript.setPreferredSize(new Dimension(60, 60));
        btnProcesarScript.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnProcesarScript);

        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSave.setMaximumSize(new Dimension(60, 60));
        btnSave.setMinimumSize(new Dimension(60, 60));
        btnSave.setPreferredSize(new Dimension(60, 60));
        btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnSave);

        btnExecute.setFocusable(false);
        btnExecute.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExecute.setMaximumSize(new Dimension(60, 60));
        btnExecute.setMinimumSize(new Dimension(60, 60));
        btnExecute.setPreferredSize(new Dimension(60, 60));
        btnExecute.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnExecute);

        btnEntregarProcesado.setFocusable(false);
        btnEntregarProcesado.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEntregarProcesado.setMaximumSize(new Dimension(60, 60));
        btnEntregarProcesado.setMinimumSize(new Dimension(60, 60));
        btnEntregarProcesado.setPreferredSize(new Dimension(60, 60));
        btnEntregarProcesado.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnEntregarProcesado);

        btnLimpiarScripts.setFocusable(false);
        btnLimpiarScripts.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLimpiarScripts.setMaximumSize(new Dimension(60, 60));
        btnLimpiarScripts.setMinimumSize(new Dimension(60, 60));
        btnLimpiarScripts.setPreferredSize(new Dimension(60, 60));
        btnLimpiarScripts.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnLimpiarScripts);

        btnLimpiarSesion.setFocusable(false);
        btnLimpiarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLimpiarSesion.setMaximumSize(new Dimension(60, 60));
        btnLimpiarSesion.setMinimumSize(new Dimension(60, 60));
        btnLimpiarSesion.setPreferredSize(new Dimension(60, 60));
        btnLimpiarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnLimpiarSesion);
        jToolBar1.add(jSeparator4);

        btnProcesadoEnCurso.setFocusable(false);
        btnProcesadoEnCurso.setHorizontalTextPosition(SwingConstants.CENTER);
        btnProcesadoEnCurso.setMaximumSize(new Dimension(60, 60));
        btnProcesadoEnCurso.setMinimumSize(new Dimension(60, 60));
        btnProcesadoEnCurso.setPreferredSize(new Dimension(60, 60));
        btnProcesadoEnCurso.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnProcesadoEnCurso);

        btnRefrescarFichero.setFocusable(false);
        btnRefrescarFichero.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRefrescarFichero.setMaximumSize(new Dimension(60, 60));
        btnRefrescarFichero.setMinimumSize(new Dimension(60, 60));
        btnRefrescarFichero.setPreferredSize(new Dimension(60, 60));
        btnRefrescarFichero.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnRefrescarFichero);

        btnInformacionModelo.setFocusable(false);
        btnInformacionModelo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInformacionModelo.setMaximumSize(new Dimension(60, 60));
        btnInformacionModelo.setMinimumSize(new Dimension(60, 60));
        btnInformacionModelo.setPreferredSize(new Dimension(60, 60));
        btnInformacionModelo.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(btnInformacionModelo);

        getContentPane().add(jToolBar1, BorderLayout.PAGE_START);

        frmSQLScript.setVisible(true);

        txtSQLCode.setColumns(20);
        txtSQLCode.setRows(5);
        jScrollPane1.setViewportView(txtSQLCode);

        frmSQLScript.getContentPane().add(jScrollPane1, BorderLayout.CENTER);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnUndo.setIcon(new ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btnUndo.setToolTipText("Deshacer");
        btnUndo.setFocusable(false);
        btnUndo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUndo.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(btnUndo);

        btnRedo.setFocusable(false);
        btnRedo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRedo.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(btnRedo);
        jToolBar2.add(jSeparator3);

        btnCopy.setFocusable(false);
        btnCopy.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCopy.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(btnCopy);

        btnCut.setFocusable(false);
        btnCut.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCut.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(btnCut);

        btnPaste.setFocusable(false);
        btnPaste.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPaste.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(btnPaste);

        frmSQLScript.getContentPane().add(jToolBar2, BorderLayout.PAGE_START);

        jSplitPane1.setLeftComponent(frmSQLScript);

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.LINE_AXIS));

        panelVigente.setLayout(new GridLayout(2, 2));

        ifrmSQLModificado.setVisible(true);

        txtSQLModificado.setEditable(false);
        txtSQLModificado.setColumns(20);
        txtSQLModificado.setRows(5);
        jScrollPane3.setViewportView(txtSQLModificado);

        ifrmSQLModificado.getContentPane().add(jScrollPane3, BorderLayout.CENTER);

        panelVigente.add(ifrmSQLModificado);

        ifrmPDC.setVisible(true);

        txtPDC.setEditable(false);
        txtPDC.setColumns(20);
        txtPDC.setRows(5);
        jScrollPane4.setViewportView(txtPDC);

        ifrmPDC.getContentPane().add(jScrollPane4, BorderLayout.CENTER);

        panelVigente.add(ifrmPDC);

        ifrmLanzaSQLModificado.setVisible(true);

        txtLanzaSQLModificado.setEditable(false);
        txtLanzaSQLModificado.setColumns(20);
        txtLanzaSQLModificado.setRows(5);
        jScrollPane5.setViewportView(txtLanzaSQLModificado);

        ifrmLanzaSQLModificado.getContentPane().add(jScrollPane5, BorderLayout.CENTER);

        panelVigente.add(ifrmLanzaSQLModificado);

        ifrmLanzaPDC.setVisible(true);

        txtLanzaPDC.setEditable(false);
        txtLanzaPDC.setColumns(20);
        txtLanzaPDC.setRows(5);
        jScrollPane6.setViewportView(txtLanzaPDC);

        ifrmLanzaPDC.getContentPane().add(jScrollPane6, BorderLayout.CENTER);

        panelVigente.add(ifrmLanzaPDC);

        tabPanel.addTab("Vigente", panelVigente);

        panelHistorico.setLayout(new GridLayout(2, 2));

        ifrmSQLH.setVisible(true);

        txtSQLH.setEditable(false);
        txtSQLH.setColumns(20);
        txtSQLH.setRows(5);
        jScrollPane7.setViewportView(txtSQLH);

        ifrmSQLH.getContentPane().add(jScrollPane7, BorderLayout.CENTER);

        panelHistorico.add(ifrmSQLH);

        ifrmPDCH.setVisible(true);

        txtPDCH.setEditable(false);
        txtPDCH.setColumns(20);
        txtPDCH.setRows(5);
        jScrollPane8.setViewportView(txtPDCH);

        ifrmPDCH.getContentPane().add(jScrollPane8, BorderLayout.CENTER);

        panelHistorico.add(ifrmPDCH);

        ifrmLanzaSQLH.setVisible(true);

        txtLanzaSQLH.setEditable(false);
        txtLanzaSQLH.setColumns(20);
        txtLanzaSQLH.setRows(5);
        jScrollPane9.setViewportView(txtLanzaSQLH);

        ifrmLanzaSQLH.getContentPane().add(jScrollPane9, BorderLayout.CENTER);

        panelHistorico.add(ifrmLanzaSQLH);

        ifrmLanzaPDCH.setVisible(true);

        txtLanzaPDCH.setEditable(false);
        txtLanzaPDCH.setColumns(20);
        txtLanzaPDCH.setRows(5);
        jScrollPane10.setViewportView(txtLanzaPDCH);

        ifrmLanzaPDCH.getContentPane().add(jScrollPane10, BorderLayout.CENTER);

        panelHistorico.add(ifrmLanzaPDCH);

        tabPanel.addTab("Histórico", panelHistorico);

        ifrmListaObjetos.setVisible(true);

        jScrollPane2.setViewportView(tblListaObjetos);

        ifrmListaObjetos.getContentPane().add(jScrollPane2, BorderLayout.CENTER);

        ifrmTYS.setVisible(true);

        txtScriptTYS.setEditable(false);
        txtScriptTYS.setColumns(20);
        txtScriptTYS.setRows(5);
        jScrollPane12.setViewportView(txtScriptTYS);

        ifrmTYS.getContentPane().add(jScrollPane12, BorderLayout.CENTER);

        ifrmTYB.setVisible(true);

        txtScriptTYB.setEditable(false);
        txtScriptTYB.setColumns(20);
        txtScriptTYB.setRows(5);
        jScrollPane13.setViewportView(txtScriptTYB);

        ifrmTYB.getContentPane().add(jScrollPane13, BorderLayout.CENTER);

        ifrmLanzador.setVisible(true);

        txtScriptLanza.setEditable(false);
        txtScriptLanza.setColumns(20);
        txtScriptLanza.setRows(5);
        jScrollPane11.setViewportView(txtScriptLanza);

        ifrmLanzador.getContentPane().add(jScrollPane11, BorderLayout.CENTER);

        internalFramePDC.setVisible(true);

        txtScriptPDC.setEditable(false);
        txtScriptPDC.setColumns(20);
        txtScriptPDC.setRows(5);
        jScrollPane14.setViewportView(txtScriptPDC);

        internalFramePDC.getContentPane().add(jScrollPane14, BorderLayout.CENTER);

        GroupLayout panelTypesLayout = new GroupLayout(panelTypes);
        panelTypes.setLayout(panelTypesLayout);
        panelTypesLayout.setHorizontalGroup(
            panelTypesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelTypesLayout.createSequentialGroup()
                .addGroup(panelTypesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ifrmListaObjetos, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addComponent(ifrmLanzador))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTypesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ifrmTYB)
                    .addComponent(ifrmTYS, GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addComponent(internalFramePDC)))
        );
        panelTypesLayout.setVerticalGroup(
            panelTypesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelTypesLayout.createSequentialGroup()
                .addComponent(ifrmTYS)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ifrmTYB)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(internalFramePDC))
            .addGroup(panelTypesLayout.createSequentialGroup()
                .addComponent(ifrmListaObjetos, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ifrmLanzador, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
        );

        tabPanel.addTab("Types", panelTypes);

        jPanel1.add(tabPanel);

        jSplitPane1.setRightComponent(jPanel1);

        getContentPane().add(jSplitPane1, BorderLayout.CENTER);
	}

	@Override
	protected void initMenuBar() {
		menuBar = new MainMenuBar(this);
	}

	@Override
	protected void initEvents() {
		FramePrincipalActionListener actionListener = new FramePrincipalActionListener(this);
		WindowListener windowListener = new FramePrincipalWindowListener();
		ListSelectionListener listSelectionListener = new ListaObjetosTableListener(this);
		editorEventHandler = new EditorEventHandler(this);
		
		addWindowListener(windowListener);
		addOnLoadListener(actionListener);

		btnLoadScript.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_LOAD_SCRIPT);
		btnCargarScriptObjetos.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_CARGAR_SCRIPT_OBJETOS);
		btnProcesarScript.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_PROCESAR_SCRIPT);
		btnSave.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_SAVE);
		btnExecute.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_EXECUTE);
		btnEntregarProcesado.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_ENTREGAR_PROCESADO);
		btnLimpiarScripts.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_LIMPIAR_SCRIPT);
		btnLimpiarSesion.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_LIMPIAR_SESION);
		btnProcesadoEnCurso.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_PROCESADO_CURSO);
		btnRefrescarFichero.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_REFRESCAR_FICHERO);
		btnInformacionModelo.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_INFORMACION_MODELO);

		btnUndo.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_BTN_UNDO);
		btnRedo.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_BTN_REDO);
		btnCut.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_BTN_CUT);
		btnCopy.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_BTN_COPY);
		btnPaste.setActionCommand(MDSQLConstants.FRAME_PRINCIPAL_BTN_PASTE);

		btnLoadScript.addActionListener(actionListener);
		btnCargarScriptObjetos.addActionListener(actionListener);
		btnProcesarScript.addActionListener(actionListener);
		btnSave.addActionListener(actionListener);
		btnExecute.addActionListener(actionListener);
		btnEntregarProcesado.addActionListener(actionListener);
		btnLimpiarScripts.addActionListener(actionListener);
		btnLimpiarSesion.addActionListener(actionListener);
		btnUndo.addActionListener(actionListener);
		btnRedo.addActionListener(actionListener);
		btnCut.addActionListener(actionListener);
		btnCopy.addActionListener(actionListener);
		btnPaste.addActionListener(actionListener);
		btnProcesadoEnCurso.addActionListener(actionListener);
		btnRefrescarFichero.addActionListener(actionListener);
		btnInformacionModelo.addActionListener(actionListener);
		
		ListSelectionModel rowSM = tblListaObjetos.getSelectionModel();
   		rowSM.addListSelectionListener(listSelectionListener);

		// Manejador de eventos del editor
		txtSQLCode.getDocument().addUndoableEditListener(editorEventHandler);

		// remueve las posibles combinaciones de teclas asociadas por defecto con el
		// JTextArea
		txtSQLCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK), "none"); // remueve
																											// CTRL + X
																											// ("Cortar")
		txtSQLCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK), "none"); // remueve
																											// CTRL + C
																											// ("Copiar")
		txtSQLCode.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK), "none"); // remueve
																											// CTRL + V
																											// ("Pegar")
	}

	@Override
	protected void initModels() {
		Cabecera cabecera = MDSQLUIHelper.createCabeceraTabla(MDSQLConstants.FRAME_PRINCIPAL_TYPES_TABLA_CABECERA);
		tblListaObjetos.initModel(new FramePrincipalTypesTableModel(cabecera));
	}

	@Override
	protected void initialState() {
		undoManager = new UndoManager(); // construye una instancia de UndoManager
		undoManager.setLimit(50); // le asigna un límite al buffer de ediciones

		disableEditionButtons();
		
		txtSQLCode.setEditable(Boolean.FALSE);
		txtSQLCode.setEnabled(Boolean.FALSE);
		
		txtScriptLanza.setEditable(Boolean.FALSE);
	}

	@Override
	protected void setupLiterals() {
		setTitle("MDSQL");
		
		btnLoadScript.setIcon(new ImageIcon(getClass().getResource("/script.png"))); // NOI18N
        btnLoadScript.setToolTipText("Cargar script");
        btnCargarScriptObjetos.setIcon(new ImageIcon(getClass().getResource("/folder.png"))); // NOI18N
        btnCargarScriptObjetos.setText("TYPE");
        btnCargarScriptObjetos.setToolTipText("Cargar script Objetos");
        btnProcesarScript.setIcon(new ImageIcon(getClass().getResource("/play.png"))); // NOI18N
        btnProcesarScript.setToolTipText("Procesar script");
        btnSave.setIcon(new ImageIcon(getClass().getResource("/floppy-disk.png"))); // NOI18N
        btnSave.setToolTipText("Guardar archivo");
        btnExecute.setIcon(new ImageIcon(getClass().getResource("/checking.png"))); // NOI18N
        btnExecute.setToolTipText("Ejecutar script");
        btnEntregarProcesado.setIcon(new ImageIcon(getClass().getResource("/execution.png"))); // NOI18N
        btnEntregarProcesado.setToolTipText("Entregar procesado");
        btnLimpiarScripts.setIcon(new ImageIcon(getClass().getResource("/clean.png"))); // NOI18N
        btnLimpiarScripts.setToolTipText("Limpiar scripts");
        btnLimpiarSesion.setIcon(new ImageIcon(getClass().getResource("/trash.png"))); // NOI18N
        btnLimpiarSesion.setToolTipText("Limpiar sesión");
        btnProcesadoEnCurso.setIcon(new ImageIcon(getClass().getResource("/info.png"))); // NOI18N
        btnProcesadoEnCurso.setToolTipText("Procesado en curso");
        btnRefrescarFichero.setIcon(new ImageIcon(getClass().getResource("/file.png"))); // NOI18N
        btnRefrescarFichero.setToolTipText("Refrescar fichero");
        btnInformacionModelo.setIcon(new ImageIcon(getClass().getResource("/information.png"))); // NOI18N
        btnInformacionModelo.setToolTipText("Información del modelo");

        btnUndo.setIcon(new ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btnRedo.setIcon(new ImageIcon(getClass().getResource("/redo.png"))); // NOI18N
        btnCopy.setIcon(new ImageIcon(getClass().getResource("/copy.png"))); // NOI18N
        btnCut.setIcon(new ImageIcon(getClass().getResource("/cut.png"))); // NOI18N
        btnPaste.setIcon(new ImageIcon(getClass().getResource("/paste.png"))); // NOI18N
		btnUndo.setToolTipText("Deshacer");
		btnRedo.setToolTipText("Rehacer");
		btnCopy.setToolTipText("Copiar");
		btnCut.setToolTipText("Cortar");
		btnPaste.setToolTipText("Pegar");
	}

	/**
	 * Actualiza el estado de las opciones "Deshacer" y "Rehacer".
	 */
	public void updateEditionControls() {
		// averigua si se pueden deshacer los cambios en el documento actual
		boolean canUndo = undoManager.canUndo();
		// averigua si se pueden rehacer los cambios en el documento actual
		boolean canRedo = undoManager.canRedo();

		// activa o desactiva las opciones en la barra de herramientas
		btnUndo.setEnabled(canUndo);
		btnRedo.setEnabled(canRedo);
	}
	
	public void resetFrames() {
		ifrmSQLModificado.setTitle(StringUtils.EMPTY);
		ifrmPDC.setTitle(StringUtils.EMPTY);
		ifrmLanzaSQLModificado.setTitle(StringUtils.EMPTY);
		ifrmLanzaPDC.setTitle(StringUtils.EMPTY);
		ifrmSQLH.setTitle(StringUtils.EMPTY);
		ifrmLanzaSQLH.setTitle(StringUtils.EMPTY);
		ifrmPDCH.setTitle(StringUtils.EMPTY);
		ifrmLanzaPDCH.setTitle(StringUtils.EMPTY);
		ifrmListaObjetos.setTitle(StringUtils.EMPTY);
		ifrmLanzador.setTitle(StringUtils.EMPTY);
		ifrmTYB.setTitle(StringUtils.EMPTY);
		ifrmTYS.setTitle(StringUtils.EMPTY);
		internalFramePDC.setTitle(StringUtils.EMPTY);
		
		txtSQLModificado.setText(StringUtils.EMPTY);
		txtPDC.setText(StringUtils.EMPTY);
		txtLanzaSQLModificado.setText(StringUtils.EMPTY);
		txtLanzaPDC.setText(StringUtils.EMPTY);
		txtSQLH.setText(StringUtils.EMPTY);
		txtLanzaSQLH.setText(StringUtils.EMPTY);
		txtPDCH.setText(StringUtils.EMPTY);
		txtLanzaPDCH.setText(StringUtils.EMPTY);
		txtScriptTYB.setText(StringUtils.EMPTY);
		txtScriptTYS.setText(StringUtils.EMPTY);
		txtScriptPDC.setText(StringUtils.EMPTY);
		txtScriptLanza.setText(StringUtils.EMPTY);
		
		((FramePrincipalTypesTableModel) tblListaObjetos.getModel()).clearData();
	}

	/**
	 * 
	 */
	public void disableTabs() {
		this.getTabPanel().setEnabledAt(0, Boolean.FALSE);
		this.getTabPanel().setEnabledAt(1, Boolean.FALSE);
		this.getTabPanel().setEnabledAt(2, Boolean.FALSE);
	}

	/**
	 * 
	 */
	public void disableEditionButtons() {
		btnUndo.setEnabled(Boolean.FALSE);
		btnRedo.setEnabled(Boolean.FALSE);
	}
}
