package com.pacman.entities;

import java.util.Random;


public interface Entity {
	/**
	 @return The X coordinate of the entity, in pixels.
	*/
	int getX();

	/**
	 @return The Y coordinate of the entity, in pixels.
	*/
	int getY();

	/**
	 @return The direction in which the entity is going
	*/
	Direction getDirection();

	/**
	 Changes the direction of the entity

	 @param direction The new direction the entity should take
	*/
	void setDirection(Direction direction);

	/**
	 Makes the entity move once, called once per game tick.
	*/
	void move();

	/**
	 Teleports the entity to specific coordinates

	 @param x X coordinate of the destination
	 @param y Y coordinate of the destination
	 @return Whether the teleport actually happened and the coordinates where valid.
	*/
	boolean teleport(int x, int y);

	/**
	 Direction of an entity
	*/
	enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;

		/**
		 Gets the associated direction, given its numerical value

		 @param value Numerical value of the direction
		 @return The associated direction
		*/
		public static Direction getDirectionFromValue(int value) {
			return switch (value) {
				case 0 -> UP;
				case 1 -> DOWN;
				case 2 -> LEFT;
				default -> RIGHT;
			};
		}

		/**
		 @return A random direction
		*/
		public static Direction getRandom() {
			Random random = new Random();
			int selected = random.nextInt(4);

			return getDirectionFromValue(selected);
		}

		/**
		 @return The next direction "to the right" of the given direction
		*/
		public Direction clockwise() {
			return switch (this) {
				case UP -> RIGHT;
				case RIGHT -> DOWN;
				case DOWN -> LEFT;
				case LEFT -> UP;
			};
		}

		/**
		 @return The previous direction "to the left" of the given direction
		*/
		public Direction counterClockwise() {
			return switch (this) {
				case UP -> LEFT;
				case LEFT -> DOWN;
				case DOWN -> RIGHT;
				case RIGHT -> UP;
			};
		}

		/**
		 @return The opposite direction of the given direction
		*/
		public Direction reverse() {
			return switch (this) {
				case UP -> DOWN;
				case RIGHT -> LEFT;
				case DOWN -> UP;
				case LEFT -> RIGHT;
			};
		}

		/**
		 @return The X axis offset of the given direction
		*/
		public int getX() {
			return switch (this) {
				case UP -> 0;
				case DOWN -> 0;
				case LEFT -> -1;
				case RIGHT -> 1;
			};
		}

		/**
		 @return The Y axis offset of the given direction
		*/
		public int getY() {
			return switch (this) {
				case UP -> -1;
				case DOWN -> 1;
				case LEFT -> 0;
				case RIGHT -> 0;
			};
		}

	}
}
