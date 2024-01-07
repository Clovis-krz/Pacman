package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.entities.Pacman;
import com.pacman.powerups.SuperState;
import com.pacman.renderers.PacballRenderer;

public class OrangePacball extends AbstractPacball {
    private final int x;
    private final int y;
    private final PacballRenderer renderer;

    public OrangePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PacballRenderer(this);
        Main.addRenderer(renderer);
    }

    @Override
    public void onPacmanInterract() {
        // Increase score
        Game.addPoints(500);

        // Set powerup state to superpacman
        Game.setPowerupState(new SuperState());

        // Remove pacball
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
        return Color.ORANGE;
    }

    @Override
    public int getRadius() {
        return 7;
    }
}