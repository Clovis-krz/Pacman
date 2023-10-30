package com.pacman.tiles;

public interface Tile {
    boolean isSolidForPacman();

    boolean isSolidForGhosts();

    void onPacmanInterract();

    int getX();
    int getY();
}

/*

Tile (interface)
        |__ boolean isSolidForPacman()
        |__ boolean isSolidForGhosts()
        |__ void onPacmanInterract()
        |__ int getX();
        |__ int getY();
 */
