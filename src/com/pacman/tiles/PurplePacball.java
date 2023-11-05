package com.pacman.tiles;

import com.pacman.Main;
import com.pacman.entities.Pacman;
import com.pacman.renderers.PurplePacballRenderer;

public class PurplePacball implements Tile{
    final int x;
    final int y;
    final PurplePacballRenderer renderer;

    public PurplePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PurplePacballRenderer(this);
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
        // Set Pacman state to invisible
        Main.setPowerupState(Pacman.State.INVISIBLE);

        // Increase Points
        Main.addPoints(300);

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
|__ PurplePacball
        |__ final int x, final int y;
        |__ PurplePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs voilettes;
 */
