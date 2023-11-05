package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.Wall;

import javax.swing.*;

public class WallRenderer extends JComponent {
    private final Wall element;

    public WallRenderer(Wall wall) {
        this.element = wall;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE;
        int y = element.getY() * Main.ELEMENT_SIZE;

        g.setColor(Color.BLUE);
        g.fillRect(x, y, Main.ELEMENT_SIZE, Main.ELEMENT_SIZE);
    }
}
