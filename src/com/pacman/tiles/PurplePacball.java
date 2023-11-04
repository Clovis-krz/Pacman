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
    }

    @Override
    public boolean isSolidForPacman() {
        return false;
    }

    @Override
    public boolean isSolidForGhosts() {
        return false;
    }

    // TODO: change pacman color to light yellow
    @Override
    public void onPacmanInterract() {
        //Set Pacman state to invisible
        Main.pacman.setState(Pacman.State.INVISIBLE);

        //Increase Points
        int prev_points = Main.points;
        Main.points += 300;

        //Update life counter if necessary
        Main.UpdateLife(prev_points);

        //Remove Pacball
        Main.RemovePacball(this.x, this.y);
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
|__ PurplePacball
        |__ final int x, final int y;
        |__ PurplePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs voilettes;
 */
