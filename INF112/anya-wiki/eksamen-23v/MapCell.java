package inf112.peripheryplanet.map;

import java.util.List;

import inf112.peripheryplanet.pawns.Pawn;
import inf112.peripheryplanet.terrain.Terrain;

public interface MapCell {
	/** terrain at this location (determines movement cost) */
	Terrain terrain();

	/**
	 * the pawn (colonist, animal, etc) currently at this location (only one
	 * allowed!)
	 */
	Pawn pawn();

	/**
	 * replace the pawn (colonist, animal, etc) currently at this location (only one
	 * allowed!)
	 */
	Pawn pawn(Pawn newPawn);

	/** other items at this location (latest added first) */
	List<MapElement> items();

}
