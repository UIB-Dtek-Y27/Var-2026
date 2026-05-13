package inf112.peripheryplanet.pawns;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import inf112.peripheryplanet.map.MapCell;
import inf112.peripheryplanet.map.PathFinder;

public class HumanColonist implements Pawn {
	private Texture image;
	private Vector2 position;
	private Vector2 target;

	public HumanColonist(int x, int y) {
		this.position = new Vector2(x, y);
		//this.image = new Texture("colonist.png");
		this.image = new Texture(100, 100, Format.RGB888);
	}

	public void setTarget(int destX, int destY) {
		target = new Vector2(destX, destY);
	}

	public void step(Map<Vector2,MapCell> world) { // do single timestep
		if (target != null) {
			if (position.equals(target)) { // are we there yet?
				target = null; // yes!
			} else {
				List<Vector2> path = PathFinder.finder(world).from(position).to(target).calculate();
				if (path != null) {
					// take one step & update map
					world.get(position).pawn(null);
					position = path.remove(0);
					world.get(position).pawn(this);
				} else {
					// ??? target is unreachable
				}
			}
		}
		// ... or do something else
	}

	public void draw(SpriteBatch batch) {
		batch.draw(image, position.x, position.y);
	}

	public Vector2 position() {
		return position;
	}
}
