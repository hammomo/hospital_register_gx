package com.hospital.register.client.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;

public class ExitButton extends BasicButtonUI {
	
	private BufferedImage exit = null, exitPressed = null;
	
	public ExitButton() {
		try {
			exit = ImageIO.read(getClass().getResource("exit.png"));
			exitPressed = ImageIO.read(getClass().getResource("05.png"));
//			exitFocused = ImageIO.read(getClass().getResource("07.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void installDefaults(AbstractButton b) {
		super.installDefaults(b);
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		if (exit != null) {
			g.drawImage(exit, 0, 0, c.getHeight(), c.getHeight(), null);
		}
		super.paint(g, c);
	}

	@Override
	protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
		// TODO Auto-generated method stub
		super.paintIcon(g, c, iconRect);
	}

	@Override
	protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
		// TODO Auto-generated method stub
		super.paintText(g, c, textRect, text);
	}

	@Override
	protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
		// TODO Auto-generated method stub
		super.paintText(g, b, textRect, text);
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect,
			Rectangle iconRect) {
//		g.drawImage(exitPressed, 0, 0, b.getHeight(), b.getHeight(), null);
		super.paintFocus(g, b, viewRect, textRect, iconRect);
	}

	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		g.drawImage(exitPressed, 0, 0, b.getHeight(), b.getHeight(), null);
		super.paintButtonPressed(g, b);
	}

	
}
