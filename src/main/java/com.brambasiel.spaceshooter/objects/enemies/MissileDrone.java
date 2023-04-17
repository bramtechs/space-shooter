package objects.enemies;

import java.awt.Color;
import java.awt.Point;

import com.brambasiel.spaceshooter.BasicMath;
import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.particles.Explosion;
import objects.particles.MissileThruster;
import objects.particles.Thruster;
import objects.player.Player;
import physics.Hitbox;

public class MissileDrone extends Enemy {
	public static int amountToSpawn = 5;
	static int snap = 80;

	Thruster thrust;
	float spawnDelay;

	public MissileDrone() {
		// w h sp mh id cm
		super(10, 50, 75f, 20000, ID.Enemy, true, 0);
		spawnDelay = Math.max(1, 4 - (WaveManager.o.wave / 10f));
		y = -h * 2;
		x = Math.round(x / snap) * snap;
		thrust = new MissileThruster();
		if (amountToSpawn > 1) {
			amountToSpawn--;
			WaveManager.spawnNow(new MissileDrone());
		}
	}

	@Override
	public void update() {
		X = (int) x;
		Y = (int) y;
		timer += Game.deltaTime + BasicMath.randFloat(0.02f) + (WaveManager.o.wave / 10000f);
		hit.move((int) x, (int) y, w, h);
		checkPlayerCollision();
		
		if (timer > spawnDelay) {
			y += speed * 0.1f;
		}

		// Relocation
		if (respawn) {
			respawn = false;
			x = BasicMath.randFloat(50, Game.w - 50);
			y = -50;
		}
		thrust.move(x, y);
		thrust.update();

		if (y > Game.h + h * 2) {
			destroy();
		}
	}
	@Override
	void checkPlayerCollision() {
		//Point p = new Point((int)x,(int)y);
		Hitbox b = Player.hit;
		if (b.isCollidingHitbox(hit)) {
			Player.damage(80);
			Explosion e = new Explosion(new Point(X,Y+h+5),5,200,2,20f,Color.YELLOW);
			Handler.obj.deleteObj(this);
			Handler.obj.addObj(e);
			destroy();
		}
	}
	
	float warningTimer = 0f;
	int id;

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		Game.g.drawImage(SpriteLoader.missiledrone, X, Y, (int) w, (int) h, null);

		warningTimer += Game.deltaTime* BasicMath.randFloat(0.9f, 1.1f);
		id = warningTimer < 0.5f ? 0 : 1;

		if (timer < spawnDelay) {
			Game.g.drawImage(SpriteLoader.warningIcons[id], X - 16, 7, 32, 32, null);
		}

		super.draw();
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
