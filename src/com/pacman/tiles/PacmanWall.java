package com.pacman.tiles;

public class PacmanWall implements Tile{
    final int x;
    final int y;

    public PacmanWall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isSolidForPacman() {
        return true;
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
}
/*
|__ PacmanWall
        |__ final int x, final int y;
        |__ PacmanWall(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → true
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): rien;
 */
