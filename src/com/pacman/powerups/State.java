package com.pacman.powerups;

import java.awt.*;


public interface State {
	enum InteractionType {
		PACMAN_DEATH,
		NONE,
		GHOST_DEATH
	}

	/**
	 @return What should happen in case pacman collides with a ghost.
	*/
	InteractionType getInteractionType();

	/**
	 @return The speed multiplier of the ghost, between 0.5 to infinity.
	*/
	float getGhostSpeedMultiplier();

	/**
	 @return The color that Pacman should use when the powerup is active
	*/
	Color getPacmanColor();

	/**
	 @return The color that ghosts should use when the powerup is active
	*/
	Color getGhostColor();

	/**
	 @return The duration of the powerup, returning 0 makes that powerup turn into the normal state
	*/
	int getDuration();
}
