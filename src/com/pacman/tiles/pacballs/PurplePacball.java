package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.entities.Pacman;
import com.pacman.renderers.PacballRenderer;

public class PurplePacball extends AbstractPacball {
    private final int x;
    private final int y;
    private final PacballRenderer renderer;

    public PurplePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PacballRenderer(this);
        Main.addRenderer(renderer);
    }

    @Override
    public void onPacmanInterract() {
        // Set Pacman state to invisible
        Game.setPowerupState(Pacman.State.INVISIBLE);

        // Increase Points
        Game.addPoints(300);

        //Remove Pacball
        Game.consumePacball(this.x, this.y);
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

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }

    @Override
    public int getRadius() {
        return 7;
    }
}