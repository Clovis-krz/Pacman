package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.entities.Ghost;
import javax.swing.*;


public class GhostRenderer extends JComponent {
	private final Ghost element;

	private static final int RADIUS = 5;

	public GhostRenderer(Ghost ghost) {
		this.element = ghost;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color color;
		switch (element.getState()) {
			case VULNERABLE:
				color = new Color(41, 0, 204);
				break;

			default:
				color = Color.RED;
				break;
		}

		int centerX = element.getX() + (Main.ELEMENT_SIZE / 2);
		int centerY = element.getY() + (Main.ELEMENT_SIZE / 2);

		g.setColor(color);
		g.fillArc(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS, 0, 180);
		g.fillRect(centerX - RADIUS, centerY, 2 * RADIUS, 2 * RADIUS);
	}
}
