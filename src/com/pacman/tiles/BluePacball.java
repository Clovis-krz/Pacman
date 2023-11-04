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
        int prev_points = Main.points;
        Main.points += 100;

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
|__ BluePacball
        |__ final int x, final int y;
        |__ BluePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): on ajoute les points;
 */
