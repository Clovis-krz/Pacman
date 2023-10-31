package com.pacman.renderers;

import com.pacman.entities.Ghost;
import javax.swing.*;


public class GhostRenderer extends JComponent {
	private final Ghost ghost;

	public GhostRenderer(Ghost ghost) {
		this.ghost = ghost;
	}
}
