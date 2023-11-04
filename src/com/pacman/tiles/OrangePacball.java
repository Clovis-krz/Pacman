package com.pacman.tiles;

import com.pacman.Main;
import com.pacman.entities.Ghost;
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

    // TODO: pacman become orange, ghosts become blue
    @Override
    public void onPacmanInterract() {
        //Set Pacman state to SuperPacman
        Main.pacman.setState(Pacman.State.SUPER);

        //Set Ghosts state to Vulnerable
        for (int i = 0; i < Main.ghosts.size(); i++) {
            Main.ghosts.get(i).setState(Ghost.State.VULNERABLE);
        }

        //Increase Points
        int prev_points = Main.points;
        Main.points += 500;

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
|__ OrangePacball
        |__ final int x, final int y;
        |__ OrangePacball(int x, int y);
        |__ getX() → x
        |__ getY() → Y
        |__ isSolidForPacman() → false
        |__ isSolidForGhosts() → false
        |__ onPacmanInterract(): effet des pacballs oranges;
 */
