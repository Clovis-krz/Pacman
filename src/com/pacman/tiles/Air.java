package com.pacman.tiles;

public class Air implements Tile {
    final int x;
    final int y;

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
    public void onPacmanInterract() {

    }

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
    public void delete() {}
}

/*
|__ Air
        |__ final int x, final int y;
        |__ Air(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): rien;
 */