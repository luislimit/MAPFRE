package com.mdsql.ui;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author federico
 */
@Slf4j
public class PanelLogotipo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3034469653390494133L;
	private transient BufferedImage image;

	public PanelLogotipo(String logotipo) {
		try {
			image = ImageIO.read(new File("./" + logotipo));
		} catch (IOException ex) {
			LogWrapper.warn(log, "ERROR:", ex);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
	}

}