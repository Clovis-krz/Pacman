package com.pacman.powerups;

import java.awt.*;


public class NormalState implements State {
	@Override
	public InteractionType getInteractionType() {
		return InteractionType.PACMAN_DEATH;
	}

	@Override
	public float getGhostSpeedMultiplier() {
		return 1;
	}

	@Override
	public Color getPacmanColor() {
		return Color.YELLOW;
	}

	@Override
	public Color getGhostColor() {
		return Color.RED;
	}

	@Override
	public int getDuration() {
		return 0;
	}
}
