package com.pacman.renderers;

import com.pacman.tiles.Air;

import javax.swing.*;

public class AirRenderer extends JComponent {
    private final Air air;
    public AirRenderer(Air air) {
        this.air = air;
    }
}
