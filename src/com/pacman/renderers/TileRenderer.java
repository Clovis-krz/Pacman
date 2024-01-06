package com.pacman.renderers;

import java.awt.*;
import com.pacman.Game;
import com.pacman.tiles.Tile;

import javax.swing.*;

public class TileRenderer extends JComponent {
    private final Tile element;

    public TileRenderer(Tile element) {
        this.element = element;
        this.setSize(Game.ELEMENT_SIZE * Game.GRID_WIDTH, Game.ELEMENT_SIZE * Game.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Game.ELEMENT_SIZE;
        int y = element.getY() * Game.ELEMENT_SIZE;

        g.setColor(element.getColor());
        g.fillRect(x, y, Game.ELEMENT_SIZE, Game.ELEMENT_SIZE);
    }
}
