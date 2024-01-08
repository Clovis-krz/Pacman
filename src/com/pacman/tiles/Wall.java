package com.pacman.tiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.TileRenderer;

public class Wall implements Tile {
    private final int x;
    private final int y;

    private final TileRenderer renderer;
    private final Color wallcolor;

    public Wall(int x, int y, Color wall_color) {
        this.x = x;
        this.y = y;
        this.wallcolor = wall_color;
        this.renderer = new TileRenderer(this);
    }

    @Override
    public Type getType() {
        return Type.NORMAL;
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
    public Color getColor() {
        return wallcolor;
    }

    @Override
    public void delete() {
        Main.removeRenderer(this.renderer);
    }
}