package states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import objects.GameObject;

@SuppressWarnings("rawtypes")
public abstract class State {

	private static State activeState;

	private final List<GameObject> gameObjects;

	public State() {
		gameObjects = new ArrayList<GameObject>();
	}

	public void addObject(GameObject obj) {
		obj.attach(this);
		gameObjects.add(obj);
	}

	public void update(float delta, float time) {
		gameObjects.forEach(obj -> {
			obj.update(delta, time);
		});

		gameObjects.removeIf(obj -> {
			return !obj.isActive();
		});
	}

	public void draw(Graphics2D graph) {
		gameObjects.forEach(obj -> {
			obj.draw(graph);
		});
	}
	
	public static void switchState(State state) {
		activeState = state;
	}
	
	public static void updateActive(float delta, float timePassed) {
		if (activeState == null) {
			throw new NullPointerException("No active state set");
		}
		activeState.update(delta, timePassed);
	}

	public static void drawActive(Graphics2D graphics) {
		if (activeState == null) {
			throw new NullPointerException("No active state set");
		}
		activeState.draw(graphics);
	}
}
