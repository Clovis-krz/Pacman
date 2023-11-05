package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.entities.Pacman;
import javax.swing.*;


public class PacmanRenderer extends JComponent {
	private static final int RADIUS = 7;

	private final Pacman element;

	public PacmanRenderer(Pacman pacman) {
		this.element = pacman;
		this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.YELLOW);

		int centerX = element.getX() + (Main.ELEMENT_SIZE / 2);
		int centerY = element.getY() + (Main.ELEMENT_SIZE / 2);

		Color color;
		switch (element.getState()) {
			case SUPER:
				color = Color.ORANGE;
				break;

			case INVISIBLE:
				color = new Color(255, 255, 163);
				break;

			default:
				color = Color.YELLOW;
				break;
		}


		int angle;
		switch (element.getDirection()) {
			case DOWN:
				angle = -45;
				break;

			case LEFT:
				angle = 225;
				break;

			case UP:
				angle = 135;
				break;

			default:
				angle = 45;
				break;
		}

		g.setColor(color);
		g.fillArc(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS, angle, 270);
	}
}
