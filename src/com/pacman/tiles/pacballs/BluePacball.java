package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.PacballRenderer;

public class BluePacball extends AbstractPacball {
    private final int x;
    private final int y;
    private final PacballRenderer renderer;

    public BluePacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PacballRenderer(this);
        Main.addRenderer(renderer);
    }

    @Override
    public void onPacmanInterract() {
        // Increase score
        Game.addPoints(100);

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
        return new Color(0, 140, 255);
    }

    @Override
    public int getRadius() {
        return 5;
    }
}