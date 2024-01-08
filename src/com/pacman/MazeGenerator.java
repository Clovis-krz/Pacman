package com.pacman;

import com.pacman.tiles.Air;
import com.pacman.tiles.PacmanWall;
import com.pacman.tiles.Tile;
import com.pacman.tiles.Wall;
import com.pacman.tiles.pacballs.BluePacball;
import com.pacman.tiles.pacballs.GreenPacball;
import com.pacman.tiles.pacballs.OrangePacball;
import com.pacman.tiles.pacballs.PurplePacball;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class MazeGenerator {
	// Unique random instance to save on performance and have less chances to be predictable.
	private static final Random random = new Random();

	/**
	 Procedurally generates the maze layout and sets the game board to it.

	 @param board The board array that will get overwritten afterwards.
	 @return The amount of pacballs and special pacballs in the generated maze, as an array of 2 integers.
	*/
	public static int[] generateBoard(Tile[] board) {
		return generateBoard(board, -1, -1);
	}

	/**
	 Procedurally generates the maze layout and sets the game board to it.

	 @param board The board array that will get overwritten afterwards.
	 @param normalPacballs Maximum amount of pacballs to generate, if the layout exceeds this amount of pacballs, the remaining pacballs will instead be air.
	 @param specialPacballs Maximum amount of special pacballs to generate, if the layout exceeds this amount of pacballs, the remaining pacballs will instead be air.
	 @return The amount of pacballs and special pacballs in the generated maze, as an array of 2 integers.
	*/
	public static int[] generateBoard(Tile[] board, int normalPacballs, int specialPacballs) {
		// Grabs a random layout
		String[] boardString = getRandomBoard();

		// Randomly choses the wall color for the new level, unless it is the first level which is always blue.
		Color[] colors = new Color[]{Color.BLUE, Color.GREEN,Color.orange, Color.YELLOW, Color.WHITE, Color.lightGray, Color.PINK};
		Color color = colors[new Random().nextInt(colors.length)];
		if (normalPacballs == -1 || specialPacballs == -1) color = Color.BLUE;

		// Amounts of generated pacballs
		int pacballsCounter = 0;
		int specialPacballsCounter = 0;

		// Layout "parser" to correctly set the board to the layout.
		for (int i = 0; i < boardString.length; i++) {
			// Tile coordinates
			int x = i % Game.GRID_WIDTH;
			int y = i / Game.GRID_HEIGHT;

			switch (boardString[i]) {
				case "W": // Normal wall
					board[i] = new Wall(x, y, color);
					break;

				case "X": // Pacman Wall
					board[i] = new PacmanWall(x, y);
					break;

				case "A": // Air
					board[i] = new Air(x, y);
					break;

				case "B": // Blue pacballs
					if (pacballsCounter < normalPacballs || normalPacballs < 0) {
						board[i] = new BluePacball(x, y);
						pacballsCounter += 1;
					}
					else board[i] = new Air(x, y);

					break;

				case "S": // Special pacballs
					if (specialPacballsCounter < specialPacballs || specialPacballs < 0) {
						int type = random.nextInt(3);
						if (type == 0) board[i] = new GreenPacball(x, y);
						if (type == 1) board[i] = new OrangePacball(x, y);
						if (type == 2) board[i] = new PurplePacball(x, y);

						specialPacballsCounter += 1;
					}
					else board[i] = new Air(x, y);

					break;

				default: // Fallback in case we forgot to define one caracter in a layout.
					System.out.println("Error, unknown expression at coordinates x: " + x + ", y: " + y);
			}
		}

		// Returns the final pacball counts
		return new int[]{pacballsCounter, specialPacballsCounter};
	}

	/**
	 @return A random board layout from a list of 5 pre-defined layouts.
	*/
	private static String[] getRandomBoard() {
		/*
		 Symbols:
		 W → Wall
		 X → Pacman Wall
		 A → Air
		 B → Blue pacball
		 S → Special pacball
		*/

		ArrayList<String[]> boards = new ArrayList<>();

		String[] board1 = {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","B","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","S","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"B","B","W","B","B","W","B","B","S","B","B","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"W","B","W","B","B","W","B","B","B","B","B","B","B","B","B","B","B","B","B","B",
				"W","B","W","B","W","W","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","A","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","W","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","B","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","W","B","B","B","W","A","A","X","X","A","A","W","B","W","W","W","W","W",
				"W","B","W","B","W","B","B","A","A","A","A","A","A","B","B","B","B","B","B","B",
				"W","B","W","B","B","B","B","W","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","W","W","B","W","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"B","B","B","B","B","B","B","B","B","B","B","B","B","W","B","W","W","W","W","W",
				"W","B","W","W","W","W","W","B","W","B","B","B","B","W","B","B","B","S","B","W",
				"W","B","W","S","B","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
		};
		boards.add(board1);

		String[] board2 = {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","W","B","B","B","B","S","W",
				"W","W","W","B","W","B","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"B","B","W","B","W","B","B","B","S","B","B","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","W","W","W","W","B","W","B","B","B","W","W","W","B","W",
				"W","B","W","B","B","B","B","B","B","B","B","W","B","B","B","B","B","B","B","B",
				"W","B","W","B","W","B","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","W","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","W","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","B","B","B","B","W","A","A","X","X","A","A","W","B","W","W","W","B","W",
				"W","B","W","B","W","B","W","A","A","A","A","A","A","B","B","B","B","B","B","B",
				"W","B","W","B","W","B","W","W","W","W","W","W","W","W","W","W","W","W","B","W",
				"W","B","W","B","W","B","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"B","B","B","B","B","B","B","B","W","W","W","W","B","W","B","W","W","W","B","W",
				"W","B","W","W","W","W","W","B","W","B","S","B","B","W","B","B","B","S","B","W",
				"W","B","W","B","B","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				};
		boards.add(board2);

		String[] board3 = {
				"W","B","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","B","B","W","B","B","B","B","B","B","B","B","W","B","B","B","B","S","W",
				"W","W","W","B","W","B","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","B","B","W","B","S","B","B","W","W","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","W","W","W","W","B","W","B","B","B","W","W","W","B","W",
				"W","B","B","B","B","B","B","B","B","B","B","W","B","B","B","B","B","B","B","W",
				"W","B","W","W","W","B","W","B","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","W","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","W","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","W","W","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","B","W","B","B","W","A","A","X","X","A","A","W","B","W","W","W","B","W",
				"W","B","W","W","W","B","W","A","A","A","A","A","A","W","B","B","B","B","B","W",
				"W","B","W","B","B","B","W","W","W","W","W","W","B","W","W","W","W","W","B","W",
				"W","B","W","W","W","B","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","B","B","B","B","B","B","B","W","W","W","W","B","W","B","W","W","W","B","W",
				"W","B","W","B","W","B","W","B","W","B","S","B","B","W","B","B","B","S","B","W",
				"W","B","W","B","W","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","B","W",
		};
		boards.add(board3);

		String[] board4 = {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","W","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","S","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","W","W","W","B","W","B","W",
				"W","B","W","B","B","W","B","B","S","B","B","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","W","W","B","W","W","W","B","W",
				"W","B","W","B","B","W","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","B","W","B","W","W","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","A","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","W","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","B","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","W","B","B","B","W","A","A","X","X","A","A","W","B","W","W","W","B","W",
				"W","B","W","B","W","B","B","A","A","A","A","A","A","B","B","B","B","B","B","W",
				"W","B","W","B","B","B","B","W","W","W","W","W","B","W","B","W","W","W","B","W",
				"W","B","W","W","B","W","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","B","B","B","B","B","B","B","W","W","B","W","B","W","B","W","W","W","B","W",
				"W","B","W","W","W","W","W","B","W","B","B","B","B","W","B","B","B","S","B","W",
				"W","B","W","S","B","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
		};
		boards.add(board4);

		String[] board5 = {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","B","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","S","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"B","B","W","B","B","W","B","B","S","B","B","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"W","B","W","B","B","B","B","B","B","B","B","W","B","B","B","B","B","B","B","B",
				"W","B","W","B","W","B","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","W","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","W","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","W","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","W","W","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","B","W","B","B","W","A","A","X","X","A","A","W","B","W","W","W","B","W",
				"W","B","W","W","W","B","W","A","A","A","A","A","A","W","B","B","B","B","B","W",
				"W","B","W","B","B","B","W","W","W","W","W","W","B","W","W","W","W","W","B","W",
				"W","B","W","W","W","B","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"W","B","B","B","B","B","B","B","W","W","B","W","B","W","B","W","W","W","B","W",
				"W","B","W","W","W","W","W","B","W","B","B","B","B","W","B","B","B","S","B","W",
				"W","B","W","S","B","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","B","B","B","B","B","B","B","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
		};
		boards.add(board5);

		return boards.get(random.nextInt(boards.size()));
	}
}
