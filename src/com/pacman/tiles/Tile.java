package com.pacman.tiles;

import java.awt.*;


public interface Tile {
    enum Type {
        NORMAL,
        PACBALL,
        SUPERPACBALL
    }

    /**
     Type of the tile, used to check that a tile is effectively a pacball.
    */
    Type getType();

    /**
     @return Whether the tile should be considered as a wall by pacman
    */
    boolean isSolidForPacman();

    /**
     @return Whether the tile should be considered as a wall by ghosts
    */
    boolean isSolidForGhosts();

    /**
     @return Function that gets called when pacman goes on the tile.
    */
    void onPacmanInterract();

    /**
     @return Returns the X coordinate of the tile.
    */
    int getX();

    /**
     @return Returns the Y coordinate of the tile.
    */
    int getY();

    /**
     @return The prefered color used by rendering, present here in order to make the codebase much cleaner and not have to do double a lot of classes.
    */
    Color getColor();

    /**
     Unregisters the tile's renderer in the window, registering the tile is done automatically when creating it.
    */
    void delete();
}