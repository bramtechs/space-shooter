package objects.projectiles;

import java.awt.Color;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.WaveManager;
import objects.player.Player;
import physics.Hitbox;

public class CircleBullet extends Bullet {
	boolean enemy;
	float damage, speed;
	int xx, yy;
	float radius;
	Hitbox hit;

	public CircleBullet(float x, float y,float radius, float damage, int size, float speed, float startAngle) {
		super(x, y, true, Color.white, damage, size, speed);
		this.damage = damage + WaveManager.o.wave / 8f;
		this.speed = speed + WaveManager.o.wave / 60f;
		this.radius = radius;
		this.time = startAngle;
		hit = new Hitbox();
		Handler.obj.registerBullet(this);
	}
	
	public void move(float x, float y) {
		this.xx = (int)x;
		this.yy = (int)y;
	}
	
	float time;
	@Override
	public void update() {
		// TODO Auto-generated method stub
		checkPlayerCollision();
		checkBulletCollision();
		
		x = (int)(xx + Math.sin(time)*radius);
		y = (int)(yy + Math.cos(time)*radius);
		
		time += Game.deltaTime;
		// Despawn out of bounds
		if (y < -10) {
			destroy();
		} else if (y > Game.h + 10) {
			destroy();
		}

		hit.move((int)(x), (int)(y), (int) width, (int) width);
	}

	@Override
	void checkBulletCollision() {
		for (int i = 0; i < Handler.obj.bullets.size(); i++) {
			Bullet b = Handler.obj.bullets.get(i);
			if (b.equals(this))
				continue;
			if (b.hit.isCollidingHitbox(hit) && !b.enemy) {
				Game.print("Bullet touched another");
				destroy();
			}
		}
	}

	@Override
	void checkPlayerCollision() {
		// Point p = new Point((int)x,(int)y);
		Hitbox b = Player.hit;
		if (b.isCollidingHitbox(hit)) {
			Player.damage(damage);
			destroy();
		}
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(c);
		Game.g.drawImage(SpriteLoader.circleBullet,(int)x,(int)y,(int)width,(int)width,null);
		if (Game.debug)
			hit.draw();
	}
}
