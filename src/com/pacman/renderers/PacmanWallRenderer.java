package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.PacmanWall;

import javax.swing.*;

public class PacmanWallRenderer extends JComponent {
    private final PacmanWall element;

    public PacmanWallRenderer(PacmanWall pacmanWall) {
        this.element = pacmanWall;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE;
        int y = element.getY() * Main.ELEMENT_SIZE;

        g.setColor(Color.RED);
        g.fillRect(x, y, Main.ELEMENT_SIZE, Main.ELEMENT_SIZE);
    }
}
