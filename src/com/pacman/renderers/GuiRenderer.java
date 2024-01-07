package com.pacman.renderers;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import javax.swing.*;


public class GuiRenderer extends JComponent {
	public static final int GUI_SIZE = 55;

	public enum Type {
		NONE,
		WIN,
		LOSE
	}
	private Type type;

	public void setType(Type type) {
		this.type = type;
	}

	public GuiRenderer() {
		this.setSize(Game.ELEMENT_SIZE * Game.GRID_WIDTH, Game.ELEMENT_SIZE * Game.GRID_HEIGHT + GUI_SIZE);
		Main.addRenderer(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), GUI_SIZE);

		g.setColor(Color.WHITE);
		Font font = new Font("Sans-Serif", Font.PLAIN, 12);
		g.setFont(font);

		g.drawString("Vies: " + Game.getRemainingLives(), 10, 20);
		g.drawString("Score: " + Game.getScore(), 10, 40);

		g.drawString("Appuyez sur entrée", 290, 20);
		g.drawString("pour recommencer.", 290, 40);

		if (type == Type.WIN) g.drawString("GAGNÉ!", 175, 30);
		else if (type == Type.LOSE) g.drawString("PERDU!", 175, 30);
	}
}
