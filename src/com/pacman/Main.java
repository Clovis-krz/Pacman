package com.pacman;

import com.pacman.entities.Ghost;
import com.pacman.entities.Pacman;
import com.pacman.tiles.*;

import java.util.ArrayList;


public class Main {
    public static int lifes;
    public static int points;
    public static Pacman pacman;
    public static ArrayList<Ghost> ghosts;
    public static ArrayList<Tile> pacballs;
    public static final int ELEMENT_SIZE = 20;

    // TODO: Placeholder, replace with an implementation from a game class.
    public static Tile getTileAtCoords(int x, int y) {
        return null;
    }

    // TODO: Placeholder, replace with an implementation from a game class.
    public static int getBoardLength() {
        return 0;
    }

    // TODO: Placeholder, replace with an implementation from a game class.
    public static int[] getWrapAroundCoordinates(int x, int y) {
        return null;
    }

    public static void main(String[] args) {

        //Init Life
        lifes = 3;

        //Init Points
        points = 0;

        //TODO: Init labyrinth and set entities and tiles coordinates

        //Init Pacman
        pacman = new Pacman(0,0);

        //Init Ghosts
        ghosts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ghosts.add(new Ghost(0,0));
        }

        //Init Pacballs
        pacballs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pacballs.add(new BluePacball(0, 0));
        }
        pacballs.add(new PurplePacball(0, 0));
        pacballs.add(new PurplePacball(0, 0));
        pacballs.add(new OrangePacball(0, 0));
        pacballs.add(new GreenPacball(0, 0));

        //Play
        while(!pacballs.isEmpty() && lifes > 0) {
            //PLAY
            if (isCollision()) {
                if (pacman.getState() == Pacman.State.SUPER) {
                    //Ghosts to Center
                    //Ghosts speed low
                } else if (pacman.getState() == Pacman.State.NORMAL) {
                    lifes -= 1;
                }
            }
        }

        //End of Game, decide if the player won or Lost
        if (lifes > 0) {
            //WON
        } else {
            //LOST
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
        if (prev_points / 5000 > points / 5000) {
            lifes += 1;
        }
    }

    //Remove Pacball after being eaten
    public static void RemovePacball(int x, int y) {
        int to_remove_index = -1;
        for (int i = 0; i < pacballs.size(); i++) {
            if (pacballs.get(i).getX() == x && pacballs.get(i).getY() == y) {
                to_remove_index = i;
            }
        }
        if (to_remove_index > 0) {
            pacballs.remove(to_remove_index);
        }
    }
}