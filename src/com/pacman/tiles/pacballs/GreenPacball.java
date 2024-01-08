package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.Game;
import com.pacman.Main;
import com.pacman.renderers.PacballRenderer;

public class GreenPacball extends AbstractPacball {
    private final int x;
    private final int y;
    private final PacballRenderer renderer;

    public GreenPacball(int x, int y) {
        this.x = x;
        this.y = y;
        this.renderer = new PacballRenderer(this);
    }

    @Override
    public void onPacmanInterract() {
        //Increase Points
        Game.addPoints(1000);

        // Regens maze
        Game.regenerateMaze();

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
    public void delete() {
        Main.removeRenderer(this.renderer);
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public int getRadius() {
        return 7;
    }
}