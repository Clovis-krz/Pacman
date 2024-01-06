package com.pacman.tiles;

import java.awt.*;


public interface Tile {
    boolean isSolidForPacman();

    boolean isSolidForGhosts();

    void onPacmanInterract();

    int getX();
    int getY();
    void draw();

    Color getColor();

    void delete();
}