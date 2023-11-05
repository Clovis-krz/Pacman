package com.pacman.tiles;

import com.pacman.Main;
import com.pacman.renderers.GreenPacballRenderer;

public class GreenPacball implements Tile{
    final int x;
    final int y;
    final GreenPacballRenderer renderer;
    public GreenPacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new GreenPacballRenderer(this);
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
        Main.addPoints(1000);

        // TODO: change labyrinth structure

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
|__ GreenPacball
        |__ final int x, final int y;
        |__ OrangePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs vertes;
 */
