package com.mdsql.ui.renderer;

import com.mdsql.bussiness.entities.TipoElemento;
import org.apache.commons.lang3.StringUtils;

public class CmbTipoElementoRenderer extends CmbStringRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3517770689739103773L;

    @Override
    public String getTexto(Object value) {
        return (value != null ? ((TipoElemento) value).getDescripcionElemento() : StringUtils.EMPTY);
    }
}
