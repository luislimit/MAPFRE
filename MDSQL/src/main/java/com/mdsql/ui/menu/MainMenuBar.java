/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.mdsql.ui.listener.MenuListener;
import com.mdsql.ui.listener.MenuMantenimientoActionListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.MenuSupport;

/**
 *
 * @author federico
 */
public class MainMenuBar extends MenuSupport {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -519829056044806094L;
	
	private FrameSupport frameParent;

    private JMenu mnuPermisos;
    private JMenu mnuVariables;
    private JMenuItem mnuItemVariables;
    private JMenu mnuEntornos;
    private JMenuItem mnuItemEntornos;
    private JMenuItem mnuNotasModelos;
    private JMenuItem mnuItemNotasModelos;
    private JMenu mnuConsultas;
    private JMenuItem mnuConsultaHistoricoCambios;
    private JMenuItem mnuConsultaPeticiones;
    private JMenu mnuScriptInicial;
    private JMenuItem mnuMantenimientoHistorico;
    
    private JMenuItem mnuPermisosGenerales;
    private JMenu mnuPermisosPersonalizados;
    private JMenuItem mnuConsultaPermisos;
	private JMenuItem mnuMantenimientoPermisos;
	private JMenuItem mnuGenerarPermisos;
    
    private JMenuItem mnuMantenimientoEntornosPruebas;
    private JMenuItem mnuEjecucionScriptInicial;
//    private JMenuItem mnuConfiguracionEntornosPrueba;
    
    public MainMenuBar(FrameSupport frameParent) {
        super();
        this.frameParent = frameParent;
    }

	/**
	 *
	 */
	protected void initComponents() {
        mnuPermisos = new JMenu();
        
        mnuPermisosGenerales = new JMenuItem();
        mnuPermisosGenerales.setActionCommand(MDSQLConstants.MNU_PERMISOS_GENERALES); // NOI18N
        mnuPermisos.add(mnuPermisosGenerales);
      
        mnuPermisosPersonalizados = new JMenu();
        mnuConsultaPermisos = new JMenuItem();
        mnuConsultaPermisos.setActionCommand(MDSQLConstants.MNU_CONSULTA_PERMISOS); // NOI18N
        mnuPermisosPersonalizados.add(mnuConsultaPermisos);
        
        mnuMantenimientoPermisos = new JMenuItem();
        mnuMantenimientoPermisos.setActionCommand(MDSQLConstants.MNU_MANTENIMIENTO_PERMISOS); // NOI18N
        mnuPermisosPersonalizados.add(mnuMantenimientoPermisos);
        
        mnuGenerarPermisos = new JMenuItem();
        mnuGenerarPermisos.setActionCommand(MDSQLConstants.MNU_GENERAR_PERMISOS); // NOI18N
        mnuPermisosPersonalizados.add(mnuGenerarPermisos);
        
        mnuPermisos.add(mnuPermisosPersonalizados);
        
        mnuEntornos = new JMenu();
        mnuItemEntornos = new JMenuItem();
        mnuItemEntornos.setActionCommand(MDSQLConstants.MNU_ENTORNOS); // NOI18N
        mnuEntornos.add(mnuItemEntornos);
        
        mnuVariables = new JMenu();
        mnuItemVariables = new JMenuItem();
        mnuItemVariables.setActionCommand(MDSQLConstants.MNU_VARIABLES); // NOI18N
        mnuVariables.add(mnuItemVariables);
        
        mnuNotasModelos = new JMenu();
        mnuItemNotasModelos = new JMenuItem();
        mnuItemNotasModelos.setActionCommand(MDSQLConstants.MNU_NOTAS_MODELOS); // NOI18N
        mnuNotasModelos.add(mnuItemNotasModelos);
        
        mnuConsultas = new JMenu();
        mnuConsultaHistoricoCambios = new JMenuItem();
        mnuConsultaHistoricoCambios.setActionCommand(MDSQLConstants.MNU_CONSULTA_HISTORICO_CAMBIOS); // NOI18N
        mnuConsultas.add(mnuConsultaHistoricoCambios);
        
        mnuConsultaPeticiones = new JMenuItem();
        mnuConsultaPeticiones.setActionCommand(MDSQLConstants.MNU_CONSULTA_PETICIONES); // NOI18N
        mnuConsultas.add(mnuConsultaPeticiones);
        
        mnuScriptInicial = new JMenu();
        mnuMantenimientoEntornosPruebas = new JMenuItem();
        mnuMantenimientoEntornosPruebas.setActionCommand(MDSQLConstants.MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS); // NOI18N
        mnuScriptInicial.add(mnuMantenimientoEntornosPruebas);
        
        mnuEjecucionScriptInicial = new JMenuItem();
        mnuEjecucionScriptInicial.setActionCommand(MDSQLConstants.MNU_EJECUCION_SCRIPT_INICIAL); // NOI18N
        mnuScriptInicial.add(mnuEjecucionScriptInicial);
        
//        mnuConfiguracionEntornosPrueba = new JMenuItem();
//        mnuConfiguracionEntornosPrueba.setActionCommand(MDSQLConstants.MNU_CONFIGURACION_ENTORNOS_PRUEBA); // NOI18N
//        mnuScriptInicial.add(mnuConfiguracionEntornosPrueba);
        
        mnuMantenimientoHistorico = new JMenuItem();
        mnuMantenimientoHistorico.setActionCommand(MDSQLConstants.MNU_MANTENIMIENTO_HISTORICO); // NOI18N

        add(mnuPermisos);
        add(mnuEntornos);
        add(mnuVariables);
        add(mnuNotasModelos);
        add(mnuConsultas);
        add(mnuScriptInicial);
        add(mnuMantenimientoHistorico);
    }
	
