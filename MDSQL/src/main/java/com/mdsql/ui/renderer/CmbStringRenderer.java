/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.ui.renderer;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LVARONA
 */
public class CmbStringRenderer extends BasicComboBoxRenderer {

    private static final long serialVersionUID = 3517770689739103773L;

    /**
     * Se debe sobrescribir si el combo muestra la información de otra forma
     *
     * @param value
     * @return
     */
    public String getTexto(Object value) {
        return value != null ? value.toString() : StringUtils.EMPTY;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(getTexto(value));

        return this;
    }

}
