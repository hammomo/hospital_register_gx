package com.hospital.register.client.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private BufferedImage background = null;
	
	public MainPanel() {
		try {
			background = ImageIO.read(getClass().getResource("seaside.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 	// 强制类型转化
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (background != null) {
			// 绘制背景
			g2d.drawImage(background, 6, 6, getWidth() - 10, getHeight() - 10, null);
		}
		// 绘制顶部标签
		g2d.setClip(0, 0, getWidth(), 50);
		g2d.setColor(new Color(255, 235, 205, 90));
		g2d.fillRoundRect(1, 3, getWidth() - 2, getHeight() - 1, 20, 20);
		g2d.setClip(null);

		// 绘制边框
		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(new Color(106, 90, 205));
		g2d.drawRoundRect(3, 3, getWidth() - 7, getHeight() - 7, 20, 20);
	}

	@Override
	protected void paintChildren(Graphics g) {
		// TODO Auto-generated method stub
		super.paintChildren(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		// TODO Auto-generated method stub
		super.paintBorder(g);
	}

}
