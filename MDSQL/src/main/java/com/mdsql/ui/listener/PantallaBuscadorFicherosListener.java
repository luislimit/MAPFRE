package com.mdsql.ui.listener;

import com.mdsql.bussiness.entities.Session;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mdsql.ui.PantallaBuscadorFicheros;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.ConfigurationSingleton;
import java.io.IOException;

public class PantallaBuscadorFicherosListener extends ListenerSupport implements ActionListener, OnLoadListener {

    private final PantallaBuscadorFicheros pantalla;
    private String rutaInicial;

    public PantallaBuscadorFicherosListener(PantallaBuscadorFicheros pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj.equals(pantalla.getBtnAceptar())) {
            pantalla.getReturnParams().put("RutaInicial",
                    pantalla.getTxtRuta().getText());
            pantalla.dispose();
        } else if (obj.equals(pantalla.getBtnCancelar())) {
            pantalla.dispose();
        } else if (obj.equals(pantalla.getBtnRutaDefecto())) {
            pantalla.getTxtRuta().setText(rutaInicial);
        }
    }

    @Override
    public void onLoad() {
        try {
            //Cargamos la ruta por defecto en variable global x si desea restablecer
            ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
            rutaInicial = configuration.getConfig("RutaDefectoScripts");
            //Tomamos la ruta establecida en la sesión
            Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
            String ruta = session.getSelectedRoute();
            //Si no hay ruta previamente seleccionada, cargamos el valor por defecto
            if (ruta == null || ruta.isEmpty()) {
                ruta = rutaInicial;
            }
            pantalla.getTxtRuta().setText(ruta);
        } catch (IOException e) {
            MDSQLUIHelper.showErrors(pantalla, e);
        }
    }
}
