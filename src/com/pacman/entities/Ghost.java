package com.pacman.entities;

public class Ghost implements Entity {
	public enum State {
		NORMAL,
		VULNERABLE
	}

	private State state;
	private int x, y;
	private int tileX, tileY;
	private Direction direction;

	// TODO: Adjust ghost speed properly
	private static final int NORMAL_SPEED = 2;
	private static final int VULNERABLE_SPEED = 1;

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getTileX() {
		return this.tileX;
	}

	@Override
	public int getTileY() {
		return this.tileY;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	// TODO: Implement ghost logic (moving, wall checks etc)
	@Override
	public void move() {
	}
}
