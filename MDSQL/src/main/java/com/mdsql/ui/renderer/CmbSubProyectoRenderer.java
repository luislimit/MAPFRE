package com.mdsql.ui.renderer;

import com.mdsql.bussiness.entities.SubProyecto;
import java.awt.Component;
import java.util.Objects;
import javax.swing.JList;
import org.apache.commons.lang3.StringUtils;

public class CmbSubProyectoRenderer extends CmbStringRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3517770689739103773L;

    @Override
    public String getTexto(Object value) {
        SubProyecto subProyecto = (SubProyecto) value;
        String text;
        if (Objects.isNull(subProyecto)) {
            text = StringUtils.EMPTY;
        } else {
            // Ellipsis
            text = subProyecto.getDescripcionSubProyecto();
            if (text.length() > 20) {
                text = text.substring(0, 20) + "...";
            }
        }
        return text;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String text = getTexto(value);
        if (text.length() > 20) {
            SubProyecto subProyecto = (SubProyecto) value;
            this.setToolTipText(subProyecto.getDescripcionSubProyecto());
        }
        setText(text);
        return this;
    }
}
