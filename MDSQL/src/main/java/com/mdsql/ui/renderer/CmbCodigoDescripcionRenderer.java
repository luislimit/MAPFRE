package com.mdsql.ui.renderer;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import org.apache.commons.lang3.StringUtils;

public class CmbCodigoDescripcionRenderer extends CmbStringRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3517770689739103773L;

    @Override
    public String getTexto(Object value) {
        return (value != null ? ((CodigoDescripcion) value).getDescripcion() : StringUtils.EMPTY);
    }

}
