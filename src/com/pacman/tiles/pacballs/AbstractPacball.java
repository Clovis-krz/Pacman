package com.pacman.tiles.pacballs;

import java.awt.*;
import com.pacman.tiles.Tile;

public abstract class AbstractPacball implements Tile {
	@Override
	public Type getType() {
		return Type.SUPERPACBALL;
	}

	@Override
	public boolean isSolidForPacman() {
		return false;
	}

	@Override
	public boolean isSolidForGhosts() {
		return false;
	}

	/**
	 @return Radius of the pacball, in pixels.
	*/
	public abstract int getRadius();
}
