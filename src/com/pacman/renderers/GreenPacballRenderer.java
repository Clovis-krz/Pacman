package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.GreenPacball;

import javax.swing.*;

public class GreenPacballRenderer extends JComponent {
    private final GreenPacball element;

    public GreenPacballRenderer(GreenPacball greenPacball) {
        this.element = greenPacball;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE + 2;
        int y = element.getY() * Main.ELEMENT_SIZE + 2;

        g.setColor(Color.GREEN);

        g.fillArc(x, y, Main.ELEMENT_SIZE - 4, Main.ELEMENT_SIZE - 4, 0, 360);
    }
}
