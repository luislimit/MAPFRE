package com.mdval.ui.utils;


import java.awt.Dimension;

import javax.swing.JInternalFrame;

/**
 * @author federico
 *
 */
public class InternalFrameSupport extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -637526827846474731L;
	

	/**
	 * 
	 */
	public InternalFrameSupport() {
		super();
	}
	
	/**
	 * @param width
	 * @param height
	 */
	public void setBounds(int width, int height) {
		Dimension dimension = new Dimension(width, height);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setResizable(Boolean.TRUE);
	}

	/**
	 * 
	 */
	public void forceRepaint() {
		// Corregir bug de no mostrado de resultados redimensionando la pantalla en 1
		// punto
		Integer width = getWidth();
		Integer height = getHeight();

		// sumamos 1 punto por dimension y redimensionamos
		Integer newWidth = width + 1;
		Integer newHeight = height + 1;
		setBounds(newWidth, newHeight);

		// Volver al tamaño original
		setBounds(width, height);
	}
}
