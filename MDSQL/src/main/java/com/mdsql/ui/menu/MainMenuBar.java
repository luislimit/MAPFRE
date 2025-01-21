/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.menu;

import com.mdsql.ui.listener.MenuMantenimientoActionListener;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.utils.LiteralesSingleton;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author federico
 */
@Slf4j
public class MainMenuBar extends JMenuBar {

    /**
     *
     */
    private static final long serialVersionUID = -519829056044806094L;
    protected LiteralesSingleton literales;

    private final FrameSupport frameParent;

    private JMenu mnuPermisos;
    private JMenu mnuVariables;
    private JMenuItem mnuItemVariables;
    private JMenu mnuEntornos;
    private JMenuItem mnuItemEntornos;
    private JMenuItem mnuNotasModelos;
    private JMenuItem mnuItemNotasModelos;
    private JMenu mnuConsultas;
    private JMenuItem mnuConsultaHistoricoCambios;
    private JMenuItem mnuConsultaHistoricoCambiosModelo;
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

    private JMenu mnuAvisosValidaciones;
    private JMenuItem mnuAvisosPorObjeto;
    private JMenuItem mnuValidacionesProgramadas;

    private JMenu mnuDiagramas;
    private JMenuItem mnuMantenimientoDiagramas;
    private JMenuItem mnuConsultaDiagramas;

    public MainMenuBar(FrameSupport frameParent) {
        this.frameParent = frameParent;
        initialize();
    }

    private void initialize() {
        try {
            initComponents();
            initLiterals();
            initEvents();
        } catch (IOException e) {
            log.warn("ERROR:", e);
        }
    }

    /**
     *
     */
    //@Override
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

        mnuConsultaHistoricoCambiosModelo = new JMenuItem();
        mnuConsultaHistoricoCambiosModelo.setActionCommand(MDSQLConstants.MNU_CONSULTA_HISTORICO_CAMBIOS_MODELO); // NOI18N
        mnuConsultas.add(mnuConsultaHistoricoCambiosModelo);

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

        // Menu avisos y validaciones
        mnuAvisosValidaciones = new JMenu();
        mnuAvisosValidaciones.setActionCommand(MDSQLConstants.MNU_AVISOS_VALIDACIONES); // NOI18N

        mnuAvisosPorObjeto = new JMenuItem();
        mnuAvisosPorObjeto.setActionCommand(MDSQLConstants.MNU_AVISOS_OBJETO);
        mnuAvisosValidaciones.add(mnuAvisosPorObjeto);

        mnuValidacionesProgramadas = new JMenuItem();
        mnuValidacionesProgramadas.setActionCommand(MDSQLConstants.MNU_VALIDACIONES_PROGRAMADAS); // NOI18N
        mnuAvisosValidaciones.add(mnuValidacionesProgramadas);

        // Menu Diagramas
        mnuDiagramas = new JMenu();
        mnuDiagramas.setActionCommand(MDSQLConstants.MNU_DIAGRAMAS); // NOI18N

        mnuMantenimientoDiagramas = new JMenuItem();
        mnuMantenimientoDiagramas.setActionCommand(MDSQLConstants.MNU_MANTENIMIENTO_DIAGRAMAS); // NOI18N
        mnuDiagramas.add(mnuMantenimientoDiagramas);

        mnuConsultaDiagramas = new JMenuItem();
        mnuConsultaDiagramas.setActionCommand(MDSQLConstants.MNU_CONSULTA_DIAGRAMAS); // NOI18N
        mnuDiagramas.add(mnuConsultaDiagramas);

        //Añadir los menús al principal
        add(mnuPermisos);
        add(mnuEntornos);
        add(mnuVariables);
        add(mnuNotasModelos);
        add(mnuConsultas);
        add(mnuScriptInicial);

        add(mnuAvisosValidaciones);
        add(mnuDiagramas);

        add(mnuMantenimientoHistorico);
    }

    /**
     *
     */
    //@Override
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
        mnuConsultaHistoricoCambiosModelo.setText(literales.getLiteral("menu.consultas.consultaHistorico.modelo"));
        mnuConsultaPeticiones.setText(literales.getLiteral("menu.consultas.consultaPeticiones"));
        mnuScriptInicial.setText(literales.getLiteral("menu.scriptInicial"));
        mnuMantenimientoEntornosPruebas.setText(literales.getLiteral("menu.scriptInicial.mantenimientoEntornos"));
        mnuEjecucionScriptInicial.setText(literales.getLiteral("menu.scriptInicial.ejecucionScript"));
//        mnuConfiguracionEntornosPrueba.setText(literales.getLiteral("menu.scriptInicial.configuracionEntornos"));

        mnuMantenimientoHistorico.setText(literales.getLiteral("menu.mantenimientoHistorico"));

//
        mnuAvisosValidaciones.setText(literales.getLiteral("menu.avisosValidaciones"));
        mnuAvisosPorObjeto.setText(literales.getLiteral("menu.avisosPorObjeto"));
        mnuValidacionesProgramadas.setText(literales.getLiteral("menu.validacionesProgramadas"));
        //
        mnuDiagramas.setText(literales.getLiteral("menu.diagramas"));
        mnuMantenimientoDiagramas.setText(literales.getLiteral("menu.mantenimientoDiagramas"));
        mnuConsultaDiagramas.setText(literales.getLiteral("menu.consultaDiagramas"));
    }

    /**
     *
     */
    // @Override
    protected void initEvents() {
        //      ActionListener menuActionListener = new MenuListener(frameParent);
        ActionListener menuMantenimientoActionListener = new MenuMantenimientoActionListener(frameParent);

        mnuPermisosGenerales.addActionListener(menuMantenimientoActionListener);

        mnuConsultaPermisos.addActionListener(menuMantenimientoActionListener);
        mnuMantenimientoPermisos.addActionListener(menuMantenimientoActionListener);
        mnuGenerarPermisos.addActionListener(menuMantenimientoActionListener);

        mnuItemEntornos.addActionListener(menuMantenimientoActionListener);
        mnuItemVariables.addActionListener(menuMantenimientoActionListener);
        mnuItemNotasModelos.addActionListener(menuMantenimientoActionListener);
        mnuConsultaHistoricoCambios.addActionListener(menuMantenimientoActionListener);
        mnuConsultaHistoricoCambiosModelo.addActionListener(menuMantenimientoActionListener);
        mnuConsultaPeticiones.addActionListener(menuMantenimientoActionListener);

        mnuMantenimientoEntornosPruebas.addActionListener(menuMantenimientoActionListener);
        mnuEjecucionScriptInicial.addActionListener(menuMantenimientoActionListener);
//        mnuConfiguracionEntornosPrueba.addActionListener(menuActionListener);

        mnuMantenimientoHistorico.addActionListener(menuMantenimientoActionListener);

        mnuAvisosPorObjeto.addActionListener(menuMantenimientoActionListener);
        mnuValidacionesProgramadas.addActionListener(menuMantenimientoActionListener);
        //
        mnuMantenimientoDiagramas.addActionListener(menuMantenimientoActionListener);
        mnuConsultaDiagramas.addActionListener(menuMantenimientoActionListener);
    }

    private void initLiterals() throws IOException {
        literales = LiteralesSingleton.getInstance();

        setupLiterals();
    }
}
