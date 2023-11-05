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
	private Direction direction = Direction.DOWN;

	private final PacmanRenderer renderer;
	private int x, y;

	public Pacman(int x, int y) {
		this.x = x;
		this.y = y;
		this.renderer = new PacmanRenderer(this);
		Main.addRenderer(renderer);
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

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
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
		if (directionX > 0 || directionY > 0) {
			nextTileX = Math.ceilDiv(nextX, Main.ELEMENT_SIZE);
			nextTileY = Math.ceilDiv(nextY, Main.ELEMENT_SIZE);
		}

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

		// If we're not aligned to the grid while going horizontal
		if (directionX != 0 && y % Main.ELEMENT_SIZE > 2) {
			Tile secondaryTile = Main.getTileAtCoords(nextTileX, nextTileY + 1);

			if (secondaryTile != null && secondaryTile.isSolidForPacman()) return;
		}

		// If we're not aligned to the grid while going vertical
		if (directionY != 0 && x % Main.ELEMENT_SIZE > 2) {
			Tile secondaryTile = Main.getTileAtCoords(nextTileX + 1, nextTileY);

			if (secondaryTile != null && secondaryTile.isSolidForPacman()) return;
		}

		// If there's no obstacle in the way, we go to the next coordinates.
		this.x = nextX;
		this.y = nextY;

		if (tileX != nextTileX || tileY != nextTileY) nextTile.onPacmanInterract();
	}
	
	@Override
	public void draw() {
		renderer.repaint();
	}


	@Override
	public boolean teleport(int x, int y) {
		if (x < 0 || x >= Main.GRID_WIDTH || y < 0 || y >= Main.GRID_HEIGHT) return false;

		this.x = x;
		this.y = y;

		return true;
	}

	@Override
	public void delete() {
		Main.removeRenderer(this.renderer);
	}
}
