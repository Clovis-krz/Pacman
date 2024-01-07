package com.pacman.renderers;

import java.awt.*;
import com.pacman.Game;
import com.pacman.entities.Pacman;
import javax.swing.*;


public class PacmanRenderer extends JComponent {
	private static final int RADIUS = 7;

	private final Pacman element;

	public PacmanRenderer(Pacman pacman) {
		this.element = pacman;
		this.setSize(Game.ELEMENT_SIZE * Game.GRID_WIDTH, Game.ELEMENT_SIZE * Game.GRID_HEIGHT + GuiRenderer.GUI_SIZE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = element.getX() + (Game.ELEMENT_SIZE / 2);
		int centerY = element.getY() + (Game.ELEMENT_SIZE / 2) + GuiRenderer.GUI_SIZE;

		Color color = Game.getPowerupState().getPacmanColor();

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
