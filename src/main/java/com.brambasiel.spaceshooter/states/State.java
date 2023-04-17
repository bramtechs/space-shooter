package com.brambasiel.spaceshooter.states;

import com.brambasiel.spaceshooter.objects.GameObject;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class State {

	private final List<GameObject> gameObjects;

	public State() {
		gameObjects = new ArrayList<>();
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
}
