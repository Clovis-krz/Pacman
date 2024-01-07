package com.pacman.powerups;

import java.awt.*;


public class InvisibleState implements State {
	@Override
	public InteractionType getInteractionType() {
		return InteractionType.NONE;
	}

	@Override
	public float getGhostSpeedMultiplier() {
		return 1;
	}

	@Override
	public Color getPacmanColor() {
		return new Color(255, 255, 190);
	}

	@Override
	public Color getGhostColor() {
		return Color.RED;
	}

	@Override
	public int getDuration() {
		return 250;
	}
}
