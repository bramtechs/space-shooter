package states.menu;

import java.awt.image.BufferedImage;

import engine.Game;

public class Asteroid {
	
	float x,y;
	float speed;
	int size;
	BufferedImage sprite;
	
	public Asteroid() {
		x = engine.BasicMath.randFloat(Game.size.x);
		y = engine.BasicMath.randFloat(Game.size.y);
		size = engine.BasicMath.randInt(14, 14*4);
		speed = size;
		sprite = SpriteLoader.asteroids[engine.BasicMath.randInt(SpriteLoader.asteroids.length)];
	}
	
	void restart() {
		x = engine.BasicMath.randFloat(Game.w);
		speed = engine.BasicMath.randFloat(18f, 32f);
		size = engine.BasicMath.randInt(14, 14*4);
		y = engine.BasicMath.randFloat(-Game.h-size);
		sprite = SpriteLoader.asteroids[engine.BasicMath.randInt(SpriteLoader.asteroids.length)];
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
