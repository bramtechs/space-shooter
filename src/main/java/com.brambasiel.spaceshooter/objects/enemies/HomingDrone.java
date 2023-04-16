package objects.enemies;

import java.awt.Color;

import engine.Game;
import graphics.SpriteLoader;
import objects.Handler;
import objects.ID;
import objects.WaveManager;
import objects.player.PlayerData;
import objects.projectiles.HomingBullet;

public class HomingDrone extends Enemy {

	public HomingDrone() {
		//    w   h sp mh id cm
		super(25,30,12f,6,ID.Enemy,true,70);
	}

	@Override
	public void update() {
		
		float shootsp = engine.BasicMath.clamp(WaveManager.o.wave/5f,1,3);
		if (timer > 8f/shootsp) {
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
		Game.g.drawImage(SpriteLoader.homingdrone, (int)x, (int)y, (int)w, (int)h,null);
		super.draw();
	}

	void shoot() {
		HomingBullet b = new HomingBullet(x, y+5, true, Color.pink, 50f,6,15f);
		Handler.obj.addObj(b);
	}
	
	@Override
	public void destroy() {
		PlayerData.o.homingDroneKills++;
		super.destroy();
	}
}
