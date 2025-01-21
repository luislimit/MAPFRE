/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.ui.listener.window;

import com.mdsql.ui.PantallaEjecutarScriptInicialEntornoPrueba;
import com.mdsql.ui.listener.PantallaEjecutarScriptInicialEntornoPruebaListener;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.exceptions.ServiceException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author LVARONA
 */
public class PantallaEjecutarScriptInicialEntornoPruebaWindowListener
        extends PantallaEjecutarScriptInicialEntornoPruebaListener
        implements WindowListener{

    public PantallaEjecutarScriptInicialEntornoPruebaWindowListener(PantallaEjecutarScriptInicialEntornoPrueba pantalla) {
        super(pantalla);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            rechazarProcesado();
        } catch (ServiceException ex) {
            MDSQLUIHelper.showErrors(pantalla, ex);
        }
        pantalla.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
