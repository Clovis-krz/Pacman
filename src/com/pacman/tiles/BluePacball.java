package com.pacman.tiles;

import com.pacman.Main;
import com.pacman.renderers.BluePacballRenderer;

public class BluePacball implements Tile{
    final int x;
    final int y;
    final BluePacballRenderer renderer;

    public BluePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new BluePacballRenderer(this);
        Main.addRenderer(renderer);
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
        //Increase Points
        Main.addPoints(100);

        //Remove Pacball
        Main.consumePacball(this.x, this.y);
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

    @Override
    public void delete() {
        Main.removeRenderer(this.renderer);
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
