package com.pacman.tiles;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.TileRenderer;

public class Wall implements Tile {
    private final int x;
    private final int y;

    private final TileRenderer renderer;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new TileRenderer(this);
        Main.addRenderer(renderer);
    }

    @Override
    public boolean isSolidForPacman() {
        return true;
    }

    @Override
    public boolean isSolidForGhosts() {
        return true;
    }

    @Override
    public void onPacmanInterract() {}

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
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public void delete() {
        Main.removeRenderer(this.renderer);
    }
}