package com.pacman.renderers;

import com.pacman.tiles.GreenPacball;
import com.pacman.tiles.OrangePacball;

import javax.swing.*;

public class OrangePacballRenderer extends JComponent {
    final OrangePacball orangePacball;
    public OrangePacballRenderer(OrangePacball orangePacball) {
        this.orangePacball = orangePacball;
    }
}
