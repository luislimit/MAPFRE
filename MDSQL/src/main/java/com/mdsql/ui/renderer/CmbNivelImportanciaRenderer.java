package com.mdsql.ui.renderer;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.NivelImportancia;

public class CmbNivelImportanciaRenderer extends CmbStringRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 3517770689739103773L;

    @Override
    public String getTexto(Object value) {
        return (value != null ? ((NivelImportancia) value).getDescripcionNivelAviso() : StringUtils.EMPTY);
    }
}
