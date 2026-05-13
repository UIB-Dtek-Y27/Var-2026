package inf112.peripheryplanet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;

import inf112.peripheryplanet.pawns.HumanColonist;
import inf112.peripheryplanet.pawns.Pawn;


public class PathFindingTest {

	private HumanColonist player;
	private List<Vector2> correctPath = List.of(new Vector2(2, 2), 
			new Vector2(2, 3), new Vector2(2, 4), new Vector2(3, 5));
	private Map<Vector2,Pawn> world;

	@BeforeEach
	void setupBeforeEach() {
		player = new HumanColonist(2, 2);
		world = new HashMap<>();
		world.put(player.position(), player);
	}
	
	@Test
	void testPathFinding() {
		player.setTarget(3, 5);
		for(Vector2 step : correctPath) {
			assertEquals(step, player.position());
			player.step(world);
		}
	}
}
