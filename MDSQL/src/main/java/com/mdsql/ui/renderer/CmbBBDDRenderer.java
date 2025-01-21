package com.mdsql.ui.renderer;

import com.mdsql.bussiness.entities.BBDD;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class CmbBBDDRenderer extends CmbStringRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3517770689739103773L;

    @Override
    public String getTexto(Object value) {

        if (Objects.isNull(value)) {
            return StringUtils.EMPTY;
        }
        BBDD bbdd = (BBDD) value;
        String text = bbdd.getNombreBBDD();
        if (text.length() > 20) {
            text = text.substring(0, 20) + "...";
            this.setToolTipText(bbdd.getNombreBBDD());
        }
        return (text);
    }

}
