package com.pacman.tiles;

import com.pacman.Main;
import com.pacman.entities.Pacman;
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

    @Override
    public void onPacmanInterract() {
        //Increase Points
        Main.addPoints(500);

        Main.setPowerupState(Pacman.State.SUPER);

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
