package com.pacman.tiles;

import com.pacman.renderers.PacmanWallRenderer;

public class PacmanWall implements Tile{
    final int x;
    final int y;
    final PacmanWallRenderer renderer;

    public PacmanWall(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PacmanWallRenderer(this);
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

    @Override
    public void draw() {
        renderer.repaint();
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
