package com.pacman.powerups;

import java.awt.*;


public class SuperState implements State {
	@Override
	public InteractionType getInteractionType() {
		return InteractionType.GHOST_DEATH;
	}

	@Override
	public float getGhostSpeedMultiplier() {
		return 0.5f;
	}

	@Override
	public Color getPacmanColor() {
		return Color.ORANGE;
	}

	@Override
	public Color getGhostColor() {
		return Color.CYAN;
	}

	@Override
	public int getDuration() {
		return 250;
	}
}
