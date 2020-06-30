package states;

import java.awt.Graphics2D;

public abstract class State {
	public abstract void update(float delta, float time);
	public abstract void draw(Graphics2D graph);
}
