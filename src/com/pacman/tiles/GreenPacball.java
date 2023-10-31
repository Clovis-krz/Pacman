package com.pacman.tiles;

import com.pacman.renderers.GreenPacballRenderer;

public class GreenPacball implements Tile{
    final int x;
    final int y;
    final GreenPacballRenderer renderer;
    public GreenPacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new GreenPacballRenderer(this);
    }
    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    // TODO: increase by 1000 game points, change labyrinth structure
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
|__ GreenPacball
        |__ final int x, final int y;
        |__ OrangePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs vertes;
 */
