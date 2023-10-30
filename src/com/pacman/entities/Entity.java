package com.pacman.entities;

public interface Entity {
	int getX();

	int getY();

	int getTileX();

	int getTileY();

	Direction getDirection();

	void move();

	enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
}
