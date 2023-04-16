package objects;

import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject {

	private float maxHealth = 100f;
	private float health;
	private BufferedImage texture;
	
	public Entity(float x, float y, int w, int h, int health, String spriteName) {
		super(x, y);
		this.maxHealth = health;
		this.health = health;
		this.texture = Game.loader.
	}
	
	
}
