package com.pacman.entities;

import java.util.Random;
import com.pacman.Main;
import com.pacman.tiles.Tile;


public class Ghost implements Entity {
	public enum State {
		NORMAL,
		VULNERABLE
	}

	private State state = State.NORMAL;
	private Direction direction = Direction.getRandom();
	private int x, y;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
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
		int tileX = Math.floorDiv(this.x, Main.ELEMENT_SIZE);
		int tileY = Math.floorDiv(this.y, Main.ELEMENT_SIZE);

		// Upcoming x, y coordinates
		int nextX = this.x + directionX * currentSpeedMultiplier;
		int nextY = this.y + directionY * currentSpeedMultiplier;

		// Upcoming tile coordinates
		int nextTileX = Math.floorDiv(nextX, Main.ELEMENT_SIZE);
		int nextTileY = Math.floorDiv(nextY, Main.ELEMENT_SIZE);

		// Upcoming tile
		Tile nextTile = Main.getTileAtCoords(nextTileX, nextTileY);
		if (nextTile == null) return;

		// Wraparound mechanics (if the next tile we're going on is outside the board, we wrap around if possible or stop moving otherwise)
		if (nextTileX < 0 || nextTileX >= Main.getBoardLength() || nextTileY < 0 || nextTileY >= Main.getBoardLength()) {
			int[] destination = Main.getWrapAroundCoordinates(tileX, tileY);

			if (destination == null) return;

			this.x = destination[0] + directionX * currentSpeedMultiplier;
			this.y = destination[1] + directionY * currentSpeedMultiplier;

			return;
		}

		// If the upcoming tile is a wall, we stop moving
		if (nextTile.isSolidForGhosts()) {
			// Direction to the left of the ghost
			Direction leftDirection = direction.counterClockwise();
			int leftDirectionX = leftDirection.getX();
			int leftDirectionY = leftDirection.getY();

			// Tile to the left of the ghost
			Tile leftTile = Main.getTileAtCoords(tileX + leftDirectionX, tileY + leftDirectionY);
			boolean leftTileSolid = leftTile == null || leftTile.isSolidForGhosts();

			// Direction to the right of the ghost
			Direction rightDirection = direction.clockwise();
			int rightDirectionX = rightDirection.getX();
			int rightDirectionY = rightDirection.getY();

			// Tile to the right of the ghost
			Tile rightTile = Main.getTileAtCoords(tileX + rightDirectionX, tileY + rightDirectionY);
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
}
