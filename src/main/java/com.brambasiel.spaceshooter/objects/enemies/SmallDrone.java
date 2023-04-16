package objects.enemies;

import java.awt.Color;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.player.PlayerData;
import objects.projectiles.Bullet;

public class SmallDrone extends Enemy {

	public SmallDrone() {
		//    w   h sp mh id cm
		super(30,20,7f,22,ID.Enemy,true,20);
	}

	@Override
	public void update() {
		
		float shootsp = engine.BasicMath.clamp(WaveManager.o.wave/5f,1,5);
		if (timer > 6f/shootsp) {
			timer = 0f;
			shoot();
		}
		
		y += speed*0.1f;
		
		//Relocation
		if (respawn) {
			respawn = false;
			x = engine.BasicMath.randFloat(50, Game.w-50);
			y = 20;
		}
		
		super.update();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Game.g.setColor(Color.orange);
		Game.g.drawImage(SpriteLoader.smalldrone, (int)x, (int)y, (int)w, (int)h,null);
		super.draw();
	}

	void shoot() {
		Bullet b = new Bullet(x, y+5, true, Color.yellow, 10f,3,14f);
		Handler.obj.addObj(b);
	}
	
	@Override
	public void destroy() {
		PlayerData.o.smallDroneKills++;
		super.destroy();
	}
}
