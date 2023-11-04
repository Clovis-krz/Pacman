package com.pacman;

import com.pacman.entities.Ghost;
import com.pacman.entities.Pacman;
import com.pacman.tiles.*;

import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static int GRID_WIDTH = 20;
    public static int GRID_HEIGHT = 20;
    public static int lifes;
    public static int points;

    public static Pacman pacman;
    public static ArrayList<Ghost> ghosts;
    public static int pacballs_counter;
    public static Tile[] board;
    public static final int ELEMENT_SIZE = 20;

    // TODO: Placeholder, replace with an implementation from a game class.
    public static Tile getTileAtCoords(int x, int y) {
        return board[x*GRID_WIDTH + y];
    }

    // TODO: Placeholder, replace with an implementation from a game class.
    public static int getBoardLength() {
        return GRID_WIDTH * GRID_HEIGHT;
    }

    // TODO: Placeholder, replace with an implementation from a game class.
    public static int[] getWrapAroundCoordinates(int x, int y) {
        return null;
    }

    public static void main(String[] args) throws InterruptedException {

        //Init Life
        lifes = 3;

        //Init Points
        points = 0;

        //Init Board
        InitBoard();

        //Init Pacman
        int[] pac_coord = FindAir();
        pacman = new Pacman(pac_coord[0],pac_coord[1]);

        //Init Ghosts
        ghosts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] ghost_coord = FindAir();
            ghosts.add(new Ghost(ghost_coord[0],ghost_coord[1]));
        }

        //Play
        while(pacballs_counter > 0 && lifes > 0) {
            if (isCollision()) {
                if (pacman.getState() == Pacman.State.SUPER) {
                    //Ghosts to Center
                    for (int i = 0; i < ghosts.size(); i++) {
                        Ghost new_ghost = new Ghost(GRID_WIDTH/2, GRID_HEIGHT/2);
                        ghosts.set(i, new_ghost);
                    }
                    //Ghosts speed low
                } else if (pacman.getState() == Pacman.State.NORMAL) {
                    lifes -= 1;
                }
            }
            Thread.sleep(50);
        }

        //End of Game, decide if the player won or Lost
        if (lifes > 0) {
            System.out.println("You Won");
        } else {
            System.out.println("Game Over");
        }


    }
    //Check if there is a collision with a ghost
    public static boolean isCollision() {
        boolean collision = false;
        for (Ghost ghost: ghosts) {
            if (ghost.getX() == pacman.getX() && ghost.getY() == pacman.getY()) {
                collision = true;
            }
        }
        return collision;
    }

    //Increase player's life counter every 5000 points
    public static void UpdateLife(int prev_points) {
        if (prev_points / 5000 < points / 5000) {
            lifes += 1;
        }
    }

    //Remove and replace Pacball after being eaten
    public static void RemovePacball(int x, int y) {
        int index = GRID_WIDTH*x + y;
        board[index] = new Air(x, y);
        pacballs_counter--;
    }
    //Find Random Air
    public static int[] FindAir() {
        int index = -1;
        while(index < 0) {
            Random rand = new Random();
            int temp = rand.nextInt(400);
            if (board[temp] instanceof Air) {
                index = temp;
            }
        }
        return new int[]{index / GRID_WIDTH, index % GRID_HEIGHT};
    }
    //Init board
    public static void InitBoard() {
        //W -> Wall
        //A -> Air
        //X -> Pacman Wall
        //B -> Blue Pacball
        //G -> Green Pacball
        //O -> Orange Pacball
        //P -> Purple Pacball
        String[] string_board = {
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
                "W","A","A","A","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
                "W","B","W","A","W","W","W","A","A","P","A","A","A","A","B","W","W","W","W","W",
                "W","W","W","A","W","W","W","A","W","W","W","W","W","W","A","W","W","W","W","W",
                "A","A","A","A","W","W","W","A","W","W","W","W","W","W","A","A","A","A","A","A",
                "W","W","W","A","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","A","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","A","W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","W",
                "W","W","W","A","A","A","A","A","X","B","A","A","A","A","A","A","W","W","W","W",
                "W","W","W","A","W","W","W","W","W","A","W","A","W","W","W","A","W","W","W","W",
                "W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","W","A","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","A","A","W","W","W","W","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","B","W","W","W","W","A","W","W","W","A","W","W","W","B","W","W","W","W",
                "W","W","A","W","W","W","W","A","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","A","W","W","W","W","A","W","W","W","A","W","W","W","A","W","W","W","W",
                "W","W","G","W","A","A","A","A","A","O","A","A","W","W","W","A","B","A","A","W",
                "W","W","A","A","B","W","W","W","W","W","W","W","W","W","W","W","W","W","A","W",
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","A","W",
                "W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
        };

        board = new Tile[400];
        pacballs_counter = 0;

        for (int i = 0; i < string_board.length; i++) {
            int x = i/GRID_WIDTH;
            int y = i%GRID_HEIGHT;
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
                    board[i] = new BluePacball(x,y);
                    pacballs_counter++;
                    break;
                case "G":
                    board[i] = new GreenPacball(x,y);
                    pacballs_counter++;
                    break;
                case "O":
                    board[i] = new OrangePacball(x,y);
                    pacballs_counter++;
                    break;
                case "P":
                    board[i] = new PurplePacball(x,y);
                    pacballs_counter++;
                    break;
                default:
                    System.out.println("Error, unknown expression at coordinates x"+x+", y: "+y);
            }

        }

    }
}