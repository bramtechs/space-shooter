package objects.projectiles;

import java.awt.Color;

import audio.AudioPlayer;
import engine.Game;
import objects.GameObject;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.player.Player;
import physics.Hitbox;

public class Bullet extends GameObject {
	
	boolean enemy;
	float damage, speed, width;
	Color c;
	Hitbox hit;
	
	public Bullet(float x, float y, boolean enemy,Color c, float damage,int width, float speed) {
		super(x,y,ID.Bullet);
		this.enemy = enemy;
		this.damage = damage+WaveManager.o.wave/8f;
		this.speed = speed+WaveManager.o.wave/10f;
		this.width = width;
		this.c = c;
		hit = new Hitbox();
		Handler.obj.registerBullet(this);
		if (enemy && WaveManager.o.wave < 60)
			AudioPlayer.play("bulletshot");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (enemy) {
			y += speed*0.1f;
			checkPlayerCollision();
		}else {
			y -= speed*0.1f;
			checkEnemyCollision();
		}
		
		checkBulletCollision();
		
		//Despawn out of bounds
		if (y < -10) {
			destroy();
		}
		else if (y > Game.size.y+10) {
			destroy();
		}
		
		hit.move((int)x, (int)y, (int)width, (int)width*2);
	}

	void checkBulletCollision() {
		for (int i = 0; i < Handler.obj.bullets.size(); i++)
		{
			Bullet b = Handler.obj.bullets.get(i);
			if (b.equals(this)) continue;
			if (b.hit.isCollidingHitbox(hit)) {
				Game.print("Bullet touched another");
				destroy();
			}
		}
	}

	void checkPlayerCollision() {
		//Point p = new Point((int)x,(int)y);
		Hitbox b = Player.hit;
		if (b.isCollidingHitbox(hit)) {
			Player.damage(damage);
			destroy();
		}
	}

	void checkEnemyCollision() {
		//Check collision
		for (int i = 0; i < WaveManager.o.enemies.size(); i++) {
			//Point p = new Point((int)x,(int)y);
			Hitbox b = WaveManager.o.enemies.get(i).hit;
			if (b.isCollidingHitbox(hit)) {
				WaveManager.o.enemies.get(i).damage(damage);
				destroy();
			}
		}
	}
	
	public void destroy() {
		Handler.obj.deleteBullet(this);
		Handler.obj.deleteObj(this);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(c);
		int xx = (int)(x-width/2);
		int yy = (int)(y);
		int ss = (int)(width);
		
		Game.g.fillRect(xx, yy, ss, ss*2);
		if (Game.debug)
			hit.draw();
	}
}
