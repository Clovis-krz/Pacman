package com.pacman.entities;

import java.util.Random;


public interface Entity {
	int getX();

	int getY();

	Direction getDirection();

	void setDirection(Direction direction);

	void move();

	void draw();

	boolean teleport(int x, int y);

	void delete();

	enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;

		public static Direction getDirectionFromValue(int value) {
			return switch (value) {
				case 0 -> UP;
				case 1 -> DOWN;
				case 2 -> LEFT;
				default -> RIGHT;
			};
		}

		public static Direction getRandom() {
			Random random = new Random();
			int selected = random.nextInt(4);

			return getDirectionFromValue(selected);
		}

		public Direction clockwise() {
			return switch (this) {
				case UP -> RIGHT;
				case RIGHT -> DOWN;
				case DOWN -> LEFT;
				case LEFT -> UP;
			};
		}

		public Direction counterClockwise() {
			return switch (this) {
				case UP -> LEFT;
				case LEFT -> DOWN;
				case DOWN -> RIGHT;
				case RIGHT -> UP;
			};
		}

		public Direction reverse() {
			return switch (this) {
				case UP -> DOWN;
				case RIGHT -> LEFT;
				case DOWN -> UP;
				case LEFT -> RIGHT;
			};
		}

		public int getX() {
			return switch (this) {
				case UP -> 0;
				case DOWN -> 0;
				case LEFT -> -1;
				case RIGHT -> 1;
			};
		}

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