	/**
	 *
	 */
	protected void setupLiterals() {
		mnuPermisos.setText(literales.getLiteral("menu.permisos"));
		mnuPermisosGenerales.setText(literales.getLiteral("menu.permisos.generales"));
		mnuPermisosPersonalizados.setText(literales.getLiteral("menu.permisos.personalizados"));
		mnuConsultaPermisos.setText(literales.getLiteral("menu.permisos.personalizados.consulta"));
		mnuMantenimientoPermisos.setText(literales.getLiteral("menu.permisos.personalizados.mantenimiento"));
		mnuGenerarPermisos.setText(literales.getLiteral("menu.permisos.personalizados.generar"));
        mnuEntornos.setText(literales.getLiteral("menu.entornos"));
        mnuItemEntornos.setText(literales.getLiteral("menu.entornos"));
        mnuVariables.setText(literales.getLiteral("menu.variables"));
        mnuItemVariables.setText(literales.getLiteral("menu.variables"));
        mnuNotasModelos.setText(literales.getLiteral("menu.notasModelos"));
        mnuItemNotasModelos.setText(literales.getLiteral("menu.notasModelos"));
        mnuConsultas.setText(literales.getLiteral("menu.consultas"));
        mnuConsultaHistoricoCambios.setText(literales.getLiteral("menu.consultas.consultaHistorico"));
        mnuConsultaPeticiones.setText(literales.getLiteral("menu.consultas.consultaPeticiones"));
        mnuScriptInicial.setText(literales.getLiteral("menu.scriptInicial"));
        mnuMantenimientoEntornosPruebas.setText(literales.getLiteral("menu.scriptInicial.mantenimientoEntornos"));
        mnuEjecucionScriptInicial.setText(literales.getLiteral("menu.scriptInicial.ejecucionScript"));
//        mnuConfiguracionEntornosPrueba.setText(literales.getLiteral("menu.scriptInicial.configuracionEntornos"));
        mnuMantenimientoHistorico.setText(literales.getLiteral("menu.mantenimientoHistorico"));
	}
	
	/**
	 *
	 */
	protected void initEvents() {
		ActionListener menuActionListener = new MenuListener(frameParent);
        ActionListener menuMantenimientoActionListener = new MenuMantenimientoActionListener(frameParent);

        mnuPermisosGenerales.addActionListener(menuMantenimientoActionListener);

        mnuConsultaPermisos.addActionListener(menuMantenimientoActionListener);
		mnuMantenimientoPermisos.addActionListener(menuActionListener);
		mnuGenerarPermisos.addActionListener(menuActionListener);
        
		mnuItemEntornos.addActionListener(menuMantenimientoActionListener);
        mnuItemVariables.addActionListener(menuMantenimientoActionListener);
        mnuItemNotasModelos.addActionListener(menuMantenimientoActionListener);
        mnuConsultaHistoricoCambios.addActionListener(menuActionListener);
        mnuConsultaPeticiones.addActionListener(menuActionListener);
        
        mnuMantenimientoEntornosPruebas.addActionListener(menuMantenimientoActionListener);
        mnuEjecucionScriptInicial.addActionListener(menuMantenimientoActionListener);
//        mnuConfiguracionEntornosPrueba.addActionListener(menuActionListener);

        mnuMantenimientoHistorico.addActionListener(menuMantenimientoActionListener);
	}
}
