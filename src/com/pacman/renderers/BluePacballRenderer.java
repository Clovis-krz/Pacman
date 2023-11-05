package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.BluePacball;

import javax.swing.*;

public class BluePacballRenderer extends JComponent {
    private final BluePacball element;

    public BluePacballRenderer(BluePacball bluePacball) {
        this.element = bluePacball;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE + 3;
        int y = element.getY() * Main.ELEMENT_SIZE + 4;

        g.setColor(Color.CYAN);

        g.fillArc(x, y, Main.ELEMENT_SIZE - 7, Main.ELEMENT_SIZE - 7, 0, 360);
    }
}
