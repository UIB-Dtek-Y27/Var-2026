package inf112.peripheryplanet.terrain;

import com.badlogic.gdx.graphics.Color;

public interface Terrain {
	/** movement cost for this type of terrain */
	double movementCost();

	/** primary color */
	Color color();

	/**
	 * how much weight can this terrain support?
	 */
	double strength();

	/**
	 * next terrain layer – for example, this might be "fine carpet", with "stone
	 * floor" underneath, and "dirt" below
	 */
	Terrain next();
}
