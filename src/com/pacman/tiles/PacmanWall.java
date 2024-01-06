package com.pacman.tiles;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.TileRenderer;


public class PacmanWall implements Tile{
    private final int x;
    private final int y;
    private final TileRenderer renderer;

    public PacmanWall(int x, int y) {
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
        return false;
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
        return Color.RED;
    }

    @Override
    public void delete() {
        Main.removeRenderer(this.renderer);
    }
}