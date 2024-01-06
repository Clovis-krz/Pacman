package com.pacman;

import com.pacman.tiles.Air;
import com.pacman.tiles.PacmanWall;
import com.pacman.tiles.Tile;
import com.pacman.tiles.Wall;
import com.pacman.tiles.pacballs.BluePacball;
import com.pacman.tiles.pacballs.GreenPacball;
import com.pacman.tiles.pacballs.OrangePacball;
import com.pacman.tiles.pacballs.PurplePacball;


public class MazeGenerator {

	// TODO: Replace with a function to generate the labyrinth procedurally
	/**
	 Procedurally generates the maze layout and sets the game board to it.

	 @param board The board array that will get overwritten afterwards.
	 @return The amount of pacballs in the generated maze.
	*/
	public static int generateBoard(Tile[] board) {
		//W -> Wall
		//A -> Air
		//X -> Pacman Wall
		//B -> Blue Pacball
		//G -> Green Pacball
		//O -> Orange Pacball
		//P -> Purple Pacball


		String[] string_board = {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"W","B","P","B","B","B","B","W","B","B","B","B","B","B","B","B","B","O","B","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"B","B","W","B","B","W","B","B","P","B","B","W","B","W","B","W","B","W","B","W",
				"W","B","W","B","W","W","B","W","W","W","W","W","B","B","B","B","B","B","B","W",
				"W","B","W","B","B","W","B","B","B","B","B","B","B","B","B","B","B","B","B","B",
				"W","B","W","B","W","W","W","W","W","W","W","W","B","W","B","W","B","W","B","W",
				"W","B","B","B","B","B","A","A","A","A","A","A","A","A","B","B","B","W","B","W",
				"W","B","W","B","B","B","W","A","A","X","X","A","A","W","B","W","B","W","B","W",
				"W","B","W","B","B","B","W","A","X","A","A","X","A","W","B","W","B","W","B","W",
				"W","B","W","B","B","B","W","A","X","A","A","X","A","W","B","W","B","B","B","W",
				"W","B","W","B","B","B","W","A","A","X","X","A","A","W","B","W","W","W","W","W",
				"W","B","W","B","B","B","B","A","A","A","A","A","A","B","B","B","B","B","B","B",
				"W","B","W","B","B","B","B","W","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","W","W","B","B","B","B","B","B","B","B","B","W","B","B","B","B","B","W",
				"B","B","B","B","B","B","B","B","B","B","B","B","B","W","B","W","W","W","W","W",
				"W","B","W","W","W","W","W","B","W","B","B","B","B","W","B","B","B","O","B","W",
				"W","B","W","B","B","B","W","B","W","W","W","W","W","W","B","W","W","W","W","W",
				"W","B","B","B","B","B","W","P","B","B","B","B","B","B","B","B","B","B","B","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				};

		int pacballsCounter = 0;

		for (int i = 0; i < string_board.length; i++) {
			int x = i % Game.GRID_WIDTH;
			int y = i / Game.GRID_HEIGHT;

			switch (string_board[i]) {
				case "W":
					board[i] = new Wall(x, y);
					break;
				case "A":
					board[i] = new Air(x, y);
					break;
				case "X":
					board[i] = new PacmanWall(x, y);
					break;
				case "B":
					board[i] = new BluePacball(x, y);
					pacballsCounter++;
					break;
				case "G":
					board[i] = new GreenPacball(x, y);
					pacballsCounter++;
					break;
				case "O":
					board[i] = new OrangePacball(x, y);
					pacballsCounter++;
					break;
				case "P":
					board[i] = new PurplePacball(x, y);
					pacballsCounter++;
					break;
				default:
					System.out.println("Error, unknown expression at coordinates x: " + x + ", y: " + y);
			}

		}

		return pacballsCounter;
	}
}
