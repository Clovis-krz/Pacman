package com.pacman.renderers;

import com.pacman.tiles.Wall;

import javax.swing.*;

public class WallRenderer extends JComponent {
    final Wall wall;
    public WallRenderer(Wall wall) {
        this.wall = wall;
    }
}