package com.mdsql.ui.renderer;

import com.mdsql.bussiness.entities.EntornoPrueba;
import org.apache.commons.lang3.StringUtils;

public class CmbEntornoPruebaRenderer extends CmbStringRenderer {

    @Override
    public String getTexto(Object value) {
        return (value != null ? ((EntornoPrueba) value).getNombreEntorno() : StringUtils.EMPTY);
    }
}
