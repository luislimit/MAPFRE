package com.mdsql.ui.listener.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.mdsql.ui.PantallaMantenimientoValidacionesProgramadas;
import com.mdsql.ui.utils.ListenerSupport;

public class PantallaMantenimientoValidacionesProgramadasWindowListener extends ListenerSupport implements WindowListener {

    private final PantallaMantenimientoValidacionesProgramadas pantalla;


    public PantallaMantenimientoValidacionesProgramadasWindowListener(PantallaMantenimientoValidacionesProgramadas pantalla) {
        this.pantalla = pantalla;
    }


/**
 * Al salir, ya sea por la X o por Cancelar, verificar si hay cambios pendientes
 * @param e 
 */
    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {
        pantalla.cerrarVentana();
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
