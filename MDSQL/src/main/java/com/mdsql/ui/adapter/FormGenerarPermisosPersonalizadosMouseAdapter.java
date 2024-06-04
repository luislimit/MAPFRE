package com.mdsql.ui.adapter;

import com.mdsql.ui.form.FormGenerarPermisosPersonalizados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author LVARONA
 */
public class FormGenerarPermisosPersonalizadosMouseAdapter extends MouseAdapter {

    private FormGenerarPermisosPersonalizados pantalla;
    
    public void setPantalla(FormGenerarPermisosPersonalizados pantalla) {
        this.pantalla = pantalla;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2) {
            if (e.getSource() instanceof JTable) {
                JTable table = ((JTable) e.getSource());                
                int row = table.getSelectedRow();
                int columnPeticion = (table == pantalla.getTblPermisos()) ? 9 : 7;                
                String codPeticion = (String) table.getValueAt(row, columnPeticion);
                pantalla.getTxtPeticion().setText(codPeticion);
            }
        }
    }
    
}
