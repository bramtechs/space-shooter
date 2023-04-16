package objects;

import java.awt.Graphics2D;
import states.State;

public abstract class GameObject<T> {
	public float x, y;

	private State state;
	private boolean isActive;
	
	public GameObject() {
		this.isActive = true;
	}

	@SuppressWarnings("unchecked")
	public T position(float x, float y) {
		this.x = x;
		this.y = y;
		return (T) this;
	}
	
	public void attach(State state) {
		this.state = state;
	}

	public void selfDestruct() {
		isActive = false;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public State getState() {
		if (state == null) {
			throw new IllegalStateException("GameObject isn't attached to any state!");
		}
		return state;
	}

	public abstract void update(float delta, float timePassed);
	public abstract void draw(Graphics2D graph);
}
