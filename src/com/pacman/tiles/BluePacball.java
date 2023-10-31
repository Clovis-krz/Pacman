package com.pacman.tiles;

import com.pacman.renderers.BluePacballRenderer;

public class BluePacball implements Tile{
    final int x;
    final int y;
    final BluePacballRenderer renderer;
    public BluePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new BluePacballRenderer(this);
    }
    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    // TODO: increase by 100 game points
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
    public void draw() {
        renderer.repaint();
    }
}
/*
|__ BluePacball
        |__ final int x, final int y;
        |__ BluePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): on ajoute les points;
 */
