package com.pacman.entities;

import java.util.Random;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.GhostRenderer;
import com.pacman.tiles.Tile;


public class Ghost implements Entity {
	public enum State {
		NORMAL,
		VULNERABLE
	}

	private State state = State.NORMAL;
	private Direction direction = Direction.getRandom();
	private final GhostRenderer renderer;

	private int x, y;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
		this.renderer = new GhostRenderer(this);
		Main.addRenderer(renderer);
	}

	private static final int NORMAL_SPEED_MULTIPLIER = 2;
	private static final int VULNERABLE_SPEED_MULTIPLIER = 1;

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
		// Sets the current ghost speed
		int currentSpeedMultiplier = NORMAL_SPEED_MULTIPLIER;
		if (this.state == State.VULNERABLE) currentSpeedMultiplier = VULNERABLE_SPEED_MULTIPLIER;

		// Offset coordinates for the current direction we're in.
		int directionX = direction.getX();
		int directionY = direction.getY();

		// Current tile coordinates
		int tileX = Math.floorDiv(this.x, Game.ELEMENT_SIZE);
		int tileY = Math.floorDiv(this.y, Game.ELEMENT_SIZE);

		// Upcoming x, y coordinates
		int nextX = this.x + directionX * currentSpeedMultiplier;
		int nextY = this.y + directionY * currentSpeedMultiplier;

		// Upcoming tile coordinates
		int nextTileX = tileX + directionX;
		int nextTileY = tileY + directionY;

		// Upcoming tile
		Tile nextTile = Game.getTileAtCoords(nextTileX, nextTileY);

		// Wraparound mechanics (if the next tile we're going on is outside the board, we wrap around if possible or stop moving otherwise)
		if (nextTile == null) {
			wraparound(tileX, tileY, currentSpeedMultiplier);
			return;
		}

		boolean reachedWall = (directionX != 0 && (x == (nextTileX + 1) * Game.ELEMENT_SIZE || x + Game.ELEMENT_SIZE == nextTileX * Game.ELEMENT_SIZE)) ||
		                      (directionY != 0 && (y == (nextTileY + 1) * Game.ELEMENT_SIZE || y + Game.ELEMENT_SIZE == nextTileY * Game.ELEMENT_SIZE));

		// If the upcoming tile is a wall, we stop moving
		if (nextTile.isSolidForGhosts() && reachedWall) {
			// Direction to the left of the ghost
			Direction leftDirection = direction.counterClockwise();
			int leftDirectionX = leftDirection.getX();
			int leftDirectionY = leftDirection.getY();

			// Tile to the left of the ghost
			Tile leftTile = Game.getTileAtCoords(tileX + leftDirectionX, tileY + leftDirectionY);
			boolean leftTileSolid = leftTile == null || leftTile.isSolidForGhosts();

			// Direction to the right of the ghost
			Direction rightDirection = direction.clockwise();
			int rightDirectionX = rightDirection.getX();
			int rightDirectionY = rightDirection.getY();

			// Tile to the right of the ghost
			Tile rightTile = Game.getTileAtCoords(tileX + rightDirectionX, tileY + rightDirectionY);
			boolean rightTileSolid = rightTile == null || rightTile.isSolidForGhosts();

			if (leftTileSolid && rightTileSolid) direction = direction.reverse();
			else if (leftTileSolid) direction = rightDirection;
			else if (rightTileSolid) direction = leftDirection;
			else {
				Random random = new Random();
				int value = random.nextInt(2);

				if (value == 0) direction = leftDirection;
				else direction = rightDirection;
			}

			directionX = direction.getX();
			directionY = direction.getY();

			this.x += directionX * currentSpeedMultiplier;
			this.y += directionY * currentSpeedMultiplier;
			return;
		}

		// If there's no obstacle in the way, we go to the next coordinates.
		this.x = nextX;
		this.y = nextY;
	}


	public void wraparound(int x, int y, int speedMultiplier) {
		int[] destination = Game.getWrapAroundCoordinates(x, y, false);

		if (destination == null) {
			direction = direction.reverse();
			return;
		}

		this.x = destination[0] * Game.ELEMENT_SIZE + direction.getX() * speedMultiplier;
		this.y = destination[1] * Game.ELEMENT_SIZE + direction.getY() * speedMultiplier;
	}


	@Override
	public void draw() {
		renderer.repaint();
	}

	@Override
	public boolean teleport(int x, int y) {
		if (x < 0 || x >= Game.GRID_WIDTH * Game.ELEMENT_SIZE || y < 0 || y >= Game.GRID_HEIGHT * Game.ELEMENT_SIZE) return false;

		this.x = x;
		this.y = y;

		return true;
	}

	@Override
	public void delete() {
		Main.removeRenderer(this.renderer);
	}
}
