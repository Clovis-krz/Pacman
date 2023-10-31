package com.pacman.tiles;

import com.pacman.renderers.OrangePacballRenderer;

public class OrangePacball implements Tile{
    final int x;
    final int y;
    final OrangePacballRenderer renderer;
    public OrangePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new OrangePacballRenderer(this);
    }
    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    // TODO: increase by 500 game points, pacman become SuperPacman and orange, ghosts become blue
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
|__ OrangePacball
        |__ final int x, final int y;
        |__ OrangePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs oranges;
 */
