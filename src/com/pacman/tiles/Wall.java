package com.pacman.tiles;

public class Wall implements Tile{
    final int x;
    final int y;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isSolidForPacman() {
        return true;
    }

    @Override
    public boolean isSolidForGhosts() {
        return true;
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
}

/*
|__ Wall
        |__ final int x, final int y;
        |__ Wall(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → true
        |__ isSolidForGhosts() → true
        |__ onPacmanInterract(): rien;
 */
