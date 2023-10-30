package com.pacman.entities;

public class Pacman implements Entity {
	public enum State {
		NORMAL,
		INVISIBLE,
		SUPER
	}

	private State state;
	private int x, y;
	private int tileX, tileY;
	private Direction direction;

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

	// TODO: Implement pacman logic (moving, wall checks etc)
	@Override
	public void move() {
	}

}
