package com.pacman.renderers;

import java.awt.*;
import com.pacman.Main;
import com.pacman.tiles.PurplePacball;

import javax.swing.*;

public class PurplePacballRenderer extends JComponent {
    private final PurplePacball element;

    public PurplePacballRenderer(PurplePacball purplePacball) {
        this.element = purplePacball;
        this.setSize(Main.ELEMENT_SIZE * Main.GRID_WIDTH, Main.ELEMENT_SIZE * Main.GRID_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = element.getX() * Main.ELEMENT_SIZE + 2;
        int y = element.getY() * Main.ELEMENT_SIZE + 2;

        g.setColor(Color.MAGENTA);

        g.fillArc(x, y, Main.ELEMENT_SIZE - 4, Main.ELEMENT_SIZE - 4, 0, 360);
    }
}
