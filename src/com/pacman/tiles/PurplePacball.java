package com.pacman.tiles;

public class PurplePacball implements Tile{
    final int x;
    final int y;

    public PurplePacball(int x, int y) {
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

    // TODO: increase by 300 game points, pacman become invisible, change pacman color to light yellow
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
|__ PurplePacball
        |__ final int x, final int y;
        |__ PurplePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs voilettes;
 */