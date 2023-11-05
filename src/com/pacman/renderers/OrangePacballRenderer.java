package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.OrangePacball;

import javax.swing.*;

public class OrangePacballRenderer extends JComponent {
    private final OrangePacball element;

    public OrangePacballRenderer(OrangePacball orangePacball) {
        this.element = orangePacball;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE + 2;
        int y = element.getY() * Main.ELEMENT_SIZE + 2;

        g.setColor(Color.ORANGE);

        g.fillArc(x, y, Main.ELEMENT_SIZE - 4, Main.ELEMENT_SIZE - 4, 0, 360);
    }
}
