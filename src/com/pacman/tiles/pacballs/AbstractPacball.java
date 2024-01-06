package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.tiles.Tile;

public abstract class AbstractPacball implements Tile {
	@Override
	public boolean isSolidForPacman() {
		return false;
	}

	@Override
	public boolean isSolidForGhosts() {
		return false;
	}

	public abstract int getRadius();
}
