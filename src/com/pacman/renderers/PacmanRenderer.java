package com.pacman.renderers;

import com.pacman.entities.Pacman;
import javax.swing.*;


public class PacmanRenderer extends JComponent {
	private final Pacman pacman;

	public PacmanRenderer(Pacman pacman) {
		this.pacman = pacman;
	}
}
