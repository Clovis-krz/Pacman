package com.pacman.renderers;

import com.pacman.tiles.PacmanWall;

import javax.swing.*;

public class PacmanWallRenderer extends JComponent {
    final PacmanWall pacmanWall;
    public PacmanWallRenderer(PacmanWall pacmanWall) {
        this.pacmanWall = pacmanWall;
    }
}
