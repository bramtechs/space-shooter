package com.brambasiel.spaceshooter.states;

import com.brambasiel.spaceshooter.objects.GameObject;
import com.brambasiel.spaceshooter.objects.RenderUpdateable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class State implements RenderUpdateable {

	private final List<GameObject> gameObjects;

	public State() {
		gameObjects = new ArrayList<>();
	}

	public void addObject(GameObject obj) {
		obj.attach(this);
		gameObjects.add(obj);
	}

	@Override
	public void update(float delta, float time) {
		gameObjects.forEach(obj -> {
			obj.update(delta, time);
		});

		gameObjects.removeIf(obj -> {
			return !obj.isActive();
		});
	}

	@Override
	public void draw(Graphics2D graph) {
		gameObjects.forEach(obj -> {
			obj.draw(graph);
		});
	}
}
