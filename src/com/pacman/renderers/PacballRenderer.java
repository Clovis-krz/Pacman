package com.pacman.renderers;

import java.awt.*;
import com.pacman.Game;
import com.pacman.tiles.pacballs.AbstractPacball;
import javax.swing.*;


public class PacballRenderer extends JComponent {
	private final AbstractPacball element;

	public PacballRenderer(AbstractPacball element) {
		this.element = element;
		this.setSize(Game.ELEMENT_SIZE * Game.GRID_WIDTH, Game.ELEMENT_SIZE * Game.GRID_HEIGHT + GuiRenderer.GUI_SIZE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int radius = element.getRadius();
		int x = element.getX() * Game.ELEMENT_SIZE + (Game.ELEMENT_SIZE / 2) - radius;
		int y = element.getY() * Game.ELEMENT_SIZE + (Game.ELEMENT_SIZE / 2) - radius + GuiRenderer.GUI_SIZE;

		g.setColor(element.getColor());

		g.fillArc(x, y, 2 * radius, 2 * radius, 0, 360);
	}
}
