package inf112.peripheryplanet.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import inf112.peripheryplanet.pawns.Bird;
import inf112.peripheryplanet.pawns.Cat;
import inf112.peripheryplanet.pawns.Dodo;
import inf112.peripheryplanet.pawns.Duck;
import inf112.peripheryplanet.pawns.Pawn;

// pathfinder algorithm implemented here
public class PathFinderImpl extends PathFinderBaseImpl implements PathFinder {
	public PathFinderImpl(Map<Vector2, MapCell> map) {
		super(map);
	}

	// OLD: pathfinder will prefer cells with *low* cost
	protected double calculateCostForMapCell(MapCell mapCell) {
		if(mapCell == null)
			return 1; // default cost
		else if(mapCell.pawn() != null)
			return Double.POSITIVE_INFINITY; // already occupied
		else
			return mapCell.terrain().movementCost();
	}

	// NEW: pathfinder will prefer cells with *low* cost
	protected double calculateCostForMapCell(MapCell mapCell, Pawn pawn) {
		if(mapCell == null)
			return 1; // default cost
		else if(pawn instanceof Bird && !(pawn instanceof Dodo))
			return 1; // birds (except dodos) can fly, ignore terrain
		else if(mapCell.pawn() != null && !(pawn instanceof Cat)) // cats can sneak past other pawns
			return Double.POSITIVE_INFINITY; // already occupied
		else if(mapCell.terrain() instanceof Water && pawn instanceof Duck) // can swim
			return 1;
		else if(pawn.footwear() instanceof RollerSkates && mapCell.terrain() instanceof Paved)
			// roller skates are extra fast on paved terrain
			return mapCell.terrain().movementCost() / 2;
		else
			return mapCell.terrain().movementCost();
	}

	protected List<Vector2> calculatePath() {
		List<Vector2> result = new ArrayList<>();
		// …
		return result;
	}

}
