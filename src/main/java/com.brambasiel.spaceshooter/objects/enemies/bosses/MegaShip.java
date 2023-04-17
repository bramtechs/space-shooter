package objects.enemies.bosses;

import java.awt.Color;
import java.awt.Point;

import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import graphics.SpriteLoader;
import com.brambasiel.spaceshooter.gui.Healthbar;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.enemies.Enemy;
import objects.particles.Explosion;
import objects.player.Player;
import objects.player.PlayerData;
import objects.projectiles.Bullet;
import objects.projectiles.HomingBullet;

public class MegaShip extends Enemy {

	enum State{
		Bullet,
		Homing,
	}
	
	public MegaShip() {
		super(200, 100, 0.2f, (int)(500+WaveManager.o.wave*5), ID.Enemy, true,500);
		this.x = Game.w/2f;
		horizSpeed = 0.1f;
		bar = new Healthbar(50,20,Game.w-100,20);
		// TODO Auto-generated constructor stub
	}
	
	float timer2;
	float speedTimer;
	State state = State.Bullet;
	Healthbar bar;
	
	@Override
	public void update() {
		
		float shootsp = BasicMath.clamp(WaveManager.o.wave/5f,1,1.5f);
		if (timer > 2f/shootsp/4f && state == State.Bullet) {
			timer = 0f;
			shoot();
		}
		
		if (timer2 > 2f/shootsp/1.5f && state == State.Homing) {
			timer2 = 0f;
			shoot2();
		}
		y += speed*0.03f;
		
		timer2 += Game.deltaTime+ BasicMath.randFloat(0.02f);
		
		//Change state
		if (health < maxHealth/2f) {
			state = State.Homing;
		}
		else {
			state = State.Bullet;
		}
		
		//Change speed
		if (speedTimer < 4f)
			horizSpeed = 5f;
		else if (speedTimer < 10f)
			horizSpeed = 1f;
		else
			speedTimer = 0f;
		
		//Move up if player up
		if (y > Player.obj.y)
			y -= 5f;
		
		speedTimer += Game.deltaTime;
		
		//UI
		bar.change(health, maxHealth);
		bar.update();
		super.update();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		int xx = (int)(x*Game.scale);
		int yy = (int)(y*Game.scale);
		int ww = (int)(w*Game.scale);
		int hh = (int)(h*Game.scale);
		Game.g.drawImage(SpriteLoader.dualdrone, xx, yy, ww, hh,null);
		bar.draw();
		super.draw();
	}

	void shoot() {
		float xx = BasicMath.randFloat(w);
		Bullet b = new Bullet(x+2+w-xx, y+h+5, true, Color.yellow, 10f,3,14f);
		Bullet bl = new Bullet(x+2+xx, y+h+5, true, Color.red, 20f,5,20f);
		
		Handler.obj.addObj(b);
		Handler.obj.addObj(bl);
	}
	
	void shoot2() {
		HomingBullet hb = new HomingBullet(x+w-w/4f, y+h-7, true, Color.pink, 20, 5, 25f);
		HomingBullet hb2 = new HomingBullet(x+w/4f, y+h-7, true, Color.pink, 20, 5, 25f);
		Bullet bl = new Bullet(x+4,y+h-7,true,Color.yellow,20,5,10f);
		Bullet br = new Bullet(x+w-4,y+h-7,true,Color.yellow,20,5,10f);
		Handler.obj.addObj(bl);
		Handler.obj.addObj(br);
		Handler.obj.addObj(hb);
		Handler.obj.addObj(hb2);
	}

	@Override
	public void destroy() {
		for (int i = 0; i < 7; i++) {
			int xx = (int) BasicMath.randFloat(x, x+w);
			int yy = (int) BasicMath.randFloat(y, y+h);
			Explosion e = new Explosion(new Point(xx,yy),20,70,5,12f,Color.ORANGE);
			Handler.obj.deleteObj(this);
			Handler.obj.addObj(e);
		}
		WaveManager.o.enemies.remove(this);
		PlayerData.score(score);
	}
}
