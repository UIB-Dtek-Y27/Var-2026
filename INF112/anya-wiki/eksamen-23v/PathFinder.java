package inf112.peripheryplanet.map;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public interface PathFinder {

	/** create a new path finder */
	static PathFinder finder(Map<Vector2, MapCell> map) {
		return new PathFinderBaseImpl(map);
	}

	/** where to start from */
	PathFinder from(Vector2 pos);

	/** where we want to go to */
	PathFinder to(Vector2 pos);

	/**
	 * a waypoint we want to include in the path (can be given multiple times)
	 */
	PathFinder via(Vector2 pos);

	/**
	 * a (dangerous?) area we should avoid (can be given multiple times)
	 */
	PathFinder avoid(Polygon area);

	/** prefer the shortest path */
	PathFinder shortest();

	/** prefer the safest path */
	PathFinder safest();

	/** prefer the fastest path */
	PathFinder fastest();

	/**
	 * calculate and return the path (null if destination is unreachable)
	 */
	List<Vector2> calculate();
}