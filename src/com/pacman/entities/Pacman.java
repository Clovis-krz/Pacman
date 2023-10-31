package com.pacman.entities;

import com.pacman.Main;
import com.pacman.renderers.PacmanRenderer;
import com.pacman.tiles.Tile;


public class Pacman implements Entity {
	public enum State {
		NORMAL,
		INVISIBLE,
		SUPER
	}

	private State state = State.NORMAL;
	private Direction direction = Direction.RIGHT;
	private final PacmanRenderer renderer;
	private int x, y;

	public Pacman(int x, int y) {
		this.x = x;
		this.y = y;
		this.renderer = new PacmanRenderer(this);
	}

	private static final int SPEED_MULTIPLIER = 2;

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
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

	@Override
	public void move() {
		// Offset coordinates for the current direction we're in.
		int directionX = direction.getX();
		int directionY = direction.getY();

		// Current tile coordinates
		int tileX = Math.floorDiv(this.x, Main.ELEMENT_SIZE);
		int tileY = Math.floorDiv(this.y, Main.ELEMENT_SIZE);

		// Upcoming x, y coordinates
		int nextX = this.x + directionX * SPEED_MULTIPLIER;
		int nextY = this.y + directionY * SPEED_MULTIPLIER;

		// Upcoming tile coordinates
		int nextTileX = Math.floorDiv(nextX, Main.ELEMENT_SIZE);
		int nextTileY = Math.floorDiv(nextY, Main.ELEMENT_SIZE);

		// Upcoming tile
		Tile nextTile = Main.getTileAtCoords(nextTileX, nextTileY);

		// Wraparound mechanics (if the next tile we're going on is outside the board, we wrap around if possible or stop moving otherwise)
		if (nextTileX < 0 || nextTileX >= Main.getBoardLength() || nextTileY < 0 || nextTileY >= Main.getBoardLength()) {
			int[] destination = Main.getWrapAroundCoordinates(tileX, tileY);

			if (destination == null) return;

			this.x = destination[0] + directionX * SPEED_MULTIPLIER;
			this.y = destination[1] + directionY * SPEED_MULTIPLIER;

			return;
		}

		// If the upcoming tile is a wall, we stop moving
		if (nextTile == null || nextTile.isSolidForPacman()) return;

		// If there's no obstacle in the way, we go to the next coordinates.
		this.x = nextX;
		this.y = nextY;

		// TODO: Adapt call depending on codebase changes
		if (tileX != nextTileX || tileY != nextTileY) nextTile.onPacmanInterract();
	}
	
	@Override
	public void draw() {
		renderer.repaint();
	}
}
