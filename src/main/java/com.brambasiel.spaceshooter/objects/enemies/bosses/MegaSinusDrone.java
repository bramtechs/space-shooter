package objects.enemies.bosses;

import java.awt.Color;
import java.awt.Point;

import engine.Game;
import graphics.SpriteLoader;
import gui.primitives.Healthbar;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.enemies.Enemy;
import objects.enemies.HomingDrone;
import objects.particles.Explosion;
import objects.player.Player;
import objects.player.PlayerData;
import objects.projectiles.JoltBullet;

public class MegaSinusDrone extends Enemy {

	enum State {
		Bullet,
		Spawn,
	}

	public MegaSinusDrone() {
		super(150, 100, 0.2f, (int) (500 + WaveManager.o.wave * 5), ID.Enemy, true, 500);
		this.x = Game.w / 2f;
		horizSpeed = 0.1f;
		bar = new Healthbar(50, 20, Game.w - 100, 20);
		// TODO Auto-generated constructor stub
	}

	float timer2;
	float speedTimer;
	State state = State.Bullet;
	Healthbar bar;

	boolean shoot = false;
	float roundTimer;
	@Override
	public void update() {
		float shootsp = engine.BasicMath.clamp(WaveManager.o.wave / 5f, 1, 1.5f);
		if (timer > 2f / shootsp / 4f && shoot) {
			timer = 0f;
			if (state == State.Bullet) {
				shoot();
			}
			else if (state == State.Spawn) {
				spawn();
			}
		}
		y += speed * 0.03f;

		// Change speed
		if (speedTimer < 3f) {
			horizSpeed = 3f;
			shoot = false;
		}
		else if (speedTimer < 4f) {
			horizSpeed = 1f;
			shoot = true;
		}
		else {
			speedTimer = 0f;
		}
		// Move up if player up
		if (y > Player.obj.y)
			y -= 5f;
		
		//Change state
		if (roundTimer < 8) {
			state = State.Bullet;
		}
		else if (roundTimer < 10) {
			state = State.Spawn;
		}
		else {
			roundTimer = 0f;
		}
		
		speedTimer += Game.deltaTime;
		roundTimer += Game.deltaTime;
		System.out.println(state);
		// UI
		bar.change(health, maxHealth);
		bar.update();
		
		super.update();
	}

	void spawn() {
		WaveManager.spawn(new HomingDrone());
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		int xx = (int) (x * Game.scale);
		int yy = (int) (y * Game.scale);
		int ww = (int) (w * Game.scale);
		int hh = (int) (h * Game.scale);
		Game.g.drawImage(SpriteLoader.sinusdrone, xx, yy, ww, hh, null);
		bar.draw();
		super.draw();
	}

	void shoot() {
		JoltBullet hb = new JoltBullet(x + w - w / 4f, y + h - 7, true, 20f, 25f, Color.green);
		JoltBullet hb2 = new JoltBullet(x + w / 4f, y + h - 7, true, 20f, 25f, Color.green);
		Handler.obj.addObj(hb);
		Handler.obj.addObj(hb2);
	}

	@Override
	public void destroy() {
		for (int i = 0; i < 7; i++) {
			int xx = (int) engine.BasicMath.randFloat(x, x + w);
			int yy = (int) engine.BasicMath.randFloat(y, y + h);
			Explosion e = new Explosion(new Point(xx, yy), 20, 70, 5, 12f, Color.ORANGE);
			Handler.obj.deleteObj(this);
			Handler.obj.addObj(e);
		}
		WaveManager.o.enemies.remove(this);
		PlayerData.score(score);
	}
}
