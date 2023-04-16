package objects.enemies;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.player.PlayerData;
import objects.projectiles.CircleBullet;

public class CircleDrone extends Enemy {

	CircleBullet[] pieces;
	int amount;
	float radius;
	public CircleDrone(float radius) {
		// w h sp mh id cm
		super(20, 20, 12f, 12, ID.Enemy, true, 120);
		this.amount = 64;
		this.radius = radius;
		pieces = new CircleBullet[amount];

	}

	@Override
	public void init() {
		float r = engine.BasicMath.randFloat(70,84+radius);
		for (int i = 0; i < amount; i++) {
			CircleBullet b = new CircleBullet(x, y, r, 3f, 8, 0.1f, (amount) * i);
			pieces[i] = b;
			Handler.obj.addObj(b);
		}
	}

	@Override
	public void respawn() {
		x = engine.BasicMath.randFloat(50, Game.w-50);
		y = -radius;
	}
	
	@Override
	public void update() {

		y += speed * 0.1f;

		// Relocation
		if (respawn) {
			respawn = false;
			x = engine.BasicMath.randFloat(50, Game.w - 50);
			y = 20;
		}

		for (int i = 0; i < pieces.length; i++) {
			pieces[i].move(x + w / 2, y + h / 2);
		}

		super.update();
	}

	@Override
	public void draw() {
		Game.g.drawImage(SpriteLoader.circleDrone, (int) x, (int) y, (int) w, (int) h, null);
		super.draw();
	}

	@Override
	public void destroy() {
		for (int i = 0; i < pieces.length; i++) {
			CircleBullet p = pieces[i];
			if (p != null) {
				p.destroy();
			}
		}
		PlayerData.o.circleDroneKills++;
		super.destroy();
	}
}
