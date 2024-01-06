package com.pacman.tiles;

import java.awt.*;


public class Air implements Tile {
    private final int x;
    private final int y;

    public Air(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    @Override
    public void onPacmanInterract() {}

    @Override
    public int getX() {
	    return this.x;
    }

    @Override
    public int getY() {
	    return this.y;
    }

    @Override
    public void draw() {}

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void delete() {}
}