package com.pacman.entities;

import java.util.Random;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.GhostRenderer;
import com.pacman.tiles.Tile;


public class Ghost implements Entity {
	// Current direction in which the ghost is moving
	private Direction direction = Direction.getRandom();

	// Position of the entity, in pixels
	private int x, y;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;

		// Observer of the ghost, used to render it.
		GhostRenderer renderer = new GhostRenderer(this);
	}

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

	@Override
	public void move() {
		// Sets the current ghost speed
		int currentSpeedMultiplier = (int)(2 * Game.getPowerupState().getGhostSpeedMultiplier());

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
		int nextTileX = Math.floorDiv(nextX, Game.ELEMENT_SIZE);
		if (directionX == 1) nextTileX += 1;

		int nextTileY = Math.floorDiv(nextY, Game.ELEMENT_SIZE);
		if (directionY == 1) nextTileY += 1;

		// Upcoming tile
		Tile nextTile = Game.getTileAtCoords(nextTileX, nextTileY);

		// Wraparound mechanics (if the next tile we're going on is outside the board, we wrap around if possible or stop moving otherwise)
		if (nextTile == null) {
			if (directionX == 1) tileX += 1;
			if (directionY == 1) tileY += 1;

			wraparound(tileX, tileY);
			return;
		}

		// If the upcoming tile is a wall,
		if (nextTile.isSolidForGhosts()) {
			int distanceX = Math.abs(nextTileX * Game.ELEMENT_SIZE - this.x);
			int distanceY = Math.abs(nextTileY * Game.ELEMENT_SIZE - this.y);

			// If we're moving horizontally and can get closer to a wall
			if (directionX != 0 && distanceX > Game.ELEMENT_SIZE) {
				this.x += Math.min(currentSpeedMultiplier, distanceX) * directionX;
				return;
			}

			// If we're moving vertically and can get closer to a wall
			if (directionY != 0 && distanceY > Game.ELEMENT_SIZE) {
				this.y += Math.min(currentSpeedMultiplier, distanceY) * directionY;
				return;
			}

			// Otherwise,
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

			// Ghost direction
			if (leftTileSolid && rightTileSolid) direction = direction.reverse(); // If the ghost cannot go neither to its right or to its left
			else if (leftTileSolid) direction = rightDirection; // If the ghost can only go right
			else if (rightTileSolid) direction = leftDirection; // If the ghost can only go left
			else { // If the ghost can go to both its right or left
				Random random = new Random();
				int value = random.nextInt(2);

				if (value == 0) direction = leftDirection;
				else direction = rightDirection;
			}

			return;
		}

		// If there's no obstacle in the way, we go to the next coordinates.
		this.x = nextX;
		this.y = nextY;
	}

	/**
	 Handles how the Ghost should behave when going through a warparound tunnel.
	 (Going back if the exit is blocked)

	 @param x X position of the current tile of the ghost
	 @param y Y position of the current tile of the ghost
	*/
	private void wraparound(int x, int y) {
		int[] destination = Game.getWrapAroundCoordinates(x, y, false);

		if (destination == null) {
			direction = direction.reverse();

			return;
		}

		this.x = destination[0] * Game.ELEMENT_SIZE;
		this.y = destination[1] * Game.ELEMENT_SIZE;
	}

	@Override
	public boolean teleport(int x, int y) {
		if (x < 0 || x >= Game.GRID_WIDTH * Game.ELEMENT_SIZE || y < 0 || y >= Game.GRID_HEIGHT * Game.ELEMENT_SIZE) return false;

		this.x = x;
		this.y = y;

		return true;
	}
}
