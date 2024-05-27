package com.mdsql.ui.renderer;

import java.awt.Component;
import java.util.Objects;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.BBDD;

public class BBDDRenderer extends BasicComboBoxRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3517770689739103773L;

	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		BBDD bbdd = (BBDD) value;

		if (Objects.isNull(bbdd)) {
			setText(StringUtils.EMPTY);
		} else {
			String text = bbdd.getNombreBBDD();
			if (text.length() > 20) {
				text = text.substring(0, 20) + "...";
				this.setToolTipText(bbdd.getNombreBBDD());
			}
			setText(text);
		}
		return this;
	}
}
