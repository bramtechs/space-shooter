package objects;

import java.awt.Graphics2D;

public abstract class GameObject {
	public float x, y;
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update(float delta, float time);
	public abstract void draw(Graphics2D graph);
}
