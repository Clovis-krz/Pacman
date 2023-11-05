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
		// Second corner of pacman
		int x2 = x + Main.ELEMENT_SIZE;
		int y2 = y + Main.ELEMENT_SIZE;

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
		int nextTileX = tileX + directionX;
		int nextTileY = tileY + directionY;

		// Upcoming tile
		Tile nextTile = Main.getTileAtCoords(nextTileX, nextTileY);

		// Wraparound mechanics (if the next tile we're going on is outside the board, we wrap around if possible or stop moving otherwise)
		if (nextTile == null) {
			wraparound(tileX, tileY);
			return;
		}

		Tile secondaryTile = null;

		boolean reachedWall = (directionX != 0 && (x == (nextTileX + 1) * Main.ELEMENT_SIZE || x + Main.ELEMENT_SIZE == nextTileX * Main.ELEMENT_SIZE)) ||
		                      (directionY != 0 && (y == (nextTileY + 1) * Main.ELEMENT_SIZE || y + Main.ELEMENT_SIZE == nextTileY * Main.ELEMENT_SIZE));

		// If the upcoming tile is a wall, we stop moving
		if (nextTile.isSolidForPacman() && reachedWall) {
			if (directionX != 0 && y % Main.ELEMENT_SIZE > 0) {
				secondaryTile = Main.getTileAtCoords(nextTileX, nextTileY + 1);

				if (secondaryTile == null) {
					wraparound(tileX, tileY);
					return;
				}

				if (!secondaryTile.isSolidForPacman()) {
					this.x = secondaryTile.getX() * Main.ELEMENT_SIZE;
					this.y = secondaryTile.getY() * Main.ELEMENT_SIZE;

					secondaryTile.onPacmanInterract();
					return;
				}
			}

			// If we're not aligned to the grid while going vertical
			if (directionY != 0 && x % Main.ELEMENT_SIZE > 0) {
				secondaryTile = Main.getTileAtCoords(nextTileX + 1, nextTileY);

				if (secondaryTile == null) {
					wraparound(tileX, tileY);
					return;
				}

				if (!secondaryTile.isSolidForPacman()) {
					this.x = secondaryTile.getX() * Main.ELEMENT_SIZE;
					this.y = secondaryTile.getY() * Main.ELEMENT_SIZE;

					secondaryTile.onPacmanInterract();
					return;
				}
			}

			return;
		}

		// If we're not aligned to the grid while going horizontal
		if (directionX != 0 && y % Main.ELEMENT_SIZE > 0) {
			secondaryTile = Main.getTileAtCoords(nextTileX, nextTileY + 1);

			if (secondaryTile == null) {
				wraparound(tileX, tileY);
				return;
			}

			if (secondaryTile.isSolidForPacman() && reachedWall) {
				this.x = nextTileX * Main.ELEMENT_SIZE;
				this.y = nextTileY * Main.ELEMENT_SIZE;

				nextTile.onPacmanInterract();
				return;
			}
		}

		// If we're not aligned to the grid while going vertical
		if (directionY != 0 && x % Main.ELEMENT_SIZE > 0) {
			secondaryTile = Main.getTileAtCoords(nextTileX + 1, nextTileY);

			if (secondaryTile == null) {
				wraparound(tileX, tileY);
				return;
			}

			if (secondaryTile.isSolidForPacman() && reachedWall) {
				this.x = nextTileX * Main.ELEMENT_SIZE;
				this.y = nextTileY * Main.ELEMENT_SIZE;

				nextTile.onPacmanInterract();
				return;
			}
		}

		// If there's no obstacle in the way, we go to the next coordinates.
		this.x = nextX;
		this.y = nextY;

		nextTile.onPacmanInterract();
		if (secondaryTile != null) secondaryTile.onPacmanInterract();
	}


	public void wraparound(int x, int y) {
		int[] destination = Main.getWrapAroundCoordinates(x, y, true);

		if (destination == null) return;

		this.x = destination[0] * Main.ELEMENT_SIZE + direction.getX() * SPEED_MULTIPLIER;
		this.y = destination[1] * Main.ELEMENT_SIZE + direction.getY() * SPEED_MULTIPLIER;

		Tile tile = Main.getTileAtCoords(destination[0], destination[1]);

		if (tile == null) return;

		tile.onPacmanInterract();
	}
	
	@Override
	public void draw() {
		renderer.repaint();
	}


	@Override
	public boolean teleport(int x, int y) {
		if (x < 0 || x >= Main.GRID_WIDTH * Main.ELEMENT_SIZE || y < 0 || y >= Main.GRID_HEIGHT * Main.ELEMENT_SIZE) return false;

		this.x = x;
		this.y = y;

		return true;
	}

	@Override
	public void delete() {
		Main.removeRenderer(this.renderer);
	}
}
