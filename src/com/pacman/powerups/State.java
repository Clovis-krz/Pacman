package com.pacman.powerups;

import java.awt.*;


public interface State {
	enum InteractionType {
		PACMAN_DEATH,
		NONE,
		GHOST_DEATH
	}

	InteractionType getInteractionType();
	float getGhostSpeedMultiplier();

	Color getPacmanColor();
	Color getGhostColor();

	int getDuration();
}
