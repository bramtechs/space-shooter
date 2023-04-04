package states.menu;

import java.awt.image.BufferedImage;

import engine.Game;
import engine.SpriteLoader;
import objects.GameObject;

public class Asteroid extends GameObject {
	
	private float speed;
	private int size;
	private BufferedImage sprite;
	
	public Asteroid() {
		super(0.f,0.f);
		restart();
	}
	
	void restart() {
		x = engine.BasicMath.randFloat(Game.w);
		speed = engine.BasicMath.randFloat(18f, 32f);
		size = engine.BasicMath.randInt(14, 14*4);
		y = engine.BasicMath.randFloat(-Game.h-size);
		sprite = SpriteLoader.getRandomAsteroidTexture();
	}
	
	public void update() {
		y += Game.deltaTime;
		
		if (y > Game.h + size) {
			restart();
		}
	}
	public void draw() {
		Game.g.drawImage(sprite, (int)x, (int)y, size, size, null);
	}
}
